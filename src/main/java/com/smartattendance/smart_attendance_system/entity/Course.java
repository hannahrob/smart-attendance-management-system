package com.smartattendance.smart_attendance_system.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "classes")
public class Course {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "course_code", nullable = false, unique = true)
        private String courseCode;

        @Column(name = "course_title", nullable = false)
        private String courseTitle;

                // Lecturer teaching the course
                @ManyToOne
                @JoinColumn(name = "lecturer_id", nullable = false)
                private User lecturer;

                // Students registered for the course
                @ManyToMany
                @JoinTable(
                        name = "course_students",
                        joinColumns = @JoinColumn(name = "course_id"),
                        inverseJoinColumns = @JoinColumn(name = "student_id")
                )
                private Set<User> students = new HashSet<>();
}


