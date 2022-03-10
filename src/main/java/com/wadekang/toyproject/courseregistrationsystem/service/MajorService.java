package com.wadekang.toyproject.courseregistrationsystem.service;

import com.wadekang.toyproject.courseregistrationsystem.domain.Major;
import com.wadekang.toyproject.courseregistrationsystem.repository.MajorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MajorService {

    private final MajorRepository majorRepository;

    public Major findById(Long majorId) {
        return majorRepository.findById(majorId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 전공입니다."));
    }

    public List<Major> findAll() {
        return majorRepository.findAll();
    }
}
