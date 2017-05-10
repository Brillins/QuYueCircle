package com.lovejoy.fragment;

import com.lovejoy.activity.R;
import com.lovejoy.adapter.ListViewAdapter;
import com.lovejoy.entity.ActivityBriefInfor;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TitleJoinedFragment extends Fragment {

    private Context mContext;

	ListView joinedListView;//声明一个ListView对象

	//声明一个list，动态存储要显示的信息
	private List<ActivityBriefInfor> joinedList = new ArrayList<ActivityBriefInfor>();


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_title_joined, container, false);

		mContext = view.getContext();

		//将listView与布局对象关联
		joinedListView = (ListView)view.findViewById(R.id.joined_list_view);

		setInfor();//给活动list赋值函数，用来测试

		joinedListView.setAdapter(new ListViewAdapter(joinedList, mContext));

		joinedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ActivityBriefInfor testObject = joinedList.get(position);
				int actId = testObject.getActivityId();//获得活动ID
				String actName = testObject.getActivityName();//获得活动名称
				String cName = testObject.getCreatorName();//获得发布者昵称

				//Toast显示测试
				Toast.makeText(mContext, "测试显示:\n"+actId+"\n"+actName+"\n"+cName,Toast.LENGTH_LONG).show();
			}
		});


		return view;
	}
	public void setInfor(){
		joinedList.clear();
		int i = 0;
		while(i<2){
			ActivityBriefInfor joinedAct = new ActivityBriefInfor();
			joinedAct.setActivityId(1000 + i);
			joinedAct.setActivityName("Title" + 1);

//			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy年MM月dd日 HH:mm ");
//			Date curDate = new Date(System.currentTimeMillis());//获取当前时间
//			String curTime = formatter.format(curDate);
//
//			joinedAct.setCreateTime(curTime);

            joinedAct.setCreateTime("2017-5-8 9:30");
			joinedAct.setCreatorImageId(R.drawable.icon_profile);
			joinedAct.setCreatorName("LoveJoy");
			joinedAct.setAbstractInfor("今天下午图书馆前有一次图书漂流活动，主旨是......");
			joinedAct.setPlanMinNumber(7);
			joinedAct.setPlanMaxNumber(10);
			joinedAct.setCurrentNumber(6);
			joinedAct.setDeadline("2017-5-10 12:00");
			joinedAct.setStartTime("2017-5-11 8:00");

            joinedList.add(joinedAct);
			i++;
		}
	}

}
