package com.smartattendance.smart_attendance_system.repository;

import com.smartattendance.smart_attendance_system.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

    public interface CourseRepository extends JpaRepository<Course, Long> {

        // Find course by course code
        @Query(nativeQuery = true,
                value = "SELECT * FROM courses WHERE course_code = :courseCode")
        Optional<Course> findByCourseCode(@Param("courseCode") String courseCode);

        // Find course by ID (explicit native query, like your DrugRepository)
        @Query(nativeQuery = true,
                value = "SELECT * FROM courses WHERE id = :courseId")
        Optional<Course> findByCourseId(@Param("courseId") Long courseId);

        // Delete course by course code
        @Query(nativeQuery = true,
                value = "DELETE FROM courses WHERE course_code = :courseCode")
        void deleteByCourseCode(@Param("courseCode") String courseCode);

        // Get all courses for a lecturer
        @Query(nativeQuery = true,
                value = "SELECT * FROM courses WHERE lecturer_id = :lecturerId")
        List<Course> findByLecturerId(@Param("lecturerId") Long lecturerId);
}

