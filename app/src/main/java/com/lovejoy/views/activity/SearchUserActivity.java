package com.lovejoy.views.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.lovejoy.api.PostRequests;
import com.lovejoy.model.Friend;
import com.lovejoy.views.adapter.ApplyFriendAdapter;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchUserActivity extends AppCompatActivity {
    private String[] mStrs = {"aaa", "bbb", "ccc", "airsaid"};
    private SearchView mSearchView;
    List<Integer> friendlist = new ArrayList<Integer>();
    private List<Friend> friends;
    private ListView mListView;
   boolean flag;
    int userid=22;
    Context context=this;
    private ApplyFriendAdapter applyFriendAdapter;
    PostRequests pr=null;
   Handler searchhandler=null;
    ListView listView=null;
    Handler initialize=null;
    Handler invitehandler=null;
    Handler itemhandler=null;
    Handler finalhandler=null;
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        flag=true;
        friends=new ArrayList<Friend>();
        applyFriendAdapter=new ApplyFriendAdapter((ArrayList<Friend>)friends,context,invitehandler);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);
        JSONObject obj=new JSONObject();
        listView=(ListView)findViewById(R.id.ListView);
        listView.setAdapter(applyFriendAdapter);
        pr=new PostRequests();
        mSearchView = (SearchView) findViewById(R.id.searchView);

        initialize=new Handler(){
            public void handleMessage(android.os.Message msg) {
                if (msg!=null) {
                    if (msg.obj!=null){
                        Object result = msg.obj;
                        JSONObject robj = JSONObject.fromObject(result);
                        JSONArray jr=robj.getJSONArray("friendlist");
                        for(int i=0;i<jr.size();i++){
                            friendlist.add(jr.getInt(i));
                        }}
                    else{
                        Toast.makeText(context, "failed to get data", Toast.LENGTH_LONG).show();
                    }
                }
            };
        };
        pr.sendPost("/get_friend_list","/"+Integer.toString(userid),obj,initialize,this);




        invitehandler=new Handler(){
            public void handleMessage(android.os.Message msg) {
                if (msg!=null) {
                    Toast.makeText(context, "已经发送好友请求", Toast.LENGTH_LONG).show();
                }
            }
        };

        itemhandler=new Handler(){
            public void handleMessage(android.os.Message msg) {
                if (msg!=null) {
                    Object result = msg.obj;
                    JSONObject obj=new JSONObject();
                    JSONObject robj = JSONObject.fromObject(result);
                   Friend f=new Friend(robj.getString("name"),robj.getString("mail"),1,robj.getInt("id"));
                   friends.add(f);
                    pr.getpic("/get_pic","/"+robj.getString("ava"),finalhandler,context);



                }
            }
        };
    finalhandler=new Handler(){
       public void handleMessage(android.os.Message msg) {
           if (msg!=null) {
               Bitmap bmp = (Bitmap) msg.obj;
              Friend cf=friends.get(count);
               cf.bmp=bmp;
               friends.set(count,cf);
               listView.setAdapter(new ApplyFriendAdapter((ArrayList<Friend>)friends,context,invitehandler));
               count++;
               flag=true;

           }
           else{
               Bitmap  bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.buttonbg);
               Friend cf=friends.get(count);
               cf.bmp=bitmap;
               friends.set(count,cf);
               listView.setAdapter(new ApplyFriendAdapter((ArrayList<Friend>)friends,context,invitehandler));
               count++;
               flag=true;
           }
       }
   };




        searchhandler=new Handler(){
            public void handleMessage(android.os.Message msg) {
                if (msg!=null) {
                    Object result = msg.obj;
                    JSONObject robj = JSONObject.fromObject(result);
                  JSONArray jr=robj.getJSONArray("result");
                    JSONObject obj=new JSONObject();
                         for(int i=0;i<jr.size();i++){
                             if(!friendlist.contains(jr.getInt(i))&&userid!=jr.getInt(i)){
//
                                 pr.sendPost("/get_user_info","/"+Integer.toString(jr.getInt(i)),obj,itemhandler,context);

                              }}
//                    class mthread extends Thread {
//
//                        @Override
//                        public void run() {
//
//                        }
//
//                        public void run(JSONArray jr) {
//                            JSONObject obj=new JSONObject();
//                            for(int i=0;i<jr.size();i++){
//                                if(!friendlist.contains(jr.getInt(i))){
//                                    while (!flag){
//                                    }
//                                    pr.sendPost("/get_user_info","/"+Integer.toString(jr.getInt(i)),obj,itemhandler,context);
//                                    flag=false;
//                                }
//                            }
//                        }
//                    }
//                 mthread m=new mthread();
//                    m.run(jr);

                  }
                    else{
                        Toast.makeText(context, "failed to get data", Toast.LENGTH_LONG).show();
                    }
                }
            };



        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!TextUtils.isEmpty(query)){
                    friends.clear();
                    count=0;
                    JSONObject obj=new JSONObject();
                    pr.sendPost("/search_user","/"+query,obj,searchhandler,context);
                }
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
              return false;
            }
        });


    }
}
