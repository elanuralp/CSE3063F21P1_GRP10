class Identity(object):

    def __init__(self, order):
        self.__ID = 0

        self.__ID = self.createID(order)


    def ID(self, ID):

        pass

    def getID(self):
        return self.__ID

    def setID(self, ID):
        self.__ID = ID
    def createID(self, order):
        return order+150110000