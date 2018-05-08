package com.sfm.beyesheji.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sfm.beyesheji.bean.ShiPin;
import com.sfm.beyesheji.bean.Thing;

import java.util.ArrayList;

/**
 * Created by shanfuming on 2016/5/13.
 */
public class Like_DataHandler {
    private Like_DBOpenHelper dbOpenHandler;

    public Like_DataHandler(Context context) {
        this.dbOpenHandler = new Like_DBOpenHelper(context, "dblikelist.db", null, 1);
    }

    // 插入记录
    public void save(ShiPin shiPin) {
        // 取得数据库操作
        SQLiteDatabase db = dbOpenHandler.getWritableDatabase();
        db.execSQL("insert into like (ThingTitle1,ThingTitle2,ShipinId,ThingImg) values(?,?,?,?)",
                new Object[]{shiPin.getTitle1(), shiPin.getTitle2(), shiPin.getShipinId(), shiPin.getImgId()});

        db.close();// 记得关闭数据库操作
    }
    // 删除指定的商品
    public void delete(String thingTitle1) {
        SQLiteDatabase db = dbOpenHandler.getWritableDatabase();
        db.execSQL("delete from like where ThingTitle1=?", new Object[]{thingTitle1});
        db.close();
    }
    // 删除全部的商品
    public void deleteAll() {
        SQLiteDatabase db = dbOpenHandler.getWritableDatabase();
        db.execSQL("delete from like;");
        db.close();
    }

    // 查询所有已付款商品列表
    public ArrayList<ShiPin> findLike() {
        ArrayList<ShiPin> lists = new ArrayList<ShiPin>();
        ShiPin shiPin;
        SQLiteDatabase db = dbOpenHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from like ", null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                shiPin = new ShiPin();
                shiPin.setTitle1(cursor.getString(cursor.getColumnIndex("ThingTitle1")));
                shiPin.setTitle2(cursor.getString(cursor.getColumnIndex("ThingTitle2")));
                shiPin.setShipinId(cursor.getString(cursor.getColumnIndex("ShipinId")));
                shiPin.setImgId(cursor.getInt(cursor.getColumnIndex("ThingImg")));
                lists.add(shiPin);
            }
        }
        cursor.close();
        db.close();
        return lists;
    }

    public long getCount() {//统计所有记录数
        SQLiteDatabase db = dbOpenHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*) from like ", null);
        cursor.moveToFirst();
        db.close();
        return cursor.getLong(0);
    }
}
