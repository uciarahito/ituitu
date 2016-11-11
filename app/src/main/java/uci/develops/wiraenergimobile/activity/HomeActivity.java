package uci.develops.wiraenergimobile.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
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
import uci.develops.wiraenergimobile.fragment.FragmentCustomer;
import uci.develops.wiraenergimobile.fragment.FragmentPurchasing;
import uci.develops.wiraenergimobile.fragment.FragmentSales;
import uci.develops.wiraenergimobile.fragment.navigation.FragmentNavigationManager;
import uci.develops.wiraenergimobile.fragment.navigation.NavigationManager;
import uci.develops.wiraenergimobile.helper.Constant;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.model.ExpandableListDataSource;
import uci.develops.wiraenergimobile.model.UserXModel;
import uci.develops.wiraenergimobile.response.UserResponse;
import uci.develops.wiraenergimobile.service.RestClient;

public class HomeActivity extends AppCompatActivity {

    //utk nav header
    ImageView imageView_profile;
    TextView textView_name;

    private TabLayout tabLayout;
    private ViewPager viewPager;
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
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        mExpandableListView = (ExpandableListView) mDrawerLayout.findViewById(R.id.navList);
        mNavigationManager = FragmentNavigationManager.obtain(this);

        initItems();

        LayoutInflater inflater = getLayoutInflater();
        View listHeaderView;
        listHeaderView = inflater.inflate(R.layout.nav_header, null, false);
        mExpandableListView.addHeaderView(listHeaderView);

        imageView_profile = (ImageView) listHeaderView.findViewById(R.id.imageView_profile);
        textView_name = (TextView) listHeaderView.findViewById(R.id.textView_name);

        imageView_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new SharedPreferenceManager().getPreferences(HomeActivity.this, "roles").equals("admin")) {
                    Intent intent = new Intent(HomeActivity.this, ListCustomerActivity.class);
                    startActivity(intent);
                } else if (new SharedPreferenceManager().getPreferences(HomeActivity.this, "roles").equals("customer")) {
                    Intent intent = new Intent(HomeActivity.this, FormCustomerActivity.class);
                    startActivity(intent);
                }
            }
        });

        try {
            Call<UserResponse> userResponseCall = RestClient.getRestClient().getUser("Bearer " + new
                    SharedPreferenceManager().getPreferences(HomeActivity.this, "token"), Integer.parseInt(new SharedPreferenceManager().getPreferences(HomeActivity.this, "user_id")));
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
        } catch (Exception e){
            logout();
        }

        mExpandableListData = ExpandableListDataSource.getData(this);
        List<String> rootMenu = new ArrayList<>();
        if (new SharedPreferenceManager().getPreferences(HomeActivity.this, "roles").equals("admin")) {
            rootMenu.add("Dashboard");
            rootMenu.add("Customer");
            rootMenu.add("Purchasing");
            rootMenu.add("Sales");
            rootMenu.add("Logout");
        } else if (new SharedPreferenceManager().getPreferences(HomeActivity.this, "roles").equals("customer")) {
            rootMenu.add("Dashboard");
            rootMenu.add("Customer");
            rootMenu.add("Sales");
            rootMenu.add("Logout");
        } else {
            rootMenu.add("Dashboard");
            rootMenu.add("Logout");
        }
        mExpandableListTitle = rootMenu;

        setupTabIcons();
        addDrawerItems();
        setupDrawer();

        if (savedInstanceState == null) {
            selectFirstItemAsDefault();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        /**
         * Jika dia seorang planner,maka akan ada 1 tab tambahan yaitu Pending
         * Pending merupakan delivery_order yang telah planner assign ke driver
         * namun driver belum mengconfirm delivery_order tersebut
         */

        String roles = "";
        try {
            roles = new SharedPreferenceManager().getPreferences(HomeActivity.this, "roles");
        } catch (Exception e) {

        }
        if (roles != "" && roles.equals("admin")) {
            adapter.addFragment(new FragmentCustomer(), "");
            adapter.addFragment(new FragmentPurchasing(), "");
            adapter.addFragment(new FragmentSales(), "");
        } else if (roles != "" && roles.equals("customer")) {
            adapter.addFragment(new FragmentCustomer(), "");
            adapter.addFragment(new FragmentSales(), "");
        } else {
            adapter.addFragment(new FragmentSales(), "");
        }

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
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

                Toast.makeText(HomeActivity.this, "" + selectedItem, Toast.LENGTH_SHORT).show();
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
                    Intent intent = new Intent(HomeActivity.this, PurchaseOrderActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Good Received [GR]")) {
                    Intent intent = new Intent(HomeActivity.this, GoodReceivedActivity.class);
                    startActivity(intent);
                }

                //utk menu sales
                if (selectedItem.equals("Quotation")) {
                    Intent intent = new Intent(HomeActivity.this, SalesQuotationActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Sales Order [SO]")) {
                    Intent intent = new Intent(HomeActivity.this, SalesOrderActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Delivery Order [DO]")) {
                    Intent intent = new Intent(HomeActivity.this, DeliveryOrderActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Invoice")) {
                    Intent intent = new Intent(HomeActivity.this, InvoiceActivity.class);
                    startActivity(intent);
                } else if (selectedItem.equals("Payment")) {
                    Intent intent = new Intent(HomeActivity.this, PaymentActivity.class);
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
                    Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else if (selected_item.equals("Customer")) {
                    if (new SharedPreferenceManager().getPreferences(HomeActivity.this, "roles").equals("admin")) {
                        Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(HomeActivity.this, FormCustomerActivity.class);
                        startActivity(intent);
                    }
                }
                return false;
            }
        });
    }

    private void logout() {
        new SharedPreferenceManager().setPreferences(HomeActivity.this, "is_login", "");
        new SharedPreferenceManager().setPreferences(HomeActivity.this, "token", "");
        new SharedPreferenceManager().setPreferences(HomeActivity.this, "customer_decode", "");
        new SharedPreferenceManager().setPreferences(HomeActivity.this, "roles", "");

        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void setupTabIcons() {
//        tabLayout.getTabAt(0).setIcon(R.drawable.ic_tab_setup);
//        tabLayout.getTabAt(1).setIcon(R.drawable.ic_tab_purchasing);
//        tabLayout.getTabAt(2).setIcon(R.drawable.ic_tab_sales);
//        tabLayout.getTabAt(3).setIcon(R.drawable.ic_tab_inventory);
//        tabLayout.getTabAt(4).setIcon(R.drawable.ic_tab_report);

        String roles = "";
        try {
            roles = new SharedPreferenceManager().getPreferences(HomeActivity.this, "roles");
        } catch (Exception e) {

        }
        if (roles != "" && roles.equals("admin")) {
            tabLayout.getTabAt(0).setText(R.string.tab_customer);
            tabLayout.getTabAt(1).setText(R.string.tab_purchasing);
            tabLayout.getTabAt(2).setText(R.string.tab_sales);
        } else if (roles != "" && roles.equals("customer")) {
            tabLayout.getTabAt(0).setText(R.string.tab_customer);
            tabLayout.getTabAt(1).setText(R.string.tab_sales);
        } else if (roles != "" && roles.equals("expedition")) {
            tabLayout.getTabAt(0).setText(R.string.tab_sales);
        }
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

    private void selectFirstItemAsDefault() {
        if (mNavigationManager != null) {
            //String firstActionMovie = getResources().getStringArray(R.array.actionFilms)[0];
            //mNavigationManager.showFragmentAction(firstActionMovie);
            //getSupportActionBar().setTitle(firstActionMovie);
        }
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
        return super.onOptionsItemSelected(item);
//        }
    }

    private void initItems() {
        items = ExpandableListDataSource.getArrayTitle(HomeActivity.this);
    }

}
