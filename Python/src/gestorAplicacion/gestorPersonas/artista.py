from gestorAplicacion.gestorMusica.genero import Genero
from gestorAplicacion.gestorPersonas.usuario import Usuario
from gestorAplicacion.gestorMusica.cancion import Cancion
from gestorAplicacion.gestorPersonas.persona import Persona

''' Módulo artista.py
    Autores: Carolina Álvarez Murillo, Miller Johan Chica Acero,
             Tomás Rodríguez Taborda, Jerónimo Ledesma Patiño,
             Catalina Restrepo Salgado
    Este módulo contiene la clase Artista
'''

class Artista(Persona): #ACTUALIZAR CON PERSONA Y SERIALIZABLE

    __artistasDisponibles = []
    
    def __init__(self, str_nombre, Genero_genero = Genero.NO_ESPECIFICADO):
        self.__nombre = str_nombre
        self.__genero = Genero_genero
        self.__canciones = []
        self.__puntaje = 0
        Artista.__artistasDisponibles.append(self)
        
    def getNombre(self):
        return self.__nombre #str
    
    def setNombre(self, str_nombre):
        self.__nombre = str_nombre

    def getCanciones(self):
        return self.__canciones #list
    
    def setCanciones(self, list_canciones):
        self.__canciones = list_canciones
    
    def getGenero(self):
        return self.__genero #Genero
    
    def setGenero(self, Genero_genero):
        self.__genero = Genero_genero
    
    def getPuntaje(self):
        return self.__puntaje #float

    def setPuntaje(self, float_puntaje):
        self.__puntaje = float_puntaje
    
    def agregarCancion(self, cancion):
        self.__canciones.append(cancion)
        return "La canción " + cancion.getNombre() + "fue agregada exitosamente"
    
    def eliminarCancion(self, cancion):
        self.__canciones.remove(cancion)
        return "La canción " + cancion.getNombre() + "fue eliminada exitosamente"

    def __str__(self):
        return self.__nombre + " es un artista con " + str(len(self.__canciones)) + " canciones publicadas en Spotifree pertenecientes al género " + self.__genero.value
    
    @classmethod
    def getArtistasDisponibles(cls):
        return cls.__artistasDisponibles
    
    @classmethod
    def setArtistasDisponibles(cls, artistasDisponibles):
        cls.__artistasDisponibles = artistasDisponibles

    def getMeGusta(self, artista):
        like = 0
        for usuario in Usuario.getUsuariosExistentes():
            for cancion in usuario.getFavoritos().getFavoritos():
                if cancion.getArtista().getNombre().__eq__(artista.getNombre()):
                    like += 1
        return like
    
    def getReproducciones(self, artista):
        reproducciones = 0
        for cancion in Cancion.getCancionesDisponibles():
            if cancion.getArtista().getNombre().__eq__(artista.getNombre()):
                reproducciones += cancion.getReproducciones()
        return reproducciones
    
    @classmethod
    def ordenarPorPuntaje(cls):
        cls.__artistasDisponibles.sort(key = lambda x: x.getPuntaje(), reverse = True)
        return cls.__artistasDisponibles