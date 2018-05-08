package com.sfm.beyesheji;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sfm.beyesheji.bean.Thing;
import com.sfm.beyesheji.db.DataHandler;
import com.sfm.beyesheji.global.BYSJApplication;

import java.util.ArrayList;

/**
 * Created by shanfuming on 2018/5/6.
 */

public class OrderActivity extends BaseActivity {
    private LinearLayout ll_back;
    private TextView tv_titleName;
    private ListView orderList;

    private ArrayList<Thing> things = new ArrayList<>();
    private TextView tv_tip;

    @Override
    protected void initView() {
        setContentView(R.layout.actvity_order);

        ll_back = (LinearLayout) findViewById(R.id.ll_title_back);
        tv_titleName = (TextView) findViewById(R.id.tv_title_name);
        orderList = (ListView) findViewById(R.id.order_listview);
        tv_tip = (TextView) findViewById(R.id.tv_tip);
    }

    @Override
    protected void initData() {
        super.initData();

        tv_titleName.setText("订单中心");
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        DataHandler dataHandler = new DataHandler(BYSJApplication.sContext);
        things.addAll(dataHandler.findOrder());

        if (things.size() == 0){
            tv_tip.setVisibility(View.VISIBLE);
            orderList.setVisibility(View.GONE);
        }else

        orderList.setAdapter(new OrderAdapter(things));
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
        public View getView(int position, View convertView, ViewGroup parent) {
            MyHolder myHolder = null;
            if (convertView == null){
                myHolder = new MyHolder();
                convertView = View.inflate(OrderActivity.this,R.layout.item_shiji,null);
                myHolder.img = (ImageView) convertView.findViewById(R.id.image);
                myHolder.title1 = (TextView) convertView.findViewById(R.id.title1);
                myHolder.title2 = (TextView) convertView.findViewById(R.id.title2);
                myHolder.tip = (TextView) convertView.findViewById(R.id.tv_tip1);

                convertView.setTag(myHolder);

            }else{
                myHolder = (MyHolder) convertView.getTag();
            }
            myHolder.tip.setVisibility(View.VISIBLE);
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
        TextView tip;
    }
}
