package uci.develops.wiraenergimobile.helper;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

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
}
