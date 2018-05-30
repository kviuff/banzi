package com.bootdo.app;

import com.alibaba.fastjson.JSON;
import com.bootdo.cluemanger.domain.ManagerDO;
import com.bootdo.cluemanger.service.ManagerService;
import com.bootdo.common.annotation.Log;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.*;
import com.bootdo.system.domain.MenuDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import net.sf.json.JSONArray;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/applogin")
public class AppLoginController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    private ManagerService managerService;

    @GetMapping({ "/login"})
    String welcome(Model model) {

        return "app/login";
    }

    @Log("登录")
    @PostMapping("/dologin")
    @ResponseBody
    R ajaxLogin(String username1, String password1) {
        password1 = MD5Utils.encrypt(username1, password1);
        UsernamePasswordToken token = new UsernamePasswordToken(username1, password1);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            Map<String,Object> data = new HashMap<>();
            data.put("data",ShiroUtils.getUserId());
            return R.ok(data);
        }  catch (UnknownAccountException e) {
            return R.error("用户或密码错误");
        } catch (IncorrectCredentialsException e) {
            return R.error("用户或密码错误");
        } catch (AuthenticationException e) {
            return R.error("用户或密码错误");
        }
    }

    @GetMapping("/logout")
    String logout() {
        ShiroUtils.logout();
        return "redirect:/applogin/login";
    }

    @Log("请求访问主页")
    @GetMapping({ "/index" })
    String index(Model model, @RequestParam Map<String, Object> params) {

        //查询列表数据
        if (params.size() == 0) {
            params.put("offset", 0);
            params.put("limit", 10);
        }
        Query query = new Query(params);
        List<ManagerDO> managerList = managerService.list(query);
        int total = managerService.count(query);
        PageUtils pageUtils = new PageUtils(managerList, total);

        model.addAttribute("username", getUser().getUsername());
        model.addAttribute("pageUtils", pageUtils);
        return "app/index";
    }

    @GetMapping("/edit/{id}")
    String detail(@PathVariable("id") Long id,Model model){
        ManagerDO manager = managerService.get(id);
        model.addAttribute("manager", manager);
        return "app/cluedetail";
    }

    @GetMapping("addVisit")
    String addVisit(@RequestParam("id") Long id,Model model){
        model.addAttribute("id", id);
        return "app/addvisit";
    }

    /**
     * 保存访问详情
     */
    @ResponseBody
    @PostMapping("/saveVisit")
    public R saveVisit(ManagerDO manager){
        // 标题
        String visitTitle = manager.getVisitTitle();
        // 时间
        String visitTime = manager.getVisitTime();
        // 详情
        String visitDetail = manager.getVisitDetail();

        ManagerDO managerDO = managerService.get(manager.getId());
        String timeDetail = managerDO.getTimeDetail();

        JSONArray array = JSONArray.fromObject(timeDetail);
        List<Clue> list = JSONArray.toList(array, Clue.class);

        Clue clue = new Clue();
        clue.setVisitDetail(visitDetail);
        clue.setVisitTime(visitTime);
        clue.setVisitTitle(visitTitle);
        list.add(clue);

        String str= JSON.toJSON(list).toString();
        ManagerDO managerDO1 = new ManagerDO();
        managerDO1.setId(manager.getId());
        managerDO1.setTimeDetail(str);

        int result = managerService.update(managerDO1);
        if(result > 0){
            return R.ok();
        }
        return R.error();
    }

    @GetMapping("addClue")
    String addClue(){
        return "app/addclue";
    }


    @Log("请求我的")
    @GetMapping({ "/myIndex" })
    String myindex(Model model) {
        model.addAttribute("name", getUser().getName());
        model.addAttribute("username", getUser().getUsername());
        return "app/my";
    }

    @Log("请求销售列表")
    @GetMapping({ "/saleList" })
    String saleList(Model model, @RequestParam Map<String, Object> params) {

        //查询列表数据
        if (params.size() == 0) {
            params.put("offset", 0);
            params.put("limit", 1000);
        }
        Query query = new Query(params);
        List<UserDO> sysUserList = userService.listForSale(query);
        int total = userService.listForSaleCount(query);
        PageUtils pageUtils = new PageUtils(sysUserList, total);
        model.addAttribute("pageUtils", pageUtils);
        return "app/selllist";
    }



}
