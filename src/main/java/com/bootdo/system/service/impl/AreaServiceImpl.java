package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.AreaDao;
import com.bootdo.system.domain.AreaDO;
import com.bootdo.system.service.AreaService;



@Service
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaDao areaDao;
	
	@Override
	public AreaDO get(String areaId){
		return areaDao.get(areaId);
	}
	
	@Override
	public List<AreaDO> list(Map<String, Object> map){
		return areaDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return areaDao.count(map);
	}
	
	@Override
	public int save(AreaDO area){
		return areaDao.save(area);
	}
	
	@Override
	public int update(AreaDO area){
		return areaDao.update(area);
	}
	
	@Override
	public int remove(String areaId){
		return areaDao.remove(areaId);
	}
	
	@Override
	public int batchRemove(String[] areaIds){
		return areaDao.batchRemove(areaIds);
	}

	@Override
	public List<AreaDO> queryAll(){
		return areaDao.queryAll();
	}

	@Override
	public List<AreaDO> queryChildList(String parentId){
		return areaDao.queryChildList(parentId);
	}
	
}
