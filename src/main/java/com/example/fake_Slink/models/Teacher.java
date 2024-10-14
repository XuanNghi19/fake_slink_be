package com.example.fake_Slink.models;

import com.example.fake_Slink.dtos.requests.CreateTeacherRequest;
import com.example.fake_Slink.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "teacher")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_num", unique = true)
    private String idNum;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "dob")
    private Date dob;

    @Email(message = "Email invalid")
    @Column(name = "email")
    private String email;

    @Column(name = "CCCD")
    private String cccd;

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
    @JoinColumn(name = "department_id")
    private Department department;

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

    public static Teacher fromCreateTeacherRequest(
            CreateTeacherRequest request,
            String newIdNum,
            String encodePassword,
            Department department,
            Role role
    ) {
        return Teacher.builder()
                .idNum(newIdNum)
                .password(encodePassword)
                .name(request.getName())
                .dob(request.getDob())
                .email(request.getEmail())
                .cccd(request.getCccd())
                .phone1(request.getPhone1())
                .phone2(request.getPhone2())
                .sex(request.getSex())
                .address(request.getAddress())
                .department(department)
                .role(role)
                .build();
    }
}
