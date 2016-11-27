package uci.develops.wiraenergimobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.response.CustomerResponse;
import uci.develops.wiraenergimobile.service.RestClient;

public class WaitingApprovalActivity extends AppCompatActivity implements View.OnClickListener{
    private Button button_view_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_approval);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeComponent();
    }

    private void initializeComponent(){
        button_view_data = (Button)findViewById(R.id.button_view_data);

        button_view_data.setOnClickListener(this);

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
        if (v == button_view_data){
            Call<CustomerResponse> customerReponseCall = RestClient.getRestClient().getCustomer("Bearer "+new SharedPreferenceManager().getPreferences(WaitingApprovalActivity.this, "token"),
                    new SharedPreferenceManager().getPreferences(WaitingApprovalActivity.this, "customer_decode"));
            customerReponseCall.enqueue(new Callback<CustomerResponse>() {
                @Override
                public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                    if (response.isSuccessful()) {
                        new SharedPreferenceManager().setPreferences(WaitingApprovalActivity.this, "approve", ""+response.body().getData().get(0).getApprove());
                        startActivity(new Intent(WaitingApprovalActivity.this, FormCustomerActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(WaitingApprovalActivity.this, LoginActivity.class));
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<CustomerResponse> call, Throwable t) {
                    startActivity(new Intent(WaitingApprovalActivity.this, LoginActivity.class));
                    finish();
                }
            });
        }
    }

}
