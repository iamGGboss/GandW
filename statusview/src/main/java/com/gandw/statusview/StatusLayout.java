package com.gandw.statusview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Author      : GandW
 * Time        : 2017/3/6 09:53
 * E-mail      : wshkwg@163.com
 * Description : 使用建造者模式创建不同状态的对应布局,根据不同状态显示不同的view
 */
public class StatusLayout extends FrameLayout implements IStatusView {

    private StatusConfig config;

    private SparseArray<View> sparseArray = new SparseArray<>();

    public StatusLayout(Context context) {
        super(context);
    }

    public StatusLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StatusLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setConfig(StatusConfig config) {
        this.config = config;
    }

    @Override
    public void showContentView() {
        checkNoNull(config);
        View view = getShowView(config.contentViewID);
        this.addView(view);
    }

    @Override
    public void showNoDataView() {
        checkNoNull(config);
        View view = getShowView(config.noDataViewID);
        final NoDataRetryListener listener = config.noDataRetryListener;
        if (null != listener) {
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onRetry();
                }
            });
        }
        this.addView(view);
    }

    @Override
    public void showLoadingView() {
        checkNoNull(config);
        View view = getShowView(config.loadingViewID);
        this.addView(view);
    }

    @Override
    public void showErrowView() {
        checkNoNull(config);
        View view = getShowView(config.errorViewID);
        final ErrorRetryListener listener = config.errorRetryListener;
        if (null != listener) {
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onRetry();
                }
            });
        }
        this.addView(view);
    }

    @Override
    public void showNoNetView() {
        checkNoNull(config);
        View view = getShowView(config.noNetViewID);
        final NoNetRetryListener listener = config.noNetRetryListener;
        if (null != listener) {
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onRetry();
                }
            });
        }
        this.addView(view);
    }

    @Override
    public View getContentView() {
        checkNoNull(config);
        return sparseArray.get(config.contentViewID);
    }

    private void checkNoNull(StatusConfig config) {
        if (null == config) {
            throw new RuntimeException("statusLayout config can't be null");
        }
    }

    private View getShowView(int resID) {
        View view = sparseArray.get(resID);
        if (null == view) {
            view = LayoutInflater.from(this.getContext()).inflate(resID, null);
            sparseArray.put(resID, view);
        }
        return view;
    }

    public static class Builder {

        private StatusConfig config;

        public Builder() {
            config = new StatusConfig();
        }

        public Builder setContentViewID(@LayoutRes int contentViewID) {
            config.contentViewID = contentViewID;
            return this;
        }

        public Builder setLoadingViewID(@LayoutRes int loadingViewID) {
            config.loadingViewID = loadingViewID;
            return this;
        }

        public Builder setErrorViewID(@LayoutRes int errorViewID) {
            config.errorViewID = errorViewID;
            return this;
        }

        public Builder setNoDataViewID(@LayoutRes int noDataViewID) {
            config.noDataViewID = noDataViewID;
            return this;
        }

        public Builder setNoNetViewID(@LayoutRes int noNetViewID) {
            config.noNetViewID = noNetViewID;
            return this;
        }

        public Builder setErrorRetryListener(ErrorRetryListener listener) {
            config.errorRetryListener = listener;
            return this;
        }

        public Builder setNoDataRetryListener(NoDataRetryListener listener) {
            config.noDataRetryListener = listener;
            return this;
        }

        public Builder setNoNetRetryListener(NoNetRetryListener listener) {
            config.noNetRetryListener = listener;
            return this;
        }

        public StatusConfig build() {
            return this.config;
        }

    }

    interface ErrorRetryListener {
        void onRetry();
    }

    interface NoDataRetryListener {
        void onRetry();
    }

    interface NoNetRetryListener {
        void onRetry();
    }
}
