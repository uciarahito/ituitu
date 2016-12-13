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

public class FragmentSOCustomerDetail extends Fragment {

    @BindView(R.id.textView_so_number) TextView textView_so_number;
    @BindView(R.id.textView_customer) TextView textView_customer;
    @BindView(R.id.textView_so_date) TextView textView_so_date;
    @BindView(R.id.textView_payment_term) TextView textView_payment_term;

    public FragmentSOCustomerDetail() {
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
        view = inflater.inflate(R.layout.fragment_so_customer_detail, container, false);
        ButterKnife.bind(this, view);

        loadDataCustomerDetail();

        // start listening for refresh local file list in
        return view;
        //return inflater.inflate(R.layout.activity_ongoing_order, container, false);
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
