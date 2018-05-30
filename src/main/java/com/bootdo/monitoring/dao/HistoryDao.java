package com.bootdo.monitoring.dao;

import com.bootdo.monitoring.domain.HistoryDO;

import java.util.List;
import java.util.Map;

import com.bootdo.monitoring.vo.EchartVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 机器表历史记录表
 * @author kviuff
 * @email kviuff@163.com
 * @date 2018-02-10 21:38:03
 */
@Mapper
public interface HistoryDao {

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
