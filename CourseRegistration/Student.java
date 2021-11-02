package CourseRegistration;

public class Student {
    private String id;
    private Transcript transcript;
    private int academicYear=0;
    private int academicSemester=0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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


    public void selectCourse(Course selected_course){

    }
}
