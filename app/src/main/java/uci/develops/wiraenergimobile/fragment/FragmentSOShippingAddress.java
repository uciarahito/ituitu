package uci.develops.wiraenergimobile.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerAddressModel;
import uci.develops.wiraenergimobile.response.ListCustomerAddressResponse;
import uci.develops.wiraenergimobile.service.RestClient;
import uci.develops.wiraenergimobile.library.SearchableSpinner;


public class FragmentSOShippingAddress extends Fragment{

    @BindView(R.id.textView_address) TextView textView_address;
    @BindView(R.id.textView_pic_name) TextView textView_pic_name;
    @BindView(R.id.textView_phone) TextView textView_phone;
    @BindView(R.id.textView_mobile) TextView textView_mobile;
    @BindView(R.id.spinner_customer_address) SearchableSpinner spinner_customer_address;

    List<CustomerAddressModel> customerAddressModels;
    String check_List [];
    private BroadcastReceiver mRegistrationBroadcastReceiver;

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
        ButterKnife.bind(this, view);
        loadDataSpinnerShippingAddress();

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                //If the broadcast has received with success
                //that means device is registered successfully
                if (intent.getAction().equals("pushNotification")) {
                    String broadcastNotification = intent.getStringExtra("type");

                    // process trigger (broadcast) from list price
                    if(broadcastNotification.equals("load_data_spinner_shipping_address")){
                        loadDataSpinnerShippingAddress();
                    }
                } else {
                }
            }
        };

        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter("pushNotification"));
        // start listening for refresh local file list in
        return view;
        //return inflater.inflate(R.layout.activity_ongoing_order, container, false);
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

        // code spinner data kosong
        List<String> listEmpty = new ArrayList<String>();

        ArrayAdapter<String> dataEmptyAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.spinner_item, listEmpty);
        spinner_customer_address.setAdapter(dataEmptyAdapter);
        textView_address.setText("");
        textView_pic_name.setText("");
        textView_phone.setText("");
        textView_mobile.setText("");


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

//                        spinner_customer_address.setList(check_List);
                        Log.e("Cekkkkkkk", "vvvvvvvvvvvvvvvvvvvvv");
                        customerAddressModels = response.body().getData();

                        // code spinner yg baru
//                        spinner_customer_address.setOnItemSelectedListener(new SearchableSpinner.OnItemSelectedListener() {
//                            @Override
//                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                                Log.e("Cekkkkkkk", ""+ customerAddressModels.get(position).getAddress());
//                                Toast.makeText(getActivity().getApplicationContext(), "Cekkkkkk"+ customerAddressModels.get(position).getAddress() + check_List[position], Toast.LENGTH_SHORT).show();
//                                textView_address.setText("" + customerAddressModels.get(position).getAddress());
//                                textView_pic_name.setText("" + customerAddressModels.get(position).getPic());
//                                textView_phone.setText("" + customerAddressModels.get(position).getPhone());
//                                textView_mobile.setText("" + customerAddressModels.get(position).getMobile());
//                            }
//
//                            @Override
//                            public void onNothingSelected(AdapterView<?> parent) {
//                                Log.e("Cekkkkkkk", "qqqqqqqqqqqqqqqqqqqqqqqqqq");
//                            }
//                        });

                        // code spinner yg lama
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                                R.layout.spinner_item, check_List);
//                        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                        spinner_customer_address.setAdapter(dataAdapter);
//                        spinner_customer_address.setAdapter(dataAdapter);
//
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
                Log.e("Cekkkkkkk", "wwwwwwwwwwwwwwwwwwwww");
            }
        });
    }

}
