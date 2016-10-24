package uci.develops.wiraenergimobile.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import uci.develops.wiraenergimobile.R;

/**
 * Created by user on 10/22/2016.
 */

public class FragmentFormCustomerShippingTo extends Fragment{

    private EditText editText_pic_name, editText_address, editText_zip_code, editText_eta,
            editText_email, editText_phone, editText_mobile, editText_fax, editText_map_cordinate, editText_note;
    private AutoCompleteTextView autoComplete_city, autoComplete_province;

    public FragmentFormCustomerShippingTo() {
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
        view = inflater.inflate(R.layout.fragment_form_customer_shipping_to, container, false);
        editText_map_cordinate = (EditText)view.findViewById(R.id.editText_map_coordinate);

        editText_map_cordinate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Toast.makeText(getActivity().getApplicationContext(), "Map coordinate", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        initializeComponent(view);

        return view;
    }

    private void initializeComponent(View view){
        editText_pic_name = (EditText)view.findViewById(R.id.editText_pic_name);
        editText_address = (EditText)view.findViewById(R.id.editText_address);
        autoComplete_city = (AutoCompleteTextView)view.findViewById(R.id.autoComplete_city);
        autoComplete_province = (AutoCompleteTextView)view.findViewById(R.id.autoComplete_province);
        editText_zip_code = (EditText)view.findViewById(R.id.editText_zip_code);
        editText_eta = (EditText)view.findViewById(R.id.editText_eta);
        editText_email = (EditText)view.findViewById(R.id.editText_email);
        editText_phone = (EditText)view.findViewById(R.id.editText_phone);
        editText_mobile = (EditText)view.findViewById(R.id.editText_mobile);
        editText_fax = (EditText)view.findViewById(R.id.editText_fax);
        editText_note = (EditText)view.findViewById(R.id.editText_note);

        String[] province = getActivity().getResources().getStringArray(R.array.list_of_province);
        String[] city = getActivity().getResources().getStringArray(R.array.list_of_city);

        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,province);
        autoComplete_province.setAdapter(provinceAdapter);

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,city);
        autoComplete_province.setAdapter(cityAdapter);
    }

    public boolean isNotEmpty(){
        String pic_name="", address="", city="", province="", zip_code="", eta="", email="", phone="", mobile="", fax="", note="";

        pic_name = editText_pic_name.getText().toString();
        address = editText_address.getText().toString();
        city = autoComplete_city.getText().toString();
        province = autoComplete_province.getText().toString();
        zip_code = editText_zip_code.getText().toString();
        eta = editText_eta.getText().toString();
        email = editText_email.getText().toString();
        phone = editText_phone.getText().toString();
        mobile = editText_mobile.getText().toString();
        fax = editText_fax.getText().toString();
        note = editText_note.getText().toString();

        boolean result=false;
        if(!pic_name.equals("") && !address.equals("") && !city.equals("") && province.equals("") && zip_code.equals("")
                && eta.equals("") && email.equals("") && phone.equals("") && mobile.equals("")){

            result=true;
        }
        return  result;
    }
}
