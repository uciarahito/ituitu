package uci.develops.wiraenergimobile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerAddressModel;
import uci.develops.wiraenergimobile.response.ListCustomerAddressResponse;
import uci.develops.wiraenergimobile.service.RestClient;

public class FragmentQuotationBillingPaymentShippingAddress extends Fragment{

    private Spinner spinner_shipping_address_name;
    private EditText editText_shipping_address, editText_shipping_PIC, editText_shipping_phone, editText_shipping_mobile;

    private String address_name = "", address = "", pic = "", phone = "", mobile = "";
    List<CustomerAddressModel> customerAddressModels;
    String check_List [];

    public FragmentQuotationBillingPaymentShippingAddress(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_req_quotation_billpayment_shipping_address, container, false);

        initializeComponent(view);
        loadDataSpinnerAddressName();

        // start listening for refresh local file list in
        return view;
        //return inflater.inflate(R.layout.activity_ongoing_order, container, false);
    }

    private void initializeComponent(View view){
        spinner_shipping_address_name = (Spinner) view.findViewById(R.id.spinner_shipping_address_name);
        editText_shipping_address = (EditText) view.findViewById(R.id.editText_shipping_address);
        editText_shipping_PIC = (EditText) view.findViewById(R.id.editText_shipping_PIC);
        editText_shipping_phone = (EditText) view.findViewById(R.id.editText_shipping_phone);
        editText_shipping_mobile = (EditText) view.findViewById(R.id.editText_shipping_mobile);
    }

    /**
     * ketika activity resume
     * melakukan register broadcast receiver
     */
    @Override
    public void onResume() {
        super.onResume();
    }

    private void loadDataSpinnerAddressName(){
        customerAddressModels = new ArrayList<>();
        Call<ListCustomerAddressResponse> customerAddressResponseCall = RestClient.getRestClient().getCustomerAddress("Bearer "
                        + new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "token"),
                new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "customer_decode"));
        customerAddressResponseCall.enqueue(new Callback<ListCustomerAddressResponse>() {
            @Override
            public void onResponse(Call<ListCustomerAddressResponse> call, Response<ListCustomerAddressResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getData().size() > 0) {
                        check_List = new String[response.body().getData().size()];
                        int index = 0;
                        for (CustomerAddressModel customerAddressModel : response.body().getData()) {
                            check_List[index] = customerAddressModel.getName();
                            index++;
                        }

                        customerAddressModels = response.body().getData();

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                                R.layout.spinner_item, check_List);
                        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                        spinner_shipping_address_name.setAdapter(dataAdapter);

                        spinner_shipping_address_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Toast.makeText(getActivity().getApplicationContext(), "" + check_List[position], Toast.LENGTH_SHORT).show();
                                editText_shipping_address.setText("" + customerAddressModels.get(position).getAddress());
                                editText_shipping_PIC.setText("" + customerAddressModels.get(position).getPic());
                                editText_shipping_phone.setText("" + customerAddressModels.get(position).getPhone());
                                editText_shipping_mobile.setText("" + customerAddressModels.get(position).getMobile());
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<ListCustomerAddressResponse> call, Throwable t) {

            }
        });
    }

    public boolean isNotEmpty() {
        address_name = spinner_shipping_address_name.getSelectedItem().toString();
        address = editText_shipping_address.getText().toString();
        pic = editText_shipping_PIC.getText().toString();
        phone = editText_shipping_phone.getText().toString();
        mobile = editText_shipping_mobile.getText().toString();

        boolean result = false;
        if (!address_name.equals("") && !address.equals("") && !pic.equals("") && !phone.equals("") && !mobile.equals("")) {
            result = true;
        }
        return result;
    }

    public CustomerAddressModel getFormValue() {
        CustomerAddressModel customerAddressModel = new CustomerAddressModel();
        customerAddressModel.setName(address_name);
        customerAddressModel.setAddress(address);
        customerAddressModel.setPic(pic);
        customerAddressModel.setPhone(phone);
        customerAddressModel.setMobile(mobile);

        return customerAddressModel;
    }

    public void readOnly() {
        spinner_shipping_address_name.setEnabled(false);
        editText_shipping_address.setEnabled(false);
        editText_shipping_PIC.setEnabled(false);
        editText_shipping_phone.setEnabled(false);
        editText_shipping_mobile.setEnabled(false);
    }

}
