INSERT INTO students (id, name)
VALUES (1, 'student 1')
ON CONFLICT DO NOTHING;
SELECT setval('students_id_seq', (SELECT max(id) FROM students));