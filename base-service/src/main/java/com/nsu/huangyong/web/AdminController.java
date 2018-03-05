package com.nsu.huangyong.web;

import com.nsu.huangyong.common.constant.SysInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestController
public class AdminController {
    /**
     * 当前服务的根响应
     * @return 服务名称和版本
     */
    @RequestMapping("/")
    public Object info() {
        Map<String, String> infoMap = new LinkedHashMap<>();
        infoMap.put("name", SysInfo.NAME.getValue());
        infoMap.put("desc", "水果商城（huangyong.nsu@qq.com）");
        infoMap.put("version", SysInfo.VERSION.getValue());
        return infoMap;
    }
}
