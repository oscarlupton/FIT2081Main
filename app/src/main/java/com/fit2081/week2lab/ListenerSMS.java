package com.fit2081.week2lab;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;

public class ListenerSMS extends BroadcastReceiver {
    public static final String SMS_FILTER = "SMS_FILTER";
    public static final String SMS_MSG_KEY = "SMS_MSG_KEY";

    @Override //onReceive will be called when SMS
    public void onReceive(Context context, Intent intent) {
        //`messages` is all SMSes, stored into an array
        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        for (int i=0; i< messages.length; i++){ //iterate through all SMSes
            SmsMessage message = messages[i];
            String messageSender = message.getDisplayOriginatingAddress(); //Currently unused
            String messageContents = message.getDisplayMessageBody();
            //Send broadcast with IntentFilter and Extra data, to be read by MainActivity
            Intent messageIntent = new Intent();
            messageIntent.setAction(SMS_FILTER);
            messageIntent.putExtra(SMS_MSG_KEY, messageContents);
            context.sendBroadcast(messageIntent);
        }
    }
}