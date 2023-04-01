package com.yu.chatliteserver.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class Swagger3Config {
    @Value("${enable.swagger}")
    private boolean enableSwagger;

    /**
     * @return
     * @描述 swagger设置基本信息 要解析的接口和路径等
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
//               是否打开swagger文档 生产环境关闭
                .enable(enableSwagger)
                .apiInfo(apiInfo())
                .select()
//                定位到文档的方式
                .apis(RequestHandlerSelectors.basePackage("com.yu.chatliteserver.controller"))
//                uri请求路径 any是全路径
                .paths(PathSelectors.any())
                .build();
    }


    /**
     * 生成接口信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("我的博客文档")
                .description("springboot + vue 吴东宇的博客系统")
                .contact(new Contact("吴东宇", "http://192.168.1.3/", "9490475461@qq.com"))
                .version("1.0")
                .build();
    }


}
