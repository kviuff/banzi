package com.bootdo.monitoring.service.impl;

import com.bootdo.monitoring.vo.JKVo;
import com.bootdo.monitoring.vo.MonitoringVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.monitoring.dao.MonitoringDao;
import com.bootdo.monitoring.domain.MonitoringDO;
import com.bootdo.monitoring.service.MonitoringService;



@Service
public class MonitoringServiceImpl implements MonitoringService {
	@Autowired
	private MonitoringDao monitoringDao;

	@Override
	public MonitoringDO get(Long id){
		return monitoringDao.get(id);
	}

	@Override
	public List<MonitoringDO> list(Map<String, Object> map){
		return monitoringDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return monitoringDao.count(map);
	}

	@Override
	public int save(MonitoringDO monitoring){
		monitoring.setProp1("0");
		monitoring.setCreateTime(System.currentTimeMillis());
		return monitoringDao.save(monitoring);
	}

	@Override
	public int update(MonitoringDO monitoring){
		monitoring.setUpdateTime(System.currentTimeMillis());
		return monitoringDao.update(monitoring);
	}

	@Override
	public int remove(Long id){
		return monitoringDao.remove(id);
	}

	@Override
	public int batchRemove(Long[] ids){
		return monitoringDao.batchRemove(ids);
	}

	@Override
	public List<MonitoringVo> volist(Map<String, Object> map) {
		return  monitoringDao.volist(map);
	}

	@Override
	public int voCount(Map<String, Object> map) {
		return monitoringDao.voCount(map);
	}

	@Override
	public Map<String, String> getImportIndex() {
		return monitoringDao.getImportIndex();
	}

	@Override
	public Map<String, String> getJKImportIndex(String type) {
		return monitoringDao.getJKImportIndex(type);
	}

	@Override
	public List<JKVo> JKlist(Map<String, Object> map) {
		return monitoringDao.JKlist(map);
	}

	@Override
	public int JKCount(Map<String, Object> map) {
		return monitoringDao.JKCount(map);
	}

	@Override
	public List<String> getBelong(Map<String, Object> map) {
		return monitoringDao.getBelong(map);
	}

	@Override
	public Map<String, String> getRepairIndex() {
		return monitoringDao.getRepairIndex();
	}

    @Override
    public int updateByMonitorCode(MonitoringDO monitoringDO) {
		monitoringDO.setUpdateTime(System.currentTimeMillis());
        return monitoringDao.updateByMonitorCode(monitoringDO);
    }

	@Override
	public List<MonitoringDO> listForUnboundDevice(Map<String, Object> map){
		return monitoringDao.listForUnboundDevice(map);
	}

	@Override
	public int countForUnboundDevice(Map<String, Object> map) {
		return monitoringDao.countForUnboundDevice(map);
	}

    @Override
    public MonitoringDO getByCode(String code) {
        return monitoringDao.getByCode(code);
    }

	@Override
	public List<MonitoringVo> contentList(Map<String, Object> map) {
		return monitoringDao.contentList(map);
	}

	@Override
	public List<MonitoringVo> getByKeep(Map<String, Object> map) {
		return monitoringDao.getByKeep(map);
	}
	@Override
	public int getByKeepCount(Map<String, Object> map){
		return monitoringDao.getByKeepCount(map);
	};

	@Override
	public List<String> getGroupCode(Map<String, Object> map){
		return monitoringDao.getGroupCode(map);
	};
	@Override
	public int updateBatch(List<MonitoringDO> list){
		return monitoringDao.updateBatch(list);
	}

	@Override
	public List<MonitoringDO> findBig(Map<String, Object> map) {
		return monitoringDao.findBig(map);
	}

	;

}
