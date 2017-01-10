package com.example.sborick.raintoday.Alerts;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.sborick.raintoday.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AlertService extends IntentService {

    public AlertService() {
        super("ServiceIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String location = getLocation();
        String weatherData = getWeatherData(location);
        int chanceOfRain = rainToday(weatherData);
        if (chanceOfRain >  getCutofPoint()){
            createNotification("Chance of Rain", chanceOfRain);
        }
        else{
            createNotification("No Chance of Rain", chanceOfRain);
        }
    }

    public int rainToday(String data){
        int output = 0;
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
            output = (int) Math.floor((total/n)*100);
        }
        catch (Exception e){
            Log.d("service", e.toString());
            return -1;
        }
        return output;
    }

    public String getWeatherData(String location) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String output = "";

        try {
            URL url = new URL("https://api.darksky.net/forecast/807b43d6ad36e9be1387424334babb16/"+ location +"?exclude=currently,minutely,hourly,alerts,flags");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            output = buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output;
    }

    public void createNotification(String city, int chance) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle(city)
                        .setContentText(chance + "% chance of rain today.");
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

    public String getLocation(){
        SharedPreferences preferences = getSharedPreferences("data", 0);
        String lat = preferences.getString("lat", "0");
        String lon = preferences.getString("long", "0");
        return lat + "," + lon;
    }

    public int getCutofPoint(){
        SharedPreferences preferences = getSharedPreferences("data", 0);
        return preferences.getInt("cutofPoint", 30);
    }

}

