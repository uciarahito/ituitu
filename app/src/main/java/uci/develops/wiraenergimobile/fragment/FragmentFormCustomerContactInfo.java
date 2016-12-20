package uci.develops.wiraenergimobile.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.activity.FormCustomerActivity;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.response.CustomerResponse;
import uci.develops.wiraenergimobile.service.RestClient;

/**
 * Created by user on 10/22/2016.
 */
public class FragmentFormCustomerContactInfo extends Fragment {
    @BindView(R.id.editText_name1) EditText editText_name1;
    @BindView(R.id.editText_name2) EditText editText_name2;
    @BindView(R.id.editText_name3) EditText editText_name3;
    @BindView(R.id.editText_phone1) EditText editText_phone1;
    @BindView(R.id.editText_phone2) EditText editText_phone2;
    @BindView(R.id.editText_phone3) EditText editText_phone3;
    @BindView(R.id.editText_mobile1) EditText editText_mobile1;
    @BindView(R.id.editText_mobile2) EditText editText_mobile2;
    @BindView(R.id.editText_mobile3) EditText editText_mobile3;
    @BindView(R.id.editText_email1) EditText editText_email1;
    @BindView(R.id.editText_email2) EditText editText_email2;
    @BindView(R.id.editText_email3) EditText editText_email3;
    @BindView(R.id.editText_jabatan1) EditText editText_jabatan1;
    @BindView(R.id.editText_jabatan2) EditText editText_jabatan2;
    @BindView(R.id.editText_jabatan3) EditText editText_jabatan3;
    @BindView(R.id.linear_layout_contact_info_2_3) LinearLayout linear_layout_contact_info_2_3;

    String name1 = "", name2 = "", name3 = "", phone1 = "", phone2 = "", phone3 = "", mobile1 = "", mobile2 = "", mobile3 = "", email1 = "",
            email2 = "", email3 = "", jabatan1 = "", jabatan2 = "", jabatan3 = "";
    private String decode = "", token = "";

    public FragmentFormCustomerContactInfo() {
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
//        view = inflater.inflate(R.layout.fragment_form_customer_contact_info, container, false);
        view = inflater.inflate(R.layout.fragment_contact_new, container, false);
        ButterKnife.bind(this, view);
        decode = new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "customer_decode");
        token = new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "token");

        initializeComponent(view);

        loadData();

        return view;
    }

    private void initializeComponent(View view) {
        CustomerModel customerModel = new CustomerModel();
        editText_name1.setText(customerModel.getFirst_name() == null ? "" : customerModel.getFirst_name()+" "+customerModel.getLast_name());
        editText_email1.setText(customerModel.getEmail() == null ? "" : customerModel.getEmail());

        if (new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "roles").equals("")) {
            linear_layout_contact_info_2_3.setVisibility(View.GONE);
            if (new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "approve").equals("0")) {
                readOnly();
            }
        }
    }

    public boolean isNotEmpty() {
        name1 = editText_name1.getText().toString();
        name2 = editText_name2.getText().toString();
        name3 = editText_name3.getText().toString();
        phone1 = editText_phone1.getText().toString();
        phone2 = editText_phone2.getText().toString();
        phone3 = editText_phone3.getText().toString();
        mobile1 = editText_mobile1.getText().toString();
        mobile2 = editText_mobile2.getText().toString();
        mobile3 = editText_mobile3.getText().toString();
        email1 = editText_email1.getText().toString();
        email2 = editText_email2.getText().toString();
        email3 = editText_email3.getText().toString();
        jabatan1 = editText_jabatan1.getText().toString();
        jabatan2 = editText_jabatan2.getText().toString();
        jabatan3 = editText_jabatan3.getText().toString();

        boolean result = false;

        if (!name1.equals("") && !mobile1.equals("")) {
            result = true;
        }
        return result;
    }

    public CustomerModel getFormValue() {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setName1(name1);
        customerModel.setName2(name2);
        customerModel.setName3(name3);
        customerModel.setPhone1(phone1);
        customerModel.setPhone2(phone2);
        customerModel.setPhone3(phone3);
        customerModel.setMobile1(mobile1);
        customerModel.setMobile2(mobile2);
        customerModel.setMobile3(mobile3);
        customerModel.setEmail1(email1);
        customerModel.setEmail2(email2);
        customerModel.setEmail3(email3);
        customerModel.setJabatan1(jabatan1);
        customerModel.setJabatan2(jabatan2);
        customerModel.setJabatan3(jabatan3);

        FormCustomerActivity.customerModel_temp.setName1(name1);
        FormCustomerActivity.customerModel_temp.setName2(name2);
        FormCustomerActivity.customerModel_temp.setName3(name3);
        FormCustomerActivity.customerModel_temp.setPhone1(phone1);
        FormCustomerActivity.customerModel_temp.setPhone2(phone2);
        FormCustomerActivity.customerModel_temp.setPhone3(phone3);
        FormCustomerActivity.customerModel_temp.setMobile1(mobile1);
        FormCustomerActivity.customerModel_temp.setMobile2(mobile2);
        FormCustomerActivity.customerModel_temp.setMobile3(mobile3);
        FormCustomerActivity.customerModel_temp.setEmail1(email1);
        FormCustomerActivity.customerModel_temp.setEmail2(email2);
        FormCustomerActivity.customerModel_temp.setEmail3(email3);
        FormCustomerActivity.customerModel_temp.setJabatan1(jabatan1);
        FormCustomerActivity.customerModel_temp.setJabatan2(jabatan2);
        FormCustomerActivity.customerModel_temp.setJabatan3(jabatan3);

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
                        editText_name1.setText(customerModel.getFirst_name() == null ? "" : customerModel.getFirst_name()+" "+customerModel.getLast_name());
                        editText_name2.setText(customerModel.getName2() == null ? "" : customerModel.getName2());
                        editText_name3.setText(customerModel.getName3() == null ? "" : customerModel.getName3());
                        editText_phone1.setText(customerModel.getPhone1() == null ? "" : customerModel.getPhone1());
                        editText_phone2.setText(customerModel.getPhone2() == null ? "" : customerModel.getPhone2());
                        editText_phone3.setText(customerModel.getPhone3() == null ? "" : customerModel.getPhone3());
                        editText_mobile1.setText(customerModel.getMobile1() == null ? "" : customerModel.getMobile1());
                        editText_mobile2.setText(customerModel.getMobile2() == null ? "" : customerModel.getMobile2());
                        editText_mobile3.setText(customerModel.getMobile3() == null ? "" : customerModel.getMobile3());
                        editText_email1.setText(customerModel.getEmail() == null ? "" : customerModel.getEmail());
                        editText_email2.setText(customerModel.getEmail2() == null ? "" : customerModel.getEmail2());
                        editText_email3.setText(customerModel.getEmail3() == null ? "" : customerModel.getEmail3());
                        editText_jabatan1.setText(customerModel.getJabatan1() == null ? "" : customerModel.getJabatan1());
                        editText_jabatan2.setText(customerModel.getJabatan2() == null ? "" : customerModel.getJabatan2());
                        editText_jabatan3.setText(customerModel.getJabatan3() == null ? "" : customerModel.getJabatan3());

                        if (new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "roles").equals("admin")) {
                            if (customerModel.getApprove() == 1){
                                readOnly();
                            }
                        }

                        if (new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "roles").equals("customer")) {
                            if (customerModel.getApprove() == 1) {
                                readOnly();
                            }
                        }


//                        if (new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "roles").equals("")) {
//                            if (customerModel.getApprove() == 0) {
//                                readOnly();
//                            }
//                        } else if (new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "roles").equals("customer")) {
//                            if (customerModel.getApprove() == 1) {
//                                readOnly();
//                            }
//                        } else if (new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "roles").equals("admin")) {
//                            if (customerModel.getApprove() == 1) {
//                                readOnly();
//                            }
//                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {

            }
        });
    }

    public void readOnly() {
        editText_name1.setEnabled(false);
        editText_name2.setEnabled(false);
        editText_name3.setEnabled(false);
        editText_phone1.setEnabled(false);
        editText_phone2.setEnabled(false);
        editText_phone3.setEnabled(false);
        editText_mobile1.setEnabled(false);
        editText_mobile2.setEnabled(false);
        editText_mobile3.setEnabled(false);
        editText_email1.setEnabled(false);
        editText_email2.setEnabled(false);
        editText_email3.setEnabled(false);
        editText_jabatan1.setEnabled(false);
        editText_jabatan2.setEnabled(false);
        editText_jabatan3.setEnabled(false);
    }

}
