from CourseSectionRegister import CourseSectionRegister


class CourseSection(object):
    def __init__(self, course, sectionNumber, quota, sectionLecturer, courseHours):
        self.__registeredStudents = []

        self.__course = course
        self.__sectionNumber=sectionNumber
        self.__quota=quota
        self.__sectionLecturer=sectionLecturer
        self.__courseHours=courseHours

    def getCourse(self):
        return self.__course
    
    def setCourse(self, course):
        self.__course = course

    def getSectionNumber(self):
        return self.__sectionNumber

    def setSectionNumber(self, sectionNumber):
        self.__sectionNumber = sectionNumber

    def getCourseHours(self):
        return self.__courseHours

    def setCourseHours(self, courseHours):
        self.__courseHours = courseHours


    def getQuota(self):
        return self.__quota

    def setQuota(self, quota):
        self.__quota = quota

    def getSectionLecturer(self):
        return self.__sectionLecturer

    def setSectionLecturer(self, sectionLecturer):
        self.__sectionLecturer = sectionLecturer

    def getCourse(self):
        return self.__course



    def requestToRegister(self, student):
        courseSectionRegister = CourseSectionRegister(self,"register",student)
    def isQuotaAvailable(self):
        if len(self.getRegisteredStudents())<self.getQuota():
            return True
        return False
    def addToRegistrationList(self, register):
        newList = self.getRegisteredStudents()
        newList.append(register)
        self.setRegisteredStudents(newList)

    def getRegisteredStudents(self):
        return self.__registeredStudents

    def setRegisteredStudents(self, registeredStudents):
        self.__registeredStudents = registeredStudents