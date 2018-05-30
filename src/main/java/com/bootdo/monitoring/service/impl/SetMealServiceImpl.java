package com.bootdo.monitoring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.monitoring.dao.SetMealDao;
import com.bootdo.monitoring.domain.SetMealDO;
import com.bootdo.monitoring.service.SetMealService;



@Service
public class SetMealServiceImpl implements SetMealService {
	@Autowired
	private SetMealDao setMealDao;
	
	@Override
	public SetMealDO get(Long id){
		return setMealDao.get(id);
	}
	
	@Override
	public List<SetMealDO> list(Map<String, Object> map){
		return setMealDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return setMealDao.count(map);
	}
	
	@Override
	public int save(SetMealDO setMeal){
		return setMealDao.save(setMeal);
	}
	
	@Override
	public int update(SetMealDO setMeal){
		return setMealDao.update(setMeal);
	}
	
	@Override
	public int remove(Long id){
		return setMealDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return setMealDao.batchRemove(ids);
	}
	
}
