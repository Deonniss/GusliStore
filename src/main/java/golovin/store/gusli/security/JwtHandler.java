package golovin.store.gusli.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtHandler {

    @Value("${jwt.secret}")
    private String secret;

    public VerificationResult check(String accessToken) {
        return verify(accessToken);
    }

    private VerificationResult verify(String token) {
        Claims claims = getClaimsFromToken(token);
        final Date expirationDate = claims.getExpiration();
        if (expirationDate.before(new Date())) {
            throw new RuntimeException("Token expired");
        }
        return new VerificationResult(claims, token);
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(secret.getBytes()))
                .parseClaimsJws(token)
                .getBody();
    }

    public static class VerificationResult {
        public Claims claims;
        public String token;

        public VerificationResult(Claims claims, String token) {
            this.claims = claims;
            this.token = token;
        }
    }
}
