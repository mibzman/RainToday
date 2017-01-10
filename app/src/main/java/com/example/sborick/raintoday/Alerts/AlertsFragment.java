package com.example.sborick.raintoday.Alerts;

import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sborick.raintoday.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;


@EFragment(R.layout.fragment_alerts)
public class AlertsFragment extends Fragment implements AlertsContract.View {

    AlertsContract.Presenter presenter;

    @ViewById
    EditText city;

    @ViewById
    EditText lat;

    @ViewById
    EditText lon;

    @ViewById
    EditText cutoff;

    @AfterViews
    void setTexts(){
        presenter.getCityName();
        presenter.getCutoff();
        presenter.getSavedLocation();
    }

    @Click(R.id.search)
    void searchForCity(){
        presenter.searchCityName(city.getText().toString());
    }

    @Click(R.id.setLocation)
    void setLocation(){
        presenter.saveLocation(lat.getText().toString(), lon.getText().toString());
    }

    @Click(R.id.setCutoff)
    void setCutoff(){
        presenter.saveCutoff(Integer.parseInt(lat.getText().toString()));
    }


    @Override
    public void setPresenter(AlertsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setLocationTexts(String lat, String lon) {
        this.lon.setText(lon);
        this.lat.setText(lat);
    }

    @Override
    public void setCityText(String name) {
        city.setText(name);
    }

    @Override
    public void setCutoffText(int cutoffText) {
        this.cutoff.setText("" + cutoffText + "");
    }
}