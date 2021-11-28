import java.util.ArrayList;

public class CourseSection {
    private int quota;
    private Lecturer sectionLecturer;
    private Course course;
    private int sectionNumber;
    private String courseHours;
    private ArrayList<CourseSectionRegister> registeredStudents= new ArrayList<>();
    CourseSection(Course course,int sectionNumber,int quota,Lecturer sectionLecturer,String courseHours){
        this.course = course;
        this.sectionNumber=sectionNumber;
        this.quota=quota;
        this.sectionLecturer=sectionLecturer;
        this.courseHours=courseHours;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public String getCourseHours() {
        return courseHours;
    }

    public void setCourseHours(String courseHours) {
        this.courseHours = courseHours;
    }


    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public Lecturer getSectionLecturer() {
        return sectionLecturer;
    }

    public void setSectionLecturer(Lecturer sectionLecturer) {
        this.sectionLecturer = sectionLecturer;
    }

    public Course getCourse() {
        return course;
    }
    public void requestToRegister(Student student){
        CourseSectionRegister courseSectionRegister = new CourseSectionRegister(this,"register",student);

    }
    public boolean isQuotaAvailable(){
        if(getRegisteredStudents().size()<getQuota()){
            return true;
        }
        return false;
    }
    public void addToRegistrationList(CourseSectionRegister register){
        ArrayList <CourseSectionRegister> newList = this.getRegisteredStudents();
        newList.add(register);
        this.setRegisteredStudents(newList);
    }

    public ArrayList<CourseSectionRegister> getRegisteredStudents() {
        return registeredStudents;
    }

    public void setRegisteredStudents(ArrayList<CourseSectionRegister> registeredStudents) {
        this.registeredStudents = registeredStudents;
    }
}
