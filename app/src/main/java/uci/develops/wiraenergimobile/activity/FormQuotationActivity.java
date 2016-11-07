package uci.develops.wiraenergimobile.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import uci.develops.wiraenergimobile.R;

public class FormQuotationActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText_qty_number, editText_qty_date, editText_due_date, editText_shipping_address, editText_terbilang,
            editText_note, editText_bruto, editText_disc_percent, editText_disc_number, editText_ppn, editText_other_cost, editText_netto;
    private Spinner spinner_customer, spinner_tax_ppn;
    private Button button_cancel, button_save;
    private ImageView imageView_view, imageView_edit, imageView_delete, imageView_add;
    private TextView textView_item_name, textView_qty_item, textView_unit_item;
    private DatePickerDialog datePickerDialog;
    private Dialog dialog_item;

    //utk add item
    private Spinner spinner_item, spinner_unit;
    private EditText editText_price, editText_quantity, editText_discount, editText_disc_amount, editText_sub_total;
    private Button button_cancel_item, button_save_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_quotation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeComponent();

    }

    private void initializeComponent(){
        textView_item_name = (TextView) findViewById(R.id.textView_item_name);
        textView_qty_item = (TextView) findViewById(R.id.textView_qty_item);
        textView_unit_item = (TextView) findViewById(R.id.textView_unit_item);
        editText_qty_number = (EditText) findViewById(R.id.editText_qty_number);
        editText_qty_date = (EditText) findViewById(R.id.editText_qty_date);
        editText_due_date = (EditText) findViewById(R.id.editText_due_date);
        editText_shipping_address = (EditText) findViewById(R.id.editText_shipping_address);
        editText_terbilang = (EditText) findViewById(R.id.editText_terbilang);
        editText_note = (EditText) findViewById(R.id.editText_note);
        editText_bruto = (EditText) findViewById(R.id.editText_bruto);
        editText_disc_percent = (EditText) findViewById(R.id.editText_disc_percent);
        editText_disc_number = (EditText) findViewById(R.id.eidtText_disc_number);
        editText_ppn = (EditText) findViewById(R.id.editText_ppn);
        editText_other_cost = (EditText) findViewById(R.id.editText_other_cost);
        editText_netto = (EditText) findViewById(R.id.editText_netto);
        spinner_customer = (Spinner) findViewById(R.id.spinner_customer);
        spinner_tax_ppn = (Spinner) findViewById(R.id.spinner_tax_ppn);
        button_cancel = (Button) findViewById(R.id.button_cancel);
        button_save = (Button) findViewById(R.id.button_save);
        imageView_view = (ImageView) findViewById(R.id.imageView_view);
        imageView_edit = (ImageView) findViewById(R.id.imageView_edit);
        imageView_delete = (ImageView) findViewById(R.id.imageView_delete);
        imageView_add = (ImageView) findViewById(R.id.imageView_add);

        //utk add item
        spinner_item = (Spinner) findViewById(R.id.spinner_item);
        spinner_unit = (Spinner) findViewById(R.id.spinner_unit);
        editText_price = (EditText) findViewById(R.id.editText_price);
        editText_quantity = (EditText) findViewById(R.id.editText_quantity);
        editText_discount = (EditText) findViewById(R.id.editText_discount);
        editText_disc_amount = (EditText) findViewById(R.id.editText_disc_amount);
        editText_sub_total = (EditText) findViewById(R.id.editText_sub_total);
        button_cancel_item = (Button) findViewById(R.id.button_cancel);
        button_save_item = (Button) findViewById(R.id.button_save);

        dialog_item = new Dialog(FormQuotationActivity.this);
        dialog_item.requestWindowFeature(Window.FEATURE_NO_TITLE);

        List<String> check_List = new ArrayList<String>();
        check_List.add("No");
        check_List.add("Yes");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(FormQuotationActivity.this, android.R.layout.simple_spinner_item, check_List);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_customer.setAdapter(dataAdapter);
        spinner_tax_ppn.setAdapter(dataAdapter);

        button_cancel.setOnClickListener(this);
        button_save.setOnClickListener(this);
        imageView_view.setOnClickListener(this);
        imageView_edit.setOnClickListener(this);
        imageView_delete.setOnClickListener(this);
        imageView_add.setOnClickListener(this);
        editText_qty_date.setOnClickListener(this);
        editText_due_date.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent intent;
        if (v == editText_qty_date) {
            final Calendar calendar = Calendar.getInstance();
            int mYear = calendar.get(Calendar.YEAR); // current year
            int mMonth = calendar.get(Calendar.MONTH); // current month
            int mDay = calendar.get(Calendar.DAY_OF_MONTH); // current day
            // date picker dialog
            datePickerDialog = new DatePickerDialog(FormQuotationActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    // set day of month , month and year value in the edit text
                    editText_qty_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == editText_due_date) {
            final Calendar calendar = Calendar.getInstance();
            int mYear = calendar.get(Calendar.YEAR); // current year
            int mMonth = calendar.get(Calendar.MONTH); // current month
            int mDay = calendar.get(Calendar.DAY_OF_MONTH); // current day
            // date picker dialog
            datePickerDialog = new DatePickerDialog(FormQuotationActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    // set day of month , month and year value in the edit text
                    editText_due_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == button_save) {

        }
        if (v == imageView_view) {
            dialog_item.setContentView(R.layout.content_item_quotation);

        }
        if (v == imageView_edit) {
            dialog_item.setContentView(R.layout.content_item_quotation);
        }
        if (v == imageView_delete) {

        }
        if (v == imageView_add) {
            dialog_item.setContentView(R.layout.content_item_quotation);
        }
        if (v == button_cancel) {
            intent = new Intent(FormQuotationActivity.this, ReportQuotationActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
