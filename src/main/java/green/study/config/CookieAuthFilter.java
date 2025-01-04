package green.study.config;

import green.study.application.service.UserService;
import green.study.domain.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class CookieAuthFilter extends OncePerRequestFilter {

    private final UserService userService;

    public CookieAuthFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userId".equals(cookie.getName())) { // 쿠키 이름이 "userId"인 경우
                    String userId = cookie.getValue();

                    // 사용자 조회 및 인증 처리
                    try {
                        User user = userService.authenticateByUserId(userId); // 인증 메서드 호출
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } catch (IllegalArgumentException e) {
                        // 인증 실패 시 로그 출력 (필요시 예외 처리)
                        System.err.println("Authentication failed: " + e.getMessage());
                    }
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
