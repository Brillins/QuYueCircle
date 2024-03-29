package com.lovejoy.views.fragment;

import com.lovejoy.api.PostRequests;
import com.lovejoy.views.activity.R;
import com.lovejoy.views.adapter.ListViewAdapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicTopRecomFragment extends Fragment {

	private ListView tRecomListView;

    Context context=null;
    Handler handler=null;
    Handler handler2=null;
    PostRequests pRequest=null;
    int size=0;
    int count=0;
    int userid=22;
    List<Map<String, Object>> mlist;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

        mlist=new ArrayList<>();
		View view = inflater.inflate(R.layout.fragment_dynamic_top_recom, container, false);
        context=this.getActivity();
        tRecomListView = (ListView)view.findViewById(R.id.tRecom_list_view);
//        List<Map<String, Object>> list = getData();
//        tRecomListView.setAdapter(new ListViewAdapter(getActivity(), list));


        JSONObject js=new JSONObject();
        pRequest=new PostRequests();
        handler2 = new android.os.Handler() {
            @Override
            public void handleMessage(Message msg) {
                if(msg.obj!=null) {
                    Log.e("zhenshabi","+1s");
                    Object result = msg.obj;
                    JSONObject robj = JSONObject.fromObject(result);
                    Map<String, Object> map = new HashMap<>();
                    map.put("activityName", robj.getString("name"));
                    map.put("createTime", robj.getString("create_date"));
                    map.put("creatorImage", R.drawable.icon_profile_01);
                    map.put("creatorName", Integer.toString(robj.getInt("publisher")));
                    map.put("abstractInfor", robj.getString("description") );
                    map.put("planMinNumber", robj.getInt("min_num"));
                    map.put("planMaxNumber",  robj.getInt("max_num"));
                    map.put("currentNumber", robj.getInt("cur_num") );
                    map.put("activityDeadline",  robj.getString("end_date"));
                    map.put("startTime",  robj.getString("start_date"));
                    mlist.add(map);
                    tRecomListView.setAdapter(new ListViewAdapter(context, mlist));
                    count++;
                    if(count==size){

                        tRecomListView.setAdapter(new ListViewAdapter(getActivity(), mlist));
                    }
                }

                else{
                    Log.e("zhenshabi","youcuole");
                }

            };};


        handler = new android.os.Handler() {
            @Override
            public void handleMessage(Message msg) {
                if(msg.obj!=null) {
                    Object result = msg.obj;
                    JSONObject robj = JSONObject.fromObject(result);
                    JSONArray jr = robj.getJSONArray("result");
                    List<Integer> idlist = new ArrayList<>();
                    //List  idlist= JSONArray.toList(jr,new Integer(),new JsonConfig());

                    int i=0;
                    JSONObject js=new JSONObject();
                    size=jr.size();
                    for(i=0;i<jr.size();i++){
                        idlist.add(jr.getInt(i));
                        pRequest.sendPost("/get_activity_details","/"+Integer.toString(jr.getInt(i)),js,handler2,context);

                    }

                }
                else{
                    Log.e("hahaha","youcuole");
                }

            };};

        pRequest.sendPost("/get_user_recommendation_list","/" + Integer.toString(userid),js,handler,context);

		return view;
	}


//    public List<Map<String, Object>> getData(){
//        List<Map<String, Object>> list = new ArrayList<>();
//        list.clear();
//        for (int i = 0; i < 10; i++) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("activityName", "推荐图书漂流"+i);
//            map.put("createTime", "2017-5-8 9:00");
//            map.put("creatorImage", R.drawable.icon_profile_01);
//            map.put("creatorName", "name"+i);
//            map.put("abstractInfor", "这是一个详细信息，信息，指音讯、消息、通讯系统传输和处理的对象，泛指人类社会传播的一切内容。人通过获得、识别自然界和社会的不同信息来区别不同事物，得以认识和改造世界。在一切通讯和控制系统中，信息是一种普遍联系的形式。1948年，数学家香农在题为“通讯的数学理论”的论文中指出：“信息是用来消除随机不定性的东西”。创建一切宇宙万物的最基本万能单位是信息。\n" +
//                    "中文名信息词目信息" + i);
//            map.put("planMinNumber", 7);
//            map.put("planMaxNumber", 10);
//            map.put("currentNumber", 6);
//            map.put("activityDeadline", "2017-5-10 12:00");
//            map.put("startTime", "2017-5-11 14:00");
//
//            list.add(map);
//        }
//        return list;
//    }
	

}
