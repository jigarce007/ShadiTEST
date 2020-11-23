package com.example.shaaditest;


import com.example.shaaditest.utils.CoreConstants;

public class AppConfiguration {

   String AppBaseURL = "";

    public AppConfiguration(String appBaseURL) {
        AppBaseURL = appBaseURL;

        CoreConstants.BASEURL = appBaseURL;
    }

    public String getAppBaseURL() {
        return AppBaseURL;
    }

    public void setAppBaseURL(String appBaseURL) {
        AppBaseURL = appBaseURL;
    }
}
