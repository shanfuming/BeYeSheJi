package com.sfm.beyesheji;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sfm.beyesheji.global.BYSJApplication;
import com.sfm.beyesheji.util.SharePrefUtil;
import com.sfm.beyesheji.util.ToastUtil;

/**
 * Created by shanfuming on 2018/5/6.
 */

public class AddressActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout ll_back,ll_add;
    private TextView tv_titleName,tv_address;
    private EditText et_add;
    private Button bt_add;
    private RelativeLayout rl_address;
    private ImageView iv_next;
    private int number = 0;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_address);

        ll_back = (LinearLayout) findViewById(R.id.ll_title_back);
        tv_titleName = (TextView) findViewById(R.id.tv_title_name);
        tv_address = (TextView) findViewById(R.id.tv_address);
        et_add = (EditText) findViewById(R.id.et_addAddress);
        bt_add = (Button) findViewById(R.id.bt_add);
        ll_add = (LinearLayout) findViewById(R.id.ll_add);
        rl_address = (RelativeLayout) findViewById(R.id.rl_addressNew);
        iv_next = (ImageView) findViewById(R.id.iv_next);
    }

    @Override
    protected void initData() {
        super.initData();

        Intent intent = getIntent();
        number = intent.getIntExtra("number",0);

        tv_titleName.setText("添加/修改地址");
        if (TextUtils.isEmpty(SharePrefUtil.getString(BYSJApplication.sContext,"address",""))){
            rl_address.setVisibility(View.GONE);
            bt_add.setText("添加");
        }else{
            ll_add.setVisibility(View.GONE);
            bt_add.setText("修改");
            tv_address.setText(SharePrefUtil.getString(BYSJApplication.sContext,"address",""));
        }

        tv_address.setOnClickListener(this);
        bt_add.setOnClickListener(this);
        ll_back.setOnClickListener(this);
        iv_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.bt_add:
                if(TextUtils.isEmpty(et_add.getText().toString())){
                    ToastUtil.showToast(BYSJApplication.sContext,"请输入地址");
                }else{
                    SharePrefUtil.saveString(BYSJApplication.sContext,"address",et_add.getText().toString());
                    ToastUtil.showToast(BYSJApplication.sContext,"地址保存成功");
                    tv_address.setText(et_add.getText().toString());
                    ll_add.setVisibility(View.GONE);
                    rl_address.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.iv_next:
                rl_address.setVisibility(View.GONE);
                ll_add.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_address:
                if (number == 1){//number为1时，证明是从付款界面跳转过来
                    Intent intent = new Intent();
                    intent.putExtra("addressNew",tv_address.getText().toString());
                    setResult(RESULT_OK,intent);

                    finish();
                }
                break;
        }
    }
}
