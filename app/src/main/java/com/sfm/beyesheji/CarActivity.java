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
import android.widget.VideoView;

import com.sfm.beyesheji.bean.Thing;
import com.sfm.beyesheji.db.DataHandler;
import com.sfm.beyesheji.global.BYSJApplication;
import com.sfm.beyesheji.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by shanfuming on 2018/5/5.
 */

public class CarActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout ll_back;
    private TextView tv_titleName,tv_delete;
    private ListView car_list;
    private ArrayList<Thing> things = new ArrayList<>();
    private HashMap<Integer,Thing> deleteList = new HashMap<>();
    private TextView tv_tip;
    private Button bt_buy,bt_delete;
    private int allMoney = 0;
    private boolean isShowSelect = false;
    private CarAdapter carAdapter;
    private DataHandler dataHandler;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_car);

        ll_back = (LinearLayout) findViewById(R.id.ll_title_back);
        tv_titleName = (TextView) findViewById(R.id.tv_title_name);
        tv_delete = (TextView) findViewById(R.id.tv_title_function2);
        tv_tip = (TextView) findViewById(R.id.tv_tip);
        car_list = (ListView) findViewById(R.id.car_listview);
        bt_buy = (Button) findViewById(R.id.bt_buy);
        bt_delete = (Button) findViewById(R.id.bt_delete);
    }

    @Override
    protected void initData() {
        super.initData();

        tv_titleName.setText("购物车");
        tv_delete.setText("管理");
        ll_back.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
        bt_buy.setOnClickListener(this);
        bt_delete.setOnClickListener(this);

        dataHandler = new DataHandler(BYSJApplication.sContext);
        things.addAll(dataHandler.findCar());
        if (things.size() == 0){
            tv_tip.setVisibility(View.VISIBLE);
            car_list.setVisibility(View.GONE);
        }else{
            carAdapter = new CarAdapter(things);
            car_list.setAdapter(carAdapter);
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
                    bt_buy.setVisibility(View.VISIBLE);
                    bt_delete.setVisibility(View.GONE);
                }else{
                    isShowSelect = true;
                    tv_delete.setText("完成");
                    bt_buy.setVisibility(View.GONE);
                    bt_delete.setVisibility(View.VISIBLE);
                }
                carAdapter.notifyDataSetChanged();
                break;
            case R.id.bt_buy:
                if (things.size() == 0){
                    bt_buy.setClickable(false);
                    ToastUtil.showToast(BYSJApplication.sContext,"请先浏览商品");
                    return;
                }
                for (int i = 0; i < things.size(); i++){
                    allMoney = allMoney + Integer.parseInt(things.get(i).getMoney());
                }
                Intent intent =  new Intent(this,PayActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("carlist", things);
                intent.putExtra("carBundle",bundle);
                intent.putExtra("allMoney",allMoney+"");
                intent.putExtra("turnNUm",2);
                startActivity(intent);
                break;
            case R.id.bt_delete:
                for (int position : deleteList.keySet()){
                    dataHandler.delete(deleteList.get(position).getTitle1());
                }
                things.clear();
                things.addAll(dataHandler.findCar());
                carAdapter.notifyDataSetChanged();
                if (things.size() == 0){
                    tv_tip.setVisibility(View.VISIBLE);
                    car_list.setVisibility(View.GONE);
                }
                break;
        }
    }

    class CarAdapter extends BaseAdapter {

        private ArrayList<Thing> things;

        public CarAdapter(ArrayList<Thing> things) {
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            MyHolder myHolder = null;
            if (convertView == null){
                myHolder = new MyHolder();
                convertView = View.inflate(CarActivity.this,R.layout.item_car,null);
                myHolder.img = (ImageView) convertView.findViewById(R.id.image);
                myHolder.title1 = (TextView) convertView.findViewById(R.id.title1);
                myHolder.title2 = (TextView) convertView.findViewById(R.id.title2);
                myHolder.tip = (TextView) convertView.findViewById(R.id.tv_tip1);
                myHolder.select = (ImageView) convertView.findViewById(R.id.iv_select);
                myHolder.money = (TextView) convertView.findViewById(R.id.tv_money);
                convertView.setTag(myHolder);

            }else{
                myHolder = (MyHolder) convertView.getTag();
            }
            myHolder.tip.setVisibility(View.VISIBLE);
            myHolder.title1.setText(things.get(position).getTitle1());
            myHolder.title2.setText(things.get(position).getTitle2());
            myHolder.money.setText(things.get(position).getMoney());
            myHolder.img.setImageResource(things.get(position).getImgId());
            myHolder.select.setImageDrawable(getResources().getDrawable(R.mipmap.selectgray));
            final MyHolder finalMyHolder = myHolder;
            myHolder.select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (things.get(position).isChecked()){
                        finalMyHolder.select.setImageDrawable(getResources().getDrawable(R.mipmap.selectgray));
                        things.get(position).setChecked(false);
                        deleteList.remove(position);
                    }else{
                        finalMyHolder.select.setImageDrawable(getResources().getDrawable(R.mipmap.selectred));
                        things.get(position).setChecked(true);
                        deleteList.put(position,things.get(position));
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
        TextView money;
        ImageView img;
        TextView tip;
        ImageView select;
    }
}
