class CourseGrade(object):
    def __init__(self, course, letterGrade):

        self.setCourse(course)
        self.setLetterGrade(letterGrade)

    def getCourse(self):
        return self.__course

    def setCourse(self, course):
        self.__course = course

    def getLetterGrade(self):
        return self.__letterGrade

    def setLetterGrade(self, letterGrade):
        self.__letterGrade = letterGrade