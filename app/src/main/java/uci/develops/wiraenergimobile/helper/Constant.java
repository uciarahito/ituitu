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

    /**
     * semi database
     */
    public static List<List<String>> user_data = new ArrayList<>();
}
