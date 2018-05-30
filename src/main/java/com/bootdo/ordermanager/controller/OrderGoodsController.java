package com.bootdo.ordermanager.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.ordermanager.domain.OrderListDO;
import com.bootdo.ordermanager.service.OrderListService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.ordermanager.domain.OrderGoodsDO;
import com.bootdo.ordermanager.service.OrderGoodsService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 订单产品
 * 
 * @author kviuff
 * @email kviuff@163.com
 */
 
@Controller
@RequestMapping("/ordermanager/orderGoods")
public class OrderGoodsController {
	@Autowired
	private OrderGoodsService orderGoodsService;
	@Autowired
	private OrderListService orderListService;
	
	@GetMapping()
	String OrderGoods(@RequestParam("userId") Long userId, Model model){
		model.addAttribute("userId", userId);
		return "ordermanager/orderGoods/orderGoods";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("olist", "1");
        Query query = new Query(params);
		List<OrderGoodsDO> orderGoodsList = orderGoodsService.list(query);
		int total = orderGoodsService.count(query);
		PageUtils pageUtils = new PageUtils(orderGoodsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "ordermanager/orderGoods/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id,Model model){
		OrderGoodsDO orderGoods = orderGoodsService.get(id);
		model.addAttribute("orderGoods", orderGoods);
	    return "ordermanager/orderGoods/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( OrderGoodsDO orderGoods){

		String userId = orderGoods.getUserId();
		String orderSn = String.valueOf(System.currentTimeMillis());
		BigDecimal num = new BigDecimal(orderGoods.getGoodsNum());
		BigDecimal price = new BigDecimal(orderGoods.getGoodsPrice().toString());
		BigDecimal totlaAmount = num.multiply(price);

		OrderListDO orderListDO = new OrderListDO();
		orderListDO.setOrderSn(orderSn);
		orderListDO.setUserId(userId);
		orderListDO.setTatalPrice(totlaAmount);
		orderListDO.setProp1("2");
		orderListDO.setCreateTime(System.currentTimeMillis());
		if (orderListService.save(orderListDO) > 0) {
			orderGoods.setOrderSn(orderSn);
			orderGoods.setProp1(userId);
			if(orderGoodsService.save(orderGoods)>0){
				return R.ok();
			}
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( OrderGoodsDO orderGoods){
		orderGoodsService.update(orderGoods);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Long id){
		if(orderGoodsService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Long[] ids){
		orderGoodsService.batchRemove(ids);
		return R.ok();
	}

	@GetMapping("/OrderGoods")
	String OrderGoods1(@RequestParam("id") String id, Model model){

		OrderListDO orderListDO = orderListService.get(Long.valueOf(id));

		Map<String, Object> params = new HashMap<>();
		params.put("orderSn", orderListDO.getOrderSn());
		int total = orderGoodsService.count(params);

		model.addAttribute("id", id);
		model.addAttribute("orderSn", orderListDO.getOrderSn());
		model.addAttribute("total", total);
		model.addAttribute("totalAmount", orderListDO.getTatalPrice());

		return "ordermanager/orderGoods/orderGoods1";
	}

	@ResponseBody
	@GetMapping("/OrderGoods1List")
	public PageUtils OrderGoods1List(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<OrderGoodsDO> orderGoodsList = orderGoodsService.list(query);
		int total = orderGoodsService.count(query);
		PageUtils pageUtils = new PageUtils(orderGoodsList, total);
		return pageUtils;
	}
	
}
