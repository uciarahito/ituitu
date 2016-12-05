package uci.develops.wiraenergimobile.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.activity.HomeActivity;
import uci.develops.wiraenergimobile.activity.ListRequestCustomerActivity;
import uci.develops.wiraenergimobile.helper.Constant;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.response.ApproveResponse;
import uci.develops.wiraenergimobile.response.CustomerResponse;
import uci.develops.wiraenergimobile.response.UserResponse;
import uci.develops.wiraenergimobile.service.RestClient;

/**
 * Created by ArahitoPC on 10/24/2016.
 */
public class CustomerDialogAdapter extends RecyclerView.Adapter<CustomerDialogAdapter.MyViewHolder> {
    private List<CustomerModel> customerModelList;
    private Context context;

    Map<String, List<String>> mRoles = new TreeMap<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName, txtEmail, txtCompany;
        public Button button_assign;

        public MyViewHolder(View view) {
            super(view);
            txtName = (TextView) view.findViewById(R.id.txtName);
            txtEmail = (TextView) view.findViewById(R.id.txtEmail);
            txtCompany = (TextView) view.findViewById(R.id.txtCompany);
            button_assign = (Button) view.findViewById(R.id.button_assign);
        }
    }

    public CustomerDialogAdapter(Context context, List<CustomerModel> customerModelList) {
        this.context = context;
        this.customerModelList = customerModelList;
    }

    public void updateList(List<CustomerModel> newList) {
        this.customerModelList.clear();
        this.customerModelList = newList;
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dialog_customer, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final CustomerModel customerModel = customerModelList.get(position);
        holder.txtName.setText(": " + customerModel.getFirst_name() + " " + customerModel.getLast_name());
        holder.txtEmail.setText(": " + customerModel.getEmail());
        holder.txtCompany.setText(": " + customerModel.getAddress());

        holder.button_assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // asign to this customer
                alertDialogAssign(customerModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return customerModelList.size();
    }

    private void alertDialogAssign(final CustomerModel customerModel) {
        Toast.makeText(context, "" + customerModel.getDecode(), Toast.LENGTH_SHORT).show();
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage("Are you sure want to update to : \n Customer name : " + customerModel.getFirst_name() + "\n Customer code : " + customerModel.getCode());
        final String decodeCustomerOld = customerModel.getDecode();
        alertDialogBuilder.setNegativeButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Call<ApproveResponse> customerRejectUpdateUser = RestClient.getRestClient().customerRejectUpdateUser("Bearer " +
                                        new SharedPreferenceManager().getPreferences(context, "token"),
                                decodeCustomerOld, new SharedPreferenceManager().getPreferences(context, "customer_decode"));
                        customerRejectUpdateUser.enqueue(new Callback<ApproveResponse>() {
                            @Override
                            public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
                                if (response.isSuccessful()) {
                                    Call<UserResponse> userResponseCall = RestClient.getRestClient().getUser("Bearer " + new SharedPreferenceManager().getPreferences(context, "token"),
                                            Integer.parseInt(new SharedPreferenceManager().getPreferences(context, "customer_user_id")));
                                    userResponseCall.enqueue(new Callback<UserResponse>() {
                                        @Override
                                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                            if (response.isSuccessful()) {
                                                if (response.body().getData().getRegistration_key() != null) {
                                                    Toast.makeText(context, "Update user successfull", Toast.LENGTH_SHORT).show();
                                                    Log.e("FormCustomer", "" + response.body().getData().getRegistration_key());
                                                    Constant.sendNotification(response.body().getData().getRegistration_key(), "Request anda telah di setujui", "approve_customer");
                                                    Intent intent = new Intent(context, HomeActivity.class);
                                                    context.startActivity(intent);
                                                }
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<UserResponse> call, Throwable t) {

                                        }
                                    });
                                } else {
                                    Toast.makeText(context, "Unable to update user", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ApproveResponse> call, Throwable t) {

                            }
                        });

                        Intent intent = new Intent(context, HomeActivity.class);
                        context.startActivity(intent);
                    }
                });
        alertDialogBuilder.setPositiveButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(context, ListRequestCustomerActivity.class);
                        context.startActivity(intent);
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}