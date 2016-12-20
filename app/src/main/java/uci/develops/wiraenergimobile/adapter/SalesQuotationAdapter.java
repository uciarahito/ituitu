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
import uci.develops.wiraenergimobile.activity.FormSalesQuotationActivity;
import uci.develops.wiraenergimobile.model.QuotationModel;

/**
 * Created by ArahitoPC on 10/24/2016.
 */
public class SalesQuotationAdapter extends RecyclerView.Adapter<SalesQuotationAdapter.MyViewHolder> {
    private List<QuotationModel> quotationModelList;
    private Context context;

    Map<String, List<String>> mRoles = new TreeMap<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView_status) TextView textView_status;
        @BindView(R.id.textView_code_customer) TextView textView_code_customer;
        @BindView(R.id.textView_name_customer) TextView textView_name_customer;
        @BindView(R.id.textView_qty_item) TextView textView_qty_item;
        @BindView(R.id.textView_unit_item) TextView textView_unit_item;
        @BindView(R.id.textView_item_name) TextView textView_item_name;
        @BindView(R.id.textView_total_price) TextView textView_total_price;
        @BindView(R.id.textView_create_date) TextView textView_create_date;
        @BindView(R.id.textView_send_date) TextView textView_send_date;
        @BindView(R.id.imageView_edit) ImageView imageView_edit;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public SalesQuotationAdapter(Context context, List<QuotationModel> quotationModelList) {
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
                .inflate(R.layout.item_list_sales_quotation, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final QuotationModel quotationModel = quotationModelList.get(position);

        holder.imageView_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Set decode melalui data per row
                 * menampung di shared preference
                 */

                /**
                 * ada pengecekan jika status nya draft atau selain draft
                 * jika selain draft, maka ke FormSalesQuotationActivity
                 */
                Intent intent = new Intent(context, FormSalesQuotationActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quotationModelList.size();
    }
}