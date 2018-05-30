package com.bootdo.monitoring.service;

import com.bootdo.monitoring.domain.LampDO;
import com.bootdo.monitoring.domain.MonitoringDO;

import java.util.List;
import java.util.Map;

/**
 * 灯
 * 
 * @author dunan
 * @email nan_du@126.com
 * @date 2018-03-28 21:26:39
 */
public interface LightService {
	
	LampDO get(String returnId);
	
	List<LampDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LampDO lamp);
	
	int update(LampDO lamp);
	
	int remove(LampDO lamp);
	
	int batchRemove(String[] returnIds);

	List<LampDO> allList(Map<String, Object> map);

	String getLight(MonitoringDO monitoringDO1);
}
