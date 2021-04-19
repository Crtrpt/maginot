package com.dj.iotlite;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;



import java.util.HashMap;
import java.util.List;

public class DataGate extends BroadcastReceiver {

    public static HashMap<String, Object> getPropertys() {
        return propertys;
    }

    static HashMap<String, Object> propertys = new HashMap<>();

    public static byte[] getProperty() {
     return  null;
    }

    public void run(FullscreenActivity ctx) {
        Config.clientId=Settings.System.getString(ctx.getContentResolver(), Settings.Secure.ANDROID_ID);
        ctx.registerReceiver(this, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
            propertys.put("level", intent.getIntExtra("level", 0));
            propertys.put("intScale", intent.getIntExtra("scale", 100));
        }
    }
}
