package uci.develops.wiraenergimobile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerGroupModel;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.response.CustomerGroupResponse;
import uci.develops.wiraenergimobile.response.CustomerResponse;
import uci.develops.wiraenergimobile.service.RestClient;

/**
 * Created by user on 10/22/2016.
 */
public class FragmentQuotationShippingAddress extends Fragment {

    private EditText editText_shipping_address, editText_shipping_PIC, editText_shipping_phone, editText_shipping_mobile;
    private Spinner spinner_shipping_address_name;

    String address_name = "", address = "", shipping_PIC = "", shipping_phone = "", shipping_mobile = "";

    private String decode = "", token = "";

    public FragmentQuotationShippingAddress() {
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
//        view = inflater.inflate(R.layout.fragment_form_customer_company_info, container, false);
        view = inflater.inflate(R.layout.fragment_shipping_address, container, false);

        decode = new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "customer_decode");
        token = new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "token");

        initializeComponent(view);
        loadData();

        return view;
    }

    private void initializeComponent(View view) {
        editText_shipping_address = (EditText) view.findViewById(R.id.editText_shipping_address);
        editText_shipping_PIC = (EditText) view.findViewById(R.id.editText_shipping_PIC);
        editText_shipping_phone = (EditText) view.findViewById(R.id.editText_shipping_phone);
        editText_shipping_mobile = (EditText) view.findViewById(R.id.editText_shipping_mobile);
        spinner_shipping_address_name = (Spinner) view.findViewById(R.id.spinner_shipping_address_name);

    }

    public boolean isNotEmpty() {
        address_name = spinner_shipping_address_name.getSelectedItem().toString();
        address = editText_shipping_address.getText().toString();
        shipping_PIC = editText_shipping_PIC.getText().toString();
        shipping_phone = editText_shipping_phone.getText().toString();
        shipping_mobile = editText_shipping_mobile.getText().toString();

        boolean result = false;

        if (!address_name.equals("") && !address.equals("") && !shipping_PIC.equals("") && !shipping_phone.equals("")
                && !shipping_mobile.equals("")) {
            result = true;
        }
        return result;
    }

    public CustomerModel getFormValue() {
        CustomerModel customerModel = new CustomerModel();
//        customerModel.setShipping_address_name(address_name);
        customerModel.setAddress(address);
        customerModel.setShipping_pic(shipping_PIC);
        customerModel.setShipping_phone(shipping_phone);
        customerModel.setShipping_mobile(shipping_mobile);

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
                        editText_shipping_address.setText(customerModel.getShipping_address() == null ? "" : customerModel.getShipping_address());
                        editText_shipping_PIC.setText(customerModel.getShipping_pic() == null ? "" : customerModel.getShipping_pic());
                        editText_shipping_phone.setText(customerModel.getNpwp() == null ? "" : customerModel.getShipping_phone());
                        editText_shipping_mobile.setText(customerModel.getEmail() == null ? "" : customerModel.getShipping_email());

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
        editText_shipping_address.setEnabled(false);
        editText_shipping_PIC.setEnabled(false);
        editText_shipping_phone.setEnabled(false);
        editText_shipping_mobile.setEnabled(false);
        spinner_shipping_address_name.setEnabled(false);
    }
}
