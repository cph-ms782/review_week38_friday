package facades;

import entity.Semester;
import entity.Student;
import entity.Teacher;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import mappers.StudentInfo;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class TheFacade {

    private static TheFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private TheFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static TheFacade getTheFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new TheFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Student> getAllStudents() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Student> query
                    = em.createNamedQuery("Student.findAll", Student.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Student> getStudentsByFirstname(String firstname) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Student> query
                    = em.createNamedQuery("Student.findByFirstname", Student.class)
                            .setParameter("firstname", firstname);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Student> getStudentsByLastname(String lastname) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Student> query
                    = em.createNamedQuery("Student.findByLastname", Student.class)
                            .setParameter("lastname", lastname);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Semester getSemester(int semesterID) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query
                    = em.createNamedQuery("Semester.findById", Semester.class)
                            .setParameter("id", semesterID);
            return (Semester) query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public Student addStudent(Student s) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
            return s;
        } finally {
            em.close();
        }
    }

    public Student addStudentToSemester(Long studentID, int semesterID) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student s = em.find(Student.class, studentID);
            Semester se = em.find(Semester.class, (long) semesterID);
            s.setSemester(se);
            em.persist(s);
            em.getTransaction().commit();
            return s;
        } finally {
            em.close();
        }
    }

    public Long studentCount() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query
                    = em.createQuery("SELECT COUNT(s) FROM Student s",
                            Long.class);
            return (Long) query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public Long findStudentsPerSemester(String semesterName) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query
                    = em.createQuery("SELECT COUNT(s) FROM Student s JOIN s.semester sem where sem.name = :semesterName",
                            Long.class).setParameter("semesterName", semesterName);
            return (Long) query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public Long findAllStudentsPerSemester() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query
                    = em.createQuery("SELECT COUNT(s) FROM Student s where s.semester IS NOT NULL",
                            Long.class);
            return (Long) query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public Teacher findTeacherWithMostSemesters() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query
                    = em.createQuery("SELECT t FROM Teacher t WHERE t.id = (Select MAX(t2.id) from Semester s join s.teacherCollection t2)",
                            Teacher.class);
            return (Teacher) query.getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<StudentInfo> getStudentsInfo() {
        EntityManager em = emf.createEntityManager();
//        List<StudentInfo> studentInfoList = new ArrayList();
        try {
            TypedQuery<StudentInfo> query
                    = em.createQuery("SELECT NEW mappers.StudentInfo(s.firstname, s.lastname, s.id, s.semester.name, s.semester.description) FROM Student s where s.semester IS NOT NULL",
                            StudentInfo.class);
//            for (Student st : query.getResultList()) {
//                studentInfoList.add(new StudentInfo(
//                        st.getFirstname(), st.getLastname(), 
//                        st.getId(), 
//                        st.getSemester().getName(), 
//                        st.getSemester().getDescription()));
//            }
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public StudentInfo getStudentInfo(int id) {
        EntityManager em = emf.createEntityManager();
//        List<StudentInfo> studentInfoList = new ArrayList();
        try {
            Query query
                    = em.createQuery("SELECT NEW mappers.StudentInfo(s.firstname, s.lastname, s.id, s.semester.name, s.semester.description) FROM Student s WHERE s.semester IS NOT NULL AND s.id = :id",
                            StudentInfo.class).setParameter("id", id);
//            for (Student st : query.getResultList()) {
//                studentInfoList.add(new StudentInfo(
//                        st.getFirstname(), st.getLastname(), 
//                        st.getId(), 
//                        st.getSemester().getName(), 
//                        st.getSemester().getDescription()));
//            }
            return (StudentInfo) query.getSingleResult();
        } finally {
            em.close();
        }
    }

    private static void printingStudent(Student stud) {
        System.out.println(
                "#" + stud.getId()
                + " " + stud.getFirstname()
                + " " + stud.getLastname()
        );
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
        TheFacade facade = TheFacade.getTheFacade(emf);

        //Find all Students in the system
        System.out.println("All students:");
        for (Student stud : facade.getAllStudents()) {
            printingStudent(stud);
        }

        // Find all Students in the System with the first name Anders
        System.out.println("\nAll student named Anders:");
        for (Student stud : facade.getStudentsByFirstname("Anders")) {
            printingStudent(stud);
        }

        // Insert a new Student into the system
        System.out.println("\nInsert a new Student into the system:");
        Student s1 = new Student();
        s1.setFirstname("Hans");
        s1.setLastname("Hansen");
        printingStudent(facade.addStudent(s1));

        // Assign a new student to a semester (given the student-id and semester-id)
        System.out.println("\nAssign a new student to a semester (given the "
                + "student-id and semester-id):");
        s1 = facade.addStudentToSemester(s1.getId(), 3);    //#3 3. sem b
        printingStudent(s1);
        System.out.println("Semester: " + s1.getSemester().getDescription());

        // Had the student above already been assigned to a semester he would
        // suddenly be a member of two semesters, but still only have one reference 
        // back to the newest assigned semester. Fix this problem, preferably
        // without losing historical information.
        System.out.println("\nFix \"more semesters per student\"-problem, "
                + "preferably without losing historical information:");
        System.out.println("Assigning above student to another semester ");
        s1 = facade.addStudentToSemester(s1.getId(), 2);    // assigning again. Will
        // be stopped by setSemester
        // in Student entity

        // Find (using JPQL) all Students in the system with the last name And
        System.out.println("\nFind (using JPQL) all Students in the system with "
                + "the last name And:");
        for (Student stud : facade.getStudentsByLastname("And")) {
            printingStudent(stud);
        }

        // Find (using JPQL) the total amount of all students
        System.out.println("\nFind (using JPQL) the total amount of all students:");
        System.out.println("Amount of students: " + facade.studentCount());

        // Find (using JPQL)  the total number of students, for a semester given
        // the semester name as a parameter.
        System.out.println("\nFind (using JPQL)  the total number of students, "
                + "for a semester given\n" + "the semester name as a parameter:");
        String semester = s1.getSemester().getName();
        System.out.println("Students on semester " + semester
                + ": " + facade.findStudentsPerSemester(semester));

        // Find (using JPQL) the total number of students in all semesters.
        System.out.println("\nFind (using JPQL) the total number of students in"
                + " all semesters:");
        System.out.println("Amount of students on semester:" + facade.findAllStudentsPerSemester());

        // Find (using JPQL) the teacher(s) who teaches on most semesters.
        System.out.println("\nFind (using JPQL) the teacher(s) who teaches on "
                + "most semesters:");
        System.out.println("Teacher: "
                + facade.findTeacherWithMostSemesters().getFirstname() + " "
                + facade.findTeacherWithMostSemesters().getLastname());
        
        // 11. Often (as in almost always) we don’t want a result that matches 
        // an Entity class, but a result that matches a specific customer
        // requirement for a specific request.
        System.out.println("Printing studentinfo on all students with a semester:");
        System.out.println(facade.getStudentsInfo());
        
        // 12. Create a method, similar to the one above, but which returns a 
        // single StudentInfo, given a students id as sketched below:
        System.out.println("\n\nPrinting single studentinfo from student 4");
        System.out.println(facade.getStudentInfo(4));
        
    }
}
