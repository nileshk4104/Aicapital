package com.tech.aicapital;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsBroadcastReceiver extends BroadcastReceiver {
    private SmsListener mSmsListener;



    @Override
    public void onReceive(Context context, Intent intent) {
        try{
            Log.e("BROADCAST RECEIVER", "onReceive called()");
            Bundle data  = intent.getExtras();

            Object[] pdus = (Object[]) data.get("pdus");

            for(int i=0;i<pdus.length;i++){
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);

                String sender = smsMessage.getDisplayOriginatingAddress();
                //You must check here if the sender is your provider and not another one with same text.

                String messageBody = smsMessage.getMessageBody();
//            String message = smsMessage.getDisplayMessageBody().split(" ")[1];
//            message = message.substring(0, message.length()-1);
                //Pass on the text to our listener.
                mSmsListener.messageReceived(sender, messageBody);



            }

        }catch (Exception e){

        }

    }

    public void bindListener(SmsListener listener){
        mSmsListener = listener;
    }
}
