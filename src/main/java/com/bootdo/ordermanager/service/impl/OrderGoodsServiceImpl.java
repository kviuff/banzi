package com.bootdo.ordermanager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.ordermanager.dao.OrderGoodsDao;
import com.bootdo.ordermanager.domain.OrderGoodsDO;
import com.bootdo.ordermanager.service.OrderGoodsService;



@Service
public class OrderGoodsServiceImpl implements OrderGoodsService {
	@Autowired
	private OrderGoodsDao orderGoodsDao;
	
	@Override
	public OrderGoodsDO get(Long id){
		return orderGoodsDao.get(id);
	}
	
	@Override
	public List<OrderGoodsDO> list(Map<String, Object> map){
		return orderGoodsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orderGoodsDao.count(map);
	}
	
	@Override
	public int save(OrderGoodsDO orderGoods){
		return orderGoodsDao.save(orderGoods);
	}
	
	@Override
	public int update(OrderGoodsDO orderGoods){
		return orderGoodsDao.update(orderGoods);
	}
	
	@Override
	public int remove(Long id){
		return orderGoodsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return orderGoodsDao.batchRemove(ids);
	}
	
}
