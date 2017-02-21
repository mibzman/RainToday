package com.example.sborick.raintoday.Alerts;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;


public class AlertsPresenter implements AlertsContract.Presenter {

    private static final String DATA = "data";
    private final AlertsContract.View view;
    private final Context context;
    private final PendingIntent alarmIntent;

    public AlertsPresenter(AlertsContract.View alertsView, Context alertsContext){
        view = alertsView;
        context = alertsContext;
        view.setPresenter(this);
        alarmIntent = PendingIntent.getBroadcast(context, 0,
                new Intent(context, MyReceiver.class), 0);
    }

    @Override
    public void saveCutoff(int cutoff, DataSaver_ dataSaver) {
        dataSaver.cutoff().put(cutoff);
    }

    @Override
    public void getCutoff(DataSaver_ dataSaver) {
        int cutoff = dataSaver.cutoff().get();
        view.setCutoffText(cutoff);
    }

    @Override
    public void saveAlarm() {
        AlarmManager alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 4);
        calendar.set(Calendar.MINUTE, 30);

        alarmMgr.setInexactRepeating(AlarmManager.RTC, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);

        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    @Override
    public void cancelAlarm() {
        AlarmManager alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        if (alarmMgr!= null) {
            alarmMgr.cancel(alarmIntent);
        }
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    @Override
    public void searchCityName(String name) {
        new GetCityNameTask().execute(name);
    }

    @Override
    public void getCityName(DataSaver_ dataSaver) {
        String city = dataSaver.city().get();
        view.setCityText(city);
    }

    @Override
    public void getSavedLocation(DataSaver_ dataSaver) {
        String lat = dataSaver.lat().get();
        String lon = dataSaver.lon().get();
        view.setLocationTexts(lat, lon);
    }

    @Override
    public void saveLocation(String lat, String lon, DataSaver_ dataSaver) {
        dataSaver.lat().put(lat);
        dataSaver.lon().put(lon);
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
                    Log.d("asyncTask", e.getMessage());
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