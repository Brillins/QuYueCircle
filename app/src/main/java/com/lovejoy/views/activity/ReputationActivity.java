package com.lovejoy.views.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lovejoy.api.PostRequests;
import com.lovejoy.core.MDFontsUtils;
import com.lovejoy.model.ReputationItem;
import com.lovejoy.views.adapter.ReputationAdapter;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/6/13.
 */
public class ReputationActivity extends AppCompatActivity{

    private List<ReputationItem> reputationList=new ArrayList<ReputationItem>();
    private TextView reputation_textview;
    private TextView reputation_degree_textview;
    private TextView reputation_grade_textview;
    PostRequests pr=null;
    Context context=this;
    Handler handler=null;
    String result[][];
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reputation);

        reputation_textview=(TextView)findViewById(R.id.text_view_reputation);
        reputation_textview.setTypeface(MDFontsUtils.getTypeface(this,"fonts/Roboto-Italic.ttf"));

        reputation_degree_textview=(TextView)findViewById(R.id.text_view_reputation_degree);
        reputation_degree_textview.setTypeface(MDFontsUtils.getTypeface(this,"fonts/Roboto-Italic.ttf"));

        reputation_grade_textview=(TextView)findViewById(R.id.text_view_reputation_grade);
        reputation_grade_textview.setTypeface(MDFontsUtils.getTypeface(this,"fonts/Roboto-Italic.ttf"));

        initReputationItem();
        final ReputationAdapter adapter=new ReputationAdapter(ReputationActivity.this,R.layout.reputation_item,reputationList);
        ListView listView=(ListView) findViewById(R.id.list_view_reputation);
        listView.setAdapter(adapter);
        pr=new PostRequests();
        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ReputationItem reputationItem=reputationList.get(position);
                Toast.makeText(ReputationActivity.this,fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        */
        handler=new Handler(){
            public void handleMessage(android.os.Message msg) {
                if (msg!=null) {
                    if (msg.obj!=null){
                        reputationList.clear();
                        Object result = msg.obj;
                        JSONObject js=JSONObject.fromObject(result);
                            int length=js.getInt("total");
                        String[][]table=new String[length][5];
                        for(int i=0;i<length;i++){
                            JSONArray jr=js.getJSONArray(Integer.toString(i));

                                ReputationItem current=new ReputationItem(jr.getString(1),jr.getString(3),jr.getString(0));
                                reputationList.add(current);

                        }
                        ReputationAdapter madapter=new ReputationAdapter(ReputationActivity.this,R.layout.reputation_item,reputationList);
                        ListView listView=(ListView) findViewById(R.id.list_view_reputation);
                        listView.setAdapter(madapter);
                    }
                    else{
                        Toast.makeText(context, "failed to get data", Toast.LENGTH_LONG).show();
                    }
                }
            }};
        JSONObject obj=new JSONObject();
        pr.sendPost("/get_user_comment_record","/"+Integer.toString(23),obj,handler,this);
    }

    private void initReputationItem(){
        ReputationItem apple=new ReputationItem("Apple","$10","100");
        reputationList.add(apple);

        ReputationItem apple1=new ReputationItem("Apple","$10","100");
        reputationList.add(apple1);

        ReputationItem apple2=new ReputationItem("Apple","$10","100");
        reputationList.add(apple2);

        ReputationItem apple3=new ReputationItem("Apple","$10","100");
        reputationList.add(apple3);

        ReputationItem apple4=new ReputationItem("Apple","$10","100");
        reputationList.add(apple4);

        ReputationItem apple5=new ReputationItem("Apple","$10","100");
        reputationList.add(apple5);

        ReputationItem apple6=new ReputationItem("Apple","$10","100");
        reputationList.add(apple6);

        ReputationItem apple7=new ReputationItem("Apple","$10","100");
        reputationList.add(apple7);

        ReputationItem apple8=new ReputationItem("Apple","$10","100");
        reputationList.add(apple8);




    }

}
