import math

from Lecturer import Lecturer


class Advisor(Lecturer):

    def _initialize_instance_fields(self):
        self.__studentList = []


#var bi bokluk burda
    def __init__(self, id, name, surname):
        self._initialize_instance_fields()

        super().__init__(id,name,surname)

    def getStudentList(self):
        return self.__studentList



    def setStudentList(self, studentList):
        self.__studentList = studentList
    @staticmethod
    def CheckStudentCourseSelection(transcript):
        return True

    def checkStudentSchedule(self, student, semesterStatistic, preq):
        logs = preq

        studentSchedule = student.getSchedule()
        studentTranscript = student.getTranscript()
        tecount =0
        if math.fmod(student.getAcademicSemester(), 2)==0:
            i = 0
            while i<len(studentSchedule.getCourseSectionRegisterList()):
                #if studentSchedule.getCourseSectionRegisterList()[i].getCourseSection().getCourse()[0].getType() == "TE":
                    tecount += 1
                    if tecount>1:
                        studentSchedule.removeFromSchedule(studentSchedule.getCourseSectionRegisterList()[i])
                        logs.append("The advisor didn't approve TE because student already took 2 TE and in FALL semester only 2 TE can be taken")
                #i += 1
        if math.fmod(student.getAcademicSemester(), 2)==0:
            i = 0
            while i<len(studentSchedule.getCourseSectionRegisterList()):
                if studentSchedule.getCourseSectionRegisterList()[i].getCourseSection().getCourse()[0].getType() == "FTE":
                    studentSchedule.removeFromSchedule(studentSchedule.getCourseSectionRegisterList()[i])
                    logs.append("The advisor didn't approve FTE CSE4062 because students can't take FTE in FALL semester unless they are graduating this semester")
                i += 1
        collidingRegisters = studentSchedule.checkCourseSectionCollision()
        if len(collidingRegisters) >= 1:
            print("********************")
            for register in collidingRegisters:

                register.setStatus("COLLIDE")
                semesterStatistic.addtoDeniedCourseSectionRegisterList(register)
                studentSchedule.removeFromSchedule(register)
                logs.append("Couldn't register "+register.getCourseSection().getCourse()[0].getCode() + " due to collide problem.")


        studentRegisterList = studentSchedule.getCourseSectionRegisterList()
        studentRegisterListWillBeDeleted = []
        for register in studentRegisterList:
            if not register.getCourseSection().isQuotaAvailable():
                register.setStatus("QUOTA")
                semesterStatistic.addtoDeniedCourseSectionRegisterList(register)
                studentRegisterListWillBeDeleted.append(register)
                logs.append("Couldn't register "+register.getCourseSection().getCourse()[0].getCode() + " due to quota problem.")


        studentCompletedCredit = studentTranscript.getCompletedCredit()

        for register in studentRegisterList:
            if studentCompletedCredit < 155:

                registeredCourseSection = register.getCourseSection()
                courseOfSection = registeredCourseSection.getCourse()[0]
                semesterInfo = courseOfSection.getSemester()
                if semesterInfo > 7:
                    studentRegisterListWillBeDeleted.append(register)
                    logs.append("The advisor didn't approve "+register.getCourseSection().getCourse()[0].getCode() + " because student completed credits < 155.")
            elif studentCompletedCredit > 155 and studentCompletedCredit < 165:

                registeredCourseSection = register.getCourseSection()
                courseOfSection = registeredCourseSection.getCourse()[0]
                courseName = courseOfSection.getName()
                engineeringProject1 = "CSE 4297"
                if (courseName.compareTo(engineeringProject1)) == 0:
                    studentRegisterListWillBeDeleted.append(register)
                    logs.append("The advisor didn't approve graduation project "+register.getCourseSection().getCourse()[0].getCode() + " because student completed credits < 165")

        while student.getSchedule().calculateCreditTaken()>32:
            size =len(student.getSchedule().getCourseSectionRegisterList())
            logs.append("The advisor didn't approve "+student.getSchedule().getCourseSectionRegisterList()[size-1].getCourseSection().getCourse()[0].getCode() + " because student took enough credits.")
            student.getSchedule().getCourseSectionRegisterList().pop(size-1)
        for register in studentRegisterListWillBeDeleted:
            studentSchedule.removeFromSchedule(register)

        studentSchedule.approveSchedule()

        #if (not not) logs:
            #student.writeLogs(logs)
