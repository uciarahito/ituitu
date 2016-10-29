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

        Map<String, List<String>> expandableListDataSubMenu = new TreeMap<>();

        //root
        List<String> rootMenu = Arrays.asList(context.getResources().getStringArray(R.array.general));

        //main menu
        List<String> menuMasterSetup = Arrays.asList(context.getResources().getStringArray(R.array.master_setup));
        List<String> menuPurchasing = Arrays.asList(context.getResources().getStringArray(R.array.purchasing));
        List<String> menuInventory = Arrays.asList(context.getResources().getStringArray(R.array.inventory));
        List<String> menuSalesCustomer = Arrays.asList(context.getResources().getStringArray(R.array.sales_customer));
        List<String> menuReporting = Arrays.asList(context.getResources().getStringArray(R.array.reporting));
        List<String> menuUtility = Arrays.asList(context.getResources().getStringArray(R.array.utility));

        //sub menu
        List<String> subMenuMasterDataPurchasing = Arrays.asList(context.getResources().getStringArray(R.array.master_data_purchasing));
        List<String> subMenuTransactionPurchasing = Arrays.asList(context.getResources().getStringArray(R.array.transaction_purchasing));
        List<String> subMenuMasterDataInventory = Arrays.asList(context.getResources().getStringArray(R.array.master_data_inventory));
        List<String> subMenuTransInventory = Arrays.asList(context.getResources().getStringArray(R.array.trans_inventory));
        List<String> subMenuMasterDataSales = Arrays.asList(context.getResources().getStringArray(R.array.master_data_sales));
        List<String> subMenuMarketingOrderSales = Arrays.asList(context.getResources().getStringArray(R.array.marketing_order));
        List<String> subMenuCustomerSales = Arrays.asList(context.getResources().getStringArray(R.array.customer_sales));
        List<String> subMenuPurchaseReporting = Arrays.asList(context.getResources().getStringArray(R.array.purchase_reporting));
        List<String> subMenuInventoryReporting = Arrays.asList(context.getResources().getStringArray(R.array.inventory_reporting));
        List<String> subMenuSalesCustomerReporting = Arrays.asList(context.getResources().getStringArray(R.array.sales_reporting));
        List<String> subMenuFinanceGLReporting = Arrays.asList(context.getResources().getStringArray(R.array.finance_GL_reporting));

        //sub sub menu
        List<String> subMenuPurchaseOrder_ReportingPurchase = Arrays.asList(context.getResources().getStringArray(R.array.purchase_order));
        List<String> subMenuPurchaseVendor_ReportingPurchase = Arrays.asList(context.getResources().getStringArray(R.array.purchase_vendor));
        List<String> subMenuAccountPayable_ReportingPurchase = Arrays.asList(context.getResources().getStringArray(R.array.account_payable));
        List<String> subMenuCustomerOrder_ReportingSalesCustomer = Arrays.asList(context.getResources().getStringArray(R.array.customer_order));
        List<String> subMenuSalesDelivery_ReportingSalesCustomer = Arrays.asList(context.getResources().getStringArray(R.array.sales_delivery));
        List<String> subMenuAccountReceivable_ReportingSalesCustomer = Arrays.asList(context.getResources().getStringArray(R.array.account_receivable));

        //tree for root
        expandableListData.put(rootMenu.get(0), menuPurchasing);
        expandableListData.put(rootMenu.get(1), menuInventory);
        expandableListData.put(rootMenu.get(2), menuMasterSetup);
        expandableListData.put(rootMenu.get(3), menuReporting);
        expandableListData.put(rootMenu.get(4), menuSalesCustomer);
        expandableListData.put(rootMenu.get(5), menuUtility);

        //tree for main menu purchasing
        expandableListDataSubMenu.put(menuPurchasing.get(0), subMenuMasterDataPurchasing);
        expandableListDataSubMenu.put(menuPurchasing.get(1), subMenuTransactionPurchasing);

//        //tree for main menu inventory
//        expandableListData.put(menuInventory.get(0), subMenuMasterDataInventory);
//        expandableListData.put(menuInventory.get(1), subMenuTransInventory);
//
//        //tree for main menu sales (customer)
//        expandableListData.put(menuSalesCustomer.get(0), subMenuMasterDataSales);
//        expandableListData.put(menuSalesCustomer.get(1), subMenuMarketingOrderSales);
//        expandableListData.put(menuSalesCustomer.get(2), subMenuCustomerSales);
//
//        //tree for main menu reporting
//        expandableListData.put(menuReporting.get(0), subMenuPurchaseReporting);
//        expandableListData.put(menuReporting.get(1), subMenuInventoryReporting);
//        expandableListData.put(menuReporting.get(2), subMenuSalesCustomerReporting);
//        expandableListData.put(menuReporting.get(3), subMenuFinanceGLReporting);
//
//        //tree for sub menu reporting -> purchase
//        expandableListData.put(subMenuPurchaseReporting.get(0), subMenuPurchaseOrder_ReportingPurchase);
//        expandableListData.put(subMenuPurchaseReporting.get(1), subMenuPurchaseVendor_ReportingPurchase);
//        expandableListData.put(subMenuPurchaseReporting.get(2), subMenuAccountPayable_ReportingPurchase);
//
//        //tree for sub menu reporting -> sales (customer)
//        expandableListData.put(subMenuSalesCustomerReporting.get(0), subMenuCustomerOrder_ReportingSalesCustomer);
//        expandableListData.put(subMenuSalesCustomerReporting.get(1), subMenuSalesDelivery_ReportingSalesCustomer);
//        expandableListData.put(subMenuSalesCustomerReporting.get(2), subMenuAccountReceivable_ReportingSalesCustomer);

        return expandableListData;
    }
}
