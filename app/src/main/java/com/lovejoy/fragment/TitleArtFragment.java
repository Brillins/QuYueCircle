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

public class TitleArtFragment extends Fragment {

	private Context mContext;
	private GridView art_gridView;
	private BaseAdapter mAdapter = null;
	private ArrayList<CircleIcon> mData = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_title_art, container, false);

		mContext = view.getContext();
		art_gridView = (GridView) view.findViewById(R.id.art_grid_view);

		mData = new ArrayList<CircleIcon>();
		mData.add(new CircleIcon(R.drawable.art_icon_01_blue, "吉他"));
		mData.add(new CircleIcon(R.drawable.art_icon_02_blue, "绘画"));
		mData.add(new CircleIcon(R.drawable.art_icon_03_blue, "琵琶"));
		mData.add(new CircleIcon(R.drawable.art_icon_04_blue, "古琴"));
		mData.add(new CircleIcon(R.drawable.art_icon_05_blue, "诗歌"));
		mData.add(new CircleIcon(R.drawable.art_icon_06_blue, "舞蹈"));
		mData.add(new CircleIcon(R.drawable.art_icon_07_blue, "歌曲"));
		mData.add(new CircleIcon(R.drawable.art_icon_08_blue, "书法"));
		mData.add(new CircleIcon(R.drawable.art_icon_09_blue, "电影"));
		mData.add(new CircleIcon(R.drawable.art_icon_10_blue, "小说"));
		mData.add(new CircleIcon(R.drawable.art_icon_11_blue, "摄影"));
		mData.add(new CircleIcon(R.drawable.art_icon_12_blue, "表演"));


		mAdapter = new CircleAdapter<CircleIcon>(mData, R.layout.fragment_title_art_item) {
			@Override
			public void bindView(ViewHolder holder, CircleIcon obj) {
				holder.setImageResource(R.id.art_item_icon, obj.getiId());
				holder.setText(R.id.art_item_text, obj.getiName());
			}
		};

		art_gridView.setAdapter(mAdapter);

		art_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				int p = position + 1;
				Toast.makeText(mContext, "你点击了第" + p + "项", Toast.LENGTH_SHORT).show();
			}
		});

		return view;
	}

}
