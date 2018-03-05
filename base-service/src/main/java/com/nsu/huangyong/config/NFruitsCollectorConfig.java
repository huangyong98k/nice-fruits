package com.nsu.huangyong.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 系统参数配置
 * @author Hong-Yang Xiao
 */
@Data
@Configuration
@ConfigurationProperties(prefix="nsu.huangyong.base")
public class NFruitsCollectorConfig {
    private String commonHeadPortrait;
}
