package com.bootdo.ordermanager.service;

import com.bootdo.ordermanager.domain.OrderDO;
import com.bootdo.ordermanager.domain.OrderListDO;

import java.util.List;
import java.util.Map;

/**
 * 订单表
 * 
 * @author kviuff
 * @email kviuff@163.com
 */
public interface OrderListService {
	
	OrderListDO get(Long id);
	
	List<OrderListDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderListDO orderList);
	
	int update(OrderListDO orderList);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	OrderDO selectCountAndMoneyByUser(Map<String, Object> map);

	List<OrderListDO> listForJxs(Map<String, Object> map);

	int countForJxs(Map<String, Object> map);
}
