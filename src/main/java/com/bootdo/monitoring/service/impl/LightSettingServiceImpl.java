package com.bootdo.monitoring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.monitoring.dao.LightSettingDao;
import com.bootdo.monitoring.domain.LightSettingDO;
import com.bootdo.monitoring.service.LightSettingService;



@Service
public class LightSettingServiceImpl implements LightSettingService {
	@Autowired
	private LightSettingDao lightSettingDao;
	
	@Override
	public LightSettingDO get(Long userId){
		return lightSettingDao.get(userId);
	}
	
	@Override
	public List<LightSettingDO> list(Map<String, Object> map){
		return lightSettingDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return lightSettingDao.count(map);
	}
	
	@Override
	public int save(LightSettingDO lightSetting){
		return lightSettingDao.save(lightSetting);
	}
	
	@Override
	public int update(LightSettingDO lightSetting){
		return lightSettingDao.update(lightSetting);
	}
	
	@Override
	public int remove(Long userId){
		return lightSettingDao.remove(userId);
	}
	
	@Override
	public int batchRemove(Long[] userIds){
		return lightSettingDao.batchRemove(userIds);
	}
	
}
