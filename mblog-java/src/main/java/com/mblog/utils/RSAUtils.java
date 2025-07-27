package com.mblog.utils;

import org.springframework.core.io.ClassPathResource;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PKCS12Attribute;
import java.security.PrivateKey;
import java.util.Base64;
import java.util.stream.Collectors;

public class RSAUtils {
    private static PrivateKey privateKey;
    static {
        try {
            // 读取私钥文件
            InputStream is = new ClassPathResource("private_key.pem").getInputStream();
            String privateKeyPem = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));

            // 移除PEM格式的标记和换行符
            privateKeyPem = privateKeyPem.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s", "");

            // 解码Base64
            byte[] decode = Base64.getDecoder().decode(privateKeyPem);

            // 创建PKCS8EncodedKeySpec
            PKCS12Attribute keySpec = new PKCS12Attribute(decode);

            // 获取RSA KeyFactory
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");



        } catch (Exception e) {
            throw new RuntimeException("Failed to load private key", e);
        }
    }

    /**
     * RSA解密
     * @param encryptedText 加密的文本
     * @return 解密后的字符串
     */
    public static String decrypt(String encryptedText) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}
