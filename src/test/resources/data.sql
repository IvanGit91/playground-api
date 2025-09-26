SET
REFERENTIAL_INTEGRITY FALSE;
TRUNCATE TABLE teachers;
TRUNCATE TABLE students;
SET
REFERENTIAL_INTEGRITY TRUE;

ALTER TABLE teachers
    ALTER COLUMN id RESTART WITH 1;
ALTER TABLE students
    ALTER COLUMN id RESTART WITH 1;

INSERT INTO teachers(id, name)
VALUES ('1', 'Mark');
INSERT INTO teachers(id, name)
VALUES ('2', 'Irvan');

INSERT INTO students(id, name)
VALUES ('1', 'Salo');
INSERT INTO students(id, name)
VALUES ('2', 'Joy');
INSERT INTO students(id, name)
VALUES ('3', 'Jeff');
INSERT INTO students(id, name)
VALUES ('4', 'Maya');
INSERT INTO students(id, name)
VALUES ('5', 'Srohi');

ALTER TABLE teachers
    ALTER COLUMN id RESTART WITH 3;
ALTER TABLE students
    ALTER COLUMN id RESTART WITH 6;