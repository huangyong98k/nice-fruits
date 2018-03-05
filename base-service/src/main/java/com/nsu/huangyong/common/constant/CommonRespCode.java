package com.nsu.huangyong.common.constant;

/**
 * 通用响应代码
 * @author Yong Huang
 */
public enum CommonRespCode {
    SUCCESS("1"), FAIL("2");

    private String value;

    CommonRespCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
