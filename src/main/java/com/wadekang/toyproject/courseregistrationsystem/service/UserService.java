package com.wadekang.toyproject.courseregistrationsystem.service;

import com.wadekang.toyproject.courseregistrationsystem.controller.dto.UserResponseDto;
import com.wadekang.toyproject.courseregistrationsystem.controller.dto.UserSignUpDto;
import com.wadekang.toyproject.courseregistrationsystem.controller.dto.UserUpdateRequestDto;
import com.wadekang.toyproject.courseregistrationsystem.domain.User;
import com.wadekang.toyproject.courseregistrationsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final MajorService majorService;

    @Transactional
    public Long join(UserSignUpDto signUpDto) {
        User user = User.signupBuilder()
                .loginId(signUpDto.getLoginId())
                .password(new BCryptPasswordEncoder().encode(signUpDto.getPassword()))
                .username(signUpDto.getUsername())
                .email(signUpDto.getEmail())
                .phoneNumber(signUpDto.getPhoneNumber())
                .major(majorService.findById(signUpDto.getMajorId()))
                .takeClasses(new ArrayList<>())
                .build();
        return userRepository.save(user).getUserId();
    }

    public UserResponseDto findById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id=" + userId));

        return new UserResponseDto(user);
    }

    public UserResponseDto findLoginUser(String loginId, String password) {
        User user = userRepository.findLoginUser(loginId, password).orElse(null);

        if(user == null) return null;

        return new UserResponseDto(user);
    }

    @Transactional
    public Long update(Long userId, UserUpdateRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id=" + userId));

        user.update(requestDto);

        return userId;
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        User user = userRepository.findByLoginId(loginId);

        if (user == null) throw new UsernameNotFoundException("해당 유저가 없습니다.");

        return user;
    }
}
