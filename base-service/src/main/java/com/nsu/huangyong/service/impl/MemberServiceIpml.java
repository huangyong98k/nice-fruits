package com.nsu.huangyong.service.impl;

import com.nsu.huangyong.common.utils.NFruitsUtils;
import com.nsu.huangyong.config.NFruitsCollectorConfig;
import com.nsu.huangyong.dao.MemberDao;
import com.nsu.huangyong.model.Member;
import com.nsu.huangyong.service.CommonService;
import com.nsu.huangyong.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@Slf4j
public class MemberServiceIpml implements MemberService{
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private NFruitsCollectorConfig config;
    @Override
    public boolean memberLogin(String phoneNo, String loginPassword, HttpServletRequest request) {
        Member member = memberDao.findMemberByPhoneNoAndLoginPassword(phoneNo,loginPassword);
        if (member != null){
            HttpSession session = request.getSession();
            session.setAttribute("memberNo",member.getMemberNo());
            return true;
        }
        return false;
    }

    @Override
    public boolean memberRegister(String phoneNo, String password, String captcha, HttpServletRequest request) {
        if(commonService.matchCaptcha(captcha,request)){
            Member member = new Member("MEM"+NFruitsUtils.identifier(),phoneNo,"男",password,phoneNo,NFruitsUtils.getCurrentTime(),100,config.getCommonHeadPortrait());
            try {
                sava(member);
                log.info("register success :{}",member);
                return true;
            } catch (Exception e) {
                log.debug("register exception :",e);
                return false;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public boolean resetPassword(String phone, String password, String captcha, HttpServletRequest request) {
        if (isRegister(phone) && commonService.matchCaptcha(captcha,request)){
            try {
                memberDao.resetPassword(password,phone);
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
        if (memberDao.findMemberByPhoneNo(phoneNo) != null){
            return true;
        }
        return false;
    }

    @Transactional
    public void sava(Member member)throws Exception{
        memberDao.save(member);
    }

}
