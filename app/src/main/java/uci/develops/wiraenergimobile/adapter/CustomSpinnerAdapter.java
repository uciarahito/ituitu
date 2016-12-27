package uci.develops.wiraenergimobile.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uci.develops.wiraenergimobile.R;

/**
 * Created by ArahitoPC on 12/26/2016.
 */

public class CustomSpinnerAdapter extends RecyclerView.Adapter<CustomSpinnerAdapter.MyViewHolder>{
    private List<String> spinnerList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewValue)
        TextView textViewValue;
        @BindView(R.id.layoutSpinnerItem)
        LinearLayout layoutSpinnerItem;

        public MyViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
        }
    }

    public CustomSpinnerAdapter(Context context, List<String> newList){
        this.context = context;
        this.spinnerList = newList;
    }

    public void updateSpinnerAdapter(List<String> newList){
        spinnerList.clear();
        spinnerList = newList;
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_spinner, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final String itemSpinner = spinnerList.get(position);
        holder.textViewValue.setText(""+itemSpinner);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.layoutSpinnerItem.setBackgroundResource(R.color.colorPrimary);
                holder.textViewValue.setTextColor(context.getResources().getColor(R.color.colorAccent));
            }
        });
    }

    @Override
    public int getItemCount(){
        return spinnerList.size();
    }
}

