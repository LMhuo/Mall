package com.zhongmian.mall.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.zhongmian.mall.R;
import com.zhongmian.mall.utils.LogUtils;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.isDebug=true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                Intent intent = new Intent(getApplicationContext(),ContentActivity.class);
                startActivity(intent);
                finish();
            }
        }).start();

    }
}
