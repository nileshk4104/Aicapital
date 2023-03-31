package com.tech.aicapital.mvps;

import android.content.Context;


import com.tech.aicapital.datalist.NewResponse;
import com.tech.aicapital.datalist.SimpleResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 9/15/2018.
 */

public class CashIterator
{





    public void getLogin(final Context mContext, String member_id, String pin, final
    CashResponseReciever responseReceived) {
        final NetworkCaller apiService = ApiClient.getClient().create(NetworkCaller.class);
        Call<NewResponse> call = apiService.getLogin(member_id,pin);
        call.enqueue(new Callback<NewResponse>() {
            @Override
            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                NewResponse hospitalListResponse = response.body();

                if (hospitalListResponse != null) {
                    responseReceived.onsuccessNew(hospitalListResponse);
                } else
                    responseReceived.onFailure("Something wrong");
            }
            @Override
            public void onFailure(Call<NewResponse> call, Throwable t) {

                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    responseReceived.onFailure("Alert Message");
                } else {
                    responseReceived.onFailure(t.toString());
                }

            }
        });

    }









//    @NonNull
//    private RequestBody createPartFromString(String descriptionString) {
//        return RequestBody.create(
//                okhttp3.MultipartBody.FORM, descriptionString);
//    }
//    @NonNull
//    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri,Context context) {
//        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
//        // use the FileUtils to get the actual file by uri
//        File file = FileUtils.getFile(context, fileUri);
//
//        // create RequestBody instance from file
//        RequestBody requestFile =RequestBody.create(MediaType.parse(context.getContentResolver().getType(fileUri)), file);
//
////        RequestBody requestFile2=RequestBody.create(MediaType.parse(get),file);
//
//        // MultipartBody.Part is used to send also the actual file name
//        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
//    }
//



}
