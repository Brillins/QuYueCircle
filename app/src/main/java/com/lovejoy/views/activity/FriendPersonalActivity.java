package com.lovejoy.views.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.lovejoy.api.PostRequests;
import com.lovejoy.core.MDFonts;
import com.lovejoy.core.MDFontsUtils;
import com.lovejoy.views.view.RoundImageView;

import net.sf.json.JSONObject;

import java.io.File;

/**
 * Created by lenovo on 2017/6/12.
 */
public class FriendPersonalActivity extends AppCompatActivity implements View.OnClickListener {
    //请求相机
    int userid=22;
    Handler handler=null;
    PostRequests pRequest = null;
    Handler handler2=null;
    JSONObject obj=null;
    Context context=this;

    private TextView telephone_text_view;
    private TextView mail_text_view;
    private TextView school_text_view;
    private TextView username_text_view;
    private TextView student_num_text_view;
    private TextView reputation_text_view;
    //private List<ActivityBriefInfor> activityList=new ArrayList<ActivityBriefInfor>();
    //private ListView historyListView;

    private TextView textView_personal;



    private TextView textView_email_ui;
    private TextView textView_telephone_ui;
    private TextView textView_reputation_ui;
    private TextView textView_school_ui;
    private TextView textView_student_num_ui;


    private RoundImageView headImage;
    private LinearLayout linearLayout_add_friend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_personal);

        telephone_text_view=(TextView) findViewById(R.id.text_view_friend_telephone);
        telephone_text_view.setTypeface(MDFontsUtils.getTypeface(this,"fonts/Roboto-Italic.ttf"));

        mail_text_view=(TextView) findViewById(R.id.text_view_friend_account);
        mail_text_view.setTypeface(MDFontsUtils.getTypeface(this,"fonts/Roboto-Italic.ttf"));

        school_text_view=(TextView) findViewById(R.id.text_view_friend_school);
        school_text_view.setTypeface(MDFontsUtils.getTypeface(this,"fonts/Roboto-Italic.ttf"));

        username_text_view=(TextView)findViewById(R.id.text_view_friend_name) ;
        username_text_view.setTypeface(MDFontsUtils.getTypeface(this,"fonts/Roboto-Italic.ttf"));

        student_num_text_view=(TextView) findViewById(R.id.text_view_friend_student_number);
        student_num_text_view.setTypeface(MDFontsUtils.getTypeface(this,"fonts/Roboto-Italic.ttf"));

        textView_personal=(TextView)findViewById(R.id.text_view_friend_personal) ;
        textView_personal.setTypeface(MDFontsUtils.getTypeface(this,"fonts/Roboto-Italic.ttf"));

        reputation_text_view=(TextView)findViewById(R.id.text_view_friend_reputation) ;
        reputation_text_view.setTypeface(MDFontsUtils.getTypeface(this,"fonts/Roboto-Italic.ttf"));

        textView_email_ui=(TextView)findViewById(R.id.text_view_friend_email_ui);
        textView_email_ui.setText(MDFonts.ENVELOPE);
        MDFontsUtils.setOcticons(textView_email_ui);

        textView_telephone_ui=(TextView)findViewById(R.id.text_view_friend_telephone_ui);
        textView_telephone_ui.setText(MDFonts.PHONE);
        MDFontsUtils.setOcticons(textView_telephone_ui);

        textView_reputation_ui=(TextView)findViewById(R.id.text_view_friend_reputation_ui);
        textView_reputation_ui.setText(MDFonts.PASSWORD);
        textView_reputation_ui.setTypeface(MDFontsUtils.getTypeface(this,"fonts/icomoon1.ttf"));

        textView_school_ui=(TextView)findViewById(R.id.text_view_friend_school_ui);
        textView_school_ui.setText(MDFonts.SCHOOL);
        textView_school_ui.setTypeface(MDFontsUtils.getTypeface(this,"fonts/icomoon1.ttf"));

        textView_student_num_ui=(TextView)findViewById(R.id.text_view_friend_student_num_ui);
        textView_student_num_ui.setText(MDFonts.STUDENT_NUM);
        textView_student_num_ui.setTypeface(MDFontsUtils.getTypeface(this,"fonts/icomoon1.ttf"));

        headImage = (RoundImageView) findViewById(R.id.image_view_friend_head);
        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.headimage);
        //headImage.setBitmap(bitmap);
        linearLayout_add_friend=(LinearLayout)findViewById(R.id.line_layout_add_friend);

        pRequest=new PostRequests();
        handler2=new Handler(){
            public void handleMessage(android.os.Message msg) {
                if (msg!=null) {
                    if (msg.obj!=null){
                        Bitmap bmp = (Bitmap) msg.obj;
                        //headImage.setBitmap(bmp);
                        headImage.setImageBitmap(bmp);
                    }
                    else{
                        Toast.makeText(context, "failed to get data", Toast.LENGTH_LONG).show();
                    }
                }
            };
        };
        handler=new Handler(){
            public void handleMessage(android.os.Message msg) {
                if (msg!=null) {
                    if (msg.obj!=null){
                        Object result = msg.obj;
                        JSONObject js2=JSONObject.fromObject(result);
                        username_text_view.setText(js2.getString("name"));
                        telephone_text_view.setText(Integer.toString(js2.getInt("phone")));
                        mail_text_view.setText(js2.getString("mail"));
                        school_text_view.setText(js2.getString("college"));
                        student_num_text_view.setText(js2.getString("stu_id"));
                        reputation_text_view.setText(js2.getString("credict"));
                        int ava=js2.getInt("ava");
                        pRequest.getpic("/get_pic", "/"+Integer.toString(ava),handler2,context);
                    }
                    else{
                        Toast.makeText(context, "failed to get data", Toast.LENGTH_LONG).show();
                    }
                }
            };
        };
        obj=new JSONObject();
        pRequest.sendPost("/get_user_info", "/"+Integer.toString(userid),obj,handler,context);

        linearLayout_add_friend.setOnClickListener(this);
        //headImage.setOnClickListener(this);
        //phone_change_button.setOnClickListener(this);
        //password_change_button.setOnClickListener(this);
        //createCameraTempFile(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.line_layout_add_friend:
                //uploadPassword();
                break;
        }
    }


}
