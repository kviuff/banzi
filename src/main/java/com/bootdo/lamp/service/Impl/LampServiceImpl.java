package com.bootdo.lamp.service.Impl;

import com.bootdo.lamp.LampImpl;
import com.bootdo.lamp.service.LampService;
import com.bootdo.lamp.vo.LampVo;
import com.bootdo.monitoring.domain.MonitoringDO;
import com.bootdo.monitoring.service.MonitoringService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LampServiceImpl implements LampService {

    @Autowired
    private MonitoringService monitoringService;

    /**
     * 获取灯的设备列表并加入机器管理表中
     * @param param
     */
    public void getDeviceList(Map param) {
        String result = LampImpl.deviceList(param);
        JSONObject jsonObject = JSONObject.fromObject(result);
        String dataStr = jsonObject.get("data").toString();
        JSONArray array = JSONArray.fromObject(dataStr);
//        DeviceVo deviceVo = (DeviceVo) JSONObject.toBean(jsonObject, DeviceVo.class);

        List<LampVo> lampVoList = JSONArray.toList(array, LampVo.class);

        // 解析设备列表保存数据库
        for (LampVo lampVo : lampVoList) {
            MonitoringDO monitoringDO = new MonitoringDO();

            monitoringDO.setMonitorCode(lampVo.getId());
            monitoringDO.setMachineType("3");
            monitoringDO.setStatus("1");
            monitoringDO.setUserId("user411");
            monitoringService.save(monitoringDO);
        }

    }
}
