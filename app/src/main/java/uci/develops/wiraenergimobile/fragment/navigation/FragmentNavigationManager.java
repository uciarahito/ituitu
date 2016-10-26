package uci.develops.wiraenergimobile.fragment.navigation;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import uci.develops.wiraenergimobile.BuildConfig;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.activity.DashboardActivity;
import uci.develops.wiraenergimobile.fragment.FragmentNavInventory;
import uci.develops.wiraenergimobile.fragment.FragmentNavMasterSetup;
import uci.develops.wiraenergimobile.fragment.FragmentNavPurchasing;
import uci.develops.wiraenergimobile.fragment.FragmentNavReporting;
import uci.develops.wiraenergimobile.fragment.FragmentNavSales;
import uci.develops.wiraenergimobile.fragment.FragmentNavUtility;

/**
 * @author msahakyan
 */

public class FragmentNavigationManager implements NavigationManager {

    private static FragmentNavigationManager sInstance;

    private FragmentManager mFragmentManager;
    private DashboardActivity mActivity;

    public static FragmentNavigationManager obtain(DashboardActivity activity) {
        if (sInstance == null) {
            sInstance = new FragmentNavigationManager();
        }
        sInstance.configure(activity);
        return sInstance;
    }

    private void configure(DashboardActivity activity) {
        mActivity = activity;
        mFragmentManager = mActivity.getSupportFragmentManager();
    }

    @Override
    public void showFragmentNavMasterSetup(String title) {
        showFragment(FragmentNavMasterSetup.newInstance(title), false);
    }

    @Override
    public void showFragmentNavPurchasing(String title) {
        showFragment(FragmentNavPurchasing.newInstance(title), false);
    }

    @Override
    public void showFragmentNavInventory(String title) {
        showFragment(FragmentNavInventory.newInstance(title), false);
    }

    @Override
    public void showFragmentNavSales(String title) {
        showFragment(FragmentNavSales.newInstance(title), false);
    }

    @Override
    public void showFragmentNavReporting(String title) {
        showFragment(FragmentNavReporting.newInstance(title), false);
    }

    @Override
    public void showFragmentNavUtility(String title) {
        showFragment(FragmentNavUtility.newInstance(title), false);
    }

    private void showFragment(Fragment fragment, boolean allowStateLoss) {
        FragmentManager fm = mFragmentManager;

        @SuppressLint("CommitTransaction")
        FragmentTransaction ft = fm.beginTransaction()
            .replace(R.id.container, fragment);

        ft.addToBackStack(null);

        if (allowStateLoss || !BuildConfig.DEBUG) {
            ft.commitAllowingStateLoss();
        } else {
            ft.commit();
        }

        fm.executePendingTransactions();
    }
}
