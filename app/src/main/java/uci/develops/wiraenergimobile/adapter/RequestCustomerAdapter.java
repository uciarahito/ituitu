package uci.develops.wiraenergimobile.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.response.RequestListCustomerResponse;

/**
 * Created by ArahitoPC on 10/24/2016.
 */
public class RequestCustomerAdapter extends RecyclerView.Adapter<RequestCustomerAdapter.MyViewHolder>{
    private List<CustomerModel> userModelList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtIdCustomer, txtDecode, txtAddress, txtCity, txtProvince;

        public MyViewHolder(View view) {
            super(view);
            txtIdCustomer = (TextView) view.findViewById(R.id.txtIdCustomer);
            txtDecode = (TextView) view.findViewById(R.id.txtDecode);
            txtAddress = (TextView) view.findViewById(R.id.txtAddress);
            txtCity = (TextView) view.findViewById(R.id.txtCity);
            txtProvince = (TextView) view.findViewById(R.id.txtProvince);
        }
    }

    public RequestCustomerAdapter(Context context, List<CustomerModel> customerModelList){
        this.context = context;
//        this.customerModelList = customerModelList;
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
        final CustomerModel userModel = userModelList.get(position);
//        holder.textView_driver_name.setText(""+userModel.getName());
    }

    @Override
    public int getItemCount(){
        return userModelList.size();
    }
}