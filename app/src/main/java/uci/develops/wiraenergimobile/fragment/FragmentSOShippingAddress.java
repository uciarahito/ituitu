package uci.develops.wiraenergimobile.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

public class FragmentSOShippingAddress extends Fragment{

    private TextView textView_address, textView_pic_name, textView_phone, textView_mobile;
    private Spinner spinner_customer_address;
    List<CustomerModel> customerModelList;
    String check_List [];

    public FragmentSOShippingAddress(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_req_so_shipping_address, container, false);

        initializeComponent(view);
        loadDataSpinnerShippingAddress();

        // start listening for refresh local file list in
        return view;
        //return inflater.inflate(R.layout.activity_ongoing_order, container, false);
    }

    private void initializeComponent(View view){
        textView_address = (TextView) view.findViewById(R.id.textView_address);
        textView_pic_name = (TextView) view.findViewById(R.id.textView_pic_name);
        textView_phone = (TextView) view.findViewById(R.id.textView_phone);
        textView_mobile = (TextView) view.findViewById(R.id.textView_mobile);
        spinner_customer_address = (Spinner) view.findViewById(R.id.spinner_customer_address);

    }

    /**
     * ketika activity resume
     * melakukan register broadcast receiver
     */
    @Override
    public void onResume() {
        super.onResume();
    }

    private void loadDataSpinnerShippingAddress(){
        customerModelList = new ArrayList<>();
        Call<CustomerResponse> customerResponseCall = RestClient.getRestClient().getCustomer("Bearer " +
                        new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "token"),
                new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "customer_decode"));
        customerResponseCall.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if (response.isSuccessful()) {
//                    final CustomerModel customerModel = response.body().getData().get(0);
//                    Toast.makeText(getContext(), "Address: " + response.body().getData().get(0).getAddress(), Toast.LENGTH_SHORT).show();

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
                        spinner_customer_address.setAdapter(dataAdapter);
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
