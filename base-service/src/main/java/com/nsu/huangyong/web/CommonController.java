package com.nsu.huangyong.web;

import com.nsu.huangyong.common.constant.CommonRespCode;
import com.nsu.huangyong.service.CommonService;
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
@Api(value = "API-base-service",description = "通用业务支撑服务")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @ApiOperation(value ="发送验证码" )
    @GetMapping("/common/sendcaptcha")
    public CommonResp sendCaptcha(@RequestParam("phoneNo") String phoneNo, HttpServletRequest request){
        log.info("recevie  phoneNo:{}",phoneNo);
        if(commonService.sendCaptcha(phoneNo,request)){
            return new CommonResp(CommonRespCode.SUCCESS);
        }
        return new CommonResp(CommonRespCode.FAIL,"发送失败");
    }
}

