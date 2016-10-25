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
import android.widget.LinearLayout;

import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.model.CustomerModel;

/**
 * Created by user on 10/22/2016.
 */

public class FragmentFormCustomerShippingTo extends Fragment{

    private EditText editText_pic_name, editText_address, editText_postcode, editText_eta,
            editText_email, editText_phone, editText_mobile, editText_fax, editText_tax, editText_map_cordinate, editText_note;
    private AutoCompleteTextView autoComplete_city, autoComplete_province;
    private LinearLayout linear_layout_eta;

    String pic_name="", address="", city="", province="", postcode="", eta="", map="", email="", phone="", mobile="", fax="",tax="",  note="";

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
        editText_postcode = (EditText)view.findViewById(R.id.editText_postcode);
        editText_eta = (EditText)view.findViewById(R.id.editText_eta);
        editText_map_cordinate = (EditText)view.findViewById(R.id.editText_map_coordinate);
        editText_email = (EditText)view.findViewById(R.id.editText_email);
        editText_phone = (EditText)view.findViewById(R.id.editText_phone);
        editText_mobile = (EditText)view.findViewById(R.id.editText_mobile);
        editText_fax = (EditText)view.findViewById(R.id.editText_fax);
        editText_tax = (EditText)view.findViewById(R.id.editText_tax);
        editText_note = (EditText)view.findViewById(R.id.editText_note);
        linear_layout_eta = (LinearLayout)view.findViewById(R.id.linear_layout_eta);

        String[] province = getActivity().getResources().getStringArray(R.array.list_of_province);
        String[] city = getActivity().getResources().getStringArray(R.array.list_of_city);

        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,province);
        autoComplete_province.setAdapter(provinceAdapter);

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,city);
        autoComplete_city.setAdapter(cityAdapter);
    }

    public boolean isNotEmpty(){
        pic_name = editText_pic_name.getText().toString();
        address = editText_address.getText().toString();
        city = autoComplete_city.getText().toString();
        province = autoComplete_province.getText().toString();
        postcode = editText_postcode.getText().toString();
        eta = editText_eta.getText().toString();
        map = editText_map_cordinate.getText().toString();
        phone = editText_phone.getText().toString();
        mobile = editText_mobile.getText().toString();
        email = editText_email.getText().toString();
        fax = editText_fax.getText().toString();
        tax = editText_fax.getText().toString();
        note = editText_note.getText().toString();

        //cek jika role == admin maka visible, selain admin invisible
        editText_eta.setVisibility(View.INVISIBLE);

        boolean result=false;
        if(!pic_name.equals("") && !address.equals("") && !city.equals("") && !province.equals("") && !postcode.equals("")
                && !eta.equals("") && !email.equals("") && !phone.equals("") && !mobile.equals("")){

            result=true;
        }
        return  result;
    }

    public CustomerModel getFormValue(){
        CustomerModel customerModel = new CustomerModel();
        customerModel.setShipping_pic(pic_name);
        customerModel.setShipping_address(address);
        customerModel.setShipping_city(city);
        customerModel.setShipping_province(province);
        customerModel.setShipping_postcode(postcode);
        customerModel.setShipping_eta(eta);
        customerModel.setShipping_map(map);
        customerModel.setShipping_phone(phone);
        customerModel.setShipping_mobile(mobile);
        customerModel.setShipping_email(email);
        customerModel.setShipping_fax(fax);
        customerModel.setShipping_fax(tax);
        customerModel.setShipping_note(note);

        return customerModel;
    }
}
