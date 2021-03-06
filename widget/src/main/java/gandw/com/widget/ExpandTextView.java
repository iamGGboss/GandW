package gandw.com.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * Author      : GandW
 * Time        : 2016/12/15 10:08
 * Description : 展开收起的Textview,有bug请联系
 */

public class ExpandTextView extends TextView implements ViewTreeObserver.OnGlobalLayoutListener {

    private static final int DEFAULT_LINSE = 3;
    private static final String DEFAULT_BEFORE_CLICK = "...";
    private static final String DEFAULT_EXPAND = "展开";
    private static final String DEFAULT_PACK_UP = "收起";

    private int maxLines;   //最大行数
    private String beforeClickStr;  //显示在点击之前的字符
    private String expandStr;   //展开的文字
    private String packupStr;   //收起的文字
    @ColorInt
    private int clickColor;     //点击的颜色
    private boolean isExpand = true;   //默认不展开
    private SpannableString allContent;  //全部内容
    private SpannableString partContent; //部分内容
    private CharSequence initialContent;  //初始内容
    private int initialLine;    //初始行数


    @Override
    public void setMaxLines(int maxLines) {
        this.maxLines = maxLines;
        resetContent();
    }

    public void setBeforeClickStr(String beforeClickStr) {
        this.beforeClickStr = beforeClickStr;
        resetContent();
    }

    public void setExpandStr(String expandStr) {
        this.expandStr = expandStr;
        resetContent();
    }

    public void setPackupStr(String packupStr) {
        this.packupStr = packupStr;
        resetContent();
    }

    public void setClickColor(@ColorInt int clickColor) {
        this.clickColor = clickColor;
        resetContent();
    }

    public ExpandTextView(Context context) {
        super(context);
        resetContent();
    }

    public ExpandTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
        resetContent();
    }

    public ExpandTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
        resetContent();
    }

    private void initAttr(Context context, AttributeSet attrs) {
        maxLines = attrs.getAttributeIntValue(android.R.attr.maxLines, -1);
        if (maxLines == -1) {
            maxLines = DEFAULT_LINSE;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandTextView);
        String before = typedArray.getString(R.styleable.ExpandTextView_beforeStr);
        if (TextUtils.isEmpty(before)) {
            before = DEFAULT_BEFORE_CLICK;
        }
        beforeClickStr = before;
        String expand = typedArray.getString(R.styleable.ExpandTextView_expandStr);
        if (TextUtils.isEmpty(expand)) {
            expand = DEFAULT_EXPAND;
        }
        expandStr = expand;
        String packup = typedArray.getString(R.styleable.ExpandTextView_packupStr);
        if (TextUtils.isEmpty(packup)) {
            packup = DEFAULT_PACK_UP;
        }
        packupStr = packup;
        clickColor = typedArray.getColor(R.styleable.ExpandTextView_clickColor, Color.RED);
        typedArray.recycle();
    }

    /**
     * @param content 设置初始内容
     */
    public void setInitText(CharSequence content) {
        initialContent = content;
        setText(content);
        resetContent();
    }

    /**
     * 重置显示内容
     */
    private void resetContent() {
        if (maxLines < 1 || TextUtils.isEmpty(initialContent)) {
            return;
        }
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onGlobalLayout() {
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
        initialLine = getLineCount();
        if (initialLine > maxLines) {
            //大于设置的最大长度后做处理
            handleContent();
            if (isExpand) {
                if (!TextUtils.isEmpty(allContent)) {
                    setText(allContent);
                }
            } else {
                if (!TextUtils.isEmpty(partContent)) {
                    setText(partContent);
                }
            }
        }
    }


    /**
     * 初始化的时候的处理
     */
    private void handleContent() {
        initContent(initialContent);
        setMovementMethod(LinkMovementMethod.getInstance());    //不设置 没有点击
        setHighlightColor(Color.TRANSPARENT); //设置点击后的颜色为透明
    }


    /**
     * @param content 内容
     * @param spanStr SPAN
     * @return
     */
    private SpannableString createSpan(String content, String spanStr) {
        if (TextUtils.isEmpty(content) || TextUtils.isEmpty(spanStr)) {
            return null;
        }
        SpannableString builder = new SpannableString(content);
        ExpandClickableSpan clickableSpan = new ExpandClickableSpan();
        int contentSize = content.length();
        int spanSize = spanStr.length();
        if (contentSize <= spanSize) {
            return null;
        }
        int start = contentSize - spanSize;
        builder.setSpan(clickableSpan, start, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

    private void initContent(CharSequence content) {
        if (TextUtils.isEmpty(expandStr) || TextUtils.isEmpty(packupStr) || TextUtils.isEmpty(content)) {
            return;
        }
        initAllContent(content);
        initPartContent(content);
    }

    /**
     * 初始化部分内容
     */
    private void initPartContent(CharSequence content) {
        //未展开的情况
        Layout layout = getLayout();
        //计算单行的才长度
        int endIndex = layout.getLineStart(maxLines);
        int startIndex = layout.getLineStart(maxLines - 1);
        int index = endIndex - startIndex;
        if (index > 0) {
            //排除当前行的情况
            CharSequence newContent = content.subSequence(0, index);
            //测量单行长度
            TextPaint paint = getPaint();
            float newContentSize = paint.measureText(newContent.toString());
            //测量点击文字和点击文字之前替代符的长度
            //点击之前的字符的长度
            float beforeSize = paint.measureText(beforeClickStr);
            float clickSize = paint.measureText(expandStr);
            //计算需要截取的长度
            float maxSize = newContentSize - beforeSize - clickSize;
            //获取截取的坐标
            //下面这几个就是剪切显示，就是大于maxWidth的时候只截取指定长度的显示
            int len = paint.breakText(newContent, 0, newContent.length(), true, maxSize, null);
            //需要显示的长度
            int needSize = startIndex + len + 1;
            StringBuilder builder = new StringBuilder();
            String toString = builder.append(content.subSequence(0, needSize)).append(beforeClickStr).append(expandStr).toString();
            SpannableString span = createSpan(toString, expandStr);
            if (!TextUtils.isEmpty(span)) {
                partContent = span;
            }
        }
    }

    /**
     * 初始化全部内容
     */
    private void initAllContent(CharSequence content) {
        Layout layout = getLayout();
        //展开的情况
        int endIndex = layout.getLineStart(initialLine);
        int startIndex = layout.getLineStart(initialLine - 1);
        int index = endIndex - startIndex;
        if (index > 0) {
            //获取最后行长度
            CharSequence newContent = content.subSequence(0, index);
            TextPaint paint = getPaint();
            float newSize = paint.measureText(newContent.toString());
            //测量点击文字的长度
            float clickSize = paint.measureText(packupStr);
            //获取每行的最大宽度
            float width = layout.getWidth();
            //获取拼接后的长度
            float sumSize = newSize + clickSize;
            //这里有两种情况
            StringBuilder builder = new StringBuilder(content);
            //未超过最大显示
            String splace = getResources().getString(R.string.splace);
            //获取空格长度
            float splaceSize = paint.measureText(splace);
            float needFillSize;
            if (sumSize > width) {
                //超过了最大显示
                builder.append("\n");
                needFillSize = width - clickSize;
            } else {
                needFillSize = width - sumSize;
            }
            int needFillTimes = (int) (needFillSize / splaceSize);
            for (int i = 0; i < needFillTimes; i++) {
                builder.append(splace);
            }
            builder.append(packupStr);
            SpannableString span = createSpan(builder.toString(), packupStr);
            if (!TextUtils.isEmpty(span)) {
                allContent = span;
            }
        }
    }

    /**
     * 点击span
     */
    class ExpandClickableSpan extends ClickableSpan {

        @Override
        public void onClick(View view) {
            isExpand = !isExpand;
            if (isExpand) {
                if (!TextUtils.isEmpty(allContent)) {
                    setText(allContent);
                }
            } else {
                if (!TextUtils.isEmpty(partContent)) {
                    setText(partContent);
                }
            }
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            //设置文本的颜色
            ds.setColor(clickColor);
            //超链接形式的下划线，false 表示不显示下划线，true表示显示下划线
            ds.setUnderlineText(false);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
    }
}

