package com.lovejoy.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.lovejoy.views.fragment.BotNavDynamicFragment;
import com.lovejoy.views.fragment.BotNavCircleFragment;
import com.lovejoy.views.fragment.BotNavFriendsFragment;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuInfo;
import com.special.ResideMenu.ResideMenuItem;


public class MainActivity extends FragmentActivity implements
		View.OnClickListener,OnCheckedChangeListener {

	public ResideMenu resideMenu;
	private ResideMenuItem itemPerson;
	private ResideMenuItem itemCredit;
	private ResideMenuItem itemCircle;
	private ResideMenuItem itemHistory;
	private ResideMenuItem itemSetting;
	private ResideMenuInfo info;
    private RadioGroup rg;
    private RadioButton rb1,rb2,rb3;
	private boolean is_closed = false;
	private long mExitTime;

	/**
	 * Called when the activity is first created.
	 */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setUpMenu();
		//changeFragment(new BotNavDynamicFragment());
		setListener();
	}

	private void setUpMenu() {
		rg=(RadioGroup)findViewById(R.id.rg);
		rb1=(RadioButton)findViewById(R.id.rb1);
		rb2=(RadioButton)findViewById(R.id.rb2);
		rb3=(RadioButton)findViewById(R.id.rb3);

		rg.setOnCheckedChangeListener(this);
		rb1.setChecked(true);

		// attach to current activity;
		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(R.drawable.menu_background);
		resideMenu.attachToActivity(this);
		resideMenu.setMenuListener(menuListener);

		// valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
		resideMenu.setScaleValue(0.7f);
		// 禁止使用右侧菜单
		resideMenu.setDirectionDisable(ResideMenu.DIRECTION_RIGHT);

		// create menu items;
		itemPerson = new ResideMenuItem(this, "个人中心");
		itemCredit = new ResideMenuItem(this, "信用积分");
		itemCircle = new ResideMenuItem(this, "关注圈子");
		itemHistory = new ResideMenuItem(this, "历史记录");
		itemSetting = new ResideMenuItem(this, "设置");

		resideMenu.addMenuItem(itemPerson, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemCredit, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemCircle, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemHistory, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemSetting, ResideMenu.DIRECTION_LEFT);

		info = new ResideMenuInfo(this, R.drawable.icon_profile, "Lovejoy", "14301000");
	}

	private void setListener() {
		resideMenu.addMenuInfo(info);

        itemPerson.setOnClickListener(this);
        itemCredit.setOnClickListener(this);
        itemCircle.setOnClickListener(this);
        itemHistory.setOnClickListener(this);
        itemSetting.setOnClickListener(this);

		info.setOnClickListener(this);
	}
	
	
	//点击按钮显示左边侧滑栏
	public void onClickLiftMenu(View v) {

        resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
	}
    
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {

        return resideMenu.dispatchTouchEvent(ev);
	}

	@Override
	public void onClick(View view) {

		if (view == itemPerson) {
//			Intent intent = new Intent();
//			intent.putExtra("flog", "个人中心");
//			intent.setClass(getApplicationContext(), SideMenuShowActivity.class);
            Intent intent = new Intent(this,ChangeInformationActivity.class);
			startActivity(intent);
		} else if (view == itemCredit) {
			Intent intent = new Intent();
			intent.putExtra("flog", "信用积分");
			intent.setClass(getApplicationContext(), SideMenuShowActivity.class);
			startActivity(intent);
		} else if (view == itemCircle) {
//            Intent intent = new Intent();
//            intent.putExtra("flog", "已关注的圈子");
//            intent.setClass(getApplicationContext(), SideMenuShowActivity.class);
            Intent intent = new Intent(this,AttentionActivity.class);
            startActivity(intent);
        } else if (view == itemHistory) {
			Intent intent = new Intent();
			intent.putExtra("flog", "历史记录");
			intent.setClass(getApplicationContext(), SideMenuShowActivity.class);
			startActivity(intent);
		} else if (view == itemSetting) {
			Intent intent = new Intent();
			intent.putExtra("flog", "设置");
			intent.setClass(getApplicationContext(), SideMenuShowActivity.class);
			startActivity(intent);
		} else if (view == info) {
			Intent intent = new Intent();
			intent.putExtra("flog", "头像");
			intent.setClass(getApplicationContext(), SideMenuShowActivity.class);
			startActivity(intent);
		}
	}

	private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
		@Override
		public void openMenu() {
			is_closed = false;
			//leftMenu.setVisibility(View.GONE);
		}

		@Override
		public void closeMenu() {
			is_closed = true;
			//leftMenu.setVisibility(View.VISIBLE);
		}
	};

	private void changeFragment(Fragment targetFragment) {
		//resideMenu.clearIgnoredViewList();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.main_fragment, targetFragment, "fragment")
				.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.commit();
	}

	// What good method is to access resideMenu？
	public ResideMenu getResideMenu() {

        return resideMenu;
	}

	// 监听手机上的BACK键
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 判断菜单是否关闭
			if (is_closed) {
				// 判断两次点击的时间间隔（默认设置为2秒）
				if ((System.currentTimeMillis() - mExitTime) > 2000) {
					Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
					mExitTime = System.currentTimeMillis();
				} else {
					finish();
					System.exit(0);
					super.onBackPressed();
				}
			} else {
				resideMenu.closeMenu();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int checkedId) {
		
		if (rb1.getId() == checkedId) {
			changeFragment(new BotNavDynamicFragment());
		} else if (rb2.getId() == checkedId) {
			changeFragment(new BotNavCircleFragment());
		} else if (rb3.getId() == checkedId) {
			changeFragment(new BotNavFriendsFragment());
		}
	}
}
