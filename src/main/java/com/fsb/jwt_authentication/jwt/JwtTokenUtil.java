package com.fsb.jwt_authentication.jwt;

import com.fsb.jwt_authentication.user.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; //24h
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    @Value("${app.jwt.secret}")
    private String secretKey;

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(user.getId() + "," + user.getEmail())
                .claim("roles",user.getRoles().toString())
                .setIssuer("Codejava")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512,secretKey)
                .compact();
    }

    public boolean validateAccessToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            
            return true;
        } catch (ExpiredJwtException e) {
            LOGGER.error("JWT expired ",e);
        } catch (UnsupportedJwtException e) {
            LOGGER.error("JWT is not supported",e);
        } catch (MalformedJwtException e) {
            LOGGER.error("JWT id invalid",e);
        } catch (SignatureException e) {
            LOGGER.error("signature validation failed",e);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Token is null, empty or has only whitespace",e);
        }
        return false;
    }

    public String getSubject(String token){
        return parseClaims(token).getSubject();
    }

    public Claims parseClaims(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

}
