package uci.develops.wiraenergimobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import uci.develops.wiraenergimobile.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button_main_test_login, button_main_test_register, button_main_test_form_customer, button_main_test_map_coordinate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeComponent();
    }

    private void initializeComponent(){
        button_main_test_login = (Button)findViewById(R.id.button_main_test_login);
        button_main_test_register = (Button)findViewById(R.id.button_main_test_register);
        button_main_test_form_customer = (Button)findViewById(R.id.button_main_test_form_customer);
        button_main_test_map_coordinate = (Button)findViewById(R.id.button_main_test_map_coordinate);

        button_main_test_login.setOnClickListener(this);
        button_main_test_register.setOnClickListener(this);
        button_main_test_form_customer.setOnClickListener(this);
        button_main_test_map_coordinate.setOnClickListener(this);
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
        if(v == button_main_test_login){
            intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        if(v == button_main_test_register){
            intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        }
        if(v == button_main_test_form_customer){
            intent = new Intent(MainActivity.this, FormCustomerActivity.class);
            startActivity(intent);
            finish();
        }
        if(v == button_main_test_map_coordinate){
            intent = new Intent(MainActivity.this, MapsCoordinateActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
