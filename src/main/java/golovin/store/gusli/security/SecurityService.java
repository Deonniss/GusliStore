package golovin.store.gusli.security;

import golovin.store.gusli.dto.TokenDetails;
import golovin.store.gusli.entity.User;
import golovin.store.gusli.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Integer expirationInSeconds;

    @Value("${jwt.issuer}")
    private String issuer;

    public TokenDetails authenticate(String username, String password) {
        User user = userService.getUserByUsername(username);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Not authenticated");
        }
        return generateToken(user).toBuilder().userId(user.getId()).build();
    }

    private TokenDetails generateToken(User user) {
        Map<String, Object> claims = new HashMap<>() {{
            put("roles", user.getRoles());
            put("username", user.getUsername());
        }};
        return generateToken(claims, user.getId().toString());
    }

    private TokenDetails generateToken(Map<String, Object> claims, String subject) {
        long expirationTimeInMillis = expirationInSeconds * 1000L;
        Date expirationDate = new Date(new Date().getTime() + expirationTimeInMillis);
        return generateToken(expirationDate, claims, subject);
    }

    private TokenDetails generateToken(Date expirationDate, Map<String, Object> claims, String subject) {
        Date createdDate = new Date();
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(issuer)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setId(UUID.randomUUID().toString())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secret.getBytes()))
                .compact();
        return TokenDetails.builder().token(token).issuedAt(createdDate).expiresAt(expirationDate).build();
    }
}
