package com.sfm.beyesheji;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sfm.beyesheji.bean.ShiPin;
import com.sfm.beyesheji.db.Like_DataHandler;
import com.sfm.beyesheji.global.BYSJApplication;
import com.sfm.beyesheji.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shanfuming on 2018/5/6.
 */

public class LikeActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout ll_back;
    private TextView tv_titleName;
    private TextView tv_delete,tv_tip;
    private ListView like_listview;
    Button bt_delete;
    private Like_DataHandler dataHandler;
    private ArrayList<ShiPin> shiPins = new ArrayList<>();
    private HashMap<Integer,ShiPin> deleteList = new HashMap<>();
    private boolean isShowSelect = false;
    private LikeAdapter likeAdapter;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_like);

        ll_back = (LinearLayout) findViewById(R.id.ll_title_back);
        tv_titleName = (TextView) findViewById(R.id.tv_title_name);
        tv_titleName.setText("收藏");
        tv_delete = (TextView) findViewById(R.id.tv_title_function2);
        tv_tip = (TextView) findViewById(R.id.tv_tip);
        like_listview = (ListView) findViewById(R.id.like_listview);
        bt_delete = (Button) findViewById(R.id.bt_delete);
    }

    @Override
    protected void initData() {
        super.initData();

        tv_delete.setText("管理");

        ll_back.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
        bt_delete.setOnClickListener(this);

        dataHandler = new Like_DataHandler(BYSJApplication.sContext);
        shiPins.addAll(dataHandler.findLike());

        if (shiPins.size() == 0){
            tv_tip.setVisibility(View.VISIBLE);
            like_listview.setVisibility(View.GONE);
        }else{
            likeAdapter = new LikeAdapter(shiPins);
            like_listview.setAdapter(likeAdapter);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_title_back:
                finish();
                break;
            case R.id.tv_title_function2:
                if (isShowSelect){
                    isShowSelect = false;
                    tv_delete.setText("管理");
                    bt_delete.setVisibility(View.GONE);
                }else{
                    isShowSelect = true;
                    tv_delete.setText("完成");
                    bt_delete.setVisibility(View.VISIBLE);
                }
                likeAdapter.notifyDataSetChanged();
                break;
            case R.id.bt_delete:
                for (int position : deleteList.keySet()){
                    dataHandler.delete(deleteList.get(position).getTitle1());
                }
                shiPins.clear();
                shiPins.addAll(dataHandler.findLike());
                likeAdapter.notifyDataSetChanged();
                if (shiPins.size() == 0){
                    tv_tip.setVisibility(View.VISIBLE);
                    like_listview.setVisibility(View.GONE);
                }
                break;
        }
    }

    class LikeAdapter extends BaseAdapter {

        private ArrayList<ShiPin> shiPins1;

        public LikeAdapter(ArrayList<ShiPin> things) {
            this.shiPins1 = things;
        }

        @Override
        public int getCount() {
            return shiPins1.size();
        }

        @Override
        public ShiPin getItem(int position) {
            return shiPins1.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            MyHolder myHolder = null;
            if (convertView == null){
                myHolder = new MyHolder();
                convertView = View.inflate(LikeActivity.this,R.layout.item_car,null);
                myHolder.img = (ImageView) convertView.findViewById(R.id.image);
                myHolder.title1 = (TextView) convertView.findViewById(R.id.title1);
                myHolder.title2 = (TextView) convertView.findViewById(R.id.title2);
                myHolder.tip = (TextView) convertView.findViewById(R.id.tv_tip1);
                myHolder.select = (ImageView) convertView.findViewById(R.id.iv_select);
                convertView.setTag(myHolder);

            }else{
                myHolder = (MyHolder) convertView.getTag();
            }
            myHolder.tip.setVisibility(View.GONE);
            myHolder.title1.setText(shiPins1.get(position).getTitle1());
            myHolder.title2.setText(shiPins1.get(position).getTitle2());
            myHolder.img.setImageResource(shiPins1.get(position).getImgId());
            final MyHolder finalMyHolder = myHolder;
            myHolder.select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (shiPins1.get(position).isChecked()){
                        finalMyHolder.select.setImageDrawable(getResources().getDrawable(R.mipmap.selectgray));
                        shiPins1.get(position).setChecked(false);
                        deleteList.remove(position);
                    }else{
                        finalMyHolder.select.setImageDrawable(getResources().getDrawable(R.mipmap.selectred));
                        shiPins1.get(position).setChecked(true);
                        deleteList.put(position, shiPins1.get(position));
                    }
                }
            });
            if (isShowSelect){
                myHolder.select.setVisibility(View.VISIBLE);
            }else{
                myHolder.select.setVisibility(View.GONE);
            }

            return convertView;
        }
    }

    class MyHolder{
        TextView title1;
        TextView title2;
        ImageView img;
        TextView tip;
        ImageView select;
    }
}
