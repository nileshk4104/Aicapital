package com.tech.aicapital.mvps;



/**
 * Created by Admin on 9/15/2018.
 */

public interface CashListViewer {
    void onFailure(String errorMsg);
    void onSuccess(String msg);
    void showProgressBar();
    void hideProgressBar();
//    void onsuccessProfile(ProfileData profileData);
    void onsuccessVersion(String versionno);
    void onsuccessNew(String message, String data);
}
