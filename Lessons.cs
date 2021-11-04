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
    
    FC MATH2055 =  new FC("Differential Equations", "MATH2055",prerequisite,1,4,0,0);
    FC CSE2025 =  new FC("Data Structures", "CSE2025",prerequisite,1,8,0,0);
    FC CSE2023 =  new FC("Discretet Comp. Structures", "CSE2023",prerequisite,1,6,0,0);
    FC EE2031 =  new FC("Electric Circuits", "EE2031",prerequisite,1,5,0,0);
    FC ECON2003 =  new FC("Introduction to Economics", "ECON2003",prerequisite,1,4,0,0);
    FC NTE2 =  new FC("Nontechnical Elective 1", "NTE2",prerequisite,1,3,0,0);
    
    FC MATH2059 =  new FC("Numerical Methods", "MATH2059",prerequisite,1,4,0,0);
    FC CSE2046 =  new FC("Analysis of Algorithms", "CSE2046",prerequisite,1,6,0,0);
    FC ECON2004 =  new FC("Engineering Economy", "ECON2003",prerequisite,1,4,0,0);
    FC EE2032 =  new FC("Electronics", "EE2032",prerequisite,1,5,0,0);
    FC CSE2138 =  new FC("Systems Programming", "CSE2138",prerequisite,1,7,0,0);
    FC STAT2053 =  new FC("Int. to Prob. and Statics", "STAT2053",prerequisite,1,4,0,0);
 
}
