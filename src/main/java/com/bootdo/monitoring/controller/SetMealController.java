package com.bootdo.monitoring.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ShiroUtils;
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

import com.bootdo.monitoring.domain.SetMealDO;
import com.bootdo.monitoring.service.SetMealService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 套餐表
 * 
 * @author dunan
 * @email nan_du@126.com
 * @date 2018-05-28 22:39:37
 */
 
@Controller
@RequestMapping("/monitoring/setMeal")
public class SetMealController {
	@Autowired
	private SetMealService setMealService;
	
	@GetMapping()
	String SetMeal(){
	    return "monitoring/setMeal/setMeal";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		Long userId= ShiroUtils.getUserId();
		params.put("userId",userId);
		//查询列表数据
        Query query = new Query(params);
		List<SetMealDO> setMealList = setMealService.list(query);
		int total = setMealService.count(query);
		PageUtils pageUtils = new PageUtils(setMealList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "monitoring/setMeal/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id,Model model){
		SetMealDO setMeal = setMealService.get(id);
		model.addAttribute("setMeal", setMeal);
	    return "monitoring/setMeal/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( SetMealDO setMeal){
		Long userId= ShiroUtils.getUserId();
		setMeal.setUserId(userId);
		setMeal.setType(0);
		if(setMealService.save(setMeal)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( SetMealDO setMeal){
		setMealService.update(setMeal);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Long id){
		if(setMealService.remove(id)>0){
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
		setMealService.batchRemove(ids);
		return R.ok();
	}
	
}
