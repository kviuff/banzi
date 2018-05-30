package com.bootdo.monitoring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.monitoring.dao.LightCtrDao;
import com.bootdo.monitoring.domain.LightCtrDO;
import com.bootdo.monitoring.service.LightCtrService;



@Service
public class LightCtrServiceImpl implements LightCtrService {
	@Autowired
	private LightCtrDao lightCtrDao;
	
	@Override
	public LightCtrDO get(Long id){
		return lightCtrDao.get(id);
	}
	
	@Override
	public List<LightCtrDO> list(Map<String, Object> map){
		return lightCtrDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return lightCtrDao.count(map);
	}

	@Override
	public int allCount(Map<String, Object> map){
		return lightCtrDao.allCount(map);
	}


	@Override
	public int save(LightCtrDO lightCtr){
		return lightCtrDao.save(lightCtr);
	}
	
	@Override
	public int update(LightCtrDO lightCtr){
		return lightCtrDao.update(lightCtr);
	}
	
	@Override
	public int remove(Long id){
		return lightCtrDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return lightCtrDao.batchRemove(ids);
	}
	
}
