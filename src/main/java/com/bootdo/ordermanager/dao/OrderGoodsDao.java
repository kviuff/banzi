package com.bootdo.ordermanager.dao;

import com.bootdo.ordermanager.domain.OrderGoodsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 订单产品
 * @author kviuff
 * @email kviuff@163.com
 */
@Mapper
public interface OrderGoodsDao {

	OrderGoodsDO get(Long id);
	
	List<OrderGoodsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderGoodsDO orderGoods);
	
	int update(OrderGoodsDO orderGoods);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
