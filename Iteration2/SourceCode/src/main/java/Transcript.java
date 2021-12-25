import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Transcript {
    private ArrayList<CourseGrade> gradeBook = new ArrayList<CourseGrade>();
    private int completedCredit;
    private double gpa;

    public Transcript(ArrayList<CourseGrade> gradeBook,int completedCredit, double gpa) {
        this.setGradeBook(gradeBook);
        this.setCompletedCredit(completedCredit);
        this.setGpa(gpa);
    }



    public ArrayList<Course> getFailedCourses(){
        ArrayList<Course> failedCourses=new ArrayList<Course>();
        for(CourseGrade courseGrade: getGradeBook()){
            if(courseGrade.getLetterGrade().contains("FF") || (courseGrade.getLetterGrade().contains("FD"))){
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

    public String addCoursesToTranscript(ArrayList<Course> courseList,Map<String,String> courseMap) {

        String[] grades = {"AA","BA" ,"BB","CB", "CC","DC","DD","FD","FF"};
        ArrayList<CourseGrade> semesterGrades= new ArrayList<>();
        for (Course courseOfFor : courseList) {
            int randomNumber = (int)(Math.random()*9);
            CourseGrade cGrade = new CourseGrade(courseOfFor, grades[randomNumber]);
            this.gradeBook.add(cGrade);
            semesterGrades.add(cGrade);
            courseMap.put(courseOfFor.getName(),grades[randomNumber]);
        }
         DecimalFormat dt= new DecimalFormat("0.00");
         return dt.format(calculateSemesterGPA(semesterGrades));
    }
    public String calculateTotalGPA(){
        double gradeCoefficient=0;
        double courseCredit=0;
        double totalWeightOfCourses=0;
        double totalCreditOfCourses=0;
        double GPA=0;
        for(CourseGrade courseGrade: gradeBook){
            courseCredit=courseGrade.getCourse().getCredit();
            totalCreditOfCourses+=courseCredit;
            if(courseGrade.getLetterGrade().equals("FF")){ gradeCoefficient=0; }
            else if(courseGrade.getLetterGrade().equals("FD")){ gradeCoefficient=0.5; }
            else if(courseGrade.getLetterGrade().equals("DD")){ gradeCoefficient=1; }
            else if(courseGrade.getLetterGrade().equals("DC")){ gradeCoefficient=1.5; }
            else if(courseGrade.getLetterGrade().equals("CC")){ gradeCoefficient=2.0; }
            else if(courseGrade.getLetterGrade().equals("CB")){ gradeCoefficient=2.5; }
            else if(courseGrade.getLetterGrade().equals("BB")){ gradeCoefficient=3.0; }
            else if(courseGrade.getLetterGrade().equals("BA")){ gradeCoefficient=3.5; }
            else if(courseGrade.getLetterGrade().equals("AA")){ gradeCoefficient=4.0; }
            totalWeightOfCourses+= gradeCoefficient*courseCredit;
        }
        GPA=totalWeightOfCourses/totalCreditOfCourses;
        this.setGpa(GPA);
        DecimalFormat dt= new DecimalFormat("0.00");
        return dt.format(GPA);
    }

    public double calculateSemesterGPA(ArrayList<CourseGrade> courseGradeList){
        double gradeCoefficient=0;
        double courseCredit=0;
        double totalWeightOfSemesterCourses=0;
        double totalCreditOfSemesterCourses=0;
        double semesterGPA=0;
        for(CourseGrade courseGrade: courseGradeList){
            courseCredit=courseGrade.getCourse().getCredit();
            totalCreditOfSemesterCourses+=courseCredit;
            String courseLetterGrade=courseGrade.getLetterGrade();
            if(courseLetterGrade.equals("FF")){ gradeCoefficient=0; }
            else if(courseLetterGrade.equals("FD")){ gradeCoefficient=0.5; }
            else if(courseLetterGrade.equals("DD")){ gradeCoefficient=1; }
            else if(courseLetterGrade.equals("DC")){ gradeCoefficient=1.5; }
            else if(courseLetterGrade.equals("CC")){ gradeCoefficient=2.0; }
            else if(courseLetterGrade.equals("CB")){ gradeCoefficient=2.5; }
            else if(courseLetterGrade.equals("BB")){ gradeCoefficient=3.0; }
            else if(courseLetterGrade.equals("BA")){ gradeCoefficient=3.5; }
            else if(courseLetterGrade.equals("AA")){ gradeCoefficient=4.0; }
            totalWeightOfSemesterCourses+=gradeCoefficient*courseCredit;
        }
        semesterGPA=totalWeightOfSemesterCourses/totalCreditOfSemesterCourses;
        return semesterGPA;
    }
}

