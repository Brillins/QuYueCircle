package com.lovejoy.fragment;

import com.lovejoy.view.activity.R;
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

public class DynamicTopRecomFragment extends Fragment {

	private ListView tRecomListView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_dynamic_top_recom, container, false);

        tRecomListView = (ListView)view.findViewById(R.id.tRecom_list_view);
        List<Map<String, Object>> list = getData();
        tRecomListView.setAdapter(new ListViewAdapter(getActivity(), list));

		return view;
	}


    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list = new ArrayList<>();
        list.clear();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("activityName", "推荐图书漂流"+i);
            map.put("createTime", "2017-5-8 9:00");
            map.put("creatorImage", R.drawable.icon_profile_01);
            map.put("creatorName", "name"+i);
            map.put("abstractInfor", "这是一个详细信息，信息，指音讯、消息、通讯系统传输和处理的对象，泛指人类社会传播的一切内容。人通过获得、识别自然界和社会的不同信息来区别不同事物，得以认识和改造世界。在一切通讯和控制系统中，信息是一种普遍联系的形式。1948年，数学家香农在题为“通讯的数学理论”的论文中指出：“信息是用来消除随机不定性的东西”。创建一切宇宙万物的最基本万能单位是信息。\n" +
                    "中文名信息词目信息" + i);
            map.put("planMinNumber", 7);
            map.put("planMaxNumber", 10);
            map.put("currentNumber", 6);
            map.put("activityDeadline", "2017-5-10 12:00");
            map.put("startTime", "2017-5-11 14:00");

            list.add(map);
        }
        return list;
    }
	

}
