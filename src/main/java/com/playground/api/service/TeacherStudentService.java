package com.playground.api.service;

import com.playground.api.repository.TeacherStudentViewRepository;
import com.playground.api.view.TeacherStudentView;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherStudentService {
    private final JdbcTemplate jdbcTemplate;
    private final TeacherStudentViewRepository teacherStudentViewRepository;

    public List<TeacherStudentView> findTeacherStudents() {
        return teacherStudentViewRepository.findAll();
    }

    public List<TeacherStudentView> findTeacherStudents2() {
        String sql = "SELECT teacher_id, teacher_name, student_name FROM teacher_student_view";

        // Use BeanPropertyRowMapper to map rows to YourViewDto
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TeacherStudentView.class));
    }
}
