/**
 *  1.单向加密算法
 单向加密算法主要用来验证数据传输的过程中，是否被篡改过。
BASE64 严格地说，属于编码格式，而非加密算法

MD5(Message Digest algorithm 5，信息摘要算法)

SHA(Secure Hash Algorithm，安全散列算法)

HMAC(Hash Message Authentication Code，散列消息鉴别码

 2.对称和非对称加密算法
   对称和非对称加密算法主要采用公钥和私钥的形式，来对数据加密。
DES(Data Encryption Standard，数据加密算法)

PBE(Password-based encryption，基于密码验证)

RSA(算法的名字以发明者的名字命名：Ron Rivest, AdiShamir 和Leonard Adleman)

DH(Diffie-Hellman算法，密钥一致协议)

DSA(Digital Signature Algorithm，数字签名)

ECC(Elliptic Curves Cryptography，椭圆曲线密码编码学)



  更多理论的简介，请自行查阅，下面提供代码，来看一下。
 */

package com.shop.yi.common.util.password;
import java.security.MessageDigest;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**  
 * @ClassName: coder   
 * @Description: 加密组件
 * @author: LUCKY  
 * @date:2016年1月4日 下午1:24:12     
 */ 
public abstract class BaseEncryptUtils {
    public static final String KEY_SHA = "SHA";
    public static final String KEY_MD5 = "MD5";
 
    /**
     * MAC算法可选以下多种算法
     * 
     * <pre>
     * HmacMD5 
     * HmacSHA1 
     * HmacSHA256 
     * HmacSHA384 
     * HmacSHA512
     * </pre>
     */
    public static final String KEY_MAC = "HmacMD5";
 
    /**
     * BASE64解密
     * 
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }
 
    /**
     * BASE64加密
     * 
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }
 
    /**
     * MD5加密
     * 
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptMD5(byte[] data) throws Exception {
 
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);
 
        return md5.digest();
 
    }
 
    /**
     * SHA加密
     * 
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptSHA(byte[] data) throws Exception {
 
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data);
 
        return sha.digest();
 
    }
 
    /**
     * 初始化HMAC密钥
     * 
     * @return
     * @throws Exception
     */
    public static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);
 
        SecretKey secretKey = keyGenerator.generateKey();
        
        return encryptBASE64(secretKey.getEncoded());
    }
 
    /**
     * HMAC加密
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {
 
        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
 
        return mac.doFinal(data);
 
    }
}