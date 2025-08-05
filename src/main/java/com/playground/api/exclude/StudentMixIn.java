package com.playground.api.exclude;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.playground.api.model.Student;

import java.util.Set;

public abstract class StudentMixIn {
    @JsonIgnore
    private Set<Student> students; // The field to exclude
}
