# Review week38 friday  
## [ORM + JPQL](https://docs.google.com/document/d/1mZ90qI9Itic0scu0D4kXwj4YEvlE7dAm9Js9nDnAtZk/edit#)
### General part

 * Explain the rationale behind the topic Object Relational Mapping and the Pros and Cons in using a ORM.  
_****_  

 * Discuss how we usually have queried a relational database  
_****_  


 * Discuss the methods we can use to query a JPA design and compare with what you explained above  
_****_  


### Practical part
The script created four tables (why FOUR)  
_**Semester og Teacher tables have a many to many relations. The DB needs a join table for that. That's the 4. table.**_  

 
 A) Use NetBeans to create a set of matching Entity Classes (see hints at the end). Make sure you understand what was created, and that you understand how classes and tables are related (almost a guaranteed discussion topic for the exam)  

B) Investigate the generated Entity classes and observe the NamedQueries generated by the Wizard.  

C) Create (preferable in a facade-class)  Dynamic Queries (or if possible, use a named Query generated by the wizard) to solve the following problems:  
_**Solutions are in main method of TheFacade.java**_  

1. [Find all Students in the system](https://github.com/cph-ms782/review_week38_friday/blob/85d6c4bc6e06221f175f506afa35a54db5da9036/ORM-JPQL/src/main/java/facades/TheFacade.java#L219)  

2. [Find all Students in the System with the first name Anders](https://github.com/cph-ms782/review_week38_friday/blob/85d6c4bc6e06221f175f506afa35a54db5da9036/ORM-JPQL/src/main/java/facades/TheFacade.java#L225)  

3. [Insert a new Student into the system](https://github.com/cph-ms782/review_week38_friday/blob/85d6c4bc6e06221f175f506afa35a54db5da9036/ORM-JPQL/src/main/java/facades/TheFacade.java#L231)  

4. [Assign a new student to a semester (given the student-id and semester-id)](https://github.com/cph-ms782/review_week38_friday/blob/85d6c4bc6e06221f175f506afa35a54db5da9036/ORM-JPQL/src/main/java/facades/TheFacade.java#L238)  

5. [Had the student above already been assigned to a semester he would suddenly be a member of two semesters, but still only have one reference back to the newest assigned semester. Fix this problem, preferably without losing historical information.](https://github.com/cph-ms782/review_week38_friday/blob/85d6c4bc6e06221f175f506afa35a54db5da9036/ORM-JPQL/src/main/java/facades/TheFacade.java#L245)  
[Is stopped by this in Student entity](https://github.com/cph-ms782/review_week38_friday/blob/448652bcd7a74d9eab0e48deb71d90974bebcd8e/ORM-JPQL/src/main/java/entity/Student.java#L82)

6. [Find (using JPQL) all Students in the system with the last name And](https://github.com/cph-ms782/review_week38_friday/blob/85d6c4bc6e06221f175f506afa35a54db5da9036/ORM-JPQL/src/main/java/facades/TheFacade.java#L256)  

7. [Find (using JPQL) the total amount of all students](https://github.com/cph-ms782/review_week38_friday/blob/85d6c4bc6e06221f175f506afa35a54db5da9036/ORM-JPQL/src/main/java/facades/TheFacade.java#L263)  

8. [Find (using JPQL)  the total number of students, for a semester given the semester name as a parameter.](https://github.com/cph-ms782/review_week38_friday/blob/85d6c4bc6e06221f175f506afa35a54db5da9036/ORM-JPQL/src/main/java/facades/TheFacade.java#L267)  

9. [Find (using JPQL) the total number of students in all semesters.](https://github.com/cph-ms782/review_week38_friday/blob/85d6c4bc6e06221f175f506afa35a54db5da9036/ORM-JPQL/src/main/java/facades/TheFacade.java#L275)  

10. [Find (using JPQL) the teacher(s) who teaches on most semesters.](https://github.com/cph-ms782/review_week38_friday/blob/85d6c4bc6e06221f175f506afa35a54db5da9036/ORM-JPQL/src/main/java/facades/TheFacade.java#L280)  

11. [Often (as in almost always) we don’t want a result that matches an Entity class, but a result that matches a specific customer requirement for a specific request.](https://github.com/cph-ms782/review_week38_friday/blob/85d6c4bc6e06221f175f506afa35a54db5da9036/ORM-JPQL/src/main/java/facades/TheFacade.java#L287)  

12. [Create a method, similar to the one above, but which returns a single StudentInfo, given a students id](https://github.com/cph-ms782/review_week38_friday/blob/85d6c4bc6e06221f175f506afa35a54db5da9036/ORM-JPQL/src/main/java/facades/TheFacade.java#L293)  

