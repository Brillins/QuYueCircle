package com.lovejoy.views.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lovejoy.views.activity.R;
import com.lovejoy.core.XLHRatingBar;
import com.lovejoy.model.actmembers;

import java.util.LinkedList;

/**
 * Created by pc on 2017/6/7.
 */
public class commentAdapter extends BaseAdapter {

    private LinkedList<actmembers> mData;
    private Context mContext;

    public commentAdapter(LinkedList<actmembers> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.commentlist,parent,false);
        ImageView img_icon = (ImageView) convertView.findViewById(R.id.avatar);
        TextView txt_aName = (TextView) convertView.findViewById(R.id.cname);
        XLHRatingBar star = (XLHRatingBar) convertView.findViewById(R.id.cratingBar);
        LinearLayout  lv=(LinearLayout)convertView.findViewById(R.id.u_list);
        if(position%2==0)
            lv.setBackgroundResource(R.drawable.textview);
        else
            lv.setBackgroundResource(R.drawable.textviewgrey);
        //img_icon.setBackgroundResource(mData.get(position).getaIcon());
        txt_aName.setText(mData.get(position).getName());
        star.setCountSelected(mData.get(position).getStars());


        return convertView;
    }
}
