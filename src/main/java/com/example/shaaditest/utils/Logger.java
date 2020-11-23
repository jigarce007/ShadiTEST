package com.example.shaaditest.utils;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Logger {
    private static boolean DEBUG = true;

    public static void e(String tag, String arg) {
        if (isEnable()) {
            log(tag, arg);
        }
    }

    public static void e(String logMsg) {
        if (isEnable()) {
            log(getCurrentClassName(), getCurrentMethodName() + "(): " + logMsg);
        }
    }

    public static void d(String tag, Object source) {
        if (isEnable()) {
            Object o = getJsonObjFromStr(source);
            if (o != null) {
                try {
                    if (o instanceof JSONObject) {
                        format(tag, ((JSONObject) o).toString(2));
                    } else if (o instanceof JSONArray) {
                        format(tag, ((JSONArray) o).toString(2));
                    } else {
                        format(tag, source);
                    }
                } catch (JSONException e) {
                    format(tag, source);
                }
            } else {
                format(tag, source);
            }
        }
    }

    private static void log(String tag, String msg) {
        Log.e(tag, msg);
    }

    private static String getSplitter(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append("-");
        }
        return builder.toString();
    }

    private static void format(String tag, Object source) {
        tag = " " + tag + " ";
        log(" ", " ");
        log(" ", getSplitter(50) + tag + getSplitter(50));
        log(" ", "" + source);
        log(" ", getSplitter(100 + tag.length()));
        log(" ", " ");
    }

    private static String getCurrentMethodName() {
        return Thread.currentThread().getStackTrace()[4].getMethodName();
    }

    private static String getCurrentClassName() {
        String className = Thread.currentThread().getStackTrace()[4].getClassName();
        String[] temp = className.split("[\\.]");
        className = temp[temp.length - 1];
        return className;
    }

    @SuppressLint("NewApi")
	private static Object getJsonObjFromStr(Object test) {
        Object o = null;
        try {
            o = new JSONObject(test.toString());
        } catch (JSONException ex) {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    o = new JSONArray(test);
                }
            } catch (JSONException ex1) {
                return null;
            }
        }
        return o;
    }

    public static boolean isEnable() {
        return DEBUG;
    }

    public static void setEnable(boolean flag) {
        Logger.DEBUG = flag;
    }
}
