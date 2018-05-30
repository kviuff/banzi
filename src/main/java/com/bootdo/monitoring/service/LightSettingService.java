package com.bootdo.monitoring.service;

import com.bootdo.monitoring.domain.LightSettingDO;

import java.util.List;
import java.util.Map;

/**
 * 灯光显示参数
 * 
 * @author dunan
 * @email nan_du@126.com
 * @date 2018-05-18 19:54:59
 */
public interface LightSettingService {
	
	LightSettingDO get(Long userId);
	
	List<LightSettingDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LightSettingDO lightSetting);
	
	int update(LightSettingDO lightSetting);
	
	int remove(Long userId);
	
	int batchRemove(Long[] userIds);
}
