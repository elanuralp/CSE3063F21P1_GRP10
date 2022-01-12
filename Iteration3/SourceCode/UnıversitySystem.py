import random
import json

from Course import Course
from CourseSection import CourseSection
from Identity import Identity
from Lecturer import Lecturer
from Schedule import Schedule
from Semester import Semester
from SemesterStatistic import SemesterStatistic
from Student import Student
from Transcript import Transcript


class UniversitySystem(object):
    def __init__(self):
        self.__academicCurrentYear = 0
        self.__currentSemesterType ="FALL"
        self.__studentNumberToBeAdded = 0
        self.__willBeSimulatedSemesterType = None
        self.__allStudentList = []
        self.__semesterList = []
        self.__courseSectionList = []
        self.__currentSemesterStatistic = SemesterStatistic()
        self.__lecturerList = []
        self.__advisorList = []


    def getAllStudentList(self):
        return self.__allStudentList

    def startSimulation(self):
        if "FALL" in self.__willBeSimulatedSemesterType:
            self.simulateSemester(7)
        else:
            self.simulateSemester(8)

        print("Simulation completed succesfully")

    def simulateSemester(self, count):
        i = 0
        while i<count:
            empty = []
            self.__currentSemesterStatistic.setDeniedCourseSectionRegisterList(empty)
            if self.__currentSemesterType is "FALL":
                self.createRandomStudents(self.__studentNumberToBeAdded)
            j = 0
            while j<len(self.__allStudentList):
                self.__allStudentList[j].startRegistration(self.__semesterList,self.__currentSemesterStatistic)

                j += 1

            self.completeSemester()
            i += 1
    def createRandomStudents(self, numOfStudentsToBeAdded):
        index = int((random.random() * len(self.__advisorList)))
        i = 0
        while i<numOfStudentsToBeAdded:
            empty = []
            student = Student(Identity(len(self.__allStudentList)), self.__academicCurrentYear,self.__advisorList[index], Transcript(empty,0,0), Schedule([],False))
            self.__allStudentList.append(student)
            # try:
            #     myWriter = java.io.FileWriter(student.getId().getID()+".json")
            #     myWriter.close()
            # except java.io.IOException as e:
            #     print("An error occurred.")
            #     e.printStackTrace()
            i += 1

    def completeSemester(self):
        i = 0
        while i<len(self.__allStudentList):
            self.__allStudentList[i].finishSemester()
            i += 1
        if self.__currentSemesterType is "FALL":
            self.__currentSemesterType="SPRING"
        else:
            self.__currentSemesterType="FALL"
            self.__academicCurrentYear += 1
        self.__currentSemesterStatistic.calculateSemesterStatistics()
        self.__currentSemesterStatistic.writeToJson()


        i = 0
        while i<len(self.__semesterList):
            j = 0
            while j<len(self.__semesterList[i].getCourseList()):
                k = 0
                while k<len(self.__semesterList[i].getCourseList()[j].getCourseSection()):
                    empty = []
                    self.__semesterList[i].getCourseList()[j].getCourseSection()[k].setRegisteredStudents(empty)
                    k += 1
                j += 1
            i += 1
    def initializeUniversityCourseSelectionSystem(self):
        self.initializeFromJson("input.json")
        self.startSimulation()


    def initializeFromJson(self,string):
        with open(string, encoding="utf-8") as json_file:
            data = json.load(json_file)
            self.__willBeSimulatedSemesterType=data[0]["Settings"]["willBeSimulatedSemesterType"]
            self.__studentNumberToBeAdded=int(data[0]["Settings"]["numberOfStudentsToBeAdded"])
            fteList = []
            nteList=[]
            teList=[]
            for p1 in data[0]["FTE"]:
                lecturer = Lecturer(0,data[0]["FTE"][p1]["name"],"")
                courseSection = CourseSection([],p1,30,lecturer,data[0]["FTE"][p1]["schedule"])
                fteList.append(courseSection)
            for p1 in data[0]["NTE"]:
                lecturer = Lecturer(0, data[0]["NTE"][p1]["name"], "")
                courseSection = CourseSection([], p1, 30, lecturer, data[0]["NTE"][p1]["schedule"])
                nteList.append(courseSection)

            for p1 in data[0]["TE"]:
                lecturer = Lecturer(0, data[0]["TE"][p1]["name"], "")
                courseSection = CourseSection([], p1, 30, lecturer, data[0]["TE"][p1]["schedule"])
                teList.append(courseSection)
            for i in range(8):
                semester = Semester([], i+1)
                self.__semesterList.append(semester)
                for p1 in data[0]["Courses" + str(i + 1)]:
                    course = Course(data[0]["Courses"+ str(i + 1)][p1]["name"],p1,[],[],i+1,int(data[0]["Courses"+ str(i + 1)][p1]["credit"]),data[0]["Courses"+ str(i + 1)][p1]["type"])
                    semester.getCourseList().append(course)
                    sectionList=[]
                    for p2 in data[0]["Courses" + str(i + 1)][p1]["sections"]:
                        lecturer1 = Lecturer(0, data[0]["Courses"+ str(i + 1)][p1]["sections"][p2]["lecturer"], "")
                        courseSection = CourseSection([course], p2, int(data[0]["Courses"+ str(i + 1)][p1]["quota"]), lecturer1, data[0]["Courses" + str(i + 1)][p1]["sections"][p2]["schedule"])
                        sectionList.append(courseSection)
                        self.__lecturerList.append(lecturer1)
                        if(len(self.__lecturerList)%5==0):
                            self.__advisorList.append(lecturer1)
                    preqList=[]
                    for p2 in data[0]["Courses" + str(i + 1)][p1]["prerequisite"]:
                        for p3 in range(len(self.__semesterList)):
                            for p4 in range(len(self.__semesterList[p3].getCourseList())):
                                if(self.__semesterList[p3].getCourseList()[p4].getCode() == p2):
                                    preqList.append(self.__semesterList[p3].getCourseList()[p4])
                    course.setCourseSection(sectionList)
                    course.setPrerequisite(preqList)

                    if(course.getType()=="NTE"):
                        course.setCourseSection(nteList)
                        for ntecourses in range(len(nteList)):

                            nteList[ntecourses].getCourse().append(course)
                    elif(course.getType()=="FTE"):
                         course.setCourseSection(fteList)
                         for ftecourses in range(len(fteList)):
                             fteList[ftecourses].getCourse().append(course)
                    elif(course.getType()=="TE"):
                         course.setCourseSection(teList)
                         for tecourses in range(len(teList)):
                             teList[tecourses].getCourse().append(course)




