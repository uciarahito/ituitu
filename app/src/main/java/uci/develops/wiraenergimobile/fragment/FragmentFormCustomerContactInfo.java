package uci.develops.wiraenergimobile.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import uci.develops.wiraenergimobile.R;

/**
 * Created by user on 10/22/2016.
 */
public class FragmentFormCustomerContactInfo extends Fragment{
    private EditText editText_name1, editText_name2, editText_name3, editText_phone1, editText_phone2, editText_phone3,
            editText_mobile1, editText_mobile2, editText_mobile3, editText_email1, editText_email2, editText_email3,
            editText_website1, editText_website2, editText_website3;

    public FragmentFormCustomerContactInfo() {
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
        view = inflater.inflate(R.layout.fragment_form_customer_contact_info, container, false);

        initializeComponent(view);

        return view;
    }

    private void initializeComponent(View view){
        editText_name1 = (EditText)view.findViewById(R.id.editText_name1);
        editText_name2 = (EditText)view.findViewById(R.id.editText_name2);
        editText_name3 = (EditText)view.findViewById(R.id.editText_name3);
        editText_phone1 = (EditText)view.findViewById(R.id.editText_phone1);
        editText_phone2 = (EditText)view.findViewById(R.id.editText_phone2);
        editText_phone3 = (EditText)view.findViewById(R.id.editText_phone3);
        editText_mobile1 = (EditText)view.findViewById(R.id.editText_mobile1);
        editText_mobile2 = (EditText)view.findViewById(R.id.editText_mobile2);
        editText_mobile3 = (EditText)view.findViewById(R.id.editText_mobile3);
        editText_email1 = (EditText)view.findViewById(R.id.editText_email1);
        editText_email2 = (EditText)view.findViewById(R.id.editText_email2);
        editText_email3 = (EditText)view.findViewById(R.id.editText_email3);
        editText_website1 = (EditText)view.findViewById(R.id.editText_jabatan1);
        editText_website2 = (EditText)view.findViewById(R.id.editText_jabatan2);
        editText_website3 = (EditText)view.findViewById(R.id.editText_jabatan3);
    }

    private boolean isNotEmpty(){
        String editText_name1="", editText_name2="", editText_name3="", editText_phone1="", editText_phone2="",
                editText_phone3="", editText_mobile1="", editText_mobile2="", editText_mobile3="", editText_email1="",
                editText_email2="", editText_email3="", editText_website1="", editText_website2="", editText_website3="";
        boolean result=false;
        if(!editText_name1.equals("") && !editText_mobile1.equals("")){
            result=true;
        }
        return  result;
    }

}
