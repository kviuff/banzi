package com.bootdo.maintainManger.dao;

import com.bootdo.maintainManger.domain.KeepLogDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 保养记录表
 * @author dunan
 * @email nan_du@126.com
 * @date 2018-03-11 17:00:54
 */
@Mapper
public interface KeepLogDao {

	KeepLogDO get(Long keepId);
	
	List<KeepLogDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(KeepLogDO keepLog);
	
	int update(KeepLogDO keepLog);
	
	int remove(Long keep_id);
	
	int batchRemove(Long[] keepIds);
}
