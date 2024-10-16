package com.example.fake_Slink.models;

import com.example.fake_Slink.dtos.requests.CreateStudentRequest;
import com.example.fake_Slink.dtos.requests.UpdateStudentRequest;
import com.example.fake_Slink.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.apache.catalina.connector.Request;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "course")
    private String course;

    @Column(name = "id_num")
    private String idNum;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "CCCD")
    private String cccd;

    @Email(message = "Email invalid")
    @Column(name = "email")
    private String email;

    @Column(name = "phone1", unique = true, nullable = false)
    private String phone1;

    @Column(name = "phone2", unique = true)
    private String phone2;

    @Column(name = "sex")
    private String sex;

    @Column(name = "address")
    private String address;

    @Column(name = "role")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "major_id")
    private Major major;

    @Column(name = "gpa")
    private float gpa;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name().toUpperCase()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return idNum;
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

    public static Student fromCreateStudentRequest(
            CreateStudentRequest request,
            String newIdNum,
            String encodePassword,
            Major major
    ){
        return Student.builder()
                .idNum(newIdNum)
                .course(request.getCourse())
                .password(encodePassword)
                .name(request.getName())
                .dob(request.getDob())
                .email(request.getEmail())
                .cccd(request.getCccd())
                .phone1(request.getPhone1())
                .phone2(request.getPhone2())
                .sex(request.getSex())
                .address(request.getAddress())
                .major(major)
                .gpa(0f)
                .role(Role.STUDENT)
                .build();
    }

    public static Student fromUpdateStudentRequest(
            UpdateStudentRequest request,
            Student student
    ){
        return Student.builder()
                .id(student.getId())
                .idNum(student.getIdNum())
                .course(student.getCourse())
                .password(student.getPassword())
                .name(request.getName())
                .dob(request.getDob())
                .email(request.getEmail())
                .cccd(request.getCccd())
                .phone1(request.getPhone1())
                .phone2(request.getPhone2())
                .sex(request.getSex())
                .address(request.getAddress())
                .major(student.getMajor())
                .gpa(student.getGpa())
                .role(Role.STUDENT)
                .build();
    }
}
