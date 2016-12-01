package uci.develops.wiraenergimobile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.activity.FormCustomerActivity;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerGroupModel;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.response.CustomerGroupResponse;
import uci.develops.wiraenergimobile.response.CustomerResponse;
import uci.develops.wiraenergimobile.service.RestClient;

/**
 * Created by user on 10/22/2016.
 */
public class FragmentProfileCustomerCompanyInfo extends Fragment {

    private TextView textView_id, textView_name, textView_address, textView_zip_code,
            textView_phone, textView_mobile, textView_fax, textView_term, textView_npwp,
            textView_email, textView_website, textView_note, textView_city, textView_province,
            textView_valuta, textView_tax_ppn, textView_active, textView_group;

    private String decode = "", token = "";

    public FragmentProfileCustomerCompanyInfo() {
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
        view = inflater.inflate(R.layout.fragment_profil_company, container, false);

        decode = new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "customer_decode");
        token = new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "token");

        initializeComponent(view);
        loadData();

        return view;
    }

    private void initializeComponent(View view) {
        textView_id = (TextView) view.findViewById(R.id.textView_id);
        textView_name = (TextView) view.findViewById(R.id.textView_name);
        textView_address = (TextView) view.findViewById(R.id.textView_address);
        textView_city = (TextView) view.findViewById(R.id.textView_city);
        textView_province = (TextView) view.findViewById(R.id.textView_province);
        textView_zip_code = (TextView) view.findViewById(R.id.textView_postcode);
        textView_phone = (TextView) view.findViewById(R.id.textView_phone);
        textView_mobile = (TextView) view.findViewById(R.id.textView_mobile);
        textView_fax = (TextView) view.findViewById(R.id.textView_fax);
        textView_term = (TextView) view.findViewById(R.id.textView_term);
        textView_valuta = (TextView) view.findViewById(R.id.textView_valuta);
        textView_npwp = (TextView) view.findViewById(R.id.textView_npwp);
        textView_tax_ppn = (TextView) view.findViewById(R.id.textView_tax_ppn);
        textView_active = (TextView) view.findViewById(R.id.textView_active);
        textView_group = (TextView) view.findViewById(R.id.textView_group);
        textView_email = (TextView) view.findViewById(R.id.textView_email);
        textView_website = (TextView) view.findViewById(R.id.textView_website);
        textView_note = (TextView) view.findViewById(R.id.textView_note);
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
                        textView_id.setText(customerModel.getDecode() == null ? "" : customerModel.getDecode());
                        textView_name.setText(customerModel.getFirst_name() == null ? "" : customerModel.getFirst_name());
                        textView_address.setText(customerModel.getAddress() == null ? "" : customerModel.getAddress());
                        textView_city.setText(customerModel.getCity() == null ? "" : customerModel.getCity());
                        textView_province.setText(customerModel.getProvince() == null ? "" : customerModel.getProvince());
                        textView_phone.setText(customerModel.getPhone() == null ? "" : customerModel.getPhone());
                        textView_mobile.setText(customerModel.getMobile() == null ? "" : customerModel.getMobile());
                        textView_fax.setText(customerModel.getFax() == null ? "" : customerModel.getFax());
                        textView_term.setText(customerModel.getTerm() == null ? "" : customerModel.getTerm());
                        textView_valuta.setText(customerModel.getValuta() == null ? "" : customerModel.getValuta());
                        textView_npwp.setText(customerModel.getNpwp() == null ? "" : customerModel.getNpwp());
                        textView_email.setText(customerModel.getEmail() == null ? "" : customerModel.getEmail());
                        textView_website.setText(customerModel.getWebsite() == null ? "" : customerModel.getWebsite());
                        textView_note.setText(customerModel.getNote() == null ? "" : customerModel.getNote());
                        textView_zip_code.setText(customerModel.getPostcode() == null ? "" : customerModel.getPostcode());
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                Log.e("FragmentProfilCompany", "Failure" + t.getMessage());
            }
        });
    }
}
