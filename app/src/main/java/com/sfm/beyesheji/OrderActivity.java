package com.sfm.beyesheji;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sfm.beyesheji.bean.Thing;
import com.sfm.beyesheji.db.DataHandler;
import com.sfm.beyesheji.global.BYSJApplication;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 订单中心
 */

public class OrderActivity extends BaseActivity {
    private LinearLayout ll_back;
    private TextView tv_titleName,tv_delete;
    private ListView orderList;

    private ArrayList<Thing> things = new ArrayList<>();
    private TextView tv_tip;
    private boolean isShowSelect = false;
    private HashMap<Integer,Thing> deleteList = new HashMap<>();
    private Button bt_delete;
    private OrderAdapter orderAdapter;
    private DataHandler dataHandler;

    @Override
    protected void initView() {
        setContentView(R.layout.actvity_order);

        ll_back = (LinearLayout) findViewById(R.id.ll_title_back);
        tv_titleName = (TextView) findViewById(R.id.tv_title_name);
        orderList = (ListView) findViewById(R.id.order_listview);
        tv_tip = (TextView) findViewById(R.id.tv_tip);
        tv_delete = (TextView) findViewById(R.id.tv_title_function2);
        bt_delete = (Button) findViewById(R.id.bt_delete);
    }

    @Override
    protected void initData() {
        super.initData();

        tv_titleName.setText("订单中心");
        tv_delete.setText("管理");
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //订单列表数据获取
        dataHandler = new DataHandler(BYSJApplication.sContext);
        things.addAll(dataHandler.findOrder());

        if (things.size() == 0){
            tv_tip.setVisibility(View.VISIBLE);
            orderList.setVisibility(View.GONE);
        }else
        //订单列表数据展示
        orderAdapter = new OrderAdapter(things);
        orderList.setAdapter(orderAdapter);
        //管理按钮点击逻辑
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowSelect){
                    isShowSelect = false;
                    tv_delete.setText("管理");
                    bt_delete.setVisibility(View.GONE);
                }else{
                    isShowSelect = true;
                    tv_delete.setText("完成");
                    bt_delete.setVisibility(View.VISIBLE);
                }
                orderAdapter.notifyDataSetChanged();
            }
        });
        //删除按钮点击逻辑
        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int position : deleteList.keySet()){
                    dataHandler.deleteOrder(deleteList.get(position).getTitle1());
                }
                things.clear();
                things.addAll(dataHandler.findOrder());
                orderAdapter.notifyDataSetChanged();
                if (things.size() == 0){
                    tv_tip.setVisibility(View.VISIBLE);
                    orderList.setVisibility(View.GONE);
                }
            }
        });
    }

    class OrderAdapter extends BaseAdapter{

        private ArrayList<Thing> things;

        public OrderAdapter(ArrayList<Thing> things) {
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
                convertView = View.inflate(OrderActivity.this,R.layout.item_car,null);
                myHolder.img = (ImageView) convertView.findViewById(R.id.image);
                myHolder.title1 = (TextView) convertView.findViewById(R.id.title1);
                myHolder.title2 = (TextView) convertView.findViewById(R.id.title2);
                myHolder.tip = (TextView) convertView.findViewById(R.id.tv_tip1);
                myHolder.select = (ImageView) convertView.findViewById(R.id.iv_select);

                convertView.setTag(myHolder);

            }else{
                myHolder = (MyHolder) convertView.getTag();
            }
            myHolder.tip.setText("已付款");
            myHolder.tip.setVisibility(View.VISIBLE);
            myHolder.title1.setText(things.get(position).getTitle1());
            myHolder.title2.setText(things.get(position).getTitle2());
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
        ImageView img;
        ImageView select;
        TextView tip;
    }
}
