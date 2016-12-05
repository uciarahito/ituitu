package uci.develops.wiraenergimobile.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.model.CustomerAddressModel;

/**
 * Created by ArahitoPC on 10/24/2016.
 */
public class ItemSOShippingAddressAdapter extends RecyclerView.Adapter<ItemSOShippingAddressAdapter.MyViewHolder> {
    List<CustomerAddressModel> customerAddressModelList;
    private Context context;

    Map<String, List<String>> mRoles = new TreeMap<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_payment_address;

        public MyViewHolder(View view) {
            super(view);
            textView_payment_address = (TextView) view.findViewById(R.id.textView_payment_address);
        }
    }

    public ItemSOShippingAddressAdapter(Context context, List<CustomerAddressModel> customerAddressModelList) {
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
                .inflate(R.layout.item_payment_address, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final CustomerAddressModel customerAddressModel = customerAddressModelList.get(position);
        holder.textView_payment_address.setText(": " + customerAddressModel.getName() == null ? "" : customerAddressModel.getName());
    }

    @Override
    public int getItemCount() {
        return customerAddressModelList.size();
    }
}