package com.playground.api.controller;

import com.playground.api.model.Student;
import com.playground.api.model.Teacher;
import com.playground.api.view.TeacherStudentView;
import com.playground.api.repository.StudentRepository;
import com.playground.api.repository.TeacherRepository;
import com.playground.api.service.StudentService;
import com.playground.api.service.TeacherStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final StudentService studentService;
    private final TeacherStudentService teacherStudentService;

    @Autowired
    public SchoolController(StudentRepository studentRepository,
                            TeacherRepository teacherRepository,
                            StudentService studentService,
                            TeacherStudentService teacherStudentService) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.studentService = studentService;
        this.teacherStudentService = teacherStudentService;
    }

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

        Teacher teacher = teacherRepository.findById(teacherId).get();

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
        //addStudent.getTeachers().add(teacher);
        teacherRepository.save(teacher);
        //studentRepository.save(addStudent);

        return new ResponseEntity<Teacher>(teacher, HttpStatus.CREATED);
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
        //Teacher teacher = teacherRepository.findById(teacherId).get();
        List<Student> students = studentRepository.findStudentsByTeacherIdNative(teacherId);
        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
    }

    // get teachers of a student
    @GetMapping("/student/{studentId}/teachers")
    public ResponseEntity<List<Teacher>> getTeachersOfAStudent(@PathVariable(value = "studentId") Long studentId) {
        //Student student = studentRepository.findById(studentId).get();
        List<Teacher> teachers = teacherRepository.findTeachersByStudentId(studentId);
        // TODO - exclude students
        return new ResponseEntity<List<Teacher>>(teachers, HttpStatus.OK);
    }
}
