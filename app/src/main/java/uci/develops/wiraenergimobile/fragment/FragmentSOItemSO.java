package uci.develops.wiraenergimobile.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uci.develops.wiraenergimobile.R;

public class FragmentSOItemSO extends Fragment implements View.OnClickListener{

    @BindView(R.id.editText_quantity) EditText editText_quantity;
    @BindView(R.id.editText_disc) EditText editText_disc;
    @BindView(R.id.editText_disc_amount) EditText editText_disc_amount;
    @BindView(R.id.editText_unit_price) EditText editText_unit_price;
    @BindView(R.id.editText_total_commission) EditText editText_total_commission;
    @BindView(R.id.editText_sub_total) EditText editText_sub_total;
    @BindView(R.id.editText_note_item) EditText editText_note_item;
    @BindView(R.id.spinner_unit) Spinner spinner_unit;
    @BindView(R.id.spinner_item) Spinner spinner_item;
    @BindView(R.id.imageViewTotalCommission) ImageView imageViewTotalCommission;

    public FragmentSOItemSO() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_so_item_so, container, false);
        ButterKnife.bind(this, view);
        initializeComponent(view);

        // start listening for refresh local file list in
        return view;
        //return inflater.inflate(R.layout.activity_ongoing_order, container, false);
    }

    private void initializeComponent(View view){
        List<String> listItem = new ArrayList<String>();
        listItem.add("Solar");
        listItem.add("Bensin");
        listItem.add("Pertamax");
        listItem.add("Barrel");
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.spinner_item, listItem);
        itemAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_item.setAdapter(itemAdapter);

        List<String> listUnit = new ArrayList<String>();
        listUnit.add("Liter");
        listUnit.add("Kilo Liter");
        listUnit.add("Mili Liter");
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.spinner_item, listUnit);
        unitAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_unit.setAdapter(unitAdapter);

        imageViewTotalCommission.setOnClickListener(this);
    }

    /**
     * ketika activity resume
     * melakukan register broadcast receiver
     */
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        if (v == imageViewTotalCommission){
            showCustomDialogFormSalesCommission();
        }
    }

    boolean is_new_commision = false;
    private void showCustomDialogFormSalesCommission() {
        final Dialog dialog_form_sales_commission = new Dialog(getActivity().getApplicationContext());
        dialog_form_sales_commission.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_form_sales_commission.setContentView(R.layout.custom_dialog_form_sales_commission);

        EditText editText_so_code = ButterKnife.findById(dialog_form_sales_commission, R.id.editText_so_code);
        EditText editText_hpp = ButterKnife.findById(dialog_form_sales_commission, R.id.editText_hpp);
        EditText editText_min_margin = ButterKnife.findById(dialog_form_sales_commission, R.id.editText_min_margin);
        EditText editText_min_price = ButterKnife.findById(dialog_form_sales_commission, R.id.editText_min_price);
        EditText editText_price = ButterKnife.findById(dialog_form_sales_commission, R.id.editText_price);
        EditText editText_max_comm = ButterKnife.findById(dialog_form_sales_commission, R.id.editText_max_comm);
        TextView textView_total = ButterKnife.findById(dialog_form_sales_commission, R.id.textView_total);
        Button button_add_salesman = ButterKnife.findById(dialog_form_sales_commission, R.id.button_add_salesman);
        Button button_cancel = ButterKnife.findById(dialog_form_sales_commission, R.id.button_cancel);
        Button button_save = ButterKnife.findById(dialog_form_sales_commission, R.id.button_save);

        button_add_salesman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                is_new_commision = true;
                dialog_form_sales_commission.dismiss();
                showCustomDialogAddSalesman();
            }
        });

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_form_sales_commission.dismiss();
            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_form_sales_commission.dismiss();
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getActivity().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialog_form_sales_commission.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog_form_sales_commission.setCancelable(false);
        dialog_form_sales_commission.show();
    }

    private void showCustomDialogAddSalesman() {
        final Dialog dialog_add_salesman = new Dialog(getActivity().getApplicationContext());
        dialog_add_salesman.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_add_salesman.setContentView(R.layout.custom_dialog_add_salesman);

        EditText editText_address = ButterKnife.findById(dialog_add_salesman, R.id.editText_address);
        EditText editText_phone = ButterKnife.findById(dialog_add_salesman, R.id.editText_phone);
        EditText editText_mobile = ButterKnife.findById(dialog_add_salesman, R.id.editText_mobile);
        EditText editText_commission = ButterKnife.findById(dialog_add_salesman, R.id.editText_commission);
        Spinner spinner_salesman = ButterKnife.findById(dialog_add_salesman, R.id.spinner_salesman);
        Button button_cancel = ButterKnife.findById(dialog_add_salesman, R.id.button_cancel);
        Button button_save = ButterKnife.findById(dialog_add_salesman, R.id.button_save);

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_add_salesman.dismiss();
            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_add_salesman.dismiss();
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getActivity().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialog_add_salesman.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog_add_salesman.setCancelable(false);
        dialog_add_salesman.show();
    }


}
