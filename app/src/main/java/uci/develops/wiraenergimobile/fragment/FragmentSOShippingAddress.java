package uci.develops.wiraenergimobile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerAddressModel;
import uci.develops.wiraenergimobile.response.ListCustomerAddressResponse;
import uci.develops.wiraenergimobile.service.RestClient;

public class FragmentSOShippingAddress extends Fragment{

    private TextView textView_address, textView_pic_name, textView_phone, textView_mobile;
    private Spinner spinner_customer_address;
    List<CustomerAddressModel> customerAddressModels;
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
                        spinner_customer_address.setAdapter(dataAdapter);

                        spinner_customer_address.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Toast.makeText(getActivity().getApplicationContext(), "" + check_List[position], Toast.LENGTH_SHORT).show();
                                textView_address.setText("" + customerAddressModels.get(position).getAddress());
                                textView_pic_name.setText("" + customerAddressModels.get(position).getPic());
                                textView_phone.setText("" + customerAddressModels.get(position).getPhone());
                                textView_mobile.setText("" + customerAddressModels.get(position).getMobile());

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

}
