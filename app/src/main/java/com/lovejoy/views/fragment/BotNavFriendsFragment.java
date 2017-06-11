package com.lovejoy.views.fragment;

import com.lovejoy.views.activity.R;
import com.lovejoy.views.view.AddPopWindow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class BotNavFriendsFragment extends Fragment {

	private View parentView;
	private ImageView friendIvAdd;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		parentView = inflater.inflate(R.layout.fragment_bot_nav_friend, container, false);

		friendIvAdd = (ImageView) parentView.findViewById(R.id.friend_iv_add);

		// 点击右边显示
		friendIvAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AddPopWindow addPopWindow = new AddPopWindow(getActivity());
				addPopWindow.showPopupWindow(friendIvAdd);
			}

		});

		return parentView;
	}
}
