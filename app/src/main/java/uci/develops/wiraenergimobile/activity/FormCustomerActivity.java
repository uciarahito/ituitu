package uci.develops.wiraenergimobile.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.fragment.FragmentFormCustomerCompanyInfo;
import uci.develops.wiraenergimobile.fragment.FragmentFormCustomerContactInfo;
import uci.develops.wiraenergimobile.fragment.FragmentFormCustomerShippingTo;
import uci.develops.wiraenergimobile.helper.Constant;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.response.ApproveResponse;
import uci.develops.wiraenergimobile.response.UserResponse;
import uci.develops.wiraenergimobile.service.RestClient;

public class FormCustomerActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout linearLayout_button_back, linearLayout_button_next;
    private LinearLayout linearLayout_container_basic_info, linearLayout_container_contact_info, linearLayout_container_shipping_to;
    private LinearLayout linearLayout_tab_basic_info, linearLayout_tab_contact_info, linearLayout_tab_shipping_to;
    private LinearLayout[] linearLayouts_fragment = new LinearLayout[3];
    private LinearLayout[] linearLayouts_tabs = new LinearLayout[3];
    private TextView textView_button_next, textView_button_back;

    /**
     * Button approve reject for admin
     */
    private LinearLayout linearLayout_button_approve, linearLayout_button_reject;

    int index_fragment = 0;

    private String role = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_customer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        role = new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token");

        initializeComponent();
    }

    private void initializeComponent() {
        linearLayout_button_back = (LinearLayout) findViewById(R.id.layout_button_back);
        linearLayout_button_next = (LinearLayout) findViewById(R.id.layout_button_next);
        linearLayout_container_basic_info = (LinearLayout) findViewById(R.id.layout_container_basic_info);
        linearLayout_container_contact_info = (LinearLayout) findViewById(R.id.layout_container_contact_info);
        linearLayout_container_shipping_to = (LinearLayout) findViewById(R.id.layout_container_shipping_to);
        linearLayout_tab_basic_info = (LinearLayout) findViewById(R.id.layout_tab_company_info);
        linearLayout_tab_contact_info = (LinearLayout) findViewById(R.id.layout_tab_contact_info);
        linearLayout_tab_shipping_to = (LinearLayout) findViewById(R.id.layout_tab_shipping_to);
        linearLayout_button_approve = (LinearLayout) findViewById(R.id.layout_button_approve);
        linearLayout_button_reject = (LinearLayout) findViewById(R.id.layout_button_reject);
        textView_button_back = (TextView) findViewById(R.id.textView_button_back);
        textView_button_next = (TextView) findViewById(R.id.textView_button_next);

        linearLayouts_fragment[0] = linearLayout_container_basic_info;
        linearLayouts_fragment[1] = linearLayout_container_contact_info;
        linearLayouts_fragment[2] = linearLayout_container_shipping_to;

        linearLayouts_tabs[0] = linearLayout_tab_basic_info;
        linearLayouts_tabs[1] = linearLayout_tab_contact_info;
        linearLayouts_tabs[2] = linearLayout_tab_shipping_to;

        linearLayouts_fragment[index_fragment].setVisibility(View.VISIBLE);

        linearLayout_button_next.setOnClickListener(this);
        linearLayout_button_back.setOnClickListener(this);
        linearLayout_button_approve.setOnClickListener(this);
        linearLayout_button_reject.setOnClickListener(this);
        linearLayout_button_back.setVisibility(View.INVISIBLE);

        /**
         * Check if role is admin
         * show button approve and reject
         */
        if (new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "role").equals("admin")) {
            linearLayout_button_approve.setVisibility(View.VISIBLE);
            linearLayout_button_reject.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == linearLayout_button_approve) {
            Call<ApproveResponse> approveResponseCall = RestClient.getRestClient().requestCustomerAction("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"),
                    new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_decode"), 1, 1);
            approveResponseCall.enqueue(new Callback<ApproveResponse>() {
                @Override
                public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
                    if (response.isSuccessful()) {
                        Call<UserResponse> userResponseCall = RestClient.getRestClient().getUser("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"), Integer.parseInt(new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_user_id")));
                        userResponseCall.enqueue(new Callback<UserResponse>() {
                            @Override
                            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                if (response.isSuccessful()) {
                                    if (response.body().getData().getRegistration_key() != null) {
                                        Toast.makeText(FormCustomerActivity.this, "Approve request successfull", Toast.LENGTH_SHORT).show();
                                        Log.e("FormCustomer", "" + response.body().getData().getRegistration_key());
                                        Constant.sendNotification(response.body().getData().getRegistration_key(), "Request anda telah di setujui", "approve_customer");
                                        Intent intent = new Intent(FormCustomerActivity.this, DashboardAdminActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<UserResponse> call, Throwable t) {

                            }
                        });
                        /*
                        Intent intent = new Intent(FormCustomerActivity.this, DashboardAdminActivity.class);
                        startActivity(intent);
                        finish();
                        */
                    } else {
                        Toast.makeText(FormCustomerActivity.this, "Unable to approve request", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ApproveResponse> call, Throwable t) {
                    Toast.makeText(FormCustomerActivity.this, "Unable to approve request", Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (v == linearLayout_button_reject) {
            Call<ApproveResponse> rejectResponseCall = RestClient.getRestClient().requestCustomerAction("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"),
                    new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_decode"), 0, 0);
            rejectResponseCall.enqueue(new Callback<ApproveResponse>() {
                @Override
                public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
                    if (response.isSuccessful()) {
                        Call<UserResponse> userResponseCall = RestClient.getRestClient().getUser("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"), Integer.parseInt(new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_user_id")));
                        userResponseCall.enqueue(new Callback<UserResponse>() {
                            @Override
                            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                if (response.isSuccessful()) {
                                    if (response.body().getData().getRegistration_key() != null) {
                                        Toast.makeText(FormCustomerActivity.this, "Reject request successfull", Toast.LENGTH_SHORT).show();
                                        Log.e("FormCustomer", "" + response.body().getData().getRegistration_key());
                                        Constant.sendNotification(response.body().getData().getRegistration_key(), "Request telah di tolak", "reject_customer");
                                        Intent intent = new Intent(FormCustomerActivity.this, DashboardAdminActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<UserResponse> call, Throwable t) {

                            }
                        });
                        /*
                        Intent intent = new Intent(FormCustomerActivity.this, DashboardAdminActivity.class);
                        startActivity(intent);
                        finish();
                        */
                    } else {
                        Toast.makeText(FormCustomerActivity.this, "Unable to reject request", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ApproveResponse> call, Throwable t) {
                    Toast.makeText(FormCustomerActivity.this, "Unable to reject request", Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (v == linearLayout_button_next) {
            if (index_fragment <= 2) {
                boolean is_not_empty = false;
                CustomerModel customerModel = new CustomerModel();
                if (index_fragment == 0) {
                    FragmentFormCustomerCompanyInfo fragmentFormCustomerCompanyInfo = (FragmentFormCustomerCompanyInfo) getSupportFragmentManager().findFragmentById(R.id.fragment_form_customer_company_info);
                    is_not_empty = fragmentFormCustomerCompanyInfo.isNotEmpty();
                    customerModel = fragmentFormCustomerCompanyInfo.getFormValue();
                }
                if (index_fragment == 1) {
                    FragmentFormCustomerContactInfo fragmentFormCustomerContactInfo = (FragmentFormCustomerContactInfo) getSupportFragmentManager().findFragmentById(R.id.fragment_form_customer_contact_info);
                    is_not_empty = fragmentFormCustomerContactInfo.isNotEmpty();
                    customerModel = fragmentFormCustomerContactInfo.getFormValue();
                }
                if (index_fragment == 2) {
                    FragmentFormCustomerShippingTo fragmentFormCustomerShippingTo = (FragmentFormCustomerShippingTo) getSupportFragmentManager().findFragmentById(R.id.fragment_form_customer_shipping_to);
                    is_not_empty = fragmentFormCustomerShippingTo.isNotEmpty();
                    customerModel = fragmentFormCustomerShippingTo.getFormValue();
                }

                if (new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "role").equals("admin")) {
                    is_not_empty = true;
                }

                if (is_not_empty) {
                    if (index_fragment == 0) {
                        linearLayout_button_back.setVisibility(View.VISIBLE);
                        textView_button_next.setText("Next");
                        Call<ApproveResponse> approveResponseCall = RestClient.getRestClient().sendDataCompanyInfo("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"),
                                new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_decode"), customerModel.getFirst_name(), customerModel.getLast_name(),
                                customerModel.getAddress(), customerModel.getCity(), customerModel.getProvince(), customerModel.getPhone(), customerModel.getMobile(), customerModel.getFax(),
                                customerModel.getTerm(), customerModel.getValuta(), customerModel.getNpwp(), customerModel.getTax().equals("Yes") ? 1 : 0, customerModel.getEmail(), customerModel.getWebsite(), customerModel.getNote(), customerModel.getPostcode());
                        approveResponseCall.enqueue(new Callback<ApproveResponse>() {
                            @Override
                            public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(FormCustomerActivity.this, "Successfully inserted", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(FormCustomerActivity.this, "" + response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                                    Toast.makeText(FormCustomerActivity.this, "Not successfull", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ApproveResponse> call, Throwable t) {

                            }
                        });
                    }
                    if (index_fragment == 1) {
                        if (new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "role").equals("admin")) {
                            textView_button_next.setText("Feedback");
                        } else {
                            textView_button_next.setText("Submit");
                            Call<ApproveResponse> approveResponseCall = RestClient.getRestClient().sendDataContactInfo("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"),
                                    new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_decode"), customerModel.getName1(), customerModel.getName2(), customerModel.getName3(),
                                    customerModel.getPhone1(), customerModel.getPhone2(), customerModel.getPhone3(), customerModel.getMobile1(), customerModel.getMobile2(), customerModel.getMobile3(),
                                    customerModel.getEmail1(), customerModel.getEmail2(), customerModel.getEmail3(), customerModel.getJabatan1(), customerModel.getJabatan2(), customerModel.getJabatan3());
                            approveResponseCall.enqueue(new Callback<ApproveResponse>() {
                                @Override
                                public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
                                    if (response.isSuccessful()) {
                                        Toast.makeText(FormCustomerActivity.this, "Successfully inserted", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(FormCustomerActivity.this, "" + response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                                        Toast.makeText(FormCustomerActivity.this, "Not successfull", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ApproveResponse> call, Throwable t) {

                                }
                            });
                        }
                    }
                    if (index_fragment == 2) {
                        if (new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "role").equals("admin")) {
                            showDialogNote();
                        } else {
                            Call<ApproveResponse> approveResponseCall = RestClient.getRestClient().sendDataShippingInfo("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"),
                                    new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_decode"), customerModel.getShipping_pic(), customerModel.getShipping_address(),
                                    customerModel.getShipping_city(), customerModel.getShipping_province(), customerModel.getShipping_postcode(), customerModel.getShipping_eta(), customerModel.getShipping_map(),
                                    customerModel.getShipping_phone(), customerModel.getShipping_mobile(), customerModel.getShipping_email(), customerModel.getShipping_fax(), customerModel.getShipping_tax(),
                                    customerModel.getShipping_note());
                            approveResponseCall.enqueue(new Callback<ApproveResponse>() {
                                @Override
                                public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
                                    if (response.isSuccessful()) {
                                        final CustomerModel customerModel = new CustomerModel();
                                        Toast.makeText(FormCustomerActivity.this, "Successfully inserted", Toast.LENGTH_SHORT).show();
                                        //baru ditambah
                                        Call<ApproveResponse> approveResponseCall = RestClient.getRestClient().requestCustomerAction(
                                                "Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"), customerModel.getDecode(), 3, 0);
                                        approveResponseCall.enqueue(new Callback<ApproveResponse>() {
                                            @Override
                                            public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
                                                if (response.isSuccessful()) {
                                                    if (new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "role").equals("admin")) {
                                                        Intent intent = new Intent(FormCustomerActivity.this, DashboardAdminActivity.class);
                                                        startActivity(intent);
                                                        finish();
                                                    } else {
                                                        Toast.makeText(FormCustomerActivity.this, "Waiting for approval", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(FormCustomerActivity.this, WaitingApprovalActivity.class);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<ApproveResponse> call, Throwable t) {

                                            }
                                        });
                                    } else {
                                        Toast.makeText(FormCustomerActivity.this, "" + response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                                        Toast.makeText(FormCustomerActivity.this, "Not successfull", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ApproveResponse> call, Throwable t) {

                                }
                            });
                        }
                    }
                    if (index_fragment < 2) {
                        index_fragment++;
                        linearLayout_container_basic_info.setVisibility(View.GONE);
                        linearLayout_container_contact_info.setVisibility(View.GONE);
                        linearLayout_container_shipping_to.setVisibility(View.GONE);
                        linearLayout_tab_basic_info.setBackgroundResource(R.drawable.rounded_rectangle_blue_gray);
                        linearLayout_tab_contact_info.setBackgroundResource(R.drawable.rounded_rectangle_blue_gray);
                        linearLayout_tab_shipping_to.setBackgroundResource(R.drawable.rounded_rectangle_blue_gray);
                        linearLayouts_tabs[index_fragment].setBackgroundResource(R.drawable.rounded_rectangle_blue);
                        linearLayouts_fragment[index_fragment].setVisibility(View.VISIBLE);
                    } else {
//                        Intent intent = new Intent(FormCustomerActivity.this, DashboardCustomerActivity.class);
                        /*
                        Intent intent = new Intent(FormCustomerActivity.this, WaitingApprovalActivity.class);
                        startActivity(intent);
                        finish();
                        */
                    }
                } else {
                    Toast.makeText(FormCustomerActivity.this, "All field are required!", Toast.LENGTH_SHORT).show();
                }
            } else {
//                Intent intent = new Intent(FormCustomerActivity.this, DashboardCustomerActivity.class);
                /*
                Intent intent = new Intent(FormCustomerActivity.this, WaitingApprovalActivity.class);
                startActivity(intent);
                finish();
                */
            }
        } else if (v == linearLayout_button_back) {
            if (index_fragment >= 0) {
                if (index_fragment == 2) {
                    textView_button_next.setText("Next");
                }
                if (index_fragment == 1) {
                    textView_button_next.setText("Next");
                    linearLayout_button_back.setVisibility(View.GONE);
                }
                if (index_fragment == 0) {
                    textView_button_next.setText("Next");
                }

                if (index_fragment > 0) {
                    index_fragment--;
                    linearLayout_container_basic_info.setVisibility(View.GONE);
                    linearLayout_container_contact_info.setVisibility(View.GONE);
                    linearLayout_container_shipping_to.setVisibility(View.GONE);
                    linearLayout_tab_basic_info.setBackgroundResource(R.drawable.rounded_rectangle_blue_gray);
                    linearLayout_tab_contact_info.setBackgroundResource(R.drawable.rounded_rectangle_blue_gray);
                    linearLayout_tab_shipping_to.setBackgroundResource(R.drawable.rounded_rectangle_blue_gray);
                    linearLayouts_tabs[index_fragment].setBackgroundResource(R.drawable.rounded_rectangle_blue);
                    linearLayouts_fragment[index_fragment].setVisibility(View.VISIBLE);
                }
            }
        }
//        else if (v == linearLayout_button_submit) {
//            Intent intent = new Intent(FormCustomerActivity.this, DashboardCustomerActivity.class);
//            startActivity(intent);
//        } else if (v == linearLayout_button_cancel) {
//            Intent intent = new Intent(FormCustomerActivity.this, DashboardCustomerActivity.class);
//            startActivity(intent);
//        }

    }

    private Dialog dialog_feedback;
    EditText editText_feedback;
    Button button_submit_feedback;

    private void showDialogNote() {
        dialog_feedback = new Dialog(FormCustomerActivity.this);
        dialog_feedback.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_feedback.setContentView(R.layout.custom_dialog_add_feedback);

        editText_feedback = (EditText) dialog_feedback.findViewById(R.id.editText_feedback);
        button_submit_feedback = (Button) dialog_feedback.findViewById(R.id.button_submit_feedback);

        button_submit_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText_feedback.getText().toString().equals("")) {
                    Call<ApproveResponse> addFeedbackResponseCall = RestClient.getRestClient().addFeedback("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"),
                            new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_decode"), editText_feedback.getText().toString());
                    addFeedbackResponseCall.enqueue(new Callback<ApproveResponse>() {
                        @Override
                        public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
                            if (response.isSuccessful()) {

                                //baru ditambah uci
                                Call<UserResponse> userResponseCall = RestClient.getRestClient().getUser("Bearer "+new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"), Integer.parseInt(new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_user_id")));
                                userResponseCall.enqueue(new Callback<UserResponse>() {
                                    @Override
                                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getData().getRegistration_key() != null) {
                                                Toast.makeText(FormCustomerActivity.this, "Berhasil menambahkan feedback", Toast.LENGTH_SHORT).show();
                                                Log.e("FormCustomer", "" + response.body().getData().getRegistration_key());
                                                Constant.sendNotification(response.body().getData().getRegistration_key(), ""+editText_feedback.getText().toString(), "feedback_customer");
                                                Intent intent = new Intent(FormCustomerActivity.this, DashboardAdminActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<UserResponse> call, Throwable t) {

                                    }
                                });
                                    // di comment sementara karna lagi ngetest push notification
//                                Intent intent = new Intent(FormCustomerActivity.this, DashboardAdminActivity.class);
//                                startActivity(intent);
//                                finish();

                            }
                        }

                        @Override
                        public void onFailure(Call<ApproveResponse> call, Throwable t) {

                        }
                    });
                } else {
                    Toast.makeText(FormCustomerActivity.this, "Field feedback tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialog_feedback.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog_feedback.setCancelable(true);
        dialog_feedback.show();
    }

}
