package com.wadekang.toyproject.courseregistrationsystem.controller.dto;

import com.wadekang.toyproject.courseregistrationsystem.domain.Major;
import lombok.Getter;

@Getter
public class UserUpdateRequestDto {

    private String email;
    private String phoneNumber;
    private Major major;

    public UserUpdateRequestDto(String email, String phoneNumber, Major major) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.major = major;
    }
}
