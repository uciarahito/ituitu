package uci.develops.wiraenergimobile.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import uci.develops.wiraenergimobile.R;

public class FormCustomerActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout linearLayout_button_back, linearLayout_button_save, linearLayout_button_cancel, linearLayout_button_next;
    private LinearLayout linearLayout_container_basic_info, linearLayout_container_contact_info, linearLayout_container_shipping_to;
    private LinearLayout linearLayout_tab_basic_info, linearLayout_tab_contact_info, linearLayout_tab_shipping_to;
    private LinearLayout[] linearLayouts_fragment = new LinearLayout[3];
    private LinearLayout[] linearLayouts_tabs = new LinearLayout[3];

    int index_fragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_customer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeComponent();
    }

    private void initializeComponent(){
        linearLayout_button_back = (LinearLayout)findViewById(R.id.layout_button_back);
        linearLayout_button_next = (LinearLayout)findViewById(R.id.layout_button_next);
        linearLayout_container_basic_info = (LinearLayout)findViewById(R.id.layout_container_basic_info);
        linearLayout_container_contact_info = (LinearLayout)findViewById(R.id.layout_container_contact_info);
        linearLayout_container_shipping_to = (LinearLayout)findViewById(R.id.layout_container_shipping_to);
        linearLayout_tab_basic_info = (LinearLayout)findViewById(R.id.layout_tab_basic_info);
        linearLayout_tab_contact_info = (LinearLayout)findViewById(R.id.layout_tab_contact_info);
        linearLayout_tab_shipping_to = (LinearLayout)findViewById(R.id.layout_tab_shipping_to);

        linearLayouts_fragment[0] = linearLayout_container_basic_info;
        linearLayouts_fragment[1] = linearLayout_container_contact_info;
        linearLayouts_fragment[2] = linearLayout_container_shipping_to;

        linearLayouts_tabs[0] = linearLayout_tab_basic_info;
        linearLayouts_tabs[1] = linearLayout_tab_contact_info;
        linearLayouts_tabs[2] = linearLayout_tab_shipping_to;

        linearLayouts_fragment[index_fragment].setVisibility(View.VISIBLE);

        linearLayout_button_next.setOnClickListener(this);
        linearLayout_button_back.setOnClickListener(this);
        linearLayout_button_save.setOnClickListener(this);
        linearLayout_button_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == linearLayout_button_next){
            if(index_fragment < 2){
                index_fragment++;
                linearLayout_container_basic_info.setVisibility(View.GONE);
                linearLayout_container_contact_info.setVisibility(View.GONE);
                linearLayout_container_shipping_to.setVisibility(View.GONE);
                linearLayout_tab_basic_info.setBackgroundResource(R.drawable.rounded_rectangle_blue_gray);
                linearLayout_tab_contact_info.setBackgroundResource(R.drawable.rounded_rectangle_blue_gray);
                linearLayout_tab_shipping_to.setBackgroundResource(R.drawable.rounded_rectangle_blue_gray);
                linearLayouts_tabs[index_fragment].setBackgroundResource(R.drawable.rounded_rectangle_blue);
                linearLayouts_fragment[index_fragment].setVisibility(View.VISIBLE);
            }
        } else if(v == linearLayout_button_back){
            if(index_fragment > 0){
                index_fragment--;
                linearLayout_container_basic_info.setVisibility(View.GONE);
                linearLayout_container_contact_info.setVisibility(View.GONE);
                linearLayout_container_shipping_to.setVisibility(View.GONE);
                linearLayout_tab_basic_info.setBackgroundResource(R.drawable.rounded_rectangle_blue_gray);
                linearLayout_tab_contact_info.setBackgroundResource(R.drawable.rounded_rectangle_blue_gray);
                linearLayout_tab_shipping_to.setBackgroundResource(R.drawable.rounded_rectangle_blue_gray);
                linearLayouts_tabs[index_fragment].setBackgroundResource(R.drawable.rounded_rectangle_blue);
                linearLayouts_fragment[index_fragment].setVisibility(View.VISIBLE);
            }
        } else if(v == linearLayout_button_save){

        } else if(v == linearLayout_button_cancel){

        }

    }
}
