package uci.develops.wiraenergimobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.activity.FormRequestPurchaseOrderActivity;

/**
 * Created by ArahitoPC on 10/24/2016.
 */
public class PurchaseOrderAdapter extends RecyclerView.Adapter<PurchaseOrderAdapter.MyViewHolder> {
//    private List<QuotationModel> quotationModelList;
    private Context context;

    Map<String, List<String>> mRoles = new TreeMap<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView_status)
        TextView textView_status;
        @BindView(R.id.textView_code)
        TextView textView_code;
        @BindView(R.id.textView_supplier)
        TextView textView_supplier;
        @BindView(R.id.textView_po_date)
        TextView textView_po_date;
        @BindView(R.id.textView_total)
        TextView textView_total;
        @BindView(R.id.imageView_view)
        ImageView imageView_view;
        @BindView(R.id.imageView_edit)
        ImageView imageView_edit;
        @BindView(R.id.imageView_delete)
        ImageView imageView_delete;
        @BindView(R.id.linear_layout_title)
        LinearLayout linearLayoutTitle;
        @BindView(R.id.linear_layout_content)
        LinearLayout linearLayoutContent;
        @BindView(R.id.linear_layout_container_item_purchase_order)
        LinearLayout linearLayoutContainer;

        boolean content1 = false;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

//    public PurchaseOrderAdapter(Context context, List<QuotationModel> quotationModelList) {
//        this.context = context;
//        this.quotationModelList = quotationModelList;
//    }

//    public void updateList(List<QuotationModel> newList) {
//        this.quotationModelList.clear();
//        this.quotationModelList = newList;
//        this.notifyDataSetChanged();
//    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_purchase_order, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.linearLayoutTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!holder.content1) {
                    holder.linearLayoutContent.setVisibility(View.VISIBLE);
                    holder.linearLayoutContainer.setVisibility(View.VISIBLE);
                    holder.content1 = true;
                } else {
                    holder.linearLayoutContent.setVisibility(View.GONE);
                    holder.linearLayoutContainer.setVisibility(View.GONE);
                    holder.content1 = false;
                }
            }
        });

//        final QuotationModel quotationModel = quotationModelList.get(position);
        holder.imageView_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Set decode melalui data per row
                 * menampung di shared preference
                 */
                Intent intent = new Intent(context, FormRequestPurchaseOrderActivity.class);
                context.startActivity(intent);
            }
        });

        holder.imageView_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Set decode melalui data per row
                 * menampung di shared preference
                 */
                Intent intent = new Intent(context, FormRequestPurchaseOrderActivity.class);
                context.startActivity(intent);
            }
        });

        holder.imageView_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Set decode melalui data per row
                 * menampung di shared preference
                 */
                Intent intent = new Intent(context, FormRequestPurchaseOrderActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
//        return quotationModelList.size();
    }
}