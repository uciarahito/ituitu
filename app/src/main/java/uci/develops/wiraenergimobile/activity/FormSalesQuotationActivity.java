package uci.develops.wiraenergimobile.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.adapter.ItemSalesQuotationOrderAdapter;
import uci.develops.wiraenergimobile.helper.DividerItemDecoration;
import uci.develops.wiraenergimobile.model.QuotationModel;

public class FormSalesQuotationActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView_customer_note, textView_terbilang;
    private EditText editText_bruto, editText_disc, editText_disc_value, editText_other_cost, editText_netto, editText_admin_note;
    private LinearLayout linearLayoutTitle1;
    private LinearLayout linearLayoutContent1;
    private LinearLayout linearLayoutContainer1;
    private LinearLayout linear_layout_button_cancel, linear_layout_button_send_quotation;
    private LinearLayout layout_tab_shipping_address, layout_tab_billing_address;
    private LinearLayout layout_container_shipping_address, layout_container_billing_address;
    private LinearLayout[] linearLayouts_fragment = new LinearLayout[2];
    private LinearLayout[] linearLayouts_tabs = new LinearLayout[2];
    int index_fragment = 0;
    boolean content1=false;

    private RecyclerView recyclerView;
    ItemSalesQuotationOrderAdapter itemSalesQuotationOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_sales_quotation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeComponent();
        
    }

    private void initializeComponent(){
        textView_customer_note = (TextView) findViewById(R.id.textView_customer_note);
        textView_terbilang = (TextView) findViewById(R.id.textView_terbilang);
        editText_bruto = (EditText) findViewById(R.id.editText_bruto);
        editText_disc = (EditText) findViewById(R.id.editText_disc);
        editText_disc_value = (EditText) findViewById(R.id.editText_disc_value);
        editText_other_cost = (EditText) findViewById(R.id.editText_other_cost);
        editText_netto = (EditText) findViewById(R.id.editText_netto);
        editText_admin_note = (EditText) findViewById(R.id.editText_admin_note);

        linearLayoutTitle1 = (LinearLayout)findViewById(R.id.linear_layout_title1);
        linearLayoutContent1 = (LinearLayout)findViewById(R.id.linear_layout_content1);
        linearLayoutContainer1 = (LinearLayout)findViewById(R.id.linear_layout_container_quotation_customer_detail);
        linear_layout_button_cancel = (LinearLayout)findViewById(R.id.linear_layout_button_cancel);
        linear_layout_button_send_quotation = (LinearLayout)findViewById(R.id.linear_layout_button_send_quotation);
        layout_tab_shipping_address = (LinearLayout) findViewById(R.id.layout_tab_shipping_address);
        layout_tab_billing_address = (LinearLayout) findViewById(R.id.layout_tab_billing_address);
        layout_container_shipping_address = (LinearLayout) findViewById(R.id.layout_container_shipping_address);
        layout_container_billing_address = (LinearLayout) findViewById(R.id.layout_container_billing_address);

        linearLayouts_fragment[0] = layout_container_shipping_address;
        linearLayouts_fragment[1] = layout_container_billing_address;

        linearLayouts_tabs[0] = layout_tab_shipping_address;
        linearLayouts_tabs[1] = layout_tab_billing_address;

        linearLayouts_fragment[index_fragment].setVisibility(View.VISIBLE);

        recyclerView = (RecyclerView)findViewById(R.id.recycle_view);
        List<QuotationModel> quotationModelsList = new ArrayList<>();
        itemSalesQuotationOrderAdapter = new ItemSalesQuotationOrderAdapter(FormSalesQuotationActivity.this, quotationModelsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(FormSalesQuotationActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(FormSalesQuotationActivity.this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(itemSalesQuotationOrderAdapter);

        linearLayoutTitle1.setOnClickListener(this);
        layout_tab_shipping_address.setOnClickListener(this);
        layout_tab_billing_address.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == linearLayoutTitle1){
            if(!content1){
                linearLayoutContent1.setVisibility(View.VISIBLE);
                linearLayoutContainer1.setVisibility(View.VISIBLE);
                content1=true;
            } else {
                linearLayoutContent1.setVisibility(View.GONE);
                linearLayoutContainer1.setVisibility(View.GONE);
                content1=false;
            }
        }

        if (v == layout_tab_shipping_address) {
            layout_container_shipping_address.setVisibility(View.VISIBLE);
            layout_container_billing_address.setVisibility(View.GONE);

            layout_tab_shipping_address.setBackgroundResource(R.drawable.rounded_rectangle_org);
            layout_tab_billing_address.setBackgroundResource(R.drawable.rounded_rectangle_gray);
        }

        if (v == layout_tab_billing_address) {
            layout_container_shipping_address.setVisibility(View.GONE);
            layout_container_billing_address.setVisibility(View.VISIBLE);

            layout_tab_shipping_address.setBackgroundResource(R.drawable.rounded_rectangle_gray);
            layout_tab_billing_address.setBackgroundResource(R.drawable.rounded_rectangle_org);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
