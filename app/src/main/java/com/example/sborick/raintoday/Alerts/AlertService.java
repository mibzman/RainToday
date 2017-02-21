package com.example.sborick.raintoday.Alerts;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.sborick.raintoday.R;

import org.androidannotations.annotations.EService;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

@EService
public class AlertService extends IntentService {

    public AlertService() {
        super("ServiceIntentService");
    }

    @Pref
    DataSaver_ dataSaver;

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("service", "service hit");
        String location = getLocation();
        WeatherDataGetter getter = new WeatherDataGetter();
        String weatherData = getter.getWeatherData(location);
        int chanceOfRain = rainToday(weatherData);
        if (isInternetAvailable()){
            if (chanceOfRain >  getCutoffPoint()){
                createNotification("Chance of Rain", chanceOfRain);
            }else if (chanceOfRain < getCutoffPoint() && chanceOfRain > 0){
                createNotification("Low Chance of Rain", chanceOfRain);
            }
            else{
                createNotification("No Chance of Rain", chanceOfRain);
            }
        }else{
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.ic_launcher)
                            .setContentTitle("No Internet Connection")
                            .setContentText("This has been a courtesy notification");
            PendingIntent resultPendingIntent =
                    PendingIntent.getActivity(
                            this,
                            0,
                            new Intent(this, AlertsActivity_.class),
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );
            mBuilder.setContentIntent(resultPendingIntent);
            int mNotificationId = 001;
            NotificationManager mNotifyMgr =
                    (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
            mNotifyMgr.notify(mNotificationId, mBuilder.build());
        }

        //createNotification("Chance of Rain", 40);
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com"); //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }

    }

    public int rainToday(String data){
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONObject jdaily = jsonObject.getJSONObject("daily");
            JSONArray jdata = jdaily.getJSONArray("data");
            double total = 0;
            int n;
            for(n = 0; n < jdata.length(); n++)
            {
                JSONObject object = jdata.getJSONObject(n);
                double prob = object.getDouble("precipProbability");
                total += prob;
            }
            return (int) Math.floor((total/n)*100);
        }
        catch (Exception e){
            Log.d("service", e.toString());
            return -1;
        }
    }


    private void createNotification(String city, int chance) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle(city)
                        .setContentText(chance + "% chance of precipitation today.");
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        new Intent(this, AlertsActivity_.class),
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        int mNotificationId = 001;
        NotificationManager mNotifyMgr =
                (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }

    private String getLocation(){
        return dataSaver.lat().get() + "," + dataSaver.lon().get();
    }

    private int getCutoffPoint(){
        return dataSaver.cutoff().get();
    }

}

