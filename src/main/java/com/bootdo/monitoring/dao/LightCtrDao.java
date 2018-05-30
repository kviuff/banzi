package com.bootdo.monitoring.dao;

import com.bootdo.monitoring.domain.LightCtrDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 灯控制器表
 * @author dunan
 * @email nan_du@126.com
 * @date 2018-05-11 18:21:25
 */
@Mapper
public interface LightCtrDao {

	LightCtrDO get(Long id);
	
	List<LightCtrDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);

	int allCount(Map<String, Object> map);
	
	int save(LightCtrDO lightCtr);
	
	int update(LightCtrDO lightCtr);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
