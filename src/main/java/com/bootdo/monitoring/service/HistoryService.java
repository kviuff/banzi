package com.bootdo.monitoring.service;

import com.bootdo.monitoring.domain.HistoryDO;
import com.bootdo.monitoring.vo.EchartVo;

import java.util.List;
import java.util.Map;

/**
 * 机器表历史记录表
 * 
 * @author kviuff
 * @email kviuff@163.com
 * @date 2018-02-10 21:38:03
 */
public interface HistoryService {
	
	HistoryDO get(Long id);
	
	List<HistoryDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(HistoryDO history);
	
	int update(HistoryDO history);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	List<EchartVo> getGroupXf (EchartVo vo);

	List<EchartVo> getGroupYs (EchartVo vo);
}
