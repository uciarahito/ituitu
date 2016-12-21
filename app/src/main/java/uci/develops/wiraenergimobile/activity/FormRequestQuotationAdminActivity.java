package uci.develops.wiraenergimobile.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
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
import uci.develops.wiraenergimobile.adapter.ItemSalesQuotationAdapter;
import uci.develops.wiraenergimobile.fragment.navigation.NavigationManager;
import uci.develops.wiraenergimobile.helper.DividerItemDecoration;
import uci.develops.wiraenergimobile.helper.NumberTextWatcher;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.library.SearchableSpinner;
import uci.develops.wiraenergimobile.model.CustomerAddressModel;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.model.ExpandableListDataSource;
import uci.develops.wiraenergimobile.model.QuotationModel;
import uci.develops.wiraenergimobile.model.UserXModel;
import uci.develops.wiraenergimobile.response.CustomerGroupResponse;
import uci.develops.wiraenergimobile.response.CustomerResponse;
import uci.develops.wiraenergimobile.response.ListCustomerAddressResponse;
import uci.develops.wiraenergimobile.response.RequestListCustomerResponse;
import uci.develops.wiraenergimobile.response.UserResponse;
import uci.develops.wiraenergimobile.service.RestClient;

public class FormRequestQuotationAdminActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.imageTitleUp1)
    ImageView imageTitleUp1;
    @BindView(R.id.imageTitleUp2)
    ImageView imageTitleUp2;
    @BindView(R.id.imageTitle1)
    ImageView imageViewTitle1;
    @BindView(R.id.imageTitle2)
    ImageView imageViewTitle2;
    @BindView(R.id.linear_layout_title1)
    LinearLayout linearLayoutTitle1;
    @BindView(R.id.linear_layout_title2)
    LinearLayout linearLayoutTitle2;
    @BindView(R.id.linear_layout_content1)
    LinearLayout linearLayoutContent1;
    @BindView(R.id.linear_layout_content2)
    LinearLayout linearLayoutContent2;
    @BindView(R.id.linear_layout_container_quotation_shipping_address)
    LinearLayout linearLayoutContainer1;
    @BindView(R.id.linear_layout_container_quotation_billing_address)
    LinearLayout linearLayoutContainer2;
    @BindView(R.id.linear_layout_button_cancel)
    LinearLayout linearLayoutButtonCancel;
    @BindView(R.id.linear_layout_button_send_quotation)
    LinearLayout linearLayoutButtonSendQuotation;
    @BindView(R.id.spinner_customer_name)
    SearchableSpinner spinner_customer_name;
    @BindView(R.id.spinner_project)
    Spinner spinner_project;
    @BindView(R.id.textView_total_qty)
    TextView textView_total_qty;
    @BindView(R.id.textView_terbilang)
    TextView textView_terbilang;
    @BindView(R.id.textView_group_customer)
    TextView textView_group_customer;
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
    @BindView(R.id.editText_admin_note)
    EditText editText_admin_note;
    @BindView(R.id.button_add_item)
    Button button_add_item;
    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;

    ItemSalesQuotationAdapter itemSalesQuotationAdapter;
    boolean content1 = false, content2 = false;
    List<CustomerModel> customerModelList;
    String check_List[];

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

    String token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_request_quotation_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        initializeComponent();

        token = new SharedPreferenceManager().getPreferences(FormRequestQuotationAdminActivity.this, "token");

        navDrawer();

        if (savedInstanceState == null) {
            selectFirstItemAsDefault();
        }
    }

    private void initializeComponent() {
        imageViewTitle1.setVisibility(View.VISIBLE);
        imageViewTitle2.setVisibility(View.VISIBLE);

        List<QuotationModel> quotationModelsList = new ArrayList<>();
        itemSalesQuotationAdapter = new ItemSalesQuotationAdapter(FormRequestQuotationAdminActivity.this, quotationModelsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(FormRequestQuotationAdminActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(FormRequestQuotationAdminActivity.this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(itemSalesQuotationAdapter);

        editText_bruto.addTextChangedListener(new NumberTextWatcher(editText_bruto));

        List<String> testItem = new ArrayList<String>();
        testItem.add("Testing1");
        testItem.add("Testing2");
        testItem.add("Testing3");
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(FormRequestQuotationAdminActivity.this,
                R.layout.spinner_item, testItem);
        itemAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_project.setAdapter(itemAdapter);

        loadDataSpinnerCustomer();

        linearLayoutTitle1.setOnClickListener(this);
        linearLayoutTitle2.setOnClickListener(this);
        button_add_item.setOnClickListener(this);
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

        if (v == button_add_item) {
            showDialogAddItem();
        }
    }

    List<CustomerModel> customerModels;

    private void loadDataSpinnerCustomer() {
        customerModels = new ArrayList<>();
        Call<RequestListCustomerResponse> requestListCustomerResponseCall = RestClient.getRestClient().getAllRequestCustomer("Bearer " + new SharedPreferenceManager().getPreferences(FormRequestQuotationAdminActivity.this, "token"));
        requestListCustomerResponseCall.enqueue(new Callback<RequestListCustomerResponse>() {
            @Override
            public void onResponse(Call<RequestListCustomerResponse> call, Response<RequestListCustomerResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getData().size() > 0) {
                        List<String> checkList = new ArrayList<String>();
                        final List<String> listIdGroup = new ArrayList<String>();
                        final List<String> decodeList = new ArrayList<String>();
                        for (CustomerModel customerModel : response.body().getData()) {
                            if (customerModel.getActive() == 1 && customerModel.getApprove() == 1) {
                                checkList.add(customerModel.getFirst_name());
                                listIdGroup.add(""+customerModel.getGroup());
                                decodeList.add(""+customerModel.getDecode());
                            }
                        }

                        customerModels = response.body().getData();

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(FormRequestQuotationAdminActivity.this,
                                R.layout.spinner_item, checkList);
                        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                        spinner_customer_name.setAdapter(dataAdapter);

                        spinner_customer_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                                new SharedPreferenceManager().setPreferences(FormRequestQuotationAdminActivity.this, "customer_decode", decodeList.get(position));
                                Intent pushNotification = new Intent("pushNotification");
                                pushNotification.putExtra("type", "load_data_spinner_shipping_address");
                                LocalBroadcastManager.getInstance(FormRequestQuotationAdminActivity.this).sendBroadcast(pushNotification);

                                Call<CustomerGroupResponse> customerGroupResponseCall = RestClient.getRestClient().getCustomerGroupByDecode("Bearer "+token, listIdGroup.get(position));
                                customerGroupResponseCall.enqueue(new Callback<CustomerGroupResponse>() {
                                    @Override
                                    public void onResponse(Call<CustomerGroupResponse> call, Response<CustomerGroupResponse> response) {
                                        if(response.isSuccessful()){
                                            textView_group_customer.setText("" + response.body().getData().getName());
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<CustomerGroupResponse> call, Throwable t) {

                                    }
                                });

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                    Toast.makeText(FormRequestQuotationAdminActivity.this, "Response sukses", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FormRequestQuotationAdminActivity.this, "Cek response tidak sukses", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RequestListCustomerResponse> call, Throwable t) {
                Toast.makeText(FormRequestQuotationAdminActivity.this, "Data Group Kosong", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void loadDataSpinnerCustomerName() {
//        customerModelList = new ArrayList<>();
//        Call<CustomerResponse> customerResponseCall = RestClient.getRestClient().getCustomer("Bearer "
//                        + new SharedPreferenceManager().getPreferences(FormRequestQuotationAdminActivity.this, "token"),
//                new SharedPreferenceManager().getPreferences(FormRequestQuotationAdminActivity.this, "customer_decode"));
//        customerResponseCall.enqueue(new Callback<CustomerResponse>() {
//            @Override
//            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
//                if (response.isSuccessful()) {
//                    if (response.body().getData().size() > 0) {
//                        check_List = new String[response.body().getData().size()];
//                        int index = 0;
//                        for (CustomerModel customerModel : response.body().getData()) {
//                            check_List[index] = customerModel.getFirst_name();
//                            index++;
//                        }
//
//                        customerModelList = response.body().getData();
//
//                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(FormRequestQuotationAdminActivity.this,
//                                R.layout.spinner_item, check_List);
//                        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
//                        spinner_customer_name.setAdapter(dataAdapter);
//
////                        spinner_customer_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
////                            @Override
////                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                                Toast.makeText(FormRequestQuotationAdminActivity.this, "" + check_List[position], Toast.LENGTH_SHORT).show();
//////                                editText_shipping_address.setText("" + customerAddressModels.get(position).getAddress());
//////                                editText_shipping_PIC.setText("" + customerAddressModels.get(position).getPic());
//////                                editText_shipping_phone.setText("" + customerAddressModels.get(position).getPhone());
//////                                editText_shipping_mobile.setText("" + customerAddressModels.get(position).getMobile());
////
////                            }
////
////                            @Override
////                            public void onNothingSelected(AdapterView<?> parent) {
////
////                            }
////                        });
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CustomerResponse> call, Throwable t) {
//
//            }
//        });
//    }

    //utk dialog add item
    private Spinner spinner_item, spinner_unit;
    private EditText editText_send_date, editText_quantity, editText_disc_item, editText_disc_amount, editText_unit_price, editText_sub_total;
    private DatePickerDialog datePickerDialog;
    private Button button_save, button_cancel;

    Dialog dialog_add_item;

    private void showDialogAddItem() {
        dialog_add_item = new Dialog(FormRequestQuotationAdminActivity.this);
        dialog_add_item.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_add_item.setContentView(R.layout.custom_dialog_form_req_item_quotation_admin);

        spinner_item = ButterKnife.findById(dialog_add_item, R.id.spinner_item);
        spinner_unit = ButterKnife.findById(dialog_add_item, R.id.spinner_unit);
        editText_send_date = ButterKnife.findById(dialog_add_item, R.id.editText_send_date);
        editText_quantity = ButterKnife.findById(dialog_add_item, R.id.editText_quantity);
        editText_disc_item = ButterKnife.findById(dialog_add_item, R.id.editText_disc1);
        editText_disc_amount = ButterKnife.findById(dialog_add_item, R.id.editText_disc_amount);
        editText_unit_price = ButterKnife.findById(dialog_add_item, R.id.editText_unit_price);
        editText_sub_total = ButterKnife.findById(dialog_add_item, R.id.editText_sub_total);
        button_save = ButterKnife.findById(dialog_add_item, R.id.button_save);
        button_cancel = ButterKnife.findById(dialog_add_item, R.id.button_cancel);

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
                if (new SharedPreferenceManager().getPreferences(FormRequestQuotationAdminActivity.this, "roles").equals("admin")) {
                    selected_item = getResources().getStringArray(R.array.general)[groupPosition];
                } else if (new SharedPreferenceManager().getPreferences(FormRequestQuotationAdminActivity.this, "roles").equals("customer")) {
                    selected_item = getResources().getStringArray(R.array.general_customer)[groupPosition];
                } else if (new SharedPreferenceManager().getPreferences(FormRequestQuotationAdminActivity.this, "roles").equals("expedition")) {
                    selected_item = getResources().getStringArray(R.array.general_expedition)[groupPosition];
                } else if (new SharedPreferenceManager().getPreferences(FormRequestQuotationAdminActivity.this, "roles").equals("")) {
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
                } else if (selected_item.equals("Profile")) {
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
