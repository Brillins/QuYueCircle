package com.lovejoy.fragment;

import android.app.Activity;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lovejoy.activity.R;

public class TitleSportFragment1 extends Activity implements View.OnClickListener {

    private ViewGroup mMoreLayout;  //父布局容器(动态加载的资源图片和文字等布局都将添加在其里面)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();   //保证启动方法的唯一性
    }

    private void setupViews() {
        setContentView(R.layout.activity_main);
       // mMoreLayout = (ViewGroup) findViewById(R.id.sport_grid_layout);   //找到该容器（这里的控件为LinearLayout，转换为ViewGroup是因为ViewGroup是容器的基类）

        /**
         * 由于文字也是动态生成，这里用数组比较多余，使用android中array文件定义资源文件，并取出(便于管理，文字和代码分开，日后要修改直接找资源数组文件便可，不用找代码)
         */
        final String[] categories = getResources().getStringArray(R.array.activity_sport);


        final int size = categories.length;     //数组的个数
        final int rowCount = size / 3;          // 需要布局的行数(每行三个)

        /**
         * 动态添加布局方法封装
         * 参数 1.父容器    2.资源文字数组  3.从第几个开始   4.行数
         */
        fillViews(mMoreLayout, categories, 0, rowCount);
    }

    private void fillViews(ViewGroup layout, String[] categories, int start, int end) {
        // 表格第一条线
        View.inflate(this, R.layout.layout_line_horizonal, layout);

        for (int i = start; i < end; i++) {

            //找到索引，便于根据索引添加图片文件和文字
            final int firstIndex = i * 3;
            final int secondIndex = i * 3 + 1;
            final int thirdIndex = i * 3 + 2;

            final String firstCategory = categories[firstIndex];
            final String secondCategory = categories[secondIndex];
            final String thirdCategory = categories[thirdIndex];

            //这里控制的是加载本地图片，通过应用包命找到 有规则命名的图片资源文件（注意：有规则命名的图片文件）
            //--->因为这里有两种效果，一是默认的图片，二是按下触发后的图片和文字，所以这里一个条目要 getResources()两次
            final int firstDrawableNormal = getResources().getIdentifier(String.format("sport_icon_%01d_blue",
                    firstIndex + 1),"drawable",getApplicationContext().getPackageName());
            final int secondDrawableNormal = getResources().getIdentifier(String.format("sport_icon_%01d_blue",
                    secondIndex + 1),"drawable",getApplicationContext().getPackageName());
            final int thirdDrawableNormal = getResources().getIdentifier(String.format("sport_icon_%01d_blue",
                    thirdIndex + 1),"drawable",getApplicationContext().getPackageName());
            final int firstDrawablePressed = getResources().getIdentifier(String.format("sport_icon_%01d_pink",
                    firstIndex + 1),"drawable",getApplicationContext().getPackageName());
            final int secondDrawablePressed = getResources().getIdentifier(String.format("sport_icon_%01d_pink",
                    secondIndex + 1),"drawable",getApplicationContext().getPackageName());
            final int thirdDrawablePressed = getResources().getIdentifier(String.format("sport_icon_%01d_pink",
                    thirdIndex + 1),"drawable",getApplicationContext().getPackageName());

            //这里是将上面找到的   默认图片  和  按下时的图片 放入到  StateListDrawable缓存中
            final StateListDrawable firstDrawable = new StateListDrawable();
            firstDrawable.addState(new int[]{android.R.attr.state_pressed}, getResources().getDrawable(firstDrawablePressed));
            firstDrawable.addState(new int[]{}, getResources().getDrawable(firstDrawableNormal));

            final StateListDrawable secondDrawable = new StateListDrawable();
            secondDrawable.addState(new int[]{android.R.attr.state_pressed}, getResources().getDrawable(secondDrawablePressed));
            secondDrawable.addState(new int[]{}, getResources().getDrawable(secondDrawableNormal));

            final StateListDrawable thirdDrawable = new StateListDrawable();
            thirdDrawable.addState(new int[]{android.R.attr.state_pressed}, getResources().getDrawable(thirdDrawablePressed));
            thirdDrawable.addState(new int[]{}, getResources().getDrawable(thirdDrawableNormal));


            // 父布局
            final LinearLayout linearLayout = new LinearLayout(this);

            // 第一个子布局
            View.inflate(this, R.layout.layout_line_vertical, linearLayout);
            View.inflate(this, R.layout.fragment_title_sport_item, linearLayout);
            View.inflate(this, R.layout.layout_line_vertical, linearLayout);

            // 第二个子布局
            View.inflate(this, R.layout.fragment_title_sport_item, linearLayout);
            View.inflate(this, R.layout.layout_line_vertical, linearLayout);

            // 第三个子布局
            View.inflate(this, R.layout.fragment_title_sport_item, linearLayout);
            View.inflate(this, R.layout.layout_line_vertical, linearLayout);

            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layout.addView(linearLayout, layoutParams);

            // 表格最后一条线
            View.inflate(this, R.layout.layout_line_horizonal, layout);

            //根据索引getChildAt到指定的位置
            final View firstView = linearLayout.getChildAt(1);
            firstView.setTag(firstCategory);        //设置tag，便于在后面判断点击的哪一个
            firstView.setOnClickListener(this);     //设置点击
            final TextView firstTextView = (TextView) firstView.findViewById(R.id.sport_item_text);
            firstTextView.setText(firstCategory);   //设置文字
            final ImageView firstImageView = (ImageView) firstView.findViewById(R.id.sport_item_icon);
            firstImageView.setImageDrawable(firstDrawable); //将之前缓存的图片设置出来

            final View secondView = linearLayout.getChildAt(3);
            secondView.setTag(secondCategory);
            secondView.setOnClickListener(this);
            final TextView secondTextView = (TextView) secondView.findViewById(R.id.sport_item_text);
            secondTextView.setText(secondCategory);
            final ImageView secondImageView = (ImageView) secondView.findViewById(R.id.sport_item_icon);
            secondImageView.setImageDrawable(secondDrawable);

            final View thirdView = linearLayout.getChildAt(5);
            thirdView.setTag(thirdCategory);
            thirdView.setOnClickListener(this);
            final TextView thirdTextView = (TextView) thirdView.findViewById(R.id.sport_item_text);
            thirdTextView.setText(thirdCategory);
            final ImageView thirdImageView = (ImageView) thirdView.findViewById(R.id.sport_item_icon);
            thirdImageView.setImageDrawable(thirdDrawable);

        }
    }


    @Override
    public void onClick(View v) {
        final Object tag = v.getTag();      //通过之前setTag找到点击位置
        if (tag != null) {
            String department = (String) tag;
            Toast.makeText(this, department, Toast.LENGTH_LONG).show();
        } // else ignored
    }
}
