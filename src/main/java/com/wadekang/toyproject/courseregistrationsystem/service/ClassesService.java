package com.wadekang.toyproject.courseregistrationsystem.service;

import com.wadekang.toyproject.courseregistrationsystem.domain.Classes;
import com.wadekang.toyproject.courseregistrationsystem.repository.ClassesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClassesService {

    private final ClassesRepository classesRepository;

    public Classes findById(Long classId) {
        return classesRepository.findById(classId)
                .orElseThrow(() -> new IllegalArgumentException("Failed: No Class Info"));
    }

    public List<Classes> findByCourse(Long courseId) {
        return classesRepository.findByCourse(courseId);
    }
}
