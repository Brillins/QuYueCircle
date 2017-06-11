package com.lovejoy.api.ObjectGenerators;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CommentJsons {
    public static JSONObject CommentA(int aid,int uid,int level, String content){
        Map<String,String> map = new HashMap<>();
       map.put("act_id",Integer.toString(aid));
        map.put("user_id",Integer.toString(uid));
       map.put("level",Integer.toString(level));
        map.put("content",content);
        JSONObject obj = JSONObject.fromObject(map);
        return obj;
    }
//    act_id=args['act_id'],comm_user_id=args['comm_user_id'], commed_user_id=args['commed_user_id'],
//    level=args['level'], content=args['content']
    public static JSONObject CommentP(int act_id,int cuser,int tuser,int level,String content){
        Map<String,String> map = new HashMap<>();
        map.put("act_id",Integer.toString(act_id));
        map.put("comm_user_id",Integer.toString(cuser));
        map.put("commed_user_id",Integer.toString(tuser));
        map.put("level",Integer.toString(level));
        map.put("content",content);
        JSONObject obj = JSONObject.fromObject(map);
        return obj;
    }
}
