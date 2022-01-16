class CourseSectionRegister(object):
    def __init__(self, courseSection, status, student):

        self.__courseSection=courseSection
        self.__status=status
        self.__student=student
        StudentSchedule =self.__student.getSchedule()
        StudentSchedule.addToSchedule(self)


    def getCourseSection(self):
        return self.__courseSection

    def setCourseSection(self, courseSection):
        self.__courseSection = courseSection

    def getStatus(self):
        return self.__status

    def setStatus(self, status):
        self.__status = status
