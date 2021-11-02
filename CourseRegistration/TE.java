package CourseRegistration;
import java.util.ArrayList;

public class TE extends Course {
    private int quota;
    private int neededCredit=155; // needed credit to be taken

    public TE(String getName, String getCode, ArrayList<Course> getPrerequisite, String getYear,
              int getCredit, int getNumberOfStudents, int getQuota){
        super(getName,getCode, getPrerequisite, getYear, getCredit, "TE", getNumberOfStudents);
        setQuota(getQuota);


    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }
}
