package com.dj.iotlite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import com.dj.iotlite.events.LogEvent;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import tl.antlr4.TLLexer;
import tl.antlr4.TLParser;


public class FullscreenActivity extends AppCompatActivity {

    TextView textView;
    Calendar c=Calendar.getInstance();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    @Subscribe
    public void handleSomethingElse(LogEvent event) {
        c.setTimeInMillis(System.currentTimeMillis());
        runOnUiThread(() -> textView.setText(formatter.format(c.getTime())+":"+event.getMsg()+"\n"+textView.getText()));
    }

    Timer timer = new Timer();

    TimerTask t=new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(()->{
                textView.setText("");
            });
        }
    };

    public void clearLog(){
        timer.scheduleAtFixedRate(t, 5000, 10000);
    }
    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        this.clearLog();
        setContentView(R.layout.activity_fullscreen);

        Log.d(this.getClass().getName(), "主 线程"+Thread.currentThread().getId());
        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            }
            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
        textView=findViewById(R.id.lastLog);
        DataGate dataGate=new DataGate();
        dataGate.run(this);
        bindService(new Intent(this, IotService.class), serviceConnection, Context.BIND_AUTO_CREATE);


    }

}