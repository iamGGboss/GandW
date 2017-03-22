package com.gandw.numkeyboard;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.support.annotation.XmlRes;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

/**
 * Author      : GandW
 * Time        : 2017/3/16 16:48
 * E-mail      : wshkwg@163.com
 * Description :
 */
public class CustomKeyBoard {

    protected PopupWindow popupWindow;

    protected KeyboardView keyboardView;

    protected Context context;

    public void show() {
        if (null != popupWindow && !popupWindow.isShowing()) {
            View contentView = popupWindow.getContentView();
            popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
        }
    }

    public void dismiss() {
        if (null != popupWindow && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    public void attachEditText(final EditText editText) {
        keyboardView.setOnKeyboardActionListener(new KeyboardView.OnKeyboardActionListener() {
            @Override
            public void onPress(int primaryCode) {

            }

            @Override
            public void onRelease(int primaryCode) {

            }

            @Override
            public void onKey(int primaryCode, int[] keyCodes) {
                EditText currentFocusEditText = getCurrentFocusEditText();
                if (null != currentFocusEditText) {
                    Editable editable = currentFocusEditText.getText();
                    int start = currentFocusEditText.getSelectionStart();
                    switch (primaryCode) {
                        case -5:
                            //删除键
                            if (!TextUtils.isEmpty(editable)) {
                                if (start > 0) {
                                    editable.delete(start - 1, start);
                                }
                            }
                            break;
                        case -10:
                            //.号
                            editable.insert(start, ".");
                            break;
                        default:
                            //普通建
                            editable.insert(start, Character.toString((char) primaryCode));
                            break;
                    }
                }
            }

            @Override
            public void onText(CharSequence text) {

            }

            @Override
            public void swipeLeft() {

            }

            @Override
            public void swipeRight() {

            }

            @Override
            public void swipeDown() {

            }

            @Override
            public void swipeUp() {

            }
        });
        /**将事件关联到editetext*/
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int inType = editText.getInputType(); // backup the input type
                //这里区分下SDK版本以解决光标的问题
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    editText.setShowSoftInputOnFocus(false);
                } else {
                    editText.setInputType(InputType.TYPE_NULL);
                }
                editText.onTouchEvent(event);
                CustomKeyBoard.this.show();
                editText.setInputType(inType);
                return false;
            }
        });
    }

    private EditText getCurrentFocusEditText() {
        if (null != context) {
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                int id = activity.getWindow().getDecorView().findFocus().getId();
                View viewById = activity.findViewById(id);
                if (viewById instanceof EditText) {
                    EditText editText = (EditText) viewById;
                    return editText;
                }
            }
        }
        return null;
    }

    public static class Builder {

        private Context context;

        @XmlRes
        private int keyboardID = R.xml.keyboard_defu;

        private OnKeyboardDismissListener onDismissListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setKeyboardResID(@XmlRes int keyboardID) {
            this.keyboardID = keyboardID;
            return this;
        }

        public Builder setOnDownClickListener(OnKeyboardDismissListener onDismissListener) {
            this.onDismissListener = onDismissListener;
            return this;
        }

        public CustomKeyBoard build() {
            CustomKeyBoard customKeyBoard = new CustomKeyBoard();
            View rootView = LayoutInflater.from(context).inflate(R.layout.view_keyboard, null);
            final PopupWindow popupWindow = new PopupWindow(rootView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setAnimationStyle(R.style.keyboard_animation);
            if (null != onDismissListener) {
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        onDismissListener.onDismiss();
                    }
                });
            }
            customKeyBoard.popupWindow = popupWindow;
            /**自定义键盘的事件处理*/
            Button btnDowd = (Button) rootView.findViewById(R.id.btn_down);
            btnDowd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
            //默认键盘布局
            KeyboardView keyBoardView = (KeyboardView) rootView.findViewById(R.id.kbv);
            //设置键盘布局
            Keyboard keyboard = new Keyboard(context, keyboardID);
            keyBoardView.setKeyboard(keyboard);
            keyBoardView.setEnabled(true);
            // 设置按键没有点击放大镜显示的效果
            keyBoardView.setPreviewEnabled(false);
            customKeyBoard.keyboardView = keyBoardView;
            customKeyBoard.context = context;
            return customKeyBoard;
        }

    }

}
