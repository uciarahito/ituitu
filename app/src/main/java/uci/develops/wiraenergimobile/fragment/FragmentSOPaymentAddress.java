package uci.develops.wiraenergimobile.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
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
import uci.develops.wiraenergimobile.adapter.ItemSOCompanyAddressAdapter;
import uci.develops.wiraenergimobile.adapter.ItemSOShippingAddressAdapter;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerAddressModel;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.response.CustomerResponse;
import uci.develops.wiraenergimobile.response.ListCustomerAddressResponse;
import uci.develops.wiraenergimobile.service.RestClient;

public class FragmentSOPaymentAddress extends Fragment implements View.OnClickListener {
    private TextView textView_address, textView_pic_name, textView_phone, textView_mobile;
    private Spinner spinner_payment_address;

    public FragmentSOPaymentAddress() {
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
        view = inflater.inflate(R.layout.fragment_req_so_payment_address, container, false);

        initializeComponent(view);

        // start listening for refresh local file list in
        return view;
        //return inflater.inflate(R.layout.activity_ongoing_order, container, false);
    }

    private void initializeComponent(View view) {
        textView_address = (TextView) view.findViewById(R.id.textView_address);
        textView_pic_name = (TextView) view.findViewById(R.id.textView_pic_name);
        textView_phone = (TextView) view.findViewById(R.id.textView_phone);
        textView_mobile = (TextView) view.findViewById(R.id.textView_mobile);
        spinner_payment_address = (Spinner) view.findViewById(R.id.spinner_payment_address);
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
        if (v == spinner_payment_address) {
            showDialogPaymentAddress();
        }
    }

    Dialog dialog_list_payment_address;
    ItemSOCompanyAddressAdapter itemSOCompanyAddressAdapter;
    ItemSOShippingAddressAdapter itemSOShippingAddressAdapter;
    private RecyclerView recyclerView_company_address, recyclerView_shipping_address;
    List<CustomerModel> customerModelList;
    List<CustomerAddressModel> customerAddressModelList;
    private void showDialogPaymentAddress(){
        dialog_list_payment_address = new Dialog(getActivity().getApplicationContext());
        dialog_list_payment_address.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_list_payment_address.setContentView(R.layout.custom_dialog_payment_address);

        recyclerView_company_address = (RecyclerView) dialog_list_payment_address.findViewById(R.id.recycleView_company_address);
        RecyclerView.LayoutManager mCompanyAdressLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView_company_address.setLayoutManager(mCompanyAdressLayoutManager);
        recyclerView_company_address.setItemAnimator(new DefaultItemAnimator());
        customerModelList = new ArrayList<>();
        itemSOCompanyAddressAdapter = new ItemSOCompanyAddressAdapter(getActivity().getApplicationContext(), customerModelList);
        recyclerView_company_address.setAdapter(itemSOCompanyAddressAdapter);

        recyclerView_shipping_address = (RecyclerView) dialog_list_payment_address.findViewById(R.id.recycleView_shipping_address);
        RecyclerView.LayoutManager mShippingAddressLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView_shipping_address.setLayoutManager(mShippingAddressLayoutManager);
        recyclerView_shipping_address.setItemAnimator(new DefaultItemAnimator());
        customerAddressModelList = new ArrayList<>();
        itemSOShippingAddressAdapter = new ItemSOShippingAddressAdapter(getActivity().getApplicationContext(), customerAddressModelList);
        recyclerView_shipping_address.setAdapter(itemSOShippingAddressAdapter);

        showProgressLoading();
        Call<CustomerResponse> customerResponseCall = RestClient.getRestClient().getCustomer("Bearer " +
                        new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "token"),
                new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "customer_decode"));
        customerResponseCall.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if (response.isSuccessful()) {
                    customerModelList = response.body().getData();
                    List<CustomerModel> dataCustomer = new ArrayList<CustomerModel>();
                    for (CustomerModel customerModel : customerModelList) {
                        if (customerModel.getActive() == 1 && customerModel.getApprove() == 1) {
                            hideProgressLoading();
                            dataCustomer.add(customerModel);
                        }
                    }
                    if (dataCustomer.size() > 0) {
                        hideProgressLoading();
                        itemSOCompanyAddressAdapter.updateList(dataCustomer);
                    } else {
                        hideProgressLoading();
                        Toast.makeText(getActivity().getApplicationContext(), "Empty data", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    hideProgressLoading();
                    Toast.makeText(getActivity().getApplicationContext(), "Empty data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                hideProgressLoading();
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getActivity().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialog_list_payment_address.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog_list_payment_address.setCancelable(true);
        dialog_list_payment_address.show();

    }

    ProgressDialog progress_loading;

    public void showProgressLoading() {
        progress_loading = new ProgressDialog(getActivity().getApplicationContext());
        progress_loading.setMessage("Please wait...");
        progress_loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress_loading.setIndeterminate(true);
        progress_loading.show();
    }

    public void hideProgressLoading() {
        progress_loading.dismiss();
    }

}
