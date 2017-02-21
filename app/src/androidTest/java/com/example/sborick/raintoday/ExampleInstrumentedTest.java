package com.example.sborick.raintoday;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.sborick.raintoday.Alerts.AlertService;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void getWeatherDataGetsData(){
        Context context = InstrumentationRegistry.getTargetContext();


        AlertService service = new AlertService();
        //String jsonData = service.getWeatherData("0,0");

       // assertNotNull(jsonData);
    }

    @Test
    public void willItRainEvaluatesCorrectly(){
        Context context = InstrumentationRegistry.getTargetContext();

        AlertService service = new AlertService();

        String dummyJSON = "{\"latitude\":41.481993,\"longitude\":-81.798191,\"timezone\":\"America/New_York\",\"offset\":-5,\"daily\":{\"summary\":\"Mixed precipitation tomorrow through Monday, with temperatures rising to 51°F on Thursday.\",\"icon\":\"snow\",\"data\":[{\"time\":1483938000,\"summary\":\"Snow (under 1 in.) overnight.\",\"icon\":\"snow\",\"sunriseTime\":1483966459,\"sunsetTime\":1484000204,\"moonPhase\":0.4,\"precipIntensity\":0,\"precipIntensityMax\":0,\"precipProbability\":0,\"temperatureMin\":13.09,\"temperatureMinTime\":1483938000,\"temperatureMax\":27.81,\"temperatureMaxTime\":1483992000,\"apparentTemperatureMin\":0.81,\"apparentTemperatureMinTime\":1483956000,\"apparentTemperatureMax\":19.01,\"apparentTemperatureMaxTime\":1483992000,\"dewPoint\":12.83,\"humidity\":0.72,\"windSpeed\":9.63,\"windBearing\":179,\"visibility\":9.35,\"cloudCover\":0.85,\"pressure\":1032.93,\"ozone\":347.17},{\"time\":1484024400,\"summary\":\"Mixed precipitation (1–2 in. of snow) throughout the day and windy starting in the afternoon, continuing until evening.\",\"icon\":\"rain\",\"sunriseTime\":1484052844,\"sunsetTime\":1484086667,\"moonPhase\":0.44,\"precipIntensity\":0.0209,\"precipIntensityMax\":0.0443,\"precipIntensityMaxTime\":1484082000,\"precipProbability\":0.59,\"precipType\":\"snow\",\"precipAccumulation\":4.123,\"temperatureMin\":25.63,\"temperatureMinTime\":1484024400,\"temperatureMax\":41.49,\"temperatureMaxTime\":1484100000,\"apparentTemperatureMin\":14.45,\"apparentTemperatureMinTime\":1484024400,\"apparentTemperatureMax\":31.57,\"apparentTemperatureMaxTime\":1484100000,\"dewPoint\":31.24,\"humidity\":0.89,\"windSpeed\":20.94,\"windBearing\":172,\"visibility\":6.54,\"cloudCover\":1,\"pressure\":1016.48,\"ozone\":297.53},{\"time\":1484110800,\"summary\":\"Light rain starting in the evening.\",\"icon\":\"rain\",\"sunriseTime\":1484139227,\"sunsetTime\":1484173131,\"moonPhase\":0.47,\"precipIntensity\":0.0067,\"precipIntensityMax\":0.0298,\"precipIntensityMaxTime\":1484179200,\"precipProbability\":0.55,\"precipType\":\"rain\",\"temperatureMin\":28.75,\"temperatureMinTime\":1484136000,\"temperatureMax\":46.74,\"temperatureMaxTime\":1484193600,\"apparentTemperatureMin\":19.99,\"apparentTemperatureMinTime\":1484136000,\"apparentTemperatureMax\":40.01,\"apparentTemperatureMaxTime\":1484193600,\"dewPoint\":36.16,\"humidity\":0.95,\"windSpeed\":12.55,\"windBearing\":182,\"visibility\":7.48,\"cloudCover\":0.77,\"pressure\":1017.93,\"ozone\":321.51},{\"time\":1484197200,\"summary\":\"Light rain throughout the day.\",\"icon\":\"rain\",\"sunriseTime\":1484225608,\"sunsetTime\":1484259596,\"moonPhase\":0.52,\"precipIntensity\":0.0134,\"precipIntensityMax\":0.0295,\"precipIntensityMaxTime\":1484265600,\"precipProbability\":0.55,\"precipType\":\"rain\",\"temperatureMin\":31.59,\"temperatureMinTime\":1484280000,\"temperatureMax\":50.66,\"temperatureMaxTime\":1484251200,\"apparentTemperatureMin\":23.77,\"apparentTemperatureMinTime\":1484280000,\"apparentTemperatureMax\":50.66,\"apparentTemperatureMaxTime\":1484251200,\"dewPoint\":45.53,\"humidity\":0.97,\"windSpeed\":12.47,\"windBearing\":203,\"visibility\":4.64,\"cloudCover\":0.97,\"pressure\":1014.01,\"ozone\":293.83},{\"time\":1484283600,\"summary\":\"Mostly cloudy throughout the day.\",\"icon\":\"partly-cloudy-day\",\"sunriseTime\":1484311987,\"sunsetTime\":1484346062,\"moonPhase\":0.55,\"precipIntensity\":0.0006,\"precipIntensityMax\":0.0024,\"precipIntensityMaxTime\":1484287200,\"precipProbability\":0.07,\"precipType\":\"snow\",\"precipAccumulation\":0.156,\"temperatureMin\":21.61,\"temperatureMinTime\":1484366400,\"temperatureMax\":31.69,\"temperatureMaxTime\":1484283600,\"apparentTemperatureMin\":11.3,\"apparentTemperatureMinTime\":1484366400,\"apparentTemperatureMax\":23.54,\"apparentTemperatureMaxTime\":1484283600,\"dewPoint\":19.72,\"humidity\":0.77,\"windSpeed\":9.14,\"windBearing\":356,\"cloudCover\":0.9,\"pressure\":1037.48,\"ozone\":311.64},{\"time\":1484370000,\"summary\":\"Mixed precipitation until afternoon, starting again in the evening.\",\"icon\":\"snow\",\"sunriseTime\":1484398364,\"sunsetTime\":1484432529,\"moonPhase\":0.58,\"precipIntensity\":0.0036,\"precipIntensityMax\":0.0107,\"precipIntensityMaxTime\":1484449200,\"precipProbability\":0.45,\"precipType\":\"snow\",\"precipAccumulation\":0.858,\"temperatureMin\":17.87,\"temperatureMinTime\":1484384400,\"temperatureMax\":29.64,\"temperatureMaxTime\":1484442000,\"apparentTemperatureMin\":6.46,\"apparentTemperatureMinTime\":1484384400,\"apparentTemperatureMax\":22.43,\"apparentTemperatureMaxTime\":1484442000,\"dewPoint\":20.48,\"humidity\":0.8,\"windSpeed\":9.05,\"windBearing\":64,\"cloudCover\":0.99,\"pressure\":1037.1,\"ozone\":260.3},{\"time\":1484456400,\"summary\":\"Mixed precipitation starting in the evening.\",\"icon\":\"snow\",\"sunriseTime\":1484484738,\"sunsetTime\":1484518998,\"moonPhase\":0.62,\"precipIntensity\":0.0067,\"precipIntensityMax\":0.0181,\"precipIntensityMaxTime\":1484539200,\"precipProbability\":0.5,\"precipType\":\"sleet\",\"temperatureMin\":23.78,\"temperatureMinTime\":1484474400,\"temperatureMax\":32.91,\"temperatureMaxTime\":1484524800,\"apparentTemperatureMin\":14.05,\"apparentTemperatureMinTime\":1484474400,\"apparentTemperatureMax\":26.05,\"apparentTemperatureMaxTime\":1484524800,\"dewPoint\":27.1,\"humidity\":0.92,\"windSpeed\":8.04,\"windBearing\":57,\"cloudCover\":1,\"pressure\":1029.48,\"ozone\":246.15},{\"time\":1484542800,\"summary\":\"Light rain in the morning and afternoon.\",\"icon\":\"rain\",\"sunriseTime\":1484571111,\"sunsetTime\":1484605467,\"moonPhase\":0.65,\"precipIntensity\":0.01,\"precipIntensityMax\":0.0188,\"precipIntensityMaxTime\":1484542800,\"precipProbability\":0.5,\"precipType\":\"rain\",\"temperatureMin\":30.9,\"temperatureMinTime\":1484542800,\"temperatureMax\":49.54,\"temperatureMaxTime\":1484614800,\"apparentTemperatureMin\":25.08,\"apparentTemperatureMinTime\":1484542800,\"apparentTemperatureMax\":43.36,\"apparentTemperatureMaxTime\":1484614800,\"dewPoint\":41.48,\"humidity\":0.96,\"windSpeed\":10.82,\"windBearing\":175,\"cloudCover\":0.99,\"pressure\":1016.28,\"ozone\":249.52}]}}";

        int chanceOfRain = service.rainToday(dummyJSON);
        assertEquals(40, chanceOfRain);
    }
}
