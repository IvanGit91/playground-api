package com.playground.api.repository;

import com.playground.api.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query("SELECT t FROM teachers t JOIN t.students s WHERE s.id = :studentId")
    List<Teacher> findTeachersByStudentId(@Param("studentId") Long studentId);
}
