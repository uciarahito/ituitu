package uci.develops.wiraenergimobile.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.concurrent.ExecutionException;

import uci.develops.wiraenergimobile.R;
import uci.develops.wiraenergimobile.activity.DashboardActivity;
import uci.develops.wiraenergimobile.activity.DashboardAdminActivity;
import uci.develops.wiraenergimobile.activity.DashboardCustomerActivity;
import uci.develops.wiraenergimobile.activity.LoginActivity;
import uci.develops.wiraenergimobile.activity.WaitingApprovalActivity;
import uci.develops.wiraenergimobile.helper.Constant;
import uci.develops.wiraenergimobile.helper.SharedPreferenceManager;

/**
 * Created by Belal on 3/18/2016.
 */
//Class extending service as it is a service that will run in background
public class NotificationListener extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //When the service is started
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Opening sharedpreferences
        SharedPreferences sharedPreferences = getSharedPreferences(Constant.SHARED_PREF, MODE_PRIVATE);

        //Getting the firebase id from sharedpreferences
        String id = sharedPreferences.getString(Constant.UNIQUE_ID, null);
        id = new SharedPreferenceManager().getPreferences(this, Constant.UNIQUE_ID);
        Log.e("NotificationListener", "UNIQUE ID: "+id);
        //Creating a firebase object
        Firebase firebase = new Firebase(Constant.FIREBASE_APP + id);

        //Adding a valueevent listener to firebase
        //this will help us to  track the value changes on firebase
        firebase.addValueEventListener(new ValueEventListener() {

            //This method is called whenever we change the value in firebase
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //Getting the value from firebase
                //We stored none as a initial value
                try {
                    String msg = snapshot.child("message").getValue().toString();
                    String tipe = snapshot.child("tipe").getValue().toString();
                    String time = snapshot.child("time").getValue().toString();

                    String time_fcm = new SharedPreferenceManager().getPreferences(NotificationListener.this, "time_fcm");

                    if(tipe.equals("approve_customer")){
                        showNotification(msg, tipe);
                    }
                    if(tipe.equals("feedback_customer")){
                        showNotification(msg, tipe);
                    }
                    if(tipe.equals("reject_customer")){
                        showNotification(msg, tipe);
                    }
                    if(tipe.equals("request_customer")){
                        showNotification(msg, tipe);
                    }

                    if(!time.equals(time_fcm)) {
                        new SharedPreferenceManager().setPreferences(NotificationListener.this, "time_fcm", time);
                        showNotification(msg, tipe);
                    }
                } catch (Exception e) {
                    Log.e("NotificationListener", "Gak masuk");
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e("The read failed: ", firebaseError.getMessage());
            }
        });

        return START_STICKY;
    }



    private void showNotification(String message, String tipe){
        Log.e("NotificationListener","Show notification");
        Boolean foreground = false;
        try {
            foreground = new BaseActivity().execute(getApplicationContext()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(tipe.equals("approve_customer")) {

            Intent intent;
            if(new SharedPreferenceManager().getPreferences(this, "is_login").equals("true")){
                intent = new Intent(this, DashboardCustomerActivity.class);
            } else {
                intent = new Intent(this, LoginActivity.class);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            int requestCode = 0;
            // Create destination after clicking notification
            PendingIntent pendingIntent = PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_ONE_SHOT);
            // Customize the notification
            android.support.v4.app.NotificationCompat.Builder noBuilder = new android.support.v4.app.NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Wira Energi Mobile")
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, noBuilder.build()); //0 = ID of notification
        }
        if(tipe.equals("feedback_customer")) {

            Intent intent;
            if(new SharedPreferenceManager().getPreferences(this, "is_login").equals("true")){
                intent = new Intent(this, WaitingApprovalActivity.class);
            } else {
                intent = new Intent(this, LoginActivity.class);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            int requestCode = 0;
            // Create destination after clicking notification
            PendingIntent pendingIntent = PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_ONE_SHOT);
            // Customize the notification
            android.support.v4.app.NotificationCompat.Builder noBuilder = new android.support.v4.app.NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Wira Energi Mobile")
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, noBuilder.build()); //0 = ID of notification
        }
        if(tipe.equals("reject_customer")) {
            Intent intent;
            if(new SharedPreferenceManager().getPreferences(this, "is_login").equals("true")){
                intent = new Intent(this, DashboardActivity.class);
            } else {
                intent = new Intent(this, LoginActivity.class);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            int requestCode = 0;
            // Create destination after clicking notification
            PendingIntent pendingIntent = PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_ONE_SHOT);
            // Customize the notification
            android.support.v4.app.NotificationCompat.Builder noBuilder = new android.support.v4.app.NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Wira Energi Mobile")
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, noBuilder.build()); //0 = ID of notification
        }
        if(tipe.equals("request_customer")) {
            Intent intent;
            if(new SharedPreferenceManager().getPreferences(this, "is_login").equals("true")){
                intent = new Intent(this, DashboardActivity.class);
            } else {
                intent = new Intent(this, LoginActivity.class);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            int requestCode = 0;
            // Create destination after clicking notification
            PendingIntent pendingIntent = PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_ONE_SHOT);
            // Customize the notification
            android.support.v4.app.NotificationCompat.Builder noBuilder = new android.support.v4.app.NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Wira Energi Mobile")
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, noBuilder.build()); //0 = ID of notification
        }

        /*
        if(!foreground)
        {
            if(tipe.equals("approve_customer")) {

                Intent intent;
                if(new SharedPreferenceManager().getPreferences(this, "is_login").equals("true")){
                    intent = new Intent(this, DashboardCustomerActivity.class);
                } else {
                    intent = new Intent(this, LoginActivity.class);
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                int requestCode = 0;
                // Create destination after clicking notification
                PendingIntent pendingIntent = PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_ONE_SHOT);
                // Customize the notification
                android.support.v4.app.NotificationCompat.Builder noBuilder = new android.support.v4.app.NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Wira Energi Mobile")
                        .setContentText(message)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, noBuilder.build()); //0 = ID of notification
            } else {
                Intent intent;
                if(new SharedPreferenceManager().getPreferences(this, "is_login").equals("true")){
                    intent = new Intent(this, DashboardActivity.class);
                } else {
                    intent = new Intent(this, LoginActivity.class);
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                int requestCode = 0;
                // Create destination after clicking notification
                PendingIntent pendingIntent = PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_ONE_SHOT);
                // Customize the notification
                android.support.v4.app.NotificationCompat.Builder noBuilder = new android.support.v4.app.NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Wira Energi Mobile")
                        .setContentText(message)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, noBuilder.build()); //0 = ID of notification
            }
        }
        else
        {
            Intent pushNotification = new Intent("pushNotification");
            pushNotification.putExtra("tipe", tipe);
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);
            Log.e("NotificationListener","Foreground");
        }
        */

    }
}
