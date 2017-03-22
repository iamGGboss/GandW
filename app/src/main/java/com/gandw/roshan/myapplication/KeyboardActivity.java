package com.gandw.roshan.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gandw.numkeyboard.CustomKeyBoard;
import com.gandw.numkeyboard.OnKeyboardDismissListener;

public class KeyboardActivity extends AppCompatActivity implements OnKeyboardDismissListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);
        Button btn = (Button) findViewById(R.id.button2);
        EditText editText1 = (EditText) findViewById(R.id.et1);
        EditText editText2 = (EditText) findViewById(R.id.et2);
        TextView textView = new TextView(getApplicationContext());
        textView.setTextColor(Color.BLACK);
        textView.setText("haha");
        View.OnClickListener  onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(KeyboardActivity.this, "点击了我", Toast.LENGTH_SHORT).show();
            }
        };
        CustomKeyBoard customKeyBoard1 = new CustomKeyBoard.Builder(this)
                .setDownView(textView)
                .setDownViewClickListener(onClickListener)
                .build();
        customKeyBoard1.attachEditText(editText1, null);
        customKeyBoard1.attachEditText(editText2, new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(KeyboardActivity.this, "我试试", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public void onDismiss() {

    }
}
