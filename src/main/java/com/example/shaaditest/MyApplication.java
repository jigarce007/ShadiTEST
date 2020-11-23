package com.example.shaaditest;

import android.app.Application;
import android.provider.SyncStateContract;

import com.example.shaaditest.utils.CoreConstants;
import com.example.shaaditest.utils.Logger;
import com.example.shaaditest.utils.URLConstants;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Logger.d("App","------------ Application Start ---------------------");

        String BaseURL = URLConstants.BASE_URL;
        Logger.e("BaseURL", "URL - "+BaseURL);

    }


}
