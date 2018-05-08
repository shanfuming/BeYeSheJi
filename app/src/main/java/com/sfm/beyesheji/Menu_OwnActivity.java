package com.sfm.beyesheji;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sfm.beyesheji.global.BYSJApplication;
import com.sfm.beyesheji.util.SharePrefUtil;

/**
 * Created by shanfuming on 2018/5/4.
 */

public class Menu_OwnActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout rl_address,rl_order,rl_car,rl_like,rl_user;
    private TextView tv_userName;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_own);

        rl_user = (RelativeLayout) findViewById(R.id.rl_user);
        tv_userName = (TextView) findViewById(R.id.tv_userName);
        rl_address = (RelativeLayout) findViewById(R.id.rl_own_address);
        rl_order = (RelativeLayout) findViewById(R.id.rl_own_order);
        rl_car = (RelativeLayout) findViewById(R.id.rl_own_car);
        rl_like = (RelativeLayout) findViewById(R.id.rl_own_like);

    }

    @Override
    protected void initData() {
        super.initData();

        tv_userName.setText(SharePrefUtil.getString(BYSJApplication.sContext,"username",""));
        rl_user.setOnClickListener(this);
        rl_address.setOnClickListener(this);
        rl_order.setOnClickListener(this);
        rl_car.setOnClickListener(this);
        rl_like.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_own_address:
                Intent intent = new Intent(Menu_OwnActivity.this,AddressActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_own_order:
                Intent intent2 = new Intent(Menu_OwnActivity.this,OrderActivity.class);
                startActivity(intent2);
                break;
            case R.id.rl_own_car:
                Intent intent3 = new Intent(Menu_OwnActivity.this,CarActivity.class);
                startActivity(intent3);
                break;
            case R.id.rl_own_like:
                Intent intent4 = new Intent(Menu_OwnActivity.this,LikeActivity.class);
                startActivity(intent4);
                break;
            case R.id.rl_user:
                Intent intent5 = new Intent(Menu_OwnActivity.this,UserActivity.class);
                startActivity(intent5);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        tv_userName.setText(SharePrefUtil.getString(BYSJApplication.sContext,"username",""));
    }
}
