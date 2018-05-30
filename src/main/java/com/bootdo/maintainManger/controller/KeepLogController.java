package com.bootdo.maintainManger.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.*;
import com.bootdo.monitoring.domain.MonitoringDO;
import com.bootdo.monitoring.service.MonitoringService;
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

import com.bootdo.maintainManger.domain.KeepLogDO;
import com.bootdo.maintainManger.service.KeepLogService;

/**
 * 保养记录表
 * 
 * @author dunan
 * @email nan_du@126.com
 * @date 2018-03-11 17:00:54
 */
 
@Controller
@RequestMapping("/maintainManger/keepLog")
public class KeepLogController {
	@Autowired
	private KeepLogService keepLogService;

	@Autowired
	private MonitoringService monitoringService;

	/**
	 * 回访详情页面
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/upkeepDetail")
	String KeepLog(Model model,@RequestParam("id") String id){
		KeepLogDO keepLog = keepLogService.get(Long.valueOf(id));
		model.addAttribute("keepLog",keepLog);
		return "maintainManger/upkeepDetail";
	}

	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<KeepLogDO> keepLogList = keepLogService.list(query);
		int total = keepLogService.count(query);
		PageUtils pageUtils = new PageUtils(keepLogList, total);
		return pageUtils;
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( KeepLogDO keepLog){
		if(keepLog != null) {
			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
			Map<String, Object> params = new HashMap<>();
			params.put("userId",keepLog.getUserId());
			params.put("machineType",keepLog.getMachineType());
			params.put("limit",keepLog.getProp4());
			params.put("offset",0);
			params.put("ids",keepLog.getIds());
			Query query = new Query(params);
			List<MonitoringDO> monitoringList = monitoringService.list(query);
			int day =0;
			//新风,老饮水机
			if("1".equals(keepLog.getMachineType()) || "4".equals(keepLog.getMachineType())){

				try {
					if(monitoringList.size() > 0){
						for(MonitoringDO monitoringDO : monitoringList){
							//获取保养间隔天数
							day = Integer.parseInt(monitoringDO.getProp7())*7;
							monitoringDO.setProp6(f.format(f.parse(TimeUtils.plusDay2(day))));
						}
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				//批量更新到期维护时间
				monitoringService.updateBatch(monitoringList);
			}else if("2".equals(keepLog.getMachineType()) && monitoringList.size() > 0){
				try {
					for(MonitoringDO monitoringDO : monitoringList) {
						//如果是时间计算流量的
						if ("".equals(monitoringDO.getProp8())) {
							//获取保养间隔天数
							day = Integer.parseInt(monitoringDO.getProp7()) * 7;
							monitoringDO.setProp6(f.format(f.parse(TimeUtils.plusDay2(day))));
						}
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				monitoringService.updateBatch(monitoringList);
			}
		}
		if(keepLogService.save(keepLog)>0){
			return R.ok();
		}
		return R.error();
	}

	
}
