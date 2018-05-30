package com.bootdo.lamp.vo;

import java.io.Serializable;
import java.util.List;

public class DeviceVo implements Serializable {
    private String code;
    private String operations;
    private String message;
    private List<LampVo> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOperations() {
        return operations;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LampVo> getData() {
        return data;
    }

    public void setData(List<LampVo> data) {
        this.data = data;
    }
}
