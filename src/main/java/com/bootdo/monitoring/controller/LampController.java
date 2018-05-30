package com.bootdo.monitoring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.common.annotation.Log;
import com.bootdo.lamp.LampImpl;
import com.bootdo.monitoring.domain.MonitoringDO;
import com.bootdo.monitoring.service.MonitoringService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.monitoring.domain.LampDO;
import com.bootdo.monitoring.service.LightService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 灯
 * 
 * @author dunan
 * @email nan_du@126.com
 * @date 2018-03-28 21:26:39
 */
 
@Controller
@RequestMapping("/monitoring/lamp")
public class LampController {
	@Autowired
	private LightService lampService;
	@Autowired
	private MonitoringService monitoringService;


	@GetMapping("/page")
	String Lamp(Model model,@RequestParam("id") long id){
		MonitoringDO monitoringDO1 = monitoringService.get(id);
		//获取最新的灯
		String controller = lampService.getLight(monitoringDO1);
		model.addAttribute("controller",controller);
		model.addAttribute("address",monitoringDO1.getReturnid());
		model.addAttribute("id",id);
		return "monitoring/lamp/lamp";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<LampDO> lampList = lampService.list(query);
		int total = lampService.count(query);
		PageUtils pageUtils = new PageUtils(lampList, total);
		return pageUtils;
	}
	



	/**
	 * 修改灯的开关状态
	 */
	@PostMapping( "/updatelampopenorclose")
	@ResponseBody
	@Log("修改灯的开关状态")
	public R updateLampOpenOrClose(@RequestParam("returnId") String returnId, @RequestParam("id") Long id, @RequestParam("status") String status){
		MonitoringDO monitoringDO1 = monitoringService.get(id);
		String lanerUserName = monitoringDO1.getLanerUserName();
		String lanerPassword = monitoringDO1.getLanerPassword();
		String contollerAddr = monitoringDO1.getContollerAddr();

		MonitoringDO monitoringDO = new MonitoringDO();
		monitoringDO.setId(id);

		Map params1 = new HashMap();
		params1.put("userName", lanerUserName);
		params1.put("userPwd", lanerPassword);
		String result = LampImpl.loginToLamp(params1);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String tokenId = jsonObject.get("tokenId").toString();

		Map params5 = new HashMap();
		params5.put("tokenId", tokenId);
		params5.put("toAddr", contollerAddr);
		params5.put("uId", UUID.randomUUID().toString().replace("-", ""));
		params5.put("pwd", "172168");
		params5.put("realID", returnId);
		if (status.equals("1")) {  // 开启
			params5.put("status", "ff");
			LampImpl.lampOpenOrCloseTurn(params5);
		} else {  // 关闭
			params5.put("status", "00");
			LampImpl.lampOpenOrCloseTurn(params5);
		}
		Map<String,Object> data = new HashMap<>();
		data.put("data",new HashMap<>());
		return R.ok(data);
	}




}
