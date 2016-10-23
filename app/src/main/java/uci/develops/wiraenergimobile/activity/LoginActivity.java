package uci.develops.wiraenergimobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.response.LoginResponse;
import uci.develops.wiraenergimobile.service.RestClient;

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
//                if(login(email, password)){
//                    Intent intent;
//                    if(new SharedPreferenceManager().getPreferences(LoginActivity.this, "role_login").equals("admin")) {
//                        intent = new Intent(LoginActivity.this, DashboardAdminActivity.class);
//                        startActivity(intent);
//                        finish();
//                    } else {
////                        Call<LoginResponse> loginResponseCall = RestClient.getRestClient().Login(email, password);
////                        loginResponseCall.en
//                        intent = new Intent(LoginActivity.this, DashboardCustomerActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                }
                Call<LoginResponse> loginResponseCall = RestClient.getRestClient().Login(email, password);
                loginResponseCall.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            String status = "";
                            String code = "";
                            String info = "";
                            String token = "";
                            boolean activated;
                            String role = "";
                            status = response.body().getStatus();
                            code = response.body().getCode();
                            info = response.body().getInfo();
                            token = response.body().getToken();
                            activated = response.body().isActivated();
                            //Toast.makeText(LoginActivity.this, activated + "  " + token, Toast.LENGTH_SHORT).show();
                            new SharedPreferenceManager().setPreferences(LoginActivity.this, "token", token);
                            if (activated == true){
                                Intent intent = new Intent(LoginActivity.this, DashboardAdminActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, "Lakukan Verifikasi Email!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Email dan Password tidak terdaftar!", Toast.LENGTH_SHORT).show();
                            Log.d("error message", "Error");
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.e("LoginActivity", "Retrofit Error");
                    }
                });
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
