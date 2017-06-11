package com.lovejoy.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import net.sf.json.JSONObject;

import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class PostRequests {
    private AsyncHttpClient client=new AsyncHttpClient();
	 static  String BA_URL = "http://192.168.1.113:5000";

	public void sendPost(String route, String data, JSONObject obj, Handler handler,Context context){
		JSONObject message = new JSONObject();
		boolean flag=false;
        String ADD_URL=BA_URL+route+data;
       internetthread iThread = new internetthread(handler,ADD_URL,obj,context);
 iThread.start();

	}

    public void getpic(String route, String data, Handler handler,Context context) {
        JSONObject message = new JSONObject();
        boolean flag = false;
        String ADD_URL = BA_URL + route + data;
        picturethread pThread = new picturethread(handler, ADD_URL, context);
        pThread.start();
    }



    public static void upload(final Context cont,Bitmap photodata,final Handler handler) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            //将bitmap一字节流输出 Bitmap.CompressFormat.PNG 压缩格式，100：压缩率，baos：字节流
            photodata.compress(Bitmap.CompressFormat.PNG, 100, baos);
            baos.close();
            byte[] buffer = baos.toByteArray();
            System.out.println("图片的大小："+buffer.length);

            //将图片的字节流数据加密成base64字符输出
            //String photo = Base64.encodeToString(buffer, 0, buffer.length,Base64.DEFAULT);
            String photo = new String(Base64.encode(baos.toByteArray(),0));
            //photo=URLEncoder.encode(photo,"UTF-8");
            RequestParams params = new RequestParams();
            params.put("photo", photo);
            params.put("size", Integer.toString(buffer.length));//传输的字符数据
            String url = BA_URL+"/upload_pic";


            AsyncHttpClient client = new AsyncHttpClient();
            client.post(url, params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(org.json.JSONObject response) {

                    response.toString();
                    JSONObject message = new JSONObject();
                    message= JSONObject.fromObject(response.toString());

                        int pic=message.getInt("pic_id");
                        Message message1 = Message.obtain();
                        message1.obj = pic;
                        handler.sendMessage(message1);}


                @Override
                public void onFailure(Throwable e, org.json.JSONObject errorResponse) {
                    Message message1 = Message.obtain();
                    message1.obj = -1;
                    handler.sendMessage(message1);
                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    private class picturethread extends Thread{

        Handler mhandler;
        String ADD_URL;
        JSONObject obj=null;
        Context context;
        public picturethread(Handler handler,String url,Context context){
            this.mhandler=handler;
            this.ADD_URL=url;
            this.context=context;
        }
        @Override
        public void run() {
            String[] allowedContentTypes = new String[] { "image/png", "image/jpeg" };

            client.get(ADD_URL, new BinaryHttpResponseHandler(allowedContentTypes) {

                @Override
                public void onSuccess(byte[] binaryData) {

                    Log.e("binaryData:", "共下载了：" + binaryData.length);
                    Bitmap bmp = BitmapFactory.decodeByteArray(binaryData, 0, binaryData.length);
                    Message message1 = Message.obtain();
                    message1.obj = bmp;
                    mhandler.sendMessage(message1);

}
                        @Override
                        public void onFailure(Throwable error, byte[] binaryData) {
                            Message message1 = Message.obtain();
                            message1.obj = null;
                            mhandler.sendMessage(message1);}

                    }

            );

        }}

    private class internetthread extends Thread {
        Handler mhandler;
        String ADD_URL;
        JSONObject obj=null;
        Context context;
        public internetthread(Handler handler,String url,JSONObject obj,Context context){
            this.mhandler=handler;
            this.ADD_URL=url;
            this.obj=obj;
            this.context=context;
        }
        @Override
        public void run() {

            ByteArrayEntity entity = null;
            try {
                entity = new ByteArrayEntity(obj.toString().getBytes("UTF-8"));
                entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            client.post(context,ADD_URL,entity,"application/json",new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(org.json.JSONObject response) {

                    response.toString();
                    JSONObject message = new JSONObject();
                    message= JSONObject.fromObject(response.toString());
                    if (message.getString("status").equals("0")) {

                    }
                    else{
                        Message message1 = Message.obtain();
                        message1.obj = response.toString();
                        mhandler.sendMessage(message1);}
                }

                @Override
                public void onFailure(Throwable e, org.json.JSONObject errorResponse) {
                    Message message1 = Message.obtain();
                    message1.obj = null;
                    mhandler.sendMessage(message1);
                }

            });
        }
    }
}