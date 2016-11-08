package uci.develops.wiraenergimobile.model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;

/**
 * Created by msahakyan on 22/10/15.
 */
public class ExpandableListDataSource {

    /**
     * Returns fake data of films
     *
     * @param context
     * @return
     */
    public static Map<String, List<String>> getData(Context context) {
        Map<String, List<String>> expandableListData = new TreeMap<>();

        //root
        //List<String> rootMenu = Arrays.asList(context.getResources().getStringArray(R.array.general));
        List<String> rootMenu = new ArrayList<>();
        if(new SharedPreferenceManager().getPreferences(context, "roles").equals("admin")) {
            rootMenu.add("Dashboard");
            rootMenu.add("Customer");
            rootMenu.add("Purchasing");
            rootMenu.add("Sales");
            rootMenu.add("Logout");
        } else if(new SharedPreferenceManager().getPreferences(context, "roles").equals("customer")){
            rootMenu.add("Dashboard");
            rootMenu.add("Sales");
            rootMenu.add("Logout");
        } else {
            rootMenu.add("Dashboard");
            rootMenu.add("Logout");
        }

        //main menu
        List<String> menuPurchasing = Arrays.asList(context.getResources().getStringArray(R.array.menu_purchasing));
        List<String> menuSales = Arrays.asList(context.getResources().getStringArray(R.array.menu_sales));

        /*
        //tree for root
        expandableListData.put(rootMenu.get(0), menuPurchasing);
        expandableListData.put(rootMenu.get(1), menuSales);
        */

        for(String menu_root_ : rootMenu){
            Log.e("xyz", menu_root_);
            if(menu_root_.equals("Purchasing")){
                expandableListData.put(menu_root_, menuPurchasing);
            } else if(menu_root_.equals("Sales")){
                expandableListData.put(menu_root_, menuSales);
            } else {
                expandableListData.put(menu_root_, new ArrayList<String>());
            }
        }
        return expandableListData;
    }

    public static List<String> getTitle(){
        List<String> rootMenu = new ArrayList<>();
        rootMenu.add("Dashboard");
        rootMenu.add("Customer");
        rootMenu.add("Purchasing");
        rootMenu.add("Sales");
        rootMenu.add("Logout");
        return  rootMenu;
    }
}
