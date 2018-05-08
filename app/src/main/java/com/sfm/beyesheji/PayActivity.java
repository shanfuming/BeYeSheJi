package com.sfm.beyesheji;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sfm.beyesheji.bean.Thing;
import com.sfm.beyesheji.db.DataHandler;
import com.sfm.beyesheji.global.BYSJApplication;
import com.sfm.beyesheji.util.SharePrefUtil;
import com.sfm.beyesheji.util.ToastUtil;

import java.util.ArrayList;

/**
 * Created by shanfuming on 2018/5/5.
 */

public class PayActivity extends BaseActivity {
    private TextView tv_money,tv_pay;
    private ProgressBar progressBar;
    private DataHandler dataHandler = new DataHandler(BYSJApplication.sContext);
    private Thing thing;
    private ArrayList<Thing> things = new ArrayList<>();

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 1:
                    progressBar.setVisibility(View.VISIBLE);
                    handler.sendEmptyMessageDelayed(2,1000);
                    break;
                case 2:
                    progressBar.setVisibility(View.GONE);

                    //保存购物车数据
                    if (things.size() == 0 && thing != null){
                        thing.setIsBuy(1);
                        dataHandler.save(thing);
                    }else if (things.size() > 0){
                        for (int i = 0;i < things.size();i++){
                            things.get(i).setIsBuy(1);
                            dataHandler.save(things.get(i));
                        }
                    }

                    Intent intent = new Intent(PayActivity.this,PaySuccessActivity.class);
                    startActivity(intent);

                    finish();
                    break;
            }
        }
    };
    private LinearLayout ll_back;
    private TextView tv_titlename;
    private TextView tv_address;
    private RelativeLayout rl_address;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_pay);

        ll_back = (LinearLayout) findViewById(R.id.ll_title_back);
        tv_titlename = (TextView) findViewById(R.id.tv_title_name);
        tv_titlename.setText("付款");
        tv_money = (TextView) findViewById(R.id.tv_money);
        tv_pay = (TextView) findViewById(R.id.bt_pay);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        rl_address = (RelativeLayout) findViewById(R.id.rl_address);
        tv_address = (TextView) findViewById(R.id.tv_address);
    }

    @Override
    protected void initData() {
        super.initData();

        final Intent intent = getIntent();
        if (intent.getIntExtra("turnNUm",0) == 1){
            Bundle bundle = intent.getBundleExtra("buyThing");
            thing = (Thing) bundle.getSerializable("theThing");
            tv_money.setText(thing.getMoney());
        }else{
            tv_money.setText(intent.getStringExtra("allMoney"));
            Bundle bundle = intent.getBundleExtra("carBundle");
            things.addAll((ArrayList<Thing>) bundle.getSerializable("carlist"));
        }


        if (TextUtils.isEmpty(SharePrefUtil.getString(BYSJApplication.sContext,"address",""))){
            tv_address.setText("未添加收货地址");
        }else{
            tv_address.setText(SharePrefUtil.getString(BYSJApplication.sContext,"address",""));
        }

        rl_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(PayActivity.this,AddressActivity.class);
                intent1.putExtra("number",1);
                startActivityForResult(intent1,1001);
            }
        });

        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(SharePrefUtil.getString(BYSJApplication.sContext,"address",""))){
                    handler.sendEmptyMessage(1);
                }else{
                    ToastUtil.showToast(BYSJApplication.sContext,"请先添加收货地址");
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == 1001){
                tv_address.setText(data.getStringExtra("addressNew"));
            }
        }
    }
}
