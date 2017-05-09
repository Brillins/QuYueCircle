package com.lovejoy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class Register1Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register1);
	}

	public void onClick1(View v)
	{
		this.finish();
		this.overridePendingTransition(R.anim.activity_move_in, R.anim.activity_move_out);
		
	}
	
}
