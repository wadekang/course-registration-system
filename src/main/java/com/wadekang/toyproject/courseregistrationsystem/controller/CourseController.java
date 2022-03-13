package com.wadekang.toyproject.courseregistrationsystem.controller;

import com.wadekang.toyproject.courseregistrationsystem.controller.dto.ClassSearch;
import com.wadekang.toyproject.courseregistrationsystem.domain.Course;
import com.wadekang.toyproject.courseregistrationsystem.domain.Major;
import com.wadekang.toyproject.courseregistrationsystem.service.CourseService;
import com.wadekang.toyproject.courseregistrationsystem.service.MajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final MajorService majorService;

    @GetMapping("/courses")
    public String courseList(@ModelAttribute("classSearch") ClassSearch classSearch, Model model) {
        List<Course> courses = courseService.findByMajor(classSearch.getMajorId());
        List<Major> majors = majorService.findAll();

        model.addAttribute("courses", courses);
        model.addAttribute("majors", majors);

        return "courseList";
    }

    @GetMapping("/courses/{id}")
    public String classList(@PathVariable("id") Long courseId, Model model) {
        Course course = courseService.findById(courseId);

        model.addAttribute("classes", course.getClasses());

        return "classList";
    }
}
