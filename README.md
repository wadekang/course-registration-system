# course-registration-system
Spring Boot 수강신청 웹 사이트 토이 프로젝트

### Main Page
<img src="https://user-images.githubusercontent.com/49421226/160076539-2fbc7bf1-324a-4341-9a2a-6587d3e69227.png">

---
## Tools
1. Java: JDK 11.0.13
2. Spring Boot 2.6.4
3. Gradle
4. DB: H2 1.4.200 & MariaDB(AWS RDS) 10.5.13
5. Cloud: AWS EC2
6. CI/CD: TravisCI, AWS S3, AWS CodeDeploy

---
## ERD
<img src="https://user-images.githubusercontent.com/49421226/157011402-7a7a8b26-0304-4894-b6ff-17f23f828601.png" width="450" height="900">

--- 
### Login & Logout

Spring Security 적용
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
---
### 수강 조회

#### 전공별 수강조회 기능
<img src="https://user-images.githubusercontent.com/49421226/160076998-cbc3b303-387f-4262-a8c6-5061de982af0.png">

#
#### 과목별 분반 조회
<img src="https://user-images.githubusercontent.com/49421226/160077193-1589805d-711a-4a3f-a828-115da61c07e8.png">

---
### 수강 신청

#### 첫 번째 셀렉트 박스에서 전공을 선택하면 두 번재 셀렉트 박스에 해당 전공 과목들이 나옴
<img src="https://user-images.githubusercontent.com/49421226/160077603-69bc1321-59b5-458c-a5fe-2967de40f9b4.png">
<img src="https://user-images.githubusercontent.com/49421226/160077852-c07afd49-6e3f-41bb-9cbc-5172dd51b124.png">
<img src="https://user-images.githubusercontent.com/49421226/160077882-49387f55-5c8a-4506-b125-71268fd1ec9f.png">

---
### 수강 내역

#### 내 수강 신청 내역
<img src="https://user-images.githubusercontent.com/49421226/160078294-14f55840-aa18-4c99-ada7-10931299c7f8.png">

---
### How to Build

#### 1. git clone
```shell
git clone https://github.com/wadekang/course-registration-system.git
```

#### 2. Make application-db.yml (db setting)
```yaml
# file_path: /src/main/resources/application-db.yml

spring:
  datasource:
    url: [DB URL]
    username: [DB ID]
    password: [DB Password]
    driver-class-name: [DB Driver]

  jpa:
    hibernate:
      ddl-auto: [ddl-auto] # create, update, none, create-drop
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.MySQL5InnoDBDialect
        storage-engine: innodb
```

#### 3. Test
<img src="https://user-images.githubusercontent.com/49421226/160832960-3fcf043a-d38a-4e86-902e-eea8e1e2be10.png">

#### 4. Run Application
<img src="https://user-images.githubusercontent.com/49421226/160833658-37159d1f-346b-4684-a72c-0828dfe32320.png">
