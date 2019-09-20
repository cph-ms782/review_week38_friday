package mappers;

/**
 *
 * @author msi
 */
public class StudentInfo {

    public String fullName;
    public long studentId;
    public String classNameThisSemester;
    public String classDescription;

    public StudentInfo(String firstname, String lastname, long studentId, String classNameThisSemester, String classDescription) {
        this.fullName = firstname + " " + lastname;
        this.studentId = studentId;
        this.classNameThisSemester = classNameThisSemester;
        this.classDescription = classDescription;
    }

    @Override
    public String toString() {
        return "\n\nfullName= " + fullName 
                + "\nstudentId= " + studentId 
                + "\nclassNameThisSemester= " + classNameThisSemester 
                + "\nclassDescription= " + classDescription;
    }

    
    
}
