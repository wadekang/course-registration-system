package com.wadekang.toyproject.courseregistrationsystem.repository;

import com.wadekang.toyproject.courseregistrationsystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.loginId = :loginId and u.password = :password")
    Optional<User> findLoginUser(@Param("loginId") String loginId, @Param("password") String password);

    User findByLoginId(String loginId);
}
