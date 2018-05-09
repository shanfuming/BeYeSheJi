package com.sfm.beyesheji;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sfm.beyesheji.global.BYSJApplication;
import com.sfm.beyesheji.util.SharePrefUtil;
import com.sfm.beyesheji.util.ToastUtil;

import java.util.regex.Pattern;

/**
 * 修改手机号页面
 */

public class UserActivity extends BaseActivity implements View.OnClickListener {
    private TextView tvPhone,tv_titlename;
    private Button bt_change,bt_save;
    private LinearLayout ll_show,ll_change,ll_back;
    private EditText et_phone;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_user);

        ll_back = (LinearLayout) findViewById(R.id.ll_title_back);
        tv_titlename = (TextView) findViewById(R.id.tv_title_name);
        ll_show = (LinearLayout) findViewById(R.id.ll_show);
        ll_change = (LinearLayout) findViewById(R.id.ll_change);
        et_phone = (EditText) findViewById(R.id.et_phone);
        bt_save = (Button) findViewById(R.id.bt_save);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        bt_change = (Button) findViewById(R.id.bt_change);
    }

    @Override
    protected void initData() {
        super.initData();

        tv_titlename.setText("账户资料");
        tvPhone.setText(SharePrefUtil.getString(BYSJApplication.sContext,"username",""));
        ll_back.setOnClickListener(this);
        bt_change.setOnClickListener(this);
        bt_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.bt_change:
                ll_change.setVisibility(View.VISIBLE);
                ll_show.setVisibility(View.GONE);
                break;
            case R.id.bt_save:
                ll_change.setVisibility(View.GONE);
                ll_show.setVisibility(View.VISIBLE);
                //判断手机号是否符合格式
                if (TextUtils.isEmpty(et_phone.getText().toString().trim())) {
                    ToastUtil.showToast(BYSJApplication.sContext, "请输入手机号");
                    return;
                }
                if (!Pattern.compile("^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$").matcher(et_phone.getText().toString().trim()).matches()) {
                    ToastUtil.showToast(BYSJApplication.sContext, "请输入正确的手机号");
                    return;
                }
                //符合就保存在本地，修改username字段对应的保存值
                SharePrefUtil.saveString(BYSJApplication.sContext,
                        et_phone.getText().toString().trim(),
                        SharePrefUtil.getString(BYSJApplication.sContext,tvPhone.getText().toString(),""));
                SharePrefUtil.getString(BYSJApplication.sContext,"username",et_phone.getText().toString().trim());
                tvPhone.setText(et_phone.getText().toString().trim());
                break;
        }
    }
}
