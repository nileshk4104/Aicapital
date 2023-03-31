package com.tech.aicapital.notification;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

//import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by MyClan on 3/8/2017.
 */

public class MyFirebaseInstanceIDService  extends FirebaseMessagingService {
    private static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName();
    private static final String TOPIC_GLOBAL = "global";

    @Override
   public void onNewToken(String token) {
        //super.onTokenRefresh();
        String refreshedToken="";

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("Token", "getInstanceId failed", task.getException());
                            return;
                        }
                        // Get new Instance ID token
                        String tokens = task.getResult().getToken();
                        PrefUtility.setValue(getApplicationContext(), "regId", tokens);

                    }
                });


        Log.e("RESTTOKENFORSONGLE", refreshedToken);
        // Saving reg id to shared preferences
        storeRegIdInPref(refreshedToken);
        // sending reg id to your server
        sendRegistrationToServer(refreshedToken);
        registerToken(refreshedToken);
        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(Config.REGISTRATION_COMPLETE);
        registrationComplete.putExtra("token", refreshedToken);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
        SharedPreferences sp1 = getApplicationContext().getSharedPreferences("reg_token", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp1.edit();
        editor.putString("token", refreshedToken);
        editor.commit();
    }

    private void sendRegistrationToServer(final String token) {
        // sending gcm token to server
        Log.e(TAG, "sendRegistrationToServer: " + token);
    }

    private void storeRegIdInPref(String token) {

        PrefUtility.setValue(getApplicationContext(), "regId", token);

    }

    private void registerToken(String recent_token) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("TOKEN", recent_token)
                .build();

        Request request = new Request.Builder()
                .url("http://www.mychillar.com/pushnotify/sendSinglePush.php")
                .post(body)
                .build();

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
