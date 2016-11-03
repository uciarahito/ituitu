package uci.develops.wiraenergimobile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uci.develops.wiraenergimobile.R;

/**
 * Created by user on 11/3/2016.
 */

public class FragmentSales extends Fragment {

    public FragmentSales(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_sales, container, false);

        // start listening for refresh local file list in
        return view;
        //return inflater.inflate(R.layout.activity_ongoing_order, container, false);
    }

    private void initializeComponent(){
    }

    /**
     * ketika activity resume
     * melakukan register broadcast receiver
     */
    @Override
    public void onResume() {
        super.onResume();
    }
}
