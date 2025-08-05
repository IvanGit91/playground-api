SET
REFERENTIAL_INTEGRITY FALSE;
TRUNCATE TABLE teacher;
TRUNCATE TABLE student;
SET
REFERENTIAL_INTEGRITY TRUE;
ALTER TABLE teacher
    ALTER COLUMN teacher_id RESTART WITH 1;
ALTER TABLE student
    ALTER COLUMN student_id RESTART WITH 1;

INSERT INTO teacher(teacher_id, teacher_name)
VALUES ('1', 'Mark');
INSERT INTO teacher(teacher_id, teacher_name)
VALUES ('2', 'Irvan');

INSERT INTO student(student_id, student_name)
VALUES ('1', 'Salo');
INSERT INTO student(student_id, student_name)
VALUES ('2', 'Joy');
INSERT INTO student(student_id, student_name)
VALUES ('3', 'Jeff');
INSERT INTO student(student_id, student_name)
VALUES ('4', 'Maya');
INSERT INTO student(student_id, student_name)
VALUES ('5', 'Srohi');