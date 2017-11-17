package com.gandw.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Author      : GandW
 * Time        : 2017/11/17 11:45
 * E-mail      : wshkwg@163.com
 * Description :
 */
public class AppUtils {
    /**
     * @param context 上下文
     * @return 返回应用版本号
     */
    public static int getAppVersion(Context context) {
        int version = 0;
        try {
            PackageInfo packageInfo = context.getApplicationContext().getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            version = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }
}
