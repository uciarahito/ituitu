package uci.develops.wiraenergimobile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uci.develops.wiraenergimobile.R;

public class FragmentDeliveryOrderExpedition extends Fragment implements View.OnClickListener{

    @BindView(R.id.editText_supplier_name_value) EditText editText_supplier_name_value;
    @BindView(R.id.editText_driver_name_value) EditText editText_driver_name_value;
    @BindView(R.id.editText_capacity) EditText editText_capacity;
    @BindView(R.id.editText_min_quantity) EditText editText_min_quantity;
    @BindView(R.id.editText_price_unit) EditText editText_price_unit;
    @BindView(R.id.spinner_supplier_name) Spinner spinner_supplier_name;
    @BindView(R.id.spinner_driver_name) Spinner spinner_driver_name;
    @BindView(R.id.spinner_vehicle_number) Spinner spinner_vehicle_number;
    @BindView(R.id.spinner_shipping_costs) Spinner spinner_shipping_costs;

    public FragmentDeliveryOrderExpedition() {
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
        view = inflater.inflate(R.layout.fragment_delivery_order_expedition, container, false);
        ButterKnife.bind(this, view);
        initializeComponent(view);

        // start listening for refresh local file list in
        return view;
        //return inflater.inflate(R.layout.activity_ongoing_order, container, false);
    }

    private void initializeComponent(View view){
        List<String> testList = new ArrayList<String>();
        testList.add("Testing1");
        testList.add("Testing2");
        testList.add("Testing3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.spinner_item, testList);
        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_supplier_name.setAdapter(dataAdapter);
        spinner_driver_name.setAdapter(dataAdapter);
        spinner_vehicle_number.setAdapter(dataAdapter);
        spinner_shipping_costs.setAdapter(dataAdapter);

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
