package uci.develops.wiraenergimobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.activity.FormQuotationActivity;

/**
 * Created by ArahitoPC on 11/18/2016.
 */
public class QuotationAdapter extends RecyclerView.Adapter<QuotationAdapter.MyViewHolder> {
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_name_customer, textView_qty_item, textView_unit_item, textView_item_name, textView_send_date;
        public ImageView imageView_detail;

        public MyViewHolder(View view) {
            super(view);
            textView_name_customer = (TextView) view.findViewById(R.id.textView_name_customer);
            textView_qty_item = (TextView) view.findViewById(R.id.textView_qty_item);
            textView_unit_item = (TextView) view.findViewById(R.id.textView_unit_item);
            textView_item_name = (TextView) view.findViewById(R.id.textView_item_name);
            textView_send_date = (TextView) view.findViewById(R.id.textView_send_date);

            imageView_detail = (ImageView) view.findViewById(R.id.imageView_detail);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_request_quotation, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView_name_customer.setText("PT. TPD");
        holder.textView_qty_item.setText("5000");
        holder.textView_unit_item.setText("liter");
        holder.textView_item_name.setText("Solar");
        holder.textView_send_date.setText("Send date: 04/01/2019");

        holder.imageView_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FormQuotationActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
