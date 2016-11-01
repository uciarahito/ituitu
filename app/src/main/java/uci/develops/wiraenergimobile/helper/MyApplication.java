package uci.develops.wiraenergimobile.helper;

import android.app.Application;
import android.content.Context;

import com.firebase.client.Firebase;

/**
 * Created by ArahitoPC on 11/1/2016.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Initializing firebase
        Firebase.setAndroidContext(getApplicationContext());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
