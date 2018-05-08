package com.sfm.beyesheji;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.sfm.beyesheji.global.BYSJApplication;
import com.sfm.beyesheji.util.SharePrefUtil;

/**
 * Created by shanfuming on 2018/5/4.
 */

public class SplashActivity extends BaseActivity{
    private ImageView imageView;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    goHome();
                    break;
            }
        }
    };

    @Override
    protected void initView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initData() {
        super.initData();
        imageView = (ImageView) findViewById(R.id.imageView);

        handler.sendEmptyMessageDelayed(1,1500);
    }

    private void goHome() {
        if(!SharePrefUtil.getBoolean(this,"isLogin",false)){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        finish();
    }
}

