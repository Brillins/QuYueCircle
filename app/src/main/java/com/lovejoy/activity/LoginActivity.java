package com.lovejoy.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lovejoy.connection.PostRequests;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity {

    private EditText nameEditText = null;
    private EditText pwEditText = null;
    private String loginUserName = null;
    private String loginPassword = null;
    PostRequests pRequest = null;
    Context context=this;
    JSONObject obj2=null;
    Handler handler2=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pRequest=new PostRequests();
        nameEditText = (EditText) findViewById(R.id.user_name_login);
        pwEditText = (EditText) findViewById(R.id.password_login);

        handler2=new Handler(){
            public void handleMessage(android.os.Message msg) {
                if (msg!=null) {
                    Object result = msg.obj;
                    JSONObject robj=JSONObject.fromObject(result);

                    Toast.makeText(LoginActivity.this,robj.get("status").toString(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            };
        };
//        loginUserName = nameEditText.getText().toString();
//        loginPassword = pwEditText.getText().toString();

        Button loginButton = (Button)findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Thread loginThread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        loginUserName = nameEditText.getText().toString();
                        loginPassword = pwEditText.getText().toString();

                        Map<String,String> map = new HashMap<String,String>();
                        map.put("name",loginUserName);
                        map.put("password",loginPassword);
                        JSONObject obj = JSONObject.fromObject(map);
                        pRequest.sendPost("/login","",obj,handler2,context);

                    }
                });
                loginThread.start();
            }
        });

    }

    public void onClick2(View v) {
        this.finish();
        this.overridePendingTransition(R.anim.activity_move_in, R.anim.activity_move_out);
    }

}