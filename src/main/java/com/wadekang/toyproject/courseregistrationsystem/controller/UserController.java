package com.wadekang.toyproject.courseregistrationsystem.controller;

import com.wadekang.toyproject.courseregistrationsystem.controller.dto.UserLoginDto;
import com.wadekang.toyproject.courseregistrationsystem.controller.dto.UserResponseDto;
import com.wadekang.toyproject.courseregistrationsystem.controller.dto.UserSignUpDto;
import com.wadekang.toyproject.courseregistrationsystem.domain.Major;
import com.wadekang.toyproject.courseregistrationsystem.service.MajorService;
import com.wadekang.toyproject.courseregistrationsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final MajorService majorService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginDto", new UserLoginDto());
        return "login";
    }

    @PostMapping("/login")
    public String userLogin(@Validated UserLoginDto loginDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "login";
        }

        UserResponseDto loginUser = userService.findLoginUser(loginDto.getLoginId(), loginDto.getPassword());

        log.info("{}", loginUser);

        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        List<Major> majors = majorService.findAll();

        model.addAttribute("signUpDto", new UserSignUpDto());
        model.addAttribute("majors", majors);
        return "signup";
    }

    @PostMapping("/signup")
    public String create(@Validated UserSignUpDto signUpDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "signup";
        }

        userService.join(signUpDto);

        return "redirect:/";
    }
}
