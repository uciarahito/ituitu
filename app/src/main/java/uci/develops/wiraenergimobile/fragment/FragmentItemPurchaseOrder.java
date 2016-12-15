package uci.develops.wiraenergimobile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import uci.develops.wiraenergimobile.R;

public class FragmentItemPurchaseOrder extends Fragment{

    @BindView(R.id.textView_item_name) TextView textView_item_name;
    @BindView(R.id.textView_unit) TextView textView_unit;
    @BindView(R.id.textView_qty) TextView textView_qty;
    @BindView(R.id.textView_qty_gr) TextView textView_qty_gr;
    @BindView(R.id.textView_balance) TextView textView_balance;
    @BindView(R.id.textView_unit_price) TextView textView_unit_price;
    @BindView(R.id.textView_amount_disc) TextView textView_amount_disc;
    @BindView(R.id.textView_sub_total) TextView textView_sub_total;

    public FragmentItemPurchaseOrder(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_item_purchase_order, container, false);

        ButterKnife.bind(this, view);

        // start listening for refresh local file list in
        return view;
        //return inflater.inflate(R.layout.activity_ongoing_order, container, false);
    }

    /**
     * ketika activity resume
     * melakukan register broadcast receiver
     */
    @Override
    public void onResume() {
        super.onResume();
    }
}
