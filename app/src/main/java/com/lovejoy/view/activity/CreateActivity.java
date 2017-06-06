package com.lovejoy.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class CreateActivity extends Activity {

	private Spinner B_circle;
	private Spinner S_circle;

	private Button button1, button2;
	private ImageView imageView1;
	private final int IMAGE_RESULT_CODE = 2;// 表示打开照相机
	private final int PICK = 1;// 选择图片库

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create);

		//--------------------两个下拉框之间的关联-------------------
		B_circle=(Spinner)this.findViewById(R.id.Bcircle);
		S_circle=(Spinner) this.findViewById(R.id.Scircle);
		// 从资源数组文件中获取数据
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.B_circle, android.R.layout.simple_spinner_item);
		// 设置下拉列表的风格
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 将数据绑定到Spinner视图上
		B_circle.setAdapter(adapter);
		B_circle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
									   int position, long id) {
				// parent既是Bcircle对象
				Spinner spinner = (Spinner) parent;
				String Bc = (String) spinner.getItemAtPosition(position);
				// 处理省的市的显示，将默认值与ArrayAdapter连接
				ArrayAdapter<CharSequence> ScAdapter=ArrayAdapter.createFromResource(
						CreateActivity.this, R.array.Sport,
						android.R.layout.simple_spinner_item);;
				// 获取所在省含有哪些市(从资源数组文件中获取数据)
				if (Bc.equals("体育")) {
					ScAdapter = ArrayAdapter.createFromResource(
							CreateActivity.this, R.array.Sport,
							android.R.layout.simple_spinner_item);
				} else if (Bc.equals("其他")) {
					ScAdapter = ArrayAdapter.createFromResource(
							CreateActivity.this, R.array.Other,
							android.R.layout.simple_spinner_item);
				}
				ScAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				// 绑定数据到Spinner(City)上
				S_circle.setAdapter(ScAdapter);
				S_circle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
											   View view, int position, long id) {
						Spinner spinner = (Spinner) parent;
						String Sc = (String) spinner
								.getItemAtPosition(position);

					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub

					}
				});
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}

		});

		//-------------------------实现上传照片-------------------
		// button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.Photo);

		imageView1 = (ImageView) findViewById(R.id.image1);
        /*
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                // 使用意图 直接调用安装在手机上的照相机
                // 直接开发Camera硬件
                Intent intent = new Intent(
                        android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, IMAGE_RESULT_CODE);// 打开照相机
            }
        });
        */

		button2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, PICK);// 打开照相机
			}
		});

	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
			// 表示 调用照相机拍照
			case IMAGE_RESULT_CODE:
				if (resultCode == RESULT_OK) {
					Bundle bundle = data.getExtras();
					Bitmap bitmap = (Bitmap) bundle.get("data");
					imageView1.setImageBitmap(bitmap);
				}
				break;
			// 选择图片库的图片
			case PICK:
				if (resultCode == RESULT_OK) {
					Uri uri = data.getData();
					imageView1.setImageURI(uri);
				}
				break;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		return true;
	}

	public void onClick3(View v) {
		this.finish();
		this.overridePendingTransition(R.anim.activity_move_in, R.anim.activity_move_out);
		
	}

}
