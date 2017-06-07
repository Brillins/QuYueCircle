package com.lovejoy.views.fragment;

import com.lovejoy.views.activity.CircleListActivity;
import com.lovejoy.views.activity.R;
import com.lovejoy.views.adapter.CircleAdapter;
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


public class CircleTopSportFragment extends Fragment {

    private GridView sport_gridView;
    private BaseAdapter mAdapter = null;
    private ArrayList<CircleIcon> mData = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_circle_top_sport, container, false);

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

        mAdapter = new CircleAdapter<CircleIcon>(mData, R.layout.fragment_circle_top_sport_item) {
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
                String circleTitle = null;
                Toast.makeText(getContext(), "你点击了第" + p + "项", Toast.LENGTH_SHORT).show();

                switch (p){
                    case 1:
                        circleTitle = "篮球";
                        break;
                    case 2:
                        circleTitle = "足球";
                        break;
                    case 3:
                        circleTitle = "网球";
                        break;
                    case 4:
                        circleTitle = "跑步";
                        break;
                    case 5:
                        circleTitle = "攀岩";
                        break;
                    case 6:
                        circleTitle = "自行车";
                        break;
                    case 7:
                        circleTitle = "乒乓球";
                        break;
                    case 8:
                        circleTitle = "健身";
                        break;
                    case 9:
                        circleTitle = "羽毛球";
                        break;
                    case 10:
                        circleTitle = "游泳";
                        break;
                    case 11:
                        circleTitle = "棒球";
                        break;
                    case 12:
                        circleTitle = "太极拳";
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