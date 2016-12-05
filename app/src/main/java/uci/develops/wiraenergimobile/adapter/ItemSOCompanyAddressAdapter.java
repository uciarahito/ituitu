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
import uci.develops.wiraenergimobile.model.CustomerModel;

/**
 * Created by ArahitoPC on 10/24/2016.
 */
public class ItemSOCompanyAddressAdapter extends RecyclerView.Adapter<ItemSOCompanyAddressAdapter.MyViewHolder> {
    List<CustomerModel> customerModelList;
    private Context context;

    Map<String, List<String>> mRoles = new TreeMap<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_payment_address;

        public MyViewHolder(View view) {
            super(view);
            textView_payment_address = (TextView) view.findViewById(R.id.textView_payment_address);
        }
    }

    public ItemSOCompanyAddressAdapter(Context context, List<CustomerModel> customerModelList) {
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
                .inflate(R.layout.item_payment_address, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final CustomerModel customerModel = customerModelList.get(position);
        holder.textView_payment_address.setText(customerModel.getName1() == null ? "" : customerModel.getName1());
    }

    @Override
    public int getItemCount() {
        return customerModelList.size();
    }
}