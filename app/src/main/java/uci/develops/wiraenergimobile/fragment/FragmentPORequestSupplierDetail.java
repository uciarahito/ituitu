package uci.develops.wiraenergimobile.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.response.CustomerResponse;
import uci.develops.wiraenergimobile.service.RestClient;

public class FragmentPORequestSupplierDetail extends Fragment implements View.OnClickListener{

    @BindView(R.id.editText_po_number) EditText editText_po_number;
    @BindView(R.id.editText_valuta) EditText editText_valuta;
    @BindView(R.id.editText_supplier_name) EditText editText_supplier_name;
    @BindView(R.id.editText_supplier_address) EditText editText_supplier_address;
    @BindView(R.id.editText_po_date) EditText editText_po_date;
    @BindView(R.id.editText_shipping_date) EditText editText_shipping_date;
    @BindView(R.id.editText_payment_term) EditText editText_payment_term;
    @BindView(R.id.editText_shipping_address) EditText editText_shipping_address;
    @BindView(R.id.spinner_supplier) Spinner spinner_supplier;
    @BindView(R.id.spinner_warehouse) Spinner spinner_warehouse;

    DatePickerDialog datePickerDialogPODate, datePickerDialogShippingDate;
    String check_List[];

    public FragmentPORequestSupplierDetail() {
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
        view = inflater.inflate(R.layout.fragment_po_request_supplier_detail, container, false);
        ButterKnife.bind(this, view);
        initializeComponent(view);

        // start listening for refresh local file list in
        return view;
        //return inflater.inflate(R.layout.activity_ongoing_order, container, false);
    }

    private void initializeComponent(View view){

        List<String> tests = new ArrayList<String>();
        tests.add("Test 1");
        tests.add("Test 2");
        tests.add("Test 3");
        ArrayAdapter<String> testAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.spinner_item, tests);
        testAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_supplier.setAdapter(testAdapter);
        spinner_warehouse.setAdapter(testAdapter);

        editText_po_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //utk send date
                final Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR); // current year
                int mMonth = calendar.get(Calendar.MONTH); // current month
                int mDay = calendar.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialogPODate = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        editText_po_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialogPODate.show();
            }
        });


        editText_shipping_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //utk send date
                final Calendar calendarShippingDate = Calendar.getInstance();
                int mYear = calendarShippingDate.get(Calendar.YEAR); // current year
                int mMonth = calendarShippingDate.get(Calendar.MONTH); // current month
                int mDay = calendarShippingDate.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialogShippingDate = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        editText_shipping_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialogShippingDate.show();
            }
        });

//        spinner_supplier.setOnClickListener(this);
//        spinner_warehouse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

//        if (v == spinner_supplier){
//            loadDataSpinnerSupplier();
//        }
//
//        if (v == spinner_warehouse){
//            loadDataSpinnerWarehouse();
//        }
    }

    private void loadDataSpinnerSupplier() {
        //implementasi utk load data spinner supplier
    }

    private void loadDataSpinnerWarehouse() {
        //implementasi utk load data spinner warehouse
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
