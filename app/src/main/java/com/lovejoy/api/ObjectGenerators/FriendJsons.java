package com.lovejoy.api.ObjectGenerators;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FriendJsons {
    public static JSONObject SendInvitation(int uid,int tuid,String content){
        Map<String,String> map = new HashMap<String,String>();
        map.put("userid",Integer.toString(uid));
        map.put("friend",Integer.toString(tuid));
        map.put("text",content);
        JSONObject obj = JSONObject.fromObject(map);
        return obj;
    }

    public static JSONObject ReplyInvitation(int uid,int tuid){
        Map<String,String> map = new HashMap<String,String>();
        map.put("invitor",Integer.toString(uid));
        map.put("receiver",Integer.toString(tuid));
        JSONObject obj = JSONObject.fromObject(map);
        return obj;
    }

    public static JSONObject SendMessage(){
        Map<String,String> map = new HashMap<String,String>();
        JSONObject obj = JSONObject.fromObject(map);
        return obj;
    }

}
