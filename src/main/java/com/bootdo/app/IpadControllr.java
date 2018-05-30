package com.bootdo.app;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.*;
import com.bootdo.monitoring.domain.LampDO;
import com.bootdo.monitoring.domain.LightSettingDO;
import com.bootdo.monitoring.domain.MonitoringDO;
import com.bootdo.monitoring.domain.WeatherVo;
import com.bootdo.monitoring.service.LightService;
import com.bootdo.monitoring.service.LightSettingService;
import com.bootdo.monitoring.service.MonitoringService;
import com.bootdo.system.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ipad")
public class IpadControllr extends BaseController {

    @Autowired
    private MonitoringService monitoringService;

    @Autowired
    private DictService sysDictService;

    @Autowired
    UserService userService;

    @Autowired
    private LightService lampService;

    @Autowired
    private LightSettingService lightSettingService;
    /**
     * 获取班级列表
     */
    @PostMapping( "/classList")
    @ResponseBody
    public JSONObject classList(@RequestParam("userId") String userId){
        JSONObject jsonObj = new JSONObject();
        Map<String,Object> map = new HashMap<>();
        map.put("userId", userId);
        List<String> listRegion = monitoringService.getBelong(map);
        if(listRegion.size()>0){
            jsonObj.put("data", JSONArray.fromObject(listRegion, JSONUtils.getJsonConfig()));
            jsonObj.put("code","0");
            jsonObj.put("msg","操作成功");
        }else {
            jsonObj.put("code","1");
            jsonObj.put("msg","无数据");
            jsonObj.put("data",new HashMap<>());
        }
        return jsonObj;
    }

    /**
     * 主页
     */
    @PostMapping( "/index")
    @ResponseBody
    public JSONObject index(@RequestParam("belongRegion") String belongRegion ,@RequestParam("userId") String userId,@RequestParam("monitorCode") String monitorCode){
        JSONObject jsonObj = new JSONObject();
        Map<String,Object> dataMap = new HashMap<>();
        Map<String,Object> map = new HashMap<>();
        map.put("userId", userId);//用户id
        map.put("belongRegion", belongRegion);//区域
        map.put("limit",10);
        map.put("offset",0);
        Query query = new Query(map);
        List<MonitoringDO> monitoringList = monitoringService.list(query);
        MonitoringDO water = monitoringService.getByCode(monitorCode);
        monitoringList.add(water);
        for(MonitoringDO monitoringDO : monitoringList){
            if(monitoringDO.getMachineType().equals("3")){
                monitoringDO.setParamJson1(monitoringDO.getParamJson());
                monitoringDO.setParamJson("{}");
            }
        }
        //用户的这个班级的机器列表
        dataMap.put("monitoringList",JSONArray.fromObject(monitoringList, JSONUtils.getJsonConfig()));
        //获取天气
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String today = format.format(new Date());//获取今天日期
        List<DictDO> dictDOList = sysDictService.listByType("weather");
        WeatherVo weatherVo = new WeatherVo();

        if(dictDOList.size() > 0){
            DictDO dict =dictDOList.get(0);
            //如果日期是今天的 则直接取今天的天气预报 如果不是 则调取接口 获取今天的 天气
            if(dict.getName().equals(today)){
                JSONObject object = JSONObject.fromObject(dict.getValue());
                dataMap.put("weather",object);
            }else{
                //获取天气
                Map<String, Object> weatherMap = WeatherUtil.getNowWeather();
                weatherVo.setTemp(weatherMap.get("temp").toString());//温度
                weatherVo.setPm25(weatherMap.get("pm25").toString()); //pm2.5
                dict.setName(weatherVo.getNowDate());
                dict.setValue(JSONUtils.beanToJson(weatherVo,"WeatherVo"));
                sysDictService.update(dict);
                dataMap.put("weather",weatherVo);
            }
        }else{
            dataMap.put("weather",weatherVo);
        }
        jsonObj.put("data",dataMap);
        return jsonObj;
    }

    @PostMapping("/checkPass")
    @ResponseBody
    R ajaxLogin(String password,Long userId) {
        String username = userService.get(userId).getUsername();
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            Map<String,Object> data = new HashMap<>();
            data.put("data",userId);
            return R.ok(data);
        } catch (UnknownAccountException e) {
            return R.error("用户或密码错误");
        } catch (IncorrectCredentialsException e) {
            return R.error("用户或密码错误");
        } catch (AuthenticationException e) {
            return R.error("用户或密码错误");
        }
    }

    /**
     * 灯页面数据
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/lampList")
    public JSONObject lampList(@RequestParam("id") Long id ){
        JSONObject jsonObj = new JSONObject();
        Map<String,Object> dataMap = new HashMap<>();
        MonitoringDO monitoringDO = monitoringService.get(id);
        LightSettingDO lightSetting = lightSettingService.get(Long.valueOf(monitoringDO.getUserId()));
        if(lightSetting == null){
            dataMap.put("lightSetting",new LightSettingDO());//灯光显示参数
        }else{
            dataMap.put("lightSetting",lightSetting);//灯光显示参数
        }
        dataMap.put("mode",monitoringDO.getParamJson());//情景模式
        jsonObj.put("data",dataMap);
        jsonObj.put("code","0");
        jsonObj.put("msg","操作成功");
        return jsonObj;
    }


}
