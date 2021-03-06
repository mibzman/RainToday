package com.example.sborick.raintoday.Alerts;

import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sborick.raintoday.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;


@EFragment(R.layout.fragment_alerts)
public class AlertsFragment extends Fragment implements AlertsContract.View {

    private AlertsContract.Presenter presenter;

    @Pref
    DataSaver_ dataSaver;

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
        presenter.getCityName(dataSaver);
        presenter.getCutoff(dataSaver);
        presenter.getSavedLocation(dataSaver);
        //test
    }

    @Override
    public void setPresenter(AlertsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Click(R.id.search)
    void searchForCity(){
        presenter.searchCityName(city.getText().toString());
    }

    @Click(R.id.save)
    void save(){
        presenter.saveCutoff(Integer.parseInt(cutoff.getText().toString()), dataSaver);
        presenter.saveLocation(lat.getText().toString(), lon.getText().toString(), dataSaver);
        presenter.saveAlarm();
    }

    @Click(R.id.cancel)
    void cancel(){
        presenter.cancelAlarm();
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

    @Override
    public void makeToast(String text) {
        Toast.makeText(getActivity(), text,
                Toast.LENGTH_LONG).show();
    }
}