package io.github.talelin.latticy.common.configuration;

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

@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)//指定api类型为swagger2
                .apiInfo(apiInfo())//定义api文档汇总信息
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.github.talelin.latticy.controller"))//指定controller包
                .paths(PathSelectors.any())//所有controller
                .build();
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("接口api文档")//文档页标题
                .contact(new Contact("imooc","http://skboyed.xyz","364971779@qq.com"))
                .description("api文档")
                .version("1.0.0")
                .termsOfServiceUrl("http://skboyed.xyz")
                .build();
    }
}
