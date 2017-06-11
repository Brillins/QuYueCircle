package com.lovejoy.api.ObjectGenerators;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserJsons {
    public static JSONObject Register(String name,String password,String mail,String phone,String stu_id,String college, String profession,String sex,String birth,int avatar){
        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("password",password);
        map.put("mail",mail);
        map.put("phone",phone);
        map.put("stu_id",stu_id);
        map.put("college",college);
        map.put("profession",profession);
        map.put("sex",sex);
        map.put("birthdate",birth);
        map.put("avatar",Integer.toString(avatar));
        map.put("is_activated","n");
        JSONObject obj = JSONObject.fromObject(map);
        return obj;
    }

    public static JSONObject Login(String name,String password){
        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("password",password);
        JSONObject obj = JSONObject.fromObject(map);
        return obj;
    }
// [ name, mail, phone, stu_id, college, profession, sex, birthdate, credict, act_v, ava, is_act ]
//    public static JSONObject ChangeUserInfo(String name, String mail,String phone,String stu_id,String college,String profession,String sex,String birthdate ,String credict,int ava,String is_act){
//
//        Map<String,String> map = new HashMap<String,String>();
//        map.put("name",name);
//        map.put("password",password);
//        map.put("mail",mail);
//        map.put("phone",phone);
//        map.put("stu_id",stu_id);
//        map.put("college",college);
//        map.put("profession",profession);
//        map.put("sex",sex);
//        map.put("birthdate",birth);
//        map.put("avatar",Integer.toString(avatar));
//        map.put("is_activated","n");
//        JSONObject obj = JSONObject.fromObject(map);
//        return obj;
//    }

    public static JSONObject ChangeAvatar(int ava,int userid){
        Map<String,String> map = new HashMap<>();
        map.put("user_id",Integer.toString(userid));
        map.put("pic_id",Integer.toString(ava));
        JSONObject obj = JSONObject.fromObject(map);
        return obj;
    }

    public static JSONObject FocusGroup(int groupid) {
        Map<String,String> map = new HashMap<>();
        map.put("group_id",Integer.toString(groupid));
        JSONObject obj = JSONObject.fromObject(map);
        return obj;
    }

    public static JSONObject CancelFocus(int groupid){
        Map<String,String> map = new HashMap<>();
        map.put("group_id",Integer.toString(groupid));
        JSONObject obj = JSONObject.fromObject(map);
        return obj;
    }
}
