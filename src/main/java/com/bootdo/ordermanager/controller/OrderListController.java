package com.bootdo.ordermanager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.ordermanager.domain.OrderDO;
import com.bootdo.ordermanager.domain.OrderGoodsDO;
import com.bootdo.ordermanager.service.OrderGoodsService;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
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

import com.bootdo.ordermanager.domain.OrderListDO;
import com.bootdo.ordermanager.service.OrderListService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 订单表
 * 
 * @author kviuff
 * @email kviuff@163.com
 */
 
@Controller
@RequestMapping("/ordermanager/orderList")
public class OrderListController {
	@Autowired
	private OrderListService orderListService;

	@Autowired
	private UserService userService;

	@Autowired
	private OrderGoodsService orderGoodsService;
	
	@GetMapping()
	String OrderList(){
	    return "ordermanager/orderList/orderList";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){


		List<OrderListDO> orderListList = new ArrayList<>();
		int total = 0;
		UserDO userDO = ShiroUtils.getUser(); // 获取当前登录用户
		if ("admin".equals(userDO.getUsername())) { // 超级管理员查询所有
			//查询列表数据
			Query query = new Query(params);
			orderListList = orderListService.listForJxs(query);
			total = orderListService.countForJxs(query);
		} else if ("1".equals(userDO.getAccountnumberType())) { // 经销商
			params.put("agencyId", userDO.getUserId());
			//查询列表数据
			Query query = new Query(params);
			orderListList = orderListService.listForJxs(query);
			total = orderListService.countForJxs(query);
		}
		PageUtils pageUtils = new PageUtils(orderListList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(@RequestParam(value = "userId", required = false) Long userId, Model model){
		model.addAttribute("userId", userId);
		return "ordermanager/orderList/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id,Model model){
		OrderListDO orderList = orderListService.get(id);
		model.addAttribute("orderList", orderList);
	    return "ordermanager/orderList/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( OrderListDO orderList){
		if(orderListService.save(orderList)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( OrderListDO orderList){
		orderListService.update(orderList);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Long id){
		OrderListDO orderListDO = orderListService.get(id);
		if(orderListService.remove(id)>0){
			String orderSn = orderListDO.getOrderSn();

			Map<String, Object> map = new HashMap<>();
			map.put("orderSn", orderSn);
			List<OrderGoodsDO> orderGoodsDOS = orderGoodsService.list(map);
			for (OrderGoodsDO orderGoodsDO : orderGoodsDOS) {
				orderGoodsService.remove(orderGoodsDO.getId());
			}
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
		orderListService.batchRemove(ids);
		return R.ok();
	}


	/**
	 * 客户订单汇总列表
	 * @return
	 */
	@GetMapping("/customerorder")
	String customerorder(Model model,@PathVariable(value = "userId", required = false) Long userId){

		// 根据用户ID获取用户信息
		UserDO userDO = null;
		if (null != userId) {
			userDO = userService.get(userId);
		}

		// 获取经销商列表
//		Map params = new HashMap();
//		params.put("accountnumberType", "1");
//		params.put("offset", "1");
//		params.put("limit", "1000");
//		Query query = new Query(params);
//		List<UserDO> sysUserList = userService.listForOrderGroup(query);

		// 查询经销商的客户数
//		Map params1 = new HashMap();
//		params1.put("accountnumberType", "0");
//		params1.put("offset", "1");
//		params1.put("limit", "1000");
//		if (null != userDO) {
//			params1.put("responsibleForPhone", userDO.getResponsibleForPhone());
//		}
//		Query query1 = new Query(params1);
//		int kehuCount = userService.listForOrderGroupCount(query1);

		// 当前订单数和订单总额
//		Map params2 = new HashMap();
//		params2.put("accountnumberType", "0");
//		if (null != userDO) {
//			params2.put("responsibleForPhone", userDO.getResponsibleForPhone());
//		}
//		OrderDO orderDO = orderListService.selectCountAndMoneyByUser(params2);


//		model.addAttribute("sysUserList", sysUserList.size());
//		model.addAttribute("kehuCount", kehuCount);
//		model.addAttribute("orderCount", orderDO.getOrderCount());
//		model.addAttribute("orderAmount", orderDO.getOrderAmount() == null ? 0 : orderDO.getOrderAmount());
		return "ordermanager/orderList/customerorder";
	}

	@ResponseBody
	@GetMapping("/customerorderlist")
	public PageUtils customerorderlist(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<UserDO> sysUserList = userService.listForOrderGroup(query);
		int total = userService.listForOrderGroupCount(query);
		PageUtils pageUtil = new PageUtils(sysUserList, total);
		return pageUtil;
	}

	/**
	 * 订单审核
	 */
	@ResponseBody
	@PostMapping("/orderAudit")
	public R orderAudit(@RequestParam("id") Long id){
		OrderListDO orderListDO = new OrderListDO();
		orderListDO.setId(id);
		orderListDO.setProp1("1");
		if(orderListService.update(orderListDO)>0){
			return R.ok();
		}
		return R.error();
	}

	
}
