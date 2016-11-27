package uci.develops.wiraenergimobile.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.activity.DashboardAdminActivity;

public class FragmentMasterSetup extends Fragment implements View.OnClickListener{

    private LinearLayout linearLayout_menu_master_customer;

    public FragmentMasterSetup(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_master_setup, container, false);

        initializeComponent(view);

        // start listening for refresh local file list in
        return view;
        //return inflater.inflate(R.layout.activity_ongoing_order, container, false);
    }

    private void initializeComponent(View view){
        linearLayout_menu_master_customer = (LinearLayout) view.findViewById(R.id.linearLayout_menu_master_customer);

        linearLayout_menu_master_customer.setOnClickListener(this);
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
        if (v == linearLayout_menu_master_customer) {
            Intent intent = new Intent(getActivity().getApplicationContext(), DashboardAdminActivity.class);
            startActivity(intent);
        }
    }
}
