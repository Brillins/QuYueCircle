package com.lovejoy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lovejoy.activity.R;

import java.util.List;
import java.util.Map;

public class ListViewAdapter extends BaseAdapter{

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;


    public ListViewAdapter(Context context,List<Map<String, Object>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
    }

    //组件集合，对应list.xml中的控件
    public final class ItemComponent{
        public TextView activityName;
        public TextView createTime;
        public ImageView creatorImage;
        public TextView creatorName;
        public TextView abstractInfor;
        public TextView planMinNumber;
        public TextView planMaxNumber;
        public TextView currentNumber;
        public TextView activityDeadline;
        public TextView startTime;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    //获得某一位置的数据
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    //获得唯一标识
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemComponent itemComponent = null;

        if(convertView == null){

            itemComponent = new ItemComponent();
            //获得组件，实例化组件
            convertView = layoutInflater.inflate(R.layout.activity_list_item, null);
            itemComponent.activityName = (TextView) convertView.findViewById(R.id.activity_name);
            itemComponent.createTime = (TextView) convertView.findViewById(R.id.create_Time);
            itemComponent.creatorImage = (ImageView) convertView.findViewById(R.id.creator_image);
            itemComponent.creatorName = (TextView) convertView.findViewById(R.id.creator_name);
            itemComponent.abstractInfor = (TextView) convertView.findViewById(R.id.abstract_infor);
            itemComponent.planMinNumber = (TextView) convertView.findViewById(R.id.plan_number_min);
            itemComponent.planMaxNumber = (TextView) convertView.findViewById(R.id.plan_number_max);
            itemComponent.currentNumber = (TextView) convertView.findViewById(R.id.current_number);
            itemComponent.activityDeadline = (TextView) convertView.findViewById(R.id.activity_deadline);
            itemComponent.startTime = (TextView) convertView.findViewById(R.id.start_time);

            convertView.setTag(itemComponent);
        }else{
            itemComponent=(ItemComponent)convertView.getTag();
        }

        //绑定数据
        itemComponent.activityName.setText(data.get(position).get("activityName").toString());
        itemComponent.createTime.setText(data.get(position).get("createTime").toString());
        itemComponent.creatorImage.setImageResource((Integer)data.get(position).get("creatorImage"));
        itemComponent.creatorName.setText(data.get(position).get("creatorName").toString());
        itemComponent.abstractInfor.setText((String)data.get(position).get("abstractInfor"));
        itemComponent.planMinNumber.setText(data.get(position).get("planMinNumber").toString());
        itemComponent.planMaxNumber.setText(data.get(position).get("planMaxNumber").toString());
        itemComponent.currentNumber.setText(data.get(position).get("currentNumber").toString());
        itemComponent.activityDeadline.setText(data.get(position).get("activityDeadline").toString());
        itemComponent.startTime.setText(data.get(position).get("startTime").toString());

        return convertView;
    }



}
