package com.wadekang.toyproject.courseregistrationsystem.domain;

import com.wadekang.toyproject.courseregistrationsystem.controller.dto.UserUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity implements UserDetails {

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

    @Builder(builderClassName = "UserBuilder", builderMethodName = "userBuilder")
    public User(String loginId, String password, String username, Major major, Role role, String email, String phoneNumber) {
        this.loginId = loginId;
        this.password = password;
        this.username = username;
        this.major = major;
        this.role = role;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Builder(builderClassName = "UserSignUpBuilder", builderMethodName = "signupBuilder")
    public User(String loginId, String password, String username, Major major, String email, String phoneNumber) {
        this.loginId = loginId;
        this.password = password;
        this.username = username;
        this.major = major;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = Role.STUDENT;
    }

    public void update(UserUpdateRequestDto requestDto) {
        this.email = requestDto.getEmail();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.major = requestDto.getMajor();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
