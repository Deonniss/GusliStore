package golovin.store.gusli.security;

import golovin.store.gusli.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class UserLockedFilter extends OncePerRequestFilter {

    private final UserService userService;

    @Override
    @SneakyThrows
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId != null && userService.isLocked(userId)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "User has been locked");
        }
        filterChain.doFilter(request, response);
    }
}
