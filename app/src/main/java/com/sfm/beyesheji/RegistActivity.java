package com.sfm.beyesheji;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sfm.beyesheji.global.BYSJApplication;
import com.sfm.beyesheji.util.SharePrefUtil;
import com.sfm.beyesheji.util.ToastUtil;

import java.util.regex.Pattern;

/**
 * Created by shanfuming on 2018/5/4.
 */

public class RegistActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_title,tv_getCode,tv_regist;
    private LinearLayout ll_back;
    private EditText et_phone,et_pass,et_code;
    private TimeCount time;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_regist);

        tv_title = (TextView) findViewById(R.id.tv_title_name);
        ll_back = (LinearLayout) findViewById(R.id.ll_title_back);
        tv_getCode = (TextView) findViewById(R.id.tv_rf_getCode);
        tv_regist = (TextView) findViewById(R.id.tv_rf_regist);
        et_phone = (EditText) findViewById(R.id.et_rf_phone);
        et_pass = (EditText) findViewById(R.id.et_rf_pass);
        et_code = (EditText) findViewById(R.id.et_rf_code);

        tv_title.setText("注册");
        ll_back.setOnClickListener(this);
        tv_getCode.setOnClickListener(this);
        tv_regist.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        String userphone = intent.getStringExtra("userphone");
        if (!TextUtils.isEmpty(userphone)){
            et_phone.setText(userphone);
        }

        time = new TimeCount(60000, 1000);//构造CountDownTimer对象
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_rf_regist:
                regist();
                break;
            case R.id.tv_rf_getCode:
                String userPhone = et_phone.getText().toString().trim();
                if (TextUtils.isEmpty(userPhone)) {
                    ToastUtil.showToast(getApplicationContext(), "手机号不能为空");
                    return;
                }
                if (!Pattern.compile("^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$").matcher(userPhone).matches()) {
                    ToastUtil.showToast(getApplicationContext(), "请输入正确的手机号");
                    return;
                }
                time.start();
                ToastUtil.showToast(getApplicationContext(), "验证码已发送");
                break;
            case R.id.ll_title_back:
                finish();
                break;
        }
    }

    private void regist() {
        String userPhone = et_phone.getText().toString().trim();
        String password = et_pass.getText().toString().trim();
        String verfnum = et_code.getText().toString().trim();

        if (TextUtils.isEmpty(userPhone)) {
            ToastUtil.showToast(getApplicationContext(), "手机号不能为空");
            return;
        }
        if (!Pattern.compile("^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$").matcher(userPhone).matches()) {
            ToastUtil.showToast(getApplicationContext(), "请输入正确的手机号");
            return;
        }
        if (TextUtils.isEmpty(verfnum)) {
            ToastUtil.showToast(getApplicationContext(), "请先输入验证码");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            ToastUtil.showToast(getApplicationContext(), "请先输入密码");
            return;
        }

        if (!TextUtils.equals("123456",verfnum)) {
            ToastUtil.showToast(getApplicationContext(), "请正确输入验证码");
            return;
        }

        SharePrefUtil.saveString(this,userPhone,password);
        ToastUtil.showToast(BYSJApplication.sContext,"注册成功");
        finish();

    }


    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            tv_getCode.setText("重获验证码");
            tv_getCode.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            tv_getCode.setClickable(false);
            tv_getCode.setText(millisUntilFinished / 1000 + "秒");
        }
    }
}
