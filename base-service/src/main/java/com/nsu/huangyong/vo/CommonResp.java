package com.nsu.huangyong.vo;



import com.nsu.huangyong.common.constant.CommonRespCode;
import lombok.Data;

/**
 * 通用响应消息
 * @author Yong Huang
 */
@Data
public class CommonResp {
    /**
     * 业务代码
     */
    private String bizCode;
    /**
     * 业务描述
     */
    private String bizDesc;

    public CommonResp(CommonRespCode bizCode) {
        this.bizCode = bizCode.getValue();
        this.bizDesc = "success";
    }

    public CommonResp(CommonRespCode bizCode, String bizDesc) {
        this.bizCode = bizCode.getValue();
        this.bizDesc = bizDesc;
    }
}
