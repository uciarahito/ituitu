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
import uci.develops.wiraenergimobile.activity.FormRequestDeliveryOrderActivity;

/**
 * Created by ArahitoPC on 10/24/2016.
 */
public class DeliveryOrderAdapter extends RecyclerView.Adapter<DeliveryOrderAdapter.MyViewHolder> {
//    private List<QuotationModel> quotationModelList;
    private Context context;

    Map<String, List<String>> mRoles = new TreeMap<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView_reminder)
        TextView textView_reminder;
        @BindView(R.id.textView_so_code)
        TextView textView_so_code;
        @BindView(R.id.textView_expedition)
        TextView textView_expedition;
        @BindView(R.id.textView_do_date)
        TextView textView_do_date;
        @BindView(R.id.textView_send_date) TextView textView_send_date;
        @BindView(R.id.textView_qty_delivery) TextView textView_unit_delivery;
        @BindView(R.id.imageView_edit)
        ImageView imageView_edit;
        @BindView(R.id.imageView_delete)
        ImageView imageView_delete;
        @BindView(R.id.linear_layout_title)
        LinearLayout linearLayoutTitle;
        @BindView(R.id.linear_layout_content)
        LinearLayout linearLayoutContent;
        @BindView(R.id.linear_layout_container_item_delivery_order)
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
                .inflate(R.layout.item_list_delivery_order, parent, false);
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
        holder.imageView_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Set decode melalui data per row
                 * menampung di shared preference
                 */
                Intent intent = new Intent(context, FormRequestDeliveryOrderActivity.class);
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
                Intent intent = new Intent(context, FormRequestDeliveryOrderActivity.class);
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