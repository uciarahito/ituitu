package uci.develops.wiraenergimobile.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.adapter.CustomExpandableListAdapter;
import uci.develops.wiraenergimobile.adapter.CustomerAdapter;
import uci.develops.wiraenergimobile.adapter.CustomerDialogAdapter;
import uci.develops.wiraenergimobile.fragment.FragmentFormCustomerCompanyInfo;
import uci.develops.wiraenergimobile.fragment.FragmentFormCustomerContactInfo;
import uci.develops.wiraenergimobile.fragment.FragmentFormCustomerShippingTo;
import uci.develops.wiraenergimobile.fragment.navigation.NavigationManager;
import uci.develops.wiraenergimobile.helper.Constant;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerAddressModel;
import uci.develops.wiraenergimobile.model.CustomerGroupModel;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.model.ExpandableListDataSource;
import uci.develops.wiraenergimobile.model.UserXModel;
import uci.develops.wiraenergimobile.response.ApproveResponse;
import uci.develops.wiraenergimobile.response.CustomerResponse;
import uci.develops.wiraenergimobile.response.RequestListCustomerResponse;
import uci.develops.wiraenergimobile.response.UserResponse;
import uci.develops.wiraenergimobile.service.RestClient;

public class FormCustomerActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private String[] items;

    private ExpandableListView mExpandableListView;
    private ExpandableListAdapter mExpandableListAdapter;
    private List<String> mExpandableListTitle;
    private NavigationManager mNavigationManager;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    private Map<String, List<String>> mExpandableListData;

    private LinearLayout linearLayout_button_back, linearLayout_button_next;
    private LinearLayout linearLayout_container_basic_info, linearLayout_container_contact_info, linearLayout_container_shipping_to;
    private LinearLayout linearLayout_tab_basic_info, linearLayout_tab_contact_info, linearLayout_tab_shipping_to;
    private LinearLayout[] linearLayouts_fragment = new LinearLayout[3];
    private LinearLayout[] linearLayouts_tabs = new LinearLayout[3];
    private TextView textView_button_next, textView_button_back;

    //utk button feedback
    private Dialog dialog_feedback;
    EditText editText_feedback;
    Button button_submit_feedback;

    /**
     * Button approve reject for admin
     */
    private LinearLayout linearLayout_button_approve, linearLayout_button_reject;

    int index_fragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_customer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeComponent();
        buttonValidation();

        navDrawer();

        if (savedInstanceState == null) {
            selectFirstItemAsDefault();
        }
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
         * Check if roles is admin
         * show button approve, reject, and feedback
         */
        if (new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "roles").equals("admin")) {
            linearLayout_tab_basic_info.setOnClickListener(this);
            linearLayout_tab_contact_info.setOnClickListener(this);
            linearLayout_tab_shipping_to.setOnClickListener(this);
            linearLayout_button_back.setVisibility(View.GONE);
            linearLayout_button_next.setVisibility(View.GONE);
            if (Integer.parseInt(new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "approve")) == 0) {
                linearLayout_button_approve.setVisibility(View.VISIBLE);
                linearLayout_button_reject.setVisibility(View.VISIBLE);
            } else if (Integer.parseInt(new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "approve")) == 1) {
                linearLayout_button_approve.setVisibility(View.GONE);
                linearLayout_button_reject.setVisibility(View.GONE);
            }
        }

        if (new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "roles").equals("")) {
            Toast.makeText(FormCustomerActivity.this, "Role empty", Toast.LENGTH_SHORT).show();
            if (Integer.parseInt(new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "approve")) == 0) {
                linearLayout_button_back.setVisibility(View.GONE);
                linearLayout_button_next.setVisibility(View.GONE);
                linearLayout_tab_basic_info.setOnClickListener(this);
                linearLayout_tab_contact_info.setOnClickListener(this);
                linearLayout_tab_shipping_to.setOnClickListener(this);
            } else if (Integer.parseInt(new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "approve")) == 2) {
                linearLayout_button_approve.setVisibility(View.GONE);
                linearLayout_button_reject.setVisibility(View.GONE);
                linearLayout_tab_basic_info.setEnabled(false);
                linearLayout_tab_contact_info.setEnabled(false);
                linearLayout_tab_shipping_to.setEnabled(false);
            }
        }

        if (new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "roles").equals("customer")) {
            linearLayout_button_approve.setVisibility(View.GONE);
            linearLayout_button_reject.setVisibility(View.GONE);
            linearLayout_tab_basic_info.setOnClickListener(this);
            linearLayout_tab_contact_info.setOnClickListener(this);
            linearLayout_tab_shipping_to.setOnClickListener(this);
        }
    }

    private void buttonValidation() {
        Call<CustomerResponse> customerResponseCall = RestClient.getRestClient().getCustomer("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"),
                new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_decode"));
        customerResponseCall.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getData().size() > 0) {
                        CustomerModel customerModel = response.body().getData().get(0);
                        if (new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "roles").equals("admin")) {
                            if (customerModel.getApprove() == 1 && customerModel.getActive() == 1) {
                                linearLayout_button_approve.setVisibility(View.GONE);
                                linearLayout_button_reject.setVisibility(View.GONE);
                                linearLayout_button_back.setVisibility(View.GONE);
                                linearLayout_button_next.setVisibility(View.GONE);
                            } else if (customerModel.getApprove() == 0 && customerModel.getActive() == 0) {
                                linearLayout_button_approve.setVisibility(View.VISIBLE);
                                linearLayout_button_reject.setVisibility(View.VISIBLE);
                                linearLayout_button_back.setVisibility(View.GONE);
                                linearLayout_button_next.setVisibility(View.GONE);
                            } else if (customerModel.getApprove() == 2 && customerModel.getActive() == 0) {
                                linearLayout_button_approve.setVisibility(View.VISIBLE);
                                linearLayout_button_reject.setVisibility(View.VISIBLE);
                                linearLayout_button_back.setVisibility(View.GONE);
                                linearLayout_button_next.setVisibility(View.GONE);
                            }
                        } else if (new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "roles").equals("")) {
                            if (customerModel.getApprove() == 2 && customerModel.getActive() == 0) {
                                linearLayout_button_approve.setVisibility(View.GONE);
                                linearLayout_button_reject.setVisibility(View.GONE);
                                linearLayout_tab_basic_info.setEnabled(false);
                                linearLayout_tab_contact_info.setEnabled(false);
                                linearLayout_tab_shipping_to.setEnabled(false);
                            } else if (customerModel.getApprove() == 0 && customerModel.getActive() == 0) {
                                linearLayout_button_back.setVisibility(View.GONE);
                                linearLayout_button_next.setVisibility(View.GONE);
                            }
                        } else if (new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "roles").equals("customer")) {
                            if (customerModel.getApprove() == 1 && customerModel.getActive() == 1) {
                                linearLayout_button_back.setVisibility(View.GONE);
                                linearLayout_button_next.setVisibility(View.GONE);
                                linearLayout_button_approve.setVisibility(View.GONE);
                                linearLayout_button_reject.setVisibility(View.GONE);
                            }
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {

            }
        });
    }

    public void approveActiveCustomer(String customer_code) {
        Call<ApproveResponse> approveResponseCall = RestClient.getRestClient().requestCustomerAction("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"),
                customer_code, 1, 1);
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
                }
            }

            @Override
            public void onFailure(Call<ApproveResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == linearLayout_tab_basic_info) {
            linearLayouts_fragment[0].setVisibility(View.VISIBLE);
            linearLayouts_fragment[1].setVisibility(View.GONE);
            linearLayouts_fragment[2].setVisibility(View.GONE);
        }
        if (v == linearLayout_tab_contact_info) {
            linearLayouts_fragment[0].setVisibility(View.GONE);
            linearLayouts_fragment[1].setVisibility(View.VISIBLE);
            linearLayouts_fragment[2].setVisibility(View.GONE);
        }
        if (v == linearLayout_tab_shipping_to) {
            linearLayouts_fragment[0].setVisibility(View.GONE);
            linearLayouts_fragment[1].setVisibility(View.GONE);
            linearLayouts_fragment[2].setVisibility(View.VISIBLE);
        }
        if (v == linearLayout_button_approve) {
//            approveActiveCustomer(new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_code"));

            Call<ApproveResponse> approveResponseCall = RestClient.getRestClient().customerApproveActive("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"),
                    new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_decode"));
            approveResponseCall.enqueue(new Callback<ApproveResponse>() {
                @Override
                public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
                    if (response.isSuccessful()) {
                        Call<UserResponse> userResponseCall = RestClient.getRestClient().getUser("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"),
                                Integer.parseInt(new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_user_id")));
                        userResponseCall.enqueue(new Callback<UserResponse>() {
                            @Override
                            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                if (response.isSuccessful()) {
                                    if (response.body().getData().getRegistration_key() != null) {
                                        Toast.makeText(FormCustomerActivity.this, "Approve request successfull", Toast.LENGTH_SHORT).show();
                                        Log.e("FormCustomer", "" + response.body().getData().getRegistration_key());
                                        Constant.sendNotification(response.body().getData().getRegistration_key(), "Request anda telah di setujui", "approve_customer");
                                        Intent intent = new Intent(FormCustomerActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<UserResponse> call, Throwable t) {

                            }
                        });
                    } else {
                        Toast.makeText(FormCustomerActivity.this, "Unable to approve request", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ApproveResponse> call, Throwable t) {
                    Toast.makeText(FormCustomerActivity.this, "Unable to approve request", Toast.LENGTH_SHORT).show();
                }
            });

//            Call<ApproveResponse> approveResponseCall = RestClient.getRestClient().requestCustomerAction("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"),
//                    new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_decode"), 1, 1);
//            approveResponseCall.enqueue(new Callback<ApproveResponse>() {
//                @Override
//                public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
//                    if (response.isSuccessful()) {
//                        Call<UserResponse> userResponseCall = RestClient.getRestClient().getUser("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"),
//                                Integer.parseInt(new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_user_id")));
//                        userResponseCall.enqueue(new Callback<UserResponse>() {
//                            @Override
//                            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
//                                if (response.isSuccessful()) {
//                                    if (response.body().getData().getRegistration_key() != null) {
//                                        Toast.makeText(FormCustomerActivity.this, "Approve request successfull", Toast.LENGTH_SHORT).show();
//                                        Log.e("FormCustomer", "" + response.body().getData().getRegistration_key());
//                                        Constant.sendNotification(response.body().getData().getRegistration_key(), "Request anda telah di setujui", "approve_customer");
//                                        Intent intent = new Intent(FormCustomerActivity.this, HomeActivity.class);
//                                        startActivity(intent);
//                                        finish();
//                                    }
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<UserResponse> call, Throwable t) {
//
//                            }
//                        });
//                    } else {
//                        Toast.makeText(FormCustomerActivity.this, "Unable to approve request", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ApproveResponse> call, Throwable t) {
//                    Toast.makeText(FormCustomerActivity.this, "Unable to approve request", Toast.LENGTH_SHORT).show();
//                }
//            });
        }
        if (v == linearLayout_button_reject) {
            showCustomDialogCustomerUsername();
        }
        if (v == linearLayout_button_next) {
            if (index_fragment <= 2) {
                boolean is_not_empty = false;
                CustomerModel customerModel = new CustomerModel();
                CustomerAddressModel customerAddressModel = new CustomerAddressModel();
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
                    customerAddressModel = fragmentFormCustomerShippingTo.getFormValue();
                }

                if (new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "roles").equals("admin")) {
                    is_not_empty = true;
                }

                if (is_not_empty) {
                    if (index_fragment == 0) {
                        linearLayout_button_back.setVisibility(View.VISIBLE);
                        textView_button_next.setText("Next");
                        Call<ApproveResponse> approveResponseCall = RestClient.getRestClient().sendDataCompanyInfo("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"),
                                new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_decode"), customerModel.getFirst_name(), customerModel.getLast_name(),
                                customerModel.getAddress(), customerModel.getCity(), customerModel.getProvince(), customerModel.getPhone(), customerModel.getMobile(), customerModel.getFax(),
                                customerModel.getTerm(), customerModel.getGroup(), customerModel.getValuta(), customerModel.getNpwp(), customerModel.getTax().equals("Yes") ? 1 : 0, customerModel.getEmail(), customerModel.getWebsite(), customerModel.getNote(), customerModel.getPostcode());
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
                        if (new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "roles").equals("admin")) {
//                            textView_button_next.setText("Feedback");
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
                        if (new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "roles").equals("admin")) {
//                            showDialogNote();
                        } else {
//                            Call<ApproveResponse> updateApproveActiveResponseCall = RestClient.getRestClient().customerUpdateApproveActive("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"),
//                                    new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_decode"), 0, 0);
//                            updateApproveActiveResponseCall.enqueue(new Callback<ApproveResponse>() {
//                                @Override
//                                public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
//                                    CustomerAddressModel customerAddressModel = new CustomerAddressModel();
//                                    Call<ApproveResponse> approveResponseCall = RestClient.getRestClient().sendDataShippingInfoNew("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"),
//                                            new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_decode"), 4, customerAddressModel.getName(),
//                                            customerAddressModel.getAddress(), customerAddressModel.getPic(), customerAddressModel.getPhone(),
//                                            customerAddressModel.getMobile(), customerAddressModel.getMap());
//                                    approveResponseCall.enqueue(new Callback<ApproveResponse>() {
//                                        @Override
//                                        public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
//                                            if (response.isSuccessful()) {
//                                                Call<UserResponse> userResponseCall = RestClient.getRestClient().getUser("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"),
//                                                        Integer.parseInt(new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "user_id")));
//                                                userResponseCall.enqueue(new Callback<UserResponse>() {
//                                                    @Override
//                                                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
//                                                        if (response.isSuccessful()) {
//                                                            String reg_key_admin = "";
//                                                            reg_key_admin = response.body().getData().getRegistration_key();
//                                                            Constant.sendNotification(reg_key_admin, "Ada customer baru mendaftar", "register_customer");
//                                                            Toast.makeText(FormCustomerActivity.this, "Waiting for approval", Toast.LENGTH_SHORT).show();
//                                                            Intent intent = new Intent(FormCustomerActivity.this, WaitingApprovalActivity.class);
//                                                            startActivity(intent);
//                                                            finish();
//                                                        }
//                                                    }
//
//                                                    @Override
//                                                    public void onFailure(Call<UserResponse> call, Throwable t) {
//
//                                                    }
//                                                });
//                                            }
//                                        }
//
//                                        @Override
//                                        public void onFailure(Call<ApproveResponse> call, Throwable t) {
//
//                                        }
//                                    });
//                                }
//
//                                @Override
//                                public void onFailure(Call<ApproveResponse> call, Throwable t) {
//
//                                }
//                            });

                            Call<ApproveResponse> approveResponseCall = RestClient.getRestClient().sendDataShippingInfoNew("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"),
                                    new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_decode"), 4, customerAddressModel.getName(),
                                    customerAddressModel.getAddress(), customerAddressModel.getPic(), customerAddressModel.getPhone(),
                                    customerAddressModel.getMobile(), customerAddressModel.getMap());
                            approveResponseCall.enqueue(new Callback<ApproveResponse>() {
                                @Override
                                public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
                                    if (response.isSuccessful()) {
                                        Call<ApproveResponse> updateApproveActiveResponseCall = RestClient.getRestClient().customerUpdateApproveActive("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"),
                                                new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_decode"), 0, 0);
                                        updateApproveActiveResponseCall.enqueue(new Callback<ApproveResponse>() {
                                            @Override
                                            public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
//                                                final CustomerAddressModel customerAddressModel = new CustomerAddressModel();
                                                Toast.makeText(FormCustomerActivity.this, "Successfully inserted", Toast.LENGTH_SHORT).show();
                                                //baru ditambah
                                                Call<ApproveResponse> submitCustomerResponse = RestClient.getRestClient().customerSubmit("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"),
                                                        new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_decode"), 0);
                                                submitCustomerResponse.enqueue(new Callback<ApproveResponse>() {
                                                    @Override
                                                    public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
                                                        if (response.isSuccessful()) {
                                                            Call<UserResponse> userResponseCall = RestClient.getRestClient().getUser("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"),
                                                                    Integer.parseInt(new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "user_id")));
                                                            userResponseCall.enqueue(new Callback<UserResponse>() {
                                                                @Override
                                                                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                                                    if (response.isSuccessful()) {
                                                                        String reg_key_admin = "";
                                                                        reg_key_admin = response.body().getData().getRegistration_key();
                                                                        Constant.sendNotification(reg_key_admin, "Ada customer baru mendaftar", "register_customer");
                                                                        Toast.makeText(FormCustomerActivity.this, "Waiting for approval", Toast.LENGTH_SHORT).show();
                                                                        Intent intent = new Intent(FormCustomerActivity.this, WaitingApprovalActivity.class);
                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                }

                                                                @Override
                                                                public void onFailure(Call<UserResponse> call, Throwable t) {

                                                                }
                                                            });
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Call<ApproveResponse> call, Throwable t) {

                                                    }
                                                });
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
//                        linearLayout_tab_basic_info.setBackgroundResource(R.drawable.rounded_rectangle_blue_gray);
//                        linearLayout_tab_contact_info.setBackgroundResource(R.drawable.rounded_rectangle_blue_gray);
//                        linearLayout_tab_shipping_to.setBackgroundResource(R.drawable.rounded_rectangle_blue_gray);
//                        linearLayouts_tabs[index_fragment].setBackgroundResource(R.drawable.rounded_rectangle_blue);

                        linearLayout_tab_basic_info.setBackgroundResource(R.drawable.rounded_rectangle_dark_gray);
                        linearLayout_tab_contact_info.setBackgroundResource(R.drawable.rounded_rectangle_dark_gray);
                        linearLayout_tab_shipping_to.setBackgroundResource(R.drawable.rounded_rectangle_dark_gray);
                        linearLayouts_tabs[index_fragment].setBackgroundResource(R.drawable.rounded_rectangle_orange);
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

//                    linearLayout_tab_basic_info.setBackgroundResource(R.drawable.rounded_rectangle_blue_gray);
//                    linearLayout_tab_contact_info.setBackgroundResource(R.drawable.rounded_rectangle_blue_gray);
//                    linearLayout_tab_shipping_to.setBackgroundResource(R.drawable.rounded_rectangle_blue_gray);
//                    linearLayouts_tabs[index_fragment].setBackgroundResource(R.drawable.rounded_rectangle_blue);

                    linearLayout_tab_basic_info.setBackgroundResource(R.drawable.rounded_rectangle_dark_gray);
                    linearLayout_tab_contact_info.setBackgroundResource(R.drawable.rounded_rectangle_dark_gray);
                    linearLayout_tab_shipping_to.setBackgroundResource(R.drawable.rounded_rectangle_dark_gray);
                    linearLayouts_tabs[index_fragment].setBackgroundResource(R.drawable.rounded_rectangle_orange);
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
                            new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_decode"), editText_feedback.getText().toString(), 2, 0);
                    addFeedbackResponseCall.enqueue(new Callback<ApproveResponse>() {
                        @Override
                        public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
                            if (response.isSuccessful()) {
                                //baru ditambah
                                Call<UserResponse> userResponseCall = RestClient.getRestClient().getUser("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"), Integer.parseInt(new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_user_id")));
                                userResponseCall.enqueue(new Callback<UserResponse>() {
                                    @Override
                                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getData().getRegistration_key() != null) {
                                                Constant.sendNotification(response.body().getData().getRegistration_key(), "" + editText_feedback.getText().toString(), "feedback_customer");
                                                Intent intent = new Intent(FormCustomerActivity.this, HomeActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<UserResponse> call, Throwable t) {

                                    }
                                });
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

    private void alertDialogReject() {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure want to reject the customer?");
        alertDialogBuilder.setNegativeButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Call<UserResponse> userResponseCall = RestClient.getRestClient().getUser("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"), Integer.parseInt(new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_user_id")));
                        userResponseCall.enqueue(new Callback<UserResponse>() {
                            @Override
                            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                if (response.isSuccessful()) {
                                    if (response.body().getData().getRegistration_key() != null) {
                                        Toast.makeText(FormCustomerActivity.this, "Reject request successfull", Toast.LENGTH_SHORT).show();
                                        Log.e("FormCustomer", "" + response.body().getData().getRegistration_key());
                                        Constant.sendNotification(response.body().getData().getRegistration_key(), "Request telah di tolak", "reject_customer");
                                        Intent intent = new Intent(FormCustomerActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<UserResponse> call, Throwable t) {

                            }
                        });
                    }
                });
        alertDialogBuilder.setPositiveButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private EditText editText_dialog_customer_code;
    private Button button_dialog_save, button_dialog_cancel, button_dialog_contact1_delete, button_dialog_contact2_delete,
            button_dialog_contact3_delete, button_dialog_submit, button_dialog_new_customer, button_dialog_existing_customer,
            button_dialog_keep_username, button_dialog_delete_username;
    private TextView textView_dialog_contact1_name, textView_dialog_contact1_email, textView_dialog_contact2_name,
            textView_dialog_contact2_email, textView_dialog_contact3_name, textView_dialog_contact3_email;
    private RecyclerView recyclerView_customer;

    boolean is_new_customer = false;

    private void showCustomDialogCustomerUsername() {
        final Dialog dialog_customer_register_type = new Dialog(FormCustomerActivity.this);
        dialog_customer_register_type.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_customer_register_type.setContentView(R.layout.custom_dialog_username_choice);

        button_dialog_keep_username = (Button) dialog_customer_register_type.findViewById(R.id.button_keep_username);
        button_dialog_delete_username = (Button) dialog_customer_register_type.findViewById(R.id.button_delete_username);

        button_dialog_keep_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is_new_customer = true;
                dialog_customer_register_type.dismiss();
                showCustomDialogListCustomer();
            }
        });

        button_dialog_delete_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showCustomDialogListCustomer();
                /**
                 * Access delete username
                 */
                alertDialogDeleteUsername();

            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialog_customer_register_type.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog_customer_register_type.setCancelable(false);
        dialog_customer_register_type.show();
    }

    private void alertDialogDeleteUsername() {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure want to delete the customer?");
        alertDialogBuilder.setNegativeButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Call<ApproveResponse> customerRejectUpdateUser = RestClient.getRestClient().customerRejectDeleteCustomer("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"),
                                new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_decode"));
                        customerRejectUpdateUser.enqueue(new Callback<ApproveResponse>() {
                            @Override
                            public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
                                Intent intent = new Intent(FormCustomerActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }

                            @Override
                            public void onFailure(Call<ApproveResponse> call, Throwable t) {

                            }
                        });
                    }
                });
        alertDialogBuilder.setPositiveButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(FormCustomerActivity.this, ListRequestCustomerActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void showCustomDialogCustomerCode() {
        final Dialog dialog_customer_code = new Dialog(FormCustomerActivity.this);
        dialog_customer_code.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_customer_code.setContentView(R.layout.custom_dialog_customer_code_input);

        editText_dialog_customer_code = (EditText) dialog_customer_code.findViewById(R.id.editText_customer_code);
        button_dialog_save = (Button) dialog_customer_code.findViewById(R.id.button_save);
        button_dialog_cancel = (Button) dialog_customer_code.findViewById(R.id.button_cancel);

        editText_dialog_customer_code.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!is_new_customer) {
                    showCustomDialogCustomerRegisterType();
                }
                return false;
            }
        });

        button_dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_customer_code.dismiss();
            }
        });

        button_dialog_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText_dialog_customer_code.getText().toString().equals("")) {
                    Toast.makeText(FormCustomerActivity.this, "Field customer code is required", Toast.LENGTH_SHORT).show();
                } else {
                    Call<ApproveResponse> approveResponseCall = RestClient.getRestClient().requestCustomerAction("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"),
                            new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_decode"), 1, 1);
                    approveResponseCall.enqueue(new Callback<ApproveResponse>() {
                        @Override
                        public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(FormCustomerActivity.this, "Approve response success", Toast.LENGTH_SHORT).show();
                                Call<UserResponse> userResponseCall = RestClient.getRestClient().getUser("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"), Integer.parseInt(new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_user_id")));
                                userResponseCall.enqueue(new Callback<UserResponse>() {
                                    @Override
                                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().getData().getRegistration_key() != null) {
                                                Toast.makeText(FormCustomerActivity.this, "Approve request successfull", Toast.LENGTH_SHORT).show();
                                                Log.e("FormCustomer", "" + response.body().getData().getRegistration_key());
                                                Constant.sendNotification(response.body().getData().getRegistration_key(), "Request anda telah di setujui", "approve_customer");
                                                Intent intent = new Intent(FormCustomerActivity.this, HomeActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<UserResponse> call, Throwable t) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<ApproveResponse> call, Throwable t) {

                        }
                    });
                }
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialog_customer_code.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog_customer_code.setCancelable(true);
        dialog_customer_code.show();
    }

    private void showCustomDialogContactInfoCustomer() {
        final Dialog dialog_contactt_info_customer = new Dialog(FormCustomerActivity.this);
        dialog_contactt_info_customer.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_contactt_info_customer.setContentView(R.layout.custom_dialog_contact_info_customer);

        textView_dialog_contact1_name = (TextView) dialog_contactt_info_customer.findViewById(R.id.textView_contact1_name);
        textView_dialog_contact2_name = (TextView) dialog_contactt_info_customer.findViewById(R.id.textView_contact2_name);
        textView_dialog_contact3_name = (TextView) dialog_contactt_info_customer.findViewById(R.id.textView_contact3_name);
        textView_dialog_contact1_email = (TextView) dialog_contactt_info_customer.findViewById(R.id.textView_contact1_email);
        textView_dialog_contact2_email = (TextView) dialog_contactt_info_customer.findViewById(R.id.textView_contact2_email);
        textView_dialog_contact3_email = (TextView) dialog_contactt_info_customer.findViewById(R.id.textView_contact3_email);
        button_dialog_contact1_delete = (Button) dialog_contactt_info_customer.findViewById(R.id.button_delete_contact_1);
        button_dialog_contact2_delete = (Button) dialog_contactt_info_customer.findViewById(R.id.button_delete_contact_2);
        button_dialog_contact3_delete = (Button) dialog_contactt_info_customer.findViewById(R.id.button_delete_contact_3);
        button_dialog_submit = (Button) dialog_contactt_info_customer.findViewById(R.id.button_submit);

        Call<CustomerResponse> customerResponseCall = RestClient.getRestClient().getCustomer("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"),
                new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "customer_decode"));
        customerResponseCall.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if (response.isSuccessful()) {
                    CustomerModel customerModel = response.body().getData().get(0);
                    textView_dialog_contact1_name.setText("" + (customerModel.getName1()));
                    textView_dialog_contact2_name.setText("" + (customerModel.getName2()));
                    textView_dialog_contact3_name.setText("" + (customerModel.getName3()));
                    textView_dialog_contact1_email.setText("" + (customerModel.getEmail1()));
                    textView_dialog_contact2_email.setText("" + (customerModel.getEmail2()));
                    textView_dialog_contact3_email.setText("" + (customerModel.getEmail3()));
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {

            }
        });

        button_dialog_contact1_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // delete contact 1
                dialog_contactt_info_customer.dismiss();
            }
        });
        button_dialog_contact2_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // delete contact 2
                dialog_contactt_info_customer.dismiss();
            }
        });
        button_dialog_contact3_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // delete contact 3
                dialog_contactt_info_customer.dismiss();
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialog_contactt_info_customer.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog_contactt_info_customer.setCancelable(true);
        dialog_contactt_info_customer.show();
    }

    private void showCustomDialogCustomerRegisterType() {
        final Dialog dialog_customer_register_type = new Dialog(FormCustomerActivity.this);
        dialog_customer_register_type.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_customer_register_type.setContentView(R.layout.custom_dialog_customer_register_type);

        button_dialog_new_customer = (Button) dialog_customer_register_type.findViewById(R.id.button_new_customer);
        button_dialog_existing_customer = (Button) dialog_customer_register_type.findViewById(R.id.button_existing_customer);

        button_dialog_new_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is_new_customer = true;
                dialog_customer_register_type.dismiss();
            }
        });

        button_dialog_existing_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialogListCustomer();
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialog_customer_register_type.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog_customer_register_type.setCancelable(false);
        dialog_customer_register_type.show();
    }

    List<CustomerModel> modelRequestList;
    CustomerDialogAdapter customerDialogAdapter;

    private void showCustomDialogListCustomer() {
        final Dialog dialog_list_customer = new Dialog(FormCustomerActivity.this);
        dialog_list_customer.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_list_customer.setContentView(R.layout.custom_dialog_list_customer);

        recyclerView_customer = (RecyclerView) dialog_list_customer.findViewById(R.id.recycleView_customer);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(FormCustomerActivity.this);
        recyclerView_customer.setLayoutManager(mLayoutManager);
        recyclerView_customer.setItemAnimator(new DefaultItemAnimator());
        modelRequestList = new ArrayList<>();
        customerDialogAdapter = new CustomerDialogAdapter(FormCustomerActivity.this, modelRequestList);
        recyclerView_customer.setAdapter(customerDialogAdapter);

        Call<RequestListCustomerResponse> requestListCustomerResponseCall = RestClient.getRestClient().getAllRequestCustomer("Bearer " + new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"));
        requestListCustomerResponseCall.enqueue(new Callback<RequestListCustomerResponse>() {
            @Override
            public void onResponse(Call<RequestListCustomerResponse> call, Response<RequestListCustomerResponse> response) {
                if (response.isSuccessful()) {
//                    modelRequestList = response.body().getData();
//                    customerDialogAdapter.updateList(modelRequestList);

                    if (response.body().getData().size() > 0) {
                        modelRequestList = response.body().getData();
                        List<CustomerModel> dataCustomer = new ArrayList<CustomerModel>();
                        for (CustomerModel customerModel : modelRequestList) {
                            if (customerModel.getActive() == 1 && customerModel.getApprove() == 1) {
                                dataCustomer.add(customerModel);

                            }
                        }
                        if (dataCustomer.size() > 0) {
                            customerDialogAdapter.updateList(dataCustomer);
                        } else {
                            Toast.makeText(FormCustomerActivity.this, "Empty data", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(FormCustomerActivity.this, "Empty data", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RequestListCustomerResponse> call, Throwable t) {

            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialog_list_customer.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog_list_customer.setCancelable(true);
        dialog_list_customer.show();
    }

    private void selectFirstItemAsDefault() {
        if (mNavigationManager != null) {
            //String firstActionMovie = getResources().getStringArray(R.array.actionFilms)[0];
            //mNavigationManager.showFragmentAction(firstActionMovie);
            //getSupportActionBar().setTitle(firstActionMovie);
        }
    }

    public void navDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        mExpandableListView = (ExpandableListView) mDrawerLayout.findViewById(R.id.navList);

        initItems();

        LayoutInflater inflater = getLayoutInflater();
        View listHeaderView;
        listHeaderView = inflater.inflate(R.layout.nav_header, null, false);
        mExpandableListView.addHeaderView(listHeaderView);

        ImageView imageView_profile = (ImageView) listHeaderView.findViewById(R.id.imageView_profile);
        final TextView textView_name = (TextView) listHeaderView.findViewById(R.id.textView_name);

        imageView_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "roles").equals("admin")) {
                    Intent intent = new Intent(FormCustomerActivity.this, ListCustomerActivity.class);
                    startActivity(intent);
                } else if (new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "roles").equals("customer")) {
                    Intent intent = new Intent(FormCustomerActivity.this, FormCustomerActivity.class);
                    startActivity(intent);
                } else if (new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "roles").equals("")) {
                    Intent intent = new Intent(FormCustomerActivity.this, FormCustomerActivity.class);
                    startActivity(intent);
                }
            }
        });

        Call<UserResponse> userResponseCall = RestClient.getRestClient().getUser("Bearer " + new
                        SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "token"),
                Integer.parseInt(new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "user_id")));
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    String name = "";
                    UserXModel userXModel = new UserXModel();
                    userXModel = response.body().getData();
                    textView_name.setText(userXModel.getName() == null ? "" : userXModel.getName());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });

        mExpandableListData = ExpandableListDataSource.getData(this);
        List<String> rootMenu = new ArrayList<>();

        if (new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "roles").equals("admin")) {
            rootMenu.add("Dashboard");
            rootMenu.add("Customer");
            rootMenu.add("Purchasing");
            rootMenu.add("Sales");
            rootMenu.add("Logout");
        } else if (new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "roles").equals("customer")) {
            rootMenu.add("Dashboard");
            rootMenu.add("Customer");
            rootMenu.add("Sales");
            rootMenu.add("Logout");
        } else if (new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "roles").equals("")) {
            rootMenu.add("Logout");
        } else if (new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "roles").equals("expedition")) {
            rootMenu.add("Dashboard");
            rootMenu.add("Delivery Order");
            rootMenu.add("Logout");
        }
        mExpandableListTitle = rootMenu;

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initItems() {
        items = ExpandableListDataSource.getArrayTitle(FormCustomerActivity.this);
    }

    private void addDrawerItems() {
        mExpandableListAdapter = new CustomExpandableListAdapter(this, mExpandableListTitle, mExpandableListData);
        mExpandableListView.setAdapter(mExpandableListAdapter);

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                String selectedItem = ((List) (mExpandableListData.get(mExpandableListTitle.get(groupPosition))))
                        .get(childPosition).toString();
                getSupportActionBar().setTitle(selectedItem);

                Toast.makeText(FormCustomerActivity.this, "" + selectedItem, Toast.LENGTH_SHORT).show();
                /*
                if (items[0].equals(mExpandableListTitle.get(groupPosition))) {
                    mNavigationManager.showFragmentNavPurchasing(selectedItem);
                } else if (items[1].equals(mExpandableListTitle.get(groupPosition))) {
                    mNavigationManager.showFragmentNavSales(selectedItem);
                } else {
                    throw new IllegalArgumentException("Not supported fragment type");
                }*/

                //utk menu purchasing
                if (selectedItem.equals("Purchase Order [PO]")) {
                    Log.e("Cekkkkkk", selectedItem + "qqqqqqqqqqqqqqqq");
                    Intent intent = new Intent(FormCustomerActivity.this, PurchaseOrderActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Good Received [GR]")) {
                    Intent intent = new Intent(FormCustomerActivity.this, GoodReceivedActivity.class);
                    startActivity(intent);
                }

                //utk menu sales
                if (selectedItem.equals("Quotation")) {
                    Intent intent = new Intent(FormCustomerActivity.this, SalesQuotationActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Sales Order [SO]")) {
                    Intent intent = new Intent(FormCustomerActivity.this, SalesOrderActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Delivery Order [DO]")) {
                    Intent intent = new Intent(FormCustomerActivity.this, DeliveryOrderActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Invoice")) {
                    Intent intent = new Intent(FormCustomerActivity.this, InvoiceActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Payment")) {
                    Intent intent = new Intent(FormCustomerActivity.this, PaymentActivity.class);
                    startActivity(intent);
                }

                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });

        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                String selected_item = getResources().getStringArray(R.array.general)[groupPosition];
                if (selected_item.equals("Logout")) {
                    logout();
                } else if (selected_item.equals("Dashboard")) {
                    Intent intent = new Intent(FormCustomerActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else if (selected_item.equals("Customer")) {
                    if (new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "roles").equals("admin")) {
                        Intent intent = new Intent(FormCustomerActivity.this, HomeActivity.class);
                        startActivity(intent);
                    } else if (new SharedPreferenceManager().getPreferences(FormCustomerActivity.this, "roles").equals("customer")) {
                        Intent intent = new Intent(FormCustomerActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    }
                }
                return false;
            }
        });
    }

    private void logout() {
        new SharedPreferenceManager().setPreferences(FormCustomerActivity.this, "is_login", "");
        new SharedPreferenceManager().setPreferences(FormCustomerActivity.this, "token", "");
        new SharedPreferenceManager().setPreferences(FormCustomerActivity.this, "customer_decode", "");
        new SharedPreferenceManager().setPreferences(FormCustomerActivity.this, "roles", "");

        Intent intent = new Intent(FormCustomerActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(R.string.dashboard);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
