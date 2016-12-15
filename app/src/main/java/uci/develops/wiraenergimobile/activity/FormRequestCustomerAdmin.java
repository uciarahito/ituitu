package uci.develops.wiraenergimobile.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.view.Menu;
import android.view.MenuItem;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.adapter.CustomExpandableListAdapter;
import uci.develops.wiraenergimobile.adapter.CustomerDialogAdapter;
import uci.develops.wiraenergimobile.fragment.navigation.NavigationManager;
import uci.develops.wiraenergimobile.helper.Constant;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.model.ExpandableListDataSource;
import uci.develops.wiraenergimobile.model.UserXModel;
import uci.develops.wiraenergimobile.response.ApproveResponse;
import uci.develops.wiraenergimobile.response.RequestListCustomerResponse;
import uci.develops.wiraenergimobile.response.UserResponse;
import uci.develops.wiraenergimobile.service.RestClient;

public class FormRequestCustomerAdmin extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.layout_button_approve) LinearLayout layout_button_approve;
    @BindView(R.id.layout_button_reject) LinearLayout layout_button_reject;
    @BindView(R.id.layout_tab_company_info) LinearLayout layout_tab_company_info;
    @BindView(R.id.layout_tab_contact_info) LinearLayout layout_tab_contact_info;
    @BindView(R.id.layout_tab_shipping_to) LinearLayout layout_tab_shipping_to;
    @BindView(R.id.layout_container_company_info) LinearLayout layout_container_company_info;
    @BindView(R.id.layout_container_contact_info) LinearLayout layout_container_contact_info;
    @BindView(R.id.layout_container_shipping_to) LinearLayout layout_container_shipping_to;

    private LinearLayout[] linearLayouts_fragment = new LinearLayout[3];
    private LinearLayout[] linearLayouts_tabs = new LinearLayout[3];
    int index_fragment = 0;
    public static CustomerModel customerModel_temp;

    //utk nav drawer
    private DrawerLayout mDrawerLayout;
    private String[] items;

    private ExpandableListView mExpandableListView;
    private ExpandableListAdapter mExpandableListAdapter;
    private List<String> mExpandableListTitle;
    private NavigationManager mNavigationManager;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    private Map<String, List<String>> mExpandableListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_request_customer_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        customerModel_temp = new CustomerModel();
        initializeComponent();

        navDrawer();

        if (savedInstanceState == null) {
            selectFirstItemAsDefault();
        }
    }

    private void initializeComponent() {
        linearLayouts_fragment[0] = layout_container_company_info;
        linearLayouts_fragment[1] = layout_container_contact_info;
        linearLayouts_fragment[2] = layout_container_shipping_to;

        linearLayouts_tabs[0] = layout_tab_company_info;
        linearLayouts_tabs[1] = layout_tab_contact_info;
        linearLayouts_tabs[2] = layout_tab_shipping_to;

        layout_tab_company_info.setBackgroundResource(R.drawable.rounded_rectangle_orange);
        layout_tab_contact_info.setBackgroundResource(R.drawable.rounded_rectangle_dark_gray);
        layout_tab_shipping_to.setBackgroundResource(R.drawable.rounded_rectangle_dark_gray);

        linearLayouts_fragment[index_fragment].setVisibility(View.VISIBLE);

        layout_tab_company_info.setOnClickListener(this);
        layout_tab_contact_info.setOnClickListener(this);
        layout_tab_shipping_to.setOnClickListener(this);
        layout_button_approve.setOnClickListener(this);
        layout_button_reject.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == layout_tab_company_info) {
            linearLayouts_fragment[0].setVisibility(View.VISIBLE);
            linearLayouts_fragment[1].setVisibility(View.GONE);
            linearLayouts_fragment[2].setVisibility(View.GONE);

            layout_tab_company_info.setBackgroundResource(R.drawable.rounded_rectangle_orange);
            layout_tab_contact_info.setBackgroundResource(R.drawable.rounded_rectangle_dark_gray);
            layout_tab_shipping_to.setBackgroundResource(R.drawable.rounded_rectangle_dark_gray);
        }
        if (v == layout_tab_contact_info) {
            linearLayouts_fragment[0].setVisibility(View.GONE);
            linearLayouts_fragment[1].setVisibility(View.VISIBLE);
            linearLayouts_fragment[2].setVisibility(View.GONE);

            layout_tab_company_info.setBackgroundResource(R.drawable.rounded_rectangle_dark_gray);
            layout_tab_contact_info.setBackgroundResource(R.drawable.rounded_rectangle_orange);
            layout_tab_shipping_to.setBackgroundResource(R.drawable.rounded_rectangle_dark_gray);
        }
        if (v == layout_tab_shipping_to) {
            linearLayouts_fragment[0].setVisibility(View.GONE);
            linearLayouts_fragment[1].setVisibility(View.GONE);
            linearLayouts_fragment[2].setVisibility(View.VISIBLE);

            layout_tab_company_info.setBackgroundResource(R.drawable.rounded_rectangle_dark_gray);
            layout_tab_contact_info.setBackgroundResource(R.drawable.rounded_rectangle_dark_gray);
            layout_tab_shipping_to.setBackgroundResource(R.drawable.rounded_rectangle_orange);
        }
        if (v == layout_button_approve) {
            showProgressLoading();

            Call<ApproveResponse> approveResponseCall = RestClient.getRestClient().customerApproveActive("Bearer " +
                    new SharedPreferenceManager().getPreferences(FormRequestCustomerAdmin.this, "token"),
                    new SharedPreferenceManager().getPreferences(FormRequestCustomerAdmin.this, "customer_decode"));
            approveResponseCall.enqueue(new Callback<ApproveResponse>() {
                @Override
                public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
                    if (response.isSuccessful()) {
                        Call<UserResponse> userResponseCall = RestClient.getRestClient().getUser("Bearer " +
                                new SharedPreferenceManager().getPreferences(FormRequestCustomerAdmin.this, "token"),
                                Integer.parseInt(new SharedPreferenceManager().getPreferences(FormRequestCustomerAdmin.this, "customer_user_id")));
                        userResponseCall.enqueue(new Callback<UserResponse>() {
                            @Override
                            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                                if (response.isSuccessful()) {
                                    if (response.body().getData().getRegistration_key() != null) {
                                        hideProgressLoading();
                                        Toast.makeText(FormRequestCustomerAdmin.this, "Approve request successfull", Toast.LENGTH_SHORT).show();
                                        Log.e("FormCustomer", "" + response.body().getData().getRegistration_key());
                                        Constant.sendNotification(response.body().getData().getRegistration_key(), "Request anda telah di setujui", "approve_customer");
                                        Intent intent = new Intent(FormRequestCustomerAdmin.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<UserResponse> call, Throwable t) {
                                hideProgressLoading();
                            }
                        });

                        Intent intent = new Intent(FormRequestCustomerAdmin.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        hideProgressLoading();
                        Toast.makeText(FormRequestCustomerAdmin.this, "Unable to approve request", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ApproveResponse> call, Throwable t) {
                    hideProgressLoading();
                    Toast.makeText(FormRequestCustomerAdmin.this, "Unable to approve request", Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (v == layout_button_reject) {
            showCustomDialogCustomerUsername();
        }
    }

    boolean is_new_customer = false;
    private void showCustomDialogCustomerUsername() {
        final Dialog dialog_customer_register_type = new Dialog(FormRequestCustomerAdmin.this);
        dialog_customer_register_type.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_customer_register_type.setContentView(R.layout.custom_dialog_username_choice);

        Button button_dialog_keep_username = ButterKnife.findById(dialog_customer_register_type, R.id.button_keep_username);
        Button button_dialog_delete_username = ButterKnife.findById(dialog_customer_register_type, R.id.button_delete_username);

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
                        Call<ApproveResponse> customerRejectUpdateUser = RestClient.getRestClient().customerRejectDeleteCustomer("Bearer " +
                                new SharedPreferenceManager().getPreferences(FormRequestCustomerAdmin.this, "token"),
                                new SharedPreferenceManager().getPreferences(FormRequestCustomerAdmin.this, "customer_decode"));
                        customerRejectUpdateUser.enqueue(new Callback<ApproveResponse>() {
                            @Override
                            public void onResponse(Call<ApproveResponse> call, Response<ApproveResponse> response) {
                                if (response.isSuccessful()) {
                                    Intent intent = new Intent(FormRequestCustomerAdmin.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else{

                                }
                            }

                            @Override
                            public void onFailure(Call<ApproveResponse> call, Throwable t) {

                            }
                        });

                        Intent intent = new Intent(FormRequestCustomerAdmin.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
        alertDialogBuilder.setPositiveButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(FormRequestCustomerAdmin.this, ListRequestCustomerActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    List<CustomerModel> modelRequestList;
    CustomerDialogAdapter customerDialogAdapter;

    private void showCustomDialogListCustomer() {
        final Dialog dialog_list_customer = new Dialog(FormRequestCustomerAdmin.this);
        dialog_list_customer.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_list_customer.setContentView(R.layout.custom_dialog_list_customer);

        RecyclerView recyclerView_customer = ButterKnife.findById(dialog_list_customer, R.id.recycleView_customer);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(FormRequestCustomerAdmin.this);
        recyclerView_customer.setLayoutManager(mLayoutManager);
        recyclerView_customer.setItemAnimator(new DefaultItemAnimator());
        modelRequestList = new ArrayList<>();
        customerDialogAdapter = new CustomerDialogAdapter(FormRequestCustomerAdmin.this, modelRequestList);
        recyclerView_customer.setAdapter(customerDialogAdapter);

        showProgressLoading();
        Call<RequestListCustomerResponse> requestListCustomerResponseCall = RestClient.getRestClient().getAllRequestCustomer("Bearer " +
                new SharedPreferenceManager().getPreferences(FormRequestCustomerAdmin.this, "token"));
        requestListCustomerResponseCall.enqueue(new Callback<RequestListCustomerResponse>() {
            @Override
            public void onResponse(Call<RequestListCustomerResponse> call, Response<RequestListCustomerResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getData().size() > 0) {
                        modelRequestList = response.body().getData();
                        List<CustomerModel> dataCustomer = new ArrayList<CustomerModel>();
                        for (CustomerModel customerModel : modelRequestList) {
                            if (customerModel.getActive() == 1 && customerModel.getApprove() == 1) {
                                hideProgressLoading();
                                dataCustomer.add(customerModel);
                            }
                        }
                        if (dataCustomer.size() > 0) {
                            hideProgressLoading();
                            customerDialogAdapter.updateList(dataCustomer);
                        } else {
                            hideProgressLoading();
                            Toast.makeText(FormRequestCustomerAdmin.this, "Empty data", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        hideProgressLoading();
                        Toast.makeText(FormRequestCustomerAdmin.this, "Empty data", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RequestListCustomerResponse> call, Throwable t) {
                hideProgressLoading();
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

    ProgressDialog progress_loading;
    public void showProgressLoading() {
        progress_loading = new ProgressDialog(FormRequestCustomerAdmin.this);
        progress_loading.setMessage("Please wait...");
        progress_loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress_loading.setIndeterminate(true);
        progress_loading.show();
    }

    public void hideProgressLoading() {
        progress_loading.dismiss();
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
                if (new SharedPreferenceManager().getPreferences(FormRequestCustomerAdmin.this, "roles").equals("admin")) {
                    Intent intent = new Intent(FormRequestCustomerAdmin.this, ListCustomerActivity.class);
                    startActivity(intent);
                } else if (new SharedPreferenceManager().getPreferences(FormRequestCustomerAdmin.this, "roles").equals("customer")) {
                    Intent intent = new Intent(FormRequestCustomerAdmin.this, FormCustomerActivity.class);
                    startActivity(intent);
                }
            }
        });

        Call<UserResponse> userResponseCall = RestClient.getRestClient().getUser("Bearer " + new
                        SharedPreferenceManager().getPreferences(FormRequestCustomerAdmin.this, "token"),
                Integer.parseInt(new SharedPreferenceManager().getPreferences(FormRequestCustomerAdmin.this, "user_id")));
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

        if (new SharedPreferenceManager().getPreferences(FormRequestCustomerAdmin.this, "roles").equals("admin")) {
            rootMenu.add("Dashboard");
            rootMenu.add("Customer");
            rootMenu.add("Purchasing");
            rootMenu.add("Sales");
            rootMenu.add("Logout");
        } else if (new SharedPreferenceManager().getPreferences(FormRequestCustomerAdmin.this, "roles").equals("customer")) {
            rootMenu.add("Dashboard");
            rootMenu.add("Profile");
            rootMenu.add("Sales");
            rootMenu.add("Logout");
        } else if (new SharedPreferenceManager().getPreferences(FormRequestCustomerAdmin.this, "roles").equals("")) {
            rootMenu.add("Logout");
        } else if (new SharedPreferenceManager().getPreferences(FormRequestCustomerAdmin.this, "roles").equals("expedition")) {
            rootMenu.add("Dashboard");
            rootMenu.add("Profile");
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
        items = ExpandableListDataSource.getArrayTitle(FormRequestCustomerAdmin.this);
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

                Toast.makeText(FormRequestCustomerAdmin.this, "" + selectedItem, Toast.LENGTH_SHORT).show();
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
                    Intent intent = new Intent(FormRequestCustomerAdmin.this, PurchaseOrderActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Good Received [GR]")) {
                    Intent intent = new Intent(FormRequestCustomerAdmin.this, GoodReceivedActivity.class);
                    startActivity(intent);
                }

                //utk menu sales
                if (selectedItem.equals("Quotation")) {
                    Intent intent = new Intent(FormRequestCustomerAdmin.this, SalesQuotationAdminActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Sales Order [SO]")) {
                    Intent intent = new Intent(FormRequestCustomerAdmin.this, SalesOrderActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Delivery Order [DO]")) {
                    Intent intent = new Intent(FormRequestCustomerAdmin.this, DeliveryOrderActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Invoice")) {
                    Intent intent = new Intent(FormRequestCustomerAdmin.this, InvoiceActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Payment")) {
                    Intent intent = new Intent(FormRequestCustomerAdmin.this, PaymentActivity.class);
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
                if(new SharedPreferenceManager().getPreferences(FormRequestCustomerAdmin.this, "roles").equals("admin")){
                    selected_item = getResources().getStringArray(R.array.general)[groupPosition];
                } else if(new SharedPreferenceManager().getPreferences(FormRequestCustomerAdmin.this, "roles").equals("customer")){
                    selected_item = getResources().getStringArray(R.array.general_customer)[groupPosition];
                } else if (new SharedPreferenceManager().getPreferences(FormRequestCustomerAdmin.this, "roles").equals("expedition")){
                    selected_item = getResources().getStringArray(R.array.general_expedition)[groupPosition];
                } else if (new SharedPreferenceManager().getPreferences(FormRequestCustomerAdmin.this, "roles").equals("")){
                    selected_item = getResources().getStringArray(R.array.general_guest)[groupPosition];
                }

                if (selected_item.equals("Logout")) {
                    logout();
                } else if (selected_item.equals("Dashboard")) {
                    Intent intent = new Intent(FormRequestCustomerAdmin.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else if (selected_item.equals("Customer")) {
                    if (new SharedPreferenceManager().getPreferences(FormRequestCustomerAdmin.this, "roles").equals("admin")) {
                        Intent intent = new Intent(FormRequestCustomerAdmin.this, HomeActivity.class);
                        startActivity(intent);
                    }
                }  else if (selected_item.equals("Profile")) {
                    if (new SharedPreferenceManager().getPreferences(FormRequestCustomerAdmin.this, "roles").equals("customer")) {
                        Intent intent = new Intent(FormRequestCustomerAdmin.this, FormCustomerActivity.class);
                        startActivity(intent);
                    }
                }
                return false;
            }
        });
    }

    private void logout() {
        new SharedPreferenceManager().setPreferences(FormRequestCustomerAdmin.this, "is_login", "");
        new SharedPreferenceManager().setPreferences(FormRequestCustomerAdmin.this, "token", "");
        new SharedPreferenceManager().setPreferences(FormRequestCustomerAdmin.this, "customer_decode", "");
        new SharedPreferenceManager().setPreferences(FormRequestCustomerAdmin.this, "roles", "");

        Intent intent = new Intent(FormRequestCustomerAdmin.this, LoginActivity.class);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intentLogin, intentRegister;

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
