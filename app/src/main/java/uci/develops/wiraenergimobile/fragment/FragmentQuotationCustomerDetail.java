package uci.develops.wiraenergimobile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import uci.develops.wiraenergimobile.R;

public class FragmentQuotationCustomerDetail extends Fragment {

    private TextView textView_customer, textView_request_number, textView_request_date,
            textView_approve_date;

    public FragmentQuotationCustomerDetail() {
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
        view = inflater.inflate(R.layout.fragment_quotation_customer_detail, container, false);

        initializeComponent(view);
        loadDataCustomerDetail();

        // start listening for refresh local file list in
        return view;
        //return inflater.inflate(R.layout.activity_ongoing_order, container, false);
    }

    private void initializeComponent(View view) {
        textView_customer = (TextView) view.findViewById(R.id.textView_customer);
        textView_request_number = (TextView) view.findViewById(R.id.textView_request_number);
        textView_request_date = (TextView) view.findViewById(R.id.textView_request_date);
        textView_approve_date = (TextView) view.findViewById(R.id.textView_approve_date);
    }

    private void loadDataCustomerDetail(){

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
