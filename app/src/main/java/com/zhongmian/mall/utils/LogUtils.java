package com.zhongmian.mall.utils;

import android.util.Log;

/**
 * Created by L on 2016/2/28 0028.
 */
public class LogUtils {
    public static boolean isDebug = false;

    public static void i(String tag,String msg ){
        if(isDebug){
            Log.i(tag, msg);
        }

    }
}
