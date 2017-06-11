package com.lovejoy.api.ObjectGenerators;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ActivityJsons {
    public static JSONObject AdmitaAct(String name, int uid,int groupid,int Max, int Min,String start,String end,String description,String tags,String picid){
        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("publisher",Integer.toString(uid));
        map.put("group_id",Integer.toString(groupid));
        map.put("description",description);
        map.put("start_date",start);
        map.put("end_date",end);
        map.put("min_num",Integer.toString(Min));
        map.put("max_num",Integer.toString(Max));
        map.put("is_canceled",Integer.toString(1));
        map.put("cur_num",Integer.toString(0));
        map.put("tags",tags);
        map.put("join_ids","");
        map.put("pic_id",picid);
        JSONObject obj = JSONObject.fromObject(map);
        return obj;
    }

    public static JSONObject JoinAct(int userid,int actid){
        Map<String,String> map = new HashMap<>();
        map.put("activity_id",Integer.toString(actid));
        map.put("user_id",Integer.toString(userid));
        JSONObject obj = JSONObject.fromObject(map);
        return obj;
    }

    public static JSONObject QuitAct(int userid,int actid){
        Map<String,String> map = new HashMap<>();
        map.put("activity_id",Integer.toString(actid));
        map.put("user_id",Integer.toString(userid));
        JSONObject obj = JSONObject.fromObject(map);
        return obj;
    }

    public static JSONObject SearchAct(int groupid,String labels,String key){
        Map<String,String> map = new HashMap<>();
        map.put("groupid",Integer.toString(groupid));
        map.put("lables",labels);
        map.put("key",key);
        JSONObject obj = JSONObject.fromObject(map);
       return obj;
    }

}


