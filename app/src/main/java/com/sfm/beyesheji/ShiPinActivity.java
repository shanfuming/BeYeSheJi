package com.sfm.beyesheji;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.sfm.beyesheji.bean.ShiPin;
import com.sfm.beyesheji.bean.Thing;
import com.sfm.beyesheji.db.DataHandler;
import com.sfm.beyesheji.db.Like_DataHandler;
import com.sfm.beyesheji.global.BYSJApplication;
import com.sfm.beyesheji.util.ToastUtil;

/**
 * 视频详情页
 */

public class ShiPinActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout ll_back;
    private TextView tv_titlename,thing_title1,thing_title2;
    private VideoView videoView;
    private Button like, actionNow;
    private ShiPin shipin;
    private ImageView iv_bg;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_shipindetail);

        ll_back = (LinearLayout) findViewById(R.id.ll_title_back);
        tv_titlename = (TextView) findViewById(R.id.tv_title_name);
        videoView = (VideoView) findViewById(R.id.videoView);
        thing_title1 = (TextView) findViewById(R.id.thing_title1);
        thing_title2 = (TextView) findViewById(R.id.thing_title2);
        like = (Button) findViewById(R.id.button);
        iv_bg = (ImageView) findViewById(R.id.iv_bg);
        actionNow = (Button) findViewById(R.id.button2);

    }

    @Override
    protected void initData() {
        super.initData();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("shipinBundle");
        shipin = (ShiPin) bundle.getSerializable(Menu_ShipinActivity.shipinKey);
        tv_titlename.setText(shipin.getTitle1());
        iv_bg.setImageResource(shipin.getImgId());
        thing_title1.setText(shipin.getTitle1());
        thing_title2.setText(shipin.getTitle2());
        ll_back.setOnClickListener(this);
        like.setOnClickListener(this);
        actionNow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.button:
                addLike();
                break;
            case R.id.button2:
                action();
                break;
        }
    }
    //收藏
    private void addLike() {
        Like_DataHandler dataHandler = new Like_DataHandler(BYSJApplication.sContext);
        dataHandler.save(shipin);
        ToastUtil.showToast(BYSJApplication.sContext,"收藏成功");
    }

    //立即播放
    private void action() {

        iv_bg.setVisibility(View.GONE);
        videoView.setVisibility(View.VISIBLE);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.ceshi;//获取视频路径
        Uri uri = Uri.parse(path);//将路径转换成uri
        videoView.setVideoURI(uri);//为视频播放器设置视频路径
        videoView.setMediaController(new MediaController(this));//显示控制栏
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();//开始播放视频
            }
        });
    }
}
