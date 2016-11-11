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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.activity.MapsCoordinateActivity;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.response.CustomerResponse;
import uci.develops.wiraenergimobile.service.RestClient;

/**
 * Created by user on 10/22/2016.
 */

public class FragmentFormCustomerShippingTo extends Fragment {

    private EditText editText_pic_name, editText_address_name, editText_address, editText_postcode, editText_eta,
            editText_email, editText_phone, editText_mobile, editText_fax, editText_tax, editText_map_cordinate, editText_note;
    private AutoCompleteTextView autoComplete_city, autoComplete_province;
    private LinearLayout linear_layout_eta, linear_layout_note, linear_layout_tax;

    String pic_name = "", address_name = "", address = "", city = "", province = "", postcode = "", eta = "", map = "", email = "", phone = "", mobile = "", fax = "", tax = "", note = "";

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
        editText_map_cordinate = (EditText) view.findViewById(R.id.editText_map_coordinate);

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
        editText_pic_name = (EditText) view.findViewById(R.id.editText_pic_name);
        editText_address_name = (EditText) view.findViewById(R.id.editText_address_name);
        editText_address = (EditText) view.findViewById(R.id.editText_address);
        autoComplete_city = (AutoCompleteTextView) view.findViewById(R.id.autoComplete_city);
        autoComplete_province = (AutoCompleteTextView) view.findViewById(R.id.autoComplete_province);
        editText_postcode = (EditText) view.findViewById(R.id.editText_postcode);
        editText_eta = (EditText) view.findViewById(R.id.editText_eta);
        editText_map_cordinate = (EditText) view.findViewById(R.id.editText_map_coordinate);
        editText_email = (EditText) view.findViewById(R.id.editText_email);
        editText_phone = (EditText) view.findViewById(R.id.editText_phone);
        editText_mobile = (EditText) view.findViewById(R.id.editText_mobile);
        editText_fax = (EditText) view.findViewById(R.id.editText_fax);
        editText_tax = (EditText) view.findViewById(R.id.editText_tax);
        editText_note = (EditText) view.findViewById(R.id.editText_note);
        linear_layout_eta = (LinearLayout) view.findViewById(R.id.linear_layout_eta);
        linear_layout_note = (LinearLayout) view.findViewById(R.id.linear_layout_note);
        linear_layout_tax = (LinearLayout) view.findViewById(R.id.linear_layout_tax);


        String[] province = getActivity().getResources().getStringArray(R.array.list_of_province);
        String[] city = getActivity().getResources().getStringArray(R.array.list_of_city);

        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, province);
        autoComplete_province.setAdapter(provinceAdapter);

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, city);
        autoComplete_city.setAdapter(cityAdapter);

        if(new SharedPreferenceManager().getPreferences(getContext(), "roles").equals("customer")){
            linear_layout_eta.setVisibility(View.GONE);
            linear_layout_note.setVisibility(View.GONE);
            linear_layout_tax.setVisibility(View.GONE);
        }
    }

    public boolean isNotEmpty() {
        pic_name = editText_pic_name.getText().toString();
//        address_name = editText_address_name.getText().toString();
        address = editText_address.getText().toString();
        city = autoComplete_city.getText().toString();
        province = autoComplete_province.getText().toString();
        postcode = editText_postcode.getText().toString();
        eta = editText_eta.getText().toString();
        map = editText_map_cordinate.getText().toString();
        phone = editText_phone.getText().toString();
        mobile = editText_mobile.getText().toString();
        email = editText_email.getText().toString();
        fax = editText_fax.getText().toString();
        tax = editText_fax.getText().toString();
        note = editText_note.getText().toString();

        //cek jika role == admin maka visible, selain admin invisible
        editText_eta.setVisibility(View.INVISIBLE);

        boolean result = false;
        if (!pic_name.equals("") && !address.equals("") && !city.equals("") && !province.equals("") &&
                !postcode.equals("") && !email.equals("") && !phone.equals("") && !mobile.equals("")) {

            result = true;
        }
        return result;
    }

    public CustomerModel getFormValue() {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setShipping_pic(pic_name);
        customerModel.setShipping_address(address);
        customerModel.setShipping_city(city);
        customerModel.setShipping_province(province);
        customerModel.setShipping_postcode(postcode);
        customerModel.setShipping_eta(eta);
        customerModel.setShipping_map(map);
        customerModel.setShipping_phone(phone);
        customerModel.setShipping_mobile(mobile);
        customerModel.setShipping_email(email);
        customerModel.setShipping_fax(fax);
        customerModel.setShipping_tax(tax);
        customerModel.setShipping_note(note);

        return customerModel;
    }

    private void loadData() {
        Call<CustomerResponse> customerResponseCall = RestClient.getRestClient().getCustomer("Bearer " + token, decode);
        customerResponseCall.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getData().size() > 0) {
                        CustomerModel customerModel = new CustomerModel();
                        customerModel = response.body().getData().get(0);
                        editText_pic_name.setText(customerModel.getShipping_pic() == null ? "" : customerModel.getShipping_pic());
                        editText_address.setText(customerModel.getShipping_address() == null ? "" : customerModel.getShipping_address());
                        autoComplete_city.setText(customerModel.getShipping_city() == null ? "" : customerModel.getShipping_city());
                        autoComplete_province.setText(customerModel.getShipping_province() == null ? "" : customerModel.getShipping_province());
                        editText_postcode.setText(customerModel.getShipping_postcode() == null ? "" : customerModel.getShipping_postcode());
                        editText_eta.setText(customerModel.getShipping_eta() == null ? "" : customerModel.getShipping_eta());
                        editText_map_cordinate.setText(customerModel.getShipping_map() == null ? "" : customerModel.getShipping_map());
                        editText_phone.setText(customerModel.getShipping_phone() == null ? "" : customerModel.getShipping_phone());
                        editText_mobile.setText(customerModel.getShipping_mobile() == null ? "" : customerModel.getShipping_mobile());
                        editText_email.setText(customerModel.getShipping_email() == null ? "" : customerModel.getShipping_email());
                        editText_fax.setText(customerModel.getShipping_fax() == null ? "" : customerModel.getShipping_fax());
                        editText_tax.setText(customerModel.getShipping_tax() == null ? "" : customerModel.getShipping_tax());
                        editText_note.setText(customerModel.getShipping_note() == null ? "" : customerModel.getShipping_note());

                        if (new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "roles").equals("customer")) {
                            if (customerModel.getApprove() == 0 | customerModel.getApprove() == 1) {
                                readOnly();
                            }
                        } else if (new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "roles").equals("admin")) {
                            if (customerModel.getApprove() == 1) {
                                readOnly();
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {

            }
        });
    }

    public void readOnly() {
        editText_pic_name.setEnabled(false);
        editText_address.setEnabled(false);
        autoComplete_city.setEnabled(false);
        autoComplete_province.setEnabled(false);
        editText_postcode.setEnabled(false);
        editText_eta.setEnabled(false);
        editText_map_cordinate.setEnabled(false);
        editText_email.setEnabled(false);
        editText_phone.setEnabled(false);
        editText_mobile.setEnabled(false);
        editText_fax.setEnabled(false);
        editText_tax.setEnabled(false);
        editText_note.setEnabled(false);

    }
}
