package com.lovejoy.views.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lovejoy.api.PostRequests;
import com.lovejoy.core.CustomDialog;
import com.lovejoy.core.CustomDialogforpeople;
import com.lovejoy.views.adapter.MyAdapter;
import net.sf.json.JSONObject;

import java.util.ArrayList;

public class AccessActivity extends Activity {
    private TextView name;
    private TextView desc;
    private TextView sponser;
    private TextView currentNum;
    private TextView minNum;
    private TextView maxNum;
    private TextView startTime;
    private TextView deadLine;
    Context mcontext=this;
    private TextView address;
    private GridView gridView;
    private Button access;
    private BaseAdapter mAdapter = null;
    private ArrayList<Integer> mData = null;
    int actid=22;
    int userid=22;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);
        name=(TextView)this.findViewById(R.id.Name);
        desc=(TextView)this.findViewById(R.id.Desc);
        sponser=(TextView)this.findViewById(R.id.Sponsor);
        currentNum=(TextView)this.findViewById(R.id.CurrentNum);
        minNum=(TextView)this.findViewById(R.id.MinNum);
        maxNum=(TextView)this.findViewById(R.id.MaxNum);
        startTime=(TextView)this.findViewById(R.id.StartTime);
        deadLine=(TextView)this.findViewById(R.id.Deadline);
        address=(TextView)this.findViewById(R.id.Address);
        gridView=(GridView) this.findViewById(R.id.gridview);
        access=(Button) this.findViewById(R.id.Access);
        //点击评价按钮 ，弹出评价框
        mData = new ArrayList<Integer>();
        mData.add(R.drawable.robot);
        mData.add(R.drawable.robot);

        mAdapter = new MyAdapter<Integer>(mData, R.layout.iconsforg) {
            @Override
            public void bindView(ViewHolder holder, Integer obj) {
                holder.setImageResource(R.id.img_icon,obj);

            }
        };
        gridView.setAdapter(mAdapter);


        handler =new Handler(){
            public void handleMessage(android.os.Message msg) {
                if (msg != null) {
                    if (msg.obj != null) {
                        Object result = msg.obj;
                        JSONObject robj = JSONObject.fromObject(result);
                        name.setText("活动名称:"+robj.getString("name"));
                        desc.setText("描述;"+robj.getString("description"));
                        sponser.setText("发布id:"+robj.getString("publisher"));
                        currentNum.setText("参加人数:"+Integer.toString(robj.getInt("cur_num")));
                        maxNum.setText("限制人数:"+Integer.toString(robj.getInt("max_num")));
                        minNum.setText("最少人数:"+Integer.toString(robj.getInt("min_num")));
                        address.setText("标签:"+robj.getString("tags"));
                        startTime.setText("开始时间:"+robj.getString("start_date"));
                        deadLine.setText("报名截止:"+robj.getString("end_date"));

                    } else {
                        Toast.makeText(mcontext, "failed to get data", Toast.LENGTH_LONG).show();
                    }
                }
            }};
        access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog1();
            }
        });
        PostRequests pr=new PostRequests();
        JSONObject obj=new JSONObject();
        pr.sendPost("/get_activity_details","/"+Integer.toString(actid),obj,handler,mcontext);




    }

    public void showDialog1() {


        CustomDialog.Builder builder = new CustomDialog.Builder(mcontext);
        builder.setMessage("活动评分");
        builder.setTitle("活动评分");
        builder .setPositiveButton("确定"  ,new android.content.DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                showDialog2();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });

        builder.create().show();


    }

    public void showDialog2() {

        CustomDialogforpeople.Builder builder = new CustomDialogforpeople.Builder(mcontext);
        builder.setMessage("用户评分");
        builder.setTitle("用户评价");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("取消",
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();

    }
}
