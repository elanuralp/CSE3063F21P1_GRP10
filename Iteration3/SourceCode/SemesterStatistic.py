import json
class SemesterStatistic(object):



    def __init__(self):
        self.__deniedCourseSectionRegisterList = []
        self.__courseMap = {}
        self.__courseTypeMap = {}


    def addtoDeniedCourseSectionRegisterList(self, deniedCourseSectionRegister):
        self.__deniedCourseSectionRegisterList.append(deniedCourseSectionRegister)



    def setDeniedCourseSectionRegisterList(self, deniedCourseSectionRegisterList):
        self.__deniedCourseSectionRegisterList = deniedCourseSectionRegisterList

    def calculateSemesterStatistics(self):
        i =0
        while i< len(self.__deniedCourseSectionRegisterList):


            courseNameAndStatus = self.__deniedCourseSectionRegisterList[i].getCourseSection().getCourse()[0].getName()+ " DUE TO " + self.__deniedCourseSectionRegisterList[i].getStatus()
            courseType =self.__deniedCourseSectionRegisterList[i].getCourseSection().getCourse()[0].getType()

            i=i+1

            if courseNameAndStatus in self.__courseMap.keys():

                self.__courseMap[courseNameAndStatus]=self.__courseMap[courseNameAndStatus] + 1


            else:
                self.__courseMap[courseNameAndStatus]=1

            if courseType in self.__courseTypeMap.keys():

                self.__courseTypeMap[courseType]=self.__courseTypeMap[courseType] + 1


            else:
                self.__courseTypeMap[courseType] = 1


    def writeToJson(self):
        name="DEPARTMENT_OUTPUT_SEMESTER.json"
        logs=[]

        for i,j in self.__courseMap.items():
            logs.append(str(j) + " STUDENTS COULDN'T REGISTER FOR "+ i + " PROBLEMS.")

        for i,j in self.__courseTypeMap.items():
            logs.append(str(j)+ " STUDENTS COULDN'T REGISTER FOR A "+ i + " THIS SEMESTER.")

        with open(name, "w", encoding='utf-8') as file:
            jsonString = json.dumps(logs,indent=4)
            file.write(jsonString)

        return

