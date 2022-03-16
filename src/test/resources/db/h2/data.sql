insert into MAJOR (MAJOR_NAME) values ( 'Computer Science' );

insert into COURSE (COURSE_NAME, MAJOR_ID) VALUES ( 'Java Programming', 1);

insert into CLASSES (CLASS_NUMBER, CUR_STUDENT_NUM, MAX_STUDENT_NUM, PROFESSOR_NAME, COURSE_ID) VALUES ( 1, 0, 2, 'KIM', 1 );
insert into CLASSES (CLASS_NUMBER, CUR_STUDENT_NUM, MAX_STUDENT_NUM, PROFESSOR_NAME, COURSE_ID) VALUES ( 2, 0, 2, 'LEE', 1 );

insert into USER (EMAIL, LOGIN_ID, PASSWORD, PHONE_NUMBER, ROLE, USERNAME, MAJOR_ID) VALUES ( 'test@test.com', 'test1', 'test1234', '1234', 'STUDENT', 'test1', 1 );
insert into USER (EMAIL, LOGIN_ID, PASSWORD, PHONE_NUMBER, ROLE, USERNAME, MAJOR_ID) VALUES ( 'test@test.com', 'test2', 'test1234', '1234', 'STUDENT', 'test2', 1 );
insert into USER (EMAIL, LOGIN_ID, PASSWORD, PHONE_NUMBER, ROLE, USERNAME, MAJOR_ID) VALUES ( 'test@test.com', 'test3', 'test1234', '1234', 'STUDENT', 'test3', 1 );