package uci.develops.wiraenergimobile.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import uci.develops.wiraenergimobile.R;

/**
 * Created by ArahitoPC on 11/27/2016.
 */
public class FragmentProfileCustomer extends Fragment implements View.OnClickListener{
    @BindView(R.id.imageTitle1) ImageView imageViewTitle1;
    @BindView(R.id.imageTitle2) ImageView imageViewTitle2;
    @BindView(R.id.imageTitle3) ImageView imageViewTitle3;
    @BindView(R.id.layoutTitle1) LinearLayout linearLayoutTitle1;
    @BindView(R.id.layoutTitle2) LinearLayout linearLayoutTitle2;
    @BindView(R.id.layoutTitle3) LinearLayout linearLayoutTitle3;
    @BindView(R.id.layoutContent1) LinearLayout linearLayoutContent1;
    @BindView(R.id.layoutContent2) LinearLayout linearLayoutContent2;
    @BindView(R.id.layoutContent3) LinearLayout linearLayoutContent3;
    @BindView(R.id.layout_container_profil_company_info) LinearLayout layout_container_profil_company_info;
    @BindView(R.id.layout_container_profil_contact_info) LinearLayout layout_container_profil_contact_info;
    @BindView(R.id.layout_container_profil_shipping_address) LinearLayout layout_container_profil_shipping_address;

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
        ButterKnife.bind(this, view);
        initializeComponent(view);
        loadData();
        return view;
    }

    private void loadData(){
    }

    private void initializeComponent(View view){
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
                layout_container_profil_company_info.setVisibility(View.VISIBLE);
                content1=true;
            } else {
                linearLayoutContent1.setVisibility(View.GONE);
                layout_container_profil_company_info.setVisibility(View.GONE);
                content1=false;
            }
        }
        if(v == linearLayoutTitle2){
            if(!content2){
                linearLayoutContent2.setVisibility(View.VISIBLE);
                layout_container_profil_contact_info.setVisibility(View.VISIBLE);
                content2=true;
            } else {
                linearLayoutContent2.setVisibility(View.GONE);
                layout_container_profil_contact_info.setVisibility(View.GONE);
                content2=false;
            }
        }
        if(v == linearLayoutTitle3){
            if(!content3){
                linearLayoutContent3.setVisibility(View.VISIBLE);
                layout_container_profil_shipping_address.setVisibility(View.VISIBLE);
                content3=true;
            } else {
                linearLayoutContent3.setVisibility(View.GONE);
                layout_container_profil_shipping_address.setVisibility(View.GONE);
                content3=false;
            }
        }
    }
}
