import random

from CourseGrade import CourseGrade


class Transcript(object):

    def __init__(self, gradeBook, completedCredit, gpa):
        gradeBook = []
        completedCredit = 0
        gpa = 0

        self.setGradeBook(gradeBook)
        self.setCompletedCredit(completedCredit)
        self.setGpa(gpa)



    def getFailedCourses(self):
        failedCourses = []
        for courseGrade in self.getGradeBook():
            if "FF" in courseGrade.getLetterGrade() or ("FD" in courseGrade.getLetterGrade()):
                failedCourses.append(courseGrade.getCourse())
        return failedCourses

    def getCompletedCourses(self):
        completedCourses = []
        for courseGrade in self.getGradeBook():
            if not("FF" in courseGrade.getLetterGrade()) and not("FD" in courseGrade.getLetterGrade()):
                completedCourses.append(courseGrade.getCourse())

        return completedCourses

    def getGradeBook(self):
        return self.__gradeBook

    def setGradeBook(self, gradeBook):
        self.__gradeBook = gradeBook

    def getCompletedCredit(self):
        return self.__completedCredit

    def setCompletedCredit(self, completedCredit):
        self.__completedCredit = completedCredit

    def getGpa(self):
        return self.__gpa

    def setGpa(self, gpa):
        self.__gpa = gpa

    def addCoursesToTranscript(self, courseList, courseMap):

        grades = ["AA", "BA", "BB", "CB", "CC", "DC", "DD", "FD", "FF"]
        semesterGrades = []
        for courseOfFor in courseList:
            randomNumber = int((random.random()*9))
            cGrade = CourseGrade(courseOfFor, grades[randomNumber])
            self.__gradeBook.append(cGrade)
            semesterGrades.append(cGrade)
            courseMap.update({courseOfFor.getName(): grades[randomNumber]})
        #dt = java.text.DecimalFormat("0.00")
        return format(self.calculateSemesterGPA(semesterGrades))
    def calculateTotalGPA(self):
        gradeCoefficient =0
        courseCredit =0
        totalWeightOfCourses =0
        totalCreditOfCourses =1 #değiştirilcek
        GPA =0
        for courseGrade in self.__gradeBook:
            courseCredit=courseGrade.getCourse().getCredit()
            totalCreditOfCourses+=courseCredit
            if courseGrade.getLetterGrade() == "FF":
                gradeCoefficient=0
            elif courseGrade.getLetterGrade() == "FD":
                gradeCoefficient=0.5
            elif courseGrade.getLetterGrade() == "DD":
                gradeCoefficient=1
            elif courseGrade.getLetterGrade() == "DC":
                gradeCoefficient=1.5
            elif courseGrade.getLetterGrade() == "CC":
                gradeCoefficient=2.0
            elif courseGrade.getLetterGrade() == "CB":
                gradeCoefficient=2.5
            elif courseGrade.getLetterGrade() == "BB":
                gradeCoefficient=3.0
            elif courseGrade.getLetterGrade() == "BA":
                gradeCoefficient=3.5
            elif courseGrade.getLetterGrade() == "AA":
                gradeCoefficient=4.0
            totalWeightOfCourses+= gradeCoefficient*courseCredit
        GPA=totalWeightOfCourses/totalCreditOfCourses
        self.setGpa(GPA)
        #dt = java.text.DecimalFormat("0.00")
        return format(GPA)

    def calculateSemesterGPA(self, courseGradeList):
        gradeCoefficient =0
        courseCredit =0
        totalWeightOfSemesterCourses =0
        totalCreditOfSemesterCourses =0
        semesterGPA =0
        for courseGrade in courseGradeList:
            courseCredit=courseGrade.getCourse().getCredit()
            totalCreditOfSemesterCourses+=courseCredit
            courseLetterGrade =courseGrade.getLetterGrade()
            if courseLetterGrade == "FF":
                gradeCoefficient=0
            elif courseLetterGrade == "FD":
                gradeCoefficient=0.5
            elif courseLetterGrade == "DD":
                gradeCoefficient=1
            elif courseLetterGrade == "DC":
                gradeCoefficient=1.5
            elif courseLetterGrade == "CC":
                gradeCoefficient=2.0
            elif courseLetterGrade == "CB":
                gradeCoefficient=2.5
            elif courseLetterGrade == "BB":
                gradeCoefficient=3.0