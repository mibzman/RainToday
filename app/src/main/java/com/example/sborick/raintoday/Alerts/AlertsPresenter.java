package com.example.sborick.raintoday.Alerts;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.widget.TextView;

import com.example.sborick.raintoday.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;


public class AlertsPresenter implements AlertsContract.Presenter {

    public static final String DATA = "data";
    private final AlertsContract.View view;
    private final Context context;

    public AlertsPresenter(AlertsContract.View alertsView, Context alertsContext){
        view = alertsView;
        context = alertsContext;
        view.setPresenter(this);
    }

    public void setNotificationAlarm(String text){
        AlarmManager alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, MyReceiver.class);
        intent.putExtra("text", text);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 41);

        alarmMgr.setInexactRepeating(AlarmManager.RTC, calendar.getTimeInMillis(),
                60 * 1000, alarmIntent);
    }


    @Override
    public void saveCutoff(int cutoff) {
        SharedPreferences preferences = context.getSharedPreferences(DATA, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("cutoff", cutoff);
        editor.apply();
    }

    @Override
    public void getCutoff() {
        SharedPreferences preferences = context.getSharedPreferences(DATA, 0);
        int cutoff = preferences.getInt("cutoff", 30);
        view.setCutoffText(cutoff);
    }

    @Override
    public void saveAlarm() {
        AlarmManager alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, MyReceiver.class);
        //intent.putExtra("text", text);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 13);
        calendar.set(Calendar.MINUTE, 19);

        alarmMgr.setInexactRepeating(AlarmManager.RTC, calendar.getTimeInMillis(),
                60 * 1000, alarmIntent);
    }

    @Override
    public void searchCityName(String name) {
        new GetCityNameTask().execute(name);
    }

    @Override
    public void getCityName() {
        SharedPreferences preferences = context.getSharedPreferences(DATA, 0);
        String city = preferences.getString("city", "Lakewood");
        view.setCityText(city);
    }

    @Override
    public void getSavedLocation() {
        SharedPreferences preferences = context.getSharedPreferences(DATA, 0);
        String lat = preferences.getString("lat", "41.4820");
        String lon = preferences.getString("long", "81.7982");
        view.setLocationTexts(lat, lon);
    }

    @Override
    public void saveLocation(String lat, String lon) {
        SharedPreferences preferences = context.getSharedPreferences(DATA, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("lat", lat);
        editor.putString("lon", lon);
        editor.apply();
    }

    private class GetCityNameTask extends AsyncTask<String, Void, Address>{

        @Override
        protected Address doInBackground(String... strings) {
            if(Geocoder.isPresent()){
                try {
                    Geocoder gc = new Geocoder(context);
                    List<Address> addresses= gc.getFromLocationName(strings[0], 1); // get the found Address Objects

                    for(Address a : addresses){
                        if(a.hasLatitude() && a.hasLongitude()){
                            //Double[] output = {a.getLatitude(), a.getLongitude()};
                            return a;
                        }
                    }
                } catch (IOException e) {
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Address address) {
            if (address != null){
                view.setLocationTexts(Double.toString(address.getLatitude()), Double.toString(address.getLongitude()));
                view.makeToast(address.getAddressLine(0));
            }else{
                view.makeToast("City could not be found");
            }
        }
    }
}