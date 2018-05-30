package com.bootdo.system.dao;

import com.bootdo.system.domain.AreaDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 地区表
 * @author dunan
 * @email nan_du@126.com
 * @date 2018-03-07 15:15:28
 */
@Mapper
public interface AreaDao {

	AreaDO get(String areaId);
	
	List<AreaDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AreaDO area);
	
	int update(AreaDO area);
	
	int remove(String area_id);
	
	int batchRemove(String[] areaIds);

	List<AreaDO> queryAll();

	List<AreaDO> queryChildList(String parentId);
}
