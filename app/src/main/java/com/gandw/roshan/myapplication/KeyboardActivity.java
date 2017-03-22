package com.gandw.roshan.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
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
        CustomKeyBoard customKeyBoard1 = new CustomKeyBoard.Builder(this)
                .build();
        customKeyBoard1.attachEditText(editText1);
        int code = customKeyBoard1.getClass().hashCode();
        Log.d("KeyboardActivity", "code:" + code);
        CustomKeyBoard customKeyBoard2 = new CustomKeyBoard.Builder(this)
                .build();
        customKeyBoard2.attachEditText(editText2);
        int code1 = customKeyBoard2.getClass().hashCode();
        Log.d("KeyboardActivity", "code1:" + code1);
    }

    @Override
    public void onDismiss() {

    }
}
