public class CourseSectionRegister {
        CourseSection courseSection;
        String status;
        Student student;
        CourseSectionRegister(CourseSection courseSection,String status,Student student){
            this.courseSection=courseSection;
            this.status=status;
            this.student=student;
            Schedule StudentSchedule=this.student.getSchedule();
            StudentSchedule.addToSchedule(this);
        }


    public CourseSection getCourseSection() {
        return courseSection;
    }

    public void setCourseSection(CourseSection courseSection) {
        this.courseSection = courseSection;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
