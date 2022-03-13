package com.wadekang.toyproject.courseregistrationsystem.controller;

import com.wadekang.toyproject.courseregistrationsystem.controller.dto.ClassSearch;
import com.wadekang.toyproject.courseregistrationsystem.domain.Classes;
import com.wadekang.toyproject.courseregistrationsystem.domain.Course;
import com.wadekang.toyproject.courseregistrationsystem.domain.Major;
import com.wadekang.toyproject.courseregistrationsystem.service.ClassesService;
import com.wadekang.toyproject.courseregistrationsystem.service.CourseService;
import com.wadekang.toyproject.courseregistrationsystem.service.MajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TakeClassController {

    private final MajorService majorService;
    private final CourseService courseService;
    private final ClassesService classesService;

    @GetMapping("/register")
    public String courseRegistration(@ModelAttribute("classSearch") ClassSearch classSearch,
                                     Model model) {

        List<Classes> classes = classesService.findByCourse(classSearch.getCourseId());
        List<Course> courses = courseService.findByMajor(classSearch.getMajorId());
        List<Major> majors = majorService.findAll();

        model.addAttribute("classes", classes);
        model.addAttribute("courses", courses);
        model.addAttribute("majors", majors);

        return "courseRegistration";
    }
}
