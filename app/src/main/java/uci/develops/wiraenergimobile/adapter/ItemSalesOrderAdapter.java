package uci.develops.wiraenergimobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.activity.FormRequestQuotationCustomerActivity;
import uci.develops.wiraenergimobile.activity.FormSalesOrderActivity;
import uci.develops.wiraenergimobile.model.QuotationModel;

/**
 * Created by ArahitoPC on 10/24/2016.
 */
public class ItemSalesOrderAdapter extends RecyclerView.Adapter<ItemSalesOrderAdapter.MyViewHolder> {
    private List<QuotationModel> quotationModelList;
    private Context context;

    Map<String, List<String>> mRoles = new TreeMap<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView_status) TextView textView_status;
        @BindView(R.id.textView_name_customer) TextView textView_name_customer;
        @BindView(R.id.textView_code_customer) TextView textView_code_customer;
        @BindView(R.id.textView_total_price) TextView textView_total_price;
        @BindView(R.id.textView_so_date) TextView textView_so_date;
        @BindView(R.id.textView_delivery_date) TextView textView_delivery_date;
        @BindView(R.id.textView_days_to_delivery) TextView textView_days_to_delivery;
        @BindView(R.id.imageView_view) ImageView imageView_view;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public ItemSalesOrderAdapter(Context context, List<QuotationModel> quotationModelList) {
        this.context = context;
        this.quotationModelList = quotationModelList;
    }

    public void updateList(List<QuotationModel> newList) {
        this.quotationModelList.clear();
        this.quotationModelList = newList;
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sales_order, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final QuotationModel quotationModel = quotationModelList.get(position);

        holder.imageView_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Set decode melalui data per row
                 * menampung di shared preference
                 */
                Intent intent = new Intent(context, FormSalesOrderActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quotationModelList.size();
    }
}