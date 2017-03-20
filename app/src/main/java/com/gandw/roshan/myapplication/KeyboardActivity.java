package com.gandw.roshan.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.gandw.numkeyboard.OnKeyboardDismissListener;

public class KeyboardActivity extends AppCompatActivity implements OnKeyboardDismissListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);
        Button btn = (Button) findViewById(R.id.button2);
//        final CustomKeyBoard keyBoard = new CustomKeyBoard.Builder(KeyboardActivity.this)
//                .setOnDownClickListener(this)
//                .build();
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                keyBoard.show();
//            }
//        });
    }

    @Override
    public void onDismiss() {

    }
}
