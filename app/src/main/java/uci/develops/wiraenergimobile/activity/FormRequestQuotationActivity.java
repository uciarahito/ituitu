package uci.develops.wiraenergimobile.activity;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.response.ApproveResponse;
import uci.develops.wiraenergimobile.response.CustomerResponse;
import uci.develops.wiraenergimobile.service.RestClient;

public class FormRequestQuotationActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button_shipping_address, button_company_address;
    private ImageView imageView_add_quotation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_request_quotation);

        initializeComponent();
    }

    private void initializeComponent(){
        button_shipping_address = (Button)findViewById(R.id.button_shipping_address);
        button_company_address = (Button)findViewById(R.id.button_company_address);
        imageView_add_quotation = (ImageView)findViewById(R.id.imageView_add_quotation);

        button_shipping_address.setOnClickListener(this);
        button_company_address.setOnClickListener(this);
        imageView_add_quotation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == button_shipping_address){
            showDialogShippingAddress();
        }

        if(v == button_company_address){
            showDialogCompanyAddress();
        }

        if(v == imageView_add_quotation){
            showDialogNewItemQuotation();
        }
    }


    private void showDialogShippingAddress(){
        final Dialog dialog = new Dialog(FormRequestQuotationActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.shipping_address);

        final Button dialog_button_save = (Button)dialog.findViewById(R.id.button_save);
        final Button dialog_button_cancel = (Button)dialog.findViewById(R.id.button_cancel);

        dialog_button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog_button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialog.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.show();
    }

    private void showDialogNewItemQuotation(){
        final Dialog dialog = new Dialog(FormRequestQuotationActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.item_new_quotation);


        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialog.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.show();
    }

    private void showDialogCompanyAddress(){
        final Dialog dialog = new Dialog(FormRequestQuotationActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.company_address);


        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialog.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.show();
    }
}
