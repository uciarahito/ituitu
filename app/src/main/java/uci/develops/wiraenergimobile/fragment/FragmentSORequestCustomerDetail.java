package uci.develops.wiraenergimobile.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

public class FragmentSORequestCustomerDetail extends Fragment implements View.OnClickListener{

    @BindView(R.id.editText_so_code) EditText editText_so_code;
    @BindView(R.id.editText_so_date) EditText editText_so_date;
    @BindView(R.id.editText_qt_code) EditText editText_qt_code;
    @BindView(R.id.editText_po_code) EditText editText_po_code;
    @BindView(R.id.editText_delivery_date) EditText editText_delivery_date;
    @BindView(R.id.editText_payment_term) EditText editText_payment_term;
    @BindView(R.id.spinner_customer_name) Spinner spinner_customer_name;

    private DatePickerDialog datePickerDialog;
    List<CustomerModel> customerModelList;
    String check_List[];

    public FragmentSORequestCustomerDetail() {
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
        view = inflater.inflate(R.layout.fragment_so_request_customer_detail, container, false);
        ButterKnife.bind(this, view);
        initializeComponent();

        // start listening for refresh local file list in
        return view;
        //return inflater.inflate(R.layout.activity_ongoing_order, container, false);
    }

    private void initializeComponent(){
        editText_so_date.setOnClickListener(this);
        editText_delivery_date.setOnClickListener(this);
        spinner_customer_name.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == editText_so_date){
            setDatePickerDialog();
        }

        if (v == editText_delivery_date){
            setDatePickerDialog();
        }

        if (v == spinner_customer_name){
            loadDataSpinnerCustomerName();
        }

    }

    public void setDatePickerDialog(){
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
                editText_so_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void loadDataSpinnerCustomerName() {
        customerModelList = new ArrayList<>();
        Call<CustomerResponse> customerResponseCall = RestClient.getRestClient().getCustomer("Bearer "
                        + new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "token"),
                new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "customer_decode"));
        customerResponseCall.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getData().size() > 0) {
                        check_List = new String[response.body().getData().size()];
                        int index = 0;
                        for (CustomerModel customerModel : response.body().getData()) {
                            check_List[index] = customerModel.getFirst_name();
                            index++;
                        }

                        customerModelList = response.body().getData();

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                                R.layout.spinner_item, check_List);
                        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                        spinner_customer_name.setAdapter(dataAdapter);

                    }
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {

            }
        });
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
