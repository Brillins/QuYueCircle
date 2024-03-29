package com.lovejoy.model;


public class ActivityBriefInfor {
    private int activityId;//活动ID
    private String activityName;//活动名称
    private String createTime;//活动发布时间
    private String creatorName;//发布者昵称
    private int creatorImageId;//发布者头像Id
    private String abstractInfor;//信息摘要
    private int planMinNumber;//规定最少人数
    private int planMaxNumber;//规定最多人数
    private int currentNumber;//当前人数
    private String deadline;//活动报名截止时间
    private String startTime;//活动开始时间

    //活动ID函数
    public int getActivityId(){
        return activityId;
    }
    public void setActivityId(int id){
        this.activityId = id;
    }

    //活动名称函数
    public String getActivityName(){
        return activityName;
    }
    public void setActivityName(String an){
        this.activityName = an;
    }

    //活动发布时间函数
    public String getCreateTime(){
        return createTime;
    }
    public void setCreateTime(String ct){
        this.createTime = ct;
    }


    //发布者昵称
    public String getCreatorName(){
        return creatorName;
    }
    public void setCreatorName(String cn){
        this.creatorName = cn;
    }

    //发布者头像ID函数
    public int getCreatorImageId(){
        return creatorImageId;
    }
    public void setCreatorImageId(int cIi){
        this.creatorImageId = cIi;
    }

    //信息摘要函数
    public String getAbstractInfor(){
        return abstractInfor;
    }
    public void setAbstractInfor(String ai){
        this.abstractInfor = ai;
    }

    //规定最少人数
    public int getPlanMinNumber(){
        return planMinNumber;
    }
    public void setPlanMinNumber(int pMinn){
        this.planMinNumber = pMinn;
    }

    //规定最多人数
    public int getPlanMaxNumber(){
        return planMaxNumber;
    }
    public void setPlanMaxNumber(int pMaxn){
        this.planMaxNumber = pMaxn;
    }

    //当前人数
    public int getCurrentNumber(){
        return currentNumber;
    }
    public void setCurrentNumber(int cn){
        this.currentNumber = cn;
    }

    //活动报名截止时间
    public String getDeadline(){
        return deadline;
    }
    public void setDeadline(String dl){
        this.deadline = dl;
    }

    //活动开始时间
    public String getStartTime(){
        return startTime;
    }
    public void setStartTime(String st){
        this.startTime = st;
    }
}
