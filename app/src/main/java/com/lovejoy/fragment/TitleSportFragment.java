package com.lovejoy.fragment;

import com.lovejoy.activity.R;
import com.lovejoy.adapter.CircleAdapter;
import com.lovejoy.entity.CircleIcon;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;


public class TitleSportFragment extends Fragment {

    private Context mContext;
	private GridView sport_gridView;
	private BaseAdapter mAdapter = null;
	private ArrayList<CircleIcon> mData = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_title_sport, container, false);

        mContext = view.getContext();
		sport_gridView = (GridView) view.findViewById(R.id.sport_grid_view);

		mData = new ArrayList<CircleIcon>();
		mData.add(new CircleIcon(R.drawable.sport_icon_01_blue, "篮球"));
		mData.add(new CircleIcon(R.drawable.sport_icon_02_blue, "足球"));
		mData.add(new CircleIcon(R.drawable.sport_icon_03_blue, "网球"));
		mData.add(new CircleIcon(R.drawable.sport_icon_04_blue, "跑步"));
		mData.add(new CircleIcon(R.drawable.sport_icon_05_blue, "攀岩"));
		mData.add(new CircleIcon(R.drawable.sport_icon_06_blue, "自行车"));
		mData.add(new CircleIcon(R.drawable.sport_icon_07_blue, "乒乓球"));
        mData.add(new CircleIcon(R.drawable.sport_icon_08_blue, "健身"));
        mData.add(new CircleIcon(R.drawable.sport_icon_09_blue, "羽毛球"));
        mData.add(new CircleIcon(R.drawable.sport_icon_10_blue, "游泳"));
        mData.add(new CircleIcon(R.drawable.sport_icon_11_blue, "棒球"));
        mData.add(new CircleIcon(R.drawable.sport_icon_12_blue, "太极拳"));


        mAdapter = new CircleAdapter<CircleIcon>(mData, R.layout.fragment_title_sport_item) {
            @Override
            public void bindView(ViewHolder holder, CircleIcon obj) {
                holder.setImageResource(R.id.sport_item_icon, obj.getiId());
                holder.setText(R.id.sport_item_text, obj.getiName());
            }
        };

        sport_gridView.setAdapter(mAdapter);

        sport_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int p = position + 1;
                Toast.makeText(mContext, "你点击了第" + p + "项", Toast.LENGTH_SHORT).show();
            }
        });

		return view;
	}
}
