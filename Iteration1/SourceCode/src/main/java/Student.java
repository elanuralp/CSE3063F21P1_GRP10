import java.util.ArrayList;
import java.util.Collections;

public class Student {
    private Identity id;
    private int academicYear=0;
    private Advisor advisor;
    private Transcript transcript;
    private Schedule schedule;
    private int academicSemester=0;


    public Student(Identity id, int academicYear,Advisor advisor,Transcript transcript,Schedule schedule) {
        this.id = id;
        this.transcript = transcript;
        this.academicYear = academicYear;
        this.advisor=advisor;
        this.setSchedule(schedule);
    }

    public Identity getId() {
        return id;
    }

    public void setId(Identity id) {
        this.id = id;
    }

    public Transcript getTranscript() {
        return transcript;
    }




    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    public int getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(int academicYear) {
        this.academicYear = academicYear;
    }

    public int getAcademicSemester() {
        return academicSemester;
    }

    public void setAcademicSemester(int academicSemester) {
        this.academicSemester = academicSemester;
    }




    public void startRegistration(ArrayList<Semester> allSemesterList) {

        ArrayList<Course> failedCourses = this.getTranscript().getFailedCourses();
        ArrayList<Course> completedCourses = this.getTranscript().getCompletedCourses();


        // Registering to failedCourses first.
        for (Course course : failedCourses) {
            ArrayList<CourseSection> sectionsOfCourse = course.getCourseSection();
            int CourseSectionCount = sectionsOfCourse.size();
            int sectionNumber = (int) (Math.random()) * CourseSectionCount;
            (sectionsOfCourse.get(sectionNumber)).requestToRegister(this);
        } // Registering to currentSemesterCourses
        for (Semester semester : allSemesterList){
            ArrayList<Course> currentSemesterCourses = semester.getCourseList();
            for (Course course : currentSemesterCourses) {
                ArrayList<CourseSection> sectionsOfCourse = course.getCourseSection();
                int CourseSectionCount = sectionsOfCourse.size();
                int sectionNumber = (int) (Math.random()) * CourseSectionCount; // this val is to register randomly
                ArrayList<Course> prerequisites = course.getPrerequisite();
                boolean hasPrerequisite = false;
                for (Course pCourse : prerequisites) {
                    if (completedCourses.contains(pCourse)) {
                        hasPrerequisite = true;
                    } else {
                        hasPrerequisite = false;
                        break;
                    }
                }
                if (hasPrerequisite) {
                    sectionsOfCourse.get(sectionNumber).requestToRegister(this);
                } else {
                    //
                    // JSON'A YAZ YAZ YAZ BIR KE-NA-RA YAZ
                    // " The system didnt allow student to regsiter for course x ."
                    //
                }
            }

        }
        this.advisor.checkStudentSchedule(this);
    }

    public void finishSemester(){

        ArrayList<CourseSectionRegister> registerList = this.schedule.getCourseSectionRegisterList();
        ArrayList<Course> courses= new ArrayList<Course>();
        for(CourseSectionRegister register: registerList){
            CourseSection sectionOfReg= register.getCourseSection();
            Course courseOfSection = sectionOfReg.getCourse();
            courses.add(courseOfSection);
        }

        this.transcript.addCoursesToTranscript(courses); // Transcript her dersi gradeleyip GradeBook'a ekleyecek
       // this.schedule.clearSchedule();
        this.incrSemester();
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void incrSemester(){
        this.setAcademicSemester((this.getAcademicYear()+1));
    }
}
