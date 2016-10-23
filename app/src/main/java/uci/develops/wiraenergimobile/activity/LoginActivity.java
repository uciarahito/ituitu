package uci.develops.wiraenergimobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText_login_email, editText_email_password;
    private Button button_login_login, button_login_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeComponent();
    }

    private void initializeComponent(){
        editText_login_email = (EditText)findViewById(R.id.editText_login_email);
        editText_email_password = (EditText)findViewById(R.id.editText_login_password);
        button_login_login = (Button)findViewById(R.id.button_login_login);
        button_login_register = (Button)findViewById(R.id.button_login_register);

        button_login_login.setOnClickListener(this);
        button_login_register.setOnClickListener(this);
    }

    /**
     * Akses API Login
     */
    private boolean login(String email, String password){
        String[][] data_dummy = {{"admin@email.com", "password", "admin"}, {"customer@email.com", "password", "customer"}};
        boolean result = false;

        for(int i=0; i<data_dummy.length; i++){
            if(data_dummy[i][0].equals(email) && data_dummy[i][1].equals(password)){
                result = true;
                new SharedPreferenceManager().setPreferences(LoginActivity.this, "email_login", email);
                new SharedPreferenceManager().setPreferences(LoginActivity.this, "role_login", data_dummy[i][2]);
            }
        }
        return result;
    }

    @Override
    public void onClick(View v) {
        if(v == button_login_login){
            String email = "", password = "";
            email = editText_login_email.getText().toString();
            password = editText_email_password.getText().toString();
            if(!email.equals("") && !password.equals("")) {
                if(login(email, password)){
                    Intent intent;
                    if(new SharedPreferenceManager().getPreferences(LoginActivity.this, "role_login").equals("admin")) {
                        intent = new Intent(LoginActivity.this, DashboardAdminActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        intent = new Intent(LoginActivity.this, DashboardCustomerActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Email atau password anda salah", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LoginActivity.this, "Field tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }
        }

        if(v == button_login_register){
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
