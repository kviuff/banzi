package com.bootdo.cluemanger.service;

import com.bootdo.cluemanger.domain.ManagerDO;

import java.util.List;
import java.util.Map;

/**
 * 线索管理
 * 
 * @author kviuff
 * @email kviuff@163.com
 * @date 2018-03-19 19:37:26
 */
public interface ManagerService {
	
	ManagerDO get(Long id);
	
	List<ManagerDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ManagerDO manager);
	
	int update(ManagerDO manager);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
