package com.smartattendance.smart_attendance_system.repository;

import com.smartattendance.smart_attendance_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
    public interface UserRepository extends JpaRepository<User, Long> {

        // Find user by identifier (student matric number or staff ID)
        @Query(nativeQuery = true, value = "SELECT * FROM users WHERE identifier = :identifier")
        Optional<User> findByIdentifier(@Param("identifier") String identifier);

       // Optional single user by identifier
//        @Query(nativeQuery = true, value = "SELECT * FROM users WHERE identifier = :identifier LIMIT 1")
//        Optional<User> findOptionalByIdentifier(@Param("identifier") String identifier);

        // Check existence by identifier
        @Query(nativeQuery = true, value = "SELECT COUNT(*) > 0 FROM users WHERE identifier = :identifier")
        boolean existsByIdentifier(@Param("identifier") String identifier);

        // Delete user by identifier
        @Query(nativeQuery = true, value = "DELETE FROM users WHERE identifier = :identifier")
        void deleteByIdentifier(@Param("identifier") String identifier);
}


