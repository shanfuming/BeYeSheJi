package com.sfm.beyesheji;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sfm.beyesheji.framework.MenuActivity;
import com.sfm.beyesheji.framework.TabItem;
import com.sfm.beyesheji.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 底部按钮控制页面
 */

public class MainActivity extends MenuActivity implements OnClickListener {

    public static final String SHIJI = "Shiji";
    public static final String SHIPIN = "Shipin";
    public static final String OWN = "own";

    public static final int REQUEST_PERMISSION_STORAGE = 0x01;

    private List<TabItem> mTabItem;
    private List<View> mTabView;
    private LinearLayout mLlBtnShipin, mLlBtnShiji,  mLlBtnOwn;
    private ImageView mIvShipin,mIvOwn, mIvShiji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFirstViewPage();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int tab = intent.getIntExtra("tab", -1);
        if (tab != -1) {
            switch (tab) {
                case 0:
                    updateTab(mLlBtnShiji);
                    break;
                case 1:
                    updateTab(mLlBtnShipin);
                    break;
                case 2:
                    updateTab(mLlBtnOwn);
                    break;
            }
        }
    }

    @Override
    protected void initTabItem() {
        mTabItem = new ArrayList<TabItem>();

        TabItem shijiItem = new TabItem(SHIJI, R.drawable.main_tab1_selector, new Intent(this, Menu_ShijiActivity.class));
        TabItem shipinItem = new TabItem(SHIPIN, R.drawable.main_tab2_selector, new Intent(this, Menu_ShipinActivity.class));
        TabItem ownItem = new TabItem(OWN, R.drawable.main_tab2_selector, new Intent(this, Menu_OwnActivity.class));

        mTabItem.add(shijiItem);
        mTabItem.add(shipinItem);
        mTabItem.add(ownItem);

    }

    @Override
    protected void initTabView() {

        mTabView = new ArrayList<View>();
        mLlBtnShipin = (LinearLayout) findViewById(R.id.ll_shipin);
        mLlBtnShiji = (LinearLayout) findViewById(R.id.ll_shiji);
        mLlBtnOwn = (LinearLayout) findViewById(R.id.ll_own);

        mIvShipin = (ImageView) findViewById(R.id.iv_menu_shipin);
        mIvOwn = (ImageView) findViewById(R.id.iv_menu_own);
        mIvShiji = (ImageView) findViewById(R.id.iv_menu_shiji);

        mLlBtnShipin.setOnClickListener(this);
        mLlBtnShiji.setOnClickListener(this);
        mLlBtnOwn.setOnClickListener(this);

        mTabView.add(mLlBtnShiji);
        mTabView.add(mLlBtnShipin);
        mTabView.add(mLlBtnOwn);
    }

    @Override
    protected int getTabItemCount() {
        return mTabItem.size();
    }

    @Override
    protected int getTabViewCount() {
        return mTabView.size();
    }

    @Override
    protected Intent getTabItemIntent(int position) {
        return mTabItem.get(position).getIntent();
    }

    @Override
    protected View getTabView(int position) {
        return mTabView.get(position);
    }

    @Override
    protected String getTabItemId(int posotion) {
        return mTabItem.get(posotion).getTitle();
    }

    @Override
    protected void setTabItemPic(View view, int position) {
        Drawable image = getResources().getDrawable(mTabItem.get(position).getIcon());
        ((ImageView) view).setImageDrawable(image);
    }

    @Override
    public void onClick(View v) {
        updateTab(v);
        switch (v.getId()){
            case R.id.ll_own:
                setImgAnimation(mIvOwn);
                break;
            case R.id.ll_shipin:
                setImgAnimation(mIvShipin);
                break;
            case R.id.ll_shiji:
                setImgAnimation(mIvShiji);
                break;

        }
    }

    /**
     * 底部按钮点击效果
     * @param img
     */
    private void setImgAnimation(ImageView img){
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(img, "scaleY", 1, 1.5f, 1);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(img, "scaleX", 1, 1.5f, 1);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(scaleX,scaleY);
        set.setDuration(400);
        set.start();
    }
}
