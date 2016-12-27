package uci.develops.wiraenergimobile.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.helper.JsonParse;
import uci.develops.wiraenergimobile.model.CityModel;

/**
 * Created by ArahitoPC on 12/22/2016.
 */

public class CityAdapter  extends ArrayAdapter<String>{
    protected static final String TAG = "CityAdapter";
    private List<String> cities;

    public CityAdapter(Context context, String nameFilter) {
        super(context, R.layout.spinner_dropdown_item);
        cities = new ArrayList<String>();
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public String getItem(int index) {
        return cities.get(index);
    }

    @Override
    public Filter getFilter(){
        Filter myFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                JsonParse jp=new JsonParse();
                if (constraint != null){
                    List<CityModel> new_city = jp.getparseJsonWCF();
                    cities.clear();
                    for (int i=0;i<new_city.size();i++){
                        cities.add(new_city.get(i).getCity_name());
                        Log.e(TAG, "Data Cities: " + new_city.get(i).getCity_name());
                    }

                    // Now assign the values and count to the FilterResults
                    // object
                    filterResults.values = cities;
                    filterResults.count = cities.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0){
                    notifyDataSetChanged();
                } else {
                    notifyDataSetChanged();
                }
            }
        };
        return myFilter;
    }
}
