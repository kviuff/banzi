package com.bootdo.lamp.service;

import java.util.Map;

public interface LampService {

    /**
     * 获取灯的设备列表并加入机器管理表中
     * @param param
     */
    void getDeviceList(Map param);
}
