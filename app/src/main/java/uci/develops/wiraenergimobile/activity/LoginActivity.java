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
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.model.RoleModel;
import uci.develops.wiraenergimobile.model.UserModel;
import uci.develops.wiraenergimobile.response.ListRoleResponse;
import uci.develops.wiraenergimobile.response.LoginResponse;
import uci.develops.wiraenergimobile.response.RegisterResponse;
import uci.develops.wiraenergimobile.service.RestClient;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText_login_email, editText_email_password;
    private Button button_login_login, button_login_register;
    private CheckBox checkBox_login;
    private TextView lost_pasword;

    String email = "", password = "", name = "", role = "", registration_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeComponent();
        loadData();
    }

    private void initializeComponent(){
        lost_pasword = (TextView)findViewById(R.id.lost_password);
        checkBox_login = (CheckBox)findViewById(R.id.checkBox_login);
        editText_login_email = (EditText)findViewById(R.id.editText_login_email);
        editText_email_password = (EditText)findViewById(R.id.editText_login_password);
        button_login_login = (Button)findViewById(R.id.button_login_login);
        button_login_register = (Button)findViewById(R.id.button_login_register);

        button_login_login.setOnClickListener(this);
        button_login_register.setOnClickListener(this);
    }

    /**
     * Akses API Login (manual)
     */
//    private boolean login(String email, String password){
//        String[][] data_dummy = {{"admin@email.com", "password", "admin"}, {"customer@email.com", "password", "customer"}};
//        boolean result = false;
//
//        for(int i=0; i<data_dummy.length; i++){
//            if(data_dummy[i][0].equals(email) && data_dummy[i][1].equals(password)){
//                result = true;
//                new SharedPreferenceManager().setPreferences(LoginActivity.this, "email_login", email);
//                new SharedPreferenceManager().setPreferences(LoginActivity.this, "role_login", data_dummy[i][2]);
//            }
//        }
//        return result;
//    }

    @Override
    public void onClick(View v) {
        if(v == button_login_login){
            Constant.role_data.clear();
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

                            status = response.body().getStatus();
                            code = response.body().getCode();
                            info = response.body().getInfo();
                            token = response.body().getToken();
                            final int user_id = response.body().getUser_id();
                            final int active = response.body().getActive();
                            final int approve = response.body().getApprove();
                            activated = response.body().isActivated();
                            customer_decode = response.body().getCustomer_decode();
                            //Toast.makeText(LoginActivity.this, activated + "  " + token, Toast.LENGTH_SHORT).show();
                            new SharedPreferenceManager().setPreferences(LoginActivity.this, "token", token);
                            new SharedPreferenceManager().setPreferences(LoginActivity.this, "customer_decode", customer_decode);
                            new SharedPreferenceManager().setPreferences(LoginActivity.this, "is_login", "true");
                            if (activated == true){
                                Call<ListRoleResponse> listRoleResponseCall = RestClient.getRestClient().getAllRoles("Bearer "+token);
                                listRoleResponseCall.enqueue(new Callback<ListRoleResponse>() {
                                    @Override
                                    public void onResponse(Call<ListRoleResponse> call, Response<ListRoleResponse> response) {
                                        if(response.isSuccessful()){
                                            if(response.body().getData().size() > 0){
                                                for(RoleModel roleModel : response.body().getData()){
                                                    if(roleModel.getUser_id() == user_id){
                                                        if(roleModel.getRole_id() == 4){
                                                            if (active == 1){
                                                                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                                                startActivity(intent);
                                                            } else {
                                                                if (approve == 0){
                                                                    Intent intent = new Intent(LoginActivity.this, VerificationStatusActivity.class);
                                                                    startActivity(intent);
                                                                } else if(approve == 1){
                                                                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                                                    startActivity(intent);
                                                                } else if(approve == 2){
                                                                    Intent intent = new Intent(LoginActivity.this, FormCustomerActivity.class);
                                                                    startActivity(intent);
                                                                } else if(approve == 3){
                                                                    Intent intent = new Intent(LoginActivity.this, WaitingApprovalActivity.class);
                                                                    startActivity(intent);
                                                                }
                                                            }
                                                        }
                                                        if(roleModel.getRole_id() <= 3){
                                                            new SharedPreferenceManager().setPreferences(LoginActivity.this, "role", "admin");
                                                            Intent intent = new Intent(LoginActivity.this, DashboardAdminActivity.class);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ListRoleResponse> call, Throwable t) {

                                    }
                                });
                                //Intent intent = new Intent(LoginActivity.this, DashboardAdminActivity.class);
                                //startActivity(intent);
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

    private void loadData(){
        UserModel userModel = new UserModel();
        editText_login_email.setText(userModel.getEmail()==null ? "" : userModel.getEmail());
        editText_email_password.setText(userModel.getPassword()==null ? "" : userModel.getPassword());
    }

    private String generateUnique_id(){
        Firebase firebase = new Firebase(Constant.FIREBASE_APP);

        //Pushing a new element to firebase it will automatically create a unique id
        Firebase newFirebase = firebase.push();

        //Creating a map to store name value pair
        Map<String, String> val = new HashMap<>();

        //pushing msg = none in the map
        val.put("message", "none");
        val.put("tipe","none");
        val.put("time", "" + new SharedPreferenceManager().getCurrentDateTime());
        //saving the map to firebase
        newFirebase.setValue(val);

        String uniqueId = newFirebase.getKey();

        new SharedPreferenceManager().setPreferences(LoginActivity.this,Constant.UNIQUE_ID,uniqueId);
        startService(new Intent(getBaseContext(), NotificationListener.class));

        return uniqueId;
    }
}
