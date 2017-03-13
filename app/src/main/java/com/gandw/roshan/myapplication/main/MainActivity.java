package com.gandw.roshan.myapplication.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.gandw.roshan.myapplication.R;
import com.gandw.roshan.myapplication.StatusActivity;
import com.gandw.roshan.myapplication.TurnsActivity;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (null == presenter) {
            Log.d("MainActivity", "空的");
        }
        Button btnStatus = (Button) findViewById(R.id.btn_status);
        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StatusActivity.class);
                startActivity(intent);
            }
        });
        Button btnTurns = (Button) findViewById(R.id.btn_turns);
        btnTurns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TurnsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public MainContract.Presenter createPresenter() {
        return presenter = new MainPresenter(this);
    }

}
