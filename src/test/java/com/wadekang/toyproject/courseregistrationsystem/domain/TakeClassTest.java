package com.wadekang.toyproject.courseregistrationsystem.domain;

import com.wadekang.toyproject.courseregistrationsystem.repository.*;
import com.wadekang.toyproject.courseregistrationsystem.service.TakeClassService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TakeClassTest {

    @Autowired
    ClassesRepository classesRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TakeClassService takeClassService;

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
    void 수강신청() {
        // given
        List<User> userList = userRepository.findAll();
        User user = userList.get(0);

        List<Classes> classList = classesRepository.findAll();
        Classes classes1 = classList.get(0);

        // when
        takeClassService.save(user.getUserId(), classes1.getClassId());

        // then
        List<TakeClass> takeClasses = user.getTakeClasses();
        TakeClass takeClass = takeClasses.get(0);

        assertThat(takeClass.getClasses().getProfessorName()).isEqualTo("KIM");
        assertThat(takeClass.getClasses().getCurStudentNum()).isEqualTo(1);

        assertThat(takeClass.getClasses().getCourse().getCourseName()).isEqualTo("Java Programming");
    }

    @Test
    void 중복수강신청() {
        // given
        List<User> userList = userRepository.findAll();
        User user = userList.get(0);

        List<Classes> classList = classesRepository.findAll();
        Classes classes1 = classList.get(0);
        Classes classes2 = classList.get(1);

        takeClassService.save(user.getUserId(), classes1.getClassId());

        // when
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            takeClassService.save(user.getUserId(), classes2.getClassId());
        });

        // then
        assertThat(illegalArgumentException.getMessage()).isEqualTo("Failed: Already Registered!");
    }

    @Test
    void 수강인원초과() {
        // given
        List<User> userList = userRepository.findAll();
        User user1 = userList.get(0);
        User user2 = userList.get(1);
        User user3 = userList.get(2);

        List<Classes> classList = classesRepository.findAll();
        Classes classes1 = classList.get(0);

        takeClassService.save(user1.getUserId(), classes1.getClassId());
        takeClassService.save(user2.getUserId(), classes1.getClassId());

        // when
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            takeClassService.save(user3.getUserId(), classes1.getClassId());
        });

        // then
        assertThat(illegalArgumentException.getMessage()).isEqualTo("Failed: Full");
    }
}
