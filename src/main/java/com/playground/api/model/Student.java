package com.playground.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"teachers"})
@Entity(name = "student")
public class Student implements Serializable {
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    @Column(name = "student_name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "students", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private Set<Teacher> teachers = new HashSet<>();
}
