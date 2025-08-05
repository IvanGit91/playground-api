package com.playground.api.repository;

import com.playground.api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM student s JOIN s.teachers t WHERE t.id = :teacherId")
    List<Student> findStudentsByTeacherId(@Param("teacherId") Long teacherId);

    @Query(value = "SELECT s.* FROM student s JOIN teachers_students ts ON s.student_id = ts.student_id WHERE ts.teacher_id = :teacherId", nativeQuery = true)
    List<Student> findStudentsByTeacherIdNative(@Param("teacherId") Long teacherId);

    // It seems not working for functions
    @Procedure("t_say_hello")
    String getHello();

    @Query(value = "SELECT t_say_hello()", nativeQuery = true)
    String getHello2();
}
