package com.sfm.beyesheji;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sfm.beyesheji.global.BYSJApplication;
import com.sfm.beyesheji.util.SharePrefUtil;
import com.sfm.beyesheji.util.ToastUtil;

import java.util.regex.Pattern;

/**
 * 登录页面
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_close;
    private EditText et_username,et_password;
    private TextView tv_login,tv_regist,tv_forgetPass;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);

        iv_close = (ImageView) findViewById(R.id.iv_close);
        et_username = (EditText) findViewById(R.id.et_userName);
        et_password = (EditText) findViewById(R.id.et_userPass);
        tv_login = (TextView) findViewById(R.id.tv_login);
        tv_regist = (TextView) findViewById(R.id.tv_regist);
        tv_forgetPass = (TextView) findViewById(R.id.tv_forgetPass);
    }

    @Override
    protected void initData() {
        super.initData();
        iv_close.setOnClickListener(this);
        tv_login.setOnClickListener(this);
        tv_regist.setOnClickListener(this);
        tv_forgetPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_login:
                login();
                break;
            case R.id.tv_regist:
                regist();
                break;
            case R.id.tv_forgetPass:
                forgetPass();
                break;
            case R.id.iv_close:
                finish();
                break;
        }
    }

    /**
     * 忘记密码
     */
    private void forgetPass() {
        Intent intent = new Intent(this,ForgetPassActivity.class);
        startActivity(intent);
    }

    /**
     * 注册
     */
    private void regist() {
        String userphone = et_username.getText().toString().trim();
        Intent intent = new Intent(this,RegistActivity.class);
        if (!TextUtils.isEmpty(userphone)){
            intent.putExtra("userphone",userphone);
        }
        startActivity(intent);
    }

    /**
     * 登录：点击登录时会进行验证，是否注册，是否正确填写账号密码等
     */
    private void login() {
        final String userphone = et_username.getText().toString().trim();
        final String password = et_password.getText().toString().trim();

        if (TextUtils.isEmpty(userphone)) {
            ToastUtil.showToast(BYSJApplication.sContext, "请输入手机号");
            return;
        }
        if (!Pattern.compile("^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$").matcher(userphone).matches()) {
            ToastUtil.showToast(BYSJApplication.sContext, "请输入正确的手机号");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showToast(BYSJApplication.sContext, "请输入密码");
            return;
        }
        //未注册的先注册，在登录
        if(TextUtils.equals(SharePrefUtil.getString(this,userphone,""),"")){//未注册
            ToastUtil.showToast(BYSJApplication.sContext, "您还没有注册，请先注册");
        }else if(TextUtils.equals(SharePrefUtil.getString(this,userphone,""),password)){//已注册
            SharePrefUtil.saveBoolean(this,"isLogin",true);
            SharePrefUtil.saveString(BYSJApplication.sContext,"username",userphone);
            ToastUtil.showToast(BYSJApplication.sContext, "登录成功");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            ToastUtil.showToast(BYSJApplication.sContext, "手机号或密码不正确");
        }
    }
}
