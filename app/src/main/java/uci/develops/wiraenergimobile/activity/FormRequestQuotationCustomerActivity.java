package uci.develops.wiraenergimobile.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.adapter.ItemRequestQuotationAdapter;
import uci.develops.wiraenergimobile.helper.DividerItemDecoration;
import uci.develops.wiraenergimobile.model.QuotationModel;

public class FormRequestQuotationCustomerActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout linearLayoutTitle1, linearLayoutTitle2;
    private LinearLayout linearLayoutContent1, linearLayoutContent2;
    private LinearLayout linearLayoutContainer1, linearLayoutContainer2;
    private LinearLayout linearLayoutButtonCancel, linearLayoutButtonSaveAsDraft, linearLayoutButtonSend;
    private EditText editText_customer_note;

    //utk dialog add item
    private Spinner spinner_item, spinner_unit;
    private EditText editText_send_date, editText_quantity, editText_notes;
    private DatePickerDialog datePickerDialog;
    private Button button_add_item, button_save, button_cancel;
    private RecyclerView recyclerView;

    private ItemRequestQuotationAdapter itemRequestQuotationAdapter;

    boolean content1=false, content2=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_request_quotation_customer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeComponent();

    }

    private void initializeComponent(){
        linearLayoutTitle1 = (LinearLayout)findViewById(R.id.linear_layout_title1);
        linearLayoutTitle2 = (LinearLayout)findViewById(R.id.linear_layout_title2);
        linearLayoutContent1 = (LinearLayout)findViewById(R.id.linear_layout_content1);
        linearLayoutContent2 = (LinearLayout)findViewById(R.id.linear_layout_content2);
        linearLayoutContainer1 = (LinearLayout)findViewById(R.id.linear_layout_container_quotation_shipping_address);
        linearLayoutContainer2 = (LinearLayout)findViewById(R.id.linear_layout_container_quotation_billing_address);
        linearLayoutButtonCancel = (LinearLayout)findViewById(R.id.linear_layout_button_cancel);
        linearLayoutButtonSaveAsDraft = (LinearLayout)findViewById(R.id.linear_layout_button_save_as_draft);
        linearLayoutButtonSend = (LinearLayout)findViewById(R.id.linear_layout_button_send);
        editText_customer_note = (EditText)findViewById(R.id.editText_customer_note);

        button_add_item = (Button)findViewById(R.id.button_add_item);
        recyclerView = (RecyclerView)findViewById(R.id.recycle_view);
        List<QuotationModel> quotationModelsList = new ArrayList<>();
        itemRequestQuotationAdapter = new ItemRequestQuotationAdapter(FormRequestQuotationCustomerActivity.this, quotationModelsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(FormRequestQuotationCustomerActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(FormRequestQuotationCustomerActivity.this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(itemRequestQuotationAdapter);

        linearLayoutTitle1.setOnClickListener(this);
        linearLayoutTitle2.setOnClickListener(this);
        button_add_item.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == button_add_item){
            showDialogAddItem();
        }

        if(v == linearLayoutTitle1){
            if(!content1){
                linearLayoutContent1.setVisibility(View.VISIBLE);
                linearLayoutContainer1.setVisibility(View.VISIBLE);
                content1=true;
            } else {
                linearLayoutContent1.setVisibility(View.GONE);
                linearLayoutContainer1.setVisibility(View.GONE);
                content1=false;
            }
        }

        if(v == linearLayoutTitle2){
            if(!content2){
                linearLayoutContent2.setVisibility(View.VISIBLE);
                linearLayoutContainer2.setVisibility(View.VISIBLE);
                content2=true;
            } else {
                linearLayoutContent2.setVisibility(View.GONE);
                linearLayoutContainer2.setVisibility(View.GONE);
                content2=false;
            }
        }
    }

    Dialog dialog_add_item;
    private void showDialogAddItem() {
        dialog_add_item = new Dialog(FormRequestQuotationCustomerActivity.this);
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
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(FormRequestQuotationCustomerActivity.this,
                R.layout.spinner_item, listItem);
        itemAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_item.setAdapter(itemAdapter);

        List<String> listUnit = new ArrayList<String>();
        listUnit.add("Kertas A4 Putih");
        listUnit.add("Tinta Epson");
        listUnit.add("Laptop Asus");
        listUnit.add("Solar");
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<String>(FormRequestQuotationCustomerActivity.this,
                R.layout.spinner_item, listUnit);
        unitAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_unit.setAdapter(unitAdapter);

        editText_send_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //utk send date
                final Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR); // current year
                int mMonth = calendar.get(Calendar.MONTH); // current month
                int mDay = calendar.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(FormRequestQuotationCustomerActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        editText_send_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

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
        WindowManager windowmanager = (WindowManager) FormRequestQuotationCustomerActivity.this.getSystemService(FormRequestQuotationCustomerActivity.this.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialog_add_item.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog_add_item.setCancelable(true);
        dialog_add_item.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
