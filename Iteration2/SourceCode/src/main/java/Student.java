import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Student {
    private Identity id;
    private int academicYear=0;
    private Advisor advisor;
    private Transcript transcript;
    private Schedule schedule;
    private int academicSemester=0;


    public Student(Identity id, int academicYear,Advisor advisor,Transcript transcript,Schedule schedule) {
        this.id = id;
        this.transcript = transcript;
        this.academicYear = academicYear;
        this.advisor=advisor;
        this.setSchedule(schedule);
    }

    public Identity getId() {
        return id;
    }

    public void setId(Identity id) {
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




    public void startRegistration(ArrayList<Semester> allSemesterList,SemesterStatistic semesterStatistic) {
        ArrayList<Course> failedCourses = this.getTranscript().getFailedCourses();
        ArrayList<Course> completedCourses = this.getTranscript().getCompletedCourses();
        ArrayList<String> preqCourses = new ArrayList<>();
        // Registering to failedCourses first.
        for (Course course : failedCourses) {
            ArrayList<CourseSection> sectionsOfCourse = course.getCourseSection();
            int CourseSectionCount = sectionsOfCourse.size();
            int sectionNumber = (int) (Math.random() * CourseSectionCount)+1;

        } // Registering to currentSemesterCourses

        for (Semester semester : allSemesterList){
            ArrayList<Course> currentSemesterCourses = semester.getCourseList();
            for (Course course : currentSemesterCourses) {
                if(course.getSemester()<=this.getAcademicSemester()+1 &&  !completedCourses.contains(course)) {
                    ArrayList<CourseSection> sectionsOfCourse = course.getCourseSection();
                    int CourseSectionCount = sectionsOfCourse.size();
                    int sectionNumber = (int) (Math.random() * CourseSectionCount) + 1; // this val is to register randomly

                    ArrayList<Course> prerequisites = course.getPrerequisite();
                    boolean hasPrerequisite = false;
                    for (Course pCourse : prerequisites) {
                        if (completedCourses.contains(pCourse) ) {
                            hasPrerequisite = true;
                        } else {
                            hasPrerequisite = false;
                            break;
                        }
                    }
                    if(prerequisites.size()==0) {
                        hasPrerequisite=true;
                    }

                    if (hasPrerequisite ) {
                        sectionsOfCourse.get(sectionNumber - 1).requestToRegister(this);

                    } else {
                        preqCourses.add("The system didn't allow "+sectionsOfCourse.get(sectionNumber-1).getCourse().get(0).getCode()+"  because student failed prereq.");
                        CourseSectionRegister register = new CourseSectionRegister(sectionsOfCourse.get(sectionNumber-1),"PREREQUISITE",this);
                        semesterStatistic.addtoDeniedCourseSectionRegisterList(register);
                    }
                }
            }

        }
        this.advisor.checkStudentSchedule(this,semesterStatistic,preqCourses);
    }
    public void writeToTheJSON(Map<String,String> courseMap){
        String fileName = getId().getID()+".json";JSONObject jsonObject = new JSONObject();
        JSONArray courses = new JSONArray();
        for (Map.Entry<String,String> entry : courseMap.entrySet()){
            courses.add("Course: " + entry.getKey() +", Grade:"+ entry.getValue());
        }

        //logs.add("Course: " + name +", Grade:"+ grade );
        jsonObject.put("Semester " + (this.academicSemester+1) + ": ",courses );
        if(this.academicSemester+1==1){
            try {
                FileWriter file = new FileWriter(fileName,true);
                file.write("[" +jsonObject.toJSONString()+"\n,");
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{

            try {
                FileWriter file = new FileWriter(fileName,true);
                file.write(jsonObject.toJSONString()+"\n,");
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }}
    }
    public void writeLogs(ArrayList<String> logs){
        String fileName = getId().getID()+".json";JSONObject jsonObject = new JSONObject();
        JSONArray logOfCourses = new JSONArray();
        logs.forEach((n) -> logOfCourses.add(n));
        jsonObject.put("Logs: ",logOfCourses);
        try {
            FileWriter file = new FileWriter(fileName,true);
            file.write(jsonObject.toJSONString()+"\n,");
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeGPA(String logs){
        String fileName = getId().getID()+".json";JSONObject jsonObject = new JSONObject();
        JSONArray logOfCourses = new JSONArray();
        logOfCourses.add(logs);
        jsonObject.put("GPA: ",logOfCourses);
        try {
            FileWriter file = new FileWriter(fileName,true);
            file.write(jsonObject.toJSONString()+"\n,");
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void endOfFile(){
        String fileName = getId().getID()+".json";JSONObject jsonObject = new JSONObject();
        try {
            File file = new File(fileName);
            FileChannel fileChannel = new FileOutputStream(file, true).getChannel();
            fileChannel.truncate(fileChannel.size() - 1); //Removes last character
            fileChannel.close();
        }
        catch (FileNotFoundException ex) {

        }
        catch (IOException ex) {

        }
        try {
            FileWriter file = new FileWriter(fileName,true);
            file.write("]");
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void finishSemester(){
        ArrayList<CourseSectionRegister> registerList = this.schedule.getCourseSectionRegisterList();
        ArrayList<Course> courses= new ArrayList<Course>();
        int ntecount=0;
        int ftecount=0;
        int tecount=0;
        for(int i=0;i<getTranscript().getGradeBook().size();i++){
            if(!(getTranscript().getGradeBook().get(i).getLetterGrade().equals("FF")||getTranscript().getGradeBook().get(i).getLetterGrade().equals("FD"))) {

                if (getTranscript().getGradeBook().get(i).getCourse().getType().equals("NTE")) {
                    ntecount++;
                } else if (getTranscript().getGradeBook().get(i).getCourse().getType().equals("TE")) {
                    tecount++;
                }
            }
        }
        for(CourseSectionRegister register: registerList){
            CourseSection sectionOfReg= register.getCourseSection();
            Course courseOfSection = sectionOfReg.getCourse().get(0);
            if(sectionOfReg.getCourse().get(0).getType().equals("FTE")){
                 courseOfSection = sectionOfReg.getCourse().get(ftecount);
            }
            else if(sectionOfReg.getCourse().get(0).getType().equals("TE")){
                 courseOfSection = sectionOfReg.getCourse().get(tecount);
                 tecount++;
            }
            else if(sectionOfReg.getCourse().get(0).getType().equals("NTE")){
                 courseOfSection = sectionOfReg.getCourse().get(ntecount);
            }

            courses.add(courseOfSection);
        }

        Map<String,String> courseMap = new TreeMap<String,String>();
        String gpaJSON;
        gpaJSON = "GPA: "+ this.transcript.addCoursesToTranscript(courses,courseMap)+",cGPA: "+getTranscript().calculateTotalGPA();

        writeToTheJSON(courseMap);
        writeGPA(gpaJSON);
        courseMap.clear();
        this.schedule.clearSchedule();
        this.incrSemester();
    }


    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void incrSemester(){
        this.setAcademicSemester((this.getAcademicSemester()+1));
    }
}
