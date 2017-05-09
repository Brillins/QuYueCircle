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
public class jsontest {

    public static final String ADD_URL = "http://192.168.1.107:5000/login";

    public static void appadd() {

        try {
            //��������
            URL url = new URL(ADD_URL);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.connect();

            //POST����
            DataOutputStream out = new DataOutputStream(
                    connection.getOutputStream());
            JSONObject obj = new JSONObject();
            /*obj.element("name", "wanghui");
            obj.element("password", "123456");
            obj.element("email", "14301024@bjtu.edu.cn");
            obj.element("phone", "13800138000");
            obj.element("stu_id", "14301019");
            obj.element("college", "mit");
            obj.element("profession","ddd");
            obj.element("sex", "m");
            obj.element("birthdate","1999-01-03");
*/
            obj.element("name", "wangtianran");
            obj.element("password", "123456");
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
            System.out.println(sb);
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

    }
    public static void main(String[] args) {
        appadd();
    }
    
}
