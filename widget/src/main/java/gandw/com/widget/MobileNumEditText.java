package gandw.com.widget;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Author      : GandW
 * Time        : 2016/12/20 11:06
 * E-mail      : wshkwg@163.com
 * Description : 手机号码，隔开样式
 */

public class MobileNumEditText extends EditText {
    public MobileNumEditText(Context context) {
        super(context);
        init();
    }

    public MobileNumEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MobileNumEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setMaxLines(1);
        this.setFilters(new InputFilter[]{new InputFilter.LengthFilter(13)});//最大输入13个字符
        this.setInputType(InputType.TYPE_CLASS_NUMBER);
        MobileNumTextWatcher watcher = new MobileNumTextWatcher(this);
        this.addTextChangedListener(watcher);
    }

    public String getMobileNum(){
        String num = getText().toString().trim().replaceAll(" ", "");
        return num;
    }

    class MobileNumTextWatcher implements TextWatcher {

        private EditText editText;

        public MobileNumTextWatcher(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            if (charSequence == null || charSequence.length() == 0) return;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < charSequence.length(); i++) {
                if (i != 3 && i != 8 && charSequence.charAt(i) == ' ') {
                    continue;
                } else {
                    sb.append(charSequence.charAt(i));
                    if ((sb.length() == 4 || sb.length() == 9) && sb.charAt(sb.length() - 1) != ' ') {
                        sb.insert(sb.length() - 1, ' ');
                    }
                }
            }
            if (!sb.toString().equals(charSequence.toString())) {
                int index = start + 1;
                if (sb.charAt(start) == ' ') {
                    if (before == 0) {
                        index++;
                    } else {
                        index--;
                    }
                } else {
                    if (before == 1) {
                        index--;
                    }
                }
                editText.setText(sb.toString());
                editText.setSelection(index);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
