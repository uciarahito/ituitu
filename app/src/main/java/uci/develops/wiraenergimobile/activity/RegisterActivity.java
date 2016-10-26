package uci.develops.wiraenergimobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.helper.Constant;
import uci.develops.wiraenergimobile.response.RegisterResponse;
import uci.develops.wiraenergimobile.service.RestClient;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText_register_name, editText_register_email, editText_register_password;
    private Button button_register_register;
    private Spinner spinner_register_role;
    private TextView textView_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeComponent();
    }

    private void initializeComponent(){
        textView_login = (TextView)findViewById(R.id.textView_login);
        editText_register_name = (EditText)findViewById(R.id.editText_register_name);
        editText_register_email = (EditText)findViewById(R.id.editText_register_email);
        editText_register_password = (EditText)findViewById(R.id.editText_register_password);
        spinner_register_role = (Spinner)findViewById(R.id.spinner_register_role);
        button_register_register = (Button)findViewById(R.id.button_register_register);

        List<String> roles = new ArrayList<String>();
        roles.add("Admin");
        roles.add("Customer");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, roles);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_register_role.setAdapter(dataAdapter);

        textView_login.setOnClickListener(this);
        button_register_register.setOnClickListener(this);
    }

    private void register(){

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if(v == button_register_register){
            String name="", email="", password="", role="";
            name = editText_register_name.getText().toString();
            email = editText_register_email.getText().toString();
            password = editText_register_password.getText().toString();
            role = spinner_register_role.getSelectedItem().toString();

            //fitur utk lihat semua user
//            if(!name.equals("") && !email.equals("") && !password.equals("")) {
//                List<String> user_data = new ArrayList<>();
//                user_data.add(name);
//                user_data.add(email);
//                user_data.add(password);
//                user_data.add(role);
//                Constant.user_data.add(user_data);
//                intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }

            if(!name.equals("") && !email.equals("") && !password.equals("")){
                Call<RegisterResponse> registerResponseCall = RestClient.getRestClient().Register(name, email, password);
                registerResponseCall.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                        if (response.isSuccessful()) {
                            String info = "";
                            info = response.body().getInfo();
                            Toast.makeText(RegisterActivity.this, info, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Log.d("error message", "Error");
                            Toast.makeText(RegisterActivity.this, "Data yang anda isi tidak valid!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                        Log.e("RegisterActivity", t.getMessage());
                    }
                });
            }
        }
        if(v == textView_login){
            intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
