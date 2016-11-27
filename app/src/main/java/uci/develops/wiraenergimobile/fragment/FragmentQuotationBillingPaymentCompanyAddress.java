package uci.develops.wiraenergimobile.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.activity.HomeActivity;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.response.CustomerResponse;
import uci.develops.wiraenergimobile.service.RestClient;

public class FragmentQuotationBillingPaymentCompanyAddress extends Fragment{

    private TextView textView_company_address, textView_name_pic1, textView_name_pic2, textView_name_pic3, textView_position1,
            textView_position2, textView_position3, textView_phone1, textView_phone2, textView_phone3, textView_mobile1,
            textView_mobile2, textView_mobile3, textView_email1, textView_email2, textView_email3;
    private CheckBox checkbox1, checkbox2, checkbox3;

    public FragmentQuotationBillingPaymentCompanyAddress(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_req_quotation_billpayment_company_address, container, false);

        initializeComponent(view);
        loadDataSpinnerAddressName();

        // start listening for refresh local file list in
        return view;
        //return inflater.inflate(R.layout.activity_ongoing_order, container, false);
    }

    private void initializeComponent(View view){
        textView_company_address = (TextView) view.findViewById(R.id.textView_company_address);
        textView_name_pic1 = (TextView) view.findViewById(R.id.textView_name_pic1);
        textView_name_pic2 = (TextView) view.findViewById(R.id.textView_name_pic2);
        textView_name_pic3 = (TextView) view.findViewById(R.id.textView_name_pic3);
        textView_position1 = (TextView) view.findViewById(R.id.textView_position1);
        textView_position2 = (TextView) view.findViewById(R.id.textView_position2);
        textView_position3 = (TextView) view.findViewById(R.id.textView_position3);
        textView_phone1 = (TextView) view.findViewById(R.id.textView_phone1);
        textView_phone2 = (TextView) view.findViewById(R.id.textView_phone2);
        textView_phone3 = (TextView) view.findViewById(R.id.textView_phone3);
        textView_mobile1 = (TextView) view.findViewById(R.id.textView_mobile1);
        textView_mobile2 = (TextView) view.findViewById(R.id.textView_mobile2);
        textView_mobile3 = (TextView) view.findViewById(R.id.textView_mobile3);
        textView_email1 = (TextView) view.findViewById(R.id.textView_email1);
        textView_email2 = (TextView) view.findViewById(R.id.textView_email2);
        textView_email3 = (TextView) view.findViewById(R.id.textView_email3);

        checkbox1 = (CheckBox) view.findViewById(R.id.checkbox1);
        checkbox2 = (CheckBox) view.findViewById(R.id.checkbox2);
        checkbox3 = (CheckBox) view.findViewById(R.id.checkbox3);
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
        Call<CustomerResponse> customerResponseCall = RestClient.getRestClient().getCustomer("Bearer " +
                        new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "token"),
                new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "customer_decode"));
        customerResponseCall.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if(response.isSuccessful()){
                    CustomerModel customerModel = response.body().getData().get(0);
                    textView_company_address.setText(customerModel.getAddress() == null ? "" : customerModel.getAddress());
                    textView_name_pic1.setText(customerModel.getFirst_name() == null ? "" : customerModel.getFirst_name()+" "+customerModel.getLast_name());
                    textView_name_pic2.setText(customerModel.getName2() == null ? "" : customerModel.getName2());
                    textView_name_pic3.setText(customerModel.getName3() == null ? "" : customerModel.getName3());
                    textView_phone1.setText(customerModel.getPhone() == null ? "" : customerModel.getPhone());
                    textView_phone2.setText(customerModel.getPhone2() == null ? "" : customerModel.getPhone2());
                    textView_phone3.setText(customerModel.getPhone3() == null ? "" : customerModel.getPhone3());
                    textView_mobile1.setText(customerModel.getMobile() == null ? "" : customerModel.getMobile());
                    textView_mobile2.setText(customerModel.getMobile2() == null ? "" : customerModel.getMobile2());
                    textView_mobile3.setText(customerModel.getMobile3() == null ? "" : customerModel.getMobile3());
                    textView_email1.setText(customerModel.getEmail() == null ? "" : customerModel.getEmail());
                    textView_email2.setText(customerModel.getEmail2() == null ? "" : customerModel.getEmail2());
                    textView_email3.setText(customerModel.getEmail3() == null ? "" : customerModel.getEmail3());
                    textView_position1.setText(customerModel.getJabatan1() == null ? "" : customerModel.getJabatan1());
                    textView_position2.setText(customerModel.getJabatan2() == null ? "" : customerModel.getJabatan2());
                    textView_position3.setText(customerModel.getJabatan3() == null ? "" : customerModel.getJabatan3());
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                Intent intent = new Intent(getActivity().getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
