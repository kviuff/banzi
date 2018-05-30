package com.bootdo.maintainManger.dao;

import com.bootdo.maintainManger.domain.RepairLogDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 维修记录表
 * @author dunan
 * @email nan_du@126.com
 * @date 2018-03-09 18:55:50
 */
@Mapper
public interface RepairLogDao {

	RepairLogDO get(Integer repairId);
	
	List<RepairLogDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RepairLogDO repairLog);
	
	int update(RepairLogDO repairLog);
	
	int remove(Integer repair_id);
	
	int batchRemove(Integer[] repairIds);

	Map<String,String> getMaintainIndex();

	List<RepairLogDO> repairList(Map<String, Object> map);
}
