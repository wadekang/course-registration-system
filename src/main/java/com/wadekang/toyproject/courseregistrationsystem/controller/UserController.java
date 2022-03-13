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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final MajorService majorService;

    @GetMapping("/login")
    public String login(Model model,
                        @RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        model.addAttribute("loginDto", new UserLoginDto());

        return "login";
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
