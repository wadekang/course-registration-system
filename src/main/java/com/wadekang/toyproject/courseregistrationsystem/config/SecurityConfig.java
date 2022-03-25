package com.wadekang.toyproject.courseregistrationsystem.config;

import com.wadekang.toyproject.courseregistrationsystem.handler.AuthFailureHandler;
import com.wadekang.toyproject.courseregistrationsystem.handler.AuthSuccessHandler;
import com.wadekang.toyproject.courseregistrationsystem.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final AuthSuccessHandler authSuccessHandler;
    private final AuthFailureHandler authFailureHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/", "/home", "/login/**", "/css/**", "/signup/**").permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .usernameParameter("loginId")
                    .passwordParameter("password")
                    .loginPage("/login")
                    .loginProcessingUrl("/login/action")
                    .successHandler(authSuccessHandler)
                    .failureHandler(authFailureHandler)
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/home")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID").permitAll()
                .and()
                    .sessionManagement()
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(false)
                    .expiredUrl("/login?error=true&exception=Session Expired!");
    }
}
