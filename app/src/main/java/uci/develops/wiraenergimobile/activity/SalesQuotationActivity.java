package uci.develops.wiraenergimobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import uci.develops.wiraenergimobile.R;

public class SalesQuotationActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout linearLayout_menu_all_quotation, linearLayout_menu_new_quotation;
    private TextView textView_label_all_quotation, textView_label_new_quotation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_quotation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeComponent();
    }

    private void initializeComponent(){
        linearLayout_menu_all_quotation = (LinearLayout) findViewById(R.id.linearLayout_menu_all_customer);
        linearLayout_menu_new_quotation = (LinearLayout) findViewById(R.id.linearLayout_menu_new_customer);
        textView_label_all_quotation = (TextView) findViewById(R.id.textView_label_all_customer);
        textView_label_new_quotation = (TextView) findViewById(R.id.textView_label_new_customer);

        linearLayout_menu_all_quotation.setOnClickListener(this);
        linearLayout_menu_new_quotation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if(v == linearLayout_menu_all_quotation){
            intent = new Intent(SalesQuotationActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        if(v == linearLayout_menu_new_quotation){
            intent = new Intent(SalesQuotationActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

}
