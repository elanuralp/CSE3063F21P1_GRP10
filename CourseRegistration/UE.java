package CourseRegistration;
import java.util.ArrayList;

public class UE extends Course {
    private int quota;

    public UE(String getName, String getCode, ArrayList<Course> getPrerequisite, int getSemester,
              int getCredit, int getNumberOfStudents, int getQuota){
        super(getName,getCode, getPrerequisite, getSemester, getCredit, "UE", getNumberOfStudents);
        setQuota(getQuota);


    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }
}