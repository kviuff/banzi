package com.bootdo.common.utils;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * java调用和风天气
 *
 * @author dn
 *
 */
public class WeatherUtil {

    /**
     * 获取pm2.5<br>
     * 方 法 名：getAirWeatherPm <br>
     * @param Cityid  城市编码
     */
    public static  Map<String, Object> getAirWeatherPm(String Cityid,String key)
            throws IOException, NullPointerException {
        // 连接中央气象台的API
        URL url = new URL("https://free-api.heweather.com/s6/air/now?location="+Cityid+"&key="+key);
        URLConnection connectionData = url.openConnection();
        connectionData.setConnectTimeout(1000);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    connectionData.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null)
                sb.append(line);
            String datas = sb.toString();
            System.out.println(datas);
            JSONObject jsonData = JSONObject.fromObject(datas);
            JSONArray jsonArray = JSONArray.fromObject(jsonData.get("HeWeather6"));
            JSONObject json = JSONObject.fromObject(jsonArray.get(0));
            System.out.println(json.getString("status"));
            JSONObject jsonNow = JSONObject.fromObject(json.getString("air_now_city"));
            System.out.println(jsonNow.getString("pm25"));
            map.put("pm25", jsonNow.getString("pm25"));// 相对湿度
            JSONObject jsonTime = JSONObject.fromObject(json.getString("update"));
            map.put("loc", jsonTime.getString("loc"));// 当前时间
        } catch (SocketTimeoutException e) {
            System.out.println("连接超时");
        } catch (FileNotFoundException e) {
            System.out.println("加载文件出错");
        }

        return map;

    }

    /**
     *
     * 获取实时天气1<br>
     * 方 法 名： getTodayWeather <br>
     *
     * @param Cityid
     *            城市编码
     */
    public static Map<String, Object> getTodayWeather1(String Cityid,String key)
            throws IOException, NullPointerException {
        // 连接中央气象台的API
        URL url = new URL("https://free-api.heweather.com/s6/weather/now?location="+Cityid+"&key="+key);
        URLConnection connectionData = url.openConnection();
        connectionData.setConnectTimeout(1000);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    connectionData.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null)
                sb.append(line);
            String datas = sb.toString();
            System.out.println(datas);
            JSONObject jsonData = JSONObject.fromObject(datas);
            JSONArray jsonArray = JSONArray.fromObject(jsonData.get("HeWeather6"));
            JSONObject json = JSONObject.fromObject(jsonArray.get(0));
            System.out.println(json.getString("status"));
            JSONObject jsonNow = JSONObject.fromObject(json.getString("now"));
            JSONObject jsonTime = JSONObject.fromObject(json.getString("update"));
            System.out.println(jsonNow.getString("wind_spd"));
            map.put("city", "北京");// 城市
            map.put("temp", jsonNow.getString("tmp"));// 温度 默认单位：摄氏度
            map.put("WS", jsonNow.getString("wind_spd"));// 风速  风速，公里/小时
            map.put("hum", jsonNow.getString("hum"));// 相对湿度
            map.put("loc", jsonTime.getString("loc"));// 当前时间

        } catch (SocketTimeoutException e) {
            System.out.println("连接超时");
        } catch (FileNotFoundException e) {
            System.out.println("加载文件出错");
        }

        return map;

    }

    private static String getWeek(int iw) {
        String weekStr = "";
        switch (iw) {
            case 1:
                weekStr = "星期天";
                break;
            case 2:
                weekStr = "星期一";
                break;
            case 3:
                weekStr = "星期二";
                break;
            case 4:
                weekStr = "星期三";
                break;
            case 5:
                weekStr = "星期四";
                break;
            case 6:
                weekStr = "星期五";
                break;
            case 7:
                weekStr = "星期六";
                break;
            default:
                break;
        }
        return weekStr;
    }

    //获取页面所需要的天气数据
    public static Map<String, Object> getNowWeather() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //获取天气
            map = getTodayWeather1("CN101010100","d24d5afe0bdc4fd19ad3e0797a284d45");
            //获取pm
            Map<String, Object> map1 =getAirWeatherPm("CN101010100","d24d5afe0bdc4fd19ad3e0797a284d45");
            map.put("pm25",map1.get("pm25"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] args) {
        try {
            //测试获取实时天气1(包含风况，湿度)
//            Map<String, Object> map = getTodayWeather1("CN101010100","d24d5afe0bdc4fd19ad3e0797a284d45");
//            System.out.println(map.get("city") + "\t" + map.get("temp")
//                    + "\t" + map.get("WS")
//                    + "\t" + map.get("hum") );
            //测试获取当前北京市的空气质量
            Map<String, Object> map =getAirWeatherPm("CN101010100","d24d5afe0bdc4fd19ad3e0797a284d45");

           System.out.println(map.get("loc").toString().substring(0,10) + "\t" + map.get("pm25"));
//                    + "\t" + map2.get("temp2") + "\t" + map2.get("weather")
//                    + "\t" + map2.get("ptime"));



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}