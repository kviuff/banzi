package com.bootdo.ordermanager.service.impl;

import com.bootdo.ordermanager.domain.OrderDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.ordermanager.dao.OrderListDao;
import com.bootdo.ordermanager.domain.OrderListDO;
import com.bootdo.ordermanager.service.OrderListService;



@Service
public class OrderListServiceImpl implements OrderListService {
	@Autowired
	private OrderListDao orderListDao;
	
	@Override
	public OrderListDO get(Long id){
		return orderListDao.get(id);
	}
	
	@Override
	public List<OrderListDO> list(Map<String, Object> map){
		return orderListDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orderListDao.count(map);
	}
	
	@Override
	public int save(OrderListDO orderList){
		return orderListDao.save(orderList);
	}
	
	@Override
	public int update(OrderListDO orderList){
		return orderListDao.update(orderList);
	}
	
	@Override
	public int remove(Long id){
		return orderListDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return orderListDao.batchRemove(ids);
	}

	@Override
	public OrderDO selectCountAndMoneyByUser(Map<String, Object> map) {
		return orderListDao.selectCountAndMoneyByUser(map);
	}

	@Override
	public List<OrderListDO> listForJxs(Map<String, Object> map) {
		return orderListDao.listForJxs(map);
	}

	@Override
	public int countForJxs(Map<String, Object> map) {
		return orderListDao.countForJxs(map);
	}


}
