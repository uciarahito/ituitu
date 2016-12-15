package uci.develops.wiraenergimobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.activity.FormCustomerActivity;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerModel;

/**
 * Created by ArahitoPC on 10/24/2016.
 */
public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {
    private List<CustomerModel> customerModelList;
    private Context context;

    Map<String, List<String>> mRoles = new TreeMap<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtIdCustomer) TextView txtIdCustomer;
        @BindView(R.id.txtDecode) TextView txtDecode;
        @BindView(R.id.txtEmail) TextView txtEmail;
        @BindView(R.id.imageView_view) ImageView imageView_view;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public CustomerAdapter(Context context, List<CustomerModel> customerModelList) {
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
                .inflate(R.layout.item_list_customer, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final CustomerModel customerModel = customerModelList.get(position);
        holder.txtIdCustomer.setText(": " + customerModel.getFirst_name());
        holder.txtDecode.setText(": " + customerModel.getDecode());
        holder.txtEmail.setText(": " + customerModel.getEmail());

        holder.imageView_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Set decode melalui data per row
                 * menampung di shared preference
                 */
                new SharedPreferenceManager().setPreferences(context, "customer_decode", "" + customerModel.getDecode());
                new SharedPreferenceManager().setPreferences(context, "customer_user_id", "" + customerModel.getUser_id());
                Toast.makeText(context, "User_ID: " + new SharedPreferenceManager().getPreferences(context, "customer_user_id"), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, FormCustomerActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return customerModelList.size();
    }
}