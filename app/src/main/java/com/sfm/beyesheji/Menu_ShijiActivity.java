package com.sfm.beyesheji;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Script;
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
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import com.sfm.beyesheji.bean.Thing;
import com.sfm.beyesheji.global.BYSJApplication;
import com.sfm.beyesheji.util.ToastUtil;

import java.util.ArrayList;

/**
 * Created by shanfuming on 2018/5/4.
 */

public class Menu_ShijiActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout ll_tab1_11,ll_tab1_12,ll_tab1_13,ll_tab1_14,ll_tab1_21,ll_tab1_22,ll_tab1_23,ll_tab1_24,ll_back;
    private ListView listview;

    private ArrayList<Thing> things = new ArrayList<>();
    public static String thingKey = "thing";
    private ScrollView srollview;
    private TextView tv_titleName;
    private Button bt_search;
    private EditText et_hotword;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_shiji);

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
        srollview = (ScrollView) findViewById(R.id.srollview);
        bt_search = (Button) findViewById(R.id.bt_search);
        et_hotword = (EditText) findViewById(R.id.et_hot_word);

        ll_back.setVisibility(View.GONE);
        tv_titleName.setText("市集");
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

        Thing thing1 = new Thing("商品一","副标题描述一",R.mipmap.p1,"120000");
        Thing thing2 = new Thing("商品二","副标题描述二",R.mipmap.p2,"230000");
        Thing thing3 = new Thing("商品三","副标题描述三",R.mipmap.p3,"150000");
        Thing thing4 = new Thing("商品四","副标题描述四",R.mipmap.p4,"450000");
        Thing thing5 = new Thing("商品五","副标题描述五",R.mipmap.p5,"70000");
        Thing thing6 = new Thing("商品六","副标题描述六",R.mipmap.p6,"128000s");
        things.add(thing1);
        things.add(thing2);
        things.add(thing3);
        things.add(thing4);
        things.add(thing5);
        things.add(thing6);

        listview.setAdapter(new MyAdapter(things));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Menu_ShijiActivity.this,ThingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(thingKey,things.get(position));
                intent.putExtra("thingBundle",bundle);
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
                Intent intent = new Intent(Menu_ShijiActivity.this,ClassifyActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_tab1_12:
                Intent intent2 = new Intent(Menu_ShijiActivity.this,ClassifyActivity.class);
                startActivity(intent2);
                break;
            case R.id.ll_tab1_13:
                Intent intent3 = new Intent(Menu_ShijiActivity.this,ClassifyActivity.class);
                startActivity(intent3);
                break;
            case R.id.ll_tab1_14:
                Intent intent4 = new Intent(Menu_ShijiActivity.this,ClassifyActivity.class);
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
                for (int i = 0;i < things.size();i++){
                    if (TextUtils.equals(things.get(i).getTitle1(),et_hotword.getText().toString().trim())){
                        Intent intent6 = new Intent(Menu_ShijiActivity.this,ThingActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(thingKey,things.get(i));
                        intent6.putExtra("thingBundle",bundle);
                        startActivity(intent6);
                        return;
                    }

                    if (!TextUtils.equals(things.get(i).getTitle1(),et_hotword.getText().toString().trim())){
                        ToastUtil.showToast(Menu_ShijiActivity.this,"没有此商品，仅支持搜索商品列表中名称");
                    }
                }
                break;
        }
    }

    class MyAdapter extends BaseAdapter{

        private ArrayList<Thing> things;

        public MyAdapter(ArrayList<Thing> things) {
            this.things = things;
        }

        @Override
        public int getCount() {
            return things.size();
        }

        @Override
        public Thing getItem(int position) {
            return things.get(position);
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
                convertView = View.inflate(Menu_ShijiActivity.this,R.layout.item_shiji,null);
                myHolder.img = (ImageView) convertView.findViewById(R.id.image);
                myHolder.title1 = (TextView) convertView.findViewById(R.id.title1);
                myHolder.title2 = (TextView) convertView.findViewById(R.id.title2);

                convertView.setTag(myHolder);

            }else{
                myHolder = (MyHolder) convertView.getTag();
            }
            myHolder.title1.setText(things.get(position).getTitle1());
            myHolder.title2.setText(things.get(position).getTitle2());
            myHolder.img.setImageResource(things.get(position).getImgId());

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
        srollview.scrollTo(0,0);
    }
}
