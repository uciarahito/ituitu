package uci.develops.wiraenergimobile.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.editText_login_email)
    EditText editText_login_email;
    @BindView(R.id.editText_login_password)
    EditText editText_login_password;
    @BindView(R.id.lost_password)
    TextView lost_password;
    @BindView(R.id.textView_error_email)
    TextView textView_error_email;
    @BindView(R.id.textView_email_empty)
    TextView textView_email_empty;
    @BindView(R.id.textView_error_password)
    TextView textView_error_password;
    @BindView(R.id.checkBox_login)
    CheckBox checkBox_login;
    @BindView(R.id.button_login_login)
    Button button_login_login;
    @BindView(R.id.button_login_register)
    Button button_login_register;

    String email = "", password = "", name = "", role = "", registration_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        initializeComponent();
        loadData();

//        try {
//            JSONObject obj = new JSONObject(loadJSONFromAsset());
//            JSONArray m_jArry = obj.getJSONArray("formules");
//            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
//            HashMap<String, String> m_li;
//
//            for (int i = 0; i < m_jArry.length(); i++) {
//                JSONObject jo_inside = m_jArry.getJSONObject(i);
//                Log.d("Details-->", jo_inside.getString("formule"));
//                String formula_value = jo_inside.getString("formule");
//                String url_value = jo_inside.getString("url");
//
//                //Add your values in your `ArrayList` as below:
//                m_li = new HashMap<String, String>();
//                m_li.put("formule", formula_value);
//                m_li.put("url", url_value);
//
//                formList.add(m_li);
//            }
//
//            Log.e("JSON", ""+loadJSONFromAsset());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        isLogin();

        //jsonya udah tampil ban, tinggal konversinya
        // method yang di atas masih error

    }

//    public String loadJSONFromAsset() {
//        String json = null;
//        try {
//            InputStream is = getAssets().open("city.json");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            json = new String(buffer, "UTF-8");
//        } catch (IOException ex) {
//            Log.e("Exeption", "exception");
//            ex.printStackTrace();
//            return null;
//        }
//        Toast.makeText(this, ""+json, Toast.LENGTH_SHORT).show();
//        return json;
//    }

    private void isLogin() {
        try {
            if (new SharedPreferenceManager().getPreferences(LoginActivity.this, "is_login").equals("true")) {
                if (new SharedPreferenceManager().getPreferences(LoginActivity.this, "roles").equals("admin")) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                if (new SharedPreferenceManager().getPreferences(LoginActivity.this, "roles").equals("")) {
                    if (Integer.parseInt(new SharedPreferenceManager().getPreferences(LoginActivity.this, "approve")) == 2) {
                        Intent intent = new Intent(LoginActivity.this, VerificationStatusActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (Integer.parseInt(new SharedPreferenceManager().getPreferences(LoginActivity.this, "approve")) == 0) {
                        Intent intent = new Intent(LoginActivity.this, WaitingApprovalActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                if (new SharedPreferenceManager().getPreferences(LoginActivity.this, "roles").equals("customer")) {
                    Toast.makeText(LoginActivity.this, "" + Integer.parseInt(new SharedPreferenceManager().getPreferences(LoginActivity.this, "approve")), Toast.LENGTH_SHORT).show();
                    if (Integer.parseInt(new SharedPreferenceManager().getPreferences(LoginActivity.this, "approve")) == 1) {
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                if (new SharedPreferenceManager().getPreferences(LoginActivity.this, "roles").equals("expedition")) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        } catch (Exception e) {
            Toast.makeText(LoginActivity.this, "Field tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
    }

    private void initializeComponent() {
        button_login_login.setOnClickListener(this);
        button_login_register.setOnClickListener(this);
        lost_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == button_login_login) {
            Constant.role_data.clear();
            email = editText_login_email.getText().toString();
            password = editText_login_password.getText().toString();
            boolean empty_email = false, empty_password = false;

            if (!email.contains("@")) {
                textView_error_email.setVisibility(View.VISIBLE);
                empty_email = true;
            } else if (email.equals("")) {
                textView_email_empty.setVisibility(View.VISIBLE);
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

            if (!empty_email && !empty_password) {
                showProgressLoading();

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
                            new SharedPreferenceManager().setPreferences(LoginActivity.this, "user_id", "" + user_id);
                            new SharedPreferenceManager().setPreferences(LoginActivity.this, "customer_decode", customer_decode);
                            new SharedPreferenceManager().setPreferences(LoginActivity.this, "is_login", "true");

                            if (activated == true) {
                                if (response.body().getRoles().size() > 0) {
                                    for (Integer ind : roles) {
                                        if (ind != null) {
                                            if (ind == 2) {
                                                hideProgressLoading();
                                                new SharedPreferenceManager().setPreferences(LoginActivity.this, "roles", "admin");
                                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }

                                            if (ind == 3) {
                                                hideProgressLoading();
                                                new SharedPreferenceManager().setPreferences(LoginActivity.this, "roles", "customer");
                                                if (Integer.parseInt(new SharedPreferenceManager().getPreferences(LoginActivity.this, "approve")) == 1) {
                                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            }

                                            if (ind == 4) {
                                                hideProgressLoading();
                                                new SharedPreferenceManager().setPreferences(LoginActivity.this, "roles", "expedition");
                                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        } else {
                                            hideProgressLoading();
                                            new SharedPreferenceManager().setPreferences(LoginActivity.this, "roles", "");
                                            if (Integer.parseInt(new SharedPreferenceManager().getPreferences(LoginActivity.this, "approve")) == 2) {
                                                Intent intent = new Intent(LoginActivity.this, VerificationStatusActivity.class);
                                                startActivity(intent);
                                            } else if (Integer.parseInt(new SharedPreferenceManager().getPreferences(LoginActivity.this, "approve")) == 0) {
                                                Intent intent = new Intent(LoginActivity.this, WaitingApprovalActivity.class);
                                                startActivity(intent);
                                            }
                                        }
                                    }
                                } else {
                                    hideProgressLoading();
                                    new SharedPreferenceManager().setPreferences(LoginActivity.this, "roles", "");
                                    if (Integer.parseInt(new SharedPreferenceManager().getPreferences(LoginActivity.this, "approve")) == 2) {
                                        Intent intent = new Intent(LoginActivity.this, VerificationStatusActivity.class);
                                        startActivity(intent);
                                    } else if (Integer.parseInt(new SharedPreferenceManager().getPreferences(LoginActivity.this, "approve")) == 0) {
                                        Intent intent = new Intent(LoginActivity.this, WaitingApprovalActivity.class);
                                        startActivity(intent);
                                    }
                                }

                            } else {
                                hideProgressLoading();
                                Toast.makeText(LoginActivity.this, "Lakukan Verifikasi Email!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            hideProgressLoading();
                            Toast.makeText(LoginActivity.this, "Email dan Password tidak terdaftar!", Toast.LENGTH_SHORT).show();
                            Log.d("error message", "Error");
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.e("FormCustomer", "Failure" + t.getMessage());
                        hideProgressLoading();
                    }
                });
            } else {
                Toast.makeText(LoginActivity.this, "Field tidak boleh kosong", Toast.LENGTH_SHORT).show();
                hideProgressLoading();
            }
        }

        if (v == button_login_register) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        }

        if (v == lost_password) {
            Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
            startActivity(intent);
            finish();
        }
    }

    ProgressDialog progress_loading;

    public void showProgressLoading() {
        progress_loading = new ProgressDialog(LoginActivity.this);
        progress_loading.setMessage("Please wait...");
        progress_loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress_loading.setIndeterminate(true);
        progress_loading.show();
    }

    public void hideProgressLoading() {
        progress_loading.dismiss();
    }

    private void loadData() {
        UserModel userModel = new UserModel();
        editText_login_email.setText(userModel.getEmail() == null ? "" : userModel.getEmail());
        editText_login_password.setText(userModel.getPassword() == null ? "" : userModel.getPassword());
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
}