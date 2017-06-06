package com.lovejoy.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.lovejoy.adapter.ListViewAdapter;
import com.lovejoy.connection.PostRequests;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CircleListActivity extends Activity {

    private TextView circleTitleView = null;
    private ListView tCircleListView = null;
    private String circleTitle = null;
    Context context=null;
    List<Map<String, Object>> mlist;
    PostRequests prequest=null;
    Handler handler=null;
    Handler handler2=null;
    int size=0;
    int count=0;
    int userId=22;
    int groudid=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_list);

        circleTitleView = (TextView) findViewById(R.id.circle_title_text);
        Intent intent = getIntent();
        circleTitle = intent.getStringExtra("circleTitle").toString();
        circleTitleView.setText(circleTitle);

        context = this;
        mlist=new ArrayList<>();
        tCircleListView = (ListView)findViewById(R.id.circle_list_view);
        JSONObject js=new JSONObject();
        prequest=new PostRequests();

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
                    count++;
                    if(count==size){

                        tCircleListView.setAdapter(new ListViewAdapter(context, mlist));
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
                    JSONArray jr=robj.getJSONArray("activity_list");
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

        prequest.sendPost("/get_activity_list","/"+Integer.toString(groudid),js,handler,context);

    }


    public void onClick4(View v) {
        this.finish();
        this.overridePendingTransition(R.anim.activity_move_in, R.anim.activity_move_out);
    }
}
