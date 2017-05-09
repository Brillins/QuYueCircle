package com.lovejoy.thread;


import android.os.AsyncTask;
import android.widget.EditText;

import com.lovejoy.activity.R;
import com.lovejoy.connection.PostRequests;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyAsyncTask extends AsyncTask {

    private EditText nameEditText = null;
    private EditText pwEditText = null;
    private String loginUserName = null;
    private String loginPassword = null;
    PostRequests pRequest = null;

    //onPreExecute方法用于在执行后台任务前做一些UI操作
    protected void onPreExecute() {
        super.onPreExecute();
    }

    //doInBackground方法内部执行后台任务,不可在此方法内修改UI
    @Override
    protected Object doInBackground(Object[] params) {

        Map<String,String> map = new HashMap<String,String>();
        map.put("name",loginUserName);
        map.put("password",loginPassword);
        JSONObject obj = JSONObject.fromObject(map);
        JSONObject obj2 = pRequest.sendPost("login","",obj);
        return null;
    }

    //onProgressUpdate方法用于更新进度信息
    protected void onProgressUpdate(){

    }

    //onPostExecute方法用于在执行完后台任务后更新UI,显示结果
    protected void onPostExecute() {

    }
}
