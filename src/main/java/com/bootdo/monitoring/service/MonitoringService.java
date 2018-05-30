package com.bootdo.monitoring.service;

import com.bootdo.monitoring.domain.MonitoringDO;
import com.bootdo.monitoring.vo.JKVo;
import com.bootdo.monitoring.vo.MonitoringVo;

import java.util.List;
import java.util.Map;

/**
 * 机器表
 * 
 * @author kviuff
 * @email kviuff@163.com
 * @date 2018-02-10 21:38:03
 */
public interface MonitoringService {
	
	MonitoringDO get(Long id);
	
	List<MonitoringDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MonitoringDO monitoring);
	
	int update(MonitoringDO monitoring);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	//新增 机器管理 第一个页面 表格 dn
	List<MonitoringVo> volist(Map<String, Object> map);
	//新增 机器管理 分页总数 dn
	int voCount(Map<String, Object> map);
	//查询 机器管理 关键指标 dn
	Map<String,String> getImportIndex();
	//查询监控关键指标 dn
	Map<String,String> getJKImportIndex(String type);
	//新增 监控 第一个页面 表格 dn
	List<JKVo> JKlist(Map<String, Object> map);
	//新增 监控 分页总数 dn
	int JKCount(Map<String, Object> map);
	//获取楼层和区域 dn
	List<String> getBelong(Map<String, Object> map);
	//查询 保修机器 关键指标 dn
	Map<String,String> getRepairIndex();
    // 根据机器编号修改信息
    int updateByMonitorCode(MonitoringDO monitoringDO);

	/**
	 * 未绑定用户的机器列表
	 * @param map
	 * @return
	 */
	List<MonitoringDO> listForUnboundDevice(Map<String, Object> map);

	/**
	 * 未绑定用户的机器数量
	 * @param map
	 * @return
	 */
	int countForUnboundDevice(Map<String, Object> map);

    /**
     * 根据CODE获取机器信息
     * @param code
     * @return
     */
    MonitoringDO getByCode(String code);
	//微信首页显示数据
	List<MonitoringVo> contentList(Map<String, Object> map);
	//获取需要保养的用户
	List<MonitoringVo> getByKeep(Map<String, Object> map);

	int getByKeepCount(Map<String, Object> map);
	//获取需要保养的用户的机器型号
	List<String> getGroupCode(Map<String, Object> map);

	int updateBatch(List<MonitoringDO> list);


	List<MonitoringDO> findBig(Map<String, Object> map);
}
