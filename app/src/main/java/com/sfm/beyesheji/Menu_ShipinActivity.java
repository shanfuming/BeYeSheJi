package com.sfm.beyesheji;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sfm.beyesheji.bean.ShiPin;
import com.sfm.beyesheji.bean.Thing;
import com.sfm.beyesheji.global.BYSJApplication;
import com.sfm.beyesheji.util.ToastUtil;

import java.util.ArrayList;

/**
 * 视频页面
 */

public class Menu_ShipinActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout ll_tab1_11,ll_tab1_12,ll_tab1_13,ll_tab1_14,ll_tab1_21,ll_tab1_22,ll_tab1_23,ll_tab1_24,ll_back;
    private ListView listview;
    private TextView tv_titleName;
    private ArrayList<ShiPin> shipinList = new ArrayList<>();
    public static String shipinKey = "shipin";
    private Button bt_search;
    private EditText et_hotword;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_shipin);

        ll_back = (LinearLayout) findViewById(R.id.ll_title_back);
        tv_titleName = (TextView) findViewById(R.id.tv_title_name);
        ll_tab1_11 = (LinearLayout) findViewById(R.id.ll_tab1_11);
        ll_tab1_12 = (LinearLayout) findViewById(R.id.ll_tab1_12);
        ll_tab1_13 = (LinearLayout) findViewById(R.id.ll_tab1_13);
        ll_tab1_14 = (LinearLayout) findViewById(R.id.ll_tab1_14);
        ll_tab1_21 = (LinearLayout) findViewById(R.id.ll_tab1_21);
        ll_tab1_22 = (LinearLayout) findViewById(R.id.ll_tab1_22);
        ll_tab1_23 = (LinearLayout) findViewById(R.id.ll_tab1_23);
        ll_tab1_24 = (LinearLayout) findViewById(R.id.ll_tab1_24);
        listview = (ListView) findViewById(R.id.listview);
        bt_search = (Button) findViewById(R.id.bt_search);
        et_hotword = (EditText) findViewById(R.id.et_hot_word);


        ll_back.setVisibility(View.GONE);
        tv_titleName.setText("视频");
        ll_tab1_11.setOnClickListener(this);
        ll_tab1_12.setOnClickListener(this);
        ll_tab1_13.setOnClickListener(this);
        ll_tab1_14.setOnClickListener(this);
        ll_tab1_21.setOnClickListener(this);
        ll_tab1_22.setOnClickListener(this);
        ll_tab1_23.setOnClickListener(this);
        ll_tab1_24.setOnClickListener(this);
        bt_search.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        ShiPin shipin1 = new ShiPin("视频一","副标题描述一",R.mipmap.shipin1,"120001");
        ShiPin shipin2 = new ShiPin("视频二","副标题描述二",R.mipmap.shipin2,"120002");
        ShiPin shipin3 = new ShiPin("视频三","副标题描述三",R.mipmap.shipin3,"120003");
        shipinList.add(shipin1);
        shipinList.add(shipin2);
        shipinList.add(shipin3);

        listview.setAdapter(new MyAdapter(shipinList));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Menu_ShipinActivity.this,ShiPinActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(shipinKey, shipinList.get(position));
                intent.putExtra("shipinBundle",bundle);
                startActivity(intent);
            }
        });
        et_hotword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0){
                    bt_search.setVisibility(View.VISIBLE);
                }else{
                    bt_search.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_tab1_11:
                Intent intent = new Intent(Menu_ShipinActivity.this,ClassifyActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_tab1_12:
                Intent intent2 = new Intent(Menu_ShipinActivity.this,ClassifyActivity.class);
                startActivity(intent2);
                break;
            case R.id.ll_tab1_13:
                Intent intent3 = new Intent(Menu_ShipinActivity.this,ClassifyActivity.class);
                startActivity(intent3);
                break;
            case R.id.ll_tab1_14:
                Intent intent4 = new Intent(Menu_ShipinActivity.this,ClassifyActivity.class);
                startActivity(intent4);
                break;
            case R.id.ll_tab1_21:
                break;
            case R.id.ll_tab1_22:
                break;
            case R.id.ll_tab1_23:
                break;
            case R.id.ll_tab1_24:
                break;
            case R.id.bt_search:
                for (int i = 0;i < shipinList.size();i++){
                    if (TextUtils.equals(shipinList.get(i).getTitle1(),et_hotword.getText().toString().trim())){
                        Intent intent6 = new Intent(Menu_ShipinActivity.this,ShiPinActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(shipinKey, shipinList.get(i));
                        intent6.putExtra("shipinBundle",bundle);
                        startActivity(intent6);
                        return;
                    }

                    if (!TextUtils.equals(shipinList.get(i).getTitle1(),et_hotword.getText().toString().trim())){
                        ToastUtil.showToast(Menu_ShipinActivity.this,"无此视频，仅支持输入视频列表中的名称");
                    }
                }
                break;
        }
    }

    class MyAdapter extends BaseAdapter {

        private ArrayList<ShiPin> shiPins;

        public MyAdapter(ArrayList<ShiPin> things) {
            this.shiPins = things;
        }

        @Override
        public int getCount() {
            return shiPins.size();
        }

        @Override
        public ShiPin getItem(int position) {
            return shiPins.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyHolder myHolder = null;
            if (convertView == null){
                myHolder = new MyHolder();
                convertView = View.inflate(Menu_ShipinActivity.this,R.layout.item_shiji,null);
                myHolder.img = (ImageView) convertView.findViewById(R.id.image);
                myHolder.title1 = (TextView) convertView.findViewById(R.id.title1);
                myHolder.title2 = (TextView) convertView.findViewById(R.id.title2);

                convertView.setTag(myHolder);

            }else{
                myHolder = (MyHolder) convertView.getTag();
            }
            myHolder.title1.setText(shiPins.get(position).getTitle1());
            myHolder.title2.setText(shiPins.get(position).getTitle2());
            myHolder.img.setImageResource(shiPins.get(position).getImgId());

            return convertView;
        }
    }

    class MyHolder{
        TextView title1;
        TextView title2;
        ImageView img;
    }

    @Override
    protected void onResume() {
        super.onResume();
//        srollview.scrollTo(0,0);
    }
}
