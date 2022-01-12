class Semester(object):
    def __init__(self, courseList, semesterID):


        self.semesterID = semesterID
        self.__courseList = courseList

    def getSemesterID(self):
        return self.semesterID

    def setSemesterID(self, semesterID):
        self.semesterID = semesterID

    def getCourseList(self):
        return self.__courseList

    def setCourseList(self, courseList):
        self.__courseList = courseList