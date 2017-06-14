package com.lovejoy.views.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class AttentionActivity extends Activity {

    private ListView attentionListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention_circle);

        attentionListView = (ListView) findViewById(R.id.attention_circle_list_view);


    }

    public void onClickAttention(View v) {
        this.finish();
        this.overridePendingTransition(R.anim.activity_move_in, R.anim.activity_move_out);
    }
}
