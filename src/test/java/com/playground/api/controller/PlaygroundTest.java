package com.playground.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.playground.api.model.Student;
import com.playground.api.model.Teacher;
import org.h2.tools.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.SQLException;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
class PlaygroundTest {
    ObjectMapper om = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    // For debugging purposes, make h2 console available at port http://localhost:8082
    @BeforeAll
    static void initTest() throws SQLException {
        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082")
                .start();
    }

    @Test
    void addNewStudentToATeacher() throws Exception {
        Student expected = Student.builder()
                .name("New")
                .build();

        Teacher actual = om.readValue(mockMvc.perform(post("/school/teacher/1/addStudent")
                        .contentType("application/json")
                        .content(om.writeValueAsString(expected)))
                .andDo(print())
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(), Teacher.class);

        Assertions.assertEquals(1, actual.getStudents().stream().filter(s -> s.getName().equals(expected.getName())).count());
    }

    @Test
    void addExistingStudentToATeacher() throws Exception {
        Student joy = Student.builder()
                .id(1)
                .name("Salo")
                .build();

        Teacher actual = om.readValue(mockMvc.perform(post("/school/teacher/1/addStudent")
                        .contentType("application/json")
                        .content(om.writeValueAsString(joy)))
                .andDo(print())
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(), Teacher.class);

        Assertions.assertEquals(1, actual.getStudents().stream().filter(s -> s.getName().equals("Salo")).count());
    }

    @Test
    void getStudentsOfATeacher() throws Exception {
        addNewStudentToATeacher();
        addExistingStudentToATeacher();

        Set<Student> actual = om.readValue(mockMvc.perform(get("/school/teacher/1/students")
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), new TypeReference<Set<Student>>() {
        });

        Assertions.assertEquals(2, actual.size());
        Assertions.assertEquals("New,Salo", actual.stream().map(Student::getName).sorted().collect(Collectors.joining(",")));
    }

    @Test
    void getTeachersOfAStudent() throws Exception {
        addNewStudentToATeacher();
        addExistingStudentToATeacher();

        Set<Teacher> actual = om.readValue(mockMvc.perform(get("/school/student/1/teachers")
                        .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), new TypeReference<Set<Teacher>>() {
        });

        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals("Mark", actual.stream().map(Teacher::getName).sorted().collect(Collectors.joining(",")));
    }
}
