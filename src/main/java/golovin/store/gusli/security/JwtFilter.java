package golovin.store.gusli.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Set;

import static golovin.store.gusli.security.JwtHandler.getToken;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtHandler jwtHandler;
    private final UserAuthenticationBearer userAuthenticationBearer;

    private final Set<String> ALLOWED_URL = Set.of("/api/v1/auth/login/username", "/api/v1/auth/login/email","/api/v1/auth/register");

    @Override
    @SneakyThrows
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) {

        if (ALLOWED_URL.contains(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = getToken(request);

        try {
            JwtHandler.VerificationResult verificationResult = jwtHandler.check(token);
            request.setAttribute("userId", Long.parseLong(verificationResult.claims.getSubject()));
            SecurityContextHolder.getContext().setAuthentication(userAuthenticationBearer.create(verificationResult));
        } catch (Exception ex) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
        }
        filterChain.doFilter(request, response);
    }


}
