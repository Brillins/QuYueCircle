package com.lovejoy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lovejoy.connection.PostRequests;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends Activity {
	private EditText phonetext = null;
	private EditText emailtext = null;
	private EditText schooltext =null;
	private EditText studentidtext= null;
	private EditText usernametext=null;
	private EditText passwordtext=null;
	private EditText cpasswordtext=null;
	private RadioGroup radgroup=null;

	private String username = null;
	private String password = null;
	private String school=null;
	private String studentid=null;
	private String email=null;
	public String sex=null;
	private String phone=null;
	Button registerButton=null;
	PostRequests pRequest = null;
	JSONObject obj1=null;
	JSONObject obj2=null;
	Handler handler=null;
	protected void formobj(){

	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		phonetext=(EditText)findViewById(R.id.phonenumber);
		emailtext=(EditText)findViewById(R.id.register_email);
		schooltext=(EditText)findViewById(R.id.university);
		studentidtext=(EditText)findViewById(R.id.studentId);
		usernametext=(EditText)findViewById(R.id.student_name);
		passwordtext=(EditText)findViewById(R.id.register_password);
		cpasswordtext=(EditText)findViewById(R.id.password_confirm);
		radgroup=(RadioGroup)findViewById(R.id.sexGroup);
		registerButton = (Button)findViewById(R.id.email_register_button);

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				JSONObject json = (JSONObject)msg.obj;
				if(json.get("userid").toString().equals("-1")){
					Toast.makeText(RegisterActivity.this,json.get("errcode").toString(),Toast.LENGTH_LONG).show();
				}
				else{
					Toast.makeText(RegisterActivity.this,json.get("userid").toString(),Toast.LENGTH_LONG).show();
					//
				}

			};};


		registerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				username = usernametext.getText().toString();
				password = passwordtext.getText().toString();
				school=schooltext.getText().toString();
				studentid=studentidtext.getText().toString();
				email=emailtext.getText().toString();
				for (int i = 0; i < radgroup.getChildCount(); i++) {
					RadioButton rd = (RadioButton) radgroup.getChildAt(i);
					if (rd.isChecked()) {
						if(i==0)
							sex="m";
						else
							sex="f";
					}
				}
				phone=phonetext.getText().toString();


				if(checkinfo()==true){
					Thread registerThread = new Thread(new Runnable() {
						@Override
						public void run() {

							Message message=Message.obtain();
							Map<String,String> map = new HashMap<String,String>();
							map.put("name",username);
							map.put("password",password);
							map.put("mail",email);
							map.put("stu_id",studentid);
							map.put("college",school);
							map.put("profession","学生");
							map.put("sex",sex);
							map.put("birthdate","1995-01-01");
							JSONObject obj = JSONObject.fromObject(map);
							obj2 = pRequest.sendPost("register","",obj);
							message.obj=obj2;
							handler.sendMessage(message);
						}
					});
					registerThread.start();}
				else{
					Toast.makeText(RegisterActivity.this,"请检查输入项",Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	private boolean checkinfo(){

		String format = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}";
		if(email.matches(format))
			return false;
		if(passwordtext.getText().toString().equals(cpasswordtext.getText().toString()))
			return false;
		if(phone.length()!=12)
			return false;
		if(sex==null)
			return false;
		if(studentid.length()>16)
			return false;
		if(school==null)
			return false;

		return true;
	}
	public void onClick1(View v)
	{
		this.finish();
		this.overridePendingTransition(R.anim.activity_move_in, R.anim.activity_move_out);

	}

}
