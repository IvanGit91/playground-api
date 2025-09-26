package com.playground.api.service;

import com.playground.api.model.Student;
import com.playground.api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student findStudentById(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public String findHello() {
        return studentRepository.getHello2();
    }
}
