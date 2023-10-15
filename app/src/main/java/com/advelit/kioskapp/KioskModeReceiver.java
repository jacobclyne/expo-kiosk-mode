package com.advelit.kioskapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class KioskModeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Log.d("KioskModeReceiver", "Received boot completed event");

            // Start the kiosk service when the device boots
            Intent serviceIntent = new Intent(context, KioskModeService.class);
            context.startService(serviceIntent);
        }
    }
}
