package com.gandw.roshan.myapplication.main;

import static com.gandw.common.Preconditions.*;

/**
 * Author      : GandW
 * Time        : 2016/12/20 14:12
 * E-mail      : wshkwg@163.com
 * Description :
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = checkNotNull(view);
        view.createPresenter();
    }
}
