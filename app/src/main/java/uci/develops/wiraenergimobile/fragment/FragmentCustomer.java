package uci.develops.wiraenergimobile.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.activity.FormCustomerActivity;
import uci.develops.wiraenergimobile.activity.ListCustomerActivity;
import uci.develops.wiraenergimobile.activity.ListRequestCustomerActivity;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.response.RequestListCustomerResponse;
import uci.develops.wiraenergimobile.service.RestClient;

/**
 * Created by user on 11/3/2016.
 */

public class FragmentCustomer extends Fragment implements View.OnClickListener{
    private LinearLayout linearLayout_menu_all_customer, linearLayout_menu_new_customer;
    private TextView textView_label_all_customer, textView_label_new_customer;

    public FragmentCustomer(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_customer, container, false);
        initializeComponent(view);
        // start listening for refresh local file list in
        loadData();

//        if (new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "roles").equals("admin")) {
//            initializeComponent(view);
//            // start listening for refresh local file list in
//            loadData();
//        } else if (new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "roles").equals("customer")) {
//            Intent intent = new Intent(getActivity().getApplicationContext(), FormCustomerActivity.class);
//            startActivity(intent);
//        }

        return view;
        //return inflater.inflate(R.layout.activity_ongoing_order, container, false);
    }

    private void loadData(){
        Call<RequestListCustomerResponse> listCustomerResponse = RestClient.getRestClient().getAllRequestCustomer("Bearer "+
                new SharedPreferenceManager().getPreferences(getContext(), "token"));
        listCustomerResponse.enqueue(new Callback<RequestListCustomerResponse>() {
            @Override
            public void onResponse(Call<RequestListCustomerResponse> call, Response<RequestListCustomerResponse> response) {
                if(response.isSuccessful()){
                    int jumlah_customer=0;
                    int jumlah_requester=0;
                    if(response.body().getData().size() > 0) {
                        for (CustomerModel customerModel : response.body().getData()) {
                            if (customerModel.getActive() == 1 && customerModel.getApprove() == 1) {
                                jumlah_customer += 1;
                            } else {
                                jumlah_requester += 1;
                            }
                        }
                    }
                    textView_label_all_customer.setText("All Customer ("+jumlah_customer+")");
                    textView_label_new_customer.setText("New Customer ("+jumlah_requester+")");
                }
            }

            @Override
            public void onFailure(Call<RequestListCustomerResponse> call, Throwable t) {

            }
        });
    }

    private void initializeComponent(View view){
        linearLayout_menu_all_customer = (LinearLayout) view.findViewById(R.id.linearLayout_menu_all_customer);
        linearLayout_menu_new_customer = (LinearLayout) view.findViewById(R.id.linearLayout_menu_new_customer);
        textView_label_all_customer = (TextView)view.findViewById(R.id.textView_label_all_customer);
        textView_label_new_customer = (TextView)view.findViewById(R.id.textView_label_new_customer);

        linearLayout_menu_all_customer.setOnClickListener(this);
        linearLayout_menu_new_customer.setOnClickListener(this);
    }

    /**
     * ketika activity resume
     * melakukan register broadcast receiver
     */
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        if (v == linearLayout_menu_all_customer) {
            Intent intent = new Intent(getActivity().getApplicationContext(), ListCustomerActivity.class);
            startActivity(intent);
        }

        if (v == linearLayout_menu_new_customer) {
            Intent intent = new Intent(getActivity().getApplicationContext(), ListRequestCustomerActivity.class);
            startActivity(intent);
        }
    }
}