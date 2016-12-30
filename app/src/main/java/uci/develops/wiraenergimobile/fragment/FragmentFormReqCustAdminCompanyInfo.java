package uci.develops.wiraenergimobile.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.activity.FormCustomerActivity;
import uci.develops.wiraenergimobile.adapter.CustomSpinnerAdapter;
import uci.develops.wiraenergimobile.helper.NumberTextWatcher;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;
import uci.develops.wiraenergimobile.library.SearchableSpinner;
import uci.develops.wiraenergimobile.model.CityModel;
import uci.develops.wiraenergimobile.model.CustomerGroupModel;
import uci.develops.wiraenergimobile.model.CustomerModel;
import uci.develops.wiraenergimobile.model.ProvinceModel;
import uci.develops.wiraenergimobile.response.CustomerResponse;
import uci.develops.wiraenergimobile.response.ListCustomerGroupResponse;
import uci.develops.wiraenergimobile.service.RestClient;

/**
 * Created by user on 10/22/2016.
 */
public class FragmentFormReqCustAdminCompanyInfo extends Fragment {
    @BindView(R.id.editText_customer_code)
    EditText editText_customer_code;
    @BindView(R.id.editText_name)
    EditText editText_first_name;
    @BindView(R.id.editText_address)
    EditText editText_address;
    @BindView(R.id.editText_postcode)
    EditText editText_zip_code;
    @BindView(R.id.editText_phone)
    EditText editText_phone;
    @BindView(R.id.editText_mobile)
    EditText editText_mobile;
    @BindView(R.id.editText_fax)
    EditText editText_fax;
    @BindView(R.id.editText_term)
    EditText editText_term;
    @BindView(R.id.editText_npwp)
    EditText editText_npwp;
    @BindView(R.id.editText_email)
    EditText editText_email;
    @BindView(R.id.editText_website)
    EditText editText_website;
    @BindView(R.id.editText_note)
    EditText editText_note;
    @BindView(R.id.autoComplete_city)
    AutoCompleteTextView autoComplete_city;
    @BindView(R.id.autoComplete_province)
    AutoCompleteTextView autoComplete_province;
    @BindView(R.id.spinner_valuta)
    Spinner spinner_valuta;
    @BindView(R.id.editText_group)
    EditText editText_group;
    @BindView(R.id.linear_layout_id)
    LinearLayout linear_layout_id;
    @BindView(R.id.linear_layout_term)
    LinearLayout linear_layout_term;
    @BindView(R.id.linear_layout_valuta)
    LinearLayout linear_layout_valuta;
    @BindView(R.id.linear_layout_tax_ppn)
    LinearLayout linear_layout_tax_ppn;
    @BindView(R.id.linear_layout_active)
    LinearLayout linear_layout_active;
    @BindView(R.id.linear_layout_note)
    LinearLayout linear_layout_note;

    @BindView(R.id.switch_tax)
    SwitchCompat switch_tax;
    @BindView(R.id.switch_active)
    SwitchCompat switch_active;
    @BindView(R.id.textView_tax_ppn)
    TextView textView_tax_ppn;
    @BindView(R.id.textView_active)
    TextView textView_active;

    String id = "", name = "", address = "", city = "", province = "", zip_code = "", phone = "", mobile = "", fax = "", term = "",
            valuta = "", group = "", npwp = "", tax_ppn = "", active = "", email = "", website = "", note = "";

    private String decode = "", token = "";
    List<String> cityList;
    List<String> provinceList;
    int cekActive = 0;
    String cekTax = "";
    Gson gson;

    public FragmentFormReqCustAdminCompanyInfo() {
        // Required empty public constructor
    }

    String provinceJson = "", cityJson = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_company_new, container, false);
        ButterKnife.bind(this, view);

        decode = new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "customer_decode");
        token = new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "token");

        initializeComponent(view);

        provinceJson = loadJSONFromAssetProvince();
        cityJson = loadJSONFromAssetCity();

        gson = new Gson();

        // code for autocomplete province
        autoComplete_city.setEnabled(false);
        Type typeOfProvince = new TypeToken<ArrayList<ProvinceModel>>() {
        }.getType();
        provinceModelList = gson.fromJson(provinceJson, typeOfProvince);
        Type typeOfCity = new TypeToken<ArrayList<CityModel>>() {
        }.getType();
        cityModelList = gson.fromJson(cityJson, typeOfCity);

        provinceList = new ArrayList<>();
        for (int i = 0; i < provinceModelList.size(); i++) {
            provinceList.add(((ProvinceModel) provinceModelList.get(i)).getProvince());
        }
        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.spinner_item, provinceList);
        autoComplete_province.setAdapter(provinceAdapter);

        autoComplete_province.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getContext(), "ID "+provinceModelList.get(parent.getItemIdAtPosition(position)).getProvince_id(), Toast.LENGTH_SHORT).show();
                for (ProvinceModel provinceModel : provinceModelList) {
                    if (provinceModel.getProvince().equals(parent.getItemAtPosition(position))) {
                        selectedProvince = provinceModel.getProvince_id();
                        autoComplete_city.setEnabled(true);
                        setAutoCompleteCity();
                    }
                }
            }
        });

        return view;
    }

    static List<ProvinceModel> provinceModelList;
    static List<CityModel> cityModelList;
    String selectedProvince = "", selectedCity;
    List<String> cityIdList;

    // code for showing province name.
    String getProvince(String id) {
        String name = "";
        for (ProvinceModel provinceModel : provinceModelList) {
            if (provinceModel.getProvince_id().equals(id)) {
                name = provinceModel.getProvince();
            }
        }
        return name;
    }

    // code for showing city name.
    String getCity(String id) {
        String name = "";
        for (CityModel cityModel : cityModelList) {
            if (cityModel.getCity_id().equals(id)) {
                name = cityModel.getCity_name();
            }
        }
        return name;
    }

    // code for showing city based on selectedProvince
    void setAutoCompleteCity() {
        /**
         * code for city
         */
        cityList = new ArrayList<>();
        cityIdList = new ArrayList<>();
        for (int i = 0; i < cityModelList.size(); i++) {
            if (cityModelList.get(i).getProvince_id().equals(selectedProvince)) {
                cityList.add(cityModelList.get(i).getCity_name());
                cityIdList.add(cityModelList.get(i).getCity_id());
            }
        }
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.spinner_item, cityList);
        autoComplete_city.setAdapter(cityAdapter);

        autoComplete_city.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < cityList.size(); i++) {
                    if (cityList.get(i).equals(parent.getItemAtPosition(position))) {
                        selectedCity = cityIdList.get(i);
                    }
                }
                Toast.makeText(getContext(), "Id city: " + selectedCity, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // code for load file province.json from assets
    public String loadJSONFromAssetProvince() {
        String json = null;
        try {
            InputStream is = getActivity().getApplicationContext().getAssets().open("province.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            Log.e("Exeption", "exception");
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    // code for load file city.json from assets
    public String loadJSONFromAssetCity() {
        String json = null;
        try {
            InputStream is = getActivity().getApplicationContext().getAssets().open("city.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            Log.e("Exeption", "exception");
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    // Code for showing recycle view in spinner group
    List<String> updatedDataGroup;

    public void showSearchableSpinnerGroup() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        int deviceHeight = displayMetrics.heightPixels;

        dialogSpinnerGroup = new Dialog(getContext());
        dialogSpinnerGroup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogSpinnerGroup.setContentView(R.layout.fragment_search_spinner);

        Window window = dialogSpinnerGroup.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        recyclerViewSpinnerGroup = (RecyclerView) dialogSpinnerGroup.findViewById(R.id.recycleViewSpinner);
        editTextSpinnerSearchGroup = (EditText) dialogSpinnerGroup.findViewById(R.id.editTextValueSearch);
        imageViewSearchGroup = (ImageView) dialogSpinnerGroup.findViewById(R.id.imageViewSearch);
        buttonCancelSpinnerGroup = (Button) dialogSpinnerGroup.findViewById(R.id.buttonCancel);

        if (defaultDataGroup != null) {
            customSpinnerGroupAdapter = new CustomSpinnerAdapter(getContext(), defaultDataGroup);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerViewSpinnerGroup.setLayoutManager(mLayoutManager);
            recyclerViewSpinnerGroup.setItemAnimator(new DefaultItemAnimator());
            recyclerViewSpinnerGroup.setAdapter(customSpinnerGroupAdapter);

            editTextSpinnerSearchGroup.addTextChangedListener(originWatcher);
        }

        dialogSpinnerGroup.setCancelable(true);
        dialogSpinnerGroup.getWindow().setLayout(deviceWidth, deviceHeight * 6 / 10);
        dialogSpinnerGroup.show();
        editText_group.setEnabled(true);

        recyclerViewSpinnerGroup.addOnItemTouchListener(new FragmentFormReqCustAdminCompanyInfo.RecyclerTouchListener(getContext(),
                recyclerViewSpinnerGroup, new FragmentFormReqCustAdminCompanyInfo.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                String originText = "";
                if (updatedDataGroup != null) {
                    if (updatedDataGroup.size() > 0) {
                        originText = updatedDataGroup.get(position);
                    }
                } else {
                    originText = defaultDataGroup.get(position);
                }
                dialogSpinnerGroup.dismiss();
                editText_group.setText("" + originText);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        buttonCancelSpinnerGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogSpinnerGroup.dismiss();
            }
        });
    }

    // custom spinner group with search
    public Dialog dialogSpinnerGroup;
    public RecyclerView recyclerViewSpinnerGroup;
    public EditText editTextSpinnerSearchGroup;
    public ImageView imageViewSearchGroup;
    public Button buttonCancelSpinnerGroup;
    public CustomSpinnerAdapter customSpinnerGroupAdapter;
    public static List<String> defaultDataGroup, newListGroup;

    private final TextWatcher originWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.e("KeyListener", "" + editTextSpinnerSearchGroup.getText().toString());
            Log.e("KeyListener", "Size: " + defaultDataGroup.size());
            newListGroup = defaultDataGroup;
            updatedDataGroup = new ArrayList<String>();
            List<String> updateData = new ArrayList<String>();
            for (String item : newListGroup) {
                if (item.contains(editTextSpinnerSearchGroup.getText().toString().toUpperCase())) {
                    Log.e("KeyListener", "found");
                    updateData.add(item);
                }
            }
            Log.e("KeyListener", "Size new: " + updatedDataGroup.size());
            updatedDataGroup = updateData;
            customSpinnerGroupAdapter = new CustomSpinnerAdapter(getContext(), updateData);
            recyclerViewSpinnerGroup.setAdapter(customSpinnerGroupAdapter);
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private FragmentFormReqCustAdminCompanyInfo.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final FragmentFormReqCustAdminCompanyInfo.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    private void initializeComponent(View view) {
        loadDataSpinnerGroup();
        editText_customer_code.setText("");

        provinceList = new ArrayList<>();
        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.spinner_item, provinceList);
        autoComplete_province.setAdapter(provinceAdapter);

        cityList = new ArrayList<>();
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.spinner_item, cityList);
        autoComplete_city.setAdapter(cityAdapter);

        // format number for 2 number after comma
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);

        String hargaChangeDot, hargaFinal;
        String harga = "25.852.673,129";
        hargaChangeDot = harga.replace(".", "");
        hargaFinal = hargaChangeDot.replace(",", ".");

        Double doubleView = Double.parseDouble(hargaFinal);
        String formattedString = kursIndonesia.format(doubleView);

//        Toast.makeText(getActivity().getApplicationContext(), "Tessss  " + hargaChangeDot + " -- " +
//                hargaFinal + " -- " + formattedString, Toast.LENGTH_SHORT).show();

        // format number for 4 number after comma
        DecimalFormat kursIndonesiaFour = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRpFour = new DecimalFormatSymbols();
        kursIndonesiaFour.applyPattern("#,###,###,###,###.####");
        formatRpFour.setCurrencySymbol("");
        formatRpFour.setMonetaryDecimalSeparator(',');
        formatRpFour.setGroupingSeparator('.');
        kursIndonesiaFour.setDecimalFormatSymbols(formatRpFour);

        String hargaChangeDot2, hargaFinal2;
        String harga2 = "25.852.673,12909213";
        hargaChangeDot2 = harga2.replace(".", "");
        hargaFinal2 = hargaChangeDot2.replace(",", ".");

        Double doubleView2 = Double.parseDouble(hargaFinal2);
        String formattedString2 = kursIndonesiaFour.format(doubleView2);

//        Toast.makeText(getActivity().getApplicationContext(), "Tessss  " + hargaChangeDot2 + " -- " +
//                hargaFinal2 + " -- " + formattedString2, Toast.LENGTH_SHORT).show();

        // spinner for showing valuta option
        List<String> valutas = new ArrayList<String>();
        valutas.add("Rupiah");
        valutas.add("US Dollar");
        ArrayAdapter<String> valutaAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.spinner_item, valutas);
        valutaAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner_valuta.setAdapter(valutaAdapter);

        // code for checking active
        switch_active.setSwitchPadding(10);
        switch_active.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cekActive = 1;
                    textView_active.setText("" + cekActive);
                } else {
                    cekActive = 0;
                    textView_active.setText("" + cekActive);
                }
            }
        });

        // code for check switch before change switch city
        if (switch_active.isChecked()) {
            cekActive = 1;
            textView_active.setText("" + cekActive);
        } else {
            cekActive = 0;
            textView_active.setText("" + cekActive);
        }

        // code for checking active
        switch_tax.setSwitchPadding(10);
        switch_tax.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textView_tax_ppn.setText("1");
                } else {
                    textView_tax_ppn.setText("0");
                }
            }
        });

        // code for check switch before change switch tax
        if (switch_tax.isChecked()) {
            cekTax = "1";
            textView_tax_ppn.setText(cekTax);
        } else {
            cekTax = "0";
            textView_tax_ppn.setText(cekTax);
        }

        editText_group.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // To Validate multiple click from user
                // Allow only single request
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    // Prevent spinner from click, spinner disabled
                    editText_group.setEnabled(false);
                    // Call method that show dialog of list origin
                    showSearchableSpinnerGroup();
                }
                return false;
            }
        });

    }

    // code for getting all data customer group
    private static List<CustomerGroupModel> customerGroupModelList;

    void loadDataSpinnerGroup() {
        Call<ListCustomerGroupResponse> listCityResponseCallDestination = RestClient.getRestClient().getAllCustomerGroup("Bearer " + token);
        listCityResponseCallDestination.enqueue(new Callback<ListCustomerGroupResponse>() {
            @Override
            public void onResponse(Call<ListCustomerGroupResponse> call, Response<ListCustomerGroupResponse> response) {
                if (response.isSuccessful()) {
                    defaultDataGroup = new ArrayList<String>();
                    customerGroupModelList = new ArrayList<CustomerGroupModel>();
                    customerGroupModelList = response.body().getData();
                    for (CustomerGroupModel customerGroupModel : response.body().getData()) {
                        defaultDataGroup.add(customerGroupModel.getName());
                    }
                    loadData();
                    //buttonContinue.setEnabled(true);
                } else {
                    Log.e("CPBF", "Not successfull");
                }
            }

            @Override
            public void onFailure(Call<ListCustomerGroupResponse> call, Throwable t) {

            }
        });
    }

    public boolean isNotEmpty() {
        id = editText_customer_code.getText().toString();
        name = editText_first_name.getText().toString();
        address = editText_address.getText().toString();
        city = autoComplete_city.getText().toString();
        province = autoComplete_province.getText().toString();
        zip_code = editText_zip_code.getText().toString();
        phone = editText_phone.getText().toString();
        mobile = editText_mobile.getText().toString();
        fax = editText_fax.getText().toString();
        term = editText_term.getText().toString();
        valuta = spinner_valuta.getSelectedItem().toString();
        group = editText_group.getText().toString();
        npwp = editText_npwp.getText().toString();
        tax_ppn = textView_tax_ppn.getText().toString();
        active = textView_active.getText().toString();
        email = editText_email.getText().toString();
        website = editText_website.getText().toString();
        note = editText_note.getText().toString();

        boolean result = false;

        if (!name.equals("") && !address.equals("") && !city.equals("") && !province.equals("")
                && !mobile.equals("") && !npwp.equals("") && !email.equals("")) {
            result = true;
        }
        return result;
    }

    // code for saving to server
    public CustomerModel getFormValue() {
        String selectedGroup = "";
        for (CustomerGroupModel customerGroupModel : customerGroupModelList) {
            if (customerGroupModel.getName().equals(editText_group.getText().toString())) {
                selectedGroup = customerGroupModel.getDecode();
            }
        }

        CustomerModel customerModel = new CustomerModel();
        customerModel.setCode(id);
        customerModel.setFirst_name(name);
        customerModel.setLast_name(name);
        customerModel.setAddress(address);
        customerModel.setCity(selectedCity);
        customerModel.setProvince(selectedProvince);
        customerModel.setPhone(phone);
        customerModel.setMobile(mobile);
        customerModel.setFax(fax);
        customerModel.setTerm(term);
        customerModel.setValuta(valuta);
        customerModel.setGroup(selectedGroup);
        customerModel.setNpwp(npwp);
        customerModel.setTax(tax_ppn);
        customerModel.setEmail(email);
        customerModel.setWebsite(website);
        customerModel.setNote(note);
        customerModel.setPostcode(zip_code);

        FormCustomerActivity.customerModel_temp.setCode(id);
        FormCustomerActivity.customerModel_temp.setFirst_name(name);
        FormCustomerActivity.customerModel_temp.setLast_name(name);
        FormCustomerActivity.customerModel_temp.setAddress(address);
        FormCustomerActivity.customerModel_temp.setCity(selectedCity);
        FormCustomerActivity.customerModel_temp.setProvince(selectedProvince);
        FormCustomerActivity.customerModel_temp.setPhone(phone);
        FormCustomerActivity.customerModel_temp.setMobile(mobile);
        FormCustomerActivity.customerModel_temp.setFax(fax);
        FormCustomerActivity.customerModel_temp.setTerm(term);
        FormCustomerActivity.customerModel_temp.setValuta(valuta);
        FormCustomerActivity.customerModel_temp.setGroup(selectedGroup);
        FormCustomerActivity.customerModel_temp.setNpwp(npwp);
        FormCustomerActivity.customerModel_temp.setTax(tax_ppn);
        FormCustomerActivity.customerModel_temp.setEmail(email);
        FormCustomerActivity.customerModel_temp.setWebsite(website);
        FormCustomerActivity.customerModel_temp.setNote(note);
        FormCustomerActivity.customerModel_temp.setPostcode(zip_code);

        return customerModel;
    }

    // code for showing data from server
    private void loadData() {
        Toast.makeText(getActivity().getApplicationContext(), "" + decode, Toast.LENGTH_SHORT).show();

        if (!decode.equals("0")) {
            Call<CustomerResponse> customerResponseCall = RestClient.getRestClient().getCustomer("Bearer " + token, decode);
            customerResponseCall.enqueue(new Callback<CustomerResponse>() {
                @Override
                public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getData().size() > 0) {
                            CustomerModel customerModel = new CustomerModel();
                            customerModel = response.body().getData().get(0);
                            editText_customer_code.setText(customerModel.getCode() == null ? "" : customerModel.getCode());
                            editText_first_name.setText(customerModel.getFirst_name() == null ? "" : customerModel.getFirst_name());
                            editText_address.setText(customerModel.getAddress() == null ? "" : customerModel.getAddress());
                            autoComplete_city.setText(customerModel.getCity() == null ? "" : getCity(customerModel.getCity()));
                            autoComplete_province.setText(customerModel.getProvince() == null ? "" : getProvince(customerModel.getProvince()));
                            editText_phone.setText(customerModel.getPhone() == null ? "" : customerModel.getPhone());
                            editText_mobile.setText(customerModel.getMobile() == null ? "" : customerModel.getMobile());
                            editText_fax.setText(customerModel.getFax() == null ? "" : customerModel.getFax());
                            editText_term.setText(customerModel.getTerm() == null ? "" : customerModel.getTerm());
                            editText_npwp.setText(customerModel.getNpwp() == null ? "" : customerModel.getNpwp());
                            editText_email.setText(customerModel.getEmail() == null ? "" : customerModel.getEmail());
                            editText_website.setText(customerModel.getWebsite() == null ? "" : customerModel.getWebsite());
                            editText_note.setText(customerModel.getNote() == null ? "" : customerModel.getNote());
                            editText_zip_code.setText(customerModel.getPostcode() == null ? "" : customerModel.getPostcode());
                            textView_tax_ppn.setText(customerModel.getTax() == null ? "" : customerModel.getTax());

                            for (CustomerGroupModel customerGroupModel : customerGroupModelList) {
                                if (customerGroupModel.getDecode().equals(customerModel.getGroup())) {
                                    editText_group.setText(customerGroupModel.getName());
                                }
                            }

                            FormCustomerActivity.customerModel_temp.setCode(customerModel.getCode() == null ? "" : customerModel.getCode());
                            FormCustomerActivity.customerModel_temp.setFirst_name(customerModel.getFirst_name() == null ? "" : customerModel.getFirst_name());
                            FormCustomerActivity.customerModel_temp.setAddress(customerModel.getAddress() == null ? "" : customerModel.getAddress());
                            FormCustomerActivity.customerModel_temp.setCity(customerModel.getCity() == null ? "" : customerModel.getCity());
                            FormCustomerActivity.customerModel_temp.setPhone(customerModel.getPhone() == null ? "" : customerModel.getPhone());
                            FormCustomerActivity.customerModel_temp.setMobile(customerModel.getMobile() == null ? "" : customerModel.getMobile());
                            FormCustomerActivity.customerModel_temp.setFax(customerModel.getFax() == null ? "" : customerModel.getFax());
                            FormCustomerActivity.customerModel_temp.setTerm(customerModel.getTerm() == null ? "" : customerModel.getTerm());
                            FormCustomerActivity.customerModel_temp.setNpwp(customerModel.getNpwp() == null ? "" : customerModel.getNpwp());
                            FormCustomerActivity.customerModel_temp.setEmail(customerModel.getEmail() == null ? "" : customerModel.getEmail());
                            FormCustomerActivity.customerModel_temp.setWebsite(customerModel.getWebsite() == null ? "" : customerModel.getWebsite());
                            FormCustomerActivity.customerModel_temp.setNote(customerModel.getNote() == null ? "" : customerModel.getNote());
                            FormCustomerActivity.customerModel_temp.setPostcode(customerModel.getPostcode() == null ? "" : customerModel.getPostcode());
                            FormCustomerActivity.customerModel_temp.setTax(customerModel.getTax() == null ? "" : customerModel.getTax());

                            if (customerModel.getActive() == 1) {
                                switch_active.setChecked(true);
                            } else {
                                switch_active.setChecked(false);
                            }

                            String showTax = "";
                            showTax = customerModel.getTax();
                            Toast.makeText(getContext(), "Taxxx" + showTax, Toast.LENGTH_SHORT).show();
                            if (showTax.equals("1")) {
                                switch_tax.setChecked(true);
                            } else {
                                switch_tax.setChecked(false);
                            }

                            if (new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "roles").equals("admin")) {
                                if (customerModel.getApprove() == 1) {
                                    readOnly();
                                }
                            }
                            if (new SharedPreferenceManager().getPreferences(getActivity().getApplicationContext(), "roles").equals("customer")) {
                                if (customerModel.getApprove() == 1) {
                                    readOnly();
                                }
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<CustomerResponse> call, Throwable t) {

                }
            });
        }
    }

    public void readOnly() {
        editText_customer_code.setEnabled(false);
        editText_first_name.setEnabled(false);
        editText_address.setEnabled(false);
        autoComplete_city.setEnabled(false);
        autoComplete_province.setEnabled(false);
        editText_zip_code.setEnabled(false);
        editText_phone.setEnabled(false);
        editText_mobile.setEnabled(false);
        editText_fax.setEnabled(false);
        editText_group.setEnabled(false);
        editText_term.setEnabled(false);
        spinner_valuta.setEnabled(false);
        editText_npwp.setEnabled(false);
        textView_tax_ppn.setEnabled(false);
        textView_active.setEnabled(false);
        editText_email.setEnabled(false);
        editText_website.setEnabled(false);
        editText_note.setEnabled(false);
    }
}
