package com.gandw.expandabletextview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Author      : GandW
 * Time        : 2017/1/4 16:07
 * E-mail      : wshkwg@163.com
 * Description :
 */

public class ExpandableTextView extends TextView {

    private int width;  //初始宽度

    public ExpandableTextView(Context context) {
        super(context);
    }

    public ExpandableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText("123", type);
    }
}
