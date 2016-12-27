package uci.develops.wiraenergimobile.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.adapter.CustomExpandableListAdapter;
import uci.develops.wiraenergimobile.fragment.navigation.NavigationManager;
import uci.develops.wiraenergimobile.helper.NumberTextWatcher;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.model.ExpandableListDataSource;
import uci.develops.wiraenergimobile.model.UserXModel;
import uci.develops.wiraenergimobile.response.CustomerResponse;
import uci.develops.wiraenergimobile.response.UserResponse;
import uci.develops.wiraenergimobile.service.RestClient;

public class FormRequestSalesOrderActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.imageTitleUp1)
    ImageView imageTitleUp1;
    @BindView(R.id.imageTitleUp2)
    ImageView imageTitleUp2;
    @BindView(R.id.imageTitleUp3)
    ImageView imageTitleUp3;
    @BindView(R.id.imageTitleUp4)
    ImageView imageTitleUp4;
    @BindView(R.id.imageTitle1)
    ImageView imageViewTitle1;
    @BindView(R.id.imageTitle2)
    ImageView imageViewTitle2;
    @BindView(R.id.imageTitle3)
    ImageView imageViewTitle3;
    @BindView(R.id.imageTitle4)
    ImageView imageViewTitle4;
    @BindView(R.id.linear_layout_title1)
    LinearLayout linearLayoutTitle1;
    @BindView(R.id.linear_layout_content1)
    LinearLayout linearLayoutContent1;
    @BindView(R.id.linear_layout_container_so_customer_detail)
    LinearLayout linearLayoutContainer1;
    @BindView(R.id.linear_layout_title2)
    LinearLayout linearLayoutTitle2;
    @BindView(R.id.linear_layout_content2)
    LinearLayout linearLayoutContent2;
    @BindView(R.id.linear_layout_container_so_shipping_address)
    LinearLayout linearLayoutContainer2;
    @BindView(R.id.linear_layout_title3)
    LinearLayout linearLayoutTitle3;
    @BindView(R.id.linear_layout_content3)
    LinearLayout linearLayoutContent3;
    @BindView(R.id.linear_layout_container_so_payment_pic_address)
    LinearLayout linearLayoutContainer3;
    @BindView(R.id.linear_layout_title4)
    LinearLayout linearLayoutTitle4;
    @BindView(R.id.linear_layout_content4)
    LinearLayout linearLayoutContent4;
    @BindView(R.id.linear_layout_container_so_item_so)
    LinearLayout linearLayoutContainer4;
    @BindView(R.id.linear_layout_button_cancel)
    LinearLayout linear_layout_button_cancel;
    @BindView(R.id.linear_layout_button_save)
    LinearLayout linear_layout_button_save;
    @BindView(R.id.editText_bruto)
    EditText editText_bruto;
    @BindView(R.id.editText_disc)
    EditText editText_disc;
    @BindView(R.id.editText_disc_value)
    EditText editText_disc_value;
    @BindView(R.id.editText_ppn)
    EditText editText_ppn;
    @BindView(R.id.editText_ppn_value)
    EditText editText_ppn_value;
    @BindView(R.id.editText_other_cost)
    EditText editText_other_cost;
    @BindView(R.id.editText_netto)
    EditText editText_netto;
    @BindView(R.id.editText_note)
    EditText editText_note;
    @BindView(R.id.textView_terbilang)
    TextView textView_terbilang;

    //code utk customer detail
    @BindView(R.id.editText_so_code)
    EditText editText_so_code;
    @BindView(R.id.editText_so_date)
    EditText editText_so_date;
    @BindView(R.id.editText_qt_code)
    EditText editText_qt_code;
    @BindView(R.id.editText_po_code)
    EditText editText_po_code;
    @BindView(R.id.editText_delivery_date)
    EditText editText_delivery_date;
    @BindView(R.id.editText_payment_term)
    EditText editText_payment_term;
    @BindView(R.id.spinner_customer_name)
    Spinner spinner_customer_name;
    private DatePickerDialog datePickerDialogSODate, datePickerDialogDeliveryDate;
    List<CustomerModel> customerModelList;
    String check_List[];

    //code utk item sales order
    @BindView(R.id.editText_quantity) EditText editText_quantity;
    @BindView(R.id.editText_disc_item) EditText editText_disc_item;
    @BindView(R.id.editText_disc_amount) EditText editText_disc_amount;
    @BindView(R.id.editText_unit_price) EditText editText_unit_price;
    @BindView(R.id.editText_total_commission) EditText editText_total_commission;
    @BindView(R.id.editText_sub_total) EditText editText_sub_total;
    @BindView(R.id.editText_note_item) EditText editText_note_item;
    @BindView(R.id.spinner_unit) Spinner spinner_unit;
    @BindView(R.id.spinner_item) Spinner spinner_item;
    @BindView(R.id.imageViewTotalCommission) ImageView imageViewTotalCommission;

    boolean content1 = false, content2 = false, content3 = false, content4 = false;

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

    private void initializeComponent() {
        imageViewTitle1.setVisibility(View.VISIBLE);
        imageViewTitle2.setVisibility(View.VISIBLE);
        imageViewTitle3.setVisibility(View.VISIBLE);
        imageViewTitle4.setVisibility(View.VISIBLE);
//        editText_bruto.addTextChangedListener(new NumberTextWatcher(editText_bruto));
        linearLayoutTitle1.setOnClickListener(this);
        linearLayoutTitle2.setOnClickListener(this);
        linearLayoutTitle3.setOnClickListener(this);
        linearLayoutTitle4.setOnClickListener(this);

        List<String> listItem = new ArrayList<String>();
        listItem.add("Solar");
        listItem.add("Bensin");
        listItem.add("Pertamax");
        listItem.add("Barrel");
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(FormRequestSalesOrderActivity.this,
                R.layout.spinner_item, listItem);
        itemAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_item.setAdapter(itemAdapter);

        List<String> listUnit = new ArrayList<String>();
        listUnit.add("Liter");
        listUnit.add("Kilo Liter");
        listUnit.add("Mili Liter");
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<String>(FormRequestSalesOrderActivity.this,
                R.layout.spinner_item, listUnit);
        unitAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_unit.setAdapter(unitAdapter);

        imageViewTotalCommission.setOnClickListener(this);
        editText_so_date.setOnClickListener(this);
        editText_delivery_date.setOnClickListener(this);
        //        spinner_customer_name.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == linearLayoutTitle1) {
            if (!content1) {
                imageViewTitle1.setVisibility(View.GONE);
                imageTitleUp1.setVisibility(View.VISIBLE);
                linearLayoutContent1.setVisibility(View.VISIBLE);
                linearLayoutContainer1.setVisibility(View.VISIBLE);
                content1 = true;
            } else {
                imageViewTitle1.setVisibility(View.VISIBLE);
                imageTitleUp1.setVisibility(View.GONE);
                linearLayoutContent1.setVisibility(View.GONE);
                linearLayoutContainer1.setVisibility(View.GONE);
                content1 = false;
            }
        }

        if (v == linearLayoutTitle2) {
            if (!content2) {
                imageViewTitle2.setVisibility(View.GONE);
                imageTitleUp2.setVisibility(View.VISIBLE);
                linearLayoutContent2.setVisibility(View.VISIBLE);
                linearLayoutContainer2.setVisibility(View.VISIBLE);
                content2 = true;
            } else {
                imageViewTitle2.setVisibility(View.VISIBLE);
                imageTitleUp2.setVisibility(View.GONE);
                linearLayoutContent2.setVisibility(View.GONE);
                linearLayoutContainer2.setVisibility(View.GONE);
                content2 = false;
            }
        }

        if (v == linearLayoutTitle3) {
            if (!content3) {
                imageViewTitle3.setVisibility(View.GONE);
                imageTitleUp3.setVisibility(View.VISIBLE);
                linearLayoutContent3.setVisibility(View.VISIBLE);
                linearLayoutContainer3.setVisibility(View.VISIBLE);
                content3 = true;
            } else {
                imageViewTitle3.setVisibility(View.VISIBLE);
                imageTitleUp3.setVisibility(View.GONE);
                linearLayoutContent3.setVisibility(View.GONE);
                linearLayoutContainer3.setVisibility(View.GONE);
                content3 = false;
            }
        }

        if (v == linearLayoutTitle4) {
            if (!content4) {
                imageViewTitle4.setVisibility(View.GONE);
                imageTitleUp4.setVisibility(View.VISIBLE);
                linearLayoutContent4.setVisibility(View.VISIBLE);
                linearLayoutContainer4.setVisibility(View.VISIBLE);
                content4 = true;
            } else {
                imageViewTitle4.setVisibility(View.VISIBLE);
                imageTitleUp4.setVisibility(View.GONE);
                linearLayoutContent4.setVisibility(View.GONE);
                linearLayoutContainer4.setVisibility(View.GONE);
                content4 = false;
            }
        }

        if (v == editText_so_date) {
            setDatePickerDialogSODate();
        }

        if (v == editText_delivery_date) {
            setDatePickerDialogDeliveryDate();
        }

        if (v == imageViewTotalCommission){
            showCustomDialogFormSalesCommission();
        }

        //        if (v == spinner_customer_name){
//            loadDataSpinnerCustomerName();
//        }
    }

    public void setDatePickerDialogSODate() {
        //utk send date
        final Calendar calendarSODate = Calendar.getInstance();
        int mYear = calendarSODate.get(Calendar.YEAR); // current year
        int mMonth = calendarSODate.get(Calendar.MONTH); // current month
        int mDay = calendarSODate.get(Calendar.DAY_OF_MONTH); // current day
        // date picker dialog
        datePickerDialogSODate = new DatePickerDialog(FormRequestSalesOrderActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // set day of month , month and year value in the edit text
                editText_so_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            }
        }, mYear, mMonth, mDay);
        datePickerDialogSODate.show();
    }

    public void setDatePickerDialogDeliveryDate() {
        //utk send date
        final Calendar calendarDeliveryDate = Calendar.getInstance();
        int mYear = calendarDeliveryDate.get(Calendar.YEAR); // current year
        int mMonth = calendarDeliveryDate.get(Calendar.MONTH); // current month
        int mDay = calendarDeliveryDate.get(Calendar.DAY_OF_MONTH); // current day
        // date picker dialog
        datePickerDialogDeliveryDate = new DatePickerDialog(FormRequestSalesOrderActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // set day of month , month and year value in the edit text
                editText_delivery_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            }
        }, mYear, mMonth, mDay);
        datePickerDialogDeliveryDate.show();
    }

    EditText editText_so_code_commision, editText_hpp, editText_min_margin, editText_min_price, editText_price, editText_max_comm;
    TextView textView_total;
    Button button_add_salesman, button_cancel, button_save;
    boolean is_new_commision = false;
    private void showCustomDialogFormSalesCommission() {
        final Dialog dialog_form_sales_commission = new Dialog(FormRequestSalesOrderActivity.this);
        dialog_form_sales_commission.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_form_sales_commission.setContentView(R.layout.custom_dialog_form_sales_commission);

        editText_so_code_commision = ButterKnife.findById(dialog_form_sales_commission, R.id.editText_so_code_commision);
        editText_hpp = ButterKnife.findById(dialog_form_sales_commission, R.id.editText_hpp);
        editText_min_margin = ButterKnife.findById(dialog_form_sales_commission, R.id.editText_min_margin);
        editText_min_price = ButterKnife.findById(dialog_form_sales_commission, R.id.editText_min_price);
        editText_price = ButterKnife.findById(dialog_form_sales_commission, R.id.editText_price);
        editText_max_comm = ButterKnife.findById(dialog_form_sales_commission, R.id.editText_max_comm);
        textView_total = ButterKnife.findById(dialog_form_sales_commission, R.id.textView_total);
        button_add_salesman = ButterKnife.findById(dialog_form_sales_commission, R.id.button_add_salesman);
        button_cancel = ButterKnife.findById(dialog_form_sales_commission, R.id.button_cancel);
        button_save = ButterKnife.findById(dialog_form_sales_commission, R.id.button_save);

        button_add_salesman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is_new_commision = true;
//                dialog_form_sales_commission.dismiss();
                showCustomDialogAddSalesman();
            }
        });

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_form_sales_commission.dismiss();
            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_form_sales_commission.dismiss();
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialog_form_sales_commission.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog_form_sales_commission.setCancelable(false);
        dialog_form_sales_commission.show();
    }

    EditText editText_address, editText_phone, editText_mobile, editText_commission;
    Spinner spinner_salesman;
    private void showCustomDialogAddSalesman() {
        final Dialog dialog_add_salesman = new Dialog(FormRequestSalesOrderActivity.this);
        dialog_add_salesman.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_add_salesman.setContentView(R.layout.custom_dialog_add_salesman);

        editText_address = ButterKnife.findById(dialog_add_salesman, R.id.editText_address);
        editText_phone = ButterKnife.findById(dialog_add_salesman, R.id.editText_phone);
        editText_mobile = ButterKnife.findById(dialog_add_salesman, R.id.editText_mobile);
        editText_commission = ButterKnife.findById(dialog_add_salesman, R.id.editText_commission);
        spinner_salesman = ButterKnife.findById(dialog_add_salesman, R.id.spinner_salesman);
        button_cancel = ButterKnife.findById(dialog_add_salesman, R.id.button_cancel);
        button_save = ButterKnife.findById(dialog_add_salesman, R.id.button_save);

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_add_salesman.dismiss();
            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_add_salesman.dismiss();
            }
        });

        List<String> test_List = new ArrayList<String>();
        test_List.add("Testing 1");
        test_List.add("Testing 2");
        test_List.add("Testing 3");
        ArrayAdapter<String> testAdapter = new ArrayAdapter<String>(FormRequestSalesOrderActivity.this,
                R.layout.spinner_item, test_List);
        testAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_salesman.setAdapter(testAdapter);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialog_add_salesman.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog_add_salesman.setCancelable(false);
        dialog_add_salesman.show();
    }

    private void loadDataSpinnerCustomerName() {
        customerModelList = new ArrayList<>();
        Call<CustomerResponse> customerResponseCall = RestClient.getRestClient().getCustomer("Bearer "
                        + new SharedPreferenceManager().getPreferences(FormRequestSalesOrderActivity.this, "token"),
                new SharedPreferenceManager().getPreferences(FormRequestSalesOrderActivity.this, "customer_decode"));
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

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(FormRequestSalesOrderActivity.this,
                                R.layout.spinner_item, check_List);
                        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                        spinner_customer_name.setAdapter(dataAdapter);

                    }
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {

            }
        });
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
                } else if (new SharedPreferenceManager().getPreferences(FormRequestSalesOrderActivity.this, "roles").equals("expedition")) {
                    selected_item = getResources().getStringArray(R.array.general_expedition)[groupPosition];
                } else if (new SharedPreferenceManager().getPreferences(FormRequestSalesOrderActivity.this, "roles").equals("")) {
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
