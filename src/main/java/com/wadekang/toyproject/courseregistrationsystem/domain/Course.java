package com.wadekang.toyproject.courseregistrationsystem.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @ManyToOne(targetEntity = Major.class, fetch = FetchType.LAZY)
    @JoinColumn(name="major_id")
    private Major major;

    @Column(nullable = false)
    private String courseName;

    @OneToMany(mappedBy = "course")
    private List<Classes> classes;

    @Builder
    public Course(Major major, String courseName, List<Classes> classes) {
        this.major = major;
        this.courseName = courseName;
        this.classes = classes;
    }
}
