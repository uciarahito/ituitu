package uci.develops.wiraenergimobile.helper;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Locale;

/**
 * Created by ArahitoPC on 12/5/2016.
 */
public class NumberTextWatcher implements TextWatcher {

    private NumberFormat nf;
    private DecimalFormat df;
    private DecimalFormat dfnd;
    private boolean hasFractionalPart;
    String formatString = "#,###,###,###.##";
    String formatString2 = "#,###.##";

    private EditText et;

    private final Locale locale = new Locale("id", "ID");

    public NumberTextWatcher(EditText et)
    {
//        df = new DecimalFormat("#.###,##");
//        df.setDecimalSeparatorAlwaysShown(true);
//        dfnd = new DecimalFormat("#.###");

//        df = new DecimalFormat("##,###,###.##", new DecimalFormatSymbols(locale));
//        dfnd = new DecimalFormat("#.###", new DecimalFormatSymbols(locale));
//        nf = NumberFormat.getNumberInstance(locale);

        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(locale);
        otherSymbols.setDecimalSeparator(',');
        otherSymbols.setGroupingSeparator('.');
        df = new DecimalFormat(formatString, otherSymbols);
        dfnd = new DecimalFormat(formatString2, otherSymbols);

        this.et = et;
        hasFractionalPart = false;
    }

    @SuppressWarnings("unused")
    private static final String TAG = "NumberTextWatcher";

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().contains(String.valueOf(df.getDecimalFormatSymbols().getDecimalSeparator())))
        {
            hasFractionalPart = true;
        } else {
            hasFractionalPart = false;
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        et.removeTextChangedListener(this);

        try {
            int inilen, endlen;
            inilen = et.getText().length();

            String v = s.toString().replace(String.valueOf(df.getDecimalFormatSymbols().getGroupingSeparator()), "");
            Number n = df.parse(v);
            int cp = et.getSelectionStart();
            if (hasFractionalPart) {
                et.setText(df.format(n));
            } else {
                et.setText(dfnd.format(n));
            }
            endlen = et.getText().length();
            int sel = (cp + (endlen - inilen));
            if (sel > 0 && sel <= et.getText().length()) {
                et.setSelection(sel);
            } else {
                // place cursor at the end?
                et.setSelection(et.getText().length() - 1);
            }
        } catch (NumberFormatException nfe) {
            // do nothing?
        } catch (ParseException e) {
            // do nothing?
        }

        et.addTextChangedListener(this);
    }
}
