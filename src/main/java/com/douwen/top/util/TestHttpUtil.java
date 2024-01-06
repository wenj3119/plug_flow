package com.douwen.top.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestHttpUtil {

    public static void testGetUrl(
            String httpUrl
    ){
        try {
            // 创建URL对象
            URL url = new URL(httpUrl);
            // 创建HttpURLConnection对象
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法为GET
            connection.setRequestMethod("GET");
            // 发送请求
            int responseCode = connection.getResponseCode();

            // 读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // 输出响应结果
            System.out.println("Response Code: " + responseCode);
            System.out.println("Response Body: " + response.toString());

            // 断开连接
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testPostUrl(
            String httpPostUrl
    ) {
        try {
            // 创建URL对象
            URL url = new URL(httpPostUrl);

            // 创建HttpURLConnection对象
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法为POST
            connection.setRequestMethod("POST");

            // 设置请求头
            connection.setRequestProperty("Content-Type", "application/json");

            // 设置请求体
            String jsonBody = "Liar platform";
            connection.setDoOutput(true);
            connection.getOutputStream().write(jsonBody.getBytes());

            // 发送请求
            int responseCode = connection.getResponseCode();

            // 读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // 输出响应结果
            System.out.println("Response Code: " + responseCode);
            System.out.println("Response Body: " + response.toString());

            // 断开连接
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
