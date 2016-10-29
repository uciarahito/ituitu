package uci.develops.wiraenergimobile.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.ArraySet;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * Created by user on 10/22/2016.
 */
public class SharedPreferenceManager {
    public void setPreferences(Context context, String key, String value){
        /*
            Fungsi ini untuk menambahkan atau menyimpan sesuatu dengan kata kunci (key) berupa String
            MODE_PRIVATE : Agar session hanya bisa diakses oleh aplikasi ini saja
         */
        SharedPreferences.Editor editor = context.getSharedPreferences("WiraEnergi", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getPreferences(Context context, String key){
        /*
            Fungsi ini untuk mengambil atau mendapatkan nilai (string) yang tersimpan berdasarkan key
         */
        SharedPreferences preferences = context.getSharedPreferences("WiraEnergi", Context.MODE_PRIVATE);
        String position = preferences.getString(key, "");
        return position;
    }

    public String getCurrentDateTime(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
        String dateandtime = ""+mdformat.format(calendar.getTime())+" "+timeformat.format(calendar.getTime());
        return dateandtime;
    }

    public String getToken(Context context){
        return new SharedPreferenceManager().getPreferences(context, "token");
    }

    public boolean isCustomer(Context context){
        if(new SharedPreferenceManager().getPreferences(context, "role").equals("customer")){
            return true;
        }
        return false;
    }

    public String generateRandomString(int passwordSize){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < passwordSize; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        return output;
    }
}
