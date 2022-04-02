package ssj.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

public class JwtUtils {

    /**
     * 密钥
     */
    private static final String SECRET = "ssj";

    public static String createToken(String username, String password) throws UnsupportedEncodingException {
        //设置token时间 1小时
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.HOUR, 1);
        Date date = nowTime.getTime();


        //密文生成
        String token = JWT.create()
                .withClaim("username", username)
                .withClaim("password", password)
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * 验证token的有效性
     */
    public static boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            verifier.verify(token);
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    /**
     * 获取token列名
     * **/
    /**
     * 通过载荷名字获取载荷的值
     */

    public static String getClaim(String token, String name) {
        String claim = null;
        try {
            claim = JWT.decode(token).getClaim(name).asString();
        } catch (Exception e) {
            return "getClaimFalse";
        }
        return claim;
    }
}
