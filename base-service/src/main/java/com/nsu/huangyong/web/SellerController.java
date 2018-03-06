package com.nsu.huangyong.web;

import com.nsu.huangyong.common.constant.CommonRespCode;
import com.nsu.huangyong.common.constant.SellerStatus;
import com.nsu.huangyong.service.SellerService;
import com.nsu.huangyong.vo.CommonResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api")
@Api(value = "API-base-service",description = "商家业务支撑服务")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @ApiOperation(value ="商家登录" )
    @GetMapping("/seller/login")
    public CommonResp login(@RequestParam("phoneNo") String phoneNo,
                            @RequestParam("loginPassword") String loginPassword){
        log.info("recevie phoneNo:{}    loginPassword:{}",phoneNo,loginPassword);
        int status = sellerService.sellerLogin(phoneNo,loginPassword);
        if(status == 1){
            return new CommonResp(CommonRespCode.SUCCESS);
        }else if(status == 0) {
            return new CommonResp(CommonRespCode.FAIL, "该帐号"+SellerStatus.WAIT_AUDIT.getValue());
        }else if(status == 2) {
            return new CommonResp(CommonRespCode.FAIL, "该帐号已"+SellerStatus.LOCK.getValue());
        }else if(status == 3) {
            return new CommonResp(CommonRespCode.FAIL, "该帐号已"+SellerStatus.BLOCK_UP.getValue());
        }
        return new CommonResp(CommonRespCode.FAIL,"用户名或密码错误");
    }

    @ApiOperation(value ="商家注册" )
    @GetMapping("/seller/register")
    public CommonResp register(@RequestParam("trueName") String trueName,
                               @RequestParam("sex") String sex,
                               @RequestParam("certificateType") String certificateType,
                               @RequestParam("certificateNo") String certificateNo,
                               @RequestParam("phoneNo") String phoneNo,
                               @RequestParam("password") String password,
                               @RequestParam("captcha") String captcha,
                               HttpServletRequest request){
        log.info("recevie trueName:{}   sex:{}  certificateType:{}  certificateNo:{}    phoneNo:{}    password:{}    captcha:{}",trueName,sex,certificateType,certificateNo,phoneNo,password,captcha);
        if(sellerService.sellerRegister(trueName,sex,certificateType,certificateNo,phoneNo,password,captcha,request)){
            return new CommonResp(CommonRespCode.SUCCESS);
        }
        return new CommonResp(CommonRespCode.FAIL,"注册失败");
    }

    @ApiOperation(value ="密码找回" )
    @GetMapping("/seller/resetregister")
    public CommonResp resetRegister(@RequestParam("phoneNo") String phoneNo,
                                    @RequestParam("password") String password,
                                    @RequestParam("captcha") String captcha,
                                    HttpServletRequest request){
        log.info("recevie phoneNo:{}    password:{}    captcha:{}",phoneNo,password,captcha);
        if(sellerService.resetPassword(phoneNo,password,captcha,request)){
            return new CommonResp(CommonRespCode.SUCCESS);
        }
        return new CommonResp(CommonRespCode.FAIL,"验证码错误");
    }

    @ApiOperation(value ="检验是否存在此用户" )
    @GetMapping("/seller/isregister")
    public CommonResp isRegister(@RequestParam("phoneNo") String phoneNo, HttpServletRequest request){
        log.info("recevie  phoneNo:{}",phoneNo);
        if(sellerService.isRegister(phoneNo)){
            return new CommonResp(CommonRespCode.SUCCESS);
        }
        return new CommonResp(CommonRespCode.FAIL,"该号码未注册");
    }
}
