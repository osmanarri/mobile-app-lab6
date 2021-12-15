package com.example.osmantahir_comp304lab6_ex1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        String str = "SMS from ";
        //---get the SMS message passed in---
        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            SmsMessage[] smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
            for (SmsMessage message : smsMessages) {
                // Do whatever you want to do with SMS.
                //---get the sender address/phone number---
                str += message.getOriginatingAddress();
                str += ": ";
                //---get the message body---
                str += message.getMessageBody().toString();

            }
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();

        }

        Log.d("SMSReceiver", str);

        //---stop the SMS message from being broadcasted---
        this.abortBroadcast();

        //---launch the SMSActivity---
        /*
        Intent mainActivityIntent = new Intent(context, MessageActivity.class);
        mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(mainActivityIntent);
        */

        //---send a broadcast intent to update the SMS received in the activity---
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("SMS_RECEIVED_ACTION");
        broadcastIntent.putExtra("sms", str);
        context.sendBroadcast(broadcastIntent);

    }

}
