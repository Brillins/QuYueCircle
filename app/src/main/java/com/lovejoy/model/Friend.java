package com.lovejoy.model;

import android.graphics.Bitmap;

import org.apache.commons.collections.BidiMap;

/**
 * Created by rin on 2017/5/23.
 */

public class Friend {
    private int icon;
    private String text1;
    private String text2;
    public int id;

    public Bitmap bmp;
    public Friend(String text1,String text2,int icon,int id){
        this.icon=icon;
        this.text1=text1;
        this.text2=text2;
        this.id=id;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setText1(String text) {
        text1 = text;
    }

    public void setText2(String text) {
        text2 = text;
    }

    public int getIcon() {
        return icon;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }
}
