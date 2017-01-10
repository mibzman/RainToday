package com.example.sborick.raintoday.Alerts;
                
import android.support.v7.app.AppCompatActivity;

import com.example.sborick.raintoday.ActivityUtils;
import com.example.sborick.raintoday.R;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_alerts)
public class AlertsActivity extends AppCompatActivity {

    @AfterViews
    void afterViewsLoaded() {
        AlertsFragment_ alertsFragment = (AlertsFragment_) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (alertsFragment == null) {
            alertsFragment = new AlertsFragment_();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), alertsFragment, R.id.contentFrame);
        }
        AlertsPresenter presenter = new AlertsPresenter(alertsFragment, this);
    }
}