package com.bootdo.maintainManger.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.monitoring.domain.MonitoringDO;
import com.bootdo.monitoring.service.MonitoringService;
import com.bootdo.monitoring.vo.MonitoringVo;
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

import com.bootdo.maintainManger.domain.RepairLogDO;
import com.bootdo.maintainManger.service.RepairLogService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 维修记录表
 * 
 * @author dunan
 * @email nan_du@126.com
 * @date 2018-03-09 18:55:50
 */
 
@Controller
@RequestMapping("/maintainManger/repairLog")
public class RepairLogController {
	@Autowired
	private RepairLogService repairLogService;

	@Autowired
	private MonitoringService monitoringService;

	
	@GetMapping()
	String RepairLog(Model model){
		Map<String,String> map = repairLogService.getMaintainIndex();
		model.addAttribute("map",map);
	    return "repairLog/repairedNote";
	}

	/**
	 * 维修记录表格
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<RepairLogDO> repairLogList = repairLogService.list(query);
		int total = repairLogService.count(query);
		PageUtils pageUtils = new PageUtils(repairLogList, total);
		return pageUtils;
	}


	/**
	 * 申请保修
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( RepairLogDO repairLog){
		System.out.println(repairLog.getUserId());
		MonitoringDO monitoringDO = new MonitoringDO();
		monitoringDO.setId(repairLog.getMonitoringId());//机器id
		Date now=new Date();
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		monitoringDO.setProp2(f.format(now));//报修时间
		monitoringDO.setProp1("1");//状态改为已报修
		repairLog.setRepairTime(f.format(now));//报修时间
        repairLog = repairLogService.saveRepair(repairLog);
		if( repairLog != null){
			monitoringDO.setProp4(String.valueOf(repairLog.getRepairId()));
			if(monitoringService.update(monitoringDO )> 0){
				return R.ok();
			}
			return R.error();
		}
		return R.error();
	}

	/**
	 * 派单
	 */
	@ResponseBody
	@RequestMapping("/save1")
	public R save1( RepairLogDO repairLog){
		MonitoringDO monitoringDO = new MonitoringDO();
		monitoringDO.setId(repairLog.getMonitoringId());//机器id
		Date now=new Date();
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		monitoringDO.setProp3(f.format(now));//派单时间
		monitoringDO.setProp1("2");//状态改为已派单
		repairLog.setDispatchTime(f.format(now));
		repairLogService.update(repairLog);
		monitoringDO.setUpdateTime(System.currentTimeMillis());//更新时间
		if(monitoringService.update(monitoringDO )> 0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 回访
	 */
	@ResponseBody
	@RequestMapping("/save2")
	public R save2( RepairLogDO repairLog){
		MonitoringDO monitoringDO = monitoringService.get(repairLog.getMonitoringId());
		monitoringDO.setProp2("");//报修时间
		monitoringDO.setProp3("");//派单时间
		monitoringDO.setProp4("");//报修日志表id
		monitoringDO.setProp1("0");//状态改为未报修
		repairLog.setUserId(monitoringDO.getUserId());
		repairLog.setUserName(monitoringDO.getUserName());
		repairLogService.update(repairLog);
		monitoringDO.setUpdateTime(System.currentTimeMillis());//更新时间
		if(monitoringService.update(monitoringDO )> 0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 报修单详情
	 * @param model
	 * @param id 用户id
	 * @return
	 */
	@GetMapping("/v2")
	String repair_v2(Model model,@RequestParam("id") String id){
		String repairId = monitoringService.get(Long.valueOf(id)).getProp4();
		if(repairId != null && !"".equals(repairId)){
			RepairLogDO repairLog = repairLogService.get(Integer.parseInt(repairId));
			model.addAttribute("repairLog",repairLog);
		}
		return "maintainManger/repairedReturn";
	}

	/**
	 * 报修单详情
	 * @param model
	 * @param id 用户id
	 * @return
	 */
	@GetMapping("/v3")
	String repair_v3(Model model,@RequestParam("id") String id){
		String repairId = monitoringService.get(Long.valueOf(id)).getProp4();
		if(repairId != null && !"".equals(repairId)){
			RepairLogDO repairLog = repairLogService.get(Integer.parseInt(repairId));
			model.addAttribute("repairLog",repairLog);
		}
		return "maintainManger/repairedReturn1";
	}

	/**
	 * 维修记录详情
	 * @param model
	 * @param id 用户id
	 * @return
	 */
	@GetMapping("/repairListPage")
	String repairListPage(Model model,@RequestParam("id") String id){
		model.addAttribute("userId",id);
		return "repairLog/repairedList";
	}

	/**
	 * 维修记录详情表格
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/repairList")
	public PageUtils repairList(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<RepairLogDO> repairLogList = repairLogService.repairList(query);
		int total = repairLogService.count(query);
		PageUtils pageUtils = new PageUtils(repairLogList, total);
		return pageUtils;
	}

	/**
	 * 维修单详情
	 * @param model
	 * @param id 用户id
	 * @return
	 */
	@GetMapping("/repairDetail")
	String repairDetail(Model model,@RequestParam("id") String id){
		RepairLogDO repairLog = repairLogService.get(Integer.parseInt(id));
		model.addAttribute("repairLog",repairLog);
		return "repairLog/repairedReturnNoDetail";
	}
	
}
