package com.bootdo.lamp.vo;

import java.io.Serializable;

public class LampVo implements Serializable  {
    // 控制器地址
    private String controller;
    // 回路地址
    private String address;
    private String name;
    // 配置文件里的回路ID
    private String id;
    private String type;



    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
