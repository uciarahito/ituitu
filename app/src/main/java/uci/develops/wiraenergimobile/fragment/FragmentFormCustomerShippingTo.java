package uci.develops.wiraenergimobile.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.activity.MapsCoordinateActivity;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerAddressModel;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.response.CustomerAddressResponse;
import uci.develops.wiraenergimobile.response.CustomerResponse;
import uci.develops.wiraenergimobile.service.RestClient;

/**
 * Created by user on 10/22/2016.
 */

public class FragmentFormCustomerShippingTo extends Fragment {

    private EditText editText_pic_name, editText_address_name, editText_address,
            editText_phone, editText_mobile, editText_map_cordinate;
    private TextView textView_address_id;
    String pic_name = "", address_name = "", address = "", map = "", phone = "", mobile = "";

    private String decode = "", token = "";

    public FragmentFormCustomerShippingTo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
//        view = inflater.inflate(R.layout.fragment_form_customer_shipping_to, container, false);
        view = inflater.inflate(R.layout.fragment_shipping_new, container, false);

        decode = new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "customer_decode");
        token = new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "token");

        initializeComponent(view);
        loadData();

        editText_map_cordinate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(getContext(), MapsCoordinateActivity.class);
                startActivity(intent);
                return false;
            }
        });

        return view;
    }

    private void initializeComponent(View view) {
        editText_pic_name = (EditText) view.findViewById(R.id.editText_name);
        editText_address_name = (EditText) view.findViewById(R.id.editText_address_name);
        editText_address = (EditText) view.findViewById(R.id.editText_address);
        editText_map_cordinate = (EditText) view.findViewById(R.id.editText_map_coordinate);
        editText_phone = (EditText) view.findViewById(R.id.editText_phone);
        editText_mobile = (EditText) view.findViewById(R.id.editText_mobile);

        textView_address_id = (TextView) view.findViewById(R.id.textView_address_id);

        CustomerAddressModel customerAddressModel = new CustomerAddressModel();
        textView_address_id.setText(""+customerAddressModel.getId());

        if (new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "roles").equals("")) {
            if (Integer.parseInt(new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "approve")) == 0) {
                readOnly();
            }
        }
    }

    public boolean isNotEmpty() {
        pic_name = editText_pic_name.getText().toString();
        address_name = editText_address_name.getText().toString();
        address = editText_address.getText().toString();
        phone = editText_phone.getText().toString();
        mobile = editText_mobile.getText().toString();
        map = editText_map_cordinate.getText().toString();

        boolean result = false;
        if (!pic_name.equals("") && !address_name.equals("") && !address.equals("") && !phone.equals("") && !mobile.equals("")) {

            result = true;
        }
        return result;
    }

    public CustomerAddressModel getFormValue() {
        CustomerAddressModel customerAddressModel = new CustomerAddressModel();
        customerAddressModel.setName(address_name);
        customerAddressModel.setAddress(address);
        customerAddressModel.setPic(pic_name);
        customerAddressModel.setPhone(phone);
        customerAddressModel.setMobile(mobile);
        customerAddressModel.setMap(map);

        return customerAddressModel;
    }

    private void loadData() {
        Call<CustomerAddressResponse> customerAddressResponseCall = RestClient.getRestClient().getCustomerAddress("Bearer " + token, decode);
        customerAddressResponseCall.enqueue(new Callback<CustomerAddressResponse>() {
            @Override
            public void onResponse(Call<CustomerAddressResponse> call, Response<CustomerAddressResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getData().size() > 0) {
                        CustomerAddressModel customerAddressModel = new CustomerAddressModel();
                        customerAddressModel = response.body().getData().get(0);
                        editText_pic_name.setText(customerAddressModel.getPic() == null ? "" : customerAddressModel.getPic());
                        editText_address_name.setText(customerAddressModel.getName() == null ? "" : customerAddressModel.getName());
                        editText_address.setText(customerAddressModel.getAddress() == null ? "" : customerAddressModel.getAddress());
                        editText_phone.setText(customerAddressModel.getPhone() == null ? "" : customerAddressModel.getPhone());
                        editText_mobile.setText(customerAddressModel.getMobile() == null ? "" : customerAddressModel.getMobile());
                        editText_map_cordinate.setText(customerAddressModel.getMap() == null ? "" : customerAddressModel.getMap());

                        if (new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "roles").equals("admin")) {
                            if (Integer.parseInt(new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "approve")) == 1) {
                                readOnly();
                            }
                        }

                        else if (new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "roles").equals("customer")) {
                            if (Integer.parseInt(new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "approve")) == 1) {
                                readOnly();
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomerAddressResponse> call, Throwable t) {

            }
        });
    }

    public void readOnly() {
        editText_pic_name.setEnabled(false);
        editText_address_name.setEnabled(false);
        editText_address.setEnabled(false);
        editText_phone.setEnabled(false);
        editText_mobile.setEnabled(false);
        editText_map_cordinate.setEnabled(false);
    }
}
