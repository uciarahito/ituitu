package uci.develops.wiraenergimobile.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.adapter.ItemShippingAddressAdapter;
import uci.develops.wiraenergimobile.helper.DividerItemDecoration;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerAddressModel;
import uci.develops.wiraenergimobile.response.ListCustomerAddressResponse;
import uci.develops.wiraenergimobile.service.RestClient;

/**
 * Created by user on 10/22/2016.
 */

public class FragmentProfileCustomerShippingAddress extends Fragment {

    private RecyclerView recyclerView;
    private String decode = "", token = "";
    int counter_list = 0;

    private ItemShippingAddressAdapter itemShippingAddressAdapter;

    private BroadcastReceiver mRegistrationBroadcastReceiver;

    public FragmentProfileCustomerShippingAddress() {
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
        view = inflater.inflate(R.layout.fragment_profil_shipping, container, false);

        decode = new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "customer_decode");
        token = new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "token");

        initializeComponent(view);
        loadData();

        //Broadcast Receiver
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("pushNotification")){
                    String broadcastNofitication = intent.getStringExtra("type");
                    if (broadcastNofitication.equals("refresh_list_shipping")){
                        loadData();
                    } else if (broadcastNofitication.equals("sesuatu")){
                    }
                } else {

                }
            }
        };

        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter("pushNotification"));

        return view;
    }

    private void initializeComponent(View view) {
        recyclerView = (RecyclerView)view.findViewById(R.id.recycle_view);
        List<CustomerAddressModel> customerAddressModelList = new ArrayList<>();
        itemShippingAddressAdapter = new ItemShippingAddressAdapter(getContext(), customerAddressModelList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(itemShippingAddressAdapter);
    }

    private void loadData() {
        Call<ListCustomerAddressResponse> listCustomerAddressResponseCall = RestClient.getRestClient().getCustomerAddress("Bearer "+token,
                decode);
        listCustomerAddressResponseCall.enqueue(new Callback<ListCustomerAddressResponse>() {
            @Override
            public void onResponse(Call<ListCustomerAddressResponse> call, Response<ListCustomerAddressResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getData().size() > 0) {
                        itemShippingAddressAdapter.updateList(response.body().getData());
                        counter_list = response.body().getData().size();
                    } else {
                        Toast.makeText(getContext(), "Shipping address empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Response not successfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListCustomerAddressResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Failure service", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
