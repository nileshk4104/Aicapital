package com.tech.aicapital.notification;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtility {

    private static final String PREF_FILE = "APPLICATION_DATA";
    private static SharedPreferences sharedPreferences = null;

    public static void openPref(Context context) {

        sharedPreferences = context.getSharedPreferences(PREF_FILE,
                Context.MODE_PRIVATE);
    }

    public static String getValue(Context context, String key,
                                  String defaultValue) {
        String result = "";
        if (null != context) {
            PrefUtility.openPref(context);
            if (PrefUtility.sharedPreferences != null) {
                result = PrefUtility.sharedPreferences.getString(key, defaultValue);
            }
            PrefUtility.sharedPreferences = null;
        }

        return result;
    }


    public static void setValue(Context context, String key, String value) {
        PrefUtility.openPref(context);
        SharedPreferences.Editor prefsPrivateEditor = PrefUtility.sharedPreferences.edit();
        prefsPrivateEditor.putString(key, value);
        prefsPrivateEditor.commit();
        prefsPrivateEditor = null;
        PrefUtility.sharedPreferences = null;
    }


}
