package com.advelit.kioskapp;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

public class KioskModeService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("KioskModeService", "Kiosk mode service started");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Implement code to enable kiosk mode here

            // For example, you can launch your kiosk activity
            Intent kioskIntent = new Intent(this, KioskActivity.class);
            kioskIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(kioskIntent);
        }

        return START_STICKY;
    }
}
