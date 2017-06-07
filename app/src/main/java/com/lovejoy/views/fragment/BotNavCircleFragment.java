package com.lovejoy.views.fragment;

import java.util.ArrayList;
import java.util.List;

import com.lovejoy.views.activity.R;
import com.lovejoy.views.view.AddPopWindow;

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

public class BotNavCircleFragment extends Fragment implements OnCheckedChangeListener {

	private RadioGroup radioGroup;
	private RadioButton rbSport, rbArt, rbStudy;
	private ViewPager viewpager;
	private ImageView iv_add;

	List<Fragment> list = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_bot_nav_circle, container, false);
        
		viewpager = (ViewPager) view.findViewById(R.id.vp_QuanZi);
        
		radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup_quanzi);
        
		rbSport = (RadioButton) view.findViewById(R.id.rb_sport);
		rbArt = (RadioButton) view.findViewById(R.id.rb_art);
		rbStudy = (RadioButton) view.findViewById(R.id.rb_study);
        
		iv_add = (ImageView) view.findViewById(R.id.iv_add);

		list = new ArrayList<Fragment>();

		CircleTopSportFragment tSport = new CircleTopSportFragment();
		CircleTopArtFragment tArt = new CircleTopArtFragment();
		CircleTopStudyFragment tStudy = new CircleTopStudyFragment();

		list.add(tSport);
		list.add(tArt);
		list.add(tStudy);

		ZxzcAdapter zxzc = new ZxzcAdapter(getChildFragmentManager(), list);
		viewpager.setAdapter(zxzc);
		zxzc.notifyDataSetChanged();

		radioGroup.setOnCheckedChangeListener(this);
		rbSport.setChecked(true);
		
		//滑动切换
        viewpager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                switch (arg0) {
                    case 0:
                        rbSport.setChecked(true);
                        break;
                    case 1:
                        rbArt.setChecked(true);
                        break;
                    case 2:
                        rbStudy.setChecked(true);
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
				

		// 点击右边显示
		iv_add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AddPopWindow addPopWindow = new AddPopWindow(getActivity());
				addPopWindow.showPopupWindow(iv_add);
			}
		});

		return view;
	}

	class ZxzcAdapter extends FragmentStatePagerAdapter {

		List<Fragment> list;

		public ZxzcAdapter(FragmentManager fm, List<Fragment> list) {
			super(fm);
			this.list = list;
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

	@Override
	public void onCheckedChanged(RadioGroup arg0, int checkedId) {
		if (checkedId == rbSport.getId()) {
			viewpager.setCurrentItem(0);
		} else if (checkedId == rbArt.getId()) {
			viewpager.setCurrentItem(1);
		} else if (checkedId == rbStudy.getId()) {
			viewpager.setCurrentItem(2);
		}
	}
}
