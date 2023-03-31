package com.tech.aicapital.mvps;


import com.tech.aicapital.datalist.NewResponse;
import com.tech.aicapital.datalist.SimpleResponse;

/**
 * Created by Admin on 9/15/2018.
 */

public interface CashResponseReciever {
    void onFailure(String error);
    void onSuccess(SimpleResponse error);
    void onsuccessNew(NewResponse respo);


}
