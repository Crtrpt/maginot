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
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;


public class DataGate extends BroadcastReceiver {

    public static HashMap<String, Object> getPropertys() {
        return propertys;
    }

    static HashMap<String, Object> propertys = new HashMap<>();

    public static byte[] getProperty() {
        IotMessageDto msg = new IotMessageDto();
        msg.setAction("property");
        msg.setPayload(getPropertys());
        Gson gson = new Gson();
        return gson.toJson(msg).getBytes();
    }

    public void run(FullscreenActivity ctx) {
        ctx.registerReceiver(this, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        SensorManager mSmanager = (SensorManager) ctx.getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> allSensors = mSmanager.getSensorList(Sensor.TYPE_ALL);
        
        for (Sensor s : allSensors) {
            mSmanager.registerListener(new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    propertys.put(sensorEvent.sensor.getStringType(), sensorEvent.values);
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            }, s, SensorManager.SENSOR_DELAY_NORMAL);
        }

        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Activity.TELEPHONY_SERVICE);

        propertys.put("android_id", Settings.System.getString(ctx.getContentResolver(), Settings.Secure.ANDROID_ID));

        /**
         * 蓝牙扫描
         */
        BluetoothManager bm = (BluetoothManager) ctx.getSystemService(Context.BLUETOOTH_SERVICE);

        final BluetoothAdapter mBluetoothAdapter =bm.getAdapter();
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            ctx.startActivityForResult(intent, 1);
        }
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("开始搜索蓝牙设备");
                mBluetoothAdapter.startLeScan(new BluetoothAdapter.LeScanCallback() {
                    @Override
                    public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bytes) {
                        System.out.println("找到蓝牙设备");
                        System.out.println(bluetoothDevice.getUuids()[0]);

                    }
                });
            }
        },10000);


        /**
         * wifi扫描
         */
        WifiManager wifiManager=(WifiManager) ctx.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

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
