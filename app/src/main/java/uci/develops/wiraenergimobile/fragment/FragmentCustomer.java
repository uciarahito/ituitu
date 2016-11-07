package uci.develops.wiraenergimobile.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.activity.FormCustomerActivity;
import uci.develops.wiraenergimobile.activity.ListCustomerActivity;
import uci.develops.wiraenergimobile.activity.ListRequestCustomerActivity;

/**
 * Created by user on 11/3/2016.
 */

public class FragmentCustomer extends Fragment implements View.OnClickListener{
    private LinearLayout linearLayout_menu_all_customer, linearLayout_menu_new_customer;

    public FragmentCustomer(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_customer, container, false);

        initializeComponent(view);

        // start listening for refresh local file list in
        return view;
        //return inflater.inflate(R.layout.activity_ongoing_order, container, false);
    }

    private void initializeComponent(View view){
        linearLayout_menu_all_customer = (LinearLayout) view.findViewById(R.id.linearLayout_menu_all_customer);
        linearLayout_menu_new_customer = (LinearLayout) view.findViewById(R.id.linearLayout_menu_new_customer);

        linearLayout_menu_all_customer.setOnClickListener(this);
        linearLayout_menu_new_customer.setOnClickListener(this);
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
        if (v == linearLayout_menu_all_customer) {
            Intent intent = new Intent(getActivity().getApplicationContext(), ListCustomerActivity.class);
            startActivity(intent);
        }

        if (v == linearLayout_menu_new_customer) {
            Intent intent = new Intent(getActivity().getApplicationContext(), ListRequestCustomerActivity.class);
            startActivity(intent);
        }
    }
}