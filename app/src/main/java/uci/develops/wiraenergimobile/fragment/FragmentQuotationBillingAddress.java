package uci.develops.wiraenergimobile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import uci.develops.wiraenergimobile.R;

public class FragmentQuotationBillingAddress extends Fragment implements View.OnClickListener {
    @BindView(R.id.layout_tab_shipping_address) LinearLayout layout_tab_shipping_address;
    @BindView(R.id.layout_tab_company_address) LinearLayout layout_tab_company_address;
    @BindView(R.id.layout_container_shipping_address) LinearLayout layout_container_shipping_address;
    @BindView(R.id.layout_container_company_address) LinearLayout layout_container_company_address;

    private LinearLayout[] linearLayouts_fragment = new LinearLayout[2];
    private LinearLayout[] linearLayouts_tabs = new LinearLayout[2];
    int index_fragment = 0;

    public FragmentQuotationBillingAddress() {
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
        view = inflater.inflate(R.layout.fragment_quotation_billing_address, container, false);
        ButterKnife.bind(this, view);
        initializeComponent(view);

        // start listening for refresh local file list in
        return view;
        //return inflater.inflate(R.layout.activity_ongoing_order, container, false);
    }

    private void initializeComponent(View view) {

        linearLayouts_fragment[0] = layout_container_company_address;
        linearLayouts_fragment[1] = layout_container_shipping_address;

        linearLayouts_tabs[0] = layout_tab_company_address;
        linearLayouts_tabs[1] = layout_tab_shipping_address;

        linearLayouts_fragment[index_fragment].setVisibility(View.VISIBLE);

        layout_tab_shipping_address.setOnClickListener(this);
        layout_tab_company_address.setOnClickListener(this);
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
        if (v == layout_tab_company_address) {
            linearLayouts_fragment[0].setVisibility(View.GONE);
            linearLayouts_fragment[1].setVisibility(View.VISIBLE);
        }

        if (v == layout_tab_shipping_address) {
            linearLayouts_fragment[0].setVisibility(View.VISIBLE);
            linearLayouts_fragment[1].setVisibility(View.GONE);
        }

        if (v == layout_tab_company_address) {
            layout_container_company_address.setVisibility(View.VISIBLE);
            layout_container_shipping_address.setVisibility(View.GONE);

            layout_tab_company_address.setBackgroundResource(R.drawable.rounded_rectangle_org);
            layout_tab_shipping_address.setBackgroundResource(R.drawable.rounded_rectangle_gray);
        }

        if (v == layout_tab_shipping_address) {
            layout_container_company_address.setVisibility(View.GONE);
            layout_container_shipping_address.setVisibility(View.VISIBLE);

            layout_tab_company_address.setBackgroundResource(R.drawable.rounded_rectangle_gray);
            layout_tab_shipping_address.setBackgroundResource(R.drawable.rounded_rectangle_org);
        }
    }
}
