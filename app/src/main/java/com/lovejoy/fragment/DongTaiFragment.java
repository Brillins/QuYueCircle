package com.lovejoy.fragment;

import java.util.ArrayList;
import java.util.List;

import com.lovejoy.activity.R;
import com.lovejoy.view.AddPopWindow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;



public class DongTaiFragment extends Fragment implements OnCheckedChangeListener{

	private View parentView;
	private RadioGroup radioGroup;
	private RadioButton rbJoined, rbRecommendation, rbFriend;
	private ViewPager  viewpager;
    private ImageView iv_add;

	List<Fragment> list = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fragment_dongtai, container, false);
		
		radioGroup = (RadioGroup) parentView.findViewById(R.id.radioGroup);
		
		rbJoined = (RadioButton) parentView.findViewById(R.id.rbJoined);
		rbRecommendation = (RadioButton) parentView.findViewById(R.id.rbRecommendation);
		rbFriend = (RadioButton) parentView.findViewById(R.id.rbFriend);
		
		viewpager = (ViewPager)parentView.findViewById(R.id.vp_DongTai);
		
		iv_add = (ImageView) parentView.findViewById(R.id.iv_add);
		
		list = new ArrayList<Fragment>();
		TitleJoinedFragment tJoined = new TitleJoinedFragment();
		TitleRecommendationFragment tRecommendation = new TitleRecommendationFragment();
        TitleFriendFragment tFriend = new TitleFriendFragment();
		list.add(tJoined);
		list.add(tRecommendation);
        list.add(tFriend);
		
		ZxzcAdapter zxzc = new ZxzcAdapter(getChildFragmentManager(), list);
		viewpager.setAdapter(zxzc);
		zxzc.notifyDataSetChanged();
		
		radioGroup.setOnCheckedChangeListener(this);
        rbJoined.setChecked(true);

		//滑动切换
		viewpager.setOnPageChangeListener(new OnPageChangeListener() {		
			@Override
			public void onPageSelected(int arg0) {
				switch (arg0) {
                    case 0:
                        rbJoined.setChecked(true);
                        break;
                    case 1:
                        rbRecommendation.setChecked(true);
                        break;
                    case 2:
                        rbFriend.setChecked(true);
                        break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
		
		//点击右边显示
		iv_add.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                AddPopWindow addPopWindow = new AddPopWindow(getActivity());
                addPopWindow.showPopupWindow(iv_add);
            }

        });

		return parentView;
	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int checkedId) {
		if (checkedId == rbJoined.getId()) {
			viewpager.setCurrentItem(0);
		} else if (checkedId == rbRecommendation.getId()) {
			viewpager.setCurrentItem(1);
		} else if(checkedId == rbFriend.getId()){
            viewpager.setCurrentItem(2);
        }
	}

	
class ZxzcAdapter extends FragmentStatePagerAdapter {

		List<Fragment> list;			
		public ZxzcAdapter(FragmentManager fm,List<Fragment> list) {
			super(fm);
			this.list=list;			
		}
		@Override
		public Fragment getItem(int arg0) {			

            return list.get(arg0);
		}
		@Override
		public int getCount() {
		
			return list.size();
		}

	}
}
