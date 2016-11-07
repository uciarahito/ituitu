package uci.develops.wiraenergimobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.fragment.FragmentInventory;
import uci.develops.wiraenergimobile.fragment.FragmentMasterSetup;
import uci.develops.wiraenergimobile.fragment.FragmentPurchasing;
import uci.develops.wiraenergimobile.fragment.FragmentReporting;
import uci.develops.wiraenergimobile.fragment.FragmentSales;

public class HomeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        setTitle(R.string.title_activity_home);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        /**
         * Jika dia seorang planner,maka akan ada 1 tab tambahan yaitu Pending
         * Pending merupakan delivery_order yang telah planner assign ke driver
         * namun driver belum mengconfirm delivery_order tersebut
         */

        adapter.addFragment(new FragmentMasterSetup(), "");
        adapter.addFragment(new FragmentPurchasing(), "");
        adapter.addFragment(new FragmentSales(), "");
        adapter.addFragment(new FragmentInventory(), "");
        adapter.addFragment(new FragmentReporting(), "");
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

    private void setupTabIcons() {
//        tabLayout.getTabAt(0).setIcon(R.drawable.ic_tab_setup);
//        tabLayout.getTabAt(1).setIcon(R.drawable.ic_tab_purchasing);
//        tabLayout.getTabAt(2).setIcon(R.drawable.ic_tab_sales);
//        tabLayout.getTabAt(3).setIcon(R.drawable.ic_tab_inventory);
//        tabLayout.getTabAt(4).setIcon(R.drawable.ic_tab_report);

        tabLayout.getTabAt(0).setText(R.string.master_setup);
        tabLayout.getTabAt(1).setText(R.string.purchasing);
        tabLayout.getTabAt(2).setText(R.string.sales);
        tabLayout.getTabAt(3).setText(R.string.inventory);
        tabLayout.getTabAt(4).setText(R.string.reporting);

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
//        if (mDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }

        switch (id){
            case R.id.action_login:
                intentLogin = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intentLogin);
                return true;
            case R.id.action_register:
                intentRegister = new Intent(HomeActivity.this, RegisterActivity.class);
                startActivity(intentRegister );
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

//        return super.onOptionsItemSelected(item);
    }

}
