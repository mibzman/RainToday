package com.example.sborick.raintoday.Alerts;

import org.androidannotations.annotations.sharedpreferences.DefaultInt;
import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by sborick on 2/21/2017.
 */

@SharedPref(SharedPref.Scope.UNIQUE)
public interface DataSaver {
    @DefaultInt(30)
    int cutoff();

    @DefaultString("Lakewood")
    String city();

    @DefaultString("41.4820")
    String lat();

    @DefaultString("-81.7982")
    String lon();
}
