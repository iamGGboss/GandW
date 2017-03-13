package com.gandw.statusview;

import android.support.annotation.LayoutRes;

/**
 * Author      : GandW
 * Time        : 2017/3/6 20:59
 * E-mail      : wshkwg@163.com
 * Description :
 */
public class StatusConfig {

    StatusConfig() {
    }

    @LayoutRes
    int contentViewID;
    @LayoutRes
    int loadingViewID;
    @LayoutRes
    int errorViewID;
    @LayoutRes
    int noDataViewID;
    @LayoutRes
    int noNetViewID;

    StatusLayout.ErrorRetryListener errorRetryListener;

    StatusLayout.NoDataRetryListener noDataRetryListener;

    StatusLayout.NoNetRetryListener noNetRetryListener;
}
