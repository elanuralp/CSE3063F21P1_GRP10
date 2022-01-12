class Schedule(object):

    def __init__(self, courseSectionRegisterList, isApproved):
        self.__courseSectionRegisterList = courseSectionRegisterList
        self.isApproved = isApproved

    def addToSchedule(self, courseSectionRegister):
        courseSectionRegisterListToAdd =self.getCourseSectionRegisterList()
        courseSectionRegisterListToAdd.append(courseSectionRegister)

        self.setCourseSectionRegisterList(courseSectionRegisterListToAdd)
    def removeFromSchedule(self, courseSectionRegister):
        if(courseSectionRegister in self.__courseSectionRegisterList):
            self.__courseSectionRegisterList.remove(courseSectionRegister)

    def isApproved(self):
        return self.isApproved

    def setApproved(self, approved):
        self.isApproved = approved


    def checkCourseSectionCollision(self):
        ListOfCollision = []
        courseObject = None
        FullDate = []
        DayandFirstHourList = []
        i = 0
        while i < len(self.__courseSectionRegisterList):
            splittedFromComma = self.__courseSectionRegisterList[i].getCourseSection().getCourseHours().split(", ")
            for FullCourseDate in splittedFromComma:
                FullDate.append(FullCourseDate)
            i += 1
        i = 0
        while i < len(FullDate):
            DayandFirstHour = FullDate[i].split(":")
            DayandFirstHourList.append(DayandFirstHour[0])
            i += 1
        CheckList = self.CheckListIsDuplicated(DayandFirstHourList)
        m = 0
        while m < len(CheckList):
            i = 0
            while i < len(self.__courseSectionRegisterList):
                if self.__courseSectionRegisterList[i].getCourseSection().getCourseHours().startswith(CheckList[m]):
                    if courseObject is None:
                        courseObject = self.__courseSectionRegisterList[i]
                    elif self.__courseSectionRegisterList[i].getCourseSection().getCourse()[0].getSemester() > courseObject.getCourseSection().getCourse()[0].getSemester():
                        ListOfCollision.append(self.__courseSectionRegisterList[i])
                        courseObject = None

                    else:
                        ListOfCollision.append(courseObject)
                        courseObject = None

                i += 1
            m += 1
        return ListOfCollision
    def calculateCreditTaken(self):
        creditTaken =0
        i = 0
        while i<len(self.__courseSectionRegisterList):
            creditTaken+=self.__courseSectionRegisterList[i].getCourseSection().getCourse()[0].getCredit()
            i += 1
        return creditTaken

    def CheckListIsDuplicated(self, OnlyDay):
        CollisionedDaysList = []
        i = 0
        while i < len(OnlyDay):
            j = 0
            while j < len(OnlyDay):
                if i == j:
                    j+=1
                    continue #same index position, ignore
                if not OnlyDay[i] in CollisionedDaysList:
                    if OnlyDay[i] == OnlyDay[j]:
                        CollisionedDaysList.append(OnlyDay[i])
                j += 1
            i += 1
        return CollisionedDaysList
    def getCourseSectionRegisterList(self):
        return self.__courseSectionRegisterList

    def setCourseSectionRegisterList(self, courseSectionRegisterList):
        self.__courseSectionRegisterList = courseSectionRegisterList
    def approveSchedule(self):
        list =self.getCourseSectionRegisterList()
        for register in list:
            register.setStatus("Approved")
            register.getCourseSection().addToRegistrationList(register)
        self.setApproved(True)
    def clearSchedule(self):
        self.getCourseSectionRegisterList().clear()
        self.setApproved(False)
