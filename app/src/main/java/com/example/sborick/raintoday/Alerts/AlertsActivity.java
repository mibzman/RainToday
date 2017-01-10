package com.example.sborick.raintoday.Alerts;
                
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sborick.raintoday.ActivityUtils;
import com.example.sborick.raintoday.R;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_alerts)
public class AlertsActivity extends AppCompatActivity {

    private AlertsPresenter presenter;

    @AfterViews
    void afterViewsLoaded() {
        AlertsFragment_ alertsFragment = (AlertsFragment_) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (alertsFragment == null) {
            alertsFragment = new AlertsFragment_();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), alertsFragment, R.id.contentFrame);
        }
        presenter = new AlertsPresenter(alertsFragment, this);
    }
}