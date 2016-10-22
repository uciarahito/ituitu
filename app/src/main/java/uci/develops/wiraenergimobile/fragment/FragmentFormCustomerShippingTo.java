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

    private EditText editText_map_cordinate;

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
        editText_map_cordinate = (EditText)view.findViewById(R.id.ediText_map_coordinate);

        editText_map_cordinate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getActivity().getApplicationContext(), "Map coordinate", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return view;
    }
}
