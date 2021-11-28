import java.util.ArrayList;
import java.util.Random;

public class Transcript {
    private ArrayList<CourseGrade> gradeBook = new ArrayList<CourseGrade>();
    private int completedCredit;
    private double gpa;

    public Transcript(ArrayList<CourseGrade> gradeBook,int completedCredit, double gpa) {
        this.setGradeBook(gradeBook);
        this.setCompletedCredit(completedCredit);
        this.setGpa(gpa);
    }



    /*public void giveNote(Course course) {
        Random random = new Random();
        int random_int = random.nextInt(10);
        notes.add(course.getCode() + "-" + random_int + "_" + course.getCredit());
        activeCourses.add(course);
        //System.out.println(course.getCode()+ "dersi başarıyla alınmıştır");
    }*/

    /*public void skipSemester() {
        ArrayList<Course> queueCourses = new ArrayList<Course>();
        for (int i = 0; i < activeCourses.size(); i++) {
            if (!getStudentNote(activeCourses.get(i)).contains("0")) {
                takenCourses.add(activeCourses.get(i));
                //System.out.println(activeCourses.get(i).getCode()+" dersinden başarıyla geçilmiştir");
            }else{
                System.out.println(getStudentNote(activeCourses.get(i)));
            }
            queueCourses.add(activeCourses.get(i));
            //activeCourses.remove(activeCourses.get(i));
        }
        notes.add("//");
        for (int i =0;i<queueCourses.size();i++){
            activeCourses.remove(queueCourses.get(i));
        }
    }*/

    /*public String getStudentNote(Course course) {
        for (int i = notes.size()-1; i >0; i--) {
            if (notes.get(i).contains(course.getCode())) {
                String[] tokens = notes.get(i).split("-");
                int j = 0;
                for (String t : tokens) {
                    if (j == 0) {
                        j++;
                        continue;
                    }
                    String[] grades = t.split("_");
                    for(String data: grades){
                        return data;
                    }
                    //return t;
                }
            }
        }return "?";
    }*/
    public ArrayList<Course> getFailedCourses(){
        ArrayList<Course> failedCourses=new ArrayList<Course>();
        for(CourseGrade courseGrade: getGradeBook()){
            if(courseGrade.getLetterGrade().contains("FF") && (courseGrade.getLetterGrade().contains("FD"))){
                failedCourses.add(courseGrade.getCourse());
            }
        }
        return failedCourses;
    }
    public ArrayList<Course> getCompletedCourses(){
        ArrayList<Course> completedCourses=new ArrayList<Course>();
        for(CourseGrade courseGrade: getGradeBook()){
            if(!(courseGrade.getLetterGrade().contains("FF")) && !(courseGrade.getLetterGrade().contains("FD"))){
                completedCourses.add(courseGrade.getCourse());
            }

        }
        return completedCourses;
    }

    public ArrayList<CourseGrade> getGradeBook() {
        return gradeBook;
    }

    public void setGradeBook(ArrayList<CourseGrade> gradeBook) {
        this.gradeBook = gradeBook;
    }

    public int getCompletedCredit() {
        return completedCredit;
    }

    public void setCompletedCredit(int completedCredit) {
        this.completedCredit = completedCredit;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public void addCoursesToTranscript(ArrayList<Course> courseList) {
        System.out.println("dsaasdşşşşşşşşşşşşşşşşşşşş");
        String[] grades = {"AA","BA" ,"BB","CB", "CC","DC","DD","FD","FF"};

        for (Course courseOfFor : courseList) {
            int randomNumber = (int)(Math.random()*9);
            CourseGrade cGrade = new CourseGrade(courseOfFor, grades[randomNumber]);
            this.gradeBook.add(cGrade);

        }

    }
}

