class Lecturer:

    def __init__(self, id, name, surname):

        self.__id = id
        self.__name = name
        self.__surname = surname

    def getId(self):
        return self.id

    def setId(self, id):
        self.id = id

    def getName(self):
        return self.name

    def setName(self, name):
        self.name = name

    def getSurname(self):
        return self.surname

    def setSurname(self, surname):
        self.surname = surname