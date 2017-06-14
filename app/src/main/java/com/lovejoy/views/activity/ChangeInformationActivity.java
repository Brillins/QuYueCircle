package com.lovejoy.views.activity;

import android.Manifest;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lovejoy.api.PostRequests;
import com.lovejoy.core.MDFonts;
import com.lovejoy.core.MDFontsUtils;
import com.lovejoy.views.view.RoundImageView;

import net.sf.json.JSONObject;

import org.w3c.dom.Text;


/**
 * 主界面
 */
public class ChangeInformationActivity extends AppCompatActivity implements View.OnClickListener {
    //请求相机
    int userid=22;
    Handler handler=null;
    Handler changehandler=null;
    PostRequests pRequest = null;
    Handler handler2=null;
    JSONObject obj=null;
    Context context=this;
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    //头像2
    //private ImageView headImage2;
    //调用照相机返回图片临时文件
    private File tempFile;
    // 1: qq, 2: weixin
    private int type;
    private Button phone_change_button;
    private Button password_change_button;
    private TextView telephone_text_view;
    private TextView mail_text_view;
    private TextView school_text_view;
    private TextView username_text_view;
    private TextView student_num_text_view;
    //private List<ActivityBriefInfor> activityList=new ArrayList<ActivityBriefInfor>();
    //private ListView historyListView;

    private TextView textView_personal;
    private TextView textView_history;
    private TextView textView_reputation;

    private TextView textView_email_ui;
    private TextView textView_telephone_ui;
    private TextView textView_password_ui;
    private TextView textView_school_ui;
    private TextView textView_student_num_ui;


    private RoundImageView headImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal);

        phone_change_button=(Button) findViewById(R.id.button_user_telephone_change);
        password_change_button=(Button) findViewById(R.id.button_user_password_change);

        telephone_text_view=(TextView) findViewById(R.id.text_view_user_telephone);
        telephone_text_view.setTypeface(MDFontsUtils.getTypeface(this,"fonts/Roboto-Italic.ttf"));
        mail_text_view=(TextView) findViewById(R.id.text_view_user_account);
        mail_text_view.setTypeface(MDFontsUtils.getTypeface(this,"fonts/Roboto-Italic.ttf"));
        school_text_view=(TextView) findViewById(R.id.text_view_user_school);
        school_text_view.setTypeface(MDFontsUtils.getTypeface(this,"fonts/Roboto-Italic.ttf"));
        username_text_view=(TextView)findViewById(R.id.text_view_user_name) ;
        username_text_view.setTypeface(MDFontsUtils.getTypeface(this,"fonts/Roboto-Italic.ttf"));
        student_num_text_view=(TextView) findViewById(R.id.text_view_user_student_number);
        student_num_text_view.setTypeface(MDFontsUtils.getTypeface(this,"fonts/Roboto-Italic.ttf"));

        textView_personal=(TextView)findViewById(R.id.text_view_personal) ;
        textView_personal.setTypeface(MDFontsUtils.getTypeface(this,"fonts/Roboto-Italic.ttf"));
        /*
        textView_history=(TextView)findViewById(R.id.text_view_history) ;
        textView_history.setTypeface(MDFontsUtils.getTypeface(this,"fonts/Roboto-Italic.ttf"));
        textView_reputation=(TextView)findViewById(R.id.text_view_reputation) ;
        textView_reputation.setTypeface(MDFontsUtils.getTypeface(this,"fonts/Roboto-Italic.ttf"));
        */


        textView_email_ui=(TextView)findViewById(R.id.text_view_email_ui);
        textView_email_ui.setText(MDFonts.ENVELOPE);
        MDFontsUtils.setOcticons(textView_email_ui);

        textView_telephone_ui=(TextView)findViewById(R.id.text_view_telephone_ui);
        textView_telephone_ui.setText(MDFonts.PHONE);
        MDFontsUtils.setOcticons(textView_telephone_ui);

        textView_password_ui=(TextView)findViewById(R.id.text_view_password_ui);
        textView_password_ui.setText(MDFonts.PASSWORD);
        textView_password_ui.setTypeface(MDFontsUtils.getTypeface(this,"fonts/icomoon1.ttf"));

        textView_school_ui=(TextView)findViewById(R.id.text_view_school_ui);
        textView_school_ui.setText(MDFonts.SCHOOL);
        textView_school_ui.setTypeface(MDFontsUtils.getTypeface(this,"fonts/icomoon1.ttf"));

        textView_student_num_ui=(TextView)findViewById(R.id.text_view_student_num_ui);
        textView_student_num_ui.setText(MDFonts.STUDENT_NUM);
        textView_student_num_ui.setTypeface(MDFontsUtils.getTypeface(this,"fonts/icomoon1.ttf"));

        headImage = (RoundImageView) findViewById(R.id.image_view_user_head);
        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.headimage);
        //headImage.setBitmap(bitmap);

        pRequest=new PostRequests();
        handler2=new Handler(){
            public void handleMessage(android.os.Message msg) {
                if (msg!=null) {
                    if (msg.obj!=null){
                        Bitmap bmp = (Bitmap) msg.obj;
                        //headImage.setBitmap(bmp);
                        headImage.setImageBitmap(bmp);
                    }
                    else{
                        Toast.makeText(context, "failed to get data", Toast.LENGTH_LONG).show();
                    }
                }
            };
        };
        handler=new Handler(){
            public void handleMessage(android.os.Message msg) {
                if (msg!=null) {
                    if (msg.obj!=null){
                        Object result = msg.obj;
                        JSONObject js2=JSONObject.fromObject(result);
                        username_text_view.setText(js2.getString("name"));
                        telephone_text_view.setText(Integer.toString(js2.getInt("phone")));
                        mail_text_view.setText(js2.getString("mail"));
                        school_text_view.setText(js2.getString("college"));
                        student_num_text_view.setText(js2.getString("stu_id"));
                        int ava=js2.getInt("ava");
                        pRequest.getpic("/get_pic", "/"+Integer.toString(ava),handler2,context);
                    }
                    else{
                        Toast.makeText(context, "failed to get data", Toast.LENGTH_LONG).show();
                    }
                }
            };
        };
        changehandler=new Handler(){
            public void handleMessage(android.os.Message msg) {
                if (msg!=null) {
                    if (msg.obj!=null){
                        pRequest.sendPost("/get_user_info", "/"+Integer.toString(userid),obj,handler,context);
                    }
                    else{
                        Toast.makeText(context, "failed to change data", Toast.LENGTH_LONG).show();
                    }
                }
            };
        };

        obj=new JSONObject();
        pRequest.sendPost("/get_user_info", "/"+Integer.toString(userid),obj,handler,context);

        headImage.setOnClickListener(this);
        phone_change_button.setOnClickListener(this);
        password_change_button.setOnClickListener(this);
        createCameraTempFile(savedInstanceState);
    }

    /**
     * 外部存储权限申请返回
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                gotoCarema();
            } else {
                // Permission Denied
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                gotoPhoto();
            } else {
                // Permission Denied
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_user_telephone_change:
                uploadTelephone(v);
                break;
            case R.id.image_view_user_head:
                type = 2;
                uploadHeadImage();
                break;
            case R.id.button_user_password_change:
                uploadPassword();
                break;
        }
    }

    /**
     * 弹出软键盘
     */

    /**
     * 修改手机号
     */
    private void uploadTelephone(View v){
        View view = LayoutInflater.from(this).inflate(R.layout.telephone_popupwindow, null);
        final EditText newTelephone = (EditText) view.findViewById(R.id.edit_text_view_new_telephone);
        Button configNewTelephone=(Button) view.findViewById(R.id.button_config_new_telephone);
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        View parent = LayoutInflater.from(this).inflate(R.layout.personal, null);

        /*
        int[] location = new int[2];
        v.getLocationOnScreen(location);//得到相对当前控件的坐标位置

        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0], location[1]-popupWindow.getHeight());
        */

        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);

        //popupWindow在弹窗的时候背景半透明
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                getWindow().setAttributes(params);
            }
        });

        configNewTelephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_phone_num=newTelephone.getText().toString();
                JSONObject obj=new JSONObject();
                Map map=new HashMap();
                map.put("phone",new_phone_num);
                obj=JSONObject.fromObject(map);
                pRequest.sendPost("/change_my_info","/"+Integer.toString(userid),obj,changehandler,context);
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 修改密码
     */
    private void uploadPassword(){
        View view = LayoutInflater.from(this).inflate(R.layout.password_popupwindow, null);
        EditText oldPassword = (EditText) view.findViewById(R.id.edit_text_view_old_password);
        EditText newPassword = (EditText) view.findViewById(R.id.edit_text_view_new_password);
        EditText configNewPassword = (EditText) view.findViewById(R.id.edit_text_view_config_new_password);
        Button configNewPasswordButton=(Button) view.findViewById(R.id.button_config_new_password);

        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        View parent = LayoutInflater.from(this).inflate(R.layout.personal, null);
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        //popupWindow在弹窗的时候背景半透明
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                getWindow().setAttributes(params);
            }
        });

        oldPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //popupWindow.dismiss();
            }
        });
        newPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //popupWindow.dismiss();
            }
        });
        configNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //popupWindow.dismiss();
            }
        });

        configNewPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

    }

    /**
     * 上传头像
     */
    private void uploadHeadImage() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow, null);
        TextView btnCarema = (TextView) view.findViewById(R.id.btn_camera);
        TextView btnPhoto = (TextView) view.findViewById(R.id.btn_photo);
        TextView btnCancel = (TextView) view.findViewById(R.id.btn_cancel);
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        View parent = LayoutInflater.from(this).inflate(R.layout.personal, null);
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        //popupWindow在弹窗的时候背景半透明
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                getWindow().setAttributes(params);
            }
        });

        btnCarema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if (ContextCompat.checkSelfPermission(ChangeInformationActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(ChangeInformationActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到调用系统相机
                    gotoCarema();
                }
                popupWindow.dismiss();
            }
        });

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if (ContextCompat.checkSelfPermission(ChangeInformationActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请READ_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(ChangeInformationActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到调用系统图库
                    gotoPhoto();
                }
                popupWindow.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }

    /**
     * 跳转到照相机
     */
    private void gotoCarema() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        startActivityForResult(intent, REQUEST_CAPTURE);
    }

    /**
     * 创建调用系统照相机待存储的临时文件
     *
     * @param savedInstanceState
     */
    private void createCameraTempFile(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("tempFile")) {
            tempFile = (File) savedInstanceState.getSerializable("tempFile");
        } else {
            tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"),
                    System.currentTimeMillis() + ".jpg");
        }
    }

    /**
     * 检查文件是否存在
     */
    private static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("tempFile", tempFile);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    final Uri uri = intent.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
                    if (type == 2) {
                        headImage.setImageBitmap(bitMap);
                    }
                    //此处后面可以将bitMap转为二进制上传后台网络
                    //......

                }
                break;
        }
    }

    /**
     * 打开截图界面
     *
     * @param uri
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.putExtra("type", type);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }

    /**
     * 根据Uri返回文件绝对路径
     * 兼容了file:///开头的 和 content://开头的情况
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }


}
