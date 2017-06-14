package com.lovejoy.views.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class DetailActivity extends Activity {
    private TextView name;
    private TextView desc;
    private TextView sponser;
    private TextView currentNum;
    private TextView minNum;
    private TextView maxNum;
    private TextView startTime;
    private TextView deadLine;
    private TextView address;
    private GridView gridView;
    private Button join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        name=(TextView)this.findViewById(R.id.Name);
        desc=(TextView)this.findViewById(R.id.Desc);
        sponser=(TextView)this.findViewById(R.id.Sponsor);
        currentNum=(TextView)this.findViewById(R.id.CurrentNum);
        minNum=(TextView)this.findViewById(R.id.MinNum);
        maxNum=(TextView)this.findViewById(R.id.MaxNum);
        startTime=(TextView)this.findViewById(R.id.StartTime);
        deadLine=(TextView)this.findViewById(R.id.Deadline);
        address=(TextView)this.findViewById(R.id.Address);
        gridView=(GridView) this.findViewById(R.id.gridview);
        join=(Button) this.findViewById(R.id.Join);

        //点击当前人数框，显示参与该活动的人的信息
        currentNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //点击参与按钮，成功参与活动
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
