package uci.develops.wiraenergimobile.model;

import android.content.Context;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import uci.develops.wiraenergimobile.R;

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
        List<String> rootMenu = Arrays.asList(context.getResources().getStringArray(R.array.general));

        //main menu
        List<String> menuPurchasing = Arrays.asList(context.getResources().getStringArray(R.array.menu_purchasing));
        List<String> menuSales = Arrays.asList(context.getResources().getStringArray(R.array.menu_sales));

        //tree for root
        expandableListData.put(rootMenu.get(0), menuPurchasing);
        expandableListData.put(rootMenu.get(1), menuSales);

        return expandableListData;
    }
}
