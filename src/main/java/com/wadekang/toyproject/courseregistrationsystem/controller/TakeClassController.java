package com.wadekang.toyproject.courseregistrationsystem.controller;

import com.wadekang.toyproject.courseregistrationsystem.controller.dto.CourseSearch;
import com.wadekang.toyproject.courseregistrationsystem.controller.dto.MajorSearch;
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
    public String courseRegistration(@ModelAttribute("majorSearch") MajorSearch majorSearch,
                                     @ModelAttribute("courseSearch") CourseSearch courseSearch,
                                     Model model) {

        List<Classes> classes = classesService.findByCourse(courseSearch);
        List<Course> courses = courseService.findByMajor(majorSearch);
        List<Major> majors = majorService.findAll();

        model.addAttribute("classes", classes);
        model.addAttribute("courses", courses);
        model.addAttribute("majors", majors);

        return "courseRegistration";
    }
}
