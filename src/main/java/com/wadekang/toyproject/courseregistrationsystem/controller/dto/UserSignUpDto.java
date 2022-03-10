package com.wadekang.toyproject.courseregistrationsystem.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserSignUpDto {

    private String username;
    private String loginId;
    private String password;

    private String email;
    private String phoneNumber;
    private Long majorId;
}