package uci.develops.wiraenergimobile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import uci.develops.wiraenergimobile.R;

/**
 * Created by ArahitoPC on 11/27/2016.
 */
public class FragmentProfileCustomer extends Fragment implements View.OnClickListener{

    private LinearLayout linearLayoutTitle1, linearLayoutTitle2, linearLayoutTitle3;
    private LinearLayout linearLayoutContent1, linearLayoutContent2, linearLayoutContent3;
    private ImageView imageViewTitle1, imageViewTitle2, imageViewTitle3;

    boolean content1=false, content2=false, content3=false;

    public FragmentProfileCustomer(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_profile_customer, container, false);
        initializeComponent(view);
        loadData();
        return view;
    }

    private void loadData(){
    }

    private void initializeComponent(View view){
        linearLayoutTitle1 = (LinearLayout)view.findViewById(R.id.layoutTitle1);
        linearLayoutTitle2 = (LinearLayout)view.findViewById(R.id.layoutTitle2);
        linearLayoutTitle3 = (LinearLayout)view.findViewById(R.id.layoutTitle3);
        linearLayoutContent1 = (LinearLayout)view.findViewById(R.id.layoutContent1);
        linearLayoutContent2 = (LinearLayout)view.findViewById(R.id.layoutContent2);
        linearLayoutContent3 = (LinearLayout)view.findViewById(R.id.layoutContent3);
        imageViewTitle1 = (ImageView)view.findViewById(R.id.imageTitle1);
        imageViewTitle2 = (ImageView)view.findViewById(R.id.imageTitle2);
        imageViewTitle3 = (ImageView)view.findViewById(R.id.imageTitle3);

        linearLayoutTitle1.setOnClickListener(this);
        linearLayoutTitle2.setOnClickListener(this);
        linearLayoutTitle3.setOnClickListener(this);
    }

    /**
     * ketika activity resume
     * melakukan register broadcast receiver
     */
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        if(v == linearLayoutTitle1){
            if(!content1){
                linearLayoutContent1.setVisibility(View.VISIBLE);
                content1=true;
            } else {
                linearLayoutContent1.setVisibility(View.GONE);
                content1=false;
            }
        }
        if(v == linearLayoutTitle2){
            if(!content2){
                linearLayoutContent2.setVisibility(View.VISIBLE);
                content2=true;
            } else {
                linearLayoutContent2.setVisibility(View.GONE);
                content2=false;
            }
        }
        if(v == linearLayoutTitle3){
            if(!content3){
                linearLayoutContent3.setVisibility(View.VISIBLE);
                content3=true;
            } else {
                linearLayoutContent3.setVisibility(View.GONE);
                content3=false;
            }
        }
    }
}
