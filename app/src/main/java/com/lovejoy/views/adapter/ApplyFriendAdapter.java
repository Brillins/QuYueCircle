package com.lovejoy.views.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.lovejoy.api.PostRequests;
import com.lovejoy.model.Friend;
import com.lovejoy.views.view.RoundImageView;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.lovejoy.views.activity.R;

/**
 * Created by rin on 2017/6/11.
 */

public class ApplyFriendAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Friend> data;
    private PostRequests pr=null;
    private Handler imagehandler=null;
    Handler handler=null;
    public ApplyFriendAdapter(ArrayList<Friend> list , Context context,Handler handler) {
        this.context = context;
        data=list;
        this.handler=handler;
    }

    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        pr=new PostRequests();
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.applyfriendtextview,parent,false);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.text);
            holder.textView2 = (TextView) convertView.findViewById(R.id.text2);
            holder.iv = (ImageView) convertView.findViewById(R.id.accept);
            holder.head=(RoundImageView)convertView.findViewById(R.id.image) ;
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        holder.iv.setImageResource(R.drawable.evaluation_order_star_press);
         Map<String,String> map = new HashMap<String,String>();
        map.put("userid","22");
        map.put("friendid",Integer.toString(data.get(position).id));
        map.put("text","我是wangtianran,想和你做朋友");
       final JSONObject obj = JSONObject.fromObject(map);
        holder.textView.setText(data.get(position).getText1());
        holder.textView2.setText(data.get(position).getText2());
        holder.head.setImageBitmap(data.get(position).bmp);
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pr.sendPost("/send_friend_invitation","",obj,handler,context);
            }
        });
        return convertView;
    }


    static class ViewHolder {
        TextView textView;
        TextView textView2;
        RoundImageView head;
       // Button button1;
        ImageView iv;
    }

}