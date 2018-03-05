package com.nsu.huangyong.service.impl;

import com.nsu.huangyong.dao.SellerDao;
import com.nsu.huangyong.service.CommomService;
import com.nsu.huangyong.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerDao sellerDao;
    @Autowired
    private CommomService commomService;
    @Override
    public boolean sellerLogin(String phoneNo, String loginPassword) {
        if (sellerDao.findSellerByPhoneNoAndLoginPassword(phoneNo,loginPassword) != null){
            return true;
        }
        return false;
    }
    /*待重构*/
    @Override
    public boolean sellerRegister(String phoneNo, String password, String captcha, HttpServletRequest request) {
        return false;
    }

    @Override
    public boolean resetPassword(String phone, String password, String captcha, HttpServletRequest request) {
        if (isRegister(phone) && commomService.matchCaptcha(captcha,request)){
            try {
                sellerDao.resetPassword(password,phone);
                log.info("reset password sucess :{}",phone);
                return true;
            } catch (Exception e) {
                log.debug("rest password exception",e);
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean isRegister(String phoneNo) {
        if (sellerDao.findSellerByPhoneNo(phoneNo) != null){
            return true;
        }
        return false;
    }
}
