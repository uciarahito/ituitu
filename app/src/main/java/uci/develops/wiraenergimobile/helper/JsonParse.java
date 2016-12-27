package uci.develops.wiraenergimobile.helper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import uci.develops.wiraenergimobile.model.CityModel;

/**
 * Created by ArahitoPC on 12/22/2016.
 */

public class JsonParse {
    public JsonParse() {
    }

    public List<CityModel> getparseJsonWCF() {
        List<CityModel> cityModels = new ArrayList<CityModel>();
        try {
            URL js = new URL("../city.json");
            URLConnection jc = js.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(jc.getInputStream()));
            String line = reader.readLine();
            JSONArray cities = new JSONArray(line);

            //looping through All data
            for (int i = 0; i < cities.length(); i++) {
                JSONObject c = cities.getJSONObject(i);

                String city_id = c.getString("city_id");
                String province_id = c.getString("province_id");
                String province = c.getString("province");
                String type = c.getString("type");
                String city_name = c.getString("city_name");
                String postal_code = c.getString("postal_code");
                cityModels.add(new CityModel(city_name));
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return cityModels;
    }
}
