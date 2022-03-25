# course-registration-system
Spring Boot 수강신청 웹 사이트 토이 프로젝트

---
## Tools
1. Java: JDK 11.0.13
2. Spring: Spring Boot 2.6.4
3. Gradle
4. DB: H2 1.4.200 & MariaDB(AWS RDS) 10.5.13
5. AWS EC2

---
## ERD
<img src="https://user-images.githubusercontent.com/49421226/157011402-7a7a8b26-0304-4894-b6ff-17f23f828601.png" width="450" height="900">

--- 
### Login
```java
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
```

