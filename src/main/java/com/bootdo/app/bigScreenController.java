package com.bootdo.app;

import com.bootdo.common.utils.JSONUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.monitoring.domain.MonitoringDO;
import com.bootdo.monitoring.service.MonitoringService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 2018/5/23.
 */
@Controller
@RequestMapping("/screen")
public class bigScreenController {

    @Autowired
    private MonitoringService monitoringService;

    /**
     * 选择屏幕
     */
    @GetMapping("/bingIndex")
    public String bingIndex() {

        return "h5/bigIndex";
    }

    /**
     * 横屏
     */
    @GetMapping( "/HorizontalIndex")
    public String HorizontalIndex(Model model) {
        Long userId= ShiroUtils.getUserId();
        model.addAttribute("userId",userId);
        return "h5/index1";
    }

    /**
     * 横屏数据
     */
    @PostMapping( "/Horizontal")
    @ResponseBody
    public JSONObject Horizontal( @RequestParam("userId") String userId,@RequestParam("machineType") String machineType) {
        JSONObject jsonObj = new JSONObject();
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);//用户id
        map.put("machineType", machineType);
        List<MonitoringDO> monitoringList = monitoringService.findBig(map);
        if(monitoringList.size()>0){
            jsonObj.put("data", JSONArray.fromObject(monitoringList, JSONUtils.getJsonConfig()));
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
     * 竖屏 新风
     */
    @GetMapping( "/VerticalIndex")
    public String VerticalIndex(Model model) {
        Long userId= ShiroUtils.getUserId();
        model.addAttribute("userId",userId);
        return "h5/vertical_index";
    }

    /**
     * 横屏数据
     */
    @PostMapping( "/VerticalIndexList")
    @ResponseBody
    public JSONObject VerticalIndexList( @RequestParam("userId") String userId,@RequestParam("machineType") String machineType) {
        JSONObject jsonObj = new JSONObject();
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);//用户id
        map.put("machineType", machineType);
        List<MonitoringDO> monitoringList = monitoringService.findBig(map);
        if(monitoringList.size()>0){
            jsonObj.put("data", JSONArray.fromObject(monitoringList, JSONUtils.getJsonConfig()));
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
     * 竖屏 水
     */
    @GetMapping( "/VerticalIndex1")
    public String VerticalIndex1(Model model) {
        Long userId= ShiroUtils.getUserId();
        model.addAttribute("userId",userId);
        return "h5/vertical_index1";
    }

    /**
     * 横屏数据
     */
    @PostMapping( "/VerticalIndex1List")
    @ResponseBody
    public JSONObject VerticalIndex1List( @RequestParam("userId") String userId,@RequestParam("machineType") String machineType) {
        JSONObject jsonObj = new JSONObject();
        Map<String, Object> dataMap = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);//用户id
        map.put("machineType", machineType);
        List<MonitoringDO> monitoringList = monitoringService.findBig(map);
        if(monitoringList.size()>0){
            jsonObj.put("data", JSONArray.fromObject(monitoringList, JSONUtils.getJsonConfig()));
            jsonObj.put("code","0");
            jsonObj.put("msg","操作成功");
        }else {
            jsonObj.put("code","1");
            jsonObj.put("msg","无数据");
            jsonObj.put("data",new HashMap<>());
        }
        return jsonObj;

    }

}
