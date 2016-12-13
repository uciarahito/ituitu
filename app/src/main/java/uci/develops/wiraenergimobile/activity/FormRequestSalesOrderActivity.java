package uci.develops.wiraenergimobile.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.adapter.CustomExpandableListAdapter;
import uci.develops.wiraenergimobile.fragment.navigation.NavigationManager;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.model.ExpandableListDataSource;
import uci.develops.wiraenergimobile.model.UserXModel;
import uci.develops.wiraenergimobile.response.CustomerResponse;
import uci.develops.wiraenergimobile.response.UserResponse;
import uci.develops.wiraenergimobile.service.RestClient;

public class FormRequestSalesOrderActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.linear_layout_title1) LinearLayout linearLayoutTitle1;
    @BindView(R.id.linear_layout_content1) LinearLayout linearLayoutContent1;
    @BindView(R.id.linear_layout_container_so_customer_detail) LinearLayout linearLayoutContainer1;
    @BindView(R.id.linear_layout_title2) LinearLayout linearLayoutTitle2;
    @BindView(R.id.linear_layout_content2) LinearLayout linearLayoutContent2;
    @BindView(R.id.linear_layout_container_so_shipping_address) LinearLayout linearLayoutContainer2;
    @BindView(R.id.linear_layout_title3) LinearLayout linearLayoutTitle3;
    @BindView(R.id.linear_layout_content3) LinearLayout linearLayoutContent3;
    @BindView(R.id.linear_layout_container_so_payment__pic_address) LinearLayout linearLayoutContainer3;
    @BindView(R.id.linear_layout_title4) LinearLayout linearLayoutTitle4;
    @BindView(R.id.linear_layout_content4) LinearLayout linearLayoutContent4;
    @BindView(R.id.linear_layout_container_so_item_so) LinearLayout linearLayoutContainer4;
    @BindView(R.id.linear_layout_button_cancel) LinearLayout linear_layout_button_cancel;
    @BindView(R.id.linear_layout_button_save) LinearLayout linear_layout_button_save;
    @BindView(R.id.editText_bruto) EditText editText_bruto;
    @BindView(R.id.editText_disc) EditText editText_disc;
    @BindView(R.id.editText_disc_value) EditText editText_disc_value;
    @BindView(R.id.editText_ppn) EditText editText_ppn;
    @BindView(R.id.editText_ppn_value) EditText editText_ppn_value;
    @BindView(R.id.editText_other_cost) EditText editText_other_cost;
    @BindView(R.id.editText_netto) EditText editText_netto;
    @BindView(R.id.editText_note) EditText editText_note;
    @BindView(R.id.textView_terbilang) TextView textView_terbilang;

    boolean content1=false, content2=false, content3=false, content4=false;

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
        setContentView(R.layout.activity_form_request_sales_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        initializeComponent();

        navDrawer();

        if (savedInstanceState == null) {
            selectFirstItemAsDefault();
        }

    }

    private void initializeComponent(){
        linearLayoutTitle1.setOnClickListener(this);
        linearLayoutTitle2.setOnClickListener(this);
        linearLayoutTitle3.setOnClickListener(this);
        linearLayoutTitle4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == linearLayoutTitle1){
            if(!content1){
                linearLayoutContent1.setVisibility(View.VISIBLE);
                linearLayoutContainer1.setVisibility(View.VISIBLE);
                content1=true;
            } else {
                linearLayoutContent1.setVisibility(View.GONE);
                linearLayoutContainer1.setVisibility(View.GONE);
                content1=false;
            }
        }

        if(v == linearLayoutTitle2){
            if(!content2){
                linearLayoutContent2.setVisibility(View.VISIBLE);
                linearLayoutContainer2.setVisibility(View.VISIBLE);
                content2=true;
            } else {
                linearLayoutContent2.setVisibility(View.GONE);
                linearLayoutContainer2.setVisibility(View.GONE);
                content2=false;
            }
        }

        if(v == linearLayoutTitle3){
            if(!content3){
                linearLayoutContent3.setVisibility(View.VISIBLE);
                linearLayoutContainer3.setVisibility(View.VISIBLE);
                content3=true;
            } else {
                linearLayoutContent3.setVisibility(View.GONE);
                linearLayoutContainer3.setVisibility(View.GONE);
                content3=false;
            }
        }

        if(v == linearLayoutTitle4){
            if(!content4){
                linearLayoutContent4.setVisibility(View.VISIBLE);
                linearLayoutContainer4.setVisibility(View.VISIBLE);
                content4=true;
            } else {
                linearLayoutContent4.setVisibility(View.GONE);
                linearLayoutContainer4.setVisibility(View.GONE);
                content4=false;
            }
        }
    }

    ProgressDialog progress_loading;

    public void showProgressLoading() {
        progress_loading = new ProgressDialog(FormRequestSalesOrderActivity.this);
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
                if (new SharedPreferenceManager().getPreferences(FormRequestSalesOrderActivity.this, "roles").equals("admin")) {
                    Intent intent = new Intent(FormRequestSalesOrderActivity.this, ListCustomerActivity.class);
                    startActivity(intent);
                } else if (new SharedPreferenceManager().getPreferences(FormRequestSalesOrderActivity.this, "roles").equals("customer")) {
                    Intent intent = new Intent(FormRequestSalesOrderActivity.this, FormCustomerActivity.class);
                    startActivity(intent);
                }
            }
        });

        Call<UserResponse> userResponseCall = RestClient.getRestClient().getUser("Bearer " + new
                        SharedPreferenceManager().getPreferences(FormRequestSalesOrderActivity.this, "token"),
                Integer.parseInt(new SharedPreferenceManager().getPreferences(FormRequestSalesOrderActivity.this, "user_id")));
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

        if (new SharedPreferenceManager().getPreferences(FormRequestSalesOrderActivity.this, "roles").equals("admin")) {
            rootMenu.add("Dashboard");
            rootMenu.add("Customer");
            rootMenu.add("Purchasing");
            rootMenu.add("Sales");
            rootMenu.add("Logout");
        } else if (new SharedPreferenceManager().getPreferences(FormRequestSalesOrderActivity.this, "roles").equals("customer")) {
            rootMenu.add("Dashboard");
            rootMenu.add("Profile");
            rootMenu.add("Sales");
            rootMenu.add("Logout");
        } else if (new SharedPreferenceManager().getPreferences(FormRequestSalesOrderActivity.this, "roles").equals("")) {
            rootMenu.add("Logout");
        } else if (new SharedPreferenceManager().getPreferences(FormRequestSalesOrderActivity.this, "roles").equals("expedition")) {
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
        items = ExpandableListDataSource.getArrayTitle(FormRequestSalesOrderActivity.this);
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

                Toast.makeText(FormRequestSalesOrderActivity.this, "" + selectedItem, Toast.LENGTH_SHORT).show();
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
                    Intent intent = new Intent(FormRequestSalesOrderActivity.this, PurchaseOrderActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Good Received [GR]")) {
                    Intent intent = new Intent(FormRequestSalesOrderActivity.this, GoodReceivedActivity.class);
                    startActivity(intent);
                }

                //utk menu sales
                if (selectedItem.equals("Quotation")) {
                    Intent intent = new Intent(FormRequestSalesOrderActivity.this, SalesQuotationAdminActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Sales Order [SO]")) {
                    Intent intent = new Intent(FormRequestSalesOrderActivity.this, SalesOrderActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Delivery Order [DO]")) {
                    Intent intent = new Intent(FormRequestSalesOrderActivity.this, DeliveryOrderActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Invoice")) {
                    Intent intent = new Intent(FormRequestSalesOrderActivity.this, InvoiceActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Payment")) {
                    Intent intent = new Intent(FormRequestSalesOrderActivity.this, PaymentActivity.class);
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
                if (new SharedPreferenceManager().getPreferences(FormRequestSalesOrderActivity.this, "roles").equals("admin")) {
                    selected_item = getResources().getStringArray(R.array.general)[groupPosition];
                } else if (new SharedPreferenceManager().getPreferences(FormRequestSalesOrderActivity.this, "roles").equals("customer")) {
                    selected_item = getResources().getStringArray(R.array.general_customer)[groupPosition];
                } else if (new SharedPreferenceManager().getPreferences(FormRequestSalesOrderActivity.this, "roles").equals("expedition")){
                    selected_item = getResources().getStringArray(R.array.general_expedition)[groupPosition];
                } else if (new SharedPreferenceManager().getPreferences(FormRequestSalesOrderActivity.this, "roles").equals("")){
                    selected_item = getResources().getStringArray(R.array.general_guest)[groupPosition];
                }

                if (selected_item.equals("Logout")) {
                    logout();
                } else if (selected_item.equals("Dashboard")) {
                    Intent intent = new Intent(FormRequestSalesOrderActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else if (selected_item.equals("Customer")) {
                    if (new SharedPreferenceManager().getPreferences(FormRequestSalesOrderActivity.this, "roles").equals("admin")) {
                        Intent intent = new Intent(FormRequestSalesOrderActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                } else if (selected_item.equals("Profile")) {
                    if (new SharedPreferenceManager().getPreferences(FormRequestSalesOrderActivity.this, "roles").equals("customer")) {
                        Intent intent = new Intent(FormRequestSalesOrderActivity.this, FormCustomerActivity.class);
                        startActivity(intent);
                    }
                }
                return false;
            }
        });
    }

    private void logout() {
        new SharedPreferenceManager().setPreferences(FormRequestSalesOrderActivity.this, "is_login", "");
        new SharedPreferenceManager().setPreferences(FormRequestSalesOrderActivity.this, "token", "");
        new SharedPreferenceManager().setPreferences(FormRequestSalesOrderActivity.this, "customer_decode", "");
        new SharedPreferenceManager().setPreferences(FormRequestSalesOrderActivity.this, "roles", "");

        Intent intent = new Intent(FormRequestSalesOrderActivity.this, LoginActivity.class);
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
