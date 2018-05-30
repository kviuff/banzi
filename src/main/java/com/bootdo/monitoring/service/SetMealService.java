package com.bootdo.monitoring.service;

import com.bootdo.monitoring.domain.SetMealDO;

import java.util.List;
import java.util.Map;

/**
 * 套餐表
 * 
 * @author dunan
 * @email nan_du@126.com
 * @date 2018-05-28 22:39:37
 */
public interface SetMealService {
	
	SetMealDO get(Long id);
	
	List<SetMealDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SetMealDO setMeal);
	
	int update(SetMealDO setMeal);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
