package com.bootdo.monitoring.dao;

import com.bootdo.monitoring.domain.MonitoringDO;

import java.util.List;
import java.util.Map;

import com.bootdo.monitoring.vo.JKVo;
import com.bootdo.monitoring.vo.MonitoringVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 机器表
 * @author kviuff
 * @email kviuff@163.com
 * @date 2018-02-10 21:38:03
 */
@Mapper
public interface MonitoringDao {

	MonitoringDO get(Long id);
	
	List<MonitoringDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MonitoringDO monitoring);
	
	int update(MonitoringDO monitoring);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	List<MonitoringVo> volist(Map<String, Object> map);

	int voCount(Map<String, Object> map);

	Map<String,String> getImportIndex();

	Map<String,String> getJKImportIndex(String type);

	List<JKVo> JKlist(Map<String, Object> map);

	int JKCount(Map<String, Object> map);

	List<String> getBelong(Map<String, Object> map);

	Map<String,String> getRepairIndex();

	int updateByMonitorCode(MonitoringDO monitoringDO);

	List<MonitoringDO> listForUnboundDevice(Map<String, Object> map);

	int countForUnboundDevice(Map<String, Object> map);

    MonitoringDO getByCode(String code);

	List<MonitoringVo> contentList(Map<String, Object> map);

	List<MonitoringVo> getByKeep(Map<String, Object> map);

	int getByKeepCount(Map<String, Object> map);

	List<String> getGroupCode(Map<String, Object> map);

	int updateBatch(List<MonitoringDO> list);

	List<MonitoringDO> findBig(Map<String, Object> map);

}
