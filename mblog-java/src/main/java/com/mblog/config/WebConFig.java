package com.mblog.config;


import com.mblog.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置类
 */
@Configuration
public class WebConFig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor);
        // 解密
/*        registry.addInterceptor(rsaDecryptInterceptor)
                .addPathPatterns("/**")
                .order(1);*/

        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/userinfo/**", "/userinfo")
                .order(2);  // 拦截/userinfo及其所有子路径
                .addPathPatterns("/userinfo/**", "/userinfo");  // 拦截/userinfo及其所有子路径
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")  // 所有路径
//                .allowedOrigins("http://localhost:5173")  // 允许的前端地址
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // 允许的HTTP方法
//                .allowedHeaders("*")  // 允许所有请求头
//                .allowCredentials(true)  // 允许携带凭证（如cookies）
//                .maxAge(3600);  // 预检请求缓存时间（秒）
//    }
}
