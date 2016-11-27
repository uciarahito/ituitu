package uci.develops.wiraenergimobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.fragment.FragmentQuotationBillingPaymentCompanyAddress;
import uci.develops.wiraenergimobile.fragment.FragmentQuotationBillingPaymentShippingAddress;
import uci.develops.wiraenergimobile.fragment.FragmentQuotationItemQuotation;
import uci.develops.wiraenergimobile.fragment.FragmentQuotationNote;
import uci.develops.wiraenergimobile.fragment.FragmentQuotationShippingAddress;
import uci.develops.wiraenergimobile.model.QuotationModel;

public class FormRequestQuotationActivity extends AppCompatActivity implements View.OnClickListener {

    //utk tab fragment
    private LinearLayout layout_button_previous, layout_button_next;
    private LinearLayout layout_button_save_as_draft, layout_button_cancel, layout_button_send;
    private LinearLayout layout_container_shipping_address, layout_container_billPay_company_address, layout_container_billPay_shipping_address, layout_container_item_quotation, layout_container_note;
    private LinearLayout layout_tab_shipping_address, layout_tab_billPayment_company_address, layout_tab_billPayment_shipping_address, layout_tab_item_quotation, layout_tab_note;
    private LinearLayout[] linearLayouts_fragment = new LinearLayout[5];
    private LinearLayout[] linearLayouts_tabs = new LinearLayout[5];
    private TextView textView_button_previous, textView_button_save_as_draft, textView_button_cancel, textView_button_send, textView_button_next;

    int index_fragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_request_quotation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeComponent();
    }

    private void initializeComponent() {
        layout_button_previous = (LinearLayout) findViewById(R.id.layout_button_previous);
        layout_button_next = (LinearLayout) findViewById(R.id.layout_button_next);
        layout_button_save_as_draft = (LinearLayout) findViewById(R.id.layout_button_save_as_draft);
        layout_button_cancel = (LinearLayout) findViewById(R.id.layout_button_cancel);
        layout_button_send = (LinearLayout) findViewById(R.id.layout_button_send);
        layout_container_shipping_address = (LinearLayout) findViewById(R.id.layout_container_shipping_address);
        layout_container_billPay_company_address = (LinearLayout) findViewById(R.id.layout_container_billPay_company_address);
        layout_container_billPay_shipping_address = (LinearLayout) findViewById(R.id.layout_container_billPay_shipping_address);
        layout_container_item_quotation = (LinearLayout) findViewById(R.id.layout_container_item_quotation);
        layout_container_note = (LinearLayout) findViewById(R.id.layout_container_note);
        layout_tab_shipping_address = (LinearLayout) findViewById(R.id.layout_tab_shipping_address);
        layout_tab_billPayment_company_address = (LinearLayout) findViewById(R.id.layout_tab_billPayment_company_address);
        layout_tab_billPayment_shipping_address = (LinearLayout) findViewById(R.id.layout_tab_billPayment_shippinh_address);
        layout_tab_item_quotation = (LinearLayout) findViewById(R.id.layout_tab_item_quotation);
        layout_tab_note = (LinearLayout) findViewById(R.id.layout_tab_note);

        linearLayouts_fragment[0] = layout_container_shipping_address;
        linearLayouts_fragment[1] = layout_container_billPay_company_address;
        linearLayouts_fragment[2] = layout_container_billPay_shipping_address;
        linearLayouts_fragment[3] = layout_container_item_quotation;
        linearLayouts_fragment[4] = layout_container_note;

        linearLayouts_tabs[0] = layout_tab_shipping_address;
        linearLayouts_tabs[1] = layout_tab_billPayment_company_address;
        linearLayouts_tabs[2] = layout_tab_billPayment_shipping_address;
        linearLayouts_tabs[3] = layout_tab_item_quotation;
        linearLayouts_tabs[4] = layout_tab_note;

        linearLayouts_fragment[index_fragment].setVisibility(View.VISIBLE);

        layout_button_previous.setOnClickListener(this);
        layout_button_next.setOnClickListener(this);
        layout_button_save_as_draft.setOnClickListener(this);
        layout_button_cancel.setOnClickListener(this);
        layout_button_send.setOnClickListener(this);

        layout_button_previous.setVisibility(View.INVISIBLE);
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

    @Override
    public void onClick(View v) {
        if (v == layout_button_next) {
            if(index_fragment <4) {
                index_fragment++;
                for (int i = 0; i < 5; i++) {
                    if (index_fragment == i) {
                        linearLayouts_fragment[i].setVisibility(View.VISIBLE);
                    } else {
                        linearLayouts_fragment[i].setVisibility(View.GONE);
                    }
                }
                if (index_fragment != 0) {
                    layout_button_previous.setVisibility(View.VISIBLE);
                }
            }
        } else if (v == layout_button_previous) {
            if(index_fragment >=1) {
                index_fragment--;
                if(index_fragment == 0){
                    layout_button_previous.setVisibility(View.GONE);
                }
                for (int i = 0; i < 5; i++) {
                    if (index_fragment == i) {
                        linearLayouts_fragment[i].setVisibility(View.VISIBLE);
                    } else {
                        linearLayouts_fragment[i].setVisibility(View.GONE);
                    }
                }
            }
        }
    }
}
