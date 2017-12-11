package like.util;

import io.jsonwebtoken.*;
import like.entity.User;
import like.util.Constants;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.util.Date;

/**
 * Json Web Token Util
 *
 * @author like
 */
@Component
public class JWTUtil {

    private static String profiles;

    private static SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    public static String createToken(String id, String subject, long ttlMillis) {

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 解密jwt
     *
     * @param jwt
     * @return
     */
    public static Claims parseToken(String jwt) {
        SecretKey key = generalKey();
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    public static boolean validToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        try {
            Claims claims = parseToken(token);
            JSONObject json=JSONObject.fromObject(claims);
            if(json.containsKey("sub")){           	
            		return true;            	
            }else{
            	return false;
            }
                    
        } 
        catch (Exception e) {
            return false;
        }       
    }

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public static SecretKey generalKey() {
        String stringKey = profiles + Constants.JWT_SECRET;
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, signatureAlgorithm.getJcaName());
        return key;
    }

    /**
     * 生成subject信息
     *
     * @param user
     * @return
     */
    public static String generalSubject(User user) {
        JSONObject jo = new JSONObject();
        jo.put("userId", user.getId());
        jo.put("username", user.getUserName());
        return jo.toString();
    }
    
}
