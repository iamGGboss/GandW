package com.gandw.numkeyboard;

import android.content.Context;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Author      : GandW
 * Time        : 2017/3/16 17:05
 * E-mail      : wshkwg@163.com
 * Description :
 */
public class KeyboardEditText extends EditText implements View.OnTouchListener {

    protected Context context;

    protected CustomKeyBoard keyBoard;

    public KeyboardEditText(Context context) {
        super(context);
        init(context, null, 0);
    }

    public KeyboardEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public KeyboardEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(final Context context, AttributeSet attrs, int defStyleAttr) {
        this.context = context;
        this.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int inType = this.getInputType(); // backup the input type
        //这里区分下SDK版本以解决光标的问题
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.setShowSoftInputOnFocus(false);
        } else {
            this.setInputType(InputType.TYPE_NULL);
        }
        this.onTouchEvent(event);
        if (null == keyBoard) {
            keyBoard = new CustomKeyBoard.Builder(context)
                    .attachEditText(this)
                    .build();
        }
        keyBoard.show();
        this.setInputType(inType);
        return true;
    }
}
