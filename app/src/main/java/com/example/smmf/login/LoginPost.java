package com.example.smmf.login;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by smmf on 2017/11/20.
 */

public class LoginPost {

    public LoginPost() throws MalformedURLException {
    }

    public static void setJson(final String un, final String pwd) throws JSONException, IOException {

        Thread loginrequest = new Thread(){
            @Override
            public void run() {
                try {
                    // 服务器地址
                    String urlPath = "http://172.16.73.14:8080/api/account/loginAccount";
                    URL url = new URL(urlPath);
                    String username, password, content;
                    // 创建JSON对象并添加数据
                    JSONObject account = new JSONObject();
                    // un，pwd为传递过来的参数
                    username = un;
                    password = pwd;
                    account.put("username", username);
                    account.put("password", password);
                    // JSON对象转换为字符串形式
                    content = String.valueOf(account);
                    // 建立连接
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(5000);
                    // 设置允许输出
                    conn.setDoOutput(true);
                    // POST方式输出
                    conn.setRequestMethod("POST");
                    // 设置User-Agent: Fiddler
                    conn.setRequestProperty("ser-Agent", "Fiddler");
                    // 设置contentType
                    conn.setRequestProperty("Content-Type","application/json");
                    // 创建输出流对象
                    OutputStream os = conn.getOutputStream();
                    // 字符串对象转换Byte类型输出
                    os.write(content.getBytes());
                    // 关闭输出流
                    os.close();

                    // 获得服务器返回的代码，200为请求成功
                    int httpcode = conn.getResponseCode();
                    Log.i("LoginActivity", String.valueOf(httpcode));
                    if (httpcode == 200) {
                        Log.i("LoginActivity", "请求成功！");

                        // 创建输入流接收服务器传输的数据
                        InputStream inputStream = conn.getInputStream();
                        // 调用readStreamToString方法把输入流转换为字符串
                        String result = readStreamToString(inputStream);

                        // 把字符串对象转换成JSON对象
                        JSONObject jsonObject = new JSONObject(result);
                        // 获取JSON对象中的code，10000为登录成功
                        int resultCode = jsonObject.getInt("code");
                        Log.i("LoginActivity", String.valueOf(resultCode));
                        if (resultCode == 10000) {
                            Log.i("LoginActivity", "登录成功！");
                        } else {
                            Log.i("LoginActivity", "登录失败！");
                        }

                    } else {
                        Log.i("LoginActivity", "请求失败！");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        loginrequest.start();
    }

    public static String readStreamToString(InputStream inputStream) throws IOException {
        // 创建字节数组输出流 ，用来输出读取到的内容
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 创建读取缓存,大小为1024
        byte[] buffer = new byte[1024];
        // 每次读取长度
        int len = 0;
        // 开始读取输入流中的文件
        while( (len = inputStream.read(buffer) ) != -1){ // 当等于-1说明没有数据可以读取了
            byteArrayOutputStream.write(buffer,0,len); // 把读取的内容写入到输出流中
        }
        // 把读取到的字节数组转换为字符串
        String result = byteArrayOutputStream.toString();

        // 关闭输入流和输出流
        inputStream.close();
        byteArrayOutputStream.close();
        // 返回字符串结果
        return result;
    }

}
