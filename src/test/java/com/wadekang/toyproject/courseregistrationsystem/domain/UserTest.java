package com.wadekang.toyproject.courseregistrationsystem.domain;

import com.wadekang.toyproject.courseregistrationsystem.controller.dto.UserSignUpDto;
import com.wadekang.toyproject.courseregistrationsystem.repository.MajorRepository;
import com.wadekang.toyproject.courseregistrationsystem.repository.UserRepository;
import com.wadekang.toyproject.courseregistrationsystem.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    MajorRepository majorRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    DataSource dataSource;

    @BeforeAll
    public void init() {
        try (Connection conn = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("/db/h2/data.sql"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void 유저_회원가입() {
        // given
        UserSignUpDto signUpDto = UserSignUpDto.builder()
                .username("test")
                .loginId("testId")
                .password("test1234")
                .passwordConfirm("test1234")
                .email("test@test.com")
                .phoneNumber("01012345678")
                .majorId(majorRepository.findAll().get(0).getMajorId())
                .build();

        // when
        Long userId = userService.join(signUpDto);

        // then
        User user = userRepository.findById(userId).get();

        assertThat(user.getUsername()).isEqualTo("test");
        assertThat(user.getLoginId()).isEqualTo("testId");
        assertThat(passwordEncoder.matches("test1234", user.getPassword())).isEqualTo(true);
    }

    @Test
    public void 유저_회원가입_중복회원() {
        // given
        UserSignUpDto user1 = UserSignUpDto.builder()
                .username("test")
                .loginId("testId")
                .password("test1234")
                .passwordConfirm("test1234")
                .email("test@test.com")
                .phoneNumber("01012345678")
                .majorId(majorRepository.findAll().get(0).getMajorId())
                .build();

        Long savedId = userService.join(user1);

        // when
        // same loginId with user1
        UserSignUpDto user2 = UserSignUpDto.builder()
                .username("testUser")
                .loginId("testId")
                .password("test")
                .passwordConfirm("test")
                .email("testuser@test.com")
                .phoneNumber("01012345678")
                .majorId(majorRepository.findAll().get(0).getMajorId())
                .build();

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.join(user2);
        });

        // then
        assertThat(exception.getMessage()).isEqualTo("Failed: Already Exist ID!");
    }
}
