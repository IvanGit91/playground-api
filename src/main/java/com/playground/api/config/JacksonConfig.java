//package com.playground.api.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.playground.api.exclude.StudentMixIn;
//import com.playground.api.model.Teacher;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//

// mix-in Jackson configuration

//@Configuration
//public class JacksonConfig {
//    @Bean
//    public ObjectMapper objectMapper() {
//        ObjectMapper mapper = new ObjectMapper();
//        // Exclude the students field in teacher
//        mapper.addMixIn(Teacher.class, StudentMixIn.class);
//        return mapper;
//    }
//}
