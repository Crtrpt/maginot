package com.dj.iotlite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import tl.antlr4.TLLexer;
import tl.antlr4.TLParser;


public class FullscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        DataGate dataGate=new DataGate();
        dataGate.run(this);
        bindService(new Intent(this, IotService.class), serviceConnection, Context.BIND_AUTO_CREATE);

        //执行
        TLLexer lexer = new TLLexer(CharStreams.fromString("print('xxxxxxxxxxxxxxx');"));
        TLParser parser = new TLParser(new CommonTokenStream(lexer));
        parser.parse();
        //增加内嵌的动态能力
        Log.i("FullscreenActivity", "onCreate: 执行xxx");
    }

}