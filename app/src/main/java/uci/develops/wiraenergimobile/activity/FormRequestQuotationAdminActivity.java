package uci.develops.wiraenergimobile.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.adapter.CustomExpandableListAdapter;
import uci.develops.wiraenergimobile.adapter.ItemSalesQuotationAdapter;
import uci.develops.wiraenergimobile.fragment.navigation.NavigationManager;
import uci.develops.wiraenergimobile.helper.DividerItemDecoration;
import uci.develops.wiraenergimobile.helper.NumberTextWatcher;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.model.ExpandableListDataSource;
import uci.develops.wiraenergimobile.model.QuotationModel;
import uci.develops.wiraenergimobile.model.UserXModel;
import uci.develops.wiraenergimobile.response.CustomerResponse;
import uci.develops.wiraenergimobile.response.UserResponse;
import uci.develops.wiraenergimobile.service.RestClient;

public class FormRequestQuotationAdminActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout linearLayoutTitle1, linearLayoutTitle2;
    private LinearLayout linearLayoutContent1, linearLayoutContent2;
    private LinearLayout linearLayoutContainer1, linearLayoutContainer2;
    private LinearLayout linearLayoutButtonCancel, linearLayoutButtonSendQuotation;
    private Spinner spinner_customer_name;
    private TextView textView_total_qty, textView_terbilang;
    private EditText editText_bruto, editText_disc, editText_disc_value, editText_ppn, editText_ppn_value, editText_other_cost, editText_netto, editText_admin_note;
    boolean content1=false, content2=false;
    List<CustomerModel> customerModelList;
    String check_List[];

    //utk dialog add item
    private Spinner spinner_item, spinner_unit;
    private EditText editText_send_date, editText_quantity, editText_disc1, editText_disc_amount, editText_unit_price, editText_sub_total;
    private DatePickerDialog datePickerDialog;
    private Button button_add_item, button_save, button_cancel;
    private RecyclerView recyclerView;
    ItemSalesQuotationAdapter itemSalesQuotationAdapter;

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
        setContentView(R.layout.activity_form_request_quotation_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeComponent();

        navDrawer();

        if (savedInstanceState == null) {
            selectFirstItemAsDefault();
        }
    }

    private void initializeComponent(){
        spinner_customer_name = (Spinner) findViewById(R.id.spinner_customer_name);
        button_add_item = (Button) findViewById(R.id.button_add_item);
        textView_terbilang = (TextView) findViewById(R.id.textView_terbilang);
        textView_total_qty = (TextView) findViewById(R.id.textView_total_qty);
        editText_bruto = (EditText) findViewById(R.id.editText_bruto);
        editText_disc = (EditText) findViewById(R.id.editText_disc);
        editText_disc_value = (EditText) findViewById(R.id.editText_disc_value);
        editText_ppn = (EditText) findViewById(R.id.editText_ppn);
        editText_ppn_value = (EditText) findViewById(R.id.editText_ppn_value);
        editText_other_cost = (EditText) findViewById(R.id.editText_other_cost);
        editText_netto = (EditText) findViewById(R.id.editText_netto);
        editText_admin_note = (EditText) findViewById(R.id.editText_admin_note);
        linearLayoutTitle1 = (LinearLayout)findViewById(R.id.linear_layout_title1);
        linearLayoutTitle2 = (LinearLayout)findViewById(R.id.linear_layout_title2);
        linearLayoutContent1 = (LinearLayout)findViewById(R.id.linear_layout_content1);
        linearLayoutContent2 = (LinearLayout)findViewById(R.id.linear_layout_content2);
        linearLayoutContainer1 = (LinearLayout)findViewById(R.id.linear_layout_container_quotation_shipping_address);
        linearLayoutContainer2 = (LinearLayout)findViewById(R.id.linear_layout_container_quotation_billing_address);
        linearLayoutButtonCancel = (LinearLayout)findViewById(R.id.linear_layout_button_cancel);
        linearLayoutButtonSendQuotation = (LinearLayout)findViewById(R.id.linear_layout_button_send_quotation);

        recyclerView = (RecyclerView)findViewById(R.id.recycle_view);
        List<QuotationModel> quotationModelsList = new ArrayList<>();
        itemSalesQuotationAdapter = new ItemSalesQuotationAdapter(FormRequestQuotationAdminActivity.this, quotationModelsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(FormRequestQuotationAdminActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(FormRequestQuotationAdminActivity.this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(itemSalesQuotationAdapter);

        editText_bruto.addTextChangedListener(new NumberTextWatcher(editText_bruto));

        linearLayoutTitle1.setOnClickListener(this);
        linearLayoutTitle2.setOnClickListener(this);
        button_add_item.setOnClickListener(this);
//        spinner_customer_name.setOnClickListener(this);
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

        if (v == button_add_item){
            showDialogAddItem();
        }

//        if (v == spinner_customer_name){
//            loadDataSpinnerCustomerName();
//        }
    }

    private void loadDataSpinnerCustomerName() {
        customerModelList = new ArrayList<>();
        Call<CustomerResponse> customerResponseCall = RestClient.getRestClient().getCustomer("Bearer "
                        + new SharedPreferenceManager().getPreferences(FormRequestQuotationAdminActivity.this, "token"),
                new SharedPreferenceManager().getPreferences(FormRequestQuotationAdminActivity.this, "customer_decode"));
        customerResponseCall.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getData().size() > 0) {
                        check_List = new String[response.body().getData().size()];
                        int index = 0;
                        for (CustomerModel customerModel : response.body().getData()) {
                            check_List[index] = customerModel.getFirst_name();
                            index++;
                        }

                        customerModelList = response.body().getData();

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(FormRequestQuotationAdminActivity.this,
                                R.layout.spinner_item, check_List);
                        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                        spinner_customer_name.setAdapter(dataAdapter);

//                        spinner_customer_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                            @Override
//                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                                Toast.makeText(FormRequestQuotationAdminActivity.this, "" + check_List[position], Toast.LENGTH_SHORT).show();
////                                editText_shipping_address.setText("" + customerAddressModels.get(position).getAddress());
////                                editText_shipping_PIC.setText("" + customerAddressModels.get(position).getPic());
////                                editText_shipping_phone.setText("" + customerAddressModels.get(position).getPhone());
////                                editText_shipping_mobile.setText("" + customerAddressModels.get(position).getMobile());
//
//                            }
//
//                            @Override
//                            public void onNothingSelected(AdapterView<?> parent) {
//
//                            }
//                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {

            }
        });
    }

    Dialog dialog_add_item;
    private void showDialogAddItem() {
        dialog_add_item = new Dialog(FormRequestQuotationAdminActivity.this);
        dialog_add_item.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_add_item.setContentView(R.layout.custom_dialog_form_req_item_quotation_admin);

        spinner_item = (Spinner) dialog_add_item.findViewById(R.id.spinner_item);
        spinner_unit = (Spinner) dialog_add_item.findViewById(R.id.spinner_unit);
        editText_send_date = (EditText) dialog_add_item.findViewById(R.id.editText_send_date);
        editText_quantity = (EditText) dialog_add_item.findViewById(R.id.editText_quantity);
        editText_disc1 = (EditText) dialog_add_item.findViewById(R.id.editText_disc1);
        editText_disc_amount = (EditText) dialog_add_item.findViewById(R.id.editText_disc_amount);
        editText_unit_price = (EditText) dialog_add_item.findViewById(R.id.editText_unit_price);
        editText_sub_total = (EditText) dialog_add_item.findViewById(R.id.editText_sub_total);
        button_save = (Button) dialog_add_item.findViewById(R.id.button_save);
        button_cancel = (Button) dialog_add_item.findViewById(R.id.button_cancel);

        List<String> listItem = new ArrayList<String>();
        listItem.add("PCS");
        listItem.add("Militer");
        listItem.add("Liter");
        listItem.add("Barrel");
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(FormRequestQuotationAdminActivity.this,
                R.layout.spinner_item, listItem);
        itemAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_item.setAdapter(itemAdapter);

        List<String> listUnit = new ArrayList<String>();
        listUnit.add("Kertas A4 Putih");
        listUnit.add("Tinta Epson");
        listUnit.add("Laptop Asus");
        listUnit.add("Solar");
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<String>(FormRequestQuotationAdminActivity.this,
                R.layout.spinner_item, listUnit);
        unitAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_unit.setAdapter(unitAdapter);

        editText_send_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //utk send date
                final Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR); // current year
                int mMonth = calendar.get(Calendar.MONTH); // current month
                int mDay = calendar.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(FormRequestQuotationAdminActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        editText_send_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_add_item.dismiss();
            }
        });

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_add_item.dismiss();
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) FormRequestQuotationAdminActivity.this.getSystemService(FormRequestQuotationAdminActivity.this.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialog_add_item.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog_add_item.setCancelable(true);
        dialog_add_item.show();
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
                if (new SharedPreferenceManager().getPreferences(FormRequestQuotationAdminActivity.this, "roles").equals("admin")) {
                    Intent intent = new Intent(FormRequestQuotationAdminActivity.this, ListCustomerActivity.class);
                    startActivity(intent);
                } else if (new SharedPreferenceManager().getPreferences(FormRequestQuotationAdminActivity.this, "roles").equals("customer")) {
                    Intent intent = new Intent(FormRequestQuotationAdminActivity.this, FormCustomerActivity.class);
                    startActivity(intent);
                }
            }
        });

        Call<UserResponse> userResponseCall = RestClient.getRestClient().getUser("Bearer " + new
                        SharedPreferenceManager().getPreferences(FormRequestQuotationAdminActivity.this, "token"),
                Integer.parseInt(new SharedPreferenceManager().getPreferences(FormRequestQuotationAdminActivity.this, "user_id")));
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

        if (new SharedPreferenceManager().getPreferences(FormRequestQuotationAdminActivity.this, "roles").equals("admin")) {
            rootMenu.add("Dashboard");
            rootMenu.add("Customer");
            rootMenu.add("Purchasing");
            rootMenu.add("Sales");
            rootMenu.add("Logout");
        } else if (new SharedPreferenceManager().getPreferences(FormRequestQuotationAdminActivity.this, "roles").equals("customer")) {
            rootMenu.add("Dashboard");
            rootMenu.add("Profile");
            rootMenu.add("Sales");
            rootMenu.add("Logout");
        } else if (new SharedPreferenceManager().getPreferences(FormRequestQuotationAdminActivity.this, "roles").equals("")) {
            rootMenu.add("Logout");
        } else if (new SharedPreferenceManager().getPreferences(FormRequestQuotationAdminActivity.this, "roles").equals("expedition")) {
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
        items = ExpandableListDataSource.getArrayTitle(FormRequestQuotationAdminActivity.this);
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

                Toast.makeText(FormRequestQuotationAdminActivity.this, "" + selectedItem, Toast.LENGTH_SHORT).show();
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
                    Intent intent = new Intent(FormRequestQuotationAdminActivity.this, PurchaseOrderActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Good Received [GR]")) {
                    Intent intent = new Intent(FormRequestQuotationAdminActivity.this, GoodReceivedActivity.class);
                    startActivity(intent);
                }

                //utk menu sales
                if (selectedItem.equals("Quotation")) {
                    Intent intent = new Intent(FormRequestQuotationAdminActivity.this, SalesQuotationAdminActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Sales Order [SO]")) {
                    Intent intent = new Intent(FormRequestQuotationAdminActivity.this, SalesOrderActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Delivery Order [DO]")) {
                    Intent intent = new Intent(FormRequestQuotationAdminActivity.this, DeliveryOrderActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Invoice")) {
                    Intent intent = new Intent(FormRequestQuotationAdminActivity.this, InvoiceActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Payment")) {
                    Intent intent = new Intent(FormRequestQuotationAdminActivity.this, PaymentActivity.class);
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
                if(new SharedPreferenceManager().getPreferences(FormRequestQuotationAdminActivity.this, "roles").equals("admin")){
                    selected_item = getResources().getStringArray(R.array.general)[groupPosition];
                } else if(new SharedPreferenceManager().getPreferences(FormRequestQuotationAdminActivity.this, "roles").equals("customer")){
                    selected_item = getResources().getStringArray(R.array.general_customer)[groupPosition];
                } else if (new SharedPreferenceManager().getPreferences(FormRequestQuotationAdminActivity.this, "roles").equals("expedition")){
                    selected_item = getResources().getStringArray(R.array.general_expedition)[groupPosition];
                } else if (new SharedPreferenceManager().getPreferences(FormRequestQuotationAdminActivity.this, "roles").equals("")){
                    selected_item = getResources().getStringArray(R.array.general_guest)[groupPosition];
                }

                if (selected_item.equals("Logout")) {
                    logout();
                } else if (selected_item.equals("Dashboard")) {
                    Intent intent = new Intent(FormRequestQuotationAdminActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else if (selected_item.equals("Customer")) {
                    if (new SharedPreferenceManager().getPreferences(FormRequestQuotationAdminActivity.this, "roles").equals("admin")) {
                        Intent intent = new Intent(FormRequestQuotationAdminActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                }  else if (selected_item.equals("Profile")) {
                    if (new SharedPreferenceManager().getPreferences(FormRequestQuotationAdminActivity.this, "roles").equals("customer")) {
                        Intent intent = new Intent(FormRequestQuotationAdminActivity.this, FormCustomerActivity.class);
                        startActivity(intent);
                    }
                }
                return false;
            }
        });
    }

    private void logout() {
        new SharedPreferenceManager().setPreferences(FormRequestQuotationAdminActivity.this, "is_login", "");
        new SharedPreferenceManager().setPreferences(FormRequestQuotationAdminActivity.this, "token", "");
        new SharedPreferenceManager().setPreferences(FormRequestQuotationAdminActivity.this, "customer_decode", "");
        new SharedPreferenceManager().setPreferences(FormRequestQuotationAdminActivity.this, "roles", "");

        Intent intent = new Intent(FormRequestQuotationAdminActivity.this, LoginActivity.class);
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
