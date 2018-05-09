package com.sfm.beyesheji;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initView();
        initData();
    }
    //获取所有页面控件
    protected abstract void initView();
    //处理基本的数据逻辑
    protected void initData(){}
}
