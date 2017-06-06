package com.lovejoy.fragment;

import com.lovejoy.view.activity.CircleListActivity;
import com.lovejoy.view.activity.R;
import com.lovejoy.adapter.CircleAdapter;
import com.lovejoy.model.CircleIcon;

import android.content.Intent;
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

public class CircleTopArtFragment extends Fragment {

	private GridView art_gridView;
	private BaseAdapter mAdapter = null;
	private ArrayList<CircleIcon> mData = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_circle_top_art, container, false);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		art_gridView = (GridView) getActivity().findViewById(R.id.art_grid_view);

		mData = new ArrayList<CircleIcon>();
		mData.add(new CircleIcon(R.drawable.art_icon_01_pink, "吉他"));
		mData.add(new CircleIcon(R.drawable.art_icon_02_pink, "绘画"));
		mData.add(new CircleIcon(R.drawable.art_icon_03_pink, "琵琶"));
		mData.add(new CircleIcon(R.drawable.art_icon_04_pink, "古琴"));
		mData.add(new CircleIcon(R.drawable.art_icon_05_pink, "诗歌"));
		mData.add(new CircleIcon(R.drawable.art_icon_06_pink, "舞蹈"));
		mData.add(new CircleIcon(R.drawable.art_icon_07_pink, "歌曲"));
		mData.add(new CircleIcon(R.drawable.art_icon_08_pink, "书法"));
		mData.add(new CircleIcon(R.drawable.art_icon_09_pink, "电影"));
		mData.add(new CircleIcon(R.drawable.art_icon_10_pink, "小说"));
		mData.add(new CircleIcon(R.drawable.art_icon_11_pink, "摄影"));
		mData.add(new CircleIcon(R.drawable.art_icon_12_pink, "表演"));

		mAdapter = new CircleAdapter<CircleIcon>(mData, R.layout.fragment_circle_top_art_item) {
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
				String circleTitle = null;
				Toast.makeText(getContext(), "你点击了第" + p + "项", Toast.LENGTH_SHORT).show();

				switch (p){
					case 1:
						circleTitle = "吉他";
						break;
					case 2:
						circleTitle = "绘画";
						break;
					case 3:
						circleTitle = "琵琶";
						break;
					case 4:
						circleTitle = "古琴";
						break;
					case 5:
						circleTitle = "诗歌";
						break;
					case 6:
						circleTitle = "舞蹈";
						break;
					case 7:
						circleTitle = "歌曲";
						break;
					case 8:
						circleTitle = "书法";
						break;
					case 9:
						circleTitle = "电影";
						break;
					case 10:
						circleTitle = "小说";
						break;
					case 11:
						circleTitle = "摄影";
						break;
					case 12:
						circleTitle = "表演";
						break;
					default:
						break;
				}

				Bundle bundle = new Bundle();
				Intent intent = new Intent(getActivity(),CircleListActivity.class);
				bundle.putString("circleTitle",circleTitle);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});

	}

}
