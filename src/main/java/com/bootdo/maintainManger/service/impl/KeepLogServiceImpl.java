package com.bootdo.maintainManger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.maintainManger.dao.KeepLogDao;
import com.bootdo.maintainManger.domain.KeepLogDO;
import com.bootdo.maintainManger.service.KeepLogService;



@Service
public class KeepLogServiceImpl implements KeepLogService {
	@Autowired
	private KeepLogDao keepLogDao;
	
	@Override
	public KeepLogDO get(Long keepId){
		return keepLogDao.get(keepId);
	}
	
	@Override
	public List<KeepLogDO> list(Map<String, Object> map){
		return keepLogDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return keepLogDao.count(map);
	}
	
	@Override
	public int save(KeepLogDO keepLog){
		keepLog.setCreateTime(new Date());
		return keepLogDao.save(keepLog);
	}
	
	@Override
	public int update(KeepLogDO keepLog){
		return keepLogDao.update(keepLog);
	}
	
	@Override
	public int remove(Long keepId){
		return keepLogDao.remove(keepId);
	}
	
	@Override
	public int batchRemove(Long[] keepIds){
		return keepLogDao.batchRemove(keepIds);
	}
	
}
