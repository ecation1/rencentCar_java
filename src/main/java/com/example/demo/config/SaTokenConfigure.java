package com.example.demo.config;

import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@EnableWebMvc
public class SaTokenConfigure implements WebMvcConfigurer {
    //跨域配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册注解拦截器，并排除不需要注解鉴权的接口地址 (与登录拦截器无关)
        registry.addInterceptor(new SaAnnotationInterceptor())
                                            .addPathPatterns("/**")
                                            .excludePathPatterns("/test")
                                            .excludePathPatterns("/user/login")
                                            .excludePathPatterns("/user/getAllRoles")
                                            .excludePathPatterns("/user/register")
                                            .excludePathPatterns("/car/getCarMenu")
                                            .excludePathPatterns("/user/isLogin");;


    }
}
