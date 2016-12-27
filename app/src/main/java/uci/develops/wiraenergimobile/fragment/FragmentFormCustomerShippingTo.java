package uci.develops.wiraenergimobile.fragment;

import android.app.Dialog;
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
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.adapter.ItemShippingAddressAdapter;
import uci.develops.wiraenergimobile.helper.DividerItemDecoration;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerAddressModel;
import uci.develops.wiraenergimobile.response.ApproveResponse;
import uci.develops.wiraenergimobile.response.ListCustomerAddressResponse;
import uci.develops.wiraenergimobile.service.RestClient;

/**
 * Created by user on 10/22/2016.
 */

public class FragmentFormCustomerShippingTo extends Fragment {

    @BindView(R.id.button_add_shipping)
    Button button_add_shipping;
    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;

    String customer_code = "", pic_name = "", address_name = "", address = "", map = "", phone = "", mobile = "";
    private String decode = "", token = "";
    int counter_list = 0;

    public static String addressDialog = "", latitudeDialog = "", longitudeDialog = "";

    private ItemShippingAddressAdapter itemShippingAddressAdapter;

    private BroadcastReceiver mRegistrationBroadcastReceiver;

    public FragmentFormCustomerShippingTo() {
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
        view = inflater.inflate(R.layout.fragment_shipping_new, container, false);
        ButterKnife.bind(this, view);
        decode = new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "customer_decode");
        token = new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "token");

        initializeComponent(view);
        loadData();

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                //If the broadcast has received with success
                //that means device is registered successfully
                // di sini ada data2 yang dikirim melalui fcm
                // ini action intentnya
                if (intent.getAction().equals("pushNotification")) {
                    // ini valuenya
                    // jadi tadi kita ngirim broadcast refresh_list_shipping
                    // yang kita MAU, dia refresh kan
                    // code REFRESH di class ini, loadData
                    // paham?
                    String broadcastNotification = intent.getStringExtra("type");
                    if (broadcastNotification.equals("refresh_list_shipping")) {
                        // eksekusi sesuatu ketika ada data broadcast sesuai kondisi
                        loadData();
                    } else if (broadcastNotification.equals("whatever")) {
                    } else if (broadcastNotification.equals("dismiss_dialog_maps")) {
                        dialogMaps.dismiss();
                        editText_map_cordinate.setText("" + addressDialog);
//                        Toast.makeText(getContext(), "" + latitudeDialog, Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getContext(), "" + longitudeDialog, Toast.LENGTH_SHORT).show();
                        editText_map_cordinate.setEnabled(true);
                    }
                } else {
                }
            }
        };

        // ini untuk mendaftarkan object broadcast receiver di fragment ini
        // jadi ketika fragment ini aktif, dan ada broadcast dari mana pun (fcm, gcm, dialog, activity, dll)
        // bakalan di proses
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter("pushNotification"));

        return view;
    }

    Dialog dialogMaps;

    void showDialogMaps() {
        dialogMaps = new Dialog(getContext());
        dialogMaps.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogMaps.setContentView(R.layout.custom_dialog_maps);
        dialogMaps.setCancelable(true);
        dialogMaps.show();
    }

    private void initializeComponent(View view) {
        List<CustomerAddressModel> customerAddressModelList = new ArrayList<>();
        itemShippingAddressAdapter = new ItemShippingAddressAdapter(getContext(), customerAddressModelList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(itemShippingAddressAdapter);

        if (new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "roles").equals("")) {
            if (Integer.parseInt(new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "approve")) == 0) {
                button_add_shipping.setVisibility(View.GONE);
            }
        }

        if (new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "roles").equals("admin")) {
            if (Integer.parseInt(new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "approve")) == 1) {
                button_add_shipping.setVisibility(View.GONE);
            }
        }

        if (new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "roles").equals("customer")) {
            if (Integer.parseInt(new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "approve")) == 1) {
                button_add_shipping.setVisibility(View.GONE);
            }
        }

        button_add_shipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAddShipping();
            }
        });
    }

    //utk dialog add shipping
    private EditText editText_customer_code, editText_pic_name, editText_address_name, editText_address, editText_phone, editText_mobile, editText_map_cordinate;
    private Button button_save, button_cancel;
    Dialog dialog_add_shipping;
    String latlong = "";

    private void showDialogAddShipping() {
        dialog_add_shipping = new Dialog(getContext());
        dialog_add_shipping.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_add_shipping.setContentView(R.layout.custom_dialog_form_shipping_address);

        editText_customer_code = ButterKnife.findById(dialog_add_shipping, R.id.editText_customer_code);
        editText_pic_name = ButterKnife.findById(dialog_add_shipping, R.id.editText_name);
        editText_address_name = ButterKnife.findById(dialog_add_shipping, R.id.editText_address_name);
        editText_address = ButterKnife.findById(dialog_add_shipping, R.id.editText_address);
        editText_map_cordinate = ButterKnife.findById(dialog_add_shipping, R.id.editText_map_coordinate);
        editText_phone = ButterKnife.findById(dialog_add_shipping, R.id.editText_phone);
        editText_mobile = ButterKnife.findById(dialog_add_shipping, R.id.editText_mobile);
        button_save = ButterKnife.findById(dialog_add_shipping, R.id.button_save);
        button_cancel = ButterKnife.findById(dialog_add_shipping, R.id.button_cancel);

        editText_customer_code.setText("" + new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "customer_decode"));
        editText_customer_code.setEnabled(false);

        editText_map_cordinate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                editText_map_cordinate.setEnabled(false);
                showDialogMaps();
                return false;
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getContext().getSystemService(getContext().WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialog_add_shipping.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog_add_shipping.setCancelable(true);
        dialog_add_shipping.show();

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNotEmpty()) {
                    latlong = latitudeDialog + "," + longitudeDialog;
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("address_name", editText_address.getText().toString());
                    params.put("address", editText_address.getText().toString());
                    params.put("pic", editText_pic_name.getText().toString());
                    params.put("phone", editText_phone.getText().toString());
                    params.put("mobile", editText_mobile.getText().toString());
//                    params.put("map", editText_map_cordinate.getText().toString());
                    params.put("map", latlong);
                    Call<ApproveResponse> addShippingAddressCall = RestClient.getRestClient().createCustomerAddress("Bearer " + token, decode, params);
                    addShippingAddressCall.enqueue(new Callback<ApproveResponse>() {
                        @Override
                        public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getContext(), "Sukses", Toast.LENGTH_SHORT).show();
                                loadData();
                                dialog_add_shipping.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<ApproveResponse> call, Throwable t) {

                        }
                    });
                } else {
                    Toast.makeText(getContext(), "All field are required", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_add_shipping.dismiss();
            }
        });
    }

    public boolean isNotEmpty() {
        customer_code = editText_customer_code.getText().toString();
        pic_name = editText_pic_name.getText().toString();
        address_name = editText_address_name.getText().toString();
        address = editText_address.getText().toString();
        phone = editText_phone.getText().toString();
        mobile = editText_mobile.getText().toString();
        map = editText_map_cordinate.getText().toString();

        boolean result = false;
        if (!customer_code.equals("") && !pic_name.equals("") && !address_name.equals("") && !address.equals("") && !phone.equals("") && !mobile.equals("")) {

            result = true;
        }
        return result;
    }

    private void loadData() {
        Call<ListCustomerAddressResponse> listCustomerAddressResponseCall = RestClient.getRestClient().getCustomerAddress("Bearer " + token,
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
