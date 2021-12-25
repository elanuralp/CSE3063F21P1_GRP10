import java.util.ArrayList;
import java.util.Random;

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
    public void checkStudentSchedule(Student student,SemesterStatistic semesterStatistic,ArrayList<String> preq) {
        ArrayList<String> logs = preq;

        Schedule studentSchedule = student.getSchedule();
        Transcript studentTranscript = student.getTranscript();
        int tecount=0;
        if(student.getAcademicSemester()%2==0){   //check te count
            for (int i=0;i<studentSchedule.getCourseSectionRegisterList().size();i++){
                if(studentSchedule.getCourseSectionRegisterList().get(i).getCourseSection().getCourse().get(0).getType().equals("TE")){
                    tecount++;
                    if(tecount>1){
                        studentSchedule.removeFromSchedule(studentSchedule.getCourseSectionRegisterList().get(i));
                        logs.add("The advisor didn't approve TE because student already took 2 TE and in FALL semester only 2 TE can be taken");
                    }
                }
            }
        }
        if(student.getAcademicSemester()%2==0){
            for (int i=0;i<studentSchedule.getCourseSectionRegisterList().size();i++){
                if(studentSchedule.getCourseSectionRegisterList().get(i).getCourseSection().getCourse().get(0).getType().equals("FTE")){
                    studentSchedule.removeFromSchedule(studentSchedule.getCourseSectionRegisterList().get(i));
                    logs.add("The advisor didn't approve FTE CSE4062 because students can't take FTE in FALL semester unless they are graduating this semester");
                }
            }
        }
        ArrayList<CourseSectionRegister> collidingRegisters = studentSchedule.checkCourseSectionCollision();
        if (collidingRegisters.size() >= 1) {
            for (CourseSectionRegister register : collidingRegisters) {

                register.setStatus("COLLIDE");
                semesterStatistic.addtoDeniedCourseSectionRegisterList(register);
                studentSchedule.removeFromSchedule(register);
                logs.add("Couldn't register "+register.getCourseSection().getCourse().get(0).getCode() + " due to collide problem.");
            }
        }


        ArrayList<CourseSectionRegister> studentRegisterList = studentSchedule.getCourseSectionRegisterList();
        ArrayList<CourseSectionRegister> studentRegisterListWillBeDeleted= new ArrayList<>();
        for (CourseSectionRegister register : studentRegisterList) {
            if (!register.getCourseSection().isQuotaAvailable()) {
                register.setStatus("QUOTA");
                semesterStatistic.addtoDeniedCourseSectionRegisterList(register);
                studentRegisterListWillBeDeleted.add(register);
                logs.add("Couldn't register "+register.getCourseSection().getCourse().get(0).getCode() + " due to quota problem.");

            }
        }

        int studentCompletedCredit = studentTranscript.getCompletedCredit();

        for (CourseSectionRegister register : studentRegisterList) {
            if (studentCompletedCredit < 155) {

                CourseSection registeredCourseSection = register.getCourseSection();
                Course courseOfSection = registeredCourseSection.getCourse().get(0);
                int semesterInfo = courseOfSection.getSemester();
                if (semesterInfo > 7) {
                    studentRegisterListWillBeDeleted.add(register);
                    logs.add("The advisor didn't approve "+register.getCourseSection().getCourse().get(0).getCode() + " because student completed credits < 155.");
                }
            } else if (studentCompletedCredit > 155 && studentCompletedCredit < 165) {

                CourseSection registeredCourseSection = register.getCourseSection();
                Course courseOfSection = registeredCourseSection.getCourse().get(0);
                String courseName = courseOfSection.getName();
                String engineeringProject1 = "CSE 4297";
                if ((courseName.compareTo(engineeringProject1)) == 0) {
                    studentRegisterListWillBeDeleted.add(register);
                    logs.add("The advisor didn't approve graduation project "+register.getCourseSection().getCourse().get(0).getCode() + " because student completed credits < 165");

                }
            }
        }
        while(student.getSchedule().calculateCreditTaken()>32){
            int size =student.getSchedule().getCourseSectionRegisterList().size();
            logs.add("The advisor didn't approve "+student.getSchedule().getCourseSectionRegisterList().get(size-1).getCourseSection().getCourse().get(0).getCode() + " because student took enough credits.");
            student.getSchedule().getCourseSectionRegisterList().remove(size-1);
        }
        for(CourseSectionRegister register : studentRegisterListWillBeDeleted){
            studentSchedule.removeFromSchedule(register);
        }

        studentSchedule.approveSchedule();

        if(!logs.isEmpty())
            student.writeLogs(logs);

    }
}
