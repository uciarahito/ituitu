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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import uci.develops.wiraenergimobile.R;

public class RequestQuotationActivity extends AppCompatActivity implements View.OnClickListener{
    private Spinner spinner_shipping_address, spinner_name, spinner_phone, spinner_mobile, spinner_email, spinner_jabatan, spinner_item, spinner_satuan, spinner_qty;
    private EditText editText_keterangan, editText_tgl_kirim;
    private DatePickerDialog datePickerDialog;
    private Button button_save, button_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_quotation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeComponent();
    }

    private void initializeComponent(){
        spinner_shipping_address = (Spinner)findViewById(R.id.spinner_shipping_address);
        spinner_name = (Spinner)findViewById(R.id.spinner_name);
        spinner_phone = (Spinner)findViewById(R.id.spinner_phone);
        spinner_mobile = (Spinner)findViewById(R.id.spinner_mobile);
        spinner_email = (Spinner)findViewById(R.id.spinner_email);
        spinner_jabatan = (Spinner)findViewById(R.id.spinner_jabatan);
        spinner_item = (Spinner)findViewById(R.id.spinner_item);
        spinner_satuan = (Spinner)findViewById(R.id.spinner_satuan);
        spinner_qty = (Spinner)findViewById(R.id.spinner_qty);
        editText_keterangan = (EditText)findViewById(R.id.editText_keterangan);
        editText_tgl_kirim = (EditText)findViewById(R.id.editText_tgl_kirim);

        button_save = (Button)findViewById(R.id.button_save);
        button_submit = (Button)findViewById(R.id.button_submit);

        List<String> check_List = new ArrayList<String>();
        check_List.add("No");
        check_List.add("Yes");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(RequestQuotationActivity.this, android.R.layout.simple_spinner_item, check_List);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_shipping_address.setAdapter(dataAdapter);
        spinner_name.setAdapter(dataAdapter);
        spinner_phone.setAdapter(dataAdapter);
        spinner_mobile.setAdapter(dataAdapter);
        spinner_email.setAdapter(dataAdapter);
        spinner_jabatan.setAdapter(dataAdapter);
        spinner_item.setAdapter(dataAdapter);
        spinner_satuan.setAdapter(dataAdapter);
        spinner_qty.setAdapter(dataAdapter);

        button_save.setOnClickListener(this);
        button_submit.setOnClickListener(this);
        editText_tgl_kirim.setOnClickListener(this);
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
        if(v == button_save){

        }
        if(v == button_submit){

        }
        if(v == editText_tgl_kirim){
            final Calendar calendar = Calendar.getInstance();
            int mYear = calendar.get(Calendar.YEAR); // current year
            int mMonth = calendar.get(Calendar.MONTH); // current month
            int mDay = calendar.get(Calendar.DAY_OF_MONTH); // current day
            // date picker dialog
            datePickerDialog = new DatePickerDialog(RequestQuotationActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            // set day of month , month and year value in the edit text
                            editText_tgl_kirim.setText(dayOfMonth + "/"+ (monthOfYear + 1) + "/" + year);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }
}
