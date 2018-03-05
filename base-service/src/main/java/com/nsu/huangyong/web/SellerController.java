package com.nsu.huangyong.web;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v2")
@Api(value = "API-base-service",description = "商家业务支撑服务")
public class SellerController {
}
