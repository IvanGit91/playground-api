package com.playground.api.view;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Immutable // tells Hibernate: "This entity is read-only" — no INSERT, UPDATE, DELETE.
@Entity
@Table(name = "teacher_student_view")
@Subselect("select teacher_id, teacher_name, student_name from teacher_student_view")
// Subselect says: “There is no physical table for this entity”. "Just use this SQL to read data"
// Hibernate will NOT try to create or alter anything!
// The view create command should be run manually or with Flyaway
public class TeacherStudentView {
    @Id
    private Long teacherId;
    private String teacherName;
    private String studentName;
}
