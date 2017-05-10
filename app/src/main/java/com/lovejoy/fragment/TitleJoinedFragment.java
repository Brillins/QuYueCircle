package com.lovejoy.fragment;

import com.lovejoy.activity.R;
import com.lovejoy.entity.ActivityBriefInfor;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TitleJoinedFragment extends Fragment {

	ListView joinedListView;//声明一个ListView对象
	private Context mContext;

	//声明一个list，动态存储要显示的信息
	private List<ActivityBriefInfor> joinedList = new ArrayList<ActivityBriefInfor>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_title_joined, container, false);

		mContext = view.getContext();

		joinedListView = (ListView)view.findViewById(R.id.joined_list);


		return view;
	}
	

}
