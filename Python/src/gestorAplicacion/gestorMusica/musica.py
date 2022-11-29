''' Módulo musica.py
    Autores: Carolina Álvarez Murillo, Miller Johan Chica Acero,
             Tomás Rodríguez Taborda, Jerónimo Ledesma Patiño,
             Catalina Restrepo Salgado
    Este módulo contiene la clase Música, de quien van a heredar Canción, Lista y MeGusta
'''

# Esta clase describe los principales atributos y comportamientos de la música que se
# va a gestionar en la aplicación
class Musica: 

    def __init__(self, nombre):
        self.__nombre = nombre
        self.__reproducciones = 0
    
    def getNombre(self):
        return self.__nombre

    def setNombre(self, nombre):
        self.__nombre = nombre
    
    def getReproducciones(self):
        return self.__reproducciones
    
    def setReproducciones(self, reproducciones):
        self.__reproducciones = reproducciones

    def aumentarReproducciones():
        pass

    def __str__():
        pass