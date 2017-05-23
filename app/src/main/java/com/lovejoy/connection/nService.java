package com.lovejoy.connection;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import net.sf.json.JSONObject;


public class nService extends Service {
    PostRequests pRequest=new PostRequests();
    private invitationThread iThread;
    private descriptionThread dThread;
    private AsyncHttpClient client=new AsyncHttpClient();
    public String user;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        System.out.println("oncreate()");
        this.iThread = new invitationThread();
        this.iThread.start();
        this.dThread=new descriptionThread();
        this.dThread.start();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.user=intent.getStringExtra("user");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {

    }



    private class invitationThread extends Thread {

        @Override
        public void run() {
            boolean flag=true;
            String url = "http://192.168.1.111:5000/invitation/"+user;
            while (flag) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                client.post(url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject message = new JSONObject();
                        message= JSONObject.fromObject(response.toString());
                        if (message.getString("status").equals("0")){
                        Log.v("mylog", "队列空");}
                        else{
                            Log.v("mylog",message.getString("friend_id"));
                            //notification();
                        }
                    }

                });

            }
        }
    }

    private class descriptionThread extends Thread {

        @Override
        public void run() {
            boolean flag=true;
            String url = "http://192.168.1.111:5000/changedescription/"+user;
            while (flag) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                client.post(url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject message = new JSONObject();
                        message= JSONObject.fromObject(response.toString());
                        if (message.getString("status").equals("0")){
                            Log.v("mylog", "队列空");
                            return;
                            }
                        else{
                            Log.v("mylog",message.getString("activity_id"));
                            //notification();

                        }

                    }

                });

            }
        }
    }


    private class comingThread extends Thread {

        @Override
        public void run() {
            boolean flag=true;
            String url = "http://192.168.1.111:5000/invitation/"+user;
            while (flag) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                client.post(url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject message = new JSONObject();
                        message= JSONObject.fromObject(response.toString());
                        if (message.getString("status").equals("0")){
                            Log.v("mylog", "队列空");
                        return;}
                        else{


                        }
                    }

                    @Override
                    public void onFailure(Throwable error) {
                        Toast.makeText(getApplicationContext(),
                                "网络连接出错", Toast.LENGTH_LONG).show();
                    }


                });

            }
        }
    }



//    private void notification() {
//        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        Notification baseNF = new Notification();
//        baseNF.tickerText = "You clicked BaseNF!";
//        nm.notify(1, baseNF);
//        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
//        mBuilder.setContentTitle("测试标题");
//        mBuilder.setContentText("测试内容");
//        mBuilder.setTicker("测试通知来啦");
//        mBuilder.setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL));;
//        mNotificationManager.notify(0, mBuilder.build());
//    }
//
//    public PendingIntent getDefalutIntent(int flags){
//        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//        PendingIntent pendingIntent= PendingIntent.getActivity(getApplicationContext(), 1, intent, flags);
//        return pendingIntent;
//    }
}