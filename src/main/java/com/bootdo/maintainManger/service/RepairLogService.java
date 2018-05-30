package com.bootdo.maintainManger.service;

import com.bootdo.maintainManger.domain.RepairLogDO;

import java.util.List;
import java.util.Map;

/**
 * 维修记录表
 * 
 * @author dunan
 * @email nan_du@126.com
 * @date 2018-03-09 18:55:50
 */
public interface RepairLogService {
	
	RepairLogDO get(Integer repairId);
	
	List<RepairLogDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RepairLogDO repairLog);
	
	int update(RepairLogDO repairLog);
	
	int remove(Integer repairId);
	
	int batchRemove(Integer[] repairIds);

	public RepairLogDO saveRepair (RepairLogDO repairLog);

	Map<String,String> getMaintainIndex();

	List<RepairLogDO> repairList(Map<String, Object> map);

}
