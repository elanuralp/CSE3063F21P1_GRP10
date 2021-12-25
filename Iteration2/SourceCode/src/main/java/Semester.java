import java.util.ArrayList;

public class Semester {
    private ArrayList<Course> courseList = new ArrayList();
    int semesterID;
    public Semester(ArrayList courseList,int semesterID){//
        this.semesterID = semesterID;
        this.courseList = courseList;
    }

    public int getSemesterID() {
        return semesterID;
    }//

    public void setSemesterID(int semesterID) {
        this.semesterID = semesterID;
    }//

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }
}