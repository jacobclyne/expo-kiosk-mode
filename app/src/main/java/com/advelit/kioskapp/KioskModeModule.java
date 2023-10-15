package com.advelit.kioskapp;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.view.WindowManager;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class KioskModeModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private static final String DEVICE_ADMIN_COMPONENT = "com.advelit.kioskapp/.KioskModeAdminReceiver";

    public KioskModeModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "KioskMode";
    }

    @ReactMethod
    public void enableKioskMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            DevicePolicyManager devicePolicyManager = (DevicePolicyManager) reactContext.getSystemService(Context.DEVICE_POLICY_SERVICE);

            if (devicePolicyManager.isAdminActive(new ComponentName(reactContext, KioskModeAdminReceiver.class))) {
                // Check if the app is already a device administrator
                if (devicePolicyManager.isDeviceOwnerApp(reactContext.getPackageName())) {
                    // Set the app as a lock task package
                    devicePolicyManager.setLockTaskPackages(new ComponentName(reactContext, KioskModeAdminReceiver.class), new String[]{reactContext.getPackageName()});
                    startLockTask();
                } else {
                    Log.e("KioskModeModule", "The app is not the device owner.");
                }
            } else {
                Log.e("KioskModeModule", "Device administrator not active.");
            }
        } else {
            Log.e("KioskModeModule", "Kiosk mode not supported on this Android version.");
        }
    }

    @ReactMethod
    public void disableKioskMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            DevicePolicyManager devicePolicyManager = (DevicePolicyManager) reactContext.getSystemService(Context.DEVICE_POLICY_SERVICE);

            if (devicePolicyManager.isAdminActive(new ComponentName(reactContext, KioskModeAdminReceiver.class))) {
                // Clear the lock task packages and exit lock task mode
                devicePolicyManager.setLockTaskPackages(new ComponentName(reactContext, KioskModeAdminReceiver.class), new String[0]);
                stopLockTask();
            } else {
                Log.e("KioskModeModule", "Device administrator not active.");
            }
        } else {
            Log.e("KioskModeModule", "Kiosk mode not supported on this Android version.");
        }
    }
}
