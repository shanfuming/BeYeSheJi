package com.sfm.beyesheji.bean;

import java.io.Serializable;

/**
 * Created by shanfuming on 2018/5/5.
 */

public class ShiPin implements Serializable{
    String title1;
    String title2;
    int imgId;
    String shipinId;
    int isLike;
    boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public ShiPin() {
    }

    public ShiPin(String title1, String title2, int imgId, String shipinId) {
        this.title1 = title1;
        this.title2 = title2;
        this.imgId = imgId;
        this.shipinId = shipinId;
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

    public String getShipinId() {
        return shipinId;
    }

    public void setShipinId(String shipinId) {
        this.shipinId = shipinId;
    }

    public int getIsLike() {
        return isLike;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }
}
