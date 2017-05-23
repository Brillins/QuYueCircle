package com.lovejoy.fragment;

import com.lovejoy.activity.R;
import com.lovejoy.adapter.ListViewAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TitleJoinedFragment extends Fragment {

    private ListView joinedListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_title_joined, container, false);

        joinedListView = (ListView)view.findViewById(R.id.joined_list_view);
        List<Map<String, Object>> list = getData();
        joinedListView.setAdapter(new ListViewAdapter(getActivity(), list));

		return view;
	}


    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        list.clear();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("activityName", "图书漂流"+i);
            map.put("createTime", "2017-5-8 9:00");
            map.put("creatorImage", R.drawable.icon_profile_01);
            map.put("creatorName", "name"+i);
            map.put("abstractInfor", "这是一个详细信息" + i);
            map.put("planMinNumber", 7);
            map.put("planMaxNumber", 10);
            map.put("currentNumber", 6);
            map.put("activityDealine", "2017-5-10 12:00");
            map.put("startTime", "2017-5-11 14:00");

            list.add(map);
        }
        return list;
    }


}
