package com.lovejoy.views.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.lovejoy.api.PostRequests;
import com.lovejoy.model.ImageTool;

import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateActivity extends Activity {
	private Spinner B_circle;
	private Spinner S_circle;
	private static final int IMG_COUNT=8;
	private static final String IMA_ADD_TAG="a";
	private GridView gridView;
	private GVAdapter adapter;
	private  ImageView ima;
	private List<String> list;//上传图片的绝对路径集合 除了最后一个元素
	private List<Bitmap> L;
	List<Integer> picid;
	private TextView addLabel;
	private List<String> label;//标签集合
	private List<Map<String, Object>> data_list;
	private GridView gridLabel;
	private SimpleAdapter sim_adapter;
	private Button launch;//发布活动按钮
	private EditText NameEdit;
	private EditText Sponsor;
	private EditText DescEdit;
	private EditText StartTimeEdit;
	private EditText DeadlineEdit;
	private EditText MinNumEdit;
	private EditText MaxNumEdit;
	int publisherid=22;
	int groupid=2;
	Context context=this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create);

		B_circle = (Spinner) this.findViewById(R.id.Bcircle);
		S_circle = (Spinner) this.findViewById(R.id.Scircle);
		addLabel=(TextView)this.findViewById(R.id.AddLabel);
		gridLabel=(GridView)this.findViewById(R.id.GridLabel);
		launch=(Button)this.findViewById(R.id.Launch);
		NameEdit=(EditText) this.findViewById(R.id.NameEdit);
		Sponsor=(EditText) this.findViewById(R.id.Sponsor);
		DescEdit=(EditText) this.findViewById(R.id.DescEdit);
		StartTimeEdit=(EditText) this.findViewById(R.id.StartTimeEdit);
		DeadlineEdit=(EditText) this.findViewById(R.id.DeadlineEdit);
		MinNumEdit=(EditText) this.findViewById(R.id.MinNumEdit);
		MaxNumEdit=(EditText) this.findViewById(R.id.MaxNumEdit);


		DeadlineEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					showDatePickDlg();
				}
			}
		});

		StartTimeEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					showDatePickDlg2();
				}
			}
		});
		if(label==null)
			label=new ArrayList<String>();
		if(data_list==null)
			data_list= new ArrayList<Map<String, Object>>();

		//新建适配器（标签的添加）
		String [] from ={"label1"};
		int [] to = {R.id.label1};
		sim_adapter = new SimpleAdapter(this, data_list, R.layout.label,from, to);
		gridLabel.setAdapter(sim_adapter);
		addLabel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LayoutInflater inflater = getLayoutInflater();
				final View layout = inflater.inflate(R.layout.desc_label,
						(ViewGroup) findViewById(R.id.dialog));
				AlertDialog.Builder builder=new AlertDialog.Builder(CreateActivity.this,R.style.dialogTheme);
				builder.setView(layout);
				builder.setPositiveButton("取消",null);
				builder.setNegativeButton("添加", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						EditText lb=(EditText)layout.findViewById(R.id.etname);
						if(lb.getText()!=null){
							String l=lb.getText().toString();
							label.add(l);
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("label1",l);
							data_list.add(map);
							System.out.println("111"+data_list.size());

						}
						else
							finish();
					}
				});
				builder.show();
			}
		});

		//--------------------两个下拉框之间的关联-------------------

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
				// 处理大圈子的显示，将默认值与ArrayAdapter连接
				ArrayAdapter<CharSequence> ScAdapter = ArrayAdapter.createFromResource(
						CreateActivity.this, R.array.Sport,
						android.R.layout.simple_spinner_item);
				;
				// 获取大圈子里有哪些小圈子(从资源数组文件中获取数据)
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
				// 绑定数据到Spinner(小圈子)上
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
		//点击发布按钮，上传活动数据
		launch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


			}
		});


		//上传图片
		gridView = (GridView) findViewById(R.id.gridview);
		SelectPicture();

		launch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String name,publisher,description,start_date,end_date,min_num,max_num,tags;
				name=NameEdit.getText().toString();
				publisher=Sponsor.getText().toString();
				description=DescEdit.getText().toString();
				start_date=StartTimeEdit.getText().toString();
				end_date=DeadlineEdit.getText().toString();
				min_num=MinNumEdit.getText().toString();
				max_num=MaxNumEdit.getText().toString();
				tags="";
				if (label.size()>0){
					tags=label.get(0);
					for(int i=1;i<label.size();i++){
						tags=tags+","+label.get(i);
					}}
				System.out.println(tags);
				Map<String,String> map=new HashMap<>();
				map.put("name",name);
				map.put("publisher",Integer.toString(publisherid));

				map.put("description",description);
				map.put("start_date",start_date);
				map.put("end_date",end_date);
				map.put("min_num",min_num);
				map.put("max_num",max_num);
				map.put("join_ids","");

				map.put("is_canceled",Integer.toString(0));
				map.put("cur_num",Integer.toString(1));
				map.put("pic_id",Integer.toString(14));
				map.put("tags",tags);
				map.put("group_id",Integer.toString(groupid));
				JSONObject obj=new JSONObject();
				obj=JSONObject.fromObject(map);
				PostRequests pr=new PostRequests();
				pr.sendPost("/admit_activity","",obj,handler,context);

			}
		});
	}


	private void SelectPicture() {
		if (list == null) {
			list = new ArrayList<>();
			list.add(IMA_ADD_TAG);
		}
		if(L==null){
			L=new ArrayList<>();

		}
		adapter = new GVAdapter();
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (list.get(position).equals(IMA_ADD_TAG)) {
					if (list.size() < IMG_COUNT) {
						Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
						startActivityForResult(i, 0);
					} else
						Toast.makeText(CreateActivity.this, "最多只能选择7张照片！", Toast.LENGTH_SHORT).show();
				}
			}
		});
		refreshAdapter();
	}


	private void refreshAdapter() {
		if (list == null) {
			list = new ArrayList<>();
		}
		if(L==null){
			L=new ArrayList<>();
		}
		if (adapter == null) {
			adapter = new GVAdapter();
		}
		adapter.notifyDataSetChanged();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data == null) {
			System.out.println("data null");
			return;
		}
		if (requestCode == 0) {
			final Uri uri = data.getData();

			String path = ImageTool.getImageAbsolutePath(this, uri);
			//String path=ImageTool.getRealPathFromURI(this,uri);
			if (list.size() == IMG_COUNT) {
				removeItem();
				refreshAdapter();
				return;
			}
			removeItem();
			list.add(uri.toString());
			L.add(getBitmapFromUri(uri));
			//list.add(path);
			System.out.println("111111111");
			System.out.println(uri);
			list.add(IMA_ADD_TAG);
			refreshAdapter();
		}
	}

	private void removeItem() {
		if (list.size() != IMG_COUNT) {
			if (list.size() != 0) {
				list.remove(list.size() - 1);
			}
		}
	}



	private class GVAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			final ViewHolder holder;
			if (convertView == null) {
				convertView = LayoutInflater.from(getApplication()).inflate(R.layout.activity_add_photo, parent, false);
				holder = new ViewHolder();
				holder.imageView = (ImageView) convertView.findViewById(R.id.main_gridView_item_photo);
				holder.checkBox = (CheckBox) convertView.findViewById(R.id.main_gridView_item_cb);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			String s = list.get(position);
			if (!s.equals(IMA_ADD_TAG)) {
				holder.checkBox.setVisibility(View.VISIBLE);
				//holder.imageView.setImageBitmap(ImageTool.createImageThumbnail(s));
				holder.imageView.setImageURI(Uri.parse(s));
				//holder.imageView.setImageBitmap(getBitmapFromUri(Uri.parse("content://media/external/images/media/41")));
				System.out.println("22222222222222222");
				System.out.println(s);
				//holder.imageView.setImageResource(R.drawable.a1);
				//holder.imageView.setImageURI(Uri.fromFile(new File("/storage/emulated/0/DCIM/Camera/IMG_20170605_101126.jpg")));


			} else {
				holder.checkBox.setVisibility(View.GONE);
				holder.imageView.setImageResource(R.drawable.upload_photo);
			}
			holder.checkBox.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					list.remove(position);
					L.remove(position);
					refreshAdapter();
				}
			});
			return convertView;
		}

		private class ViewHolder {
			ImageView imageView;
			CheckBox checkBox;
		}

	}

	private Bitmap getBitmapFromUri(Uri uri)
	{
		try
		{
			// 读取uri所在的图片
			Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
			return bitmap;
		}
		catch (Exception e)
		{
			Log.e("[Android]", e.getMessage());
			Log.e("[Android]", "目录为：" + uri);
			e.printStackTrace();
			return null;
		}
	}
	Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg){
			if(msg!=null){
				Object result=msg.obj;
				JSONObject robj=JSONObject.fromObject(result);
				int actid=robj.getInt("actid");
				if (actid!=-1){
					//
				}
			}
		}
	};
	public void launch(View v){
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String name,publisher,description,start_date,end_date,min_num,max_num,tags;
		name=NameEdit.getText().toString();
		publisher=Sponsor.getText().toString();
		description=DescEdit.getText().toString();
		start_date=StartTimeEdit.getText().toString();
		end_date=DeadlineEdit.getText().toString();
		min_num=MinNumEdit.getText().toString();
		max_num=MaxNumEdit.getText().toString();
		tags="";
		for(int i=0;i<label.size();i++){
			tags=tags+","+label.get(i);
		}
		System.out.println(tags);
		Map<String,String> map=new HashMap<>();
		map.put("name",name);
		map.put("publisher",Integer.toString(publisherid));

		map.put("description",description);
		map.put("start_date",start_date);
		map.put("end_date",end_date);
		map.put("min_num",min_num);
		map.put("max_num",max_num);
		map.put("join_ids","");

		map.put("is_cancled",Integer.toString(0));
		map.put("cur_num",Integer.toString(1));
		map.put("pic_id",Integer.toString(14));
		map.put("tags",tags);
		map.put("group_id",Integer.toString(groupid));
		JSONObject obj=new JSONObject();
		obj=JSONObject.fromObject(map);
		PostRequests pr=new PostRequests();
		pr.sendPost("/admit_activity","",obj,handler,this);

	}



	protected void showDatePickDlg() {
		Calendar calendar = Calendar.getInstance();

		DatePickerDialog datePickerDialog = new DatePickerDialog(CreateActivity.this, new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				CreateActivity.this.DeadlineEdit.setText(year + "-" + monthOfYear + "-" + dayOfMonth+" "+"12:00:00");
			}
		}, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		datePickerDialog.show();

	}


	protected void showDatePickDlg2() {
		Calendar calendar = Calendar.getInstance();

		DatePickerDialog datePickerDialog = new DatePickerDialog(CreateActivity.this, new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				CreateActivity.this.StartTimeEdit.setText(year + "-" + monthOfYear + "-" + dayOfMonth+" "+"12:00:00");
			}
		}, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
		datePickerDialog.show();

	}

}
