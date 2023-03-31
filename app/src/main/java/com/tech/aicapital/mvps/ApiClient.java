package com.tech.aicapital.mvps;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by saurabha on 18/8/16.
 */


public class ApiClient
{
    private static Retrofit retrofit = null;
    private static Retrofit retrofit2 = null;
    private static Retrofit retrofit3 = null;
    private static Retrofit retrofit33 = null;
    private static Retrofit retrofit4 = null;
    private static Retrofit retrofit7 = null;
    private static Retrofit retrofit8 = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static Retrofit getClient7() {
        if (retrofit7 == null) {
            retrofit7 = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL8)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit7;
    }
    public static Retrofit getClient8() {
        if (retrofit8 == null) {
            retrofit8 = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL8)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit8;
    }


    public static Retrofit getClient0() {
        if (retrofit4 == null) {
            retrofit4 = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL0)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit4;
    }
    public static Retrofit getClient2(){
        if (retrofit2 == null)
        {
            retrofit2 = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL2)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit2;
    }
    public static Retrofit getClient4(){
        if (retrofit3 == null)
        {
            retrofit3 = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL4)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit3;
    }
    public static Retrofit getClient3(){
        if (retrofit33 == null)
        {
            retrofit33 = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL3)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit33;
    }

}
