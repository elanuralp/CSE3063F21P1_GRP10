import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class UniversitySystem {
    private int academicCurrentYear;
    private String currentSemesterType="FALL";
    private int studentNumberToBeAdded;
    private String willBeSimulatedSemesterType;

    public ArrayList<Student> getAllStudentList() {
        return allStudentList;
    }

    private ArrayList<Student> allStudentList = new ArrayList<>();
    private  ArrayList<Semester> semesterList = new ArrayList<>();
    private ArrayList<CourseSection> courseSectionList = new ArrayList<>();
    private SemesterStatistic currentSemesterStatistic= new SemesterStatistic();
    private ArrayList<Lecturer> lecturerList = new ArrayList<Lecturer>();
    private ArrayList<Advisor> advisorList = new ArrayList<Advisor>();
    public void  initializeUniversityCourseSelectionSystem(){
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("input.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray inputList = (JSONArray) obj;
            inputList.forEach( emp -> initializeFromJson( (JSONObject) emp ));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        startSimulation();
        allStudentList.forEach((n) -> n.endOfFile());
    }

    public void startSimulation(){
        if(willBeSimulatedSemesterType.contains("FALL")) {
            simulateSemester(7);
        }else{
            simulateSemester(8);

        }
        System.out.println("Simulation completed succesfully");
    }

    public void simulateSemester(int count){
        for(int i=0;i<count;i++){
            ArrayList<CourseSectionRegister> empty=new ArrayList<>();
            currentSemesterStatistic.setDeniedCourseSectionRegisterList(empty);
            if(currentSemesterType=="FALL") {
                createRandomStudents(studentNumberToBeAdded);
            }
            for(int j=0;j<allStudentList.size();j++){
                allStudentList.get(j).startRegistration(semesterList,currentSemesterStatistic);

            }

            completeSemester();
        }
    }
    public void createRandomStudents(int numOfStudentsToBeAdded) {
        int index = (int)(Math.random() * advisorList.size());
        for (int i = 0;i<numOfStudentsToBeAdded;i++){
            ArrayList<CourseGrade> empty =new ArrayList<>();
            Student student = new Student(new Identity(allStudentList.size()), academicCurrentYear,advisorList.get(index), new Transcript(empty,0,0), new Schedule(new ArrayList<CourseSectionRegister>(),false));
            allStudentList.add(student);
            try {
                FileWriter myWriter = new FileWriter(student.getId().getID()+".json");
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }


        }
    }

    public void completeSemester(){
        for(int i =0;i<allStudentList.size();i++){
            allStudentList.get(i).finishSemester();
        }
        if(currentSemesterType=="FALL"){
            currentSemesterType="SPRING";
        }else{
            currentSemesterType="FALL";
            academicCurrentYear++;
        }
        currentSemesterStatistic.calculateSemesterStatistics();
        currentSemesterStatistic.writeToJson();


        for(int i=0;i<semesterList.size();i++){
            for(int j=0;j<semesterList.get(i).getCourseList().size();j++){
                for(int k=0;k<semesterList.get(i).getCourseList().get(j).getCourseSection().size();k++){
                    ArrayList<CourseSectionRegister> empty =new ArrayList<>();
                    semesterList.get(i).getCourseList().get(j).getCourseSection().get(k).setRegisteredStudents(empty);
                }
            }
        }
    }
    public void initializeFromJson(JSONObject json)
    {
        JSONObject settingsObject = (JSONObject) json.get("Settings");
        settingsObject.keySet().forEach(keySettings -> {
            studentNumberToBeAdded = Integer.parseInt((String) settingsObject.get("numberOfStudentsToBeAdded"));
            willBeSimulatedSemesterType = (String) settingsObject.get("willBeSimulatedSemesterType");

        });
        JSONObject nteObject = (JSONObject) json.get("NTE");
        ArrayList<CourseSection> nteList = new ArrayList<CourseSection>();
        nteObject.keySet().forEach(keyNTE -> {
            JSONObject jsonNTE = (JSONObject) nteObject.get(keyNTE);
            Lecturer lecturer = new Lecturer(0,(String) jsonNTE.get("lecturer"),"");
            CourseSection courseSection = new CourseSection(null,0,30,lecturer,(String) jsonNTE.get("schedule"));
            nteList.add(courseSection);
        });
        JSONObject fteObject = (JSONObject) json.get("FTE");
        ArrayList<CourseSection> fteList = new ArrayList<CourseSection>();
        fteObject.keySet().forEach(keyFTE -> {
            JSONObject jsonFTE = (JSONObject) fteObject.get(keyFTE);
            Lecturer lecturer = new Lecturer(0,(String) jsonFTE.get("lecturer"),"");
            CourseSection courseSection = new CourseSection(null,0,30,lecturer,(String) jsonFTE.get("schedule"));
            fteList.add(courseSection);
        });
        JSONObject teObject = (JSONObject) json.get("TE");
        ArrayList<CourseSection> teList = new ArrayList<CourseSection>();
        teObject.keySet().forEach(keyTE -> {
            JSONObject jsonTE = (JSONObject) teObject.get(keyTE);
            Lecturer lecturer = new Lecturer(0,(String) jsonTE.get("lecturer"),"");
            CourseSection courseSection = new CourseSection(null,0,30,lecturer,(String) jsonTE.get("schedule"));
            teList.add(courseSection);
        });
        for(int k=0;k<8;k++) {
            ArrayList<Course> courseList = new ArrayList<>();
            Semester semester = new Semester(courseList,k+1);
            semesterList.add(semester);
            JSONObject inputObject = (JSONObject) json.get("Courses"+(k+1));
            inputObject.keySet().forEach(keyStr -> {
                JSONObject json2 = (JSONObject) inputObject.get(keyStr);
                JSONObject section = (JSONObject) json2.get("sections");
                ArrayList<Course> prelist = new ArrayList<Course>();
                JSONArray prerequisite = (JSONArray) json2.get("prerequisite");
                Iterator<String> iterator = prerequisite.iterator();
                while (iterator.hasNext()) {
                    String pre = iterator.next();
                    for (int i = 0; i < semesterList.size(); i++) {
                        for (int j = 0; j < semesterList.get(i).getCourseList().size(); j++) {
                            if (semesterList.get(i).getCourseList().get(j).getCode().equals(pre)) {
                                prelist.add(semesterList.get(i).getCourseList().get(j));
                            }
                        }
                    }
                }
                ArrayList<CourseSection> sectionlist = new ArrayList<CourseSection>();
                Course course = new Course((String) json2.get("name"), (String) keyStr, null, prelist, Integer.parseInt((String) json2.get("semester")), Integer.parseInt((String) json2.get("credit")), (String) json2.get("type"));
                section.keySet().forEach(keylist -> {
                    JSONObject json3 = (JSONObject) section.get(keylist);
                    int lecturerCheck = 0;
                    for (int i = 0; i < lecturerList.size(); i++) {
                        if ((lecturerList.get(i).getName().contains((String) json3.get("lecturer")))) {
                            lecturerCheck++;
                            ArrayList<Course> coursetoadd = new ArrayList<>();
                            coursetoadd.add(course);
                            CourseSection sections = new CourseSection(coursetoadd, Integer.parseInt((String) keylist), Integer.parseInt((String) json2.get("quota")), lecturerList.get(i),(String) json3.get("schedule"));
                            sectionlist.add(sections);
                        }
                    }
                    if (lecturerCheck == 0) {
                        Lecturer lecturer = new Lecturer(0, (String) json3.get("lecturer"), "");
                        if (lecturerList.size() % 5 == 0) {
                            Advisor advisor = new Advisor(0, (String) json3.get("lecturer"), "");
                            advisorList.add(advisor);
                        }
                        lecturerList.add(lecturer);
                        ArrayList<Course> coursetoadd = new ArrayList<>();
                        coursetoadd.add(course);
                        CourseSection sections = new CourseSection(coursetoadd, Integer.parseInt((String) keylist), Integer.parseInt((String) json2.get("quota")), lecturer,(String) json3.get("schedule"));
                        sectionlist.add(sections);
                    }
                });
                course.setCourseSection(sectionlist);
                if(course.getCode().contains("NTE")){
                    ArrayList<CourseSection> nteList2 = new ArrayList<>(nteList);
                    for(int l=0;l<nteList2.size();l++){
                        if(nteList2.get(l).getCourse()==null){
                            ArrayList<Course> empty = new ArrayList<>();
                            nteList2.get(l).setCourse(empty);
                        }
                        nteList2.get(l).getCourse().add(course);
                    }
                    course.setCourseSection(nteList2);
                }
                else if(course.getCode().contains("FTE")){
                   ArrayList<CourseSection> fteList2 = new ArrayList<>(fteList);
                    for(int l=0;l<fteList2.size();l++){
                        if(fteList2.get(l).getCourse()==null){
                            ArrayList<Course> empty = new ArrayList<>();
                            fteList2.get(l).setCourse(empty);
                        }
                        fteList2.get(l).getCourse().add(course);
                    }
                    course.setCourseSection(fteList2);
                }
                else if(course.getCode().contains("TE")){
                    ArrayList<CourseSection> teList2 = new ArrayList<>(teList);
                    for(int l=0;l<teList2.size();l++){
                        if(teList2.get(l).getCourse()==null){
                            ArrayList<Course> empty = new ArrayList<>();
                            teList2.get(l).setCourse(empty);
                        }
                        teList2.get(l).getCourse().add(course);
                    }
                    course.setCourseSection(teList2);
                }
                for(int i =0;i<semesterList.size();i++){
                    if(semesterList.get(i).getSemesterID()==course.getSemester()){
                        semesterList.get(i).getCourseList().add(course);
                    }
                }
            });
        }
    }
}
