package green.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity // 메서드별 보안 설정 활성화
public class SecurityConfig {

    // 비밀번호 암호화를 위한 BCryptPasswordEncoder 빈 등록
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Spring Security 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 보호 완전히 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**","/mypage","/", "/register", "/login", "/static/**", "/css/**", "/js/**", "/images/**").permitAll() // 특정 경로 인증 없이 허용
                        .anyRequest().authenticated() // 나머지 요청은 인증 필요
                );

        return http.build();
    }
}
