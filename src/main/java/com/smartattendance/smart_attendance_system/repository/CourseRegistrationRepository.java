package com.smartattendance.smart_attendance_system.repository;

import com.smartattendance.smart_attendance_system.entity.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {
     boolean existsByStudentIdentifierAndCourseCode(String studentIdentifier, String courseCode);
        List<CourseRegistration> findByStudentIdentifier(String studentIdentifier);

}
