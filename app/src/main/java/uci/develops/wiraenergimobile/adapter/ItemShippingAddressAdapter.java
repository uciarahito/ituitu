package uci.develops.wiraenergimobile.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.activity.HomeActivity;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerAddressModel;
import uci.develops.wiraenergimobile.response.ApproveResponse;
import uci.develops.wiraenergimobile.response.CustomerAddressResponse;
import uci.develops.wiraenergimobile.service.RestClient;

/**
 * Created by user on 11/20/2016.
 */

public class ItemShippingAddressAdapter extends RecyclerView.Adapter<ItemShippingAddressAdapter.MyViewHolder> {
    private List<CustomerAddressModel> customerAddressModelList;
    private Context context;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
//    public static String addressDialog = "", latitudeDialog = "", longitudeDialog = "";

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView_pic) TextView textView_pic;
        @BindView(R.id.textView_name) TextView textView_name;
        @BindView(R.id.textView_phone) TextView textView_phone;
        @BindView(R.id.textView_mobile) TextView textView_mobile;
        @BindView(R.id.textView_address) TextView textView_address;
        @BindView(R.id.imageView_edit) ImageView imageView_edit;
        @BindView(R.id.imageView_delete) ImageView imageView_delete;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            if (new SharedPreferenceManager().getPreferences(context, "roles").equals("")) {
                if (Integer.parseInt(new SharedPreferenceManager().getPreferences(context, "approve")) == 0) {
                    imageView_edit.setEnabled(false);
                    imageView_delete.setEnabled(false);
                }
            }

            if (new SharedPreferenceManager().getPreferences(context, "roles").equals("admin")) {
                if ((Integer.parseInt(new SharedPreferenceManager().getPreferences(context, "approve")) == 0) |
                        (Integer.parseInt(new SharedPreferenceManager().getPreferences(context, "approve")) == 1) |
                        (Integer.parseInt(new SharedPreferenceManager().getPreferences(context, "approve")) == 2)){
                    imageView_edit.setEnabled(false);
                    imageView_delete.setEnabled(false);
                }
            }

            if (new SharedPreferenceManager().getPreferences(context, "roles").equals("customer")) {
                if (Integer.parseInt(new SharedPreferenceManager().getPreferences(context, "approve")) == 1) {
                    imageView_edit.setEnabled(true);
                    imageView_delete.setEnabled(true);
                }
            }

            /*
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
                        if (broadcastNotification.equals("dismiss_dialog_maps")) {
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
            LocalBroadcastManager.getInstance(context).registerReceiver(mRegistrationBroadcastReceiver,
                    new IntentFilter("pushNotification"));
*/
        }
    }

    public ItemShippingAddressAdapter(Context context, List<CustomerAddressModel> customerAddressModelList) {
        this.context = context;
        this.customerAddressModelList = customerAddressModelList;
    }

    public void updateList(List<CustomerAddressModel> newList) {
        this.customerAddressModelList.clear();
        this.customerAddressModelList = newList;
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shipping_address, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final CustomerAddressModel customerAddressModel = customerAddressModelList.get(position);
        holder.textView_pic.setText("" + customerAddressModel.getPic());
        holder.textView_name.setText("" + customerAddressModel.getName());
        holder.textView_phone.setText("" + customerAddressModel.getPhone());
        holder.textView_mobile.setText("" + customerAddressModel.getMobile());
        holder.textView_address.setText("" + customerAddressModel.getAddress());

        holder.imageView_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                alertDialogDeleteShippingAddress();
//                Call<ApproveResponse> addShippingAddressCall = RestClient.getRestClient().deleteCustomerAddress("Bearer " +
//                                new SharedPreferenceManager().getPreferences(context, "token"),
//                        new SharedPreferenceManager().getPreferences(context, "customer_decode"), customerAddressModel.getDecode());
//                addShippingAddressCall.enqueue(new Callback<ApproveResponse>() {
//                    @Override
//                    public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
//                        if (response.isSuccessful()) {
//                            Toast.makeText(context, "Sukses", Toast.LENGTH_SHORT).show();
//                            if (new SharedPreferenceManager().getPreferences(context, "roles").equals("")){
//                                Intent intent = new Intent(context, FormCustomerActivity.class);
//                                context.startActivity(intent);
//                            } else if (new SharedPreferenceManager().getPreferences(context, "roles").equals("customer")){
//                                Intent intent = new Intent(context, HomeActivity.class);
//                                context.startActivity(intent);
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ApproveResponse> call, Throwable t) {
//
//                    }
//                });

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Are you sure want to delete the shipping address?");
                alertDialogBuilder.setNegativeButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Call<ApproveResponse> addShippingAddressCall = RestClient.getRestClient().deleteCustomerAddress("Bearer " +
                                                new SharedPreferenceManager().getPreferences(context, "token"),
                                        new SharedPreferenceManager().getPreferences(context, "customer_decode"), customerAddressModel.getDecode());
                                addShippingAddressCall.enqueue(new Callback<ApproveResponse>() {
                                    @Override
                                    public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
                                        if (response.isSuccessful()) {
                                            Toast.makeText(context, "Sukses", Toast.LENGTH_SHORT).show();
                                            if (new SharedPreferenceManager().getPreferences(context, "roles").equals("")) {
                                                // ini untuk mengirim notifikasi
                                                // ini disebut custom broadcast intent (kalo gak salah)
                                                // intent actionnya, pushNotification (cuma contoh)
                                                Intent pushNotification = new Intent("pushNotification");
                                                // trus, kita selipkan string (kayak ngirim intent biasa)
                                                // itulah yang di buat banyak ban
                                                // misalnya ada refresh apalah gitu
                                                // atau apalah terserah,

                                                pushNotification.putExtra("type", "refresh_list_shipping");
                                                LocalBroadcastManager.getInstance(context).sendBroadcast(pushNotification);

//                                                Intent intent = new Intent(context, FormCustomerActivity.class);
//                                                context.startActivity(intent);
                                            } else if (new SharedPreferenceManager().getPreferences(context, "roles").equals("customer")) {
                                                Intent intent = new Intent(context, HomeActivity.class);
                                                context.startActivity(intent);
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ApproveResponse> call, Throwable t) {

                                    }
                                });
                            }
                        });
                alertDialogBuilder.setPositiveButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
//                        Intent intent = new Intent(context, FormCustomerActivity.class);
//                        context.startActivity(intent);
                                arg0.dismiss();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

        holder.imageView_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogEditShipping(customerAddressModel);
            }
        });
    }

    Dialog dialogMaps;

    void showDialogMaps() {
        dialogMaps = new Dialog(context);
        dialogMaps.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogMaps.setContentView(R.layout.custom_dialog_maps);
        dialogMaps.setCancelable(true);
        dialogMaps.show();
    }

    private EditText editText_pic_name, editText_address_name, editText_address,
            editText_phone, editText_mobile, editText_map_cordinate;
    private Button button_save, button_cancel;
    String latlong = "";

    Dialog dialog_edit_shipping;
    private void showDialogEditShipping(final CustomerAddressModel customerAddressModel) {
        dialog_edit_shipping = new Dialog(context);
        dialog_edit_shipping.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_edit_shipping.setContentView(R.layout.custom_dialog_form_shipping_address);

        editText_pic_name = ButterKnife.findById(dialog_edit_shipping, R.id.editText_name);
        editText_address_name = ButterKnife.findById(dialog_edit_shipping, R.id.editText_address_name);
        editText_address = ButterKnife.findById(dialog_edit_shipping, R.id.editText_address);
        editText_map_cordinate = ButterKnife.findById(dialog_edit_shipping, R.id.editText_map_coordinate);
        editText_phone = ButterKnife.findById(dialog_edit_shipping, R.id.editText_phone);
        editText_mobile = ButterKnife.findById(dialog_edit_shipping, R.id.editText_mobile);
        button_save = ButterKnife.findById(dialog_edit_shipping, R.id.button_save);
        button_cancel = ButterKnife.findById(dialog_edit_shipping, R.id.button_cancel);

        Call<CustomerAddressResponse> customerAddressResponseCall = RestClient.getRestClient().getCustomerAddressByDecode("Bearer " +
                        new SharedPreferenceManager().getPreferences(context, "token"),
                new SharedPreferenceManager().getPreferences(context, "customer_decode"), customerAddressModel.getDecode());
        customerAddressResponseCall.enqueue(new Callback<CustomerAddressResponse>() {
            @Override
            public void onResponse(Call<CustomerAddressResponse> call, Response<CustomerAddressResponse> response) {
                if (response.isSuccessful()) {
                    CustomerAddressModel customerAddressModel = response.body().getData();
                    editText_pic_name.setText("" + customerAddressModel.getPic());
                    editText_address_name.setText("" + customerAddressModel.getName());
                    editText_address.setText("" + customerAddressModel.getAddress());
                    editText_mobile.setText("" + customerAddressModel.getMobile());
                    editText_phone.setText("" + customerAddressModel.getPhone());
                    editText_map_cordinate.setText("" + customerAddressModel.getMap());
//                    editText_map_cordinate.setText("" + addressDialog);

                    if (new SharedPreferenceManager().getPreferences(context, "roles").equals("")) {
                        if (Integer.parseInt(new SharedPreferenceManager().getPreferences(context, "approve")) == 0) {
                            readOnly();
                        }
                    }

                    if (new SharedPreferenceManager().getPreferences(context, "roles").equals("admin")) {
                        if (Integer.parseInt(new SharedPreferenceManager().getPreferences(context, "approve")) == 1) {
                            readOnly();
                        }
                    }
                } else {
                    Toast.makeText(context, "Not successfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerAddressResponse> call, Throwable t) {
                Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show();
            }
        });

        editText_map_cordinate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                editText_map_cordinate.setEnabled(false);
                showDialogMaps();
                return false;
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialog_edit_shipping.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog_edit_shipping.setCancelable(true);
        dialog_edit_shipping.show();

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean is_not_empty = false;
                is_not_empty = isNotEmpty();
                if (is_not_empty) {
//                    latlong = latitudeDialog + "," + longitudeDialog;
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("decode", customerAddressModel.getDecode());
                    params.put("name", editText_address_name.getText().toString());
                    params.put("address", editText_address.getText().toString());
                    params.put("pic", editText_pic_name.getText().toString());
                    params.put("phone", editText_phone.getText().toString());
                    params.put("mobile", editText_mobile.getText().toString());
                    params.put("map", editText_map_cordinate.getText().toString());
//                    params.put("map", latlong);
                    Call<ApproveResponse> addShippingAddressCall = RestClient.getRestClient().sendDataShippingInfoNew("Bearer " +
                                    new SharedPreferenceManager().getPreferences(context, "token"),
                            new SharedPreferenceManager().getPreferences(context, "customer_decode"), params);
                    addShippingAddressCall.enqueue(new Callback<ApproveResponse>() {
                        @Override
                        public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(context, "Sukses", Toast.LENGTH_SHORT).show();
                                // ini untuk mengirim notifikasi
                                // ini disebut custom broadcast intent (kalo gak salah)
                                // intent actionnya, pushNotification (cuma contoh)
                                Intent pushNotification = new Intent("pushNotification");
                                // trus, kita selipkan string (kayak ngirim intent biasa)
                                // itulah yang di buat banyak ban
                                // misalnya ada refresh apalah gitu
                                // atau apalah terserah,

                                pushNotification.putExtra("type", "refresh_list_shipping");
                                LocalBroadcastManager.getInstance(context).sendBroadcast(pushNotification);
                                dialog_edit_shipping.dismiss();
                            } else {
                                Toast.makeText(context, "" + response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ApproveResponse> call, Throwable t) {

                        }
                    });
                } else {
                    Toast.makeText(context, "All field are required", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_edit_shipping.dismiss();
            }
        });
    }

    String name = "", address = "", pic = "", phone = "", mobile = "", map = "";

    public boolean isNotEmpty() {
        name = editText_address_name.getText().toString();
        address = editText_address.getText().toString();
        pic = editText_pic_name.getText().toString();
        phone = editText_phone.getText().toString();
        mobile = editText_mobile.getText().toString();
        map = editText_map_cordinate.getText().toString();

        boolean result = false;
        if (!name.equals("") && !address.equals("") && !pic.equals("") && !phone.equals("") && !mobile.equals("")) {
            result = true;
        }
        return result;
    }

    public void readOnly() {
        editText_pic_name.setEnabled(false);
        editText_address_name.setEnabled(false);
        editText_address.setEnabled(false);
        editText_phone.setEnabled(false);
        editText_mobile.setEnabled(false);
        editText_map_cordinate.setEnabled(false);
    }

    @Override
    public int getItemCount() {
        return customerAddressModelList.size();
    }

}