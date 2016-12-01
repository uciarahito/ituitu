package uci.develops.wiraenergimobile.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
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
import uci.develops.wiraenergimobile.activity.HomeActivity;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.response.CustomerResponse;
import uci.develops.wiraenergimobile.service.RestClient;

public class FragmentQuotationBillingPaymentCompanyAddress extends Fragment{

    private TextView textView_company_address, textView_name_pic, textView_position,
            textView_phone, textView_mobile, textView_email;
    private Spinner spinner_shipping_contact_info;
    List<CustomerModel> customerModelList;
    String check_List [];

//    private LinearLayout linear_layout_contact_info_1, linear_layout_contact_info_2, linear_layout_contact_info_3;

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
        loadDataSpinnerCompanyAddress();

        // start listening for refresh local file list in
        return view;
        //return inflater.inflate(R.layout.activity_ongoing_order, container, false);
    }

    private void initializeComponent(View view){
        textView_company_address = (TextView) view.findViewById(R.id.textView_company_address);
        textView_name_pic = (TextView) view.findViewById(R.id.textView_name_pic);
        textView_position = (TextView) view.findViewById(R.id.textView_position);
        textView_phone = (TextView) view.findViewById(R.id.textView_phone);
        textView_mobile = (TextView) view.findViewById(R.id.textView_mobile);
        textView_email = (TextView) view.findViewById(R.id.textView_email);
        spinner_shipping_contact_info = (Spinner) view.findViewById(R.id.spinner_shipping_contact_info);
//        linear_layout_contact_info_1 = (LinearLayout) view.findViewById(R.id.linear_layout_contact_info_1);
//        linear_layout_contact_info_2 = (LinearLayout) view.findViewById(R.id.linear_layout_contact_info_2);
//        linear_layout_contact_info_3 = (LinearLayout) view.findViewById(R.id.linear_layout_contact_info_3);

//        linear_layout_contact_info_1.setOnClickListener(this);
//        linear_layout_contact_info_2.setOnClickListener(this);
//        linear_layout_contact_info_3.setOnClickListener(this);
    }

    /**
     * ketika activity resume
     * melakukan register broadcast receiver
     */
    @Override
    public void onResume() {
        super.onResume();
    }

    private void loadDataSpinnerCompanyAddress(){
        customerModelList = new ArrayList<>();
        Call<CustomerResponse> customerResponseCall = RestClient.getRestClient().getCustomer("Bearer " +
                        new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "token"),
                new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "customer_decode"));
        customerResponseCall.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if (response.isSuccessful()) {
                    final CustomerModel customerModel = response.body().getData().get(0);
                    Toast.makeText(getContext(), "Address: " + response.body().getData().get(0).getAddress(), Toast.LENGTH_SHORT).show();
                    textView_company_address.setText(customerModel.getAddress() == null ? "" : customerModel.getAddress()
                            +" "+customerModel.getCity()+" "+customerModel.getProvince());

                    if(response.body().getData().size() > 0) {
//                        check_List = new String[response.body().getData().size()];
                        int index=0;
//                        for(customerModel : response.body().getData()){
//                            check_List[index] = customerModel.getName();
//                            index++;
//                        }
                        List<String> contactInfo = new ArrayList<String>();
                        contactInfo.add("Andi");
                        contactInfo.add("Anton");
                        contactInfo.add("Arif");

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                                R.layout.spinner_item, contactInfo);
                        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                        spinner_shipping_contact_info.setAdapter(dataAdapter);
                    }

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
