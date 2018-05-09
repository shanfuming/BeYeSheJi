package com.sfm.beyesheji;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sfm.beyesheji.bean.Thing;
import com.sfm.beyesheji.db.DataHandler;
import com.sfm.beyesheji.global.BYSJApplication;
import com.sfm.beyesheji.util.ToastUtil;

/**
 * 商品详情页
 */

public class ThingActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout ll_back,ll_Car;
    private TextView tv_titlename,thing_title1,thing_title2,thing_money;
    private ImageView iv_thing,iv_car;
    private Button buyCar,buyNow;
    private Thing thing;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_thing);

        ll_back = (LinearLayout) findViewById(R.id.ll_title_back);
        tv_titlename = (TextView) findViewById(R.id.tv_title_name);
        iv_car = (ImageView) findViewById(R.id.iv_car);
        iv_thing = (ImageView) findViewById(R.id.imageView1);
        thing_title1 = (TextView) findViewById(R.id.thing_title1);
        thing_title2 = (TextView) findViewById(R.id.thing_title2);
        thing_money = (TextView) findViewById(R.id.thing_money);
        buyCar = (Button) findViewById(R.id.button);
        buyNow = (Button) findViewById(R.id.button2);

    }

    @Override
    protected void initData() {
        super.initData();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("thingBundle");
        thing = (Thing) bundle.getSerializable(Menu_ShijiActivity.thingKey);
        iv_thing.setImageResource(thing.getImgId());
        tv_titlename.setText(thing.getTitle1());
        thing_title1.setText(thing.getTitle1());
        thing_title2.setText(thing.getTitle2());
        thing_money.setText(thing.getMoney());

        ll_back.setOnClickListener(this);
        buyCar.setOnClickListener(this);
        buyNow.setOnClickListener(this);
        iv_car.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.button:
                addCar();
                break;
            case R.id.button2:
                buyNow();
                break;
            case R.id.iv_car:
                buyCar();
                break;

        }
    }
    //加入购物车
    private void addCar() {
        DataHandler dataHandler = new DataHandler(BYSJApplication.sContext);
        dataHandler.save(thing);
        ToastUtil.showToast(BYSJApplication.sContext,"已加入购物车");
    }

    //立即购买
    private void buyNow() {
        Intent intent =  new Intent(this,PayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("theThing",thing);
        intent.putExtra("buyThing",bundle);
        intent.putExtra("turnNUm",1);
        startActivity(intent);
    }

    //购物车
    private void buyCar() {
        Intent intent = new Intent(ThingActivity.this,CarActivity.class);
        startActivity(intent);
    }
}
