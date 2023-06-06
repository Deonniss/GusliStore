package golovin.store.gusli.security;

import golovin.store.gusli.entity.Role;
import golovin.store.gusli.entity.type.RoleType;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class UserAuthenticationBearer {

    @SneakyThrows
    public Authentication create(JwtHandler.VerificationResult verificationResult) {
        Claims claims = verificationResult.claims;
        String subject = claims.getSubject();
        Collection<Object> objects = (Collection<Object>) claims.get("roles");

        Set<Role> roles = new HashSet<>();

        for (Object o: objects) {

            Map<String, Object> set = (HashMap) o;



            Role role = Role.builder()
                    .id(Long.valueOf((Integer) set.get("id")))
                    .name(RoleType.valueOf((String) set.get("name")))
                    .authority((String) set.get("authority"))
                    .build();

            roles.add(role);
        }

        String username = claims.get("username", String.class);
        Long principalId = Long.parseLong(subject);
        CustomPrincipal principal = new CustomPrincipal(principalId, username);
        return new UsernamePasswordAuthenticationToken(principal, null, roles);
    }
}
