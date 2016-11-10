package uci.develops.wiraenergimobile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.activity.LoginActivity;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.response.CustomerResponse;
import uci.develops.wiraenergimobile.service.RestClient;

/**
 * Created by user on 10/22/2016.
 */
public class FragmentFormCustomerCompanyInfo extends Fragment {

    private EditText editText_id, editText_first_name, editText_address, editText_zip_code,
            editText_phone, editText_mobile, editText_fax, editText_term, editText_npwp,
            editText_email, editText_website, editText_note;
    private AutoCompleteTextView autoComplete_city, autoComplete_province;
    private Spinner spinner_valuta, spinner_tax_ppn, spinner_active, spinner_group;

    private LinearLayout linear_layout_id, linear_layout_term, linear_layout_valuta, linear_layout_tax_ppn, linear_layout_active, linear_layout_note;

    String id = "", name = "", address = "", city = "", province = "", zip_code = "", phone = "", mobile = "", fax = "", term = "",
            valuta = "", group = "", npwp = "", tax_ppn = "", active = "", email = "", website = "", note = "";

    private String decode = "", token = "";

    public FragmentFormCustomerCompanyInfo() {
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
        view = inflater.inflate(R.layout.fragment_form_customer_company_info, container, false);

        decode = new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "customer_decode");
        token = new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "token");

        initializeComponent(view);
        loadData();

        return view;
    }

    private void initializeComponent(View view) {
        editText_id = (EditText) view.findViewById(R.id.editText_id);
        editText_first_name = (EditText) view.findViewById(R.id.editText_name);
        editText_address = (EditText) view.findViewById(R.id.editText_address);
        autoComplete_city = (AutoCompleteTextView) view.findViewById(R.id.autoComplete_city);
        autoComplete_province = (AutoCompleteTextView) view.findViewById(R.id.autoComplete_province);
        editText_zip_code = (EditText) view.findViewById(R.id.editText_postcode);
        editText_phone = (EditText) view.findViewById(R.id.editText_phone);
        editText_mobile = (EditText) view.findViewById(R.id.editText_mobile);
        editText_fax = (EditText) view.findViewById(R.id.editText_fax);
        editText_term = (EditText) view.findViewById(R.id.editText_term);
        spinner_valuta = (Spinner) view.findViewById(R.id.spinner_valuta);
        editText_npwp = (EditText) view.findViewById(R.id.editText_npwp);
        spinner_tax_ppn = (Spinner) view.findViewById(R.id.spinner_tax_ppn);
        spinner_active = (Spinner) view.findViewById(R.id.spinner_active);
        spinner_group = (Spinner) view.findViewById(R.id.spinner_group);
        editText_email = (EditText) view.findViewById(R.id.editText_email);
        editText_website = (EditText) view.findViewById(R.id.editText_website);
        editText_note = (EditText) view.findViewById(R.id.editText_note);

        linear_layout_id = (LinearLayout) view.findViewById(R.id.linear_layout_id);
        linear_layout_term = (LinearLayout) view.findViewById(R.id.linear_layout_term);
        linear_layout_valuta = (LinearLayout) view.findViewById(R.id.linear_layout_valuta);
        linear_layout_tax_ppn = (LinearLayout) view.findViewById(R.id.linear_layout_tax_ppn);
        linear_layout_active = (LinearLayout) view.findViewById(R.id.linear_layout_active);
        linear_layout_note = (LinearLayout) view.findViewById(R.id.linear_layout_note);

        editText_id.setText("" + new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "customer_decode"));
        if (new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "roles").equals("customer")){
            linear_layout_id.setVisibility(View.GONE);
            linear_layout_term.setVisibility(View.GONE);
            linear_layout_valuta.setVisibility(View.GONE);
            linear_layout_tax_ppn.setVisibility(View.GONE);
            linear_layout_active.setVisibility(View.GONE);
            linear_layout_note.setVisibility(View.GONE);
        }

        List<String> valutas = new ArrayList<String>();
        valutas.add("Rupiah");
        valutas.add("US Dollar");
        ArrayAdapter<String> valutaAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_item, valutas);
        valutaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_valuta.setAdapter(valutaAdapter);

        List<String> check_List = new ArrayList<String>();
        check_List.add("No");
        check_List.add("Yes");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_item, check_List);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_tax_ppn.setAdapter(dataAdapter);
        spinner_active.setAdapter(dataAdapter);
        spinner_group.setAdapter(dataAdapter);

        String[] province = getActivity().getResources().getStringArray(R.array.list_of_province);
        String[] city = getActivity().getResources().getStringArray(R.array.list_of_city);

        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, province);
        autoComplete_province.setAdapter(provinceAdapter);

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, city);
        autoComplete_city.setAdapter(cityAdapter);
    }

    public boolean isNotEmpty() {
        name = editText_first_name.getText().toString();
        address = editText_address.getText().toString();
        city = autoComplete_city.getText().toString();
        province = autoComplete_province.getText().toString();
        zip_code = editText_zip_code.getText().toString();
        phone = editText_phone.getText().toString();
        mobile = editText_mobile.getText().toString();
        fax = editText_fax.getText().toString();
        term = editText_term.getText().toString();
        valuta = spinner_valuta.getSelectedItem().toString();
        group = spinner_group.getSelectedItem().toString();
        npwp = editText_npwp.getText().toString();
        tax_ppn = spinner_tax_ppn.getSelectedItem().toString();
        active = spinner_active.getSelectedItem().toString();
        email = editText_email.getText().toString();
        website = editText_website.getText().toString();
        note = editText_note.getText().toString();

        boolean result = false;

        if (!name.equals("") && !address.equals("") && !city.equals("") && !province.equals("")
                && !mobile.equals("") && !npwp.equals("") && !email.equals("")) {
            result = true;
        }
        return result;
    }

    public CustomerModel getFormValue() {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setFirst_name(name);
        customerModel.setLast_name(name);
        customerModel.setAddress(address);
        customerModel.setCity(city);
        customerModel.setProvince(province);
        customerModel.setPhone(phone);
        customerModel.setMobile(mobile);
        customerModel.setFax(fax);
        customerModel.setTerm(group);
        customerModel.setTerm(term);
        customerModel.setValuta(valuta);
        customerModel.setNpwp(npwp);
        customerModel.setTax(tax_ppn);
        customerModel.setEmail(email);
        customerModel.setWebsite(website);
        customerModel.setNote(note);
        customerModel.setPostcode(zip_code);

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
                        editText_first_name.setText(customerModel.getFirst_name() == null ? "" : customerModel.getFirst_name());
                        editText_address.setText(customerModel.getAddress() == null ? "" : customerModel.getAddress());
                        autoComplete_city.setText(customerModel.getCity() == null ? "" : customerModel.getCity());
                        autoComplete_province.setText(customerModel.getProvince() == null ? "" : customerModel.getProvince());
                        editText_phone.setText(customerModel.getPhone() == null ? "" : customerModel.getPhone());
                        editText_mobile.setText(customerModel.getMobile() == null ? "" : customerModel.getMobile());
                        editText_fax.setText(customerModel.getFax() == null ? "" : customerModel.getFax());
                        editText_term.setText(customerModel.getTerm() == null ? "" : customerModel.getTerm());
                        editText_npwp.setText(customerModel.getNpwp() == null ? "" : customerModel.getNpwp());
                        editText_email.setText(customerModel.getEmail() == null ? "" : customerModel.getEmail());
                        editText_website.setText(customerModel.getWebsite() == null ? "" : customerModel.getWebsite());
                        editText_note.setText(customerModel.getNote() == null ? "" : customerModel.getNote());
                        editText_zip_code.setText(customerModel.getPostcode() == null ? "" : customerModel.getPostcode());

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
        editText_id.setEnabled(false);
        editText_first_name.setEnabled(false);
        editText_address.setEnabled(false);
        autoComplete_city.setEnabled(false);
        autoComplete_province.setEnabled(false);
        editText_zip_code.setEnabled(false);
        editText_phone.setEnabled(false);
        editText_mobile.setEnabled(false);
        editText_fax.setEnabled(false);
        spinner_group.setEnabled(false);
        editText_term.setEnabled(false);
        spinner_valuta.setEnabled(false);
        editText_npwp.setEnabled(false);
        spinner_tax_ppn.setEnabled(false);
        spinner_active.setEnabled(false);
        editText_email.setEnabled(false);
        editText_website.setEnabled(false);
        editText_note.setEnabled(false);
    }
}
