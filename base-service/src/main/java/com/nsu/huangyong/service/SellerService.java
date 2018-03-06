package com.nsu.huangyong.service;

import javax.servlet.http.HttpServletRequest;

public interface SellerService {
    /**
     * 商家登录
     */
    int sellerLogin(String phoneNo,String loginPassword);
    /**
     * 商家注册
     */
    boolean sellerRegister(String trueName,String sex,String certificateType,String certificateNo,String phoneNo, String password, String captcha, HttpServletRequest request);

    /**
     * 密码重置
     */
    boolean resetPassword(String phone ,String password,String captcha, HttpServletRequest request);
    /**
     * 检验账号是否已注册
     */
    boolean isRegister(String phoneNo);
}
