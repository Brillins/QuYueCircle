package com.lovejoy.connection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
 
import net.sf.json.JSONObject;

public class PostRequests {
	private static final String ADD_URL = "http://192.168.1.110:5000/";
	public JSONObject sendPost(String route,String data,JSONObject obj){
		JSONObject message = new JSONObject();
		boolean flag=false;
		try{
		   URL url = new URL(ADD_URL+route+data);
           HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10);
           connection.setDoOutput(true);
           connection.setDoInput(true);
           connection.setRequestMethod("POST");
           connection.setUseCaches(false);
           connection.setInstanceFollowRedirects(true);
           connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

           connection.connect();

           //POST����
           DataOutputStream out = new DataOutputStream(
                   connection.getOutputStream());

           out.writeBytes(obj.toString());
           out.flush();
           out.close();

           //��ȡ��Ӧ
           BufferedReader reader = new BufferedReader(new InputStreamReader(
                   connection.getInputStream()));
           String lines;
           StringBuffer sb = new StringBuffer("");
           while ((lines = reader.readLine()) != null) {
               lines = new String(lines.getBytes(), "utf-8");
               sb.append(lines);
           }           
          
            flag=true;
            message=JSONObject.fromObject(sb.toString());
            reader.close();
           // �Ͽ�����
           connection.disconnect();
       } catch (MalformedURLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       } catch (UnsupportedEncodingException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
		if(flag==false) {
            message.put("errorcode", "connecting fault");
        }
		return message;
	}
}
