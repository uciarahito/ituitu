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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import uci.develops.wiraenergimobile.R;

/**
 * Created by ArahitoPC on 12/28/2016.
 */

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.MyViewHolder> {
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView_pic)
        TextView textView_pic;
        @BindView(R.id.textView_do_date)
        TextView textView_do_date;
        @BindView(R.id.textView_customer)
        TextView textView_customer;
        @BindView(R.id.textView_term)
        TextView textView_term;
        @BindView(R.id.textView_invoice_code)
        TextView textView_invoice_code;
        @BindView(R.id.textView_invoice_date)
        TextView textView_invoice_date;
        @BindView(R.id.textView_invoice_due_date)
        TextView textView_invoice_due_date;
        @BindView(R.id.textView_invoice_amount)
        TextView textView_invoice_amount;
        @BindView(R.id.imageView_edit)
        ImageView imageView_edit;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_invoice, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.imageView_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogEditInvoice();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private EditText editText_invoice_code, editText_invoice_date, editText_invoice_due_date, editText_invoice_amount, editText_invoice_term;
    private ImageButton dec_qty, inc_qty;
    private Button button_cancel, button_save;
    static int x = 0;

    Dialog dialog_edit_invoice;
    private void showDialogEditInvoice() {
        dialog_edit_invoice = new Dialog(context);
        dialog_edit_invoice.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_edit_invoice.setContentView(R.layout.custom_dialog_edit_invoice);

        editText_invoice_code = ButterKnife.findById(dialog_edit_invoice, R.id.editText_invoice_code);
        editText_invoice_date = ButterKnife.findById(dialog_edit_invoice, R.id.editText_invoice_date);
        editText_invoice_due_date = ButterKnife.findById(dialog_edit_invoice, R.id.editText_invoice_due_date);
        editText_invoice_amount = ButterKnife.findById(dialog_edit_invoice, R.id.editText_invoice_amount);
        dec_qty = ButterKnife.findById(dialog_edit_invoice, R.id.dec_qty);
        inc_qty = ButterKnife.findById(dialog_edit_invoice, R.id.inc_qty);
        editText_invoice_term = ButterKnife.findById(dialog_edit_invoice, R.id.editText_invoice_term);
        button_cancel = ButterKnife.findById(dialog_edit_invoice, R.id.button_cancel);
        button_save = ButterKnife.findById(dialog_edit_invoice, R.id.button_save);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;
        dialog_edit_invoice.getWindow().setLayout(deviceWidth - 20, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog_edit_invoice.setCancelable(true);
        dialog_edit_invoice.show();

        editText_invoice_term.setText("" + x);
        inc_qty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x += 1;
                editText_invoice_term.setText("" + x);
            }
        });
        dec_qty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x -= 1;
                editText_invoice_term.setText("" + x);
            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_edit_invoice.dismiss();
            }
        });

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
