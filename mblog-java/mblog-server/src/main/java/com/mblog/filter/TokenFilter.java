package com.mblog.filter;

import com.mblog.utils.CurrentHolder;
import com.mblog.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


import java.io.IOException;

/**
 * token拦截器
 * 用于拦截所以没有登录的请求，去检验是否有token，如果没有则返回登录界面
 * 此类为全局过滤器，所有请求都会经过此过滤器
 * 当前该拦截器还未发挥作用，因为我想做一个不用登录也可以访问的网页，只是不能访问个人信息等页面，放在这里想做如果以后用的话再说
 */
@Slf4j
@WebFilter(urlPatterns = {"/userinfo/*", "/userinfo"})
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest requestZ = (HttpServletRequest) request;
        HttpServletResponse responseZ = (HttpServletResponse) response;
        //1、获取请求路径
        String path = requestZ.getRequestURI();// /login

        //2、判断是否是登录请求，如果路径包含/login，说明是登录请求，放行
        if (!path.contains("userinfo")) {
            log.info("登录请求，放行");
            chain.doFilter(request, response);
            return;
        }
        //3、获取请求头中的token
        String token = requestZ.getHeader("token");

        //4、判断token是否为存在，如果为不存在，说明未登录，拦截到登录页，返回状态码（401）
        if (token == null || token.isEmpty()) {
            log.info("令牌为空，响应401");
            responseZ.setStatus(401);
            return;
        }
        //5、如果token存在，校验令牌，如果校验失败， -> 返回错误信息（响应状态码401）
        try {
            Claims claims = JwtUtils.parseJWT(token);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
            log.info("当前登录的员工id是：{}，将其存入ThreadLocal",empId);
        } catch (Exception e) {
            log.info("令牌非法，响应401");
            responseZ.setStatus(401);
            return;
        }
        //6、校验通过，放行
        log.info("令牌合法，放行");
        chain.doFilter(request, response);

        //删除ThreadLocal中的数据
        CurrentHolder.remove();

    }
}
