package uci.develops.wiraenergimobile.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.adapter.ItemQuotationAdapter;
import uci.develops.wiraenergimobile.helper.DividerItemDecoration;
import uci.develops.wiraenergimobile.model.QuotationModel;

public class FragmentQuotationItemQuotation extends Fragment{

    private Spinner spinner_item, spinner_unit;
    private EditText editText_send_date, editText_quantity, editText_notes;
    private DatePickerDialog datePickerDialog;
    private Button button_add_item, button_save, button_cancel;
    private RecyclerView recyclerView;
    int counter_list = 0;
    String qty = "", note = "";

    private ItemQuotationAdapter itemQuotationAdapter;

    public FragmentQuotationItemQuotation(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_req_quotation_item_quotation, container, false);

        initializeComponent(view);

        // start listening for refresh local file list in
        return view;
        //return inflater.inflate(R.layout.activity_ongoing_order, container, false);
    }

    private void initializeComponent(View view){
        button_add_item = (Button)view.findViewById(R.id.button_add_item);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycle_view);
        List<QuotationModel> quotationModelsList = new ArrayList<>();
        itemQuotationAdapter = new ItemQuotationAdapter(getContext(), quotationModelsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(itemQuotationAdapter);

        button_add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAddItem();
            }
        });
    }

    Dialog dialog_add_item;
    private void showDialogAddItem() {
        dialog_add_item = new Dialog(getContext());
        dialog_add_item.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_add_item.setContentView(R.layout.custom_dialog_form_item_quotation);

        spinner_item = (Spinner) dialog_add_item.findViewById(R.id.spinner_item);
        spinner_unit = (Spinner) dialog_add_item.findViewById(R.id.spinner_unit);
        editText_send_date = (EditText) dialog_add_item.findViewById(R.id.editText_send_date);
        editText_quantity = (EditText) dialog_add_item.findViewById(R.id.editText_quantity);
        editText_notes = (EditText) dialog_add_item.findViewById(R.id.editText_notes);
        button_save = (Button) dialog_add_item.findViewById(R.id.button_save);
        button_cancel = (Button) dialog_add_item.findViewById(R.id.button_cancel);

        List<String> listItem = new ArrayList<String>();
        listItem.add("PCS");
        listItem.add("Militer");
        listItem.add("Liter");
        listItem.add("Barrel");
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listItem);
        itemAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_item.setAdapter(itemAdapter);

        List<String> listUnit = new ArrayList<String>();
        listUnit.add("Kertas A4 Putih");
        listUnit.add("Tinta Epson");
        listUnit.add("Laptop Asus");
        listUnit.add("Solar");
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, listUnit);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_unit.setAdapter(unitAdapter);

        //utk send date
        final Calendar calendar = Calendar.getInstance();
        int mYear = calendar.get(Calendar.YEAR); // current year
        int mMonth = calendar.get(Calendar.MONTH); // current month
        int mDay = calendar.get(Calendar.DAY_OF_MONTH); // current day
        // date picker dialog
        datePickerDialog = new DatePickerDialog(getActivity().getApplicationContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // set day of month , month and year value in the edit text
                editText_send_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_add_item.dismiss();
            }
        });

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_add_item.dismiss();
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getContext().getSystemService(getContext().WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialog_add_item.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog_add_item.setCancelable(true);
        dialog_add_item.show();
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
