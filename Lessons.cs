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
    
    FC ATA122 =  new FC("Atatürk's Prin. & History 2", "ATA122",prerequisite,2,2,0,0);
    FC MATH2056 =  new FC("Llinear Algebra", "MATH2056",prerequisite,2,4,0,0);
    FC MATH1002 =  new FC("Calculus 2", "MATH1002",prerequisite,2,6,0,0);
    DC CSE1141 = new DC("Computer Programming 2", "CSE1142",prerequisite,2,7,0,0);
    FC PHYS1101= new FC("Physics 2", "PHYS1103",prerequisite,2,4,0,0);
    DC TRD121= new DC("Turkish Language 2", "TRD122",prerequisite,2,2,0,0);
    DC NTE1= new DC("Nontechnical Elective 1", "NTE1",prerequisite,2,2,0,0);
    FC PHYS1104= new FC("Physics Lab 2", "PHYS1104",prerequisite,2,2,0,0);
    
    FC MATH2055 =  new FC("Differential Equations", "MATH2055",prerequisite,3,4,0,0);
    FC CSE2025 =  new FC("Data Structures", "CSE2025",prerequisite,3,8,0,0);
    FC CSE2023 =  new FC("Discretet Comp. Structures", "CSE2023",prerequisite,3,6,0,0);
    FC EE2031 =  new FC("Electric Circuits", "EE2031",prerequisite,3,5,0,0);
    FC ECON2003 =  new FC("Introduction to Economics", "ECON2003",prerequisite,3,4,0,0);
    FC NTE2 =  new FC("Nontechnical Elective 1", "NTE2",prerequisite,3,3,0,0);
    
    FC MATH2059 =  new FC("Numerical Methods", "MATH2059",prerequisite,4,4,0,0);
    FC CSE2046 =  new FC("Analysis of Algorithms", "CSE2046",prerequisite,4,6,0,0);
    FC ECON2004 =  new FC("Engineering Economy", "ECON2003",prerequisite,4,4,0,0);
    FC EE2032 =  new FC("Electronics", "EE2032",prerequisite,4,5,0,0);
    FC CSE2138 =  new FC("Systems Programming", "CSE2138",prerequisite,4,7,0,0);
    FC STAT2053 =  new FC("Int. to Prob. and Statics", "STAT2053",prerequisite,4,4,0,0);
    
    FC CSE3000 =  new FC("Summer Practise", "CSE3000",prerequisite,5,10,0,0);
    FC CSE3055 =  new FC("Database Systems", "CSE3055",prerequisite,5,7,0,0);
    FC CSE3033 =  new FC("Operating Systems", "CSE3033",prerequisite,5,7,0,0);
    FC CSE3063 =  new FC("Object-Oriented Software Desg.", "CSE3063",prerequisite,5,5,0,0);
    FC CSE3015 =  new FC("Digital Logic Design", "CSE3015",prerequisite,5,7,0,0);
    FC IE3081 =  new FC("Modeling and Disc. Simulation", "IE3081",prerequisite,5,4,0,0);
    
    FC CSE3048 =  new FC("Int. to Signals and Systems", "CSE3048",prerequisite,6,5,0,0);
    FC CSE3044 =  new FC("Software Engineering", "CSE3044",prerequisite,6,7,0,0);
    FC CSE3064 =  new FC("Formal Lang. & Auto. Theory", "CSE3064",prerequisite,6,6,0,0);
    FC CSE3038 =  new FC("Computer Orginization", "CSE3038",prerequisite,6,7,0,0);
    FC IE3035 =  new FC("Digital Logic Design", "IE3035",prerequisite,6,5,0,0);
 
    FC CSE4197 =  new FC("Engineering Project 1", "CSE4197",prerequisite,7,4,0,0);
    FC CSE4074 =  new FC("Computer Networks", "CSE4074",prerequisite,7,5,0,0);
    FC TE1 =  new FC("Technical Elective 1", "TE1",prerequisite,7,5,0,0);
    FC TE2 =  new FC("Technical Elective 2", "TE2",prerequisite,7,5,0,0);
    FC CSE4117 =  new FC("Microprocessors", "CSE4117",prerequisite,7,6,0,0);
    FC ISG121 =  new FC("Work Safety", "ISG121",prerequisite,7,2,0,0);
    FC UE =  new FC("University Elective", "UE",prerequisite,7,3,0,0);
    FC CSE4000 =  new FC("Summer Practise", "CSE4000",prerequisite,7,5,0,0);
    
    FC CSE4198 =  new FC("Engineering Project 2", "CSE4198",prerequisite,8,5,0,0);
    FC TE3 =  new FC("Technical Elective 3", "TE3",prerequisite,8,5,0,0);
    FC TE4 =  new FC("Technical Elective 4", "TE4",prerequisite,8,5,0,0);
    FC TE5 =  new FC("Technical Elective 5", "TE5",prerequisite,8,5,0,0);
    FC FTE =  new FC("Faculty Technical Elective", "FTE",prerequisite,8,5,0,0);
    FC ISG122 =  new FC("Technical Elective 4", "ISG122",prerequisite,8,2,0,0);
    FC NTE3 =  new FC("Nontechnical Elective 3", "NTE3",prerequisite,8,3,0,0);
    
    
    
}
