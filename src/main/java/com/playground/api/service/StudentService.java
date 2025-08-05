package com.playground.api.service;

import com.playground.api.model.Student;
import com.playground.api.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student findStudentById(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public String findHello() {
        return studentRepository.getHello2();
    }
}
