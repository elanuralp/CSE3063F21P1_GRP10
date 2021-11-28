public class CourseGrade {
    private Course course;
    private String letterGrade;
    public CourseGrade(Course course,String letterGrade){
        this.setCourse(course);
        this.setLetterGrade(letterGrade);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }
}