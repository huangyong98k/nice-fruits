package com.nsu.huangyong.common.constant;

/**
 * 系统信息常数
 * @author Yong Huang
 */
public enum SysInfo {
    NAME("base-service"), VERSION("201802");

    String value;

    SysInfo(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

