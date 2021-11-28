import java.util.ArrayList;


public class Course {
    private String name;  //name of the course
    private String code; //course code
    private ArrayList<CourseSection>courseSection=new ArrayList<CourseSection>();
    private ArrayList<Course>prerequisite=new ArrayList<Course>(); //list of prerequisites of the course
    private int semester; // to define semester of the course
    private int credit;  //credit of course
    private String type; // TE NTE UE FC DC


    public Course(String courseName, String courseCode,ArrayList<CourseSection> courseSection, ArrayList<Course> coursePrerequisite, int courseSemester,
                  int courseCredit,String courseType){
        setName(courseName);
        setCode(courseCode);
        setPrerequisite(coursePrerequisite);
        setSemester(courseSemester);
        setCredit(courseCredit);
        setType(courseType);
        setCourseSection(courseSection);

    }

    public ArrayList<CourseSection> getCourseSection() {
        return courseSection;
    }

    public void setCourseSection(ArrayList<CourseSection> courseSection) {
        this.courseSection = courseSection;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<Course> getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(ArrayList<Course> prerequisite) {
        this.prerequisite = prerequisite;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void checkPrerequisite(Course course){
        System.out.println( course.prerequisite);
    }

}


