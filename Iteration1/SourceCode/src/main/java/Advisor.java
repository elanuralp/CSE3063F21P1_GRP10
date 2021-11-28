import java.util.ArrayList;

public class Advisor extends Lecturer {
    private ArrayList<Student> studentList = new ArrayList<>();

    public Advisor(int id,String name,String surname){
        super(id,name,surname);
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }


    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }
    public static boolean CheckStudentCourseSelection(Transcript transcript){
        return true;

    }
    public void checkStudentSchedule(Student student) {

        Schedule studentSchedule = student.getSchedule();

        Transcript studentTranscript = student.getTranscript();
        ArrayList<CourseSectionRegister> collidingRegisters = studentSchedule.checkCourseSectionCollision();
        if (collidingRegisters.size() >= 1) {
            for (CourseSectionRegister register : collidingRegisters) {
                studentSchedule.removeFromSchedule(register);

                //
                // YAZ KIZIM GEREĞİNİ DÜŞÜNDÜK "Çakışan x dersini alamadı"
                //
            }
        }
        ArrayList<CourseSectionRegister> studentRegisterList = studentSchedule.getCourseSectionRegisterList();
        for (CourseSectionRegister register : studentRegisterList) {
            if (!register.getCourseSection().isQuotaAvailable()) {
                studentSchedule.removeFromSchedule(register);
                //
                // YAZ YAZ YAZ BIR JSON'A YAZ
            }
        }
        int studentCompletedCredit = studentTranscript.getCompletedCredit();
        for (CourseSectionRegister register : studentRegisterList) {
            if (studentCompletedCredit < 155) {
                CourseSection registeredCourseSection = register.getCourseSection();
                Course courseOfSection = registeredCourseSection.getCourse();
                int semesterInfo = courseOfSection.getSemester();
                if (semesterInfo > 7) {
                    studentSchedule.removeFromSchedule(register);
                    //
                    // "KREDISI <155 OLDUĞU İÇİN X DERSİNİ ALAMADI.
                    //
                }
            } else if (studentCompletedCredit > 155 && studentCompletedCredit < 165) {
                CourseSection registeredCourseSection = register.getCourseSection();
                Course courseOfSection = registeredCourseSection.getCourse();
                String courseName = courseOfSection.getName();
                String engineeringProject1 = "CSE 4297";
                if ((courseName.compareTo(engineeringProject1)) == 0) {
                    studentSchedule.removeFromSchedule(register);
                    //
                    // JSONA= "<165 KREDI BITIRME ALAMAZ" YAZ
                    //
                }
            }
        }
        studentSchedule.approveSchedule();

    }
}
