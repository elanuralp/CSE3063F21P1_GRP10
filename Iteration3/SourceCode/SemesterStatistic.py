
class SemesterStatistic(object):



    def __init__(self):
        self.__deniedCourseSectionRegisterList = []
        self.courseMap = {}
        self.courseTypeMap = {}


    def addtoDeniedCourseSectionRegisterList(self, deniedCourseSectionRegister):
        self.__deniedCourseSectionRegisterList.append(deniedCourseSectionRegister)



    def setDeniedCourseSectionRegisterList(self, deniedCourseSectionRegisterList):
        self.__deniedCourseSectionRegisterList = deniedCourseSectionRegisterList

    def calculateSemesterStatistics(self):
        i = None
        i = 0
        while i<len(self.__deniedCourseSectionRegisterList):

            courseNameAndStatus = self.__deniedCourseSectionRegisterList[i].courseSection.getCourse()[0].getName()+ " DUE TO " + self.__deniedCourseSectionRegisterList[i].status
            courseType =self.__deniedCourseSectionRegisterList[i].courseSection.getCourse()[0].getType()
            # if courseNameAndStatus in self.courseMap.keys():
            #
            #     self.courseMap.replace(courseNameAndStatus, self.courseMap[courseNameAndStatus] + 1)
            #
            #
            # else:
            #     self.courseMap.update({courseNameAndStatus: 1})
            #
            # if courseType in self.courseTypeMap.keys():
            #
            #     self.courseTypeMap.replace(courseType, self.courseTypeMap[courseType] + 1)
            #
            #
            # else:
            #     self.courseTypeMap.update({courseType: 1})

            i += 1

    def writeToJson(self):
        print("writeToJSON fonksiyonuyum ben ")
        # jsonObject = org.json.simple.JSONObject()
        # logs = org.json.simple.JSONArray()
        # for entry in self.courseMap.entrySet():
        #     logs.add(entry.getValue() + " STUDENTS COULDN'T REGISTER FOR "+ entry.getKey() + " PROBLEMS.")
        #
        # for entry2 in self.courseTypeMap.entrySet():
        #     logs.add(entry2.getValue() + " STUDENTS COULDN'T REGISTER FOR A "+ entry2.getKey() + " THIS SEMESTER.")
        #
        #
        # jsonObject.put("Logs", logs)
        # try:
        #     file = java.io.FileWriter("DEPARTMENT_OUTPUT_SEMESTER.json")
        #     file.write(jsonObject.toJSONString())
        #     file.close()
        # except java.io.IOException as e:
        #     e.printStackTrace()
