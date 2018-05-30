package com.bootdo.monitoring.controller;

import java.util.*;

import com.bootdo.lamp.LampImpl;
import com.bootdo.lamp.vo.LampVo;
import com.bootdo.monitoring.domain.LampDO;
import com.bootdo.monitoring.domain.LightCtrVo;
import com.bootdo.monitoring.domain.MonitoringDO;
import com.bootdo.monitoring.service.LightService;
import com.bootdo.monitoring.service.MonitoringService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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

import com.bootdo.monitoring.domain.LightCtrDO;
import com.bootdo.monitoring.service.LightCtrService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 灯控制器表
 * 
 * @author dunan
 * @email nan_du@126.com
 * @date 2018-05-11 18:21:25
 */
 
@Controller
@RequestMapping("/monitoring/lightCtr")
public class LightCtrController {
	@Autowired
	private LightCtrService lightCtrService;

	@Autowired
	private LightService lampService;

	@Autowired
	private MonitoringService monitoringService;

	@GetMapping("/lightUser")
	String LightUser(){

		return "monitoring/lightCtr/lightUser";
	}
	
	@GetMapping("/lightCtr")
	String LightCtr(Model model,Long userId){
		model.addAttribute("userId",userId);
		return "monitoring/lightCtr/lightCtr";
	}

	@ResponseBody
	@GetMapping("/allList")
	public PageUtils alllist(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<LightCtrDO> lightCtrList = lightCtrService.list(query);
		int total = lightCtrService.allCount(query);
		PageUtils pageUtils = new PageUtils(lightCtrList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<LightCtrDO> lightCtrList = lightCtrService.list(query);
		int total = lightCtrService.count(query);
		PageUtils pageUtils = new PageUtils(lightCtrList, total);
		return pageUtils;
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( LightCtrDO lightCtr){
		if(lightCtrService.save(lightCtr)>0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 区域列表页
	 * @param model
     * @return
     */
	@GetMapping("/lightRegion")
	String lightRegion(Model model,Long id){
		model.addAttribute("id",id);
		return "monitoring/lightCtr/lightRegion";
	}

	/**
	 * 区域列表页
	 * @param params
	 * @return
     */
	@ResponseBody
	@GetMapping("/regionList")
	public PageUtils regionList(@RequestParam Map<String, Object> params){
		//查询控制器数据
		LightCtrDO lightCtr = lightCtrService.get(Long.valueOf(params.get("id").toString()));
		String lanerUserName = lightCtr.getLanerusername();//用户名
		String lanerPassword = lightCtr.getLanerpassword();//密码
		String contollerAddr = lightCtr.getControllerAddr();//控制器地址

		Map params1 = new HashMap();
		params1.put("userName", lanerUserName);
		params1.put("userPwd", lanerPassword);
		String result = LampImpl.loginToLamp(params1);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String tokenId = jsonObject.get("tokenId").toString();

		Map params5 = new HashMap();
		params5.put("tokenId", tokenId);
		params5.put("toAddr", contollerAddr);
		//获取区域列表
		String resultList = LampImpl.getRegionListUrl(params5);
		List<LightCtrVo> LightCtrVoList = new ArrayList<>();
		jsonObject = JSONObject.fromObject(resultList);
		String dataStr = jsonObject.get("data").toString();
		JSONArray array = JSONArray.fromObject(dataStr);
		Iterator<JSONArray> itr = array.iterator();
		//解析数据
		while (itr.hasNext()) {
			//创建控制器区域列表
			LightCtrVo vo = new LightCtrVo();
			JSONObject temp = JSONObject.fromObject(itr.next());
			vo.setId(String.valueOf(temp.get("id")));
			vo.setName(String.valueOf(temp.get("name")));
			vo.setControllerAddr(contollerAddr);
			vo.setLanerpassword(lanerPassword);
			vo.setLanerusername(lanerUserName);
			vo.setUserId(lightCtr.getUserId().toString());
			vo.setUserName(lightCtr.getUserName());
			vo.setMonitorCode(lightCtr.getControllerId());
			System.out.println("------2----->"+temp.toString());
			LightCtrVoList.add(vo);
		}
		LightCtrVoList.remove(LightCtrVoList.size()-1);
		PageUtils pageUtils = new PageUtils(LightCtrVoList, LightCtrVoList.size());
		return pageUtils;
	}


	/**
	* 绑定区域
	* @return
	*/
	@ResponseBody
	@PostMapping("/bindRegion")
	public R bindRegion(MonitoringDO monitoringDO){
		monitoringDO.setMachineType("3");
		if(monitoringService.save(monitoringDO)>0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 情景模式页
	 * @param model
	 * @param userId
	 * @return
	 */
	@GetMapping("/lightMode")
	String lightMode(Model model,Long userId,String id){
		model.addAttribute("id",id);
		model.addAttribute("userId",userId);
		Map<String,Object> map = new HashMap<>();
		map.put("userId",userId);
		List<String> listRegion = monitoringService.getBelong(map);
		model.addAttribute("region",listRegion);
		return "monitoring/lightCtr/lightMode";
	}

	/**
	 * 情景模式列表页
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/modeList")
	public PageUtils modeList(@RequestParam Map<String, Object> params){
		//查询控制器数据
		LightCtrDO lightCtr = lightCtrService.get(Long.valueOf(params.get("id").toString()));
		String lanerUserName = lightCtr.getLanerusername();//用户名
		String lanerPassword = lightCtr.getLanerpassword();//密码
		String contollerAddr = lightCtr.getControllerAddr();//控制器地址

		Map params1 = new HashMap();
		params1.put("userName", lanerUserName);
		params1.put("userPwd", lanerPassword);
		String result = LampImpl.loginToLamp(params1);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String tokenId = jsonObject.get("tokenId").toString();

		Map params5 = new HashMap();
		params5.put("tokenId", tokenId);
		params5.put("toAddr", contollerAddr);
		//获取情景列表
		String resultList = LampImpl.sceneList(params5);
		jsonObject = JSONObject.fromObject(resultList);
		String dataStr = jsonObject.get("data").toString();
		JSONArray array = JSONArray.fromObject(dataStr);
		List<LightCtrVo> lightModeList = JSONArray.toList(array, LightCtrVo.class);
		PageUtils pageUtils = new PageUtils(lightModeList, lightModeList.size());
		return pageUtils;
	}

	/**
	 * 绑定情景模式
	 * @return
	 */
	@ResponseBody
	@PostMapping("/bindMode")
	public R bindMode(String ids,String belongRegion,String userId){
		Map<String,Object> map = new HashMap<>();
		map.put("belongRegion",belongRegion);
		map.put("userId",userId);
		List<MonitoringDO> monitoringDOList = monitoringService.list(map);
		MonitoringDO monitoringDO = monitoringDOList.get(0);
		monitoringDO.setBelongRegion(belongRegion);
		monitoringDO.setUserId(userId);
		monitoringDO.setParamJson(ids);
		if(monitoringService.update(monitoringDO)>0){
			return R.ok();
		}
		return R.error();
	}
	
}
