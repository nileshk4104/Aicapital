package com.tech.aicapital;

public interface SmsListener {
    void messageReceived(String sender, String messageText);

}