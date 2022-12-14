package com.example.demo.config;

import com.example.demo.dto.security.AccountDto;
import com.example.demo.dto.security.AccountPrincipal;
import com.example.demo.repository.AccountRepository;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .mvcMatchers(
                                HttpMethod.GET,
                                "/"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/board/list")
                    .permitAll()
                    .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/board/list").permitAll()
                    .and()
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(AccountRepository accountRepository){
        return username -> accountRepository
                .findById(username)
                .map(AccountDto::from)
                .map(AccountPrincipal::from)
                .orElseThrow(() -> new UsernameNotFoundException("????????? ?????? ??? ???????????? -usernae : "+username));

    }




    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//BcryptPasswordEncoder??? PasswordEncoder ?????????????????? ??????????????? Bcrypt ?????? ????????? ????????? ??????????????? ?????????????????? ???????????? ???????????? ???????????? ??? ????????? ??????????????? DB??? ???????????? ?????? ??????????????? ?????? ????????? ??????????????? ???????????? ??????
    }
}
