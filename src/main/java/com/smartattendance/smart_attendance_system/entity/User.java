package com.smartattendance.smart_attendance_system.entity;

import com.smartattendance.smart_attendance_system.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "user_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "User_ID")
        private Long userId;
    @Column(name = "Identifier")
        private String identifier;
    @Column(name = "First_Name")
        private String firstName;
    @Column(name = "Last_Name")
    private String lastName;
    @Column(name = "Email")
        private String email;
    @Column(name = "Password")
        private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // STUDENT, LECTURER, ADMIN
    @Column(name = "Device_ID")
        private String deviceId; // for device binding
    @OneToMany(mappedBy = "assignedLecturer")
    private List<Course> classes;

}
