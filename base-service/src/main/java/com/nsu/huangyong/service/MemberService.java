package com.nsu.huangyong.service;

import javax.servlet.http.HttpServletRequest;

public interface MemberService {
    /**
     * 会员登录
     */
    boolean memberLogin(String phoneNo,String loginPassword, HttpServletRequest request);
    /**
     * 会员注册
     */
    boolean memberRegister(String phoneNo, String password, String captcha, HttpServletRequest request);

    /**
     * 密码重置
     */
    boolean resetPassword(String phone ,String password,String captcha, HttpServletRequest request);
    /**
     * 检验账号是否已注册
     */
    boolean isRegister(String phoneNo);
}
