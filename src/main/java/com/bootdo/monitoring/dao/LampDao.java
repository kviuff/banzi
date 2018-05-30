package com.bootdo.monitoring.dao;

import com.bootdo.monitoring.domain.LampDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 灯
 * @author dunan
 * @email nan_du@126.com
 * @date 2018-03-28 21:26:39
 */
@Mapper
public interface LampDao {

	LampDO get(String returnId);
	
	List<LampDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LampDO lamp);
	
	int update(LampDO lamp);
	
	int remove(LampDO lampDO);
	
	int batchRemove(String[] returnIds);

	List<LampDO> allList(Map<String, Object> map);
}
