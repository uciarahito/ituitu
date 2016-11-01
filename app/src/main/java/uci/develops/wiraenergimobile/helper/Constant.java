package uci.develops.wiraenergimobile.helper;

import android.content.Context;
import android.content.Intent;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uci.develops.wiraenergimobile.activity.LoginActivity;
import uci.develops.wiraenergimobile.fcm.NotificationListener;
import uci.develops.wiraenergimobile.model.UserModel;

/**
 * Created by user on 10/22/2016.
 */
public class Constant {
    /**
     * user authentication
     */
    public boolean is_login = false;
    public int id_login = 0;
    public String role_login = "";
    public static String FIREBASE_APP = "https://wiraenergimobile.firebaseio.com/";
    public static final String SHARED_PREF = "mynotificationapp";
    public static final String REGISTERED = "registered";
    public static final String UNIQUE_ID = "uniqueid";

    /**
     * semi database
     */
    public static List<List<String>> user_data = new ArrayList<>();
    public static List<String> role_data = new ArrayList<>();

    public static boolean isAdmin(){
        boolean result = false;
        if(Constant.role_data.size() > 0){
            for(String role : Constant.role_data){
                if(role.equals("2")){
                    result = true;
                }
            }
        }
        return result;
    }

    public static boolean isMobile(){
        boolean result = false;
        if(Constant.role_data.size() > 0){
            for(String role : Constant.role_data){
                if(role.equals("3")){
                    result = true;
                }
            }
        }
        return result;
    }

    public static boolean isCustomer(){
        boolean result = false;
        if(Constant.role_data.size() > 0){
            for(String role : Constant.role_data){
                if(role.equals("4")){
                    result = true;
                }
            }
        }
        return result;
    }

    public static void sendNotification(String unique_id, String message, String tipe){
        Firebase firebase = new Firebase(Constant.FIREBASE_APP+unique_id);

        //Pushing a new element to firebase it will automatically create a unique id
        Firebase newFirebase = firebase.push();

        //Creating a map to store name value pair
        Map<String, String> val = new HashMap<>();

        //pushing msg = none in the map
        val.put("message", ""+message);
        val.put("tipe",""+tipe);
        val.put("time", "" + new SharedPreferenceManager().getCurrentDateTime());
        //saving the map to firebase
        newFirebase.setValue(val);
    }
}
