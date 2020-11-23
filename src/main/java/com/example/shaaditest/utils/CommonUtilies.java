package com.example.shaaditest.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtilies {

    public static boolean isNetworkAvailable(Activity activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    //date formate by jigar..
    //For isAPIformat === >  pass "1" in case its an api request...else pass"0"....
   /* public static String dateFormate(String dateFormat,String inputDateStr,String isAPIformat){
        String outputDateStr = "";
        DateFormat inputFormat;
        if(isAPIformat.equals("1")){
            inputFormat  = new SimpleDateFormat("dd/MM/yyyy");
        }else {
             inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        }

        DateFormat outputFormat = new SimpleDateFormat(dateFormat);

        Date date = null;
        try {
            if (!inputDateStr.equals("")){
                date = inputFormat.parse(inputDateStr);
                outputDateStr = outputFormat.format(date);
            }else {
                outputDateStr = "-";
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return outputDateStr;
    }
*/
    //end


    //override date format..
    public static String dateFormate(String inputFormate, String outputFormate, String inputDate){
        String outputDateStr = "";
        DateFormat inputFormat;
        inputFormat  = new SimpleDateFormat(inputFormate);
        DateFormat outputFormat = new SimpleDateFormat(outputFormate);

        Date date = null;
        try {
            if (!inputDate.equals("")){
                date = inputFormat.parse(inputDate);
                outputDateStr = outputFormat.format(date);
            }else {
                outputDateStr = "-";
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return outputDateStr;
    }

    public static String getCurrentdateFormat(String outputFormate){
        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(outputFormate);
        String todayString = formatter.format(todayDate);

        return todayString;
    }



    //end

    //to check string is a date..!!
    //0 == for payment history ...1 = ticket history
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean isdate(String inputDate, String dateFormate){
        try {
                new SimpleDateFormat(dateFormate).parse(inputDate);
            return true;
            //IS VALID DATE
        } catch (ParseException e) {
            return false;
            // NOT A DATE
        }
    }




    //end


    public static boolean isStringBlank(String strValue){

        if(strValue.trim().isEmpty() == true
                || strValue.trim().equals("")
                || strValue == null
        || strValue.equals("null")){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean CompareString(String firstText, String compareText){

        if(firstText.equals(compareText) == true){
            return  true;
        }else{
            return false;
        }
    }

    public static int StringCount(String text){

        int count;
        count = text.length();

        return  count;
    }
    //Date difference..!!
    // falg === > 0 == for payment history ...1 = ticket history
    public static boolean DateDifference(String startDate, String endDate, int flag){
        SimpleDateFormat simpleDateFormat;
        if (flag == 1){
            simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd");

        }else if(flag == 2){
            simpleDateFormat  = new SimpleDateFormat("dd-MM-yyyy");
        }
        else {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        }


        long days = 0;
        try {
            Date date1 = simpleDateFormat.parse(startDate);
            Date date2 = simpleDateFormat.parse(endDate);
            days =  printDifference(date1, date2);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (days < CoreConstants.DATE_RANGE_VALUE) {
                return true;
         } else {
                return false;
        }

    }


    public static long printDifference(Date startDate, Date endDate) {
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();
        long months;
        System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);
        Logger.e("days", "days : "+ elapsedDays);

        Logger.e("Months", String.format("%d months", elapsedDays/30));
        return elapsedDays;
    }

    public static String milisToDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    //file downloder..
    public static void DownloadFiles(Context context, String url){

        DownloadManager downloadmanager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);

        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle("My File");
        request.setDescription("Downloading");//request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        Toast.makeText(context,"Invoice is downloading", Toast.LENGTH_SHORT).show();
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"intelvision");
        downloadmanager.enqueue(request);
    }

    public static String getTimeStampToDate(String timestamp, String DateFormat){

        if(CommonUtilies.isStringBlank(timestamp) ==  false) {

            long milliseconds = Long.parseLong(timestamp);

            Timestamp stamp = new Timestamp(milliseconds);
            java.sql.Date date = new java.sql.Date(stamp.getTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat(DateFormat);
            return dateFormat.format(date);
        }else{
            return "-";
        }
    }

    public static long getDatetoTimestamp(String strdate, String DateFormat){

        java.sql.Date date = null;
        try {
            date = (java.sql.Date) new SimpleDateFormat(DateFormat).parse(strdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  date.getTime();
    }

    public static long getTimeStamp(boolean isCurrent, int days){

        Calendar cal = Calendar.getInstance();
        if(isCurrent == true){

        }else{
            cal.add(Calendar.DATE, days);
        }

        return cal.getTimeInMillis();

    }


    }


