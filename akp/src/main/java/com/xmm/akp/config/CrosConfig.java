package com.xmm.akp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 前端请求后端的数据，因为前端端口后是8080，后端端口后是8181
 * 这个类的功能是是解决跨域问题（跨域问题可以在前端解决亦可以在后端解决），这就是在后端解决
 */



@Configuration
public class CrosConfig implements WebMvcConfigurer { //implements WebMvcConfigurer重写下面的方法

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
