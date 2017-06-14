package com.lovejoy.model;

/**
 * Created by lenovo on 2017/6/13.
 */
public class ReputationItem {
    private String reputation_activity_id;
    private String reputation_star;
    private String reputation_user;

    public ReputationItem( String reputation_activity_id,String reputation_star,String reputation_user){
        this.reputation_activity_id=reputation_activity_id;
        this.reputation_star=reputation_star;
        this.reputation_user=reputation_user;
    }

    public String getReputation_activity_id(){
        return reputation_activity_id;
    }

    public String getReputation_star(){
        return reputation_star;
    }

    public String getReputation_user(){
        return reputation_user;
    }
}
