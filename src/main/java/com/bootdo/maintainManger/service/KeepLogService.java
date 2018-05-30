package com.bootdo.maintainManger.service;

import com.bootdo.maintainManger.domain.KeepLogDO;

import java.util.List;
import java.util.Map;

/**
 * 保养记录表
 * 
 * @author dunan
 * @email nan_du@126.com
 * @date 2018-03-11 17:00:54
 */
public interface KeepLogService {
	
	KeepLogDO get(Long keepId);
	
	List<KeepLogDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(KeepLogDO keepLog);
	
	int update(KeepLogDO keepLog);
	
	int remove(Long keepId);
	
	int batchRemove(Long[] keepIds);
}
