package uci.develops.wiraenergimobile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.response.CustomerResponse;
import uci.develops.wiraenergimobile.service.RestClient;

/**
 * Created by user on 10/22/2016.
 */
public class FragmentProfileCustomerCompanyInfo extends Fragment {
    @BindView(R.id.textView_id) TextView textView_id;
    @BindView(R.id.textView_name) TextView textView_name;
    @BindView(R.id.textView_address) TextView textView_address;
    @BindView(R.id.textView_postcode) TextView textView_zip_code;
    @BindView(R.id.textView_phone) TextView textView_phone;
    @BindView(R.id.textView_mobile) TextView textView_mobile;
    @BindView(R.id.textView_fax) TextView textView_fax;
    @BindView(R.id.textView_term) TextView textView_term;
    @BindView(R.id.textView_npwp) TextView textView_npwp;
    @BindView(R.id.textView_email) TextView textView_email;
    @BindView(R.id.textView_website) TextView textView_website;
    @BindView(R.id.textView_note) TextView textView_note;
    @BindView(R.id.textView_city) TextView textView_city;
    @BindView(R.id.textView_province) TextView textView_province;
    @BindView(R.id.textView_valuta) TextView textView_valuta;
    @BindView(R.id.textView_tax_ppn) TextView textView_tax_ppn;
    @BindView(R.id.textView_active) TextView textView_active;
    @BindView(R.id.textView_group) TextView textView_group;

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
        ButterKnife.bind(this, view);
        decode = new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "customer_decode");
        token = new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "token");

        loadData();

        return view;
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
                        textView_zip_code.setText(customerModel.getPostcode() == null ? "" : customerModel.getPostcode());
                        textView_phone.setText(customerModel.getPhone() == null ? "" : customerModel.getPhone());
                        textView_mobile.setText(customerModel.getMobile() == null ? "" : customerModel.getMobile());
                        textView_fax.setText(customerModel.getFax() == null ? "" : customerModel.getFax());
                        textView_term.setText(customerModel.getTerm() == null ? "" : customerModel.getTerm());
                        textView_npwp.setText(customerModel.getNpwp() == null ? "" : customerModel.getNpwp());
                        textView_email.setText(customerModel.getEmail() == null ? "" : customerModel.getEmail());
                        textView_website.setText(customerModel.getWebsite() == null ? "" : customerModel.getWebsite());
                        textView_note.setText(customerModel.getNote() == null ? "" : customerModel.getNote());
                        textView_city.setText(customerModel.getCity() == null ? "" : customerModel.getCity());
                        textView_province.setText(customerModel.getProvince() == null ? "" : customerModel.getProvince());
                        textView_valuta.setText(customerModel.getValuta() == null ? "" : customerModel.getValuta());
                        textView_tax_ppn.setText(customerModel.getTax() == null ? "" : customerModel.getTax());
                        textView_group.setText(customerModel.getGroup() == null ? "" : customerModel.getGroup());

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
