import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class SemesterStatistic {
    private ArrayList<CourseSectionRegister> deniedCourseSectionRegisterList = new ArrayList<>();
    Map<String,Integer> courseMap = new TreeMap<String,Integer>();
    Map<String,Integer> courseTypeMap = new TreeMap<String,Integer>();



    public SemesterStatistic(){

    }
    public void addtoDeniedCourseSectionRegisterList(CourseSectionRegister deniedCourseSectionRegister){
        deniedCourseSectionRegisterList.add(deniedCourseSectionRegister);


    }

    public void setDeniedCourseSectionRegisterList(ArrayList<CourseSectionRegister> deniedCourseSectionRegisterList) {
        this.deniedCourseSectionRegisterList = deniedCourseSectionRegisterList;
    }

    public void calculateSemesterStatistics(){
        int i;
        for(i=0;i<deniedCourseSectionRegisterList.size();i++){

            String courseNameAndStatus = deniedCourseSectionRegisterList.get(i).courseSection.getCourse().get(0).getName()+ " DUE TO " + deniedCourseSectionRegisterList.get(i).status;
            String courseType =deniedCourseSectionRegisterList.get(i).courseSection.getCourse().get(0).getType();
            if(courseMap.containsKey(courseNameAndStatus)){

                courseMap.replace(courseNameAndStatus, courseMap.get(courseNameAndStatus) + 1);


            } else {
                courseMap.put(courseNameAndStatus, 1);

            }
            if(courseTypeMap.containsKey(courseType)){

                courseTypeMap.replace(courseType, courseTypeMap.get(courseType) + 1);


            } else {
                courseTypeMap.put(courseType, 1);

            }
        }

    }
    public void writeToJson(){
        JSONObject jsonObject = new JSONObject();
        JSONArray logs = new JSONArray();
        for (Map.Entry<String,Integer> entry : courseMap.entrySet()){
            logs.add(entry.getValue() + " STUDENTS COULDN'T REGISTER FOR "+ entry.getKey() + " PROBLEMS." );
        }

        for (Map.Entry<String,Integer> entry2 : courseTypeMap.entrySet()){
            logs.add(entry2.getValue() + " STUDENTS COULDN'T REGISTER FOR A "+ entry2.getKey() + " THIS SEMESTER." );
        }


        jsonObject.put("Logs", logs);
        try {
            FileWriter file = new FileWriter("DEPARTMENT_OUTPUT_SEMESTER.json");
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





}
