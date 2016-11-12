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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.fcm.NotificationListener;
import uci.develops.wiraenergimobile.helper.Constant;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.UserModel;
import uci.develops.wiraenergimobile.response.LoginResponse;
import uci.develops.wiraenergimobile.service.RestClient;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText_login_email, editText_email_password;
    private Button button_login_login, button_login_register;
    private CheckBox checkBox_login;
    private TextView lost_pasword, textView_error_email, textView_error_password;

    String email = "", password = "", name = "", role = "", registration_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeComponent();
        loadData();

        isLogin();
    }

    private void isLogin() {
        try {
            if (new SharedPreferenceManager().getPreferences(LoginActivity.this, "is_login").equals("true")) {
                if (new SharedPreferenceManager().getPreferences(LoginActivity.this, "roles").equals("admin")) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                if (new SharedPreferenceManager().getPreferences(LoginActivity.this, "roles").equals("customer")) {
                    Toast.makeText(LoginActivity.this, ""+Integer.parseInt(new SharedPreferenceManager().getPreferences(LoginActivity.this, "approve")), Toast.LENGTH_SHORT).show();
                    if (Integer.parseInt(new SharedPreferenceManager().getPreferences(LoginActivity.this, "approve")) == 2) {
                        Intent intent = new Intent(LoginActivity.this, VerificationStatusActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (Integer.parseInt(new SharedPreferenceManager().getPreferences(LoginActivity.this, "approve")) == 1) {
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    if (Integer.parseInt(new SharedPreferenceManager().getPreferences(LoginActivity.this, "approve")) == 0) {
                        Intent intent = new Intent(LoginActivity.this, WaitingApprovalActivity.class);
                        startActivity(intent);
                        finish();
                    }

//                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                    startActivity(intent);
//                    finish();
                }
                if (new SharedPreferenceManager().getPreferences(LoginActivity.this, "roles").equals("expedition")) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        } catch (Exception e) {

        }
    }

    private void initializeComponent() {
        lost_pasword = (TextView) findViewById(R.id.lost_password);
        textView_error_email = (TextView) findViewById(R.id.textView_error_email);
        textView_error_password = (TextView) findViewById(R.id.textView_error_password);
        checkBox_login = (CheckBox) findViewById(R.id.checkBox_login);
        editText_login_email = (EditText) findViewById(R.id.editText_login_email);
        editText_email_password = (EditText) findViewById(R.id.editText_login_password);
        button_login_login = (Button) findViewById(R.id.button_login_login);
        button_login_register = (Button) findViewById(R.id.button_login_register);

        button_login_login.setOnClickListener(this);
        button_login_register.setOnClickListener(this);
        lost_pasword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == button_login_login) {
            Constant.role_data.clear();
            email = editText_login_email.getText().toString();
            password = editText_email_password.getText().toString();
            boolean empty_email = false, empty_password = false;

            if (!email.contains("@") || email.equals("")) {
                textView_error_email.setVisibility(View.VISIBLE);
                empty_email = true;
            } else {
                textView_error_email.setVisibility(View.GONE);
                empty_email = false;
            }
            if (password.equals("")) {
                textView_error_password.setVisibility(View.VISIBLE);
                empty_password = true;
            } else {
                textView_error_password.setVisibility(View.GONE);
                empty_password = false;
            }

//            if (!email.equals("") && !password.equals("")) {

            if (!empty_email && !empty_password) {

                registration_key = generateUnique_id();
                Call<LoginResponse> loginResponseCall = RestClient.getRestClient().Login(email, password, registration_key);
                loginResponseCall.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            String status = "";
                            String code = "";
                            String info = "";
                            String token = "";
                            boolean activated;
                            String customer_decode = "";
                            List<Integer> roles = new ArrayList<Integer>();
                            roles = response.body().getRoles();

                            status = response.body().getStatus();
                            code = response.body().getCode();
                            info = response.body().getInfo();
                            token = response.body().getToken();
                            final int user_id = response.body().getUser_id();
                            final String approve = response.body().getApprove();
                            activated = response.body().isActivated();
                            customer_decode = response.body().getCustomer_decode();
                            //Toast.makeText(LoginActivity.this, activated + "  " + token, Toast.LENGTH_SHORT).show();
                            new SharedPreferenceManager().setPreferences(LoginActivity.this, "token", token);
                            new SharedPreferenceManager().setPreferences(LoginActivity.this, "approve", approve);
                            new SharedPreferenceManager().setPreferences(LoginActivity.this, "user_id", ""+user_id);
                            new SharedPreferenceManager().setPreferences(LoginActivity.this, "customer_decode", customer_decode);
                            new SharedPreferenceManager().setPreferences(LoginActivity.this, "is_login", "true");
                            if (activated == true) {
                                for (Integer ind : roles) {

//                                    Intent intent = new Intent(LoginActivity.this, DashboardCustomerActivity.class);
//                                    startActivity(intent);
//                                    finish();

                                    if (ind == 2) {
                                        new SharedPreferenceManager().setPreferences(LoginActivity.this, "roles", "admin");
                                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                    if (ind == 3) {
                                        new SharedPreferenceManager().setPreferences(LoginActivity.this, "roles", "customer");
                                        if (Integer.parseInt(new SharedPreferenceManager().getPreferences(LoginActivity.this, "approve")) == 2) {
                                            Intent intent = new Intent(LoginActivity.this, VerificationStatusActivity.class);
                                            startActivity(intent);
                                            finish();
                                        } else if (Integer.parseInt(new SharedPreferenceManager().getPreferences(LoginActivity.this, "approve")) == 1) {
                                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                        if (Integer.parseInt(new SharedPreferenceManager().getPreferences(LoginActivity.this, "approve")) == 0) {
                                            Intent intent = new Intent(LoginActivity.this, WaitingApprovalActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }

                                    if (ind == 4) {
                                        new SharedPreferenceManager().setPreferences(LoginActivity.this, "roles", "expedition");
                                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
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

        if (v == button_login_register) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        }

        if (v == lost_pasword) {
            Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void loadData() {
        UserModel userModel = new UserModel();
        editText_login_email.setText(userModel.getEmail() == null ? "" : userModel.getEmail());
        editText_email_password.setText(userModel.getPassword() == null ? "" : userModel.getPassword());
    }

    private String generateUnique_id() {
        Firebase firebase = new Firebase(Constant.FIREBASE_APP);

        //Pushing a new element to firebase it will automatically create a unique id
        Firebase newFirebase = firebase.push();

        //Creating a map to store name value pair
        Map<String, String> val = new HashMap<>();

        //pushing msg = none in the map
        val.put("message", "none");
        val.put("tipe", "none");
        val.put("time", "" + new SharedPreferenceManager().getCurrentDateTime());
        //saving the map to firebase
        newFirebase.setValue(val);

        String uniqueId = newFirebase.getKey();

        new SharedPreferenceManager().setPreferences(LoginActivity.this, Constant.UNIQUE_ID, uniqueId);
        startService(new Intent(getBaseContext(), NotificationListener.class));

        return uniqueId;
    }

    private void getMaxMin(){
        List<Pelajaran> list = new ArrayList<>();
        list.add(new Pelajaran("a", "x", 100));
        list.add(new Pelajaran("a", "x", 80));
        list.add(new Pelajaran("a", "x", 20));
        list.add(new Pelajaran("a", "z", 80));
        list.add(new Pelajaran("a", "z", 90));
        list.add(new Pelajaran("a", "z", 100));
    }

    public class Pelajaran {
        String nama;
        String pelajaran;
        int nilai;
        public Pelajaran(String nama, String pelajaran, int nilai){
            this.nama = nama;
            this.pelajaran = pelajaran;
            this.nilai = nilai;
        }

        public int getMax(String pelajaran, List<Pelajaran> pelajaranList){
            int max = 0;
            for(Pelajaran pelajaran1 : pelajaranList){
                if(max <= pelajaran1.nilai){
                    max = pelajaran1.nilai;
                }
            }
            return max;
        }
        public int getMin(String pelajaran, List<Pelajaran> pelajaranList){
            int min = pelajaranList.get(0).nilai;
            for(Pelajaran pelajaran1 : pelajaranList){
                if(min >= pelajaran1.nilai){
                    min = pelajaran1.nilai;
                }
            }
            return min;
        }
        public List<Pelajaran> getUniquePelajaran(List<Pelajaran> pelajaranList){
            List<Pelajaran> pelajaranUn = new ArrayList<>();
            pelajaranUn.add(pelajaranList.get(i))
            return pelajaranUn;
        }
    }
}