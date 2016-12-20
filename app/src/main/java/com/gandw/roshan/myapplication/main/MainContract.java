package com.gandw.roshan.myapplication.main;

import com.gandw.common.BasePresenter;
import com.gandw.common.BaseView;

/**
 * Author      : GandW
 * Time        : 2016/12/20 14:10
 * E-mail      : wshkwg@163.com
 * Description : 主页的约束类
 */

public interface MainContract {

    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {

    }


}
