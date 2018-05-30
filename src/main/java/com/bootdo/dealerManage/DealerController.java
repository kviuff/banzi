package com.bootdo.dealerManage;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.monitoring.domain.MonitoringDO;
import com.bootdo.monitoring.service.MonitoringService;
import com.bootdo.monitoring.vo.JKVo;
import com.bootdo.monitoring.vo.MonitoringVo;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 经销商管理
 * 
 * @author dn
 * @email nan_du@126.com
 * @date 2018-03-3
 */
 
@Controller
@RequestMapping("/dealerMange/dealer")
public class DealerController {
	@Autowired
	UserService userService;

    /**
     * 经销商管理页面
     *
     * @return
     */
	@GetMapping()
	String Monitoring(){
		return "dealerManage/agencyGL";
	}

    /**
     * 经销商管理页面 表格数据
     * @param params
     * @return
     */
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		params.put("accountnumberType","1");
		Query query = new Query(params);
		List<UserDO> sysUserList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtil = new PageUtils(sysUserList, total);
		return pageUtil;
	}




}
