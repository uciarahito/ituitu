package uci.develops.wiraenergimobile.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uci.develops.wiraenergimobile.R;

/**
 * Created by user on 10/22/2016.
 */
public class FragmentFormCustomerContactInfo extends Fragment{
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

        return view;
    }

}
