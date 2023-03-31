package com.tech.aicapital;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by saurabha on 10/12/18.
 */
public class InstallReferrerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.android.vending.INSTALL_REFERRER")) {
            String referrer = "";
            Bundle extras = intent.getExtras();
            if (extras != null) {
                referrer = extras.getString("referrer");
            }
            Log.e(TAG, "Referal Code Is: " + referrer);

            Pref.setValue(context, "REF", referrer);

            // PrefUtility.setValue(context,"REF_CODE",referrer);

        }
    }
}

//Https://play.google.com/store/apps/details?id=com.techerevolution.photostory&Referrer=HPYX8X

//adb shell
//am broadcast -a com.android.vending.INSTALL_REFERRER -n com.techerevolution.photostory/com.techerevolution.photostory.InstallReferrerReceiver --es "referrer" "Saurabh"