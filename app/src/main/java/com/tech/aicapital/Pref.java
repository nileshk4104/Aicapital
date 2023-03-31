package com.tech.aicapital;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tech.aicapital.cart.datalist.CartDataList;
import com.tech.aicapital.datalist.BannerDataList;
import com.tech.aicapital.datalist.CategoryDataList;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.tech.aicapital.mvps.Constant.BANNER_LIST;
import static com.tech.aicapital.mvps.Constant.CART_LIST;
import static com.tech.aicapital.mvps.Constant.TYPES_LIST;

@SuppressLint("NewApi")
public class Pref {
    public static final String PREFS_NAME = "PRODUCT_APP";
    private static SharedPreferences sharedPreferences = null;

    public static void openPref(Context context) {
        sharedPreferences = context.getSharedPreferences("Photo", Context.MODE_PRIVATE);
    }
    public static void openPref_profile(Context context) {
        sharedPreferences = context.getSharedPreferences("Photo", Context.MODE_PRIVATE);
    }

    public static  void setDataFromSharedPreferences(CartDataList curProduct, Context activity,String key){
        Gson gson = new Gson();
        String jsonCurProduct = gson.toJson(curProduct);
        SharedPreferences sharedPref = activity.getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, jsonCurProduct);
        editor.commit();
    }
    public static void addFavorite(Context context, CartDataList product) {
        List<CartDataList> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<CartDataList>();
        favorites.add(product);
        saveFavorites(context, favorites);
    }
    public static void saveFavorites(Context context, List<CartDataList> favorites) {
        SharedPreferences settings;
        Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);
        editor.putString(CART_LIST, jsonFavorites);
        editor.commit();
    }


    public static void removeFavorite(Context context, CartDataList product) {
        ArrayList<CartDataList> favorites = getFavorites(context);
        if (favorites != null)
        {
            favorites.remove(product);
            saveFavorites(context, favorites);
        }
    }

    public static ArrayList<CartDataList> getFavorites(Context context) {
        SharedPreferences settings;
        List<CartDataList> favorites = new ArrayList<CartDataList>();
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (settings.contains(CART_LIST))
        {
            String jsonFavorites = settings.getString(CART_LIST, null);
            Gson gson = new Gson();
            CartDataList[] favoriteItems = gson.fromJson(jsonFavorites, CartDataList[].class);
            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<CartDataList>(favorites);
        } else{
            return (ArrayList<CartDataList>) favorites;
        }


        return (ArrayList<CartDataList>) favorites;
    }
    public static ArrayList<BannerDataList> getBannerList(Context context) {
        SharedPreferences settings;
        List<BannerDataList> favorites;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (settings.contains(BANNER_LIST))
        {
            String jsonFavorites = settings.getString(BANNER_LIST, null);
            Gson gson = new Gson();
            BannerDataList[] favoriteItems = gson.fromJson(jsonFavorites, BannerDataList[].class);
            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<BannerDataList>(favorites);
        } else
            return null;

        return (ArrayList<BannerDataList>) favorites;
    }
    public static ArrayList<CategoryDataList> getTypesList(Context context)
    {
        SharedPreferences settings;
        List<CategoryDataList> favorites;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (settings.contains(TYPES_LIST))
        {
            String jsonFavorites = settings.getString(TYPES_LIST, null);
            Gson gson = new Gson();
            CategoryDataList[] favoriteItems = gson.fromJson(jsonFavorites, CategoryDataList[].class);
            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<CategoryDataList>(favorites);
        } else
            return null;

        return (ArrayList<CategoryDataList>) favorites;
    }


    public static  List<CartDataList> getDataFromSharedPreferences(Context activity,String key){
        Gson gson = new Gson();
        List<CartDataList> productFromShared = new ArrayList<>();
        SharedPreferences sharedPref = activity.getSharedPreferences(key, Context.MODE_PRIVATE);
        String jsonPreferences = sharedPref.getString(key, "");
//        Log.e("STR",jsonPreferences);

        Type type = new TypeToken<List<CartDataList>>() {}.getType();
        productFromShared = gson.fromJson(jsonPreferences, type);

        return productFromShared;
    }

    public static List<CartDataList> getArrayList(String key,Context activity){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static <T> void setList(Context context, String key, List<T> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        set(context,key, json);
    }
    public static void set(Context context,String key, String value) {
        Pref.openPref(context);
        Editor prefsPrivateEditor = Pref.sharedPreferences.edit();
        prefsPrivateEditor.putString(key, value);
        prefsPrivateEditor.commit();
    }

    public static void setLoginStatus(Context context, String key, boolean value) {
        Pref.openPref(context);
        Editor prefsPrivateEditor = Pref.sharedPreferences.edit();
        prefsPrivateEditor.putBoolean(key, value);
        prefsPrivateEditor.commit();
//        prefsPrivateEditor = null;
//        Pref.sharedPreferences = null;

    }

    public static boolean getLoginStatus(Context context, String key,boolean defaultValue) {
        boolean result = false;
        if (null!=context) {
            Pref.openPref(context);
            if (Pref.sharedPreferences != null) {
                result = Pref.sharedPreferences.getBoolean(key, defaultValue);
            }
            Pref.sharedPreferences = null;
        }

        return result;
    }
    public static String getValue(Context context, String key,String defaultValue) {
        String result = "";
        if (null!=context) {
            Pref.openPref(context);
            if (Pref.sharedPreferences != null) {
                result = Pref.sharedPreferences.getString(key, defaultValue);
            }
            Pref.sharedPreferences = null;
        }

        return result;
    }
    public static String getValue_profile(Context context, String key, String defaultValue) {
        Pref.openPref_profile(context);
        String result = Pref.sharedPreferences.getString(key, defaultValue);
        Pref.sharedPreferences = null;
        return result;
    }

    public static void setValue_profile(Context context, String key, String value) {
        Pref.openPref_profile(context);
        Editor prefsPrivateEditor = Pref.sharedPreferences.edit();
        prefsPrivateEditor.putString(key, value);
        prefsPrivateEditor.commit();
        prefsPrivateEditor = null;
        Pref.sharedPreferences = null;
    }

    public static int getValue(Context context, String key, int defaultValue) {
        Pref.openPref(context);
        int result = Pref.sharedPreferences.getInt(key, defaultValue);
        Pref.sharedPreferences = null;
        return result;
    }
    public static void setValue(Context context, String key, int value) {
        Pref.openPref(context);
        Editor prefsPrivateEditor = Pref.sharedPreferences.edit();
        prefsPrivateEditor.putInt(key, value);
        prefsPrivateEditor.commit();
        prefsPrivateEditor = null;
        Pref.sharedPreferences = null;
    }
    public static void setValue(Context context, String key, String value) {
        Pref.openPref(context);
        Editor prefsPrivateEditor = Pref.sharedPreferences.edit();
        prefsPrivateEditor.putString(key, value);
        prefsPrivateEditor.commit();
        prefsPrivateEditor = null;
        Pref.sharedPreferences = null;
    }

    public static boolean getValue(Context context, String key, boolean defaultValue) {
        boolean result = false;
        if (null != context) {
            Pref.openPref(context);
            if (Pref.sharedPreferences != null) {
                result = Pref.sharedPreferences.getBoolean(key, defaultValue);
            }
            Pref.sharedPreferences = null;
        }

        return result;
    }
    public static void setValue(Context context, String key, boolean value) {
        // if (sharedPreferences != null) {
        Pref.openPref(context);
        Editor prefsPrivateEditor = Pref.sharedPreferences.edit();
        prefsPrivateEditor.putBoolean(key, value);
        prefsPrivateEditor.commit();
        prefsPrivateEditor = null;
        Pref.sharedPreferences = null;
        //    }
    }

    public static void setStringSet(Context _Context, String key, Set<String> mSetArray) {

        Pref.openPref(_Context);

        Editor preferenceEditor = Pref.sharedPreferences.edit();

        preferenceEditor.putStringSet(key, mSetArray);

        preferenceEditor.commit();
        preferenceEditor = null;
        Pref.sharedPreferences = null;
    }
    public static Set<String> getStoredPassHistory(Context _Context, String mKey) {

        Pref.openPref(_Context);

        HashSet<String> mSetPassHistory = (HashSet<String>) Pref.sharedPreferences
                .getStringSet(mKey, new HashSet<String>());

        Pref.sharedPreferences = null;

        return mSetPassHistory;
    }



}
