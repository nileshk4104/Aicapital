package com.tech.aicapital;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.WindowManager;

import java.util.Locale;

public class App extends Application {
    Context context;

//    public void onCreate(){
//        super.onCreate();
//
//        LocaleUtils.setLocale(new Locale("iw"));
//        LocaleUtils.updateConfig(this, getBaseContext().getResources().getConfiguration());
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        LocaleUtils.updateConfig(this, newConfig);
//    }


    @Override
    public void onCreate() {
        super.onCreate();
        super.onCreate();
        context = getApplicationContext();
//        setupActivityListener();
    }

    public static void setLocaleMarathi (Context context){
        Locale locale = new Locale("mr");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getApplicationContext().getResources().updateConfiguration(config, null);
    }

    public static void setLocaleEn (Context context){
        Locale locale = new Locale("en_US");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getApplicationContext().getResources().updateConfiguration(config, null);
    }


//    public void onCreate() {
//        super.onCreate();
//        context = getApplicationContext();
//        setupActivityListener();
//    }

    private void setupActivityListener()
    {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
            }
            @Override
            public void onActivityStarted(Activity activity) {
                activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
            }
            @Override
            public void onActivityResumed(Activity activity) {
                activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
            }
            @Override
            public void onActivityPaused(Activity activity) {
                activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
            }
            @Override
            public void onActivityStopped(Activity activity) {
                activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
            }
            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
            }
            @Override
            public void onActivityDestroyed(Activity activity) {
                activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

            }
        });
    }
}