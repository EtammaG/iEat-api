package com.etammag.ieat.common.utils;

import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

/**
 * JWT工具类
 *
 * @author Eiji
 */
public class JwtUtil {

    /**
     * 默认有效期为一周
     */
    public static final Long JWT_TTL = 7 * 24 * 60 * 60 * 1000L;
    /**
    * 秘钥明文
    */
    private static final String JWT_KEY = "EIJI";

    /**
     * 使用UUID工具类生成一个不含-的UUID字符串
     *
     * @return String
     */
    private static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 根据密钥明文使用AES加密算法生成一个秘钥
     *
     * @return SecretKey
     */
    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 根据传入的参数生成一个JwtBuilder
     *
     * @param subject 需要加密的数据，放入jwt的subject中
     * @param ttlMillis jwt的有效时长
     * @param uuid jwt的唯一ID
     * @param params jwt的header参数
     * @return JwtBuilder
    */
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid, Map<String, Object> params) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setHeaderParams(params)
                .setId(uuid)              //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer("sg")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }

    /**
     *
     *
     * @param subject 需要加密的数据，放入jwt的subject中
     * @return String
     */
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, JWT_TTL, getUUID(), new HashMap<>(0));
        return builder.compact();
    }

    /**
     *
     *
     * @param subject 需要加密的数据，放入jwt的subject中
     * @param params jwt的header参数
     * @return String
     */
    public static String createJWT(String subject, Map<String, Object> params) {
        JwtBuilder builder = getJwtBuilder(subject, JWT_TTL, getUUID(), params);// 设置过期时间
        return builder.compact();
    }

    /**
     * 解析jwt
     *
     * @param jwt JWT
     * @return Jws<Claims>
     */
    public static Jws<Claims> parseJWT(String jwt) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt);
    }

}