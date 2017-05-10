package com.lovejoy.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.lovejoy.activity.R;
import com.lovejoy.entity.ActivityBriefInfor;

import java.util.List;

public class ListViewAdapter extends BaseAdapter{
    private Context context;

    View[] itemViews;
    public ListViewAdapter(List<ActivityBriefInfor> mListInfor, Context c){
        this.context = c;
        itemViews = new View[mListInfor.size()];

        for(int i=0;i<mListInfor.size();i++){
            //获取第i个对象
            ActivityBriefInfor getInfor=(ActivityBriefInfor)mListInfor.get(i);
            //调用makeItemView，实例化一个Item
            itemViews[i]=makeItemView(context, getInfor.getActivityName(), getInfor.getCreateTime(),
                    getInfor.getCreatorName(), getInfor.getCreatorImageId(), getInfor.getAbstractInfor(),
                    getInfor.getPlanMinNumber(), getInfor.getPlanMaxNumber(), getInfor.getCurrentNumber(),
                    getInfor.getDeadline(), getInfor.getStartTime());
        }
    }

    @Override
    public int getCount() {
        return itemViews.length;
    }

    @Override
    public View getItem(int position) {
        return itemViews[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //绘制Item的函数
    private View makeItemView(Context context, String activityName, String createTime, String creatorName,
                              int creatorImageId, String abstractInfor, int planMinNumber, int planMaxNumber,
                              int currentNumber, String deadline, String startTime) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 使用View的对象itemView与R.layout.item关联
        View itemView = inflater.inflate(R.layout.activity_list_item, null);

        //活动名称
        TextView actName = (TextView) itemView.findViewById(R.id.activity_name);
        actName.setText(activityName);
        //发布时间
        TextClock cTime = (TextClock) itemView.findViewById(R.id.create_date);
        cTime.setFormat24Hour(createTime);
        //发布者头像
        ImageView creatorImage = (ImageView) itemView.findViewById(R.id.creator_image);
        creatorImage.setImageResource(creatorImageId);
        //发布者昵称
        TextView cName = (TextView) itemView.findViewById(R.id.creator_name);
        cName.setText(creatorName);
        //信息摘要
        TextView absInfor = (TextView) itemView.findViewById(R.id.abstract_infor);
        absInfor.setText(abstractInfor);
        //规定最少人数
        TextView pMinNum = (TextView) itemView.findViewById(R.id.plan_number_min);
        pMinNum.setText(planMinNumber);
        //规定最多人数
        TextView pMaxNum = (TextView) itemView.findViewById(R.id.plan_number_max);
        pMaxNum.setText(planMaxNumber);
        //当前人数
        TextView cNum = (TextView) itemView.findViewById(R.id.current_number);
        cNum.setText(currentNumber);
        //报名截止时间
        TextClock dl = (TextClock) itemView.findViewById(R.id.activity_dealine);
        dl.setFormat24Hour(deadline);
        //活动开始时间
        TextClock sTime = (TextClock) itemView.findViewById(R.id.start_time);
        sTime.setFormat24Hour(startTime);

        return itemView;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            return itemViews[position];
        return convertView;
    }
}
