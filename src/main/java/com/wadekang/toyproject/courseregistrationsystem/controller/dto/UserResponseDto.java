package com.wadekang.toyproject.courseregistrationsystem.controller.dto;

import com.wadekang.toyproject.courseregistrationsystem.domain.Major;
import com.wadekang.toyproject.courseregistrationsystem.domain.TakeClass;
import com.wadekang.toyproject.courseregistrationsystem.domain.User;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class UserResponseDto {

    private Long userId;
    private String loginId;
    private String username;
    private String email;
    private String phoneNumber;
    private Major major;
    private List<TakeClass> takeClasses;

    public UserResponseDto(User entity) {
        this.userId = entity.getUserId();
        this.loginId = entity.getLoginId();
        this.username = entity.getUsername();
        this.email = entity.getEmail();
        this.phoneNumber = entity.getPhoneNumber();
        this.major = entity.getMajor();
        this.takeClasses = entity.getTakeClasses();
    }
}
