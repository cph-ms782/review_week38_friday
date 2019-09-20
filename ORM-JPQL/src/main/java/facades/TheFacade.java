package facades;

import entity.Semester;
import entity.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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

    public Student addStudentToSemester(Student s, int semesterID) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Semester se = em.find(Semester.class, (long) semesterID);
            s.setSemester(se);
            em.persist(s);
            em.getTransaction().commit();
            return s;
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
        s1 = facade.addStudentToSemester(s1, 3);    //#3 3. sem b
        printingStudent(s1); 
        System.out.println("Semester: " + s1.getSemester().getDescription());

        // Had the student above already been assigned to a semester he would
        // suddenly be a member of two semesters, but still only have one reference 
        // back to the newest assigned semester. Fix this problem, preferably
        // without losing historical information.
        // Find (using JPQL) all Students in the system with the last name And
        System.out.println("\nFind (using JPQL) all Students in the system with "
                + "the last name And:");

        // Find (using JPQL) the total amount of all students
        System.out.println("\nFind (using JPQL) the total amount of all students:");

        // Find (using JPQL)  the total number of students, for a semester given
        // the semester name as a parameter.
        System.out.println("\nFind (using JPQL)  the total number of students, "
                + "for a semester given\n" + "the semester name as a parameter:");

        // Find (using JPQL) the total number of students in all semesters.
        System.out.println("\nFind (using JPQL) the total number of students in"
                + " all semesters:");

        // Find (using JPQL) the teacher(s) who teaches on most semesters.
        System.out.println("\nFind (using JPQL) the teacher(s) who teaches on "
                + "most semesters:");

    }
}
