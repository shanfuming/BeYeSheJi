package com.sfm.beyesheji.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shanfuming on 2016/5/13.
 */
public class DBOpenHelper extends SQLiteOpenHelper {
    /**
     * @param context 上下文
     * @param name    数据库名
     * @param factory 可选的数据库游标工厂类，当查询(query)被提交时，该对象会被调用来实例化一个游标。默认为null。
     * @param version 数据库版本号
     */
    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // 覆写onCreate方法，当数据库创建时就用SQL命令创建一个表
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建一个cartbill表，id主键，自动增长；
        db.execSQL("create table car(id integer primary key autoincrement,ThingTitle1 varchar(100),ThingTitle2 varchar(100),ThingMoney varchar(100),ThingImg int,IsBuy int)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
