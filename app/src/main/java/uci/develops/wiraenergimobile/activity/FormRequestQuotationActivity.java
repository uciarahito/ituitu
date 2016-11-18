package uci.develops.wiraenergimobile.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerAddressModel;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.response.CustomerAddressResponse;
import uci.develops.wiraenergimobile.response.CustomerResponse;
import uci.develops.wiraenergimobile.service.RestClient;
import android.widget.AdapterView.OnItemSelectedListener;

public class FormRequestQuotationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_shipping_address, button_company_address, button_submit, button_save, button_cancel;
    private ImageView imageView_add_quotation;
    private TextView textView_choose, textView_customer_code, textView_customer_group, textView_total_qty;
    CustomerModel customerModel;
    private String decode = "", token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_request_quotation);

        initializeComponent();
    }

    private void initializeComponent() {
        button_shipping_address = (Button) findViewById(R.id.button_shipping_address);
        button_company_address = (Button) findViewById(R.id.button_company_address);
        button_submit = (Button) findViewById(R.id.button_submit);
        button_save = (Button) findViewById(R.id.button_save);
        button_cancel = (Button) findViewById(R.id.button_cancel);
        imageView_add_quotation = (ImageView) findViewById(R.id.imageView_add_quotation);
        textView_choose = (TextView) findViewById(R.id.textView_choose);
        textView_customer_code = (TextView) findViewById(R.id.textView_customer_code);
        textView_customer_group = (TextView) findViewById(R.id.textView_customer_group);
        textView_total_qty = (TextView) findViewById(R.id.textView_total_qty);

        button_shipping_address.setOnClickListener(this);
        button_company_address.setOnClickListener(this);
        button_submit.setOnClickListener(this);
        button_save.setOnClickListener(this);
        button_cancel.setOnClickListener(this);
        imageView_add_quotation.setOnClickListener(this);
        textView_choose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Call<CustomerResponse> customerResponseCall = RestClient.getRestClient().getCustomer("Bearer "+new SharedPreferenceManager().getPreferences(FormRequestQuotationActivity.this, "token"),
                new SharedPreferenceManager().getPreferences(FormRequestQuotationActivity.this, "customer_decode"));
        customerResponseCall.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if(response.isSuccessful()){
                    if (response.body().getData().size() > 0) {
                        CustomerModel customerModel = response.body().getData().get(0);
                        Log.e("qqqqq", "aaaaaaaaaaaaaaaaaa"+customerModel.getCode());
                        textView_customer_code.setText(""+(customerModel.getCode()));
                        textView_customer_group.setText(""+(customerModel.getGroup()));
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {

            }
        });


        if (v == textView_choose) {
            showDialogShippingAddress();
        }

        if (v == button_shipping_address) {
            showDialogShippingAddress();
        }

        if (v == button_company_address) {
            showDialogCompanyAddress();
        }

        if (v == imageView_add_quotation) {
            showDialogNewItemQuotation();
        }

        if (v == button_submit) {
            Intent intent = new Intent(FormRequestQuotationActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        if (v == button_save) {
            Intent intent = new Intent(FormRequestQuotationActivity.this, HomeActivity.class);
            startActivity(intent);
        }

        if (v == button_cancel) {
            Intent intent = new Intent(FormRequestQuotationActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }

    // utk nampilkan shipping address
    private Spinner spinner_shipping_address_name;
    private Button dialog_button_cancel, dialog_button_save;
    private EditText editText_shipping_address, editText_shipping_PIC, editText_shipping_phone, editText_shipping_mobile;

    private void showDialogShippingAddress() {
        final Dialog dialogShippingAddress = new Dialog(FormRequestQuotationActivity.this);
        dialogShippingAddress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogShippingAddress.setContentView(R.layout.shipping_address);

        spinner_shipping_address_name = (Spinner) dialogShippingAddress.findViewById(R.id.spinner_shipping_address_name);
        editText_shipping_address = (EditText) dialogShippingAddress.findViewById(R.id.editText_shipping_address);
        editText_shipping_PIC = (EditText) dialogShippingAddress.findViewById(R.id.editText_shipping_PIC);
        editText_shipping_phone = (EditText) dialogShippingAddress.findViewById(R.id.editText_shipping_phone);
        editText_shipping_mobile = (EditText) dialogShippingAddress.findViewById(R.id.editText_shipping_mobile);
        dialog_button_save = (Button) dialogShippingAddress.findViewById(R.id.button_save);
        dialog_button_cancel = (Button) dialogShippingAddress.findViewById(R.id.button_cancel);

        Call<CustomerAddressResponse> customerAddressResponseCall = RestClient.getRestClient().getCustomerAddress("Bearer "
                + new SharedPreferenceManager().getPreferences(FormRequestQuotationActivity.this, "token"),
                new SharedPreferenceManager().getPreferences(FormRequestQuotationActivity.this, "customer_decode"));
        customerAddressResponseCall.enqueue(new Callback<CustomerAddressResponse>() {
            @Override
            public void onResponse(Call<CustomerAddressResponse> call, Response<CustomerAddressResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getData().size() > 0) {
                        String check_List [] = new String[response.body().getData().size()];
                        int index=0;
                        for(CustomerAddressModel customerAddressModel : response.body().getData()){
                            check_List[index] = customerAddressModel.getName();
                            index++;
                        }

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(FormRequestQuotationActivity.this,
                                android.R.layout.simple_spinner_item, check_List);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_shipping_address_name.setAdapter(dataAdapter);

                        spinner_shipping_address_name.setOnItemSelectedListener(new OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                                Call<CustomerAddressResponse> customerAddressByIdResponseCall = RestClient.getRestClient().getCustomerAddress("Bearer "
                                                + new SharedPreferenceManager().getPreferences(FormRequestQuotationActivity.this, "token"),
                                        new SharedPreferenceManager().getPreferences(FormRequestQuotationActivity.this, "customer_decode"));
                                customerAddressByIdResponseCall.enqueue(new Callback<CustomerAddressResponse>() {
                                    @Override
                                    public void onResponse(Call<CustomerAddressResponse> call, Response<CustomerAddressResponse> response) {
                                        if (response.isSuccessful()) {
//                                            if (response.body().getData().size() > 0) {
                                            CustomerAddressModel customerAddressModel = (CustomerAddressModel) spinner_shipping_address_name.getItemAtPosition(position);
//                                            CustomerAddressModel customerAddressModel = (CustomerAddressModel) spinner_shipping_address_name.getSelectedItem().toString();

                                                editText_shipping_address.setText(customerAddressModel.getAddress() == null ? "" : customerAddressModel.getAddress());
                                                editText_shipping_PIC.setText(customerAddressModel.getPic() == null ? "" : customerAddressModel.getPic());
                                                editText_shipping_phone.setText(customerAddressModel.getPhone() == null ? "" : customerAddressModel.getPhone());
                                                editText_shipping_mobile.setText(customerAddressModel.getMobile() == null ? "" : customerAddressModel.getMobile());
//                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<CustomerAddressResponse> call, Throwable t) {

                                    }
                                });
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomerAddressResponse> call, Throwable t) {

            }
        });

        dialog_button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogShippingAddress.dismiss();

            }
        });

        dialog_button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogShippingAddress.dismiss();
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialogShippingAddress.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialogShippingAddress.setCancelable(true);
        dialogShippingAddress.show();
    }

    // utk nampilkan company address
    private CheckBox checkbox1, checkbox2, checkbox3;
    private TextView textView_name_pic1, textView_position1, textView_phone1, textView_mobile1, textView_email1,
            textView_name_pic2, textView_position2, textView_phone2, textView_mobile2, textView_email2,
            textView_name_pic3, textView_position3, textView_phone3, textView_mobile3, textView_email3,
            textView_company_address;

    private void showDialogCompanyAddress() {
        final Dialog dialogCompanyAddress = new Dialog(FormRequestQuotationActivity.this);
        dialogCompanyAddress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogCompanyAddress.setContentView(R.layout.company_address);

        textView_company_address = (TextView) dialogCompanyAddress.findViewById(R.id.textView_company_address);
        textView_name_pic1 = (TextView) dialogCompanyAddress.findViewById(R.id.textView_name_pic1);
        textView_position1 = (TextView) dialogCompanyAddress.findViewById(R.id.textView_position1);
        textView_phone1 = (TextView) dialogCompanyAddress.findViewById(R.id.textView_phone1);
        textView_mobile1 = (TextView) dialogCompanyAddress.findViewById(R.id.textView_mobile1);
        textView_email1 = (TextView) dialogCompanyAddress.findViewById(R.id.textView_email1);
        textView_name_pic2 = (TextView) dialogCompanyAddress.findViewById(R.id.textView_name_pic2);
        textView_position2 = (TextView) dialogCompanyAddress.findViewById(R.id.textView_position2);
        textView_phone2 = (TextView) dialogCompanyAddress.findViewById(R.id.textView_phone2);
        textView_mobile2 = (TextView) dialogCompanyAddress.findViewById(R.id.textView_mobile2);
        textView_email2 = (TextView) dialogCompanyAddress.findViewById(R.id.textView_email2);
        textView_name_pic3 = (TextView) dialogCompanyAddress.findViewById(R.id.textView_name_pic3);
        textView_position3 = (TextView) dialogCompanyAddress.findViewById(R.id.textView_position3);
        textView_phone3 = (TextView) dialogCompanyAddress.findViewById(R.id.textView_phone3);
        textView_mobile3 = (TextView) dialogCompanyAddress.findViewById(R.id.textView_mobile3);
        textView_email3 = (TextView) dialogCompanyAddress.findViewById(R.id.textView_email3);
        dialog_button_cancel = (Button) dialogCompanyAddress.findViewById(R.id.button_cancel);
        dialog_button_save = (Button) dialogCompanyAddress.findViewById(R.id.button_save);
        checkbox1 = (CheckBox) dialogCompanyAddress.findViewById(R.id.checkbox1);
        checkbox2 = (CheckBox) dialogCompanyAddress.findViewById(R.id.checkbox2);
        checkbox3 = (CheckBox) dialogCompanyAddress.findViewById(R.id.checkbox3);

        Call<CustomerResponse> customerResponseCall = RestClient.getRestClient().getCustomer("Bearer " + token, decode);
        customerResponseCall.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if(response.isSuccessful()){
                    CustomerModel customerModel = response.body().getData().get(0);
                    textView_company_address.setText(customerModel.getAddress() == null ? "" : customerModel.getAddress());
                    textView_name_pic1.setText(customerModel.getName1() == null ? "" : customerModel.getFirst_name()+" "+customerModel.getLast_name());
                    textView_name_pic2.setText(customerModel.getName2() == null ? "" : customerModel.getName2());
                    textView_name_pic3.setText(customerModel.getName3() == null ? "" : customerModel.getName3());
                    textView_phone1.setText(customerModel.getPhone1() == null ? "" : customerModel.getPhone1());
                    textView_phone2.setText(customerModel.getPhone2() == null ? "" : customerModel.getPhone2());
                    textView_phone3.setText(customerModel.getPhone3() == null ? "" : customerModel.getPhone3());
                    textView_mobile1.setText(customerModel.getMobile1() == null ? "" : customerModel.getMobile1());
                    textView_mobile2.setText(customerModel.getMobile2() == null ? "" : customerModel.getMobile2());
                    textView_mobile3.setText(customerModel.getMobile3() == null ? "" : customerModel.getMobile3());
                    textView_email1.setText(customerModel.getEmail() == null ? "" : customerModel.getEmail());
                    textView_email2.setText(customerModel.getEmail2() == null ? "" : customerModel.getEmail2());
                    textView_email3.setText(customerModel.getEmail3() == null ? "" : customerModel.getEmail3());
                    textView_position1.setText(customerModel.getJabatan1() == null ? "" : customerModel.getJabatan1());
                    textView_position2.setText(customerModel.getJabatan2() == null ? "" : customerModel.getJabatan2());
                    textView_position3.setText(customerModel.getJabatan3() == null ? "" : customerModel.getJabatan3());
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                Intent intent = new Intent(FormRequestQuotationActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        dialog_button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCompanyAddress.dismiss();
            }
        });

        dialog_button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCompanyAddress.dismiss();
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialogCompanyAddress.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialogCompanyAddress.setCancelable(true);
        dialogCompanyAddress.show();
    }

    // utk nampilkan item quotation
    private Spinner spinner_item, spinner_unit;
    private EditText editText_send_date, editText_quantity, editText_notes;
    private DatePickerDialog datePickerDialog;
    String qty = "", note = "";

    private void showDialogNewItemQuotation() {
        final Dialog dialogItemQuotation = new Dialog(FormRequestQuotationActivity.this);
        dialogItemQuotation.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogItemQuotation.setContentView(R.layout.item_new_quotation);

        spinner_item = (Spinner) dialogItemQuotation.findViewById(R.id.spinner_item);
        spinner_unit = (Spinner) dialogItemQuotation.findViewById(R.id.spinner_unit);
        editText_send_date = (EditText) dialogItemQuotation.findViewById(R.id.editText_send_date);
        editText_quantity = (EditText) dialogItemQuotation.findViewById(R.id.editText_quantity);
        editText_notes = (EditText) dialogItemQuotation.findViewById(R.id.editText_notes);
        dialog_button_save = (Button) dialogItemQuotation.findViewById(R.id.button_save);
        dialog_button_cancel = (Button) dialogItemQuotation.findViewById(R.id.button_cancel);

        List<String> listItem = new ArrayList<String>();
        listItem.add("PCS");
        listItem.add("Militer");
        listItem.add("Liter");
        listItem.add("Barrel");
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(FormRequestQuotationActivity.this,
                android.R.layout.simple_spinner_item, listItem);
        listAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_item.setAdapter(listAdapter);

        List<String> listUnit = new ArrayList<String>();
        listUnit.add("Kertas A4 Putih");
        listUnit.add("Tinta Epson");
        listUnit.add("Laptop Asus");
        listUnit.add("Solar");
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<String>(FormRequestQuotationActivity.this,
                android.R.layout.simple_spinner_item, listUnit);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_unit.setAdapter(unitAdapter);

        //utk send date
        final Calendar calendar = Calendar.getInstance();
        int mYear = calendar.get(Calendar.YEAR); // current year
        int mMonth = calendar.get(Calendar.MONTH); // current month
        int mDay = calendar.get(Calendar.DAY_OF_MONTH); // current day
        // date picker dialog
        datePickerDialog = new DatePickerDialog(FormRequestQuotationActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // set day of month , month and year value in the edit text
                editText_send_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();

        dialog_button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogItemQuotation.dismiss();
            }
        });

        dialog_button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogItemQuotation.dismiss();
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialogItemQuotation.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialogItemQuotation.setCancelable(true);
        dialogItemQuotation.show();
    }


}
