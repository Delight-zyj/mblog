package com.mblog.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mblog.annotation.RsaDecrypt;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RsaDecryptInterceptor implements HandlerInterceptor {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull  Object handler) throws Exception {
        // 检查是否需要解密
        if(handler instanceof HandlerMethod handlerMethod) {
            if(handlerMethod.hasMethodAnnotation(RsaDecrypt.class)){
                // 读取请求体
                String body = request.getReader().lines().collect(Collectors.joining());
                Map<String, Object> map = objectMapper.readValue(body, Map.class);

                if(map.containsKey("encrypted")){
                    // 解密数据
                    String decrypted = com.mblog.utils.RSAUtils.decrypt((String) map.get("encrypted"));
                    // 将解密后的数据放回请求属性中
                    request.setAttribute("decryptedBody", decrypted);
                }
            }
        }
        return true;
    }
}
