package com.bootdo.monitoring.domain;

import java.io.Serializable;

/**
 * 天气实体
 */
public class WeatherVo implements Serializable {
    private static final long serialVersionUID = 1L;
    //当前时间
    private String nowDate;
    //温度
    private String temp;
    //风速
    private String windSpd;

    //湿度
    private String hum;
    //pm2.5
    private String pm25;

    public String getNowDate() {
        return nowDate;
    }

    public void setNowDate(String nowDate) {
        this.nowDate = nowDate;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getWindSpd() {
        return windSpd;
    }

    public void setWindSpd(String windSpd) {
        this.windSpd = windSpd;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }




}
