package uci.develops.wiraenergimobile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
public class FragmentProfileCustomerContactInfo extends Fragment {
    @BindView(R.id.textView_name1) TextView textView_name1;
    @BindView(R.id.textView_name2) TextView textView_name2;
    @BindView(R.id.textView_name3) TextView textView_name3;
    @BindView(R.id.textView_phone1) TextView textView_phone1;
    @BindView(R.id.textView_phone2) TextView textView_phone2;
    @BindView(R.id.textView_phone3) TextView textView_phone3;
    @BindView(R.id.textView_mobile1) TextView textView_mobile1;
    @BindView(R.id.textView_mobile2) TextView textView_mobile2;
    @BindView(R.id.textView_mobile3) TextView textView_mobile3;
    @BindView(R.id.textView_email1) TextView textView_email1;
    @BindView(R.id.textView_email2) TextView textView_email2;
    @BindView(R.id.textView_email3) TextView textView_email3;
    @BindView(R.id.textView_jabatan1) TextView textView_jabatan1;
    @BindView(R.id.textView_jabatan2) TextView textView_jabatan2;
    @BindView(R.id.textView_jabatan3) TextView textView_jabatan3;

    private String decode = "", token = "";

    public FragmentProfileCustomerContactInfo() {
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
        view = inflater.inflate(R.layout.fragment_profil_contact, container, false);
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
                        textView_name1.setText(customerModel.getFirst_name() == null ? "" : customerModel.getFirst_name()+" "+customerModel.getLast_name());
                        textView_name2.setText(customerModel.getName2() == null ? "" : customerModel.getName2());
                        textView_name3.setText(customerModel.getName3() == null ? "" : customerModel.getName3());
                        textView_phone1.setText(customerModel.getPhone1() == null ? "" : customerModel.getPhone1());
                        textView_phone2.setText(customerModel.getPhone2() == null ? "" : customerModel.getPhone2());
                        textView_phone3.setText(customerModel.getPhone3() == null ? "" : customerModel.getPhone3());
                        textView_mobile1.setText(customerModel.getMobile1() == null ? "" : customerModel.getMobile1());
                        textView_mobile2.setText(customerModel.getMobile2() == null ? "" : customerModel.getMobile2());
                        textView_mobile3.setText(customerModel.getMobile3() == null ? "" : customerModel.getMobile3());
                        textView_email1.setText(customerModel.getEmail() == null ? "" : customerModel.getEmail());
                        textView_email2.setText(customerModel.getEmail2() == null ? "" : customerModel.getEmail2());
                        textView_email3.setText(customerModel.getEmail3() == null ? "" : customerModel.getEmail3());
                        textView_jabatan1.setText(customerModel.getJabatan1() == null ? "" : customerModel.getJabatan1());
                        textView_jabatan2.setText(customerModel.getJabatan2() == null ? "" : customerModel.getJabatan2());
                        textView_jabatan3.setText(customerModel.getJabatan3() == null ? "" : customerModel.getJabatan3());
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {

            }
        });
    }
}
