package com.wadekang.toyproject.courseregistrationsystem.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserSignUpDto {

    private String username;
    private String loginId;
    private String password;
    private String passwordConfirm;

    private String email;
    private String phoneNumber;
    private Long majorId;

    @Builder
    public UserSignUpDto(String username, String loginId, String password, String passwordConfirm, String email, String phoneNumber, Long majorId) {
        this.username = username;
        this.loginId = loginId;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.majorId = majorId;
    }
}