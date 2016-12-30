package uci.develops.wiraenergimobile.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import uci.develops.wiraenergimobile.R;

/**
 * Created by ArahitoPC on 12/28/2016.
 */

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.MyViewHolder> {
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView_invoice_code) TextView textView_invoice_code;
        @BindView(R.id.textView_invoice_date) TextView textView_invoice_date;
        @BindView(R.id.textView_term) TextView textView_term;
        @BindView(R.id.textView_invoice_due_date) TextView textView_invoice_due_date;
        @BindView(R.id.textView_invoice_amount) TextView textView_invoice_amount;
        @BindView(R.id.textView_payment_date) TextView textView_payment_date;
        @BindView(R.id.textView_payment_amount) TextView textView_payment_amount;
        @BindView(R.id.imageView_edit) ImageView imageView_edit;
        @BindView(R.id.imageItemInvoice) ImageView imageItemInvoice;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_payment, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.imageView_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogEditPayment();
            }
        });

        holder.imageItemInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogListPayment();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private EditText editText_invoice_code, editText_invoice_due_date, editText_payment_date, editText_invoice_amount, editText_payment_amount;
    private Button button_cancel, button_save;

    Dialog dialog_edit_payment;
    private void showDialogEditPayment() {
        dialog_edit_payment = new Dialog(context);
        dialog_edit_payment.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_edit_payment.setContentView(R.layout.custom_dialog_edit_payment);

        editText_invoice_code = ButterKnife.findById(dialog_edit_payment, R.id.editText_invoice_code);
        editText_invoice_due_date = ButterKnife.findById(dialog_edit_payment, R.id.editText_invoice_due_date);
        editText_payment_date = ButterKnife.findById(dialog_edit_payment, R.id.editText_payment_date);
        editText_invoice_amount = ButterKnife.findById(dialog_edit_payment, R.id.editText_invoice_amount);
        editText_payment_amount = ButterKnife.findById(dialog_edit_payment, R.id.editText_payment_amount);
        button_cancel = ButterKnife.findById(dialog_edit_payment, R.id.button_cancel);
        button_save = ButterKnife.findById(dialog_edit_payment, R.id.button_save);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialog_edit_payment.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog_edit_payment.setCancelable(true);
        dialog_edit_payment.show();

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_edit_payment.dismiss();
            }
        });

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    ItemPaymentAdapter itemPaymentAdapter;
    RecyclerView recycleView_item_payment;
    Dialog dialog_list_payment;
    private void showDialogListPayment(){
        dialog_list_payment = new Dialog(context);
        dialog_list_payment.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_list_payment.setContentView(R.layout.custom_dialog_list_payment);

        recycleView_item_payment = ButterKnife.findById(dialog_list_payment, R.id.recycleView_item_payment);
        //....................
    }


}
