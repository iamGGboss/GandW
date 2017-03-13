package com.gandw.statusview;

import android.view.View;

/**
 * Author      : GandW
 * Time        : 2017/3/6 13:21
 * E-mail      : wshkwg@163.com
 * Description :
 */
public interface IStatusView {

    void showContentView(); //显示内容界面

    void showNoDataView();  //显示无数据界面

    void showLoadingView();    //显示加载界面

    void showErrowView();   //显示错误界面

    void showNoNetView();   //显示无网络界面

    View getContentView();  //获取内容布局

}
