package uci.develops.wiraenergimobile.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import uci.develops.wiraenergimobile.R;

/**
 * Created by user on 10/22/2016.
 */

public class FragmentFormCustomerShippingTo extends Fragment{

    private EditText editText_pic_name, editText_address, editText_city, editText_province, editText_zip_code, editText_eta,
            editText_email, editText_phone, editText_mobile, editText_fax, editText_map_cordinate, editText_note;

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
                Toast.makeText(getActivity().getApplicationContext(), "Map coordinate", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        initializeComponent(view);

        return view;
    }

    private void initializeComponent(View view){
        editText_pic_name = (EditText)view.findViewById(R.id.editText_pic_name);
        editText_address = (EditText)view.findViewById(R.id.editText_address);
        editText_city = (EditText)view.findViewById(R.id.editText_city);
        editText_province = (EditText)view.findViewById(R.id.editText_province);
        editText_zip_code = (EditText)view.findViewById(R.id.editText_zip_code);
        editText_eta = (EditText)view.findViewById(R.id.editText_eta);
        editText_email = (EditText)view.findViewById(R.id.editText_email);
        editText_phone = (EditText)view.findViewById(R.id.editText_phone);
        editText_mobile = (EditText)view.findViewById(R.id.editText_mobile);
        editText_fax = (EditText)view.findViewById(R.id.editText_fax);
        editText_note = (EditText)view.findViewById(R.id.editText_note);
    }

    private boolean isNotEmpty(){
        String pic_name="", address="", city="", province="", zip_code="", eta="", email="", phone="", mobile="", fax="", note="";
        boolean result=false;
        if(!pic_name.equals("") && !address.equals("") && !city.equals("") && province.equals("") && zip_code.equals("")
                && eta.equals("") && email.equals("") && phone.equals("") && mobile.equals("")){

            result=true;
        }
        return  result;
    }
}
