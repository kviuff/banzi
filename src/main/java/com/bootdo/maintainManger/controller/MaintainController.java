package com.bootdo.maintainManger.controller;

import com.bootdo.common.utils.*;
import com.bootdo.monitoring.domain.MonitoringDO;
import com.bootdo.monitoring.service.MonitoringService;
import com.bootdo.monitoring.vo.JKVo;
import com.bootdo.monitoring.vo.MonitoringVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 维护系统
 * 
 * @author dunan
 * @email nan_du@126.com
 * @date 2018-03-9
 */
 
@Controller
@RequestMapping("/maintain")
public class MaintainController {
	@Autowired
	private MonitoringService monitoringService;

    /**
     * 在线报修页面
     * @param model
     * @return
     */
	@GetMapping("/repaired")
	String Monitoring(Model model){
		Map<String,String> map = monitoringService.getRepairIndex();
	    model.addAttribute("map",map);
		return "maintainManger/repaired";
	}

	/**
	 * 在线报修页面数据
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/voList")
	public PageUtils voList(@RequestParam Map<String, Object> params){
		/*if("1".equals(params.get("machineType"))){
			params.put("main","1");
			params.put("machineType","");
		}*/
		//查询列表数据
		Query query = new Query(params);
		List<MonitoringVo> monitoringList = monitoringService.volist(query);
		int total = monitoringService.voCount(query);
		PageUtils pageUtils = new PageUtils(monitoringList, total);
		return pageUtils;
	}

    /**
     * 在线报修 查看详情页面 表格数据
     * @param params
     * @return
     */
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		/*if("1".equals(params.get("machineType"))){
			params.put("main","1");
			params.put("machineType","");
		}*/
		//查询列表数据
        Query query = new Query(params);
		List<MonitoringDO> monitoringList = monitoringService.list(query);
		int total = monitoringService.count(query);
		PageUtils pageUtils = new PageUtils(monitoringList, total);
		return pageUtils;
	}

	/**
	 * 在线报修 查看详情页面
	 * @param model
	 * @param id 用户id
	 * @return
	 */
	@GetMapping("/repairedList")
	String repairedList(Model model,@RequestParam("id") String id,@RequestParam("prop1") String prop1){
	    model.addAttribute("userId",id);
		model.addAttribute("prop1",prop1);
		return "maintainManger/repairedList";
	}


	/**
	 * 报修单详情
	 * @param model
	 * @param id 用户id
	 * @return
	 */
	@GetMapping("/v1")
	String repair_v1(Model model,@RequestParam("id") String id){
		MonitoringDO monitoringDO = monitoringService.get(Long.valueOf(id));
		model.addAttribute("monitoringId", id);
		model.addAttribute("monitoringDO", monitoringDO);
		return "maintainManger/applyRepaired";
	}

	/**
	 * 机器保养
	 * @return
	 */
	@GetMapping("/machineUpkeep")
	String machineUpkeep(Model model){
		return "maintainManger/machineUpkeep";
	}

	/**
	 * 机器保养页面数据
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/keep")
	public PageUtils keep(@RequestParam Map<String, Object> params){
		if("4".equals(params.get("machineType").toString())){
			params.put("machine","1");
		}else{
			params.put("machine",Integer.parseInt(params.get("machineType").toString()));
		}
		//查询列表数据
		Query query = new Query(params);
		List<MonitoringVo> monitoringList = monitoringService.getByKeep(query);
		int total = monitoringService.getByKeepCount(query);
		PageUtils pageUtils = new PageUtils(monitoringList, total);
		return pageUtils;
	}

	/**
	 * 机器保养用户机器列表页面
	 * @return
	 */
	@GetMapping("/machineUp")
	String machineUp(Model model,String userId,String machineType){
		model.addAttribute("userId",userId);
		model.addAttribute("machineType",machineType);
		return "maintainManger/machineUp";
	}

	/**
	 * 机器保养机器页面数据
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/keepList")
	public PageUtils keepList(@RequestParam Map<String, Object> params){
		params.put("machine",Integer.parseInt(params.get("machineType").toString()));
		//查询列表数据
		Query query = new Query(params);
		List<MonitoringDO> monitoringList = monitoringService.list(query);
		int total = monitoringService.count(query);
		PageUtils pageUtils = new PageUtils(monitoringList, total);
		return pageUtils;
	}

	/**
	 * 报修单详情
	 * @param model
	 * @return
	 */
	@GetMapping("/upkeepDetail1")
	String upkeepDetail1(Model model,String userId,String machineType,Long[] ids){
		Map<String, Object> params = new HashMap<>();
		params.put("userId",userId);
		params.put("machineType",machineType);
		params.put("machine",Integer.parseInt(machineType));
		List<String> strList = monitoringService.getGroupCode(params);
		params.put("limit",10);
		params.put("offset",0);
		Query query = new Query(params);
		List<MonitoringVo> monitoringList = monitoringService.getByKeep(query);
		model.addAttribute("allNum",ids.length);
		model.addAttribute("userName",monitoringList.get(0).getUserName());
		String str ="";
		if(strList.size()>0){
			for(String s:strList){
				str+=s+",";
			}
		}
		model.addAttribute("ids", StringUtils.join(ids, ","));
		model.addAttribute("monitorModeCode",str.substring(0,str.length()-1));
		model.addAttribute("machineType",machineType);
		model.addAttribute("userId",userId);
		return "maintainManger/upkeepDetail1";
	}

	/**
	 * 保养记录
	 * @return
	 */
	@GetMapping("/machineMaintain")
	String machineMaintain(){
		return "maintainManger/machineMaintain";
	}





}
