package com.nsu.huangyong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger的相关配置
 * @author Huang Yong
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                // 选择那些路径和api会生成document
                .select()
                // 对所选包api进行监控
                .apis(RequestHandlerSelectors.basePackage("com.nsu.huangyong.web"))
                // 对所有路径进行监控
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("NFruits水果商城基础服务API")
                .description("此API是NFruits水果商城基础服务API,主要为水果售卖平台提供登录注册，基础数据展示等一些基础服务")
                //API的服务条款的网址
                .termsOfServiceUrl("")
                .version("1.0.0")
                .contact("huangyong.nsu@qq.com")
                .build();
    }
}

