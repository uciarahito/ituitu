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

import uci.develops.wiraenergimobile.R;

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
    private void login(){
    }

    @Override
    public void onClick(View v) {
        if(v == button_login_login){
            login();
        }

        if(v == button_login_register){
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
