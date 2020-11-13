package com.mall.cloud.common.utils;

import com.google.common.collect.Maps;
import com.mall.cloud.common.constant.Passports;
import com.mall.cloud.common.constant.Tokens;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * <p>封装Qicloud项目TokenServerUtil类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-11-04 00:48
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
public class TokenServerUtil {
    private static class TokenServerHolder {
        private static final TokenServerUtil tokenServerInstance = new TokenServerUtil();
    }

    public static synchronized TokenServerUtil getInstance() {
        return TokenServerHolder.tokenServerInstance;
    }

    private TokenServerUtil() {
    }

    /**
     * 签发/生成Token[适应和适配多场景动态生成Token]
     *
     * @param id      token主键id[id参数不为空，按照指定id密钥生成Token;否则,设置默认id]
     * @param issuer  token发行人[issuer参数不为空，按照指定issuer密钥生成Token;否则,设置默认issuer]
     * @param subject token主题[subject参数不为空，按照指定subject密钥生成Token;否则,设置默认subject]
     * @param secret  token密钥[secret参数不为空，按照指定secret密钥生成Token;否则,设置默认生成secret]
     * @param ttl     token 时效[ttl>0L,设置指定ttl时效;否则，则设置默认时效:TokenConstant.TOKEN_TTL_TIME]
     * @return 返回生成的Token字符串
     * @throws Exception 异常信息
     */
    public String create(String id, String issuer, String subject, String secret, long ttl) throws Exception {
        synchronized (this){
            //[1]指定签名的时候使用的签名算法，也就是header中的算法
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            //[2].设置Token生成时间
            long createMillis = System.currentTimeMillis();
            Date createTime = new Date(createMillis);
            //[3]创建payload的私有声明(根据特定的业务需要添加，如果要拿这个做验证,一般是需要和JWT的接受方提前沟通)
            Map<String, Object> claims = Maps.newConcurrentMap();
            claims.put("userId", issuer);
            //[3].构建Token生成对象
            JwtBuilder builder = Jwts.builder()
                    //插入私有声明
                    .setClaims(claims)
                    //设置JWT的唯一标识
                    .setId(id)
                    //设置 创建时间
                    .setIssuedAt(createTime)
                    //设置 唯一所有人 可以存放主键之类的不重复字段作为唯一标识
                    .setSubject(subject)
                    //设置算法 和 密钥
                    .signWith(signatureAlgorithm, secret);
            //[4]判断过期时间 如果大于0 则设置过期时间
            if (ttl >= 0) {
                long expMillis = createMillis + ttl;
                Date expire = new Date(expMillis);
                //设置过期时间
                builder.setExpiration(expire);
            }
            return builder.compact();
        }
    }

    /**
     * Token解析
     *
     * @param token 被解析 JWT
     * @param secret   用户实体对象 可以通过数据库查询
     * @return 返回payload中存的数据
     */
    public String parse(String token, String secret) {
        Claims claims = Jwts.parser()
                //设置 密钥
                .setSigningKey(secret)
                //设置需要解析的 token
                .parseClaimsJws(token).getBody();
        return claims.get("userName").toString();
    }

    /**
     * 验证 token信息 是否正确
     *
     * @param token 被解析 JWT
     * @param secret   密钥
     * @return 是否正确
     */
    public Boolean verify(String token, String secret) {
        //获取DefaultJwtParser
        try {
            Jwts.parser()
                    //设置 密钥
                    .setSigningKey(secret)
                    //设置需要解析的 token
                    .parseClaimsJws(token).getBody();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        try {
            String id = "123";
            String token = TokenServerUtil.getInstance().create("123", "Token", "Token", "123456@Abc", Tokens.TOKEN_TTL_TIME);
            System.out.println("生成token:" + token);
            System.out.println(Passports.PASSPORT_BLOGGER_LOGIN_TOKEN + id + ":" + token);
            String parseToken = TokenServerUtil.getInstance().parse(token, "123456@Abc");
            System.out.println("解析token：" + parseToken);
            Boolean verifyToken = TokenServerUtil.getInstance().verify(token, "123456@Abc");
            System.out.println("校验token：" + verifyToken);
            System.out.println("" + Passports.PASSPORT_BLOGGER_LOGIN_TOKEN);
            System.out.println("" + Passports.PASSPORT_MALL_LOGIN_TOKEN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
