package com.dj.titan;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        TLLexer lexer = new TLLexer(CharStreams.fromString("print('xxxxxxxxxxxxxxx');"));
        TLParser parser = new TLParser(new CommonTokenStream(lexer));
        parser.parse();
        //增加内嵌的动态能力
        Log.i("FullscreenActivity", "onCreate: 执行xxx");
    }

}