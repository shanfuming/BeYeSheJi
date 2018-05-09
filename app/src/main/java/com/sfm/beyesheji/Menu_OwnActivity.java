package com.sfm.beyesheji;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sfm.beyesheji.db.DataHandler;
import com.sfm.beyesheji.db.Like_DataHandler;
import com.sfm.beyesheji.global.BYSJApplication;
import com.sfm.beyesheji.util.SharePrefUtil;

/**
 * 我的页面
 */

public class Menu_OwnActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout rl_address,rl_order,rl_car,rl_like,rl_user;
    private TextView tv_userName;
    private RelativeLayout rl_out;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_own);

        rl_user = (RelativeLayout) findViewById(R.id.rl_user);
        tv_userName = (TextView) findViewById(R.id.tv_userName);
        rl_address = (RelativeLayout) findViewById(R.id.rl_own_address);
        rl_order = (RelativeLayout) findViewById(R.id.rl_own_order);
        rl_car = (RelativeLayout) findViewById(R.id.rl_own_car);
        rl_like = (RelativeLayout) findViewById(R.id.rl_own_like);
        rl_out = (RelativeLayout) findViewById(R.id.rl_own_out);

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
        rl_out.setOnClickListener(this);
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
            case R.id.rl_own_out:
                //退出登录，修改是否登录字段，由于没有数据交互，购物车，订单，收藏都是保存在本地数据库的，退出只能对当前账户的本地数据库进行清空
                SharePrefUtil.saveBoolean(this,"isLogin",false);
                SharePrefUtil.saveString(this,"address","");
                //清空数据库
                DataHandler dataHandler = new DataHandler(BYSJApplication.sContext);
                dataHandler.deleteAll();
                Like_DataHandler like_dataHandler = new Like_DataHandler(BYSJApplication.sContext);
                like_dataHandler.deleteAll();

                Intent intent7 = new Intent(Menu_OwnActivity.this,LoginActivity.class);
                startActivity(intent7);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        tv_userName.setText(SharePrefUtil.getString(BYSJApplication.sContext,"username",""));
    }
}
