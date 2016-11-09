package uci.develops.wiraenergimobile.fragment.navigation;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import uci.develops.wiraenergimobile.BuildConfig;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.activity.HomeActivity;
import uci.develops.wiraenergimobile.fragment.FragmentPurchasing;
import uci.develops.wiraenergimobile.fragment.FragmentSales;

/**
 * @author msahakyan
 */

public class FragmentNavigationManager implements NavigationManager {

    private static FragmentNavigationManager sInstance;

    private FragmentManager mFragmentManager;
    private HomeActivity mActivity;

    public static FragmentNavigationManager obtain(HomeActivity activity) {
        if (sInstance == null) {
            sInstance = new FragmentNavigationManager();
        }
        sInstance.configure(activity);
        return sInstance;
    }

    private void configure(HomeActivity activity) {
        mActivity = activity;
        mFragmentManager = mActivity.getSupportFragmentManager();
    }

    @Override
    public void showFragmentNavPurchasing(String title) {
        showFragment(FragmentPurchasing.newInstance(title), false);
    }

    @Override
    public void showFragmentNavSales(String title) {
        showFragment(FragmentSales.newInstance(title), false);
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
