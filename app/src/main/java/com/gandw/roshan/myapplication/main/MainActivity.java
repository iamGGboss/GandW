package com.gandw.roshan.myapplication.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gandw.roshan.myapplication.R;

import gandw.com.widget.ExpandTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExpandTextView expandTextview= (ExpandTextView) findViewById(R.id.etv_main);
        expandTextview.setText("我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊");
    }
}