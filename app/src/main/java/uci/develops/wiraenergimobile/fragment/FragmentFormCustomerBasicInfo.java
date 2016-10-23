package uci.develops.wiraenergimobile.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import uci.develops.wiraenergimobile.R;

/**
 * Created by user on 10/22/2016.
 */
public class FragmentFormCustomerBasicInfo extends Fragment{

    private EditText editText_first_name, editText_address, editText_city, editText_province, editText_zip_code,
            editText_phone, editText_mobile, editText_fax, editText_term, editText_valuta, editText_npwp,
            editText_tax_ppn, editText_active, editText_email, editText_website, editText_note;

    public FragmentFormCustomerBasicInfo() {
        // Required empty public constructor
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
        view = inflater.inflate(R.layout.fragment_form_customer_basic_info, container, false);

        initializeComponent(view);

        return view;
    }

    private void initializeComponent(View view){
        editText_first_name = (EditText)view.findViewById(R.id.editText_first_name);
        editText_address = (EditText)view.findViewById(R.id.editText_address);
        editText_city = (EditText)view.findViewById(R.id.editText_city);
        editText_province = (EditText)view.findViewById(R.id.editText_province);
        editText_zip_code = (EditText)view.findViewById(R.id.editText_zip_code);
        editText_phone = (EditText)view.findViewById(R.id.editText_phone);
        editText_mobile = (EditText)view.findViewById(R.id.editText_mobile);
        editText_fax = (EditText)view.findViewById(R.id.editText_fax);
        editText_term = (EditText)view.findViewById(R.id.editText_term);
        editText_valuta = (EditText)view.findViewById(R.id.editText_valuta);
        editText_npwp = (EditText)view.findViewById(R.id.editText_npwp);
        editText_tax_ppn = (EditText)view.findViewById(R.id.editText_tax_ppn);
        editText_active = (EditText)view.findViewById(R.id.editText_active);
        editText_email = (EditText)view.findViewById(R.id.editText_email);
        editText_website = (EditText)view.findViewById(R.id.editText_website);
        editText_note = (EditText)view.findViewById(R.id.editText_note);
    }

    private boolean isNotEmpty(){
        String first_name="", address="", city="", province="", zip_code="", phone="", mobile="", fax="", term="",
                valuta="", npwp="", tax_ppn="", active="", email="", website="", note="";
        boolean result=false;
        if(!first_name.equals("") && !address.equals("") && !city.equals("") && province.equals("")){
            result=true;
        }
        return  result;
    }
}
