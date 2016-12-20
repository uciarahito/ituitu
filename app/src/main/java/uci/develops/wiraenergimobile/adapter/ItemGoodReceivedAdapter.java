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
import uci.develops.wiraenergimobile.activity.FormRequestGoodReceived;
import uci.develops.wiraenergimobile.activity.FormRequestPurchaseOrderActivity;

/**
 * Created by ArahitoPC on 10/24/2016.
 */
public class ItemGoodReceivedAdapter extends RecyclerView.Adapter<ItemGoodReceivedAdapter.MyViewHolder> {
    //    private List<QuotationModel> quotationModelList;
    private Context context;

    Map<String, List<String>> mRoles = new TreeMap<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView_accepted)
        TextView textView_accepted;
        @BindView(R.id.textView_item)
        TextView textView_item;
        @BindView(R.id.textView_unit)
        TextView textView_unit;
        @BindView(R.id.textView_qty_po)
        TextView textView_qty_po;
        @BindView(R.id.textView_qty_gr)
        TextView textView_qty_gr;
        @BindView(R.id.textView_warehouse)
        TextView textView_warehouse;
        @BindView(R.id.imageView_view)
        ImageView imageView_view;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

//    public ItemGoodReceivedAdapter(Context context, List<QuotationModel> quotationModelList) {
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
                .inflate(R.layout.item_good_received, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
//        final QuotationModel quotationModel = quotationModelList.get(position);
        holder.imageView_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Set decode melalui data per row
                 * menampung di shared preference
                 */
                Intent intent = new Intent(context, FormRequestGoodReceived.class);
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