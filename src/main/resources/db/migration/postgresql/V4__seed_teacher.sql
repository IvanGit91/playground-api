INSERT INTO teachers (id, name)
VALUES (1, 'lala'),
       (2, 'lolo')
ON CONFLICT DO NOTHING;
SELECT setval('teachers_id_seq', (SELECT max(id) FROM teachers));