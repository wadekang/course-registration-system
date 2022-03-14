package com.wadekang.toyproject.courseregistrationsystem.controller;

import com.wadekang.toyproject.courseregistrationsystem.controller.dto.ClassSearch;
import com.wadekang.toyproject.courseregistrationsystem.domain.Classes;
import com.wadekang.toyproject.courseregistrationsystem.domain.Course;
import com.wadekang.toyproject.courseregistrationsystem.domain.Major;
import com.wadekang.toyproject.courseregistrationsystem.domain.User;
import com.wadekang.toyproject.courseregistrationsystem.service.ClassesService;
import com.wadekang.toyproject.courseregistrationsystem.service.CourseService;
import com.wadekang.toyproject.courseregistrationsystem.service.MajorService;
import com.wadekang.toyproject.courseregistrationsystem.service.TakeClassService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TakeClassController {

    private final MajorService majorService;
    private final CourseService courseService;
    private final ClassesService classesService;
    private final TakeClassService takeClassService;

    @GetMapping("/register")
    public String courseRegistration(@ModelAttribute("classSearch") ClassSearch classSearch,
                                     @RequestParam(value  = "msg", required = false) String msg,
                                     Model model) {

        List<Classes> classes = classesService.findByCourse(classSearch.getCourseId());
        List<Course> courses = courseService.findByMajor(classSearch.getMajorId());
        List<Major> majors = majorService.findAll();

        model.addAttribute("classes", classes);
        model.addAttribute("courses", courses);
        model.addAttribute("majors", majors);

        model.addAttribute("msg", msg);

        return "courseRegistration";
    }

    @GetMapping("/register/{id}")
    public String courseRegister(@PathVariable("id") Long classId, @AuthenticationPrincipal User user) {
        try {
            takeClassService.save(user.getUserId(), classId);
        } catch (IllegalArgumentException e) {
            return "redirect:/register?msg=" + e.getMessage();
        }

        return "redirect:/register?msg=Success!";
    }

    @GetMapping("/cancel/{id}")
    public String courseCancel(@PathVariable("id") Long takeId) {
        takeClassService.delete(takeId);

        return "redirect:/myCourses?msg=Success!";
    }
}
