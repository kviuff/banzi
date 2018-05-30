package com.bootdo.maintainManger.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.maintainManger.dao.RepairLogDao;
import com.bootdo.maintainManger.domain.RepairLogDO;
import com.bootdo.maintainManger.service.RepairLogService;



@Service
public class RepairLogServiceImpl implements RepairLogService {
	@Autowired
	private RepairLogDao repairLogDao;
	
	@Override
	public RepairLogDO get(Integer repairId){
		return repairLogDao.get(repairId);
	}
	
	@Override
	public List<RepairLogDO> list(Map<String, Object> map){
		return repairLogDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return repairLogDao.count(map);
	}
	
	@Override
	public int save(RepairLogDO repairLog){
		repairLog.setCreateTime(new Date());
		return repairLogDao.save(repairLog);
	}
	
	@Override
	public int update(RepairLogDO repairLog){
		repairLog.setUpdateTime(new Date());
		return repairLogDao.update(repairLog);
	}
	
	@Override
	public int remove(Integer repairId){
		return repairLogDao.remove(repairId);
	}
	
	@Override
	public int batchRemove(Integer[] repairIds){
		return repairLogDao.batchRemove(repairIds);
	}

	@Override
	public RepairLogDO saveRepair (RepairLogDO repairLog){
		repairLog.setCreateTime(new Date());
		repairLogDao.save(repairLog);
		System.out.println(repairLog.getRepairId());
		return repairLog;
	}

	/**
	 * 获取关键指标
	 * @return
	 */
	@Override
	public Map<String, String> getMaintainIndex() {
		return repairLogDao.getMaintainIndex();
	}

	@Override
	public List<RepairLogDO> repairList(Map<String, Object> map) {
		return repairLogDao.repairList(map);
	}

}
