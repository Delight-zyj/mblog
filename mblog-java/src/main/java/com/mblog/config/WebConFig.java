package com.mblog.config;


import com.mblog.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
<<<<<<< Updated upstream
        registry.addInterceptor(tokenInterceptor);
=======
        // 解密
        registry.addInterceptor(rsaDecryptInterceptor)
                .addPathPatterns("/**")
                .order(1);

        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/userinfo/**", "/userinfo")
                .order(2);  // 拦截/userinfo及其所有子路径
>>>>>>> Stashed changes
    }
}
