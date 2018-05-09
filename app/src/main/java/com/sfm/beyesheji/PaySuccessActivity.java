package com.sfm.beyesheji;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

/**
 * 支付成功页
 */

public class PaySuccessActivity extends BaseActivity implements View.OnClickListener {
    private Button bt_back,bt_order;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_paysuccess);

        bt_back = (Button) findViewById(R.id.bt_back);
        bt_order = (Button) findViewById(R.id.bt_order);
    }

    @Override
    protected void initData() {
        super.initData();

        bt_back.setOnClickListener(this);
        bt_order.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_back://返回
                finish();
                break;
            case R.id.bt_order://查看订单
                Intent intent = new Intent(this,OrderActivity.class);
                startActivity(intent);
                break;
        }
    }
}
