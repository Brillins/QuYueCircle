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

public class TitleStudyFragment extends Fragment {

	private Context mContext;
	private GridView study_gridView;
	private BaseAdapter mAdapter = null;
	private ArrayList<CircleIcon> mData = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_title_study, container, false);

		mContext = view.getContext();
		study_gridView = (GridView) view.findViewById(R.id.study_grid_view);

		mData = new ArrayList<CircleIcon>();
		mData.add(new CircleIcon(R.drawable.study_icon_01_pink, "自习"));
		mData.add(new CircleIcon(R.drawable.study_icon_02_pink, "实验"));
		mData.add(new CircleIcon(R.drawable.study_icon_03_pink, "辩论赛"));
		mData.add(new CircleIcon(R.drawable.study_icon_04_pink, "书友会"));
		mData.add(new CircleIcon(R.drawable.study_icon_05_pink, "数学建模"));
		mData.add(new CircleIcon(R.drawable.study_icon_06_pink, "宣讲会"));
		mData.add(new CircleIcon(R.drawable.study_icon_07_pink, "新软攀峰"));
		mData.add(new CircleIcon(R.drawable.study_icon_08_pink, "英语写作"));
		mData.add(new CircleIcon(R.drawable.study_icon_09_pink, "程序设计"));
		mData.add(new CircleIcon(R.drawable.study_icon_10_pink, "机械设计"));
		mData.add(new CircleIcon(R.drawable.study_icon_11_pink, "建筑结构设计"));
		mData.add(new CircleIcon(R.drawable.study_icon_12_pink, "图书漂流"));


		mAdapter = new CircleAdapter<CircleIcon>(mData, R.layout.fragment_title_study_item) {
			@Override
			public void bindView(ViewHolder holder, CircleIcon obj) {
				holder.setImageResource(R.id.study_item_icon, obj.getiId());
				holder.setText(R.id.study_item_text, obj.getiName());
			}
		};

		study_gridView.setAdapter(mAdapter);

		study_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				int p = position + 1;
				Toast.makeText(mContext, "你点击了第" + p + "项", Toast.LENGTH_SHORT).show();
			}
		});

		return view;
	}
	

}
