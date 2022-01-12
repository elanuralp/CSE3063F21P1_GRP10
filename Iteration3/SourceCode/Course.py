class Course(object):


    def __init__(self, courseName, courseCode, courseSection, coursePrerequisite, courseSemester, courseCredit, courseType):
        self.__name = None
        self.__code = None
        self.__prerequisite = []
        self.__semester = 0
        self.__credit = 0
        self.__type = None

        self.setName(courseName)
        self.setCode(courseCode)
        self.setPrerequisite(coursePrerequisite)
        self.setSemester(courseSemester)
        self.setCredit(courseCredit)
        self.setType(courseType)
        self.__courseSection = courseSection

    def getCourseSection(self):
        return self.__courseSection

    def setCourseSection(self, courseSection):
        self.__courseSection = courseSection


    def getName(self):
        return self.__name

    def setName(self, name):
        self.__name = name

    def getCode(self):
        return self.__code

    def setCode(self, code):
        self.__code = code

    def getPrerequisite(self):
        return self.__prerequisite

    def setPrerequisite(self, prerequisite):
        self.__prerequisite = prerequisite

    def getSemester(self):
        return self.__semester

    def setSemester(self, semester):
        self.__semester = semester

    def getCredit(self):
        return self.__credit

    def setCredit(self, credit):
        self.__credit = credit

    def getType(self):
        return self.__type

    def setType(self, type):
        self.__type = type

    def checkPrerequisite(self, course):
        pass
