import java.util.ArrayList;

public class Schedule {
    private ArrayList<CourseSectionRegister> courseSectionRegisterList = new ArrayList<>();
    boolean isApproved;

    public Schedule(ArrayList<CourseSectionRegister> courseSectionRegisterList, boolean isApproved) {//+
        this.courseSectionRegisterList = courseSectionRegisterList;
        this.isApproved = isApproved;
    }

    public void addToSchedule(CourseSectionRegister courseSectionRegister){//
        ArrayList<CourseSectionRegister> courseSectionRegisterListToAdd=getCourseSectionRegisterList();
        courseSectionRegisterListToAdd.add(courseSectionRegister);
        
        setCourseSectionRegisterList(courseSectionRegisterListToAdd);
    }
    public void removeFromSchedule(CourseSectionRegister courseSectionRegister){//
        //ArrayList<CourseSectionRegister> courseSectionRegisterListToRemove=getCourseSectionRegisterList();
        //courseSectionRegisterListToRemove.remove(courseSectionRegister);
        //setCourseSectionRegisterList(courseSectionRegisterListToRemove);

        this.courseSectionRegisterList.remove(courseSectionRegister);
    }

    public boolean isApproved() {
        return isApproved;
    }//

    public void setApproved(boolean approved) {
        isApproved = approved;
    }//


    public ArrayList<CourseSectionRegister> checkCourseSectionCollision() {//
        ArrayList<CourseSectionRegister> ListOfCollision = new ArrayList<CourseSectionRegister>();
        CourseSectionRegister courseObject = null;

        ArrayList<String> FullDate = new ArrayList<String>();
        ArrayList<String> DayandFirstHourList = new ArrayList<String>();

        for (int i = 0; i < courseSectionRegisterList.size(); i++) {
            String[] splittedFromComma = courseSectionRegisterList.get(i).getCourseSection().getCourseHours().split(", ");
            for (String FullCourseDate : splittedFromComma) {
                FullDate.add(FullCourseDate);
            }
        }
        for (int i = 0; i < FullDate.size(); i++) {
            String[] DayandFirstHour = FullDate.get(i).split(":");
            DayandFirstHourList.add(DayandFirstHour[0]);
        }
        ArrayList<String> CheckList = CheckListIsDuplicated(DayandFirstHourList);

        for (int m = 0; m < CheckList.size(); m++) {
            for (int i = 0; i < courseSectionRegisterList.size(); i++) {
                if (courseSectionRegisterList.get(i).getCourseSection().getCourseHours().startsWith(CheckList.get(m))) {
                    if (courseObject == null) {
                        courseObject = courseSectionRegisterList.get(i);
                    } else if (courseSectionRegisterList.get(i).getCourseSection().getCourse().get(0).getSemester() > courseObject.getCourseSection().getCourse().get(0).getSemester()) {
                        ListOfCollision.add(courseSectionRegisterList.get(i));
                        courseObject = null;

                    } else {
                        ListOfCollision.add(courseObject);
                        courseObject = null;

                    }
                }
            }
        }

        return ListOfCollision;
    }
    public int calculateCreditTaken(){//
        int creditTaken =0;
        for(int i=0;i<courseSectionRegisterList.size();i++){
            creditTaken+=courseSectionRegisterList.get(i).getCourseSection().getCourse().get(0).getCredit();
        }
        return creditTaken;
    }

    public ArrayList<String> CheckListIsDuplicated(ArrayList<String> OnlyDay) {//
        ArrayList<String> CollisionedDaysList = new ArrayList<String>();

        for (int i = 0; i < OnlyDay.size(); i++) {
            for (int j = 0; j < OnlyDay.size(); j++) {
                if (i == j) continue; //same index position, ignore
                if (!CollisionedDaysList.contains(OnlyDay.get(i))) {
                    if (OnlyDay.get(i).equals(OnlyDay.get(j))) {
                        CollisionedDaysList.add(OnlyDay.get(i));
                    }
                }
            }
        }
        return CollisionedDaysList;
    }
    public ArrayList<CourseSectionRegister> getCourseSectionRegisterList() {
        return courseSectionRegisterList;
    }

    public void setCourseSectionRegisterList(ArrayList<CourseSectionRegister> courseSectionRegisterList) {//
        this.courseSectionRegisterList = courseSectionRegisterList;
    }
    public void approveSchedule(){//
        ArrayList<CourseSectionRegister> list=this.getCourseSectionRegisterList();
        for(CourseSectionRegister register:list){
            register.setStatus("Approved");
            register.getCourseSection().addToRegistrationList(register);
        }
        this.setApproved(true);
    }
    public void clearSchedule(){//
        getCourseSectionRegisterList().clear();
        this.setApproved(false);
    }
}
