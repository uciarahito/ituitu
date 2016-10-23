package uci.develops.wiraenergimobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;

public class DashboardAdminActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button_list_customer, button_list_request;
    private TextView textView_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeComponent();
    }

    private void initializeComponent(){
        button_list_customer = (Button)findViewById(R.id.button_list_customer);
        button_list_request = (Button)findViewById(R.id.button_list_request);
        textView_welcome = (TextView)findViewById(R.id.textView_dashboard_welcome);

        button_list_customer.setOnClickListener(this);
        button_list_request.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        if(v == button_list_customer){
            intent = new Intent(DashboardAdminActivity.this, ListCustomerActivity.class);
            startActivity(intent);
        }
        if(v == button_list_request){
            intent = new Intent(DashboardAdminActivity.this, ListRequestCustomerActivity.class);
            startActivity(intent);
        }
    }
}
