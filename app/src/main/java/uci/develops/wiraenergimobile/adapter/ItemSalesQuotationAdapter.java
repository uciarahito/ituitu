package uci.develops.wiraenergimobile.adapter;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.activity.FormRequestQuotationAdminActivity;
import uci.develops.wiraenergimobile.model.QuotationModel;

/**
 * Created by ArahitoPC on 10/24/2016.
 */
public class ItemSalesQuotationAdapter extends RecyclerView.Adapter<ItemSalesQuotationAdapter.MyViewHolder> {
    private List<QuotationModel> quotationModelList;
    private Context context;
    DatePickerDialog datePickerDialog;

    Map<String, List<String>> mRoles = new TreeMap<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView_price) TextView textView_price;
        @BindView(R.id.textView_disc) TextView textView_disc;
        @BindView(R.id.textView_qty_item) TextView textView_qty_item;
        @BindView(R.id.textView_unit_item) TextView textView_unit_item;
        @BindView(R.id.textView_item_name) TextView textView_item_name;
        @BindView(R.id.textView_sub_total) TextView textView_sub_total;
        @BindView(R.id.textView_disc_amount) TextView textView_disc_amount;
        @BindView(R.id.textView_send_date) TextView textView_send_date;
        @BindView(R.id.imageView_edit) ImageView imageView_edit;
        @BindView(R.id.imageView_delete) ImageView imageView_delete;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public ItemSalesQuotationAdapter(Context context, List<QuotationModel> quotationModelList) {
        this.context = context;
        this.quotationModelList = quotationModelList;
    }

    public void updateList(List<QuotationModel> newList) {
        this.quotationModelList.clear();
        this.quotationModelList = newList;
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sales_quotation, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final QuotationModel quotationModel = quotationModelList.get(position);
        holder.imageView_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Set decode melalui data per row
                 * menampung di shared preference
                 */
                //panggil method showDialogEditItemSO
            }
        });

        holder.imageView_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Set decode melalui data per row
                 * menampung di shared preference
                 */
                Intent intent = new Intent(context, FormRequestQuotationAdminActivity.class);
                context.startActivity(intent);
            }
        });
    }

    Dialog dialog_edit_item_so;
    private void showDialogEditItemSO() {
        dialog_edit_item_so = new Dialog(context);
        dialog_edit_item_so.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_edit_item_so.setContentView(R.layout.custom_dialog_form_req_item_quotation_admin);

        EditText editText_quantity = ButterKnife.findById(dialog_edit_item_so, R.id.editText_quantity);
        EditText editText_disc1 = ButterKnife.findById(dialog_edit_item_so, R.id.editText_disc1);
        EditText editText_disc_amount = ButterKnife.findById(dialog_edit_item_so, R.id.editText_disc_amount);
        EditText editText_unit_price = ButterKnife.findById(dialog_edit_item_so, R.id.editText_unit_price);
        final EditText editText_send_date = ButterKnife.findById(dialog_edit_item_so, R.id.editText_send_date);
        EditText editText_sub_total = ButterKnife.findById(dialog_edit_item_so, R.id.editText_sub_total);
        Spinner spinner_unit = ButterKnife.findById(dialog_edit_item_so, R.id.spinner_unit);
        Spinner spinner_item = ButterKnife.findById(dialog_edit_item_so, R.id.spinner_item);
        Button button_cancel = ButterKnife.findById(dialog_edit_item_so, R.id.button_cancel);
        Button button_save = ButterKnife.findById(dialog_edit_item_so, R.id.button_save);

        List<String> listItem = new ArrayList<String>();
        listItem.add("PCS");
        listItem.add("Militer");
        listItem.add("Liter");
        listItem.add("Barrel");
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(context,
                R.layout.spinner_item, listItem);
        itemAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_item.setAdapter(itemAdapter);

        List<String> listUnit = new ArrayList<String>();
        listUnit.add("Kertas A4 Putih");
        listUnit.add("Tinta Epson");
        listUnit.add("Laptop Asus");
        listUnit.add("Solar");
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<String>(context,
                R.layout.spinner_item, listUnit);
        unitAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_unit.setAdapter(unitAdapter);

        editText_send_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //utk send date
                final Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR); // current year
                int mMonth = calendar.get(Calendar.MONTH); // current month
                int mDay = calendar.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        editText_send_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_edit_item_so.dismiss();
            }
        });

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_edit_item_so.dismiss();
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialog_edit_item_so.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog_edit_item_so.setCancelable(true);
        dialog_edit_item_so.show();
    }

    @Override
    public int getItemCount() {
        return quotationModelList.size();
    }
}