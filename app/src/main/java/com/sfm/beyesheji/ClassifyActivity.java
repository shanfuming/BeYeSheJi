package com.sfm.beyesheji;

import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by shanfuming on 2018/5/5.
 */

public class ClassifyActivity extends BaseActivity {
    private TextView tv_titleName;
    private LinearLayout ll_back;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_classify);

        ll_back = (LinearLayout) findViewById(R.id.ll_title_back);
        tv_titleName = (TextView) findViewById(R.id.tv_title_name);
        tv_titleName.setText("分类");
    }

    @Override
    protected void initData() {
        super.initData();
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
