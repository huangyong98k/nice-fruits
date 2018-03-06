package com.nsu.huangyong.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 通用基础服务
 * @author Yong Huang
 */
public interface CommonService {
    /**
     * 校验验证码(匹配：true  不匹配：false)
     */
    boolean matchCaptcha(String captcha, HttpServletRequest request);

    boolean sendCaptcha(String phoneNo,HttpServletRequest request);
}
