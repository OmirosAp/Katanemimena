INSERT INTO USERS (Username, Password, Name, Lastname, Role)
Values ('omap', '1', 'Omiros', 'Apollnios', 'Student'),
('antpeg', '1', 'Antonis', 'Pegkas', 'Student'),
('dimant', '2', 'Dimitris', 'Antonopoulos', 'Professor'),
('marpan', '1', 'Maria', 'Pantazi', 'Professor');

INSERT INTO STUDENTS (Username, Semester, AM)
Values ('omap', 9, 21503),
('antpeg', 7, 217123);

INSERT INTO PROFESSORS (Username, Course)
Values ('dimant', 'Java'),
('marpan', 'Security'), 
('marpan', 'Python');


