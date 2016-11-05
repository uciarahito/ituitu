package uci.develops.wiraenergimobile.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import net.rimoto.intlphoneinput.IntlPhoneInput;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import uci.develops.wiraenergimobile.R;

public class RequestQuotationActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spinner_shipping_address, spinner_shipping_name, spinner_item, spinner_unit;
    private EditText quantity, notes, shipping_email, shipping_position, shipping_date;
    private DatePickerDialog datePickerDialog;
    private Button button_save, button_submit, button_cancel;
    private IntlPhoneInput shipping_phone, shipping_mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_quotation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeComponent();
    }

    private void initializeComponent() {
        spinner_shipping_address = (Spinner) findViewById(R.id.spinner_shipping_address);
        spinner_shipping_name = (Spinner) findViewById(R.id.spinner_shipping_name);
        spinner_item = (Spinner) findViewById(R.id.spinner_item);
        spinner_unit = (Spinner) findViewById(R.id.spinner_unit);
        quantity = (EditText) findViewById(R.id.quantity);
        notes = (EditText) findViewById(R.id.notes);
        shipping_email = (EditText) findViewById(R.id.shipping_email);
        shipping_position = (EditText) findViewById(R.id.shipping_position);
        shipping_date = (EditText) findViewById(R.id.shipping_date);
        button_save = (Button) findViewById(R.id.button_save);
        button_submit = (Button) findViewById(R.id.button_submit);
        button_cancel = (Button) findViewById(R.id.button_cancel);
        shipping_phone = (IntlPhoneInput) findViewById(R.id.shipping_phone);
        shipping_mobile = (IntlPhoneInput) findViewById(R.id.shipping_mobile);

        List<String> check_List = new ArrayList<String>();
        check_List.add("No");
        check_List.add("Yes");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(RequestQuotationActivity.this, android.R.layout.simple_spinner_item, check_List);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_shipping_address.setAdapter(dataAdapter);
        spinner_shipping_name.setAdapter(dataAdapter);
        spinner_item.setAdapter(dataAdapter);
        spinner_unit.setAdapter(dataAdapter);

        button_save.setOnClickListener(this);
        button_submit.setOnClickListener(this);
        button_cancel.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v == button_save) {

        }
        if (v == button_submit) {

        }
        if (v == button_cancel) {

        }
        if (v == shipping_date) {
            final Calendar calendar = Calendar.getInstance();
            int mYear = calendar.get(Calendar.YEAR); // current year
            int mMonth = calendar.get(Calendar.MONTH); // current month
            int mDay = calendar.get(Calendar.DAY_OF_MONTH); // current day
            // date picker dialog
            datePickerDialog = new DatePickerDialog(RequestQuotationActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    // set day of month , month and year value in the edit text
                    shipping_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }
}
