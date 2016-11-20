package uci.develops.wiraenergimobile.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.model.CustomerAddressModel;

/**
 * Created by user on 11/20/2016.
 */

public class ShippingAddressAdapter extends RecyclerView.Adapter<ShippingAddressAdapter.MyViewHolder> {
    private List<CustomerAddressModel> customerAddressModelList;
    private Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_name, textView_phone, textView_mobile, textView_address;

        public MyViewHolder(View view) {
            super(view);
            textView_name = (TextView) view.findViewById(R.id.textView_name);
            textView_phone = (TextView) view.findViewById(R.id.textView_phone);
            textView_mobile = (TextView) view.findViewById(R.id.textView_mobile);
            textView_address = (TextView) view.findViewById(R.id.textView_address);
        }
    }

    public ShippingAddressAdapter(Context context, List<CustomerAddressModel> customerAddressModelList) {
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
        holder.textView_name.setText(""+customerAddressModel.getName());
        holder.textView_phone.setText(""+customerAddressModel.getPhone());
        holder.textView_mobile.setText(""+customerAddressModel.getMobile());
        holder.textView_address.setText(""+customerAddressModel.getAddress());
    }

    @Override
    public int getItemCount() {
        return customerAddressModelList.size();
    }
}