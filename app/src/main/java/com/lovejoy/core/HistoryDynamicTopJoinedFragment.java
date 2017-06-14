package com.lovejoy.core;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lovejoy.api.PostRequests;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lovejoy.views.activity.R;
import com.lovejoy.views.adapter.ListViewAdapter;
import com.lovejoy.views.adapter.SCZListViewAdapter;


public class HistoryDynamicTopJoinedFragment extends Fragment {

    private ListView tJoinedListView;
    Context context=null;
    Handler handler=null;
    Handler handler2=null;
    Handler pichandler=null;
    PostRequests prequest=null;
    int size=0;
    int count=0;
    int userid=22;
    int piccount=0;
    List<Map<String, Object>> mlist;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        mlist=new ArrayList<>();
		View view = inflater.inflate(R.layout.list_view_history_activity, container, false);
        context=this.getActivity();
        tJoinedListView = (ListView)view.findViewById(R.id.list_view_history);
        //List<Map<String, Object>> list = getData();
       // tJoinedListView.setAdapter(new ListViewAdapter(getActivity(), list));
        JSONObject js=new JSONObject();
        prequest=new PostRequests();
        handler2 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if(msg.obj!=null) {
                    Log.e("zhenshabi","+2s");
                    Object result = msg.obj;
                    JSONObject robj = JSONObject.fromObject(result);
                    Map<String, Object> map = new HashMap<>();
                    map.put("activityName", robj.getString("name"));
                    map.put("createTime", robj.getString("create_date"));
                    map.put("creatorImage", R.drawable.icon_profile);
                    map.put("creatorName", Integer.toString(robj.getInt("publisher")));
                    map.put("abstractInfor", robj.getString("description") );
                    map.put("planMinNumber", robj.getInt("min_num"));
                    map.put("planMaxNumber",  robj.getInt("max_num"));
                    map.put("currentNumber", robj.getInt("cur_num") );
                    map.put("activityDeadline",  robj.getString("end_date"));
                    map.put("startTime",  robj.getString("start_date"));
                    JSONArray jr=robj.getJSONArray("pic");
                    mlist.add(map);
                    prequest.getpic("/get_pic","/"+ jr.getString(0),pichandler,context);

                    //tJoinedListView.setAdapter(new ListViewAdapter(getActivity(), mlist));
                    count++;

                }

                else{
                    Log.e("zhenshabi","youcuole");
                }

            };};

        pichandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if(msg.obj!=null) {
                    Bitmap bmp=(Bitmap)msg.obj;
                    mlist.get(piccount).put("pic",bmp);
                    tJoinedListView.setAdapter(new SCZListViewAdapter(getActivity(), mlist));
                    piccount++;
            }}};
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if(msg.obj!=null) {
                    Object result = msg.obj;
                    JSONObject robj = JSONObject.fromObject(result);
                    JSONArray jr=robj.getJSONArray("activity");
                    List<Integer> idlist=new ArrayList<>();
                    //List  idlist= JSONArray.toList(jr,new Integer(),new JsonConfig());

                    int i=0;
                    JSONObject js=new JSONObject();
                    size=jr.size();
                    for(i=0;i<jr.size();i++){
                        idlist.add(jr.getInt(i));
                        prequest.sendPost("/get_activity_details","/"+Integer.toString(jr.getInt(i)),js,handler2,context);

                    }

                }
                else{
                    Log.e("zhenshabi","youcuole");
                }
            };};

        prequest.sendPost("/get_past_activity_list","/"+Integer.toString(userid),js,handler,context);
		return view;
	}

}
