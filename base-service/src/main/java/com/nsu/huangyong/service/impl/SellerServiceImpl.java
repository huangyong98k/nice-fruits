package com.nsu.huangyong.service.impl;

import com.nsu.huangyong.common.utils.NFruitsUtils;
import com.nsu.huangyong.config.NFruitsCollectorConfig;
import com.nsu.huangyong.dao.SellerDao;
import com.nsu.huangyong.model.Seller;
import com.nsu.huangyong.service.CommonService;
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
    private CommonService commonService;
    @Autowired
    private NFruitsCollectorConfig config;
    @Override
    public int sellerLogin(String phoneNo, String loginPassword) {
        Seller seller = sellerDao.findSellerByPhoneNoAndLoginPassword(phoneNo,loginPassword);
        if (seller != null){
            return seller.getStatus();
        }
        return -1;
    }

    @Override
    public boolean sellerRegister(String trueName,String sex,String certificateType,String certificateNo,String phoneNo, String password, String captcha, HttpServletRequest request) {
        if(commonService.matchCaptcha(captcha,request)){
            Seller seller = new Seller("SEL"+ NFruitsUtils.identifier(),phoneNo,"男",trueName,certificateType,certificateNo,password,phoneNo,NFruitsUtils.getCurrentTime(),0,0.00,config.getCommonHeadPortrait());
            try {
                sellerDao.save(seller);
                log.info("register success :{}",seller);
                return true;
            } catch (Exception e) {
                log.debug("register exception :",e);
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean resetPassword(String phone, String password, String captcha, HttpServletRequest request) {
        if (isRegister(phone) && commonService.matchCaptcha(captcha,request)){
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
