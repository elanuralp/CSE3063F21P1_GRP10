package CourseRegistration;
import java.util.ArrayList;
public class Transcript {
    private ArrayList<Course>activeCourses=new ArrayList<Course>();
    private ArrayList<Course>takenCourses=new ArrayList<Course>();

    public ArrayList<Course> getActiveCourses() {
        return activeCourses;
    }

    public void setActiveCourses(ArrayList<Course> activeCourses) {
        this.activeCourses = activeCourses;
    }

    public ArrayList<Course> getTakenCourses() {
        return takenCourses;
    }

    public void setTakenCourses(ArrayList<Course> takenCourses) {
        this.takenCourses = takenCourses;
    }



}
