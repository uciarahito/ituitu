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
import uci.develops.wiraenergimobile.activity.GoodReceivedActivity;
import uci.develops.wiraenergimobile.activity.ListCustomerActivity;
import uci.develops.wiraenergimobile.activity.ListRequestCustomerActivity;
import uci.develops.wiraenergimobile.activity.PurchaseOrderActivity;

/**
 * Created by user on 11/3/2016.
 */

public class FragmentPurchasing extends Fragment implements View.OnClickListener{
    @BindView(R.id.linearLayout_menu_purchase_order) LinearLayout linearLayout_menu_purchase_order;
    @BindView(R.id.linearLayout_menu_good_receive) LinearLayout linearLayout_menu_good_receive;

    private static final String KEY_MOVIE_TITLE = "key_title";

    public FragmentPurchasing(){}

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     *
     */
    public static FragmentPurchasing newInstance(String movieTitle) {
        FragmentPurchasing fragmentPurchasing = new FragmentPurchasing();
        Bundle args = new Bundle();
        args.putString(KEY_MOVIE_TITLE, movieTitle);
        fragmentPurchasing.setArguments(args);

        return fragmentPurchasing;
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
        view = inflater.inflate(R.layout.fragment_purchasing, container, false);
        ButterKnife.bind(this, view);
        initializeComponent(view);

        // start listening for refresh local file list in
        return view;
        //return inflater.inflate(R.layout.activity_ongoing_order, container, false);
    }

    private void initializeComponent(View view){
        linearLayout_menu_purchase_order.setOnClickListener(this);
        linearLayout_menu_good_receive.setOnClickListener(this);
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
        if (v == linearLayout_menu_purchase_order) {
            Intent intent = new Intent(getActivity().getApplicationContext(), PurchaseOrderActivity.class);
            startActivity(intent);
        }

        if (v == linearLayout_menu_good_receive) {
            Intent intent = new Intent(getActivity().getApplicationContext(), GoodReceivedActivity.class);
            startActivity(intent);
        }
    }
}