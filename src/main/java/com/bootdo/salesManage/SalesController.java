package com.bootdo.salesManage;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.config.Constant;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 销售管理管理
 * 
 * @author dn
 * @email nan_du@126.com
 * @date 2018-03-3
 */
 
@Controller
@RequestMapping("/salesMange/sale")
public class SalesController {
	@Autowired
	UserService userService;

    /**
     * 销售管理页面
     *
     * @return
     */
	@GetMapping("/sell")
	String Monitoring(Model model){
		Map<String, Object> params = new HashMap<>();
		params.put("offset",0);
		params.put("limit",100);
		params.put("accountnumberType","4");
		UserDO userDO = ShiroUtils.getUser();
		if(null != userDO.getAccountnumberType() && !"".equals(userDO.getAccountnumberType())){
			if("4".equals(userDO.getAccountnumberType())){
				params.put("agencyId", userDO.getUserIdCreate());
			}else{
				params.put("agencyId", userDO.getUserId());
			}
		}
		Query query = new Query(params);
		List<UserDO> sysUserList = userService.list(query);
		model.addAttribute("UserList",sysUserList);
		return "salesMange/sellGL";
	}

    /**
     * 销售管理页面 表格数据
     * @param params
     * @return
     */
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		if(null != ShiroUtils.getUser().getAccountnumberType() && !"".equals(ShiroUtils.getUser().getAccountnumberType())){
			//如果是经销商可以看到当前名下的所有销售
			if("1".equals(ShiroUtils.getUser().getAccountnumberType())){
				params.put("agencyId", ShiroUtils.getUserId());
			}else{
				params.put("chiefId", ShiroUtils.getUserId());
			}
		}
		Query query = new Query(params);
		List<UserDO> sysUserList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtil = new PageUtils(sysUserList, total);
		return pageUtil;
	}

	/**
	 * 总监理页面
	 *
	 * @return
	 */
	@GetMapping("/chief")
	String chief(){
		return "salesMange/chiefInspectorGL";
	}

	/**
	 * 总监管理页面 表格数据
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/chiefList")
	public PageUtils chiefList(@RequestParam Map<String, Object> params){
		if(null != ShiroUtils.getUser().getAccountnumberType() && !"".equals(ShiroUtils.getUser().getAccountnumberType())){
			params.put("agencyId", ShiroUtils.getUserId());
		}
		params.put("accountnumberType","4");
		Query query = new Query(params);
		List<UserDO> sysUserList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtil = new PageUtils(sysUserList, total);
		return pageUtil;
	}

	@Log("批量迁移销售")
	@PostMapping("/transferSale")
	@ResponseBody
	R batchRemove(@RequestParam(value="ids[]",required = false) Long[] userIds,@RequestParam("chief") Long chiefId,@RequestParam("chiefName") String chiefName) {
		List<UserDO> list = new ArrayList<>();
		for(Long userId : userIds){
			UserDO userDO = new UserDO();
			userDO.setUserId(userId);
			userDO.setChiefId(chiefId);
			userDO.setUserIdCreate(chiefId);
			userDO.setUserNameCreate(chiefName);
			list.add(userDO);
		}
		int r = userService.updateBatch(list);
		if (r > 0) {
			return R.ok();
		}

		return R.error();
	}


}
