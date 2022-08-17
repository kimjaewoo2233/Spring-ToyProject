package com.example.demo.config;


import com.example.demo.dto.security.AccountPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

        @Bean
        public AuditorAware<String> auditorAware(){
            return () -> Optional.ofNullable(SecurityContextHolder.getContext())
                    .map(SecurityContext::getAuthentication)    //인증주체 또는 인증 토큰을 가져와서
                    .filter(Authentication::isAuthenticated)//인증된건지확인
                    .map(Authentication::getPrincipal)// 접근주체꺼내옴
                    .map(x -> (AccountPrincipal)x)//꺼내온걸 커스텀 접근주체로 바꿈
                    .map(AccountPrincipal::getUsername);    //접근주체에 아이디를 가져와서 리턴함
        }
}
