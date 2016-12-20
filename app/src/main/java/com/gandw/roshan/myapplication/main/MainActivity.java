package com.gandw.roshan.myapplication.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gandw.roshan.myapplication.R;

import gandw.com.widget.ExpandTextView;
import gandw.com.widget.MobileNumEditText;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btn_test_fitmap);
        final ExpandTextView expandTextview = (ExpandTextView) findViewById(R.id.etv_main);
        final MobileNumEditText mobileNumEditText = (MobileNumEditText) findViewById(R.id.mnet);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, mobileNumEditText.getMobileNum(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public MainContract.Presenter createPresenter() {
        return presenter = new MainPresenter(this);
    }
}
