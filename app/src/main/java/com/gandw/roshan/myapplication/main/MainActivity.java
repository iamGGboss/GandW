package com.gandw.roshan.myapplication.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gandw.roshan.myapplication.R;

import gandw.com.widget.ExpandTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btn_test_fitmap);
        final ExpandTextView expandTextview = (ExpandTextView) findViewById(R.id.etv_main);
        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       expandTextview.setInitText("你是哪个你是哪个你是哪个你是哪个你是哪个你是哪个你是哪个你是哪个你是哪个你是哪个你是哪个你是哪个你是哪个你是哪个你是哪个你是哪个你是哪个你是哪个你是哪个你是哪个你是哪个你是哪个你是哪个你是哪个你是哪个你是哪个你是哪个");
                                   }
                               }
        );
        expandTextview.setText("我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊我是谁啊");
    }
}
