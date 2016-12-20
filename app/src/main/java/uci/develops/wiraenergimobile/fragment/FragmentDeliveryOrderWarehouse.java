package uci.develops.wiraenergimobile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import uci.develops.wiraenergimobile.R;

public class FragmentDeliveryOrderWarehouse extends Fragment implements View.OnClickListener{

    @BindView(R.id.editText_address) EditText editText_address;
    @BindView(R.id.editText_phone) EditText editText_phone;
    @BindView(R.id.editText_mobile) EditText editText_mobile;
    @BindView(R.id.editText_email) EditText editText_email;
    @BindView(R.id.editText_coordinate) EditText editText_coordinate;
    @BindView(R.id.spinner_warehouse_name) Spinner spinner_warehouse_name;
    @BindView(R.id.linear_layout_button_see_location) LinearLayout linear_layout_button_see_location;

    public FragmentDeliveryOrderWarehouse() {
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
        view = inflater.inflate(R.layout.fragment_delivery_order_warehouse, container, false);
        ButterKnife.bind(this, view);
//        initializeComponent(view);

        // start listening for refresh local file list in
        return view;
        //return inflater.inflate(R.layout.activity_ongoing_order, container, false);
    }

    private void initializeComponent(View view){

    }

    @Override
    public void onClick(View v) {

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
