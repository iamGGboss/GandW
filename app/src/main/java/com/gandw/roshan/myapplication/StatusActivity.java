package com.gandw.roshan.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gandw.statusview.StatusConfig;
import com.gandw.statusview.StatusLayout;

/**
 * Author      : GandW
 * Time        : 2017/3/7 14:01
 * E-mail      : wshkwg@163.com
 * Description :
 */
public class StatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statys);
        StatusLayout statusLayout = (StatusLayout) findViewById(R.id.status);
        StatusConfig config = new StatusLayout.Builder()
                .build();
        statusLayout.setConfig(config);
    }
}
