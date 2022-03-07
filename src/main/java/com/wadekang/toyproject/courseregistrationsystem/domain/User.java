package com.wadekang.toyproject.courseregistrationsystem.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @ManyToOne(targetEntity = Major.class, fetch = FetchType.LAZY)
    @JoinColumn(name="major_id")
    private Major major;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @OneToMany(mappedBy = "user")
    private List<TakeClass> takeClasses;

    @Builder(builderClassName = "StudentBuilder")
    public User(String loginId, String password, String username, Major major, Role role, String email, String phoneNumber) {
        this.loginId = loginId;
        this.password = password;
        this.username = username;
        this.major = major;
        this.role = role;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
