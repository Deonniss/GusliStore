package golovin.store.gusli.security;

import golovin.store.gusli.security.exception.TokenNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtHandler jwtHandler;
    private final UserAuthenticationBearer userAuthenticationBearer;

    private final Set<String> ALLOWED_URL = Set.of("/api/v1/auth/login", "/api/v1/auth/register");

    @Override
    @SneakyThrows
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) {

        if (ALLOWED_URL.contains(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = getTokenFromRequest(request);
        try {
            SecurityContextHolder.getContext().setAuthentication(userAuthenticationBearer.create(jwtHandler.check(token)));
        } catch (Exception ex) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String bearer = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearer) && bearer.startsWith(BEARER_PREFIX)) {
            return bearer.substring(BEARER_PREFIX.length());
        }
        throw new TokenNotFoundException();
    }
}
