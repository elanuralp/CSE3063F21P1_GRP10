class CourseSectionRegister(object):
    def __init__(self, courseSection, status, student):

        self.courseSection=courseSection
        self.status=status
        self.student=student
        StudentSchedule =self.student.getSchedule()
        StudentSchedule.addToSchedule(self)


    def getCourseSection(self):
        return self.courseSection

    def setCourseSection(self, courseSection):
        self.courseSection = courseSection

    def getStatus(self):
        return self.status

    def setStatus(self, status):
        self.status = status
