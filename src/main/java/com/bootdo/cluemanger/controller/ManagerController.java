package com.bootdo.cluemanger.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.bootdo.app.Clue;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import net.sf.json.JSONArray;
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

import com.bootdo.cluemanger.domain.ManagerDO;
import com.bootdo.cluemanger.service.ManagerService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 线索管理
 * 
 * @author kviuff
 * @email kviuff@163.com
 * @date 2018-03-19 19:37:26
 */
 
@Controller
@RequestMapping("/cluemanger/manager")
public class ManagerController {
	@Autowired
	private ManagerService managerService;
	@Autowired
	private UserService userService;
	
	@GetMapping()
	String Manager(){
	    return "cluemanger/manager";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		UserDO userDO = ShiroUtils.getUser();
		if ("2".equals(userDO.getAccountnumberType())) { // 销售账号，只能看自己录入的线索
			params.put("prop1", String.valueOf(userDO.getUserId()));
		} else if ("4".equals(userDO.getAccountnumberType())) { // 总监账号，自己录入的线索和所属销售录入的线索
			Map<String, Object> map = new HashMap();
			map.put("chiefId", userDO.getUserId());
			List<UserDO> list = userService.list(map); // 获取总监下的所有销售
			String userIds = "";
			for (UserDO userDO1 : list) {
				String userId = userDO1.getUserId() + "";
				userIds = userId + ",";
			}
			params.put("prop1", userIds.substring(0, userIds.length() - 1));
		}
        Query query = new Query(params);
		List<ManagerDO> managerList = managerService.list(query);
		int total = managerService.count(query);
		PageUtils pageUtils = new PageUtils(managerList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "cluemanger/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id,Model model){
		ManagerDO manager = managerService.get(id);
		model.addAttribute("manager", manager);
	    return "cluemanger/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( ManagerDO manager){
		manager.setCreateTime(System.currentTimeMillis());
		String visitTime = manager.getVisitTime();
		String visitDetail = manager.getVisitDetail();
		String visitTitle = manager.getVisitTitle();
		String[] visitTimes = visitTime.split(",");
		String[] visitDetails = visitDetail.split(",");
		String[] visitTitles = visitTitle.split(",");
		StringBuffer visitJson = new StringBuffer();
		visitJson.append("[");
		for (int i = 0; i < visitTimes.length; i++) {
			visitJson.append("{");
			visitJson.append("\"visitTime\":\"" + visitTimes[i] + "\",");
			visitJson.append("\"visitDetail\":\"" + visitDetails[i] + "\",");
			visitJson.append("\"visitTitle\":\"" + visitTitles[i] + "\"");
			visitJson.append("}");
			if (i != visitTimes.length - 1) {
				visitJson.append(",");
			}
		}
		visitJson.append("]");
		manager.setTimeDetail(visitJson.toString());
		UserDO userDO = ShiroUtils.getUser();
		manager.setProp1(String.valueOf(userDO.getUserId()));
		manager.setProp2(userDO.getUsername());
	    int result = managerService.save(manager);
		if(result > 0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( ManagerDO manager){

		String visitTitle = manager.getVisitTitle();

//		ManagerDO managerDO = managerService.get(manager.getId());
//		String timeDetail = managerDO.getTimeDetail();
//
//		JSONArray array = JSONArray.fromObject(timeDetail);
		//List<Clue> list = JSONArray.toList(array, Clue.class);

		List<Clue> list = new ArrayList<Clue>();


		String visitTime = manager.getVisitTime();
		String visitDetail = manager.getVisitDetail();
		String[] visitTimes = visitTime.split(",");
		String[] visitDetails = visitDetail.split(",");
		String[] visitTitles = visitTitle.split(",");
		for (int i = 0; i < visitTimes.length; i++) {
			Clue clue = new Clue();
			clue.setVisitDetail(visitDetails[i]);
			clue.setVisitTime(visitTimes[i]);
			clue.setVisitTitle(visitTitles[i]);
			list.add(clue);
		}
		manager.setTimeDetail(JSON.toJSON(list).toString());
		manager.setUpdateTime(System.currentTimeMillis());

		managerService.update(manager);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Long id){
		if(managerService.remove(id)>0){
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
		managerService.batchRemove(ids);
		return R.ok();
	}
	
}
