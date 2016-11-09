package uci.develops.wiraenergimobile.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.activity.FormCustomerActivity;
import uci.develops.wiraenergimobile.activity.HomeActivity;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.response.ApproveResponse;
import uci.develops.wiraenergimobile.response.CustomerResponse;
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
        holder.txtName.setText(": " + customerModel.getFirst_name()+" "+customerModel.getLast_name());
        holder.txtEmail.setText(": " + customerModel.getEmail());
        holder.txtCompany.setText(": " + customerModel.getAddress());

        holder.button_assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // asign to this customer
                alertDialogAssign();
            }
        });
    }

    @Override
    public int getItemCount() {
        return customerModelList.size();
    }

    private void alertDialogAssign(){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage("Are you sure want to add contact?");
        alertDialogBuilder.setNegativeButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // doing something here
                        Toast.makeText(context, "You choose yes sir!", Toast.LENGTH_SHORT).show();
                        // cek if full
                        Call<CustomerResponse> customerResponseCall = RestClient.getRestClient().getCustomer("Bearer "+new SharedPreferenceManager().getPreferences(context, "token"),
                                new SharedPreferenceManager().getPreferences(context, "customer_decode"));
                        customerResponseCall.enqueue(new Callback<CustomerResponse>() {
                            @Override
                            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                                if(response.isSuccessful()){
                                    CustomerModel customerModel = response.body().getData().get(0);
                                    int contact_counter = 0;
                                    if(customerModel.getName1() != null){
                                        contact_counter+=1;
                                    }
                                    if(customerModel.getName1() != null){
                                        contact_counter+=1;
                                    }
                                    if(customerModel.getName1() != null){
                                        contact_counter+=1;
                                    }
                                    if(contact_counter == 3){
                                        // it means full
                                        showCustomDialogContactInfoCustomer();
                                    } else {
                                        Toast.makeText(context, "Contact slot is available", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<CustomerResponse> call, Throwable t) {

                            }
                        });
                    }
                });
        alertDialogBuilder.setPositiveButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Call<ApproveResponse> approveResponseCall = RestClient.getRestClient().requestCustomerAction("Bearer "+new SharedPreferenceManager().getPreferences(context, "token"),
                                new SharedPreferenceManager().getPreferences(context, "customer_decode"), 1, 1);
                        approveResponseCall.enqueue(new Callback<ApproveResponse>() {
                            @Override
                            public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(context, "Approve request successfull", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(context, HomeActivity.class);
                                    context.startActivity(intent);
                                } else {
                                    Toast.makeText(context, "Approve request not successfull", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ApproveResponse> call, Throwable t) {
                                Toast.makeText(context, "Approve request not successfull", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private EditText editText_dialog_customer_code;
    private Button button_dialog_save, button_dialog_cancel, button_dialog_contact1_delete, button_dialog_contact2_delete,
            button_dialog_contact3_delete, button_dialog_submit, button_dialog_new_customer, button_dialog_existing_customer;
    private TextView textView_dialog_contact1_name, textView_dialog_contact1_email, textView_dialog_contact2_name,
            textView_dialog_contact2_email, textView_dialog_contact3_name, textView_dialog_contact3_email;

    private void showCustomDialogContactInfoCustomer(){
        final Dialog dialog_contactt_info_customer = new Dialog(context);
        dialog_contactt_info_customer.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_contactt_info_customer.setContentView(R.layout.custom_dialog_contact_info_customer);

        textView_dialog_contact1_name = (TextView)dialog_contactt_info_customer.findViewById(R.id.textView_contact1_name);
        textView_dialog_contact2_name = (TextView)dialog_contactt_info_customer.findViewById(R.id.textView_contact2_name);
        textView_dialog_contact3_name = (TextView)dialog_contactt_info_customer.findViewById(R.id.textView_contact3_name);
        textView_dialog_contact1_email = (TextView)dialog_contactt_info_customer.findViewById(R.id.textView_contact1_email);
        textView_dialog_contact2_email = (TextView)dialog_contactt_info_customer.findViewById(R.id.textView_contact2_email);
        textView_dialog_contact3_email = (TextView)dialog_contactt_info_customer.findViewById(R.id.textView_contact3_email);
        button_dialog_contact1_delete = (Button)dialog_contactt_info_customer.findViewById(R.id.button_delete_contact_1);
        button_dialog_contact2_delete = (Button)dialog_contactt_info_customer.findViewById(R.id.button_delete_contact_2);
        button_dialog_contact3_delete = (Button)dialog_contactt_info_customer.findViewById(R.id.button_delete_contact_3);
        button_dialog_submit = (Button)dialog_contactt_info_customer.findViewById(R.id.button_submit);

        Call<CustomerResponse> customerResponseCall = RestClient.getRestClient().getCustomer("Bearer "+new SharedPreferenceManager().getPreferences(context, "token"),
                new SharedPreferenceManager().getPreferences(context, "customer_decode"));
        customerResponseCall.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if(response.isSuccessful()){
                    CustomerModel customerModel = response.body().getData().get(0);
                    textView_dialog_contact1_name.setText(""+(customerModel.getName1()));
                    textView_dialog_contact2_name.setText(""+(customerModel.getName2()));
                    textView_dialog_contact3_name.setText(""+(customerModel.getName3()));
                    textView_dialog_contact1_email.setText(""+(customerModel.getEmail1()));
                    textView_dialog_contact2_email.setText(""+(customerModel.getEmail2()));
                    textView_dialog_contact3_email.setText(""+(customerModel.getEmail3()));
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {

            }
        });

        button_dialog_contact1_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // delete contact 1
                dialog_contactt_info_customer.dismiss();
            }
        });
        button_dialog_contact2_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // delete contact 2
                dialog_contactt_info_customer.dismiss();
            }
        });
        button_dialog_contact3_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // delete contact 3
                dialog_contactt_info_customer.dismiss();
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialog_contactt_info_customer.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog_contactt_info_customer.setCancelable(true);
        dialog_contactt_info_customer.show();
    }
}