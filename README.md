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

<img src="https://user-images.githubusercontent.com/49421226/157010455-707b5975-4006-446b-a8ff-e78963484a90.png" width="450" height="900">

