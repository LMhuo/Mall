package com.zhongmian.mall.activity;

import android.app.Activity;
import android.os.Bundle;

import com.zhongmian.mall.R;

public class ContentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        init();
    }

    private void init() {
    }
}
