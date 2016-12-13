package uci.develops.wiraenergimobile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uci.develops.wiraenergimobile.R;

public class FragmentDashboardInventory extends Fragment implements View.OnClickListener{

    private TextView textView_stock_all_warehouse, textView_stock_per_warehouse, textView_last_purchase, textView_last_purchase_from;
    private Spinner spinner_warehouse;

    public FragmentDashboardInventory() {
        // Required empty public constructor
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
        view = inflater.inflate(R.layout.fragment_dashboard_inventory, container, false);
        initializeComponent(view);
        return view;
    }

    private void initializeComponent(View view){
        textView_stock_all_warehouse = (TextView)view.findViewById(R.id.textView_stock_all_warehouse);
        textView_stock_per_warehouse = (TextView)view.findViewById(R.id.textView_stock_per_warehouse);
        textView_last_purchase = (TextView)view.findViewById(R.id.textView_last_purchase);
        textView_last_purchase_from = (TextView)view.findViewById(R.id.textView_last_purchase_from);
        spinner_warehouse = (Spinner) view.findViewById(R.id.spinner_warehouse);

        List<String> gudang = new ArrayList<String>();
        gudang.add("Warehouse Depok");
        gudang.add("Warehouse Jakarta");
        gudang.add("Warehouse Bandung");
        ArrayAdapter<String> valutaAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.spinner_item, gudang);
        valutaAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_warehouse.setAdapter(valutaAdapter);
//        spinner_gudang.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

}
