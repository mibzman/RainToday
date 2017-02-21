package com.example.sborick.raintoday.Alerts;


public interface AlertsContract {
    interface View{
        //methods that do something to the ui
        void setPresenter(Presenter presenter);
        void setLocationTexts(String lat, String lon);
        void setCityText(String name);
        void setCutoffText(int cutoff);
        void makeToast(String text);
    }

    interface Presenter{
        void saveCutoff(int cutoff, DataSaver_ dataSaver);
        void getCutoff(DataSaver_ dataSaver);
        void saveAlarm();
        void cancelAlarm();
        void searchCityName(String name);
        void getCityName(DataSaver_ dataSaver);
        void getSavedLocation(DataSaver_ dataSaver);
        void saveLocation(String lat, String lon, DataSaver_ dataSaver);
    }
}