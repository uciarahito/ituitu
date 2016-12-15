package uci.develops.wiraenergimobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.adapter.ItemSalesOrderAdapter;

public class SalesOrderActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.button_add_sales_order)
    Button button_add_sales_order;
    @BindView(R.id.recycleListSalesOrder)
    RecyclerView recycleListSalesOrder;
    ItemSalesOrderAdapter itemSalesOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        initializeComponent();
    }

    private void initializeComponent() {
        button_add_sales_order.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == button_add_sales_order) {
            Intent intent = new Intent(SalesOrderActivity.this, FormRequestSalesOrderActivity.class);
            startActivity(intent);
        }
    }
}
