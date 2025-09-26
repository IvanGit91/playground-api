package com.playground.api.controller;

import com.playground.api.model.Student;
import com.playground.api.model.Teacher;
import com.playground.api.repository.StudentRepository;
import com.playground.api.repository.TeacherRepository;
import com.playground.api.service.StudentService;
import com.playground.api.service.TeacherStudentService;
import com.playground.api.view.TeacherStudentView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/school")
@RequiredArgsConstructor
public class SchoolController {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final StudentService studentService;
    private final TeacherStudentService teacherStudentService;

    // get student
    @GetMapping("/student/hello")
    public ResponseEntity<String> getStudentHello() {
        String lota = studentService.findHello();
        return new ResponseEntity<>(lota, HttpStatus.OK);
    }

    //create teacher
    @PostMapping("/teacher")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        Teacher _teacher = teacherRepository.save(teacher);
        return new ResponseEntity<>(_teacher, HttpStatus.CREATED);
    }

    @GetMapping("/teacher/students")
    public ResponseEntity<List<TeacherStudentView>> getTeacherStudents() {
        List<TeacherStudentView> ts = teacherStudentService.findTeacherStudents();
        return new ResponseEntity<>(ts, HttpStatus.CREATED);
    }

    //create student
    @PostMapping("/student")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student _student = studentRepository.save(student);
        return new ResponseEntity<>(_student, HttpStatus.CREATED);
    }

    //add student to a teacher
    @PostMapping("/teacher/{teacherId}/addStudent")
    public ResponseEntity<Teacher> addStudent(@PathVariable(value = "teacherId") Long teacherId, @RequestBody Student addStudent) {
        Optional<Teacher> teacherOpt = teacherRepository.findById(teacherId);

        if (teacherOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Teacher teacher = teacherOpt.get();
        Student student = null;
        boolean studentAlreadyPresent = addStudent.getId() != 0;
        if (studentAlreadyPresent) {
            student = studentRepository.findById(addStudent.getId()).orElse(null);
            if (student != null) {
                addStudent = student;
            }
        }
        if (!studentAlreadyPresent || student == null) {
            addStudent = studentRepository.save(addStudent);
        }
        teacher.getStudents().add(addStudent);
        teacherRepository.save(teacher);

        return new ResponseEntity<>(teacher, HttpStatus.CREATED);
    }

    // get student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable(value = "studentId") Long studentId) {
        Student student = studentService.findStudentById(studentId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    //get students of a teacher
    @GetMapping("/teacher/{teacherId}/students")
    public ResponseEntity<List<Student>> getStudentsOfATeacher(@PathVariable(value = "teacherId") Long teacherId) {
        List<Student> students = studentRepository.findStudentsByTeacherIdNative(teacherId);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // get teachers of a student
    @GetMapping("/student/{studentId}/teachers")
    public ResponseEntity<List<Teacher>> getTeachersOfAStudent(@PathVariable(value = "studentId") Long studentId) {
        List<Teacher> teachers = teacherRepository.findTeachersByStudentId(studentId);
        // TODO - exclude students
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }
}
