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
import android.widget.Spinner;

import uci.develops.wiraenergimobile.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText_register_name, editText_register_email, editText_register_password;
    private Button button_register_register, button_register_login;
    private Spinner spinner_register_role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeComponent();
    }

    private void initializeComponent(){
        editText_register_name = (EditText)findViewById(R.id.editText_register_name);
        editText_register_email = (EditText)findViewById(R.id.editText_register_email);
        editText_register_password = (EditText)findViewById(R.id.editText_register_password);
        spinner_register_role = (Spinner)findViewById(R.id.spinner_register_role);
        button_register_login = (Button)findViewById(R.id.button_register_login);
        button_register_register = (Button)findViewById(R.id.button_register_register);

        button_register_login.setOnClickListener(this);
        button_register_register.setOnClickListener(this);
    }

    private void register(){

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if(v == button_register_login){
            intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        if(v == button_register_register){
        }
    }
}
