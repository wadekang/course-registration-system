package com.wadekang.toyproject.courseregistrationsystem.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserUpdateRequestDto {

    private String loginId;
    private String username;
    private String email;
    private String phoneNumber;
    private String majorName;

    public UserUpdateRequestDto(String loginId, String username, String email, String phoneNumber, String majorName) {
        this.loginId = loginId;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.majorName = majorName;
    }

    public UserUpdateRequestDto(UserResponseDto userResponseDto) {
        this.loginId = userResponseDto.getLoginId();
        this.username = userResponseDto.getUsername();
        this.email = userResponseDto.getEmail();
        this.phoneNumber = userResponseDto.getPhoneNumber();
        this.majorName = userResponseDto.getMajor().getMajorName();
    }
}
