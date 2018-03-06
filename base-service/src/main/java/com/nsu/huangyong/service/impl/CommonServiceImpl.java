package com.nsu.huangyong.service.impl;

import com.nsu.huangyong.common.utils.NFruitsUtils;
import com.nsu.huangyong.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Service
public class CommonServiceImpl implements CommonService {
    @Override
    public boolean matchCaptcha(String captcha, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(captcha.equals(session.getAttribute("captcha"))){
            log.info("校验成功  captcha:{}",captcha);
            return true;
        }
        log.info("校验失败 captcha:{}",captcha);
        return false;
    }

    @Override
    public boolean sendCaptcha(String phoneNo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String captcha = NFruitsUtils.generateNaturalNumber();
        log.info("send captcha success :{}",captcha);
        session.setAttribute("captcha",captcha);
        return true;
    }
}
