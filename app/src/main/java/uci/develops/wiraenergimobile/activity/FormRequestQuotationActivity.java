package uci.develops.wiraenergimobile.activity;

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
import uci.develops.wiraenergimobile.fragment.navigation.NavigationManager;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.ExpandableListDataSource;
import uci.develops.wiraenergimobile.model.UserXModel;
import uci.develops.wiraenergimobile.response.UserResponse;
import uci.develops.wiraenergimobile.service.RestClient;

public class FormRequestQuotationActivity extends AppCompatActivity implements View.OnClickListener {

    //utk tab fragment
    private LinearLayout layout_button_previous, layout_button_next;
    private LinearLayout layout_button_save_as_draft, layout_button_cancel, layout_button_send;
    private LinearLayout layout_container_shipping_address, layout_container_billPay_company_address, layout_container_billPay_shipping_address, layout_container_item_quotation, layout_container_note;
    private LinearLayout layout_tab_shipping_address, layout_tab_billPayment_company_address, layout_tab_billPayment_shipping_address, layout_tab_item_quotation, layout_tab_note;
    private LinearLayout[] linearLayouts_fragment = new LinearLayout[5];
    private LinearLayout[] linearLayouts_tabs = new LinearLayout[5];
    private TextView textView_button_previous, textView_button_save_as_draft, textView_button_cancel, textView_button_send, textView_button_next;

    int index_fragment = 0;

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
        setContentView(R.layout.activity_form_request_quotation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeComponent();

        navDrawer();

        if (savedInstanceState == null) {
            selectFirstItemAsDefault();
        }
    }

    private void initializeComponent() {
        layout_button_previous = (LinearLayout) findViewById(R.id.layout_button_previous);
        layout_button_next = (LinearLayout) findViewById(R.id.layout_button_next);
        layout_button_save_as_draft = (LinearLayout) findViewById(R.id.layout_button_save_as_draft);
        layout_button_cancel = (LinearLayout) findViewById(R.id.layout_button_cancel);
        layout_button_send = (LinearLayout) findViewById(R.id.layout_button_send);
        layout_container_shipping_address = (LinearLayout) findViewById(R.id.layout_container_shipping_address);
        layout_container_billPay_company_address = (LinearLayout) findViewById(R.id.layout_container_billPay_company_address);
        layout_container_billPay_shipping_address = (LinearLayout) findViewById(R.id.layout_container_billPay_shipping_address);
        layout_container_item_quotation = (LinearLayout) findViewById(R.id.layout_container_item_quotation);
        layout_container_note = (LinearLayout) findViewById(R.id.layout_container_note);
        layout_tab_shipping_address = (LinearLayout) findViewById(R.id.layout_tab_shipping_address);
        layout_tab_billPayment_company_address = (LinearLayout) findViewById(R.id.layout_tab_billPayment_company_address);
        layout_tab_billPayment_shipping_address = (LinearLayout) findViewById(R.id.layout_tab_billPayment_shippinh_address);
        layout_tab_item_quotation = (LinearLayout) findViewById(R.id.layout_tab_item_quotation);
        layout_tab_note = (LinearLayout) findViewById(R.id.layout_tab_note);

        linearLayouts_fragment[0] = layout_container_shipping_address;
        linearLayouts_fragment[1] = layout_container_billPay_company_address;
        linearLayouts_fragment[2] = layout_container_billPay_shipping_address;
        linearLayouts_fragment[3] = layout_container_item_quotation;
        linearLayouts_fragment[4] = layout_container_note;

        linearLayouts_tabs[0] = layout_tab_shipping_address;
        linearLayouts_tabs[1] = layout_tab_billPayment_company_address;
        linearLayouts_tabs[2] = layout_tab_billPayment_shipping_address;
        linearLayouts_tabs[3] = layout_tab_item_quotation;
        linearLayouts_tabs[4] = layout_tab_note;

        linearLayouts_fragment[index_fragment].setVisibility(View.VISIBLE);

        layout_button_previous.setOnClickListener(this);
        layout_button_next.setOnClickListener(this);
        layout_button_save_as_draft.setOnClickListener(this);
        layout_button_cancel.setOnClickListener(this);
        layout_button_send.setOnClickListener(this);

        layout_button_previous.setVisibility(View.INVISIBLE);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == layout_tab_shipping_address) {
            linearLayouts_fragment[0].setVisibility(View.VISIBLE);
            linearLayouts_fragment[1].setVisibility(View.GONE);
            linearLayouts_fragment[2].setVisibility(View.GONE);
            linearLayouts_fragment[3].setVisibility(View.GONE);
            linearLayouts_fragment[4].setVisibility(View.GONE);
        }
        if (v == layout_tab_billPayment_company_address) {
            linearLayouts_fragment[0].setVisibility(View.GONE);
            linearLayouts_fragment[1].setVisibility(View.VISIBLE);
            linearLayouts_fragment[2].setVisibility(View.GONE);
            linearLayouts_fragment[3].setVisibility(View.GONE);
            linearLayouts_fragment[4].setVisibility(View.GONE);
        }
        if (v == layout_tab_billPayment_shipping_address) {
            linearLayouts_fragment[0].setVisibility(View.GONE);
            linearLayouts_fragment[1].setVisibility(View.GONE);
            linearLayouts_fragment[2].setVisibility(View.VISIBLE);
            linearLayouts_fragment[3].setVisibility(View.GONE);
            linearLayouts_fragment[4].setVisibility(View.GONE);
        }
        if (v == layout_tab_item_quotation) {
            linearLayouts_fragment[0].setVisibility(View.GONE);
            linearLayouts_fragment[1].setVisibility(View.GONE);
            linearLayouts_fragment[2].setVisibility(View.GONE);
            linearLayouts_fragment[3].setVisibility(View.VISIBLE);
            linearLayouts_fragment[4].setVisibility(View.GONE);
        }
        if (v == layout_tab_note) {
            linearLayouts_fragment[0].setVisibility(View.GONE);
            linearLayouts_fragment[1].setVisibility(View.GONE);
            linearLayouts_fragment[2].setVisibility(View.GONE);
            linearLayouts_fragment[3].setVisibility(View.GONE);
            linearLayouts_fragment[4].setVisibility(View.VISIBLE);
        }

        if (v == layout_button_next) {
            if(index_fragment <=4) {
                for (int i = 0; i < 5; i++) {
                    if (index_fragment == i) {
                        linearLayouts_fragment[i].setVisibility(View.VISIBLE);
                    } else {
                        linearLayouts_fragment[i].setVisibility(View.GONE);
                    }
                }
                if (index_fragment == 0) {
                    layout_button_previous.setVisibility(View.VISIBLE);
                }

                if (index_fragment == 3) {
                    layout_button_next.setVisibility(View.GONE);
                    layout_button_cancel.setVisibility(View.VISIBLE);
                    layout_button_save_as_draft.setVisibility(View.VISIBLE);
                    layout_button_send.setVisibility(View.VISIBLE);
                }

                if (index_fragment < 4) {
                    index_fragment++;
                    layout_container_shipping_address.setVisibility(View.GONE);
                    layout_container_billPay_company_address.setVisibility(View.GONE);
                    layout_container_billPay_shipping_address.setVisibility(View.GONE);
                    layout_container_item_quotation.setVisibility(View.GONE);
                    layout_container_note.setVisibility(View.GONE);

                    layout_tab_shipping_address.setBackgroundResource(R.drawable.rounded_rectangle_dark_gray);
                    layout_tab_billPayment_company_address.setBackgroundResource(R.drawable.rounded_rectangle_dark_gray);
                    layout_tab_billPayment_shipping_address.setBackgroundResource(R.drawable.rounded_rectangle_dark_gray);
                    layout_tab_item_quotation.setBackgroundResource(R.drawable.rounded_rectangle_dark_gray);
                    layout_tab_note.setBackgroundResource(R.drawable.rounded_rectangle_dark_gray);
                    linearLayouts_tabs[index_fragment].setBackgroundResource(R.drawable.rounded_rectangle_org);
                    linearLayouts_fragment[index_fragment].setVisibility(View.VISIBLE);
                }
            }
        } else if (v == layout_button_previous) {
            if(index_fragment >=0) {
                if(index_fragment == 4){
                    layout_button_next.setVisibility(View.VISIBLE);
                    layout_button_cancel.setVisibility(View.GONE);
                    layout_button_save_as_draft.setVisibility(View.GONE);
                    layout_button_send.setVisibility(View.GONE);
                }
                if(index_fragment == 3){
                    layout_button_next.setVisibility(View.VISIBLE);
                }
                if(index_fragment == 2){
                    layout_button_next.setVisibility(View.VISIBLE);
                }
                if(index_fragment == 1){
                    layout_button_previous.setVisibility(View.GONE);
                }
                if(index_fragment == 0){
                    layout_button_next.setVisibility(View.VISIBLE);
                }

                for (int i = 0; i < 5; i++) {
                    if (index_fragment == i) {
                        linearLayouts_fragment[i].setVisibility(View.VISIBLE);
                    } else {
                        linearLayouts_fragment[i].setVisibility(View.GONE);
                    }
                }

                if (index_fragment > 0) {
                    index_fragment--;
                    layout_container_shipping_address.setVisibility(View.GONE);
                    layout_container_billPay_company_address.setVisibility(View.GONE);
                    layout_container_billPay_shipping_address.setVisibility(View.GONE);
                    layout_container_item_quotation.setVisibility(View.GONE);
                    layout_container_note.setVisibility(View.GONE);

                    layout_tab_shipping_address.setBackgroundResource(R.drawable.rounded_rectangle_dark_gray);
                    layout_tab_billPayment_company_address.setBackgroundResource(R.drawable.rounded_rectangle_dark_gray);
                    layout_tab_billPayment_shipping_address.setBackgroundResource(R.drawable.rounded_rectangle_dark_gray);
                    layout_tab_item_quotation.setBackgroundResource(R.drawable.rounded_rectangle_dark_gray);
                    layout_tab_note.setBackgroundResource(R.drawable.rounded_rectangle_dark_gray);
                    linearLayouts_tabs[index_fragment].setBackgroundResource(R.drawable.rounded_rectangle_org);
                    linearLayouts_fragment[index_fragment].setVisibility(View.VISIBLE);
                }
            }
        }
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
                if (new SharedPreferenceManager().getPreferences(FormRequestQuotationActivity.this, "roles").equals("admin")) {
                    Intent intent = new Intent(FormRequestQuotationActivity.this, ListCustomerActivity.class);
                    startActivity(intent);
                } else if (new SharedPreferenceManager().getPreferences(FormRequestQuotationActivity.this, "roles").equals("customer")) {
                    Intent intent = new Intent(FormRequestQuotationActivity.this, FormCustomerActivity.class);
                    startActivity(intent);
                }
            }
        });

        Call<UserResponse> userResponseCall = RestClient.getRestClient().getUser("Bearer " + new
                        SharedPreferenceManager().getPreferences(FormRequestQuotationActivity.this, "token"),
                Integer.parseInt(new SharedPreferenceManager().getPreferences(FormRequestQuotationActivity.this, "user_id")));
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

        if (new SharedPreferenceManager().getPreferences(FormRequestQuotationActivity.this, "roles").equals("admin")) {
            rootMenu.add("Dashboard");
            rootMenu.add("Customer");
            rootMenu.add("Purchasing");
            rootMenu.add("Sales");
            rootMenu.add("Logout");
        } else if (new SharedPreferenceManager().getPreferences(FormRequestQuotationActivity.this, "roles").equals("customer")) {
            rootMenu.add("Dashboard");
            rootMenu.add("Customer");
            rootMenu.add("Sales");
            rootMenu.add("Logout");
        } else {
            rootMenu.add("Dashboard");
            rootMenu.add("Logout");
        }
        mExpandableListTitle = rootMenu;

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initItems() {
        items = ExpandableListDataSource.getArrayTitle(FormRequestQuotationActivity.this);
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

                Toast.makeText(FormRequestQuotationActivity.this, "" + selectedItem, Toast.LENGTH_SHORT).show();
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
                    Intent intent = new Intent(FormRequestQuotationActivity.this, PurchaseOrderActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Good Received [GR]")) {
                    Intent intent = new Intent(FormRequestQuotationActivity.this, GoodReceivedActivity.class);
                    startActivity(intent);
                }

                //utk menu sales
                if (selectedItem.equals("Quotation")) {
                    Intent intent = new Intent(FormRequestQuotationActivity.this, SalesQuotationActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Sales Order [SO]")) {
                    Intent intent = new Intent(FormRequestQuotationActivity.this, SalesOrderActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Delivery Order [DO]")) {
                    Intent intent = new Intent(FormRequestQuotationActivity.this, DeliveryOrderActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Invoice")) {
                    Intent intent = new Intent(FormRequestQuotationActivity.this, InvoiceActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Payment")) {
                    Intent intent = new Intent(FormRequestQuotationActivity.this, PaymentActivity.class);
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
                if (new SharedPreferenceManager().getPreferences(FormRequestQuotationActivity.this, "roles").equals("admin")) {
                    selected_item = getResources().getStringArray(R.array.general)[groupPosition];
                } else if (new SharedPreferenceManager().getPreferences(FormRequestQuotationActivity.this, "roles").equals("customer")) {
                    selected_item = getResources().getStringArray(R.array.general_customer)[groupPosition];
                } else {
                    selected_item = getResources().getStringArray(R.array.general_expedition)[groupPosition];
                }

                if (selected_item.equals("Logout")) {
                    logout();
                } else if (selected_item.equals("Dashboard")) {
                    Intent intent = new Intent(FormRequestQuotationActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else if (selected_item.equals("Customer")) {
                    if (new SharedPreferenceManager().getPreferences(FormRequestQuotationActivity.this, "roles").equals("admin")) {
                        Intent intent = new Intent(FormRequestQuotationActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                }
                return false;
            }
        });
    }

    private void logout() {
        new SharedPreferenceManager().setPreferences(FormRequestQuotationActivity.this, "is_login", "");
        new SharedPreferenceManager().setPreferences(FormRequestQuotationActivity.this, "token", "");
        new SharedPreferenceManager().setPreferences(FormRequestQuotationActivity.this, "customer_decode", "");
        new SharedPreferenceManager().setPreferences(FormRequestQuotationActivity.this, "roles", "");

        Intent intent = new Intent(FormRequestQuotationActivity.this, LoginActivity.class);
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
