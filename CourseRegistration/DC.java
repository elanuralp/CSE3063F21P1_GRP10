package CourseRegistration;
import java.util.ArrayList;

public class DC extends Course {
    private int quota;

    public DC(String getName, String getCode, ArrayList<Course> getPrerequisite, int getSemester,
              int getCredit, int getNumberOfStudents, int getQuota){
        super(getName,getCode, getPrerequisite, getSemester, getCredit, "DC", getNumberOfStudents);
        setQuota(getQuota);


    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }
}