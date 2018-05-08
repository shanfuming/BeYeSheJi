package com.sfm.beyesheji.bean;

import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by shanfuming on 2018/5/5.
 */

public class Thing implements Serializable{
    String title1;
    String title2;
    int imgId;
    String money;
    int isBuy;
    boolean isChecked;

    public Thing() {
    }

    public Thing(String title1, String title2, int imgId, String money) {
        this.title1 = title1;
        this.title2 = title2;
        this.imgId = imgId;
        this.money = money;
    }

    public int getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(int isBuy) {
        this.isBuy = isBuy;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
