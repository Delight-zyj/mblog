package com.mblog.interceptor;

import com.mblog.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 令牌校验拦截器
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1、获取请求路径
        String path = request.getRequestURI();
        log.info("拦截到请求: {}", path);

        //2、判断是否是userinfo相关请求（这部分判断其实可以省略，因为配置已经限制了路径）
        if (path.startsWith("/userinfo")) {
            log.info("检测到/userinfo请求，开始验证token: {}", path);

            //3、获取请求头中的token
            String token = request.getHeader("token");

            //4、判断token是否存在
            if (token == null || token.isEmpty()) {
                log.info("令牌为空，响应401");
                response.setStatus(401);
                return false;
            }

            //5、校验令牌
            try {
                JwtUtils.parseJWT(token);
                log.info("令牌合法，放行");
                return true;
            } catch (Exception e) {
                log.info("令牌非法，响应401");
                response.setStatus(401);
                return false;
            }
        }

        // 理论上不会执行到这里，因为配置已经限定了只拦截/userinfo路径
        log.info("非userinfo请求，直接放行: {}", path);
        return true;
    }
}
