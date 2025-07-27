import { JSEncrypt } from 'jsencrypt';

// 这里放置公钥内容
const PUBLICK_KEY = `-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyd/B186pG/aViCn9oAME
8JkKJCXmDDTP/peKm2+RIQTFY+EGrWKIiWiZshs2eQPEljgKh+9PqpRRrseJ4AQz
8QROypl+BFroXTRzLx+nOzYrx8V5pndlTJjZMMcQGVOBih4vwsTiGxjmb+6LnDMx
RvNkj80/uyKyNS8OV80UkpNG6r6dIZPYlLe1B72ZJVZC8atHrKZlys7KE7hOGhrP
bxp42suO1aMxj/TNXqheTvikgXfq4TAyokf8omjn61cz/eFSVl5CO265Gw76XxBV
sYxjXWY9TcCSwvsnMXPHzxPIuDlxuc9mjln7+b/BYLNiCwfuUp9SHRILwk65sVeq
iwIDAQAB
-----END PUBLIC KEY-----`;

// 创建加密实例
const encryptor = new JSEncrypt()
encryptor.setPublicKey(PUBLICK_KEY);

/**
 * RSA加密
 * @param {Object|String} data 要加密的数据
 * @returns {String} 加密后的字符串
 */
export function rsaEncrypt(data){
 const dataStr = typeof data === 'string' ? data : JSON.stringify(data);
 return encryptor.encrypt(dataStr);
}

/**
 * 加密请求数据
 * @param {Object} data 请求数据
 * @returns {Object} 加密后的请求数据 {encrypted: 加密字符串}
 */
export function encryptRequestData(data){
  return {
    encrypted: rsaEncrypt(data),
  }
}
