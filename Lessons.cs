package CourseRegistration;

import java.util.ArrayList;

public class Main {
    ArrayList<Course>prerequisite=new ArrayList<Course>();
    FC ATA121 =  new FC("Atatürk's Prin. & History 1", "ATA101",prerequisite,1,2,0,0);
    FC MATH1001 =  new FC("Calculus 1", "MATH1001",prerequisite,1,6,0,0);
    FC CHEM1007= new FC("Basic Chemistry", "CHEM1007",prerequisite,1,6,0,0);
    DC CSE1141 = new DC("Computer Programming 1", "CSE1141",prerequisite,1,6,0,0);
    FC PHYS1101= new FC("Physics 1", "PHYS1103",prerequisite,1,2,0,0);
    FC CSE1100= new FC("Introduction to Computer Eng.", "CSE1100",prerequisite,1,2,0,0);
    DC TRD121= new DC("Turkish Language 1", "TRD121",prerequisite,1,2,0,0);
    FC PHYS1103= new FC("Physics Lab 1", "PHYS1103",prerequisite,1,2,0,0);
    
    FC ATA121 =  new FC("Atatürk's Prin. & History 2", "ATA102",prerequisite,1,2,0,0);
    FC MATH2056 =  new FC("Llinear Algebra", "MATH2056",prerequisite,1,4,0,0);
    FC MATH1001 =  new FC("Calculus 2", "MATH1002",prerequisite,1,6,0,0);
    DC CSE1141 = new DC("Computer Programming 2", "CSE1142",prerequisite,1,7,0,0);
    FC PHYS1101= new FC("Physics 2", "PHYS1103",prerequisite,1,4,0,0);
    DC TRD121= new DC("Turkish Language 2", "TRD122",prerequisite,1,2,0,0);
    DC NTE1= new DC("Nontechnical Elective 1", "NTE1",prerequisite,1,2,0,0);
    FC PHYS1104= new FC("Physics Lab 2", "PHYS1104",prerequisite,1,2,0,0);
 
}
