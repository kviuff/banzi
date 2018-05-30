package com.bootdo.monitoring.controller;

import java.util.List;
import java.util.Map;

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

import com.bootdo.monitoring.domain.LightSettingDO;
import com.bootdo.monitoring.service.LightSettingService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 灯光显示参数
 * 
 * @author dunan
 * @email nan_du@126.com
 * @date 2018-05-18 19:54:59
 */
 
@Controller
@RequestMapping("/monitoring/lightSetting")
public class LightSettingController {
	@Autowired
	private LightSettingService lightSettingService;
	
	@GetMapping()
	String LightSetting(){
	    return "monitoring/lightSetting/lightSetting";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<LightSettingDO> lightSettingList = lightSettingService.list(query);
		int total = lightSettingService.count(query);
		PageUtils pageUtils = new PageUtils(lightSettingList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "monitoring/lightSetting/add";
	}

	@GetMapping("/edit/{userId}")
	String edit(@PathVariable("userId") Long userId,Model model){
		LightSettingDO lightSetting = lightSettingService.get(userId);
		if(lightSetting ==null){
			lightSetting = new LightSettingDO();
			lightSetting.setUserId(userId);
			model.addAttribute("lightSetting", lightSetting);
		}else{
			model.addAttribute("lightSetting", lightSetting);
		}
	    return "monitoring/lightSetting/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( LightSettingDO lightSetting){
		if(lightSettingService.save(lightSetting)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( LightSettingDO lightSetting){
		LightSettingDO lightSetting1 = lightSettingService.get(lightSetting.getUserId());
		if(lightSetting1 == null){
			if(lightSettingService.save(lightSetting)>0){
				return R.ok();
			}
		}else {
			if(lightSettingService.update(lightSetting)>0){
				return R.ok();
			};
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Long userId){
		if(lightSettingService.remove(userId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Long[] userIds){
		lightSettingService.batchRemove(userIds);
		return R.ok();
	}
	
}
