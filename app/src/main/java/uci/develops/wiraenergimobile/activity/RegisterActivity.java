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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText_register_name, editText_register_email, editText_register_password, editText_register_password_confirmation;
    private Button button_register_register;
    private TextView textView_login, textView_error_full_name, textView_error_email, textView_error_password, textView_error_confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeComponent();
    }

    private void initializeComponent() {
        textView_login = (TextView) findViewById(R.id.textView_login);
        editText_register_name = (EditText) findViewById(R.id.editText_register_name);
        editText_register_email = (EditText) findViewById(R.id.editText_register_email);
        editText_register_password = (EditText) findViewById(R.id.editText_register_password);
        editText_register_password_confirmation = (EditText) findViewById(R.id.editText_register_password_confirmation);
        textView_error_full_name = (TextView) findViewById(R.id.textView_error_full_name);
        textView_error_email = (TextView) findViewById(R.id.textView_error_email);
        textView_error_password = (TextView) findViewById(R.id.textView_error_password);
        textView_error_confirm_password = (TextView) findViewById(R.id.textView_error_confirm_password);
        button_register_register = (Button) findViewById(R.id.button_register_register);

        textView_login.setOnClickListener(this);
        button_register_register.setOnClickListener(this);
    }

    private void register() {

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v == button_register_register) {
            String name = "", email = "", password = "", confirm_password = "";
            name = editText_register_name.getText().toString();
            email = editText_register_email.getText().toString();
            password = editText_register_password.getText().toString();
            boolean empty_email = false, empty_name = false, empty_password = false, empty_confirm_password = false, not_password_match = false;

            confirm_password = editText_register_password_confirmation.getText().toString();

            if (!email.contains("@") || email.equals("")) {
                textView_error_email.setVisibility(View.VISIBLE);
                empty_email = true;
            } else {
                textView_error_email.setVisibility(View.GONE);
                empty_email = false;
            }
            if (name.equals("")) {
                textView_error_full_name.setVisibility(View.VISIBLE);
                empty_name = true;
            } else {
                textView_error_full_name.setVisibility(View.GONE);
                empty_name = false;
            }
            if (password.equals("")) {
                textView_error_password.setVisibility(View.VISIBLE);
                empty_password = true;
            } else {
                textView_error_password.setVisibility(View.GONE);
                empty_password = false;
            }
            if (confirm_password.equals("")) {
                textView_error_confirm_password.setVisibility(View.VISIBLE);
                empty_confirm_password = true;
            } else {
                textView_error_confirm_password.setVisibility(View.GONE);
                empty_confirm_password = false;
                if (!confirm_password.equals(password)) {
                    textView_error_confirm_password.setText("Confirm password tidak sama");
                    textView_error_confirm_password.setVisibility(View.VISIBLE);
                    not_password_match = true;
                } else {
                    textView_error_password.setVisibility(View.GONE);
                    textView_error_confirm_password.setVisibility(View.GONE);
                    not_password_match = false;
                }
            }
            if (!empty_name && !empty_email && !empty_password && !empty_confirm_password && !not_password_match) {
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
        if (v == textView_login) {
            intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
