package com.sfm.beyesheji.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.sfm.beyesheji.bean.Thing;
import java.util.ArrayList;

/**
 * Created by shanfuming on 2016/5/13.
 */
public class DataHandler {
    private DBOpenHelper dbOpenHandler;

    public DataHandler(Context context) {
        this.dbOpenHandler = new DBOpenHelper(context, "dbcartlist.db", null, 1);
    }

    // 插入记录
    public void save(Thing thing) {
        // 取得数据库操作
        SQLiteDatabase db = dbOpenHandler.getWritableDatabase();
        db.execSQL("insert into car (ThingTitle1,ThingTitle2,ThingMoney,ThingImg,IsBuy) values(?,?,?,?,?)",
                new Object[]{thing.getTitle1(), thing.getTitle2(), thing.getMoney(), thing.getImgId(),thing.getIsBuy()});

        db.close();// 记得关闭数据库操作
    }
    // 删除指定的购物车商品
    public void deleteCar(String thingTitle1) {
        SQLiteDatabase db = dbOpenHandler.getWritableDatabase();
        db.execSQL("delete from car where ThingTitle1=? and IsBuy = 0;", new Object[]{thingTitle1});
        db.close();
    }

    // 删除指定的购物车商品
    public void deleteAllCar() {
        SQLiteDatabase db = dbOpenHandler.getWritableDatabase();
        db.execSQL("delete from car where IsBuy = 0;");
        db.close();
    }

    // 删除订单中的商品
    public void deleteOrder(String thingTitle1) {
        SQLiteDatabase db = dbOpenHandler.getWritableDatabase();
        db.execSQL("delete from car where ThingTitle1=? and IsBuy = 1;", new Object[]{thingTitle1});
        db.close();
    }

    // 删除全部的商品
    public void deleteAll() {
        SQLiteDatabase db = dbOpenHandler.getWritableDatabase();
        db.execSQL("delete from car;");
        db.close();
    }

    // 查询所有加入购物车商品列表
    public ArrayList<Thing> findCar() {
        ArrayList<Thing> lists = new ArrayList<Thing>();
        Thing thing;
        SQLiteDatabase db = dbOpenHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from car where IsBuy = 0;", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                thing = new Thing();
                thing.setTitle1(cursor.getString(cursor.getColumnIndex("ThingTitle1")));
                thing.setTitle2(cursor.getString(cursor.getColumnIndex("ThingTitle2")));
                thing.setMoney(cursor.getString(cursor.getColumnIndex("ThingMoney")));
                thing.setImgId(cursor.getInt(cursor.getColumnIndex("ThingImg")));
                lists.add(thing);
            }
        }
        cursor.close();
        db.close();
        return lists;
    }

    // 查询所有已付款商品列表
    public ArrayList<Thing> findOrder() {
        ArrayList<Thing> lists = new ArrayList<Thing>();
        Thing thing;
        SQLiteDatabase db = dbOpenHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from car where IsBuy = 1;", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                thing = new Thing();
                thing.setTitle1(cursor.getString(cursor.getColumnIndex("ThingTitle1")));
                thing.setTitle2(cursor.getString(cursor.getColumnIndex("ThingTitle2")));
                thing.setMoney(cursor.getString(cursor.getColumnIndex("ThingMoney")));
                thing.setImgId(cursor.getInt(cursor.getColumnIndex("ThingImg")));
                lists.add(thing);
            }
        }
        cursor.close();
        db.close();
        return lists;
    }

    public long getCount() {//统计所有记录数
        SQLiteDatabase db = dbOpenHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*) from car ", null);
        cursor.moveToFirst();
        db.close();
        return cursor.getLong(0);
    }
}
