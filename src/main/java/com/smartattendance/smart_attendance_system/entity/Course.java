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
@Entity(name = "courses")
public class Course {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "course_id")
        private Long courseId;

        @Column(name = "course_code")
        private String courseCode;

        @Column(name = "course_title")
        private String courseTitle;

        @Column(name = "level")
        private Integer level;

        @Column(name = "course_unit")
        private Integer courseUnit;

        @Column(name = "department")
        private String department;

         @Column(name = "semester")
        private String semester;

        @Column(name = "lecturer_identifier")
        private String lecturerIdentifier;

                // Lecturer teaching the course
//                @ManyToOne
//                @JoinColumn(name = "lecturer_id", nullable = false)
//                private User lecturer;


                // Students registered for the course
//                @ManyToMany
//                @JoinTable(
//                        name = "course_students",
//                        joinColumns = @JoinColumn(name = "course_id"),
//                        inverseJoinColumns = @JoinColumn(name = "student_id")
//                )
//                private Set<User> students = new HashSet<>();
}


