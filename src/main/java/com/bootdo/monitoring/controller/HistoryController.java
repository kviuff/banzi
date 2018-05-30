package com.bootdo.monitoring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.*;
import com.bootdo.monitoring.domain.WeatherVo;
import com.bootdo.monitoring.vo.EchartVo;
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

import com.bootdo.monitoring.domain.HistoryDO;
import com.bootdo.monitoring.service.HistoryService;

/**
 * 机器表历史记录表
 * 
 * @author kviuff
 * @email kviuff@163.com
 * @date 2018-02-10 21:38:03
 */
 
@Controller
@RequestMapping("/monitoring/history")
public class HistoryController {
	@Autowired
	private HistoryService historyService;

	@Autowired
	private DictService sysDictService;
	
	@GetMapping()
	String History(Model model,@RequestParam("id") String id){
		model.addAttribute("monitorId",id);
	    return "monitoring/history/history";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<HistoryDO> historyList = historyService.list(query);
		int total = historyService.count(query);
		PageUtils pageUtils = new PageUtils(historyList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "monitoring/history/add";
	}

	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id,Model model){
		HistoryDO history = historyService.get(id);
		model.addAttribute("history", history);
	    return "monitoring/history/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( HistoryDO history){
		if(historyService.save(history)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( HistoryDO history){
		historyService.update(history);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Long id){
		if(historyService.remove(id)>0){
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
		historyService.batchRemove(ids);
		return R.ok();
	}

	@GetMapping("/waterHistory")
	String waterHistory(Model model,@RequestParam("id") String id){
		model.addAttribute("monitorId",id);
		return "monitoring/history/waterHistory";
	}

	/**
	 * 新风机曲线图
	 */
	@ResponseBody
	@RequestMapping("/chartXf")
	public R chartXf( EchartVo echartsVo){
		if(echartsVo.getDay()==null || "".equals(echartsVo.getDay())){
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
			echartsVo.setDay(sdf.format(new Date()));
		}

		Map<String,Object> map = new HashMap<>();
		map.put("data",historyService.getGroupXf(echartsVo));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String today = format.format(new Date());//获取今天日期
		List<DictDO> dictDOList = sysDictService.listByType("weather");
		WeatherVo weatherVo = new WeatherVo();
		DictDO dict =dictDOList.get(0);
		//如果日期是今天的 则直接取今天的天气预报 如果不是 则调取接口 获取今天的 天气
		if(dict.getName().equals(today)){
			JSONObject object = JSONObject.fromObject(dict.getValue());
			map.put("wpm",object.get("pm25"));
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
			map.put("wpm",weatherVo.getPm25());
		}
		return R.ok(map);
	}


	/**
	 * 饮水机曲线图
	 */
	@ResponseBody
	@RequestMapping("/chartYs")
	public R chartYs( EchartVo echartsVo){
		if(echartsVo.getDay()==null || "".equals(echartsVo.getDay())){
			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
			echartsVo.setDay(sdf.format(new Date()));
		}
		Map<String,Object> map = new HashMap<>();
		map.put("data",historyService.getGroupYs(echartsVo));
		return R.ok(map);
	}
}
