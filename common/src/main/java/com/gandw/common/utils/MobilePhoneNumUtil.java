package com.gandw.common.utils;

import android.text.TextUtils;

/**
 * Author      : GandW
 * Time        : 2016/12/20 10:44
 * E-mail      : wshkwg@163.com
 * Description : 有关格式手机号码
 */

public class MobilePhoneNumUtil {
    private MobilePhoneNumUtil() {
    }

    /**
     * @param mobileNum  手机号码
     * @param replaceStr 替换字符
     * @return 中间四位数屏蔽的手机号码
     */
    public static String hideMobilePhoneNum(String mobileNum, String replaceStr) {
        if (TextUtils.isEmpty(mobileNum) || mobileNum.length() != 11) {
            return mobileNum;
        } else {
            try {
                StringBuilder builder = new StringBuilder();
                builder.append(mobileNum.substring(0, 3))
                        .append(replaceStr)
                        .append(mobileNum.substring(7, mobileNum.length()));
                return builder.toString();
            } catch (Exception e) {
                return mobileNum;
            }
        }
    }

}
