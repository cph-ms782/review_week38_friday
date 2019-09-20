# Review week38 friday  
## ORM + JPQL
### General part

 * Explain the rationale behind the topic Object Relational Mapping and the Pros and Cons in using a ORM.  
_****_  

 * Discuss how we usually have queried a relational database  
_****_  


 * Discuss the methods we can use to query a JPA design and compare with what you explained above  
_****_  


### Practical part
The script created four tables (why FOUR)  

 
 A) Use NetBeans to create a set of matching Entity Classes (see hints at the end). Make sure you understand what was created, and that you understand how classes and tables are related (almost a guaranteed discussion topic for the exam)  

B) Investigate the generated Entity classes and observe the NamedQueries generated by the Wizard.  

C) Create (preferable in a facade-class)  Dynamic Queries (or if possible, use a named Query generated by the wizard) to solve the following problems:  

1. Find all Students in the system  

2. Find all Students in the System with the first name Anders  

3. Insert a new Student into the system  

4. Assign a new student to a semester (given the student-id and semester-id)  

5. Had the student above already been assigned to a semester he would suddenly be a member of two semesters, but still only have one reference back to the newest assigned semester. Fix this problem, preferably without losing historical information.
6. Find (using JPQL) all Students in the system with the last name And
7. Find (using JPQL) the total amount of all students
8. Find (using JPQL)  the total number of students, for a semester given the semester name as a parameter.  
9. Find (using JPQL) the total number of students in all semesters.
10. Find (using JPQL) the teacher(s) who teaches on most semesters.
11. Often (as in almost always) we don’t want a result that matches an Entity class, but a result that matches a specific customer requirement for a specific request.
12. Create a method, similar to the one above, but which returns a single StudentInfo, given a students id as sketched below:
StudentInfo getStudentInfo(long id)

