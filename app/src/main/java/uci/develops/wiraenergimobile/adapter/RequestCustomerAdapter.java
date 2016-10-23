package uci.develops.wiraenergimobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.activity.DashboardAdminActivity;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.response.ApproveResponse;
import uci.develops.wiraenergimobile.response.RequestListCustomerResponse;
import uci.develops.wiraenergimobile.service.RestClient;

/**
 * Created by ArahitoPC on 10/24/2016.
 */
public class RequestCustomerAdapter extends RecyclerView.Adapter<RequestCustomerAdapter.MyViewHolder>{
    private List<CustomerModel> customerModelList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtIdCustomer, txtDecode, txtAddress, txtCity, txtProvince, txtStatus;
        public Button buttonApprove, buttonReject;

        public MyViewHolder(View view) {
            super(view);
            txtIdCustomer = (TextView) view.findViewById(R.id.txtIdCustomer);
            txtDecode = (TextView) view.findViewById(R.id.txtDecode);
            txtAddress = (TextView) view.findViewById(R.id.txtAddress);
            txtCity = (TextView) view.findViewById(R.id.txtCity);
            txtProvince = (TextView) view.findViewById(R.id.txtProvince);
            txtStatus = (TextView)view.findViewById(R.id.txtStatus);
            buttonApprove = (Button)view.findViewById(R.id.buttonApprove);
            buttonReject = (Button)view.findViewById(R.id.buttonReject);
        }
    }

    public RequestCustomerAdapter(Context context, List<CustomerModel> customerModelList){
        this.context = context;
        this.customerModelList = customerModelList;
    }

    public void updateList(List<CustomerModel> newList){
        this.customerModelList.clear();
        this.customerModelList = newList;
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_request_customer, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final CustomerModel customerModel = customerModelList.get(position);
        holder.txtIdCustomer.setText(": "+customerModel.getFirst_name());
        holder.txtDecode.setText(": "+customerModel.getDecode());
        holder.txtAddress.setText(": "+customerModel.getAddress());
        holder.txtCity.setText(": "+customerModel.getCity());
        holder.txtProvince.setText(": "+customerModel.getProvince());

        if(customerModel.getActive() == 1 && customerModel.getApprove() == 1){
            holder.txtStatus.setText("Activated");
        } else {
            holder.txtStatus.setText("Inactive");
        }

        holder.buttonApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ApproveResponse> approveResponseCall = RestClient.getRestClient().requestCustomerAction(
                        "Bearer "+new SharedPreferenceManager().getPreferences(context, "token"), customerModel.getDecode(), 1, 1);
                approveResponseCall.enqueue(new Callback<ApproveResponse>() {
                    @Override
                    public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(context, "Approve success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, DashboardAdminActivity.class);
                            context.startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ApproveResponse> call, Throwable t) {

                    }
                });
            }
        });
        holder.buttonReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ApproveResponse> approveResponseCall = RestClient.getRestClient().requestCustomerAction(
                        "Bearer "+new SharedPreferenceManager().getPreferences(context, "token"), customerModel.getDecode(), 0, 0);
                approveResponseCall.enqueue(new Callback<ApproveResponse>() {
                    @Override
                    public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(context, "Reject success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, DashboardAdminActivity.class);
                            context.startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ApproveResponse> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount(){
        return customerModelList.size();
    }
}