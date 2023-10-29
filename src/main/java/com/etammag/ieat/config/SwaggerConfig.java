package com.etammag.ieat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.etammag.ieat.controller"))
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "The API of iEat", // 标题
                "I'm the description", // 描述
                "v1.0", // 版本
                "https://ieat.com", // 公司链接
                new Contact(
                        "EtammaG",
                        "https://ieat.com/etammag",
                        "EtammaG@outlook.com"), // 联系人信息
                "Apache 2.0 许可", // 许可
                "www.xxx.com", // 许可连接
                new ArrayList<>()// 扩展
        );
    }

}