package uci.develops.wiraenergimobile.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.activity.DeliveryOrderActivity;
import uci.develops.wiraenergimobile.activity.FormRequestQuotationCustomerActivity;
import uci.develops.wiraenergimobile.activity.InvoiceActivity;
import uci.develops.wiraenergimobile.activity.PaymentActivity;
import uci.develops.wiraenergimobile.activity.SalesOrderActivity;
import uci.develops.wiraenergimobile.activity.SalesQuotationAdminActivity;
import uci.develops.wiraenergimobile.activity.SalesQuotationCustomerActivity;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;

/**
 * Created by user on 11/3/2016.
 */

public class FragmentSales extends Fragment implements View.OnClickListener{

    @BindView(R.id.linearLayout_menu_sales_quotation) LinearLayout linearLayout_menu_sales_quotation;
    @BindView(R.id.linearLayout_menu_sales_order) LinearLayout linearLayout_menu_sales_order;
    @BindView(R.id.linearLayout_menu_delivery_order) LinearLayout linearLayout_menu_delivery_order;
    @BindView(R.id.linearLayout_menu_invoicing) LinearLayout linearLayout_menu_invoicing;
    @BindView(R.id.linearLayout_menu_payment) LinearLayout linearLayout_menu_payment;

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
        ButterKnife.bind(this, view);
        initializeComponent(view);

        // start listening for refresh local file list in
        return view;
        //return inflater.inflate(R.layout.activity_ongoing_order, container, false);
    }

    private void initializeComponent(View view){
        linearLayout_menu_sales_quotation.setOnClickListener(this);
        linearLayout_menu_sales_order.setOnClickListener(this);
        linearLayout_menu_delivery_order.setOnClickListener(this);
        linearLayout_menu_invoicing.setOnClickListener(this);
        linearLayout_menu_payment.setOnClickListener(this);
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
            if (new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "roles").equals("admin")){
                Intent intent = new Intent(getActivity().getApplicationContext(), SalesQuotationAdminActivity.class);
                startActivity(intent);
            } else if (new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "roles").equals("customer")){
                Intent intent = new Intent(getActivity().getApplicationContext(), SalesQuotationCustomerActivity.class);
                startActivity(intent);
            }
        }

        if (v == linearLayout_menu_sales_order) {
            Intent intent = new Intent(getActivity().getApplicationContext(), SalesOrderActivity.class);
            startActivity(intent);
        }

        if (v == linearLayout_menu_delivery_order) {
            Intent intent = new Intent(getActivity().getApplicationContext(), DeliveryOrderActivity.class);
            startActivity(intent);
        }

        if (v == linearLayout_menu_invoicing) {
            Intent intent = new Intent(getActivity().getApplicationContext(), InvoiceActivity.class);
            startActivity(intent);
        }

        if (v == linearLayout_menu_payment) {
            Intent intent = new Intent(getActivity().getApplicationContext(), PaymentActivity.class);
            startActivity(intent);
        }
    }

}
