package com.lovejoy.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lovejoy.model.ReputationItem;

import java.util.List;
import com.lovejoy.views.activity.R;

public class ReputationAdapter extends ArrayAdapter<ReputationItem> {
    private int resourceId;

    public ReputationAdapter(Context context, int textViewResourceId, List<ReputationItem> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ReputationItem reputationItem=getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder=new ViewHolder();
            viewHolder.reputation_activity_id=(TextView) view.findViewById(R.id.text_view_reputation_activity_id);
            viewHolder.reputation_star=(TextView) view.findViewById(R.id.text_view_reputation_star);
            viewHolder.reputation_user=(TextView) view.findViewById(R.id.text_view_reputation_user);
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }
        viewHolder.reputation_activity_id.setText(reputationItem.getReputation_activity_id());
        viewHolder.reputation_star.setText(reputationItem.getReputation_star());
        viewHolder.reputation_user.setText(reputationItem.getReputation_user());
        return view;
    }

    class ViewHolder{
        TextView reputation_activity_id;
        TextView reputation_star;
        TextView reputation_user;
    }
}
