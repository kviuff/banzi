package com.bootdo.system.controller;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.collect.Maps;
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

import com.bootdo.system.domain.AreaDO;
import com.bootdo.system.service.AreaService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 地区表
 * 
 * @author dunan
 * @email nan_du@126.com
 * @date 2018-03-07 15:15:28
 */
 
@Controller
@RequestMapping("/system/area")
public class AreaController {
	@Autowired
	private AreaService areaService;
	
	@GetMapping()
	String Area(){
	    return "system/area/area";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AreaDO> areaList = areaService.list(query);
		int total = areaService.count(query);
		PageUtils pageUtils = new PageUtils(areaList, total);
		return pageUtils;
	}

	//获取下级地区
	@PostMapping(value = "/getChildArea")
	public
	@ResponseBody
	Map<String, Object> getChildArea(@RequestParam(value = "id") String parentid) throws JsonGenerationException, JsonMappingException,
			Exception {
		Map<String, Object> map = Maps.newHashMap();

		List<AreaDO> areas = areaService.queryChildList(parentid);
		map.put("result", areas);
		map.put("success", "true");
		return map;
	}
	
}
