package com.lovejoy.fragment;

import com.lovejoy.activity.R;
import com.lovejoy.adapter.CircleAdapter;
import com.lovejoy.entity.CircleIcon;

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


public class TitleSportFragment extends Fragment {

    private GridView sport_gridView;
    private BaseAdapter mAdapter = null;
    private ArrayList<CircleIcon> mData = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_title_sport, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sport_gridView = (GridView) getActivity().findViewById(R.id.sport_grid_view);

        mData = new ArrayList<CircleIcon>();
        mData.add(new CircleIcon(R.drawable.sport_icon_01_pink, "篮球"));
        mData.add(new CircleIcon(R.drawable.sport_icon_02_pink, "足球"));
        mData.add(new CircleIcon(R.drawable.sport_icon_03_pink, "网球"));
        mData.add(new CircleIcon(R.drawable.sport_icon_04_pink, "跑步"));
        mData.add(new CircleIcon(R.drawable.sport_icon_05_pink, "攀岩"));
        mData.add(new CircleIcon(R.drawable.sport_icon_06_pink, "自行车"));
        mData.add(new CircleIcon(R.drawable.sport_icon_07_pink, "乒乓球"));
        mData.add(new CircleIcon(R.drawable.sport_icon_08_pink, "健身"));
        mData.add(new CircleIcon(R.drawable.sport_icon_09_pink, "羽毛球"));
        mData.add(new CircleIcon(R.drawable.sport_icon_10_pink, "游泳"));
        mData.add(new CircleIcon(R.drawable.sport_icon_11_pink, "棒球"));
        mData.add(new CircleIcon(R.drawable.sport_icon_12_pink, "太极拳"));

        mAdapter = new CircleAdapter<CircleIcon>(mData, R.layout.fragment_title_sport_item) {
            @Override
            public void bindView(ViewHolder holder, CircleIcon obj) {
                holder.setImageResource(R.id.sport_item_icon, obj.getiId());
                holder.setText(R.id.sport_item_text, obj.getiName());
            }
        };

        sport_gridView.setAdapter(mAdapter);

        sport_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int p = position + 1;
                Toast.makeText(getContext(), "你点击了第" + p + "项", Toast.LENGTH_SHORT).show();
            }
        });

    }
}