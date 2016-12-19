package gandw.com.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Author      : GandW
 * Time        : 2016/12/15 10:08
 * Description : 展开收起的Textview
 */

public class ExpandTextView extends TextView {

    private static final int DEFAULT_LINSE = 3;
    private static final String DEFAULT_BEFORE_CLICK = "...";
    private static final String DEFAULT_EXPAND = "展开";
    private static final String DEFAULT_PACK_UP = "收起";

    private int maxLines;   //最大行数
    private String beforeClickStr;  //显示在点击之前的字符
    private String expandStr;   //展开的文字
    private String packupStr;   //收起的文字
    private String clickStr;    //点击的文字
    private int clickColor;     //点击的颜色
    private boolean isExpand = true;   //默认不展开
    private SpannableString allContent;  //全部内容
    private SpannableString partContent; //部分内容
    private CharSequence initialContent;  //初始内容
    private int initialLine;    //初始行数
    private BufferType mBufferType = BufferType.NORMAL; //获取的文字样式

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
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandTextView);
        int size = typedArray.getInteger(R.styleable.ExpandTextView_maxLines, -1);
        if (size <= 0) {
            size = DEFAULT_LINSE;
        }
        maxLines = size;
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
     * @param text 文本内容
     * @param type 文本样式
     *             <p>
     *             每次重新调用时，重置内容
     */
    @Override
    public void setText(CharSequence text, BufferType type) {
        initialContent = text;
        mBufferType = type;
        resetContent();
        super.setText(text, type);
    }

    /**
     * 重置显示内容
     */
    private void resetContent() {
        if (onGlobalLayoutListener == null || maxLines < 1 || TextUtils.isEmpty(initialContent)) {
            return;
        }
        clickStr = isExpand ? packupStr : expandStr;
        getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
    }

    /**
     * 控件加载监听器
     */
    ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onGlobalLayout() {
            getViewTreeObserver().removeOnGlobalLayoutListener(this);
            initialLine = getLineCount();
            if (initialLine > maxLines) {
                //大于设置的最大长度后做处理
                handleContent();
            }
        }
    };


    /**
     * 初始化的时候的处理
     */
    private void handleContent() {
        SpannableString contentSpan = getContent(initialContent);
        if (!TextUtils.isEmpty(contentSpan)) {
            if (isExpand) {
                if (TextUtils.isEmpty(allContent)) {
                    allContent = contentSpan;
                }
                setText(allContent);
            } else {
                if (TextUtils.isEmpty(partContent)) {
                    partContent = contentSpan;
                }
                setText(partContent);
            }
            setMovementMethod(LinkMovementMethod.getInstance());    //不设置 没有点击
            setHighlightColor(Color.TRANSPARENT); //设置点击后的颜色为透明
        }
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

    private SpannableString getContent(CharSequence content) {
        if (TextUtils.isEmpty(clickStr) || TextUtils.isEmpty(content)) {
            return null;
        }
        Layout layout = getLayout();
        if (isExpand) {
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
                float clickSize = paint.measureText(clickStr);
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
                builder.append(clickStr);
                SpannableString span = createSpan(builder.toString(), clickStr);
                return span;
            } else {
                return null;
            }
        } else {
            //未展开的情况
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
                float clickSize = paint.measureText(clickStr);
                //计算需要截取的长度
                float maxSize = newContentSize - beforeSize - clickSize;
                //获取截取的坐标
                //下面这几个就是剪切显示，就是大于maxWidth的时候只截取指定长度的显示
                int len = paint.breakText(newContent, 0, newContent.length(), true, maxSize, null);
                //需要显示的长度
                int needSize = startIndex + len + 1;
                StringBuilder builder = new StringBuilder();
                String toString = builder.append(content.subSequence(0, needSize)).append(beforeClickStr).append(clickStr).toString();
                SpannableString span = createSpan(toString, clickStr);
                return span;
            } else {
                return null;
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
                if (TextUtils.isEmpty(allContent)) {
                    clickStr = packupStr;
                    allContent = getContent(initialContent);
                }
                setText(allContent);
            } else {
                if (TextUtils.isEmpty(partContent)) {
                    clickStr = expandStr;
                    partContent = getContent(initialContent);
                }
                setText(partContent);
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

}

