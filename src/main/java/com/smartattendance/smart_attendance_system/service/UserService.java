package com.smartattendance.smart_attendance_system.service;

import com.smartattendance.smart_attendance_system.dto.AttendanceResponse;
import com.smartattendance.smart_attendance_system.dto.LoginRequestDto;
import com.smartattendance.smart_attendance_system.dto.RegisterUserDto;
import com.smartattendance.smart_attendance_system.dto.UpdateUserDto;
import com.smartattendance.smart_attendance_system.entity.User;
import com.smartattendance.smart_attendance_system.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

        private final UserRepository userRepository;

        public UserService(UserRepository userRepository){
            this.userRepository = userRepository;
        }

        public ResponseEntity<AttendanceResponse> registerUser(RegisterUserDto dto){
            try {
                if(userRepository.existsByIdentifier(dto.getIdentifier())){
                    return new ResponseEntity<>(new AttendanceResponse("99","User already exists", null), HttpStatus.CONFLICT);
                }

                User user = new User();
                user.setIdentifier(dto.getIdentifier());
                user.setFirstName(dto.getFirstName());
                user.setLastName(dto.getLastName());
                user.setEmail(dto.getEmail());
                user.setPassword(dto.getPassword()); // TODO: hash password
                user.setRole(dto.getRole());
                user.setDeviceId(dto.getDeviceId());

                userRepository.save(user);

            } catch(Exception e){
                return new ResponseEntity<>(new AttendanceResponse("99","Failed to register user", null), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(new AttendanceResponse("00","User registered successfully", null), HttpStatus.OK);
        }

        public ResponseEntity<AttendanceResponse> updateUser(UpdateUserDto dto){
            try{
                Optional<User> opt = userRepository.findByIdentifier(dto.getIdentifier());
                if(opt.isEmpty()){
                    return new ResponseEntity<>(new AttendanceResponse("99","User not found", null), HttpStatus.NOT_FOUND);
                }

                User user = opt.get();
                if(dto.getFirstName() != null) user.setFirstName(dto.getFirstName());
                if(dto.getLastName() != null) user.setLastName(dto.getLastName());
                if(dto.getEmail() != null) user.setEmail(dto.getEmail());
                if(dto.getPassword() != null) user.setPassword(dto.getPassword());
                if(dto.getRole() != null) user.setRole(dto.getRole());
                if(dto.getDeviceId() != null) user.setDeviceId(dto.getDeviceId());

                userRepository.save(user);

            } catch(Exception e){
                return new ResponseEntity<>(new AttendanceResponse("99","Failed to update user", null), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(new AttendanceResponse("00","User updated successfully", null), HttpStatus.OK);
        }

        public ResponseEntity<AttendanceResponse> login(LoginRequestDto dto){
            try{
                Optional<User> opt = userRepository.findByIdentifier(dto.getIdentifier());
                if(opt.isEmpty()){
                    return new ResponseEntity<>(new AttendanceResponse("99","User not found", null), HttpStatus.NOT_FOUND);
                }

                User user = opt.get();
                if(!user.getPassword().equals(dto.getPassword())){
                    return new ResponseEntity<>(new AttendanceResponse("99","Invalid credentials", null), HttpStatus.UNAUTHORIZED);
                }

            } catch(Exception e){
                return new ResponseEntity<>(new AttendanceResponse("99","Login failed", null), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(new AttendanceResponse("00","Login successful", null), HttpStatus.OK);
        }

        public List<User> getAllUsers(){
            return userRepository.findAll();
        }
}

