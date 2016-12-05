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

import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.activity.FormRequestQuotationCustomerActivity;
import uci.develops.wiraenergimobile.model.QuotationModel;

/**
 * Created by ArahitoPC on 10/24/2016.
 */
public class SalesQuotationAdapter extends RecyclerView.Adapter<SalesQuotationAdapter.MyViewHolder> {
    private List<QuotationModel> quotationModelList;
    private Context context;

    Map<String, List<String>> mRoles = new TreeMap<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_status, textView_code_customer, textView_name_customer,
                textView_qty_item, textView_unit_item, textView_item_name, textView_total_price,
                textView_create_date, textView_send_date;
        public ImageView imageView_edit;

        public MyViewHolder(View view) {
            super(view);
            textView_status = (TextView) view.findViewById(R.id.textView_status);
            textView_code_customer = (TextView) view.findViewById(R.id.textView_code_customer);
            textView_name_customer = (TextView) view.findViewById(R.id.textView_name_customer);
            textView_qty_item = (TextView) view.findViewById(R.id.textView_qty_item);
            textView_unit_item = (TextView) view.findViewById(R.id.textView_unit_item);
            textView_item_name = (TextView) view.findViewById(R.id.textView_item_name);
            textView_total_price = (TextView) view.findViewById(R.id.textView_total_price);
            textView_create_date = (TextView) view.findViewById(R.id.textView_create_date);
            textView_send_date = (TextView) view.findViewById(R.id.textView_send_date);

            imageView_edit = (ImageView) view.findViewById(R.id.imageView_edit);
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
//                new SharedPreferenceManager().setPreferences(context, "customer_decode", ""+customerModel.getDecode());
//                new SharedPreferenceManager().setPreferences(context, "customer_user_id", ""+customerModel.getUser_id());
//                Toast.makeText(context, "User_ID: "+new SharedPreferenceManager().getPreferences(context, "customer_user_id"), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, FormRequestQuotationCustomerActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quotationModelList.size();
    }
}