package uci.develops.wiraenergimobile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uci.develops.wiraenergimobile.R;

public class FragmentSOItemSO extends Fragment {

    @BindView(R.id.editText_quantity) EditText editText_quantity;
    @BindView(R.id.editText_disc) EditText editText_disc;
    @BindView(R.id.editText_disc_amount) EditText editText_disc_amount;
    @BindView(R.id.editText_unit_price) EditText editText_unit_price;
    @BindView(R.id.editText_total_commission) EditText editText_total_commission;
    @BindView(R.id.editText_sub_total) EditText editText_sub_total;
    @BindView(R.id.editText_note_item) EditText editText_note_item;
    @BindView(R.id.spinner_unit) Spinner spinner_unit;
    @BindView(R.id.spinner_item) Spinner spinner_item;

    public FragmentSOItemSO() {
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
        view = inflater.inflate(R.layout.fragment_so_item_so, container, false);
        ButterKnife.bind(this, view);

        // start listening for refresh local file list in
        return view;
        //return inflater.inflate(R.layout.activity_ongoing_order, container, false);
    }

    private void initializeComponent(){
        List<String> listItem = new ArrayList<String>();
        listItem.add("Solar");
        listItem.add("Bensin");
        listItem.add("Pertamax");
        listItem.add("Barrel");
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.spinner_item, listItem);
        itemAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_item.setAdapter(itemAdapter);

        List<String> listUnit = new ArrayList<String>();
        listUnit.add("Liter");
        listUnit.add("Kilo Liter");
        listUnit.add("Mili Liter");
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.spinner_item, listUnit);
        unitAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_unit.setAdapter(unitAdapter);

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
