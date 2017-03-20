package com.gandw.numkeyboard;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.support.annotation.XmlRes;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

/**
 * Author      : GandW
 * Time        : 2017/3/16 16:48
 * E-mail      : wshkwg@163.com
 * Description :
 */
public class CustomKeyBoard {

    protected PopupWindow popupWindow;

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

    public static class Builder {

        private Context context;
        protected EditText editText;

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


        public Builder attachEditText(EditText editText) {
            this.editText = editText;
            return this;
        }


        public CustomKeyBoard build() {
            CustomKeyBoard keyBoard = new CustomKeyBoard();
            View rootView = LayoutInflater.from(context).inflate(R.layout.view_keyboard, null);
            final PopupWindow popupWindow = new PopupWindow(rootView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setAnimationStyle(R.style.keyboard_animation);
            popupWindow.setFocusable(true);
            if (null != onDismissListener) {
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        onDismissListener.onDismiss();
                    }
                });
            }
            keyBoard.popupWindow = popupWindow;

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
            keyBoardView.setOnKeyboardActionListener(new KeyboardView.OnKeyboardActionListener() {
                @Override
                public void onPress(int primaryCode) {

                }

                @Override
                public void onRelease(int primaryCode) {

                }

                @Override
                public void onKey(int primaryCode, int[] keyCodes) {

                    Editable editable = editText.getText();
                    int start = editText.getSelectionStart();
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
            return keyBoard;
        }

    }

}
