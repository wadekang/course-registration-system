# course-registration-system
Spring Boot 수강신청 웹 사이트 토이 프로젝트

___
## Domain

### User
- id (Long) (PK)
- loginId (String)
- password (String)
- userName (String)
- major (Major) (FK)
- role (String)
- email (String)
- phoneNumber (String)
- createdDate (Date)
- modifiedDate (Date)
- takeClasses (List<TakeClass>)

### Major
- id (Long) (PK)
- majorName (String)

### Course
- id (Long) (PK)
- major (Major) (FK)
- courseName (String)
- classes (List<Classes>) 

### Classes
- id (Long) (PK)
- course (Course)
- number (int)
- professorName (String)
- max_student_num (int)
- cur_student_num (int)
  
### TakeClass
- id (Long) (PK)
- user (User) (FK)
- classes (Classes) (FK) 

### Role (ENUM)
- STUDENT
- ADMIN



