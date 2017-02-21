package com.example.sborick.raintoday.Alerts;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sborick on 1/24/2017.
 */

public class WeatherDataGetter {

    final String KEY = "807b43d6ad36e9be1387424334babb16";

    public String getWeatherData(String location) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String output = "";

        try {
            String resource = "https://api.darksky.net/forecast/"+ KEY +"/"+ location +"?exclude=currently,minutely,hourly,alerts,flags";
            Log.d("service", resource);
            URL url = new URL(resource);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuilder buffer = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
            }

            output = buffer.toString();
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
}
