package com.bootdo.lamp;

import java.util.HashMap;
import java.util.Map;

public class LampTest {
    public static void main (String[] args) {

        // 测试注册
//        Map params6 = new HashMap();
//        params6.put("userPhone", "18610810207");
//        params6.put("password", "123456");
//        params6.put("controllersId", "31805158");
//        System.out.println(LampImpl.registerToLamp(params6));
        // 测试登录
        Map params = new HashMap();
        params.put("userName", "18612586627");
        params.put("userPwd", "111111");
        System.out.println(LampImpl.loginToLamp(params));
        // 测试绑定控制器
//        Map params1 = new HashMap();
//        params1.put("userName", "18612586627");
//        params1.put("userPwd", "111111");
//        params1.put("address", "31805158");
//        LampImpl.bindingControllerSave(params1);
        // 测试解绑控制器
//        Map params2 = new HashMap();
//        params2.put("userName", "18612586627");
//        params2.put("userPwd", "111111");
//        params2.put("address", "31805158");
//        LampImpl.bindingControllerDel(params2);
//        // 测试设备列表
//        Map params3 = new HashMap();
//        params3.put("tokenId", "135c966c21104c22b0f34d4a07229118");
//        params3.put("toAddr", "31785168");
//        LampImpl.deviceList(params3);
        // 测试场景列表
//        Map params4 = new HashMap();
//        params4.put("tokenId", "135c966c21104c22b0f34d4a07229118");
//        params4.put("toAddr", "31785168");
//        LampImpl.sceneList(params4);
        // 测试开关灯接口
//        Map params5 = new HashMap();
//        params5.put("tokenId", "135c966c21104c22b0f34d4a07229118");
//        params5.put("toAddr", "31785168");
//        params5.put("uId", "31805158");
//        params5.put("pwd", "enzd123456");
//        params5.put("realID", "1");
//        params5.put("status", "00");
//        LampImpl.lampOpenOrCloseTurn(params5);






    }
}
