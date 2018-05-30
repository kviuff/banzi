package com.bootdo.account;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.config.Constant;
import com.bootdo.common.utils.*;
import com.bootdo.monitoring.domain.SetMealDO;
import com.bootdo.monitoring.service.SetMealService;
import com.bootdo.system.domain.AreaDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.AreaService;
import com.bootdo.system.service.UserService;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 账号管理
 * 
 * @author dn
 * @email nan_du@126.com
 * @date 2018-03-3
 */

@Controller
@RequestMapping("/accountManger/account")
public class AccountController {
	@Autowired
	UserService userService;

	@Autowired
	private AreaService areaService;

	@Autowired
	private SetMealService setMealService;
    /**
     * 账号管理页面
     *
     * @return
     */
	@GetMapping()
	String Monitoring(Model model){
		UserDO userDO = ShiroUtils.getUser();
		model.addAttribute("accountnumberType", userDO.getAccountnumberType());
		return "accountManage/accountGL";
	}

    /**
     * 账号管理页面 表格数据
     * @param params
     * @return
     */
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		Query query = new Query(params);
		List<UserDO> sysUserList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtil = new PageUtils(sysUserList, total);
		return pageUtil;
	}

	/**
	 * 跳转添加页面
	 *
	 * @return
	 */
	@GetMapping("/addPage")
	String addPage(Model model, @RequestParam("accountnumberType") String accountnumberType){
		String html ="";
		if("1".equals(accountnumberType)){//添加客户
			html ="accountManage/addClient";
		}else if("2".equals(accountnumberType)){//添加经销商
			List<AreaDO> area = areaService.queryAll();
			model.addAttribute("area",area);
			html ="accountManage/addAgency";
		} else if("3".equals(accountnumberType)){//添加销售
			html ="accountManage/addSellBefore";
		}else if("4".equals(accountnumberType)){//添加客服
			html ="accountManage/addSellAfter";
		}else if("5".equals(accountnumberType)){//添加总监
			html ="accountManage/addChief";
		}
		model.addAttribute("user",new UserDO());//用户
		model.addAttribute("accountnumberType",accountnumberType);
		return html;
	}

	/**
	 * 创建用户
	 * @param user
	 * @return
     */
	@PostMapping("/save")
	@ResponseBody
	R save(UserDO user) {
		Long userId = ShiroUtils.getUserId();
		user.setUserIdCreate(userId);//创建人id
		user.setUserNameCreate(ShiroUtils.getUser().getUsername());
		String accountnumberType = user.getAccountnumberType();
		List<Long> role = new ArrayList<>();
		if ("1".equals(accountnumberType)) { // 经销商
			role.add(Long.valueOf("59"));
			user.setRoleIds(role);//角色权限
			user.setStatus(1);//状态
		} else if ("2".equals(accountnumberType)) { // 销售
			role.add(Long.valueOf("60"));
			user.setRoleIds(role);//角
			user.setStatus(1);//状态
		} else if ("3".equals(accountnumberType)) { // 客服
			role.add(Long.valueOf("62"));
			user.setRoleIds(role);//角色权限
			user.setStatus(1);//状态
		} else if ("4".equals(accountnumberType)) { // 总监
			role.add(Long.valueOf("61"));
			user.setRoleIds(role);//角色权限
			user.setStatus(1);//状态
		} else if ("0".equals(accountnumberType)) { // 客户
			role.add(Long.valueOf("63"));
			user.setRoleIds(role);//角色权限
			user.setStatus(1);//状态
		} else { // 超级管理员
			role.add(Long.valueOf("1"));
			user.setRoleIds(role);//角色权限
			user.setStatus(1);//状态
		}


		//如果被创建的是销售账户
		if("2".equals(user.getAccountnumberType())){
			//因为是总监创建销售账号 那么经销商账号则是总监的创建人
			user.setAgencyId(ShiroUtils.getUser().getUserIdCreate());
			user.setChiefId(ShiroUtils.getUserId());//总监账号id
		}
		//如果创建的是客户账号 则需要存储经销商id
		if("0".equals(user.getAccountnumberType())){
			//获取创建人的账号类型
			UserDO ac = ShiroUtils.getUser();
			//如果创建者是经销商 则直接存id
			if("1".equals(ac.getAccountnumberType())){
				user.setAgencyId(userId);
			}else if("4".equals(ac.getAccountnumberType())){//如果是总监 则获取总监的创建人
				user.setAgencyId(ac.getUserIdCreate());
				user.setChiefId(ac.getUserId());
			}else if("2".equals(ac.getAccountnumberType())){//如果是销售 则获取销售的经销商id
				user.setAgencyId(ac.getAgencyId());
				user.setChiefId(ac.getChiefId());
			}
		}
		if("4".equals(user.getAccountnumberType())){
			user.setAgencyId(ShiroUtils.getUserId());
		}
		if("3".equals(user.getAccountnumberType())){
			user.setAgencyId(ShiroUtils.getUserId());
		}
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		if (userService.save(user) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 跳转查看账号页面
	 *
	 * @return
	 */
	@GetMapping("/lookPage")
	String lookPage(Model model, @RequestParam("id") Long id,@RequestParam("type") String type){
		String html ="";
		UserDO user = userService.get(id);
		if(null != user){
			if("0".equals(user.getAccountnumberType())){//添加客户
				html ="accountManage/addClient";
			}else if("1".equals(user.getAccountnumberType())){//添加经销商
				List<AreaDO> area = areaService.queryAll();
				model.addAttribute("area",area);
				html ="accountManage/addAgency";
			} else if("2".equals(user.getAccountnumberType())){//添加销售
				html ="accountManage/addSellBefore";
			}else if("3".equals(user.getAccountnumberType())){//添加客服
				html ="accountManage/addSellAfter";
			}else if("4".equals(user.getAccountnumberType())){//添加总监
				html ="accountManage/addChief";
			}
			model.addAttribute("accountnumberType",user.getAccountnumberType());//账号类型
			model.addAttribute("user",user);//用户
			model.addAttribute("type",type);//跳转页面类型
		}
		return html;
	}

	@Log("更新用户")
	@PostMapping("/update")
	@ResponseBody
	R update(UserDO user) {
		List<Long> role = new ArrayList<>();
		role.add(59L);
		user.setRoleIds(role);//角色权限
		if (userService.update(user) > 0) {
			return R.ok();
		}
		return R.error();
	}


	/**
	 * 客户管理页面
	 *
	 * @return
	 */
	@GetMapping("/customManagerPage")
	String customManagerPage(Model model, @RequestParam(value = "userId",required = false) String userId){
		if(userId==null || "".equals(userId)){
			model.addAttribute("userIdCreate",null);
		}else{
			model.addAttribute("userIdCreate",userId);
		}
		Map<String, Object> params = new HashMap<>();
		params.put("offset",0);
		params.put("limit",100);
		params.put("accountnumberType","2");
		UserDO userDO = ShiroUtils.getUser();
		if(null != userDO.getAccountnumberType() && !"".equals(userDO.getAccountnumberType())){
			if("4".equals(userDO.getAccountnumberType())){
				params.put("agencyId", userDO.getUserIdCreate());
			}else if("2".equals(userDO.getAccountnumberType())){
				params.put("agencyId", userDO.getAgencyId());
			}else{
				params.put("agencyId", userDO.getUserId());
			}
		}
		Query query = new Query(params);
		List<UserDO> sysUserList = userService.list(query);
		model.addAttribute("UserList",sysUserList);
		return "accountManage/customManagerList";
	}

	/**
	 * 客户管理页面 表格数据
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/customManagerList")
	public PageUtils customManagerList(@RequestParam Map<String, Object> params){
		//如果传递了需要查看的用户id下的客户 则按照传过来的id查询 如果为传递则需要对用户的身份进行判断
 		if(params.get("userIdCreate") == null || "".equals(params.get("userIdCreate"))){
			UserDO user = ShiroUtils.getUser();
			//如果是销售 则显示此销售所创建的用户
			if ("2".equals(user.getAccountnumberType())){ // 销售
				params.put("userIdCreate",user.getUserId());
			}else if("1".equals(user.getAccountnumberType())){ // 如果是经销商则显示所有的
				//params.put("agencyId",user.getUserId());
				params.put("userIdCreate",user.getUserId());
			}else if("4".equals(user.getAccountnumberType())){ // 如果是总监
				params.put("chiefId",user.getUserId());
				//params.put("userIdCreate",user.getUserId());
			}else if ("3" == user.getAccountnumberType()){ // 客服
				params.put("userIdCreate",user.getUserId());
			}
		}
		Query query = new Query(params);
		List<UserDO> sysUserList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtil = new PageUtils(sysUserList, total);
		return pageUtil;
	}

	/**
	 * 客户管理详情
	 * @return
	 */
	@GetMapping("/customDetail")
	String customDetail(Model model, @RequestParam("userId") String userId){
		model.addAttribute("userId", userId);
		return "accountManage/customDetail";
	}

	/**
	 * 客户管理-添加设备
	 * @return
	 */
	@GetMapping("/addDevice")
	String addDevice(Model model, @RequestParam("userId") String userId){
		UserDO userDO = userService.get(Long.parseLong(userId));
		Map<String,Object> map = new HashMap<>();
		map.put("userId",userId);
		List<SetMealDO> mealDOList = setMealService.list(map);
		model.addAttribute("mealDOList",mealDOList);
		model.addAttribute("userDO", userDO);
		return "accountManage/addDevice";
	}


	@Log("批量迁移客户")
	@PostMapping("/transferClient")
	@ResponseBody
	R batchRemove(@RequestParam(value="ids[]",required = false) Long[] userIds,@RequestParam("chief") Long chiefId) {
		UserDO saleUser = userService.get(chiefId);//获取被转移销售的信息
		List<UserDO> list = new ArrayList<>();
		for(Long userId : userIds){
			UserDO userDO = new UserDO();
			userDO.setUserId(userId);
			userDO.setChiefId(saleUser.getChiefId());
			userDO.setUserIdCreate(saleUser.getUserId());
			userDO.setUserNameCreate(saleUser.getUsername());
			list.add(userDO);
		}
		int r = userService.updateBatch(list);
		if (r > 0) {
			return R.ok();
		}

		return R.error();
	}

}
