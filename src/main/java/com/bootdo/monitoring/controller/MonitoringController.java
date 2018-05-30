package com.bootdo.monitoring.controller;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.*;
import com.bootdo.lamp.LampImpl;
import com.bootdo.lamp.service.LampService;
import com.bootdo.lamp.vo.LampVo;
import com.bootdo.monitoring.domain.LampDO;
import com.bootdo.monitoring.domain.MonitoringDO;
import com.bootdo.monitoring.domain.SetMealDO;
import com.bootdo.monitoring.domain.WeatherVo;
import com.bootdo.monitoring.service.LightService;
import com.bootdo.monitoring.service.MonitoringService;
import com.bootdo.monitoring.service.SetMealService;
import com.bootdo.monitoring.vo.EchartVo;
import com.bootdo.monitoring.vo.JKVo;
import com.bootdo.monitoring.vo.MonitoringVo;
import com.bootdo.socket.ByteUtils;
import com.bootdo.socket.SessionMap;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.tools.tree.NullExpression;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 机器表
 * 
 * @author kviuff
 * @email kviuff@163.com
 * @date 2018-02-10 21:38:03
 */
 
@Controller
@RequestMapping("/monitoring/monitoring")
public class MonitoringController {
	@Autowired
	private MonitoringService monitoringService;

	@Autowired
	private DictService sysDictService;

	@Autowired
	private LightService lampService;

	@Autowired
	UserService userService;

	@Autowired
	private SetMealService setMealService;
    /**
     * 机器管理页面
     * @param model
     * @return
     */
	@GetMapping()
	String Monitoring(Model model){
	    Map<String,String> map = monitoringService.getImportIndex();
	    model.addAttribute("map",map);
		return "monitoring/monitoring/monitoring";
	}

    /**
     * 机器管理页面 表格数据
     * @param params
     * @return
     */
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){

        Query query = new Query(params);
		List<MonitoringDO> monitoringList = monitoringService.list(query);
		int total = monitoringService.count(query);
		PageUtils pageUtils = new PageUtils(monitoringList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "monitoring/monitoring/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id,Model model){
		MonitoringDO monitoring = monitoringService.get(id);
		model.addAttribute("monitoring", monitoring);
	    return "monitoring/monitoring/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("monitoring:monitoring:add")
	public R save( MonitoringDO monitoring){
		if(monitoringService.save(monitoring)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( MonitoringDO monitoring){
		monitoringService.update(monitoring);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Long id){
		if(monitoringService.remove(id)>0){
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
		monitoringService.batchRemove(ids);
		return R.ok();
	}


    /**
     * 机器管理页面
     * @param params
     * @return
     */
	@ResponseBody
	@GetMapping("/voList")
	public PageUtils voList(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<MonitoringVo> monitoringList = monitoringService.volist(query);
		int total = monitoringService.voCount(query);
		PageUtils pageUtils = new PageUtils(monitoringList, total);
		return pageUtils;
	}

    /**
     * 机器管理查看详情
     * @param model
     * @param id 用户id
     * @return
     */
	@GetMapping("/v1")
	String Monitoring_v1(Model model,@RequestParam("id") String id){
		model.addAttribute("userId",id);
		Map<String,Object> map = new HashMap<>();
		map.put("userId",id);
		List<String> listRegion = monitoringService.getBelong(map);
		map.put("floor","1");
		List<String> listFloor = monitoringService.getBelong(map);
		List<SetMealDO> mealDOList = setMealService.list(map);
		model.addAttribute("mealDOList",mealDOList);
		model.addAttribute("region",listRegion);
		model.addAttribute("floor",listFloor);
		model.addAttribute("name",userService.get(Long.valueOf(id)).getUsername());
		return "monitoring/monitoring/monitoring_v1";
	}

    /**
     * 监控页面表格数据
     * @param params
     * @return
     */
    @ResponseBody
    @GetMapping("/JKList")
    public PageUtils JKList(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        List<JKVo> monitoringList = monitoringService.JKlist(query);
        int total = monitoringService.JKCount(query);
        PageUtils pageUtils = new PageUtils(monitoringList, total);
        return pageUtils;
    }

    /**
     * 新风监控页面
     * @param model
     * @return
     */
    @GetMapping("/newTrendJK")
    String newTrendJK(Model model){
        Map<String,String> map = monitoringService.getJKImportIndex("1");
        model.addAttribute("map",map);
        return "monitoring/monitoring/newTrendJK";
    }


    /**
     * 饮水机监控页面
     * @param model
     * @return
     */
    @GetMapping("/waterDispenserJK")
    String waterDispenserJK(Model model){
        Map<String,String> map = monitoringService.getJKImportIndex("2");
        model.addAttribute("map",map);
        return "monitoring/monitoring/waterDispenserJK";
    }

	/**
	 * 饮水机详情页面
	 * @param model
	 * @return
	 */
	@GetMapping("/waterDispenserJK_v1")
	String waterDispenserJK_v1(Model model,@RequestParam("userId") String userId,@RequestParam("belongRegion") String belongRegion){
		Map<String,String> map = new HashMap<>();
		map.put("userId",userId);
		map.put("belongRegion",belongRegion);
		model.addAttribute("map",map);
		return "monitoring/monitoring/waterDispenserJK_v1";
	}

	/**
	 * 新风详情页面
	 * @param model
	 * @return
	 */
	@GetMapping("/newTrendJK_v1")
	String newTrendJK_v1(Model model,@RequestParam("userId") String userId,@RequestParam("belongRegion") String belongRegion){
		Map<String,String> map = new HashMap<>();
		map.put("userId",userId);
		map.put("belongRegion",belongRegion);
		model.addAttribute("map",map);
		return "monitoring/monitoring/newTrendJK_v1";
	}



	/**
	 * 修改灯的场景
	 */
	@PostMapping( "/editScene")
	@ResponseBody
	@Log("修改灯的场景")
	public R editScene(@RequestParam("id") Long id, @RequestParam("scene") Long scene){

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
		params5.put("sceneId", scene);
		LampImpl.sceneSwitchs(params5);
		Map<String,Object> data = new HashMap<>();
		data.put("data",new HashMap<>());
		return R.ok(data);
	}

	/**
	 * 修改新风机的开关状态
	 */
	@PostMapping( "/editXinfengStatus")
	@ResponseBody
	@Log("修改新风机的开关状态")
	public R editXinfengStatus(@RequestParam("id") Long id, @RequestParam("status") String status, @RequestParam("monitorCode") String monitorCode){

		MonitoringDO monitoringDO = new MonitoringDO();
		monitoringDO.setId(id);
		SessionMap sessionMap = SessionMap.newInstance();
		if (SessionMap.num < 65535){
			SessionMap.num++;
		}else{
			SessionMap.num=0;
		}
		String num1 = ByteUtils.BinaryToHexString(ByteUtils.intToByteArray(SessionMap.num));
		num1 = num1.substring(0,2)+" "+num1.substring(2,4);
		String start = "43 ";//起始
		String length = "0D ";//数据长度
		String commondWord = "01 ";//命令字
		String xinfengStatus = "";//工作模式
		String fangearposition = "";//风机档位等
		if (status.equals("1")) {  // 开启
			monitoringDO.setStatus(status);
			xinfengStatus = "01 ";//工作模式
			fangearposition = "11 ";//风机档位等
		} else {  // 关闭
			monitoringDO.setStatus(status);
			xinfengStatus = "00 ";//工作模式
			fangearposition = "00 ";//风机档位等
		}
		String closeHour = "00 ";//定时关机时间
		String closeMinute = "00 ";
		String empty = "00 ";
		String empty1 = "00 ";

		String jiange = " 10 ";
		String empty2 = "00 ";
		String empty3 = "00 ";
		String empty4 = "00";
		String check = start + length + commondWord + xinfengStatus + fangearposition + closeHour + closeMinute + empty+empty1 + num1 + jiange + empty2 +empty3 +empty4;
		System.out.println("check==="+check);
		//校验
		String checkSum = ByteUtils.checksum(check);
		System.out.println("JAVA校验checkSum="+checkSum);
		System.out.println(checkSum);
		String result = check+" "+checkSum.substring(2,4);
		try {
			sessionMap.sendMessage(monitorCode, result);
			System.out.println(result);
		}catch (NullPointerException e){
			return R.error("机器未连接==========");
		}
		monitoringService.update(monitoringDO);
		Map<String,Object> data = new HashMap<>();
		data.put("data",new HashMap<>());
		return R.ok(data);
	}


	/**
	 * 修改新风机的档位
	 */
	@PostMapping( "/editXinfengStall")
	@ResponseBody
	@Log("修改新风机的档位")
	public R editXinfengStall(@RequestParam("monitorCode") String monitorCode, @RequestParam("stall") String stall){


		MonitoringDO monitoringDO = new MonitoringDO();
		monitoringDO.setId(monitoringService.getByCode(monitorCode).getId());
		monitoringDO.setMonitorCode(monitorCode);
		monitoringDO.setStall(stall);
		SessionMap sessionMap = SessionMap.newInstance();
		if (SessionMap.num < 65535){
			SessionMap.num++;
		}else{
			SessionMap.num=0;
		}
		String num1 = ByteUtils.BinaryToHexString(ByteUtils.intToByteArray(SessionMap.num));
		num1 = num1.substring(0,2)+" "+num1.substring(2,4);
		String start = "43 ";//起始
		String length = "0D ";//数据长度
		String commondWord = "01 ";//命令字
		String xinfengStatus = "";//工作模式
		String fangearposition = "";//风机档位等
		xinfengStatus = "01 ";//工作模式
		if (stall.equals("1")) {  // 开启
			fangearposition = "11 ";//风机档位等
		} else if (stall.equals("2")){  // 关闭
			fangearposition = "12 ";//风机档位等
		} else {
			fangearposition = "13 ";//风机档位等
		}
		String closeHour = "00 ";//定时关机时间
		String closeMinute = "00 ";
		String empty = "00 ";
		String empty1 = "00 ";

		String jiange = " 10 ";
		String empty2 = "00 ";
		String empty3 = "00 ";
		String empty4 = "00";
		String check = start + length + commondWord + xinfengStatus + fangearposition + closeHour + closeMinute + empty+empty1 + num1 + jiange + empty2 +empty3 +empty4;
		System.out.println("check==="+check);
		//校验
		String checkSum = ByteUtils.checksum(check);
		System.out.println("JAVA校验checkSum="+checkSum);
		System.out.println(checkSum);
		String result = check+" "+checkSum.substring(2,4);
		try{
			sessionMap.sendMessage(monitorCode, result);
			System.out.println(result);
		}catch (NullPointerException e){
			return R.error("机器未连接==========");
		}
		monitoringService.updateByMonitorCode(monitoringDO);
		Map<String,Object> data = new HashMap<>();
		data.put("data",new HashMap<>());
		return R.ok(data);
	}



	/**
	 * 机器管理页面
	 * @param model
	 * @return
	 */
	@GetMapping("/monitoringForUnbound")
	String MonitoringForUnbound(Model model){
		Map<String,String> map = monitoringService.getImportIndex();
		model.addAttribute("map",map);
		return "monitoring/monitoring/monitoring_unbound";
	}

	/**
	 * 机器管理页面 表格数据
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/listForUnbound")
	public PageUtils listForUnbound(@RequestParam Map<String, Object> params){
		Query query = new Query(params);
		List<MonitoringDO> monitoringList = monitoringService.listForUnboundDevice(query);
		int total = monitoringService.countForUnboundDevice(query);
		PageUtils pageUtils = new PageUtils(monitoringList, total);
		return pageUtils;
	}


    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/updateByCode")
    public R updateByCode( MonitoringDO monitoring){
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		if("1".equals(monitoring.getMachineType())){
			int day = Integer.parseInt(monitoring.getProp7())*7;
			try {
				monitoring.setProp6(f.format(f.parse(TimeUtils.plusDay2(day))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//饮水
		}else if("2".equals(monitoring.getMachineType())){
			//绑定水机的时候 发送设置好的 套餐流量
			SessionMap sessionMap = SessionMap.newInstance();
			try{
				sessionMap.bindingWaterMessage(monitoring.getMonitorCode(), Long.valueOf(monitoring.getProp8()));
			}catch (NullPointerException e){
				return R.error("机器未连接==========");
			}
//			Long flow = Long.valueOf(monitoring.getProp8());
//			monitoring.setProp9(String.valueOf(flow));
		}
        monitoringService.updateByMonitorCode(monitoring);
        return R.ok();
    }
	/**
	 * 微信用户页面
	 * @param model
	 * @return
	 */
	@GetMapping("/WechatIndex")
	String WechatIndex(Model model){
		return "weChatPages/content";
	}

	/**
	 * 机器管理页面 表格数据
	 * @param params
	 * @return
	 */
	@ResponseBody
	@PostMapping("/contentList")
	public List<MonitoringVo> contentList(@RequestParam Map<String, Object> params){
		//查询列表数据
		List<MonitoringVo> monitoringList = monitoringService.contentList(params);
		return monitoringList;
	}



	/**
	 * 班级页面
	 * @param model
	 * @return
	 */
	@GetMapping("/WechatClass")
	String WechatClass(Model model,@RequestParam("userId") String userId){
		model.addAttribute("userId",userId);
		return "weChatPages/class";
	}

	/**
	 * 班级页面 表格数据
	 * @param params
	 * @return
	 */
	@ResponseBody
	@PostMapping("/classList")
	public PageUtils classList(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<MonitoringDO> monitoringList = monitoringService.list(query);
		int total = monitoringService.count(query);
		PageUtils pageUtils = new PageUtils(monitoringList, total);
		return pageUtils;
	}

	/**
	 * 班级详情页面
	 * @param model
	 * @return
	 */
	@GetMapping("/WechatClassDetail")
	String WechatClassDetail(Model model,@RequestParam("id") Long id){
		model.addAttribute("id", id);
		return "weChatPages/classDetail";
	}

	/**
	 * 班级页面
	 * @return
	 */
	@ResponseBody
	@PostMapping("/detailList")
	public Map<String,Object> detailList(@RequestParam("id") Long id){
		//机器数据
		MonitoringDO monitoring = monitoringService.get(id);
		Map<String,Object> map = new HashMap<>();
		map.put("monitoring",monitoring);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String today = format.format(new Date());//获取今天日期
		List<DictDO> dictDOList = sysDictService.listByType("weather");
		WeatherVo weatherVo = new WeatherVo();
		if(dictDOList.size() > 0){
			DictDO dict =dictDOList.get(0);
			//如果日期是今天的 则直接取今天的天气预报 如果不是 则调取接口 获取今天的 天气
			if(dict.getName().equals(today)){
				JSONObject object = JSONObject.fromObject(dict.getValue());
				map.put("weather",object);
			}else{
				//获取天气
				Map<String, Object> weatherMap = WeatherUtil.getNowWeather();
				weatherVo.setHum(weatherMap.get("hum").toString());//湿度
				weatherVo.setNowDate(weatherMap.get("loc").toString().substring(0,10));//本地时间
				weatherVo.setTemp(weatherMap.get("temp").toString());//温度
				weatherVo.setWindSpd(weatherMap.get("WS").toString()); //风速
				weatherVo.setPm25(weatherMap.get("pm25").toString()); //pm2.5
				dict.setName(weatherVo.getNowDate());
				dict.setValue(JSONUtils.beanToJson(weatherVo,"WeatherVo"));
				sysDictService.update(dict);
				map.put("weather",weatherVo);
			}
		}else{
			map.put("weather",weatherVo);
		}
		return map;
	}


    /**
	 * 修改水的开关状态
	 */
	@PostMapping( "/editWaterStatus")
	@ResponseBody
	@Log("修改水的开关状态")
	public R editWaterStatus(@RequestParam("id") Long id, @RequestParam("status") String status, @RequestParam("monitorCode") String monitorCode){

		MonitoringDO monitoringDO = new MonitoringDO();
		monitoringDO.setId(id);
		SessionMap sessionMap = SessionMap.newInstance();
		//绑定套餐
		//sessionMap.bindingWaterMessage(monitorCode);
		// 发送开关机指令
		try{
			sessionMap.sendWaterMessage(monitorCode, monitorCode, status);
		}catch (NullPointerException e){
			return R.error("机器未连接==========");
		}
		monitoringDO.setStatus(status);
		monitoringService.update(monitoringDO);
		Map<String,Object> data = new HashMap<>();
		data.put("data",new HashMap<>());
		return R.ok(data);
	}

	/**
	 * 修改水的开关状态
	 */
	@PostMapping( "/reset")
	@ResponseBody
	@Log("修改水的复位")
	public R reset( @RequestParam("type") int type, @RequestParam("monitorCode") String monitorCode){
		System.out.println(type+"级复位----------"+monitorCode);
		SessionMap sessionMap = SessionMap.newInstance();
		try{
			//绑定套餐
			sessionMap.reset(type,monitorCode);
		}catch (NullPointerException e){
			return R.error("机器未连接==========");
		}
		return R.ok();
	}


	/**
	 * 修改定时关机状态
	 */
	@ResponseBody
	@RequestMapping("/updateCloseTime")
	public R updateCloseTime(@RequestParam("ids") String ids, @RequestParam("closetime") String closetime){
		if (StringUtils.isNotEmpty(ids)) {
			String[] idStr = ids.split(",");
			MonitoringDO monitoring = new MonitoringDO();
			for (String str : idStr) {
				monitoring.setId(Long.valueOf(str));
				monitoring.setCloseTime(closetime);
				monitoringService.update(monitoring);
			}
		}
		return R.ok();
	}

	/**
	 * 模拟客户端 测试用的
	 */
	@GetMapping("/testWind")
	public R updateCloseTime() {
		String str = "4465766963653a7465737420313631313236202020200000000000000000000000a020a6149dbf424a424c2041502056313730323134547d5e0fae0195789c4315810000000000000000000000000000161010000000ef001f0021d60d0a";
		try {
			//创建一个Socket,跟本机的8080端口连接
			Socket socket = new Socket("127.0.0.1", 8000);
			//使用Socket创建PrintWriter和BufferedReader进行读写数据
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//发送数据
			dos.write(ByteUtils.toBytes(str));
			dos.flush();
			System.out.println("发送：" + ByteUtils.toBytes(str).length);
			//接收数据
			String line = is.readLine();
			System.out.println("===:" + line);
			//关闭资源
			pw.close();
			is.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("发送错误");
		}
		return R.ok();
	}

	/**
	 * 充值
	 */
	@GetMapping("/Recharge")
	public R Recharge(String id) {
		SessionMap sessionMap = SessionMap.newInstance();
		sessionMap.Recharge(id);
		return R.ok();
	}
	/**
	 * 恢复出厂设置
	 */
	@GetMapping("/hf")
	public R hf(String id) {
		SessionMap sessionMap = SessionMap.newInstance();
		sessionMap.hf(id);
		return R.ok();
	}
	/**
	 * 水更改绑定套餐
	 */
	@ResponseBody
	@RequestMapping("/bind")
	public R bind(String monitorCode,String prop8) {
		MonitoringDO monitoring = new MonitoringDO();
		monitoring.setMonitorCode(monitorCode);
		monitoring.setProp8(prop8);
		try {
			SessionMap sessionMap = SessionMap.newInstance();
			sessionMap.bindingWaterMessage(monitorCode,Long.valueOf(prop8));
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("发送错误");
		}
		monitoringService.updateByMonitorCode(monitoring);
		return R.ok();
	}

	/**
	 * 水更改计时套餐
	 */
	@ResponseBody
	@RequestMapping("/changeTime")
	public R changeTime(String monitorCode,String prop7) {
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		int day = Integer.parseInt(prop7)*7;
		MonitoringDO monitoring = new MonitoringDO();
		monitoring.setMonitorCode(monitorCode);
		monitoring.setProp8("");
		try {
			monitoring.setProp5(f.format(new Date()));
			monitoring.setProp6(f.format(f.parse(TimeUtils.plusDay2(day))));
			monitoringService.updateByMonitorCode(monitoring);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error("发送错误");
		}
		return R.ok();
	}

	/**
	 * 全部开机
	 */
	@ResponseBody
	@RequestMapping("/openall")
	public R openall(@RequestParam("type") String type, @RequestParam("userId") String userId){


		if ("1".equals(type)) { // 新风
			Map<String, Object> map = new HashMap<>();
			map.put("machineType", "1");
			map.put("userId", userId);
			List<MonitoringDO> monitoringDOList = monitoringService.list(map);

			for (MonitoringDO monitoringDO : monitoringDOList) {
				SessionMap sessionMap = SessionMap.newInstance();
				if (SessionMap.num < 65535){
					SessionMap.num++;
				}else{
					SessionMap.num=0;
				}
				String num1 = ByteUtils.BinaryToHexString(ByteUtils.intToByteArray(SessionMap.num));
				num1 = num1.substring(0,2)+" "+num1.substring(2,4);
				String start = "43 ";//起始
				String length = "0D ";//数据长度
				String commondWord = "01 ";//命令字
				String xinfengStatus = "";//工作模式
				String fangearposition = "";//风机档位等
				xinfengStatus = "01 ";//工作模式
				fangearposition = "11 ";//风机档位等
				String closeHour = "00 ";//定时关机时间
				String closeMinute = "00 ";
				String empty = "00 ";
				String empty1 = "00 ";

				String jiange = " 10 ";
				String empty2 = "00 ";
				String empty3 = "00 ";
				String empty4 = "00";
				String check = start + length + commondWord + xinfengStatus + fangearposition + closeHour + closeMinute + empty+empty1 + num1 + jiange + empty2 +empty3 +empty4;
				System.out.println("check==="+check);
				//校验
				String checkSum = ByteUtils.checksum(check);
				System.out.println("JAVA校验checkSum="+checkSum);
				System.out.println(checkSum);
				String result = check+" "+checkSum.substring(2,4);
				try{
					sessionMap.sendMessage(monitoringDO.getMonitorCode(), result);
					System.out.println(result);
				}catch (NullPointerException e){
					return R.error("机器未连接==========");
				}
				MonitoringDO monitoringDO1 = new MonitoringDO();
				monitoringDO1.setId(monitoringDO.getId());
				monitoringDO1.setStatus("1");
				monitoringService.update(monitoringDO1);
			}

		} else if ("2".equals(type)) { // 饮水机
			Map<String, Object> map = new HashMap<>();
			map.put("machineType", "2");
			map.put("userId", userId);
			List<MonitoringDO> monitoringDOList = monitoringService.list(map);

			for (MonitoringDO monitoringDO : monitoringDOList) {
				String monitorCode = monitoringDO.getMonitorCode();
				SessionMap sessionMap = SessionMap.newInstance();
				//绑定套餐
				//sessionMap.bindingWaterMessage(monitorCode);
				// 发送开关机指令
				try{
				sessionMap.sendWaterMessage(monitorCode, monitorCode, "0");
				}catch (NullPointerException e){
					return R.error("机器未连接==========");
				}
				MonitoringDO monitoringDO1 = new MonitoringDO();
				monitoringDO1.setId(monitoringDO.getId());
				monitoringDO1.setStatus("0");
				monitoringService.update(monitoringDO1);
			}

		} else { // 灯
			Map<String, Object> map = new HashMap<>();
			map.put("machineType", "3");
			map.put("userId", userId);
			map.put("limit","1");
			map.put("offset",0);
			Query query = new Query(map);
			List<MonitoringDO> monitoringDO = monitoringService.list(query);
			//然后查询出这个用户下的所有灯 进行控制
			map.remove("machineType");
			map.remove("limit");
			map.remove("offset");
			List<LampDO> lampDOs = lampService.list(map);

			String lanerUserName = monitoringDO.get(0).getLanerUserName();
			String lanerPassword = monitoringDO.get(0).getLanerPassword();
			Map params1 = new HashMap();
			params1.put("userName", lanerUserName);
			params1.put("userPwd", lanerPassword);
			String result = LampImpl.loginToLamp(params1);
			JSONObject jsonObject = JSONObject.fromObject(result);
			String tokenId = jsonObject.get("tokenId").toString();
			for (LampDO lampDO : lampDOs) {
				Map params5 = new HashMap();
				params5.put("tokenId", tokenId);
				params5.put("toAddr", lampDO.getController());
				params5.put("uId", UUID.randomUUID().toString().replace("-", ""));
				params5.put("pwd", "172168");
				params5.put("realID", lampDO.getReturnId());
				params5.put("status", "ff");
				LampImpl.lampOpenOrCloseTurn(params5);
			}
		}

		return R.ok();
	}

	/**
	 * 全部关机
	 */
	@ResponseBody
	@RequestMapping("/closeall")
	public R closeall(@RequestParam("type") String type, @RequestParam("userId") String userId){

		if ("1".equals(type)) { // 新风
			Map<String, Object> map = new HashMap<>();
			map.put("machineType", "1");
			map.put("userId", userId);
			List<MonitoringDO> monitoringDOList = monitoringService.list(map);

			for (MonitoringDO monitoringDO : monitoringDOList) {
				SessionMap sessionMap = SessionMap.newInstance();
				if (SessionMap.num < 65535){
					SessionMap.num++;
				}else{
					SessionMap.num=0;
				}
				String num1 = ByteUtils.BinaryToHexString(ByteUtils.intToByteArray(SessionMap.num));
				num1 = num1.substring(0,2)+" "+num1.substring(2,4);
				String start = "43 "; // 起始
				String length = "0D ";//数据长度
				String commondWord = "01 ";//命令字
				String xinfengStatus = "";//工作模式
				String fangearposition = "";//风机档位等
				xinfengStatus = "00 ";//工作模式
				fangearposition = "00 ";//风机档位等
				String closeHour = "00 ";//定时关机时间
				String closeMinute = "00 ";
				String empty = "00 ";
				String empty1 = "00 ";

				String jiange = " 10 ";
				String empty2 = "00 ";
				String empty3 = "00 ";
				String empty4 = "00";
				String check = start + length + commondWord + xinfengStatus + fangearposition + closeHour + closeMinute + empty+empty1 + num1 + jiange + empty2 +empty3 +empty4;
				System.out.println("check==="+check);
				//校验
				String checkSum = ByteUtils.checksum(check);
				System.out.println("JAVA校验checkSum="+checkSum);
				System.out.println(checkSum);
				String result = check+" "+checkSum.substring(2,4);
				try {
					sessionMap.sendMessage(monitoringDO.getMonitorCode(), result);
				}catch (NullPointerException e){
					return R.error("机器未连接==========");
				}
				System.out.println(result);

				MonitoringDO monitoringDO1 = new MonitoringDO();
				monitoringDO1.setId(monitoringDO.getId());
				monitoringDO1.setStatus("1");
				monitoringService.update(monitoringDO1);
			}

		} else if ("2".equals(type)) { // 饮水机
			Map<String, Object> map = new HashMap<>();
			map.put("machineType", "2");
			map.put("userId", userId);
			List<MonitoringDO> monitoringDOList = monitoringService.list(map);

			for (MonitoringDO monitoringDO : monitoringDOList) {
				String monitorCode = monitoringDO.getMonitorCode();
				SessionMap sessionMap = SessionMap.newInstance();
				//绑定套餐
				//sessionMap.bindingWaterMessage(monitorCode);
				try{
				// 发送开关机指令
				sessionMap.sendWaterMessage(monitorCode, monitorCode, "1");
				}catch (NullPointerException e){
					return R.error("机器未连接==========");
				}
				MonitoringDO monitoringDO1 = new MonitoringDO();
				monitoringDO1.setId(monitoringDO.getId());
				monitoringDO1.setStatus("1");
				monitoringService.update(monitoringDO1);
			}

		} else { // 灯
			Map<String, Object> map = new HashMap<>();
			map.put("machineType", "3");
			map.put("userId", userId);
			map.put("limit","1");
			map.put("offset",0);
			Query query = new Query(map);
			//查询出用户下的其中一个灯的控制器 用于获取用户名和密码
			List<MonitoringDO> monitoringDO = monitoringService.list(query);
			String lanerUserName = monitoringDO.get(0).getLanerUserName();
			String lanerPassword = monitoringDO.get(0).getLanerPassword();
			Map params1 = new HashMap();
			params1.put("userName", lanerUserName);
			params1.put("userPwd", lanerPassword);
			String result = LampImpl.loginToLamp(params1);
			JSONObject jsonObject = JSONObject.fromObject(result);
			String tokenId = jsonObject.get("tokenId").toString();
			//然后查询出这个用户下的所有灯 进行控制
			map.remove("machineType");
			map.remove("limit");
			map.remove("offset");
			List<LampDO> lampDOs = lampService.list(map);
			for (LampDO lampDO : lampDOs) {
				Map params5 = new HashMap();
				params5.put("tokenId", tokenId);
				params5.put("toAddr", lampDO.getController());
				params5.put("uId", UUID.randomUUID().toString().replace("-", ""));
				params5.put("pwd", "172168");
				params5.put("realID", lampDO.getReturnId());
				params5.put("status", "00");
				LampImpl.lampOpenOrCloseTurn(params5);
			}

		}

		return R.ok();
	}

	/**
	 * 指定开机
	 */
	@ResponseBody
	@RequestMapping("/openselect")
	public R openselect(@RequestParam("type") String type, @RequestParam("ids[]") Long[] ids){


		if ("1".equals(type)) { // 新风
			for (Long id : ids) {
				MonitoringDO monitoringDO = monitoringService.get(id);

				SessionMap sessionMap = SessionMap.newInstance();
				if (SessionMap.num < 65535){
					SessionMap.num++;
				}else{
					SessionMap.num=0;
				}
				String num1 = ByteUtils.BinaryToHexString(ByteUtils.intToByteArray(SessionMap.num));
				num1 = num1.substring(0,2)+" "+num1.substring(2,4);
				String start = "43 ";//起始
				String length = "0D ";//数据长度
				String commondWord = "01 ";//命令字
				String xinfengStatus = "";//工作模式
				String fangearposition = "";//风机档位等
				xinfengStatus = "01 ";//工作模式
				fangearposition = "11 ";//风机档位等
				String closeHour = "00 ";//定时关机时间
				String closeMinute = "00 ";
				String empty = "00 ";
				String empty1 = "00 ";

				String jiange = " 10 ";
				String empty2 = "00 ";
				String empty3 = "00 ";
				String empty4 = "00";
				String check = start + length + commondWord + xinfengStatus + fangearposition + closeHour + closeMinute + empty+empty1 + num1 + jiange + empty2 +empty3 +empty4;
				System.out.println("check==="+check);
				//校验
				String checkSum = ByteUtils.checksum(check);
				System.out.println("JAVA校验checkSum="+checkSum);
				System.out.println(checkSum);
				String result = check+" "+checkSum.substring(2,4);
				try{
				sessionMap.sendMessage(monitoringDO.getMonitorCode(), result);
				System.out.println(result);
				}catch (NullPointerException e){
					return R.error("机器未连接==========");
				}
				MonitoringDO monitoringDO1 = new MonitoringDO();
				monitoringDO1.setId(monitoringDO.getId());
				monitoringDO1.setStatus("1");
				monitoringService.update(monitoringDO1);
			}
		} else if ("2".equals(type)) { // 饮水机

			for (Long id : ids) {
				MonitoringDO monitoringDO = monitoringService.get(id);
				String monitorCode = monitoringDO.getMonitorCode();
				SessionMap sessionMap = SessionMap.newInstance();
				//绑定套餐
				//sessionMap.bindingWaterMessage(monitorCode);
				try {
					// 发送开关机指令
					sessionMap.sendWaterMessage(monitorCode, monitorCode, "0");
				}catch (NullPointerException e){
					return R.error("机器未连接==========");
				}
				MonitoringDO monitoringDO1 = new MonitoringDO();
				monitoringDO1.setId(monitoringDO.getId());
				monitoringDO1.setStatus("0");
				monitoringService.update(monitoringDO1);
			}

		} else { // 灯

			Map<String,Object> map = new HashMap<>();
			map.put("ids",ids);
			//获取每个灯的控制器
			List<MonitoringDO> monitoringDOList = monitoringService.list(map);
			String lanerUserName = monitoringDOList.get(0).getLanerUserName();
			String lanerPassword = monitoringDOList.get(0).getLanerPassword();
			//进行登录
			Map params1 = new HashMap();
			params1.put("userName", lanerUserName);
			params1.put("userPwd", lanerPassword);
			String result = LampImpl.loginToLamp(params1);
			JSONObject jsonObject = JSONObject.fromObject(result);
			String tokenId = jsonObject.get("tokenId").toString();
			for(MonitoringDO monitoringDO : monitoringDOList){
				//根据控制器查询出每个控制器下的灯
				map.put("controller",monitoringDO.getContollerAddr());
				map.put("address",monitoringDO.getReturnid());
				map.remove("ids");
				List<LampDO> lampDOs = lampService.list(map);
				for (LampDO lampDO : lampDOs) {
					Map params5 = new HashMap();
					params5.put("tokenId", tokenId);
					params5.put("toAddr", lampDO.getController());
					params5.put("uId", UUID.randomUUID().toString().replace("-", ""));
					params5.put("pwd", "172168");
					params5.put("realID", lampDO.getReturnId());
					params5.put("status", "ff");
					LampImpl.lampOpenOrCloseTurn(params5);
				}
			}


		}

		return R.ok();
	}

	/**
	 * 指定关机
	 */
	@ResponseBody
	@RequestMapping("/closeselect")
	public R closeselect(@RequestParam("type") String type, @RequestParam("ids[]") Long[] ids){


		if ("1".equals(type)) { // 新风
			for (Long id : ids) {
				MonitoringDO monitoringDO = monitoringService.get(id);

				SessionMap sessionMap = SessionMap.newInstance();
				if (SessionMap.num < 65535){
					SessionMap.num++;
				}else{
					SessionMap.num=0;
				}
				String num1 = ByteUtils.BinaryToHexString(ByteUtils.intToByteArray(SessionMap.num));
				num1 = num1.substring(0,2)+" "+num1.substring(2,4);
				String start = "43 ";//起始
				String length = "0D ";//数据长度
				String commondWord = "01 ";//命令字
				String xinfengStatus = "";//工作模式
				String fangearposition = "";//风机档位等
				xinfengStatus = "00 ";//工作模式
				fangearposition = "00 ";//风机档位等
				String closeHour = "00 ";//定时关机时间
				String closeMinute = "00 ";
				String empty = "00 ";
				String empty1 = "00 ";

				String jiange = " 10 ";
				String empty2 = "00 ";
				String empty3 = "00 ";
				String empty4 = "00";
				String check = start + length + commondWord + xinfengStatus + fangearposition + closeHour + closeMinute + empty+empty1 + num1 + jiange + empty2 +empty3 +empty4;
				System.out.println("check==="+check);
				//校验
				String checkSum = ByteUtils.checksum(check);
				System.out.println("JAVA校验checkSum="+checkSum);
				System.out.println(checkSum);
				String result = check+" "+checkSum.substring(2,4);
				try{
				sessionMap.sendMessage(monitoringDO.getMonitorCode(), result);
				System.out.println(result);
				}catch (NullPointerException e){
					return R.error("机器未连接==========");
				}
				MonitoringDO monitoringDO1 = new MonitoringDO();
				monitoringDO1.setId(monitoringDO.getId());
				monitoringDO1.setStatus("1");
				monitoringService.update(monitoringDO1);
			}
		} else if ("2".equals(type)) { // 饮水机

			for (Long id : ids) {
				MonitoringDO monitoringDO = monitoringService.get(id);
				String monitorCode = monitoringDO.getMonitorCode();
				SessionMap sessionMap = SessionMap.newInstance();
				//绑定套餐
				//sessionMap.bindingWaterMessage(monitorCode);
				try{
				// 发送开关机指令
				sessionMap.sendWaterMessage(monitorCode, monitorCode, "1");
				}catch (NullPointerException e){
					return R.error("机器未连接==========");
				}
				MonitoringDO monitoringDO1 = new MonitoringDO();
				monitoringDO1.setId(monitoringDO.getId());
				monitoringDO1.setStatus("1");
				monitoringService.update(monitoringDO1);
			}

		} else { // 灯

			Map<String,Object> map = new HashMap<>();
			map.put("ids",ids);
			//获取每个灯的控制器
			List<MonitoringDO> monitoringDOList = monitoringService.list(map);
			String lanerUserName = monitoringDOList.get(0).getLanerUserName();
			String lanerPassword = monitoringDOList.get(0).getLanerPassword();
			//进行登录
			Map params1 = new HashMap();
			params1.put("userName", lanerUserName);
			params1.put("userPwd", lanerPassword);
			String result = LampImpl.loginToLamp(params1);
			JSONObject jsonObject = JSONObject.fromObject(result);
			String tokenId = jsonObject.get("tokenId").toString();
			for(MonitoringDO monitoringDO : monitoringDOList){
				//根据控制器查询出每个控制器下的灯
				map.put("controller",monitoringDO.getContollerAddr());
				map.remove("ids");
				List<LampDO> lampDOs = lampService.list(map);
				for (LampDO lampDO : lampDOs) {
					Map params5 = new HashMap();
					params5.put("tokenId", tokenId);
					params5.put("toAddr", lampDO.getController());
					params5.put("uId", UUID.randomUUID().toString().replace("-", ""));
					params5.put("pwd", "172168");
					params5.put("realID", lampDO.getReturnId());
					params5.put("status", "00");
					LampImpl.lampOpenOrCloseTurn(params5);
				}
			}

		}

		return R.ok();
	}

	/**
	 * 新增老新风机
	 */
	@ResponseBody
	@RequestMapping("/addOld")
	public R addOld( MonitoringDO monitoring){
		monitoring.setCreateTime(System.currentTimeMillis());
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		int day = Integer.parseInt(monitoring.getProp7())*7;
		try {
			monitoring.setProp6(f.format(f.parse(TimeUtils.plusDay2(day))));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		monitoringService.save(monitoring);
		return R.ok();
	}



}
