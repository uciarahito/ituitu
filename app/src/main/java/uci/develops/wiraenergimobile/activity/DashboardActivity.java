package uci.develops.wiraenergimobile.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.adapter.CustomExpandableListAdapter;
import uci.develops.wiraenergimobile.fragment.FragmentNavInventory;
import uci.develops.wiraenergimobile.fragment.FragmentNavMasterSetup;
import uci.develops.wiraenergimobile.fragment.FragmentNavPurchasing;
import uci.develops.wiraenergimobile.fragment.navigation.FragmentNavigationManager;
import uci.develops.wiraenergimobile.fragment.navigation.NavigationManager;
import uci.develops.wiraenergimobile.model.ExpandableListDataSource;

public class DashboardActivity extends AppCompatActivity{

    private TabLayout tabLayout;
    private ViewPager viewPager;

//    private LinearLayout linearLayout_container_1, linearLayout_container_2, linearLayout_container_3, linearLayout_container_4;
//    private LinearLayout linearLayout_tab_1, linearLayout_tab_2, linearLayout_tab_3, linearLayout_tab_4;
//    private LinearLayout[] linearLayouts_fragment = new LinearLayout[4];
//    private LinearLayout[] linearLayouts_tabs = new LinearLayout[4];
//
//    int index_fragment = 0;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private String[] items;

    private ExpandableListView mExpandableListView;
    private ExpandableListAdapter mExpandableListAdapter;
    private List<String> mExpandableListTitle;
    private NavigationManager mNavigationManager;

    private Map<String, List<String>> mExpandableListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        mExpandableListView = (ExpandableListView) mDrawerLayout.findViewById(R.id.navList);
//        mNavigationManager = FragmentNavigationManager.obtain(this);

        initItems();

        LayoutInflater inflater = getLayoutInflater();
        View listHeaderView;
        listHeaderView = inflater.inflate(R.layout.nav_header, null, false);
        mExpandableListView.addHeaderView(listHeaderView);

        mExpandableListData = ExpandableListDataSource.getData(this);
        mExpandableListTitle = new ArrayList(mExpandableListData.keySet());

        addDrawerItems();
        setupDrawer();

        if (savedInstanceState == null) {
            selectFirstItemAsDefault();
        }

//        initializeComponent();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentNavMasterSetup(), "ONE");
        adapter.addFragment(new FragmentNavPurchasing(), "TWO");
        adapter.addFragment(new FragmentNavInventory(), "THREE");
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

//    private void initializeComponent() {
//        linearLayout_container_1 = (LinearLayout) findViewById(R.id.layout_container_1);
//        linearLayout_container_2 = (LinearLayout) findViewById(R.id.layout_container_2);
//        linearLayout_container_3 = (LinearLayout) findViewById(R.id.layout_container_3);
//        linearLayout_container_4 = (LinearLayout) findViewById(R.id.layout_container_4);
//
//        linearLayout_tab_1 = (LinearLayout) findViewById(R.id.layout_tab_1);
//        linearLayout_tab_2 = (LinearLayout) findViewById(R.id.layout_tab_2);
//        linearLayout_tab_3 = (LinearLayout) findViewById(R.id.layout_tab_3);
//        linearLayout_tab_4 = (LinearLayout) findViewById(R.id.layout_tab_4);
//
//        linearLayouts_fragment[0] = linearLayout_container_1;
//        linearLayouts_fragment[1] = linearLayout_container_2;
//        linearLayouts_fragment[2] = linearLayout_container_3;
//        linearLayouts_fragment[3] = linearLayout_container_4;
//
//        linearLayouts_tabs[0] = linearLayout_tab_1;
//        linearLayouts_tabs[1] = linearLayout_tab_2;
//        linearLayouts_tabs[2] = linearLayout_tab_3;
//        linearLayouts_tabs[3] = linearLayout_tab_4;
//
//        linearLayouts_fragment[index_fragment].setVisibility(View.VISIBLE);
//    }

//    @Override
//    public void onClick(View v) {
//        if (v == linearLayout_tab_1) {
//            FragmentNavMasterSetup fragmentNavMasterSetup = (FragmentNavMasterSetup ) getSupportFragmentManager().findFragmentById(R.id.fragment_nav_master_setup);
//        } else if (v == linearLayout_tab_2) {
//            FragmentNavPurchasing fragmentNavPurchasing = (FragmentNavPurchasing) getSupportFragmentManager().findFragmentById(R.id.fragment_nav_purchase);
//        }
//    }

    private void selectFirstItemAsDefault() {
        if (mNavigationManager != null) {
            //String firstActionMovie = getResources().getStringArray(R.array.actionFilms)[0];
            //mNavigationManager.showFragmentAction(firstActionMovie);
            //getSupportActionBar().setTitle(firstActionMovie);
        }
    }

    private void initItems() {
        items = getResources().getStringArray(R.array.general);
    }

    private void addDrawerItems() {
        mExpandableListAdapter = new CustomExpandableListAdapter(this, mExpandableListTitle, mExpandableListData);
        mExpandableListView.setAdapter(mExpandableListAdapter);
        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                getSupportActionBar().setTitle(mExpandableListTitle.get(groupPosition).toString());
            }
        });

        mExpandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
//                getSupportActionBar().setTitle(R.string.film_genres);
            }
        });

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                String selectedItem = ((List) (mExpandableListData.get(mExpandableListTitle.get(groupPosition))))
                        .get(childPosition).toString();
                getSupportActionBar().setTitle(selectedItem);

//                if (items[0].equals(mExpandableListTitle.get(groupPosition))) {
//                    mNavigationManager.showFragmentNavMasterSetup(selectedItem);
//                } else if (items[1].equals(mExpandableListTitle.get(groupPosition))) {
//                    mNavigationManager.showFragmentNavPurchasing(selectedItem);
//                } else if (items[2].equals(mExpandableListTitle.get(groupPosition))) {
//                    mNavigationManager.showFragmentNavInventory(selectedItem);
//                } else if (items[3].equals(mExpandableListTitle.get(groupPosition))) {
//                    mNavigationManager.showFragmentNavSales(selectedItem);
//                } else if (items[4].equals(mExpandableListTitle.get(groupPosition))) {
//                    mNavigationManager.showFragmentNavReporting(selectedItem);
//                } else if (items[5].equals(mExpandableListTitle.get(groupPosition))) {
//                    mNavigationManager.showFragmentNavUtility(selectedItem);
//                } else {
//                    throw new IllegalArgumentException("Not supported fragment type");
//                }

                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                getSupportActionBar().setTitle(R.string.film_genres);
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

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (id){
            case R.id.action_login:
                intentLogin = new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(intentLogin);
                return true;
            case R.id.action_register:
                intentRegister = new Intent(DashboardActivity.this, RegisterActivity.class);
                startActivity(intentRegister );
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

//        return super.onOptionsItemSelected(item);
    }
}
