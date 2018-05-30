package com.bootdo.lamp;

import com.bootdo.common.utils.HttpUtil;

import java.util.Map;

public class LampImpl {

    /**
     * 域名
     */
//    public static final String DOMAIN_URL = "http://59.110.46.71:8080";
    public static final String DOMAIN_URL = "http://www.enzded.com";

    /**
     * 注册
     */
    public static final String REGISTER_URL = "/Enzd/user/userRegister.action";

    /**
     * 登录
     */
    public static final String LOGIN_URL = "/Enzd/user/userLogin.action";

    /**
     * 绑定控制器
     */
    public static final String BINDING_CONTROLLER_SAVE_URL = "/Enzd/admin/userControllers/save.action";

    /**
     * 解绑控制器
     */
    public static final String BINDING_CONTROLLER_DEL_URL = "/Enzd/admin/userControllers/delController.action";

    /**
     * 设备列表
     */
    public static final String DEVICE_LIST_URL = "/Enzd/controllers/getallactuator.action";

    /**
     * 场景列表
     */
    public static final String SCENE_LIST_URL = "/Enzd/wechat/getAllScene.action";

    /**
     * 开关灯接口
     */
    public static final String LAMP_TYPE_URL = "/Enzd/actuators/switchs.action";

    /**
     * 场景控制指令
     */
    public static final String SCENE_SWITCHS_URL = "/Enzd/scenes/scenesSwitchs.action";

    /**
     * 获取区域列表
     */
    public static final String REGION_LIST_URL="/Enzd/wechat/getAllAreas.action";

    /**
     * 用户注册接口
     * @param params userPhone-用户手机号 password-用户密码 controllersId-控制器ID
     */
    public static String registerToLamp(Map params) {
        String url = DOMAIN_URL + REGISTER_URL;
        String result = HttpUtil.doPost(url, params);
        System.out.println("返回结果" + result);
        return  result;
    }

    /**
     * 用户登录接口
     * @param params userName-用户名 userPwd-用户密码
     */
    public static String loginToLamp (Map params) {
        String url = DOMAIN_URL + LOGIN_URL;
        String result = HttpUtil.doPost(url, params);
        System.out.println("返回结果" + result);
        return  result;
    }

    /**
     * 绑定控制器接口
     * @param params userName-用户名 password-密码 address-控制器Id:八位Id标识
     */
    public static String bindingControllerSave (Map params) {
        String url = DOMAIN_URL + BINDING_CONTROLLER_SAVE_URL;
        String result = HttpUtil.doPost(url, params);
        System.out.println("返回结果" + result);
        return  result;
    }

    /**
     * 解绑控制器接口
     * @param params userName-用户名 password-密码 address-控制器Id:八位Id标识
     */
    public static String bindingControllerDel (Map params) {
        String url = DOMAIN_URL + BINDING_CONTROLLER_DEL_URL;
        String result = HttpUtil.doPost(url, params);
        System.out.println("返回结果" + result);
        return  result;
    }

    /**
     * 设备列表接口
     * @param params tokenId-用户唯一标识 toAddr-控制器地址
     */
    public static String deviceList (Map params) {
        String url = DOMAIN_URL + DEVICE_LIST_URL;
        String result = HttpUtil.doPost(url, params);
        System.out.println("返回结果" + result);
        return  result;
    }

    /**
     * 场景列表接口
     * @param params tokenId-用户唯一标识 toAddr-控制器地址
     */
    public static String sceneList (Map params) {
        String url = DOMAIN_URL + SCENE_LIST_URL;
        String result = HttpUtil.doPost(url, params);
        System.out.println("返回结果" + result);
        return  result;
    }

    /**
     * 发送开关灯指令接口
     * @param params tokenId-用户唯一标识 toAddr-控制器地址 uId-与控制器通信唯一ID
     *               pwd-控制器連接密碼 realID-配置文件里的回路ID status-开关命令:通用（00==关，ff==开）
     */
    public static String lampOpenOrCloseTurn (Map params) {
        String url = DOMAIN_URL + LAMP_TYPE_URL;
        String result = HttpUtil.doPost(url, params);
        System.out.println("返回结果" + result);
        return  result;
    }

    /**
     * 场景控制接口
     * @param params tokenId-用户唯一标识 toAddr-控制器地址 uId-与控制器通信唯一ID pwd-控制器連接密碼 sceneId-配置文件里的场景ID
     * @return
     */
    public static String sceneSwitchs (Map params) {
        String url = DOMAIN_URL + SCENE_SWITCHS_URL;
        String result = HttpUtil.doPost(url, params);
        System.out.println("返回结果" + result);
        return  result;
    }


    /**
     * 获取区域列表
     * @param params tokenId-用户唯一标识 toAddr-控制器地址
     * @return
     */
    public static String getRegionListUrl (Map params) {
        String url = DOMAIN_URL + REGION_LIST_URL;
        String result = HttpUtil.doPost(url, params);
        System.out.println("返回结果" + result);
        return  result;
    }


}
