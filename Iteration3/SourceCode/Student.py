import random
import os
import time

from CourseSectionRegister import CourseSectionRegister
from Advisor import Advisor
from Lecturer import Lecturer
from Transcript import Transcript
import json
# encoding:utf-8


class Student(object):


    def __init__(self, id, academicYear, advisor, transcript, schedule):
        advisor = Advisor(None,None,None)
        self.__academicSemester =0
        self.__id = id
        self.__transcript = transcript
        self.__academicYear = academicYear
        self.__advisor=advisor
        self.setSchedule(schedule)

    def getId(self):
        return self.__id

    def setId(self, id):
        self.__id = id

    def getTranscript(self):
        return self.__transcript

    def setTranscript(self, transcript):
        self.__transcript = transcript

    def getAcademicYear(self):
        return self.__academicYear

    def setAcademicYear(self, academicYear):
        self.__academicYear = academicYear

    def getAcademicSemester(self):
        return self.__academicSemester

    def setAcademicSemester(self, academicSemester):
        self.__academicSemester = academicSemester

    def startRegistration(self, allSemesterList, semesterStatistic):
        failedCourses=[]
        failedCourses = self.getTranscript().getFailedCourses()
        completedCourses = self.getTranscript().getCompletedCourses()
        preqCourses = []
        for course in failedCourses:
            sectionsOfCourse = course.getCourseSection()
            CourseSectionCount = len(sectionsOfCourse)
            #sectionNumber = 545454 #random

        for semester in allSemesterList:
            currentSemesterCourses = semester.getCourseList()
            for course2 in currentSemesterCourses:
                if course2.getSemester()<=self.getAcademicSemester()+1 and (course2 not in completedCourses):
                    sectionsOfCourse = course2.getCourseSection()
                    CourseSectionCount = len(sectionsOfCourse)
                    sectionNumber = int(random.randint(0,CourseSectionCount-1))+1 # random
                    prerequisites = course2.getPrerequisite()
                    hasPrerequisite = False
                    for pCourse in prerequisites:
                        if pCourse in completedCourses:
                            hasPrerequisite = True
                        else:
                            hasPrerequisite = False
                            break

                    if len(prerequisites)==0:
                        hasPrerequisite=True

                    if hasPrerequisite:
                        sectionsOfCourse[sectionNumber-1].requestToRegister(self)

                    else:
                        preqCourses.append("The system didn't allow "+sectionsOfCourse[sectionNumber-1].getCourse()[0].getCode()+" because student failed prereq.")
                        register = CourseSectionRegister(sectionsOfCourse[sectionNumber-1], "PREREQUISITE", self)
                        semesterStatistic.addtoDeniedCourseSectionRegisterList(register)

        self.__advisor.checkStudentSchedule(self, semesterStatistic,preqCourses)


    def writeToTheJSON(self, courseMap):
        name = str(self.getId().getID())+'.json'

        if(bool(courseMap)==False):
            courseMap={"": ""}


        if (os.path.isfile(name))==False:
            ss={}
            with open(name, "w", encoding='utf-8') as file:
                ss["Semester "+str(self.getAcademicSemester()+1)+":"]=courseMap
                file.seek(0)

                json.dump(ss, file,indent=4)

                return
        if (os.path.isfile(name)) == True:
            with open(name, "r+",encoding='utf-8') as file:
                data = json.load(file)
                data["Semester " + str(self.getAcademicSemester()+1)+":"]=courseMap
                file.seek(0)
                json.dump(data, file,indent=4)







    def writeLogs(self, logs):
        name = str(self.getId().getID()) + '.json'

        with open(name, "r+", encoding='utf-8') as file:
            data = json.load(file)
            data["Semester " + str(self.getAcademicSemester() + 1) + " logs:"] = logs
            file.seek(0)
            json.dump(data, file, indent=4)

        return

    def writeGPA(self, logs):
        name = str(self.getId().getID())+'.json'


        with open(name, "r+", encoding='utf-8') as file:
            data = json.load(file)
            data["Semester " + str(self.getAcademicSemester()+1)+" GPA and cGPA:"] = logs
            file.seek(0)
            json.dump(data, file, indent=4)

        return



    def finishSemester(self):
        registerList = self.schedule.getCourseSectionRegisterList()
        courses = []
        ntecount=0
        ftecount=0
        tecount=0
        for i in range(0,len(self.getTranscript().getGradeBook())):
            if not (self.getTranscript().getGradeBook()[i].getLetterGrade()=="FF" or self.getTranscript().getGradeBook()[i].getLetterGrade()=="FD"):
                if self.getTranscript().getGradeBook()[i].getCourse().getType()=="NTE":
                    ntecount +=1
                elif (self.getTranscript().getGradeBook()[i].getCourse().getType()=="TE"):
                    tecount +=1

        for register in registerList:
            sectionOfReg = register.getCourseSection()
            courseOfSection = sectionOfReg.getCourse()[0]
            if (sectionOfReg.getCourse()[0].getType()=="FTE"):
                courseOfSection=sectionOfReg.getCourse()[ftecount]
            elif (sectionOfReg.getCourse()[0].getType()=="NTE"):
                courseOfSection=sectionOfReg.getCourse()[ntecount]

            courses.append(courseOfSection)


        courseMap = {}
        gpaJSON = "GPA: " + self.__transcript.addCoursesToTranscript(courses, courseMap) + ",cGPA: " + self.getTranscript().calculateTotalGPA();

        #courseDict['GPA and cGPA']= gpaJSON
        self.writeToTheJSON(courseMap);
        self.writeGPA(gpaJSON);
        courseMap.clear();
        self.schedule.clearSchedule();
        self.incrSemester();


    def getSchedule(self):
        return self.schedule

    def setSchedule(self, schedule):
        self.schedule = schedule

    def incrSemester(self):
        self.setAcademicSemester(self.getAcademicSemester()+1)
