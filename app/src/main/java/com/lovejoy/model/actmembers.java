package com.lovejoy.model;

/**
 * Created by pc on 2017/6/7.
 */
public class actmembers {
    private int avatarid;
    private String name;
    private int stars;

    public actmembers(String name,int stars, int avatarid){
        this.name=name;
        this.stars=stars;
        this.avatarid=avatarid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getAvatarid() {
        return avatarid;
    }

    public void setAvatarid(int avatarid) {
        this.avatarid = avatarid;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }




}
