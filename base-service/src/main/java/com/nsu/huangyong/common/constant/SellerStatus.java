package com.nsu.huangyong.common.constant;

import lombok.Getter;

/**
 * 卖家账号状态
 */
public enum  SellerStatus {
    WAIT_AUDIT("待审核"),
    REAL_NAME("已实名"),
    LOCK("锁定"),
    BLOCK_UP("停用");
    @Getter
    private String value;

    SellerStatus(String value) {
        this.value = value;
    }
}
