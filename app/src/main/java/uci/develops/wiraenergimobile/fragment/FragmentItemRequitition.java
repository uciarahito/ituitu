package uci.develops.wiraenergimobile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.adapter.ItemSalesQuotationOrderAdapter;
import uci.develops.wiraenergimobile.helper.DividerItemDecoration;
import uci.develops.wiraenergimobile.model.QuotationModel;

public class FragmentItemRequitition extends Fragment{

    private TextView textView_customer_note, textView_terbilang;
    private EditText editText_bruto, editText_disc1, editText_disc2, editText_other_cost, editText_netto, editText_admin_note;
    private RecyclerView recyclerView;
    private ItemSalesQuotationOrderAdapter itemSalesQuotationOrderAdapter;

    public FragmentItemRequitition(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_item_requitition, container, false);

        initializeComponent(view);

        // start listening for refresh local file list in
        return view;
        //return inflater.inflate(R.layout.activity_ongoing_order, container, false);
    }

    private void initializeComponent(View view){
        textView_customer_note = (TextView) view.findViewById(R.id.textView_customer_note);
        textView_terbilang = (TextView) view.findViewById(R.id.textView_terbilang);
        editText_bruto = (EditText) view.findViewById(R.id.editText_bruto);
        editText_disc1 = (EditText) view.findViewById(R.id.editText_disc1);
        editText_disc2 = (EditText) view.findViewById(R.id.editText_disc2);
        editText_other_cost = (EditText) view.findViewById(R.id.editText_other_cost);
        editText_netto = (EditText) view.findViewById(R.id.editText_netto);
        editText_admin_note = (EditText) view.findViewById(R.id.editText_admin_note);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        List<QuotationModel> quotationModelList = new ArrayList<>();
        itemSalesQuotationOrderAdapter = new ItemSalesQuotationOrderAdapter(getContext(), quotationModelList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(itemSalesQuotationOrderAdapter);
    }

    private void loadData(){

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
