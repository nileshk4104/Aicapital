package com.tech.aicapital.mvps;

import android.content.Context;
import android.net.Uri;

import com.tech.aicapital.datalist.NewResponse;
import com.tech.aicapital.datalist.SimpleResponse;


/**
 * Created by Admin on 9/15/2018.
 */

public class CashPresenter implements CashResponseReciever {
    Context mContext;
    CashListViewer cashListViewer;

    public CashPresenter(Context mContext, CashListViewer cashListViewer) {
        this.mContext = mContext;
        this.cashListViewer = cashListViewer;
    }
    public void getLogin(String mobileno, String pinno) {
        cashListViewer.showProgressBar();
        new CashIterator().getLogin(mContext,mobileno,pinno,CashPresenter.this);
    }

    public void getIncreaseCount(String appId, String userId, String count) {
        cashListViewer.showProgressBar();
    }
    public void getUserProfileData(String userid) {
        cashListViewer.showProgressBar();
//        new CashIterator().getUserProfileData(mContext,userid,CashPresenter.this);
    }

    public void newverifyOtp(String userotp, String mobile, String pin) {
        cashListViewer.showProgressBar();
    }
    public void forgotPassword(String mobile) {
        cashListViewer.showProgressBar();
    }
    public void getVersionNo() {
        cashListViewer.showProgressBar();
//        new CashIterator().getVersionNo(mContext,CashPresenter.this);
    }
    public void updateUserProfileData(String userid, String name, String email, String dob, String district, String tehasil, String village, String state) {
        cashListViewer.showProgressBar();
    }


    public void updateProfilePicture(Uri doc_file, Uri mImageUriPan, String userId) {
        cashListViewer.showProgressBar();
//        new CashIterator().updateProfilepicture(mContext,doc_file,mImageUriPan,userId,CashPresenter.this);
    }
    public void getOtp(String user_mobile, String user_nmae, String user_mail, String user_dob, String gender) {
        cashListViewer.showProgressBar();
    }
    public void verifyOtp(String userotp, String mobile, String referid) {
        cashListViewer.showProgressBar();
    }

    @Override
    public void onFailure(String error) {
        cashListViewer.hideProgressBar();
    }
    @Override
    public void onSuccess(SimpleResponse error) {
        cashListViewer.hideProgressBar();
        if (error.isStatus())
        {
            cashListViewer.onSuccess(error.getMessage());
        } else {
            cashListViewer.onFailure(error.getMessage());
        }
    }
    @Override
    public void onsuccessNew(NewResponse respo) {
        cashListViewer.hideProgressBar();

        if (respo.isStatus())
        {
            cashListViewer.onsuccessNew(respo.getMessage(),respo.getData());
        }
        else
        {
            cashListViewer.onFailure(respo.getMessage());
        }
    }


}
