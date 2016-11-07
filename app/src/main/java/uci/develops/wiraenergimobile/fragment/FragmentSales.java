package uci.develops.wiraenergimobile.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.activity.RequestQuotationActivity;

/**
 * Created by user on 11/3/2016.
 */

public class FragmentSales extends Fragment implements View.OnClickListener{

    private LinearLayout linearLayout_menu_sales_quotation, linearLayout_menu_sales_order, linearLayout_menu_delivery_order, linearLayout_menu_invoicing_payment;
    private static final String KEY_MOVIE_TITLE = "key_title";

    public FragmentSales(){}

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment FragmentSales.
     */
    public static FragmentSales newInstance(String movieTitle) {
        FragmentSales fragmentSales = new FragmentSales();
        Bundle args = new Bundle();
        args.putString(KEY_MOVIE_TITLE, movieTitle);
        fragmentSales.setArguments(args);

        return fragmentSales;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_sales, container, false);

        initializeComponent(view);

        // start listening for refresh local file list in
        return view;
        //return inflater.inflate(R.layout.activity_ongoing_order, container, false);
    }

    private void initializeComponent(View view){
        linearLayout_menu_sales_quotation = (LinearLayout) view.findViewById(R.id.linearLayout_menu_sales_quotation);
        linearLayout_menu_sales_order = (LinearLayout) view.findViewById(R.id.linearLayout_menu_sales_order);
        linearLayout_menu_delivery_order = (LinearLayout) view.findViewById(R.id.linearLayout_menu_delivery_order);
        linearLayout_menu_invoicing_payment = (LinearLayout) view.findViewById(R.id.linearLayout_menu_invoicing_payment);

        linearLayout_menu_sales_quotation.setOnClickListener(this);
        linearLayout_menu_sales_order.setOnClickListener(this);
        linearLayout_menu_delivery_order.setOnClickListener(this);
        linearLayout_menu_invoicing_payment.setOnClickListener(this);
    }

    /**
     * ketika activity resume
     * melakukan register broadcast receiver
     */
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        if (v == linearLayout_menu_sales_quotation) {
            Intent intent = new Intent(getActivity().getApplicationContext(), RequestQuotationActivity.class);
            startActivity(intent);
        }
    }

}
