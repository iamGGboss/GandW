package com.gandw.common;

/**
 * Author      : GandW
 * Time        : 2016/12/20 13:24
 * E-mail      : wshkwg@163.com
 * Description : 规定必须生成对应的presenter
 */

public interface BaseView<T> {

    T createPresenter();

}
