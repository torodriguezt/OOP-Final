from gestorAplicacion.gestorMusica.coleccion import Coleccion
from gestorAplicacion.gestorMusica.meGusta import meGusta
from gestorAplicacion.gestorMusica.genero import Genero
from gestorAplicacion.gestorMusica.musica import Musica
from gestorAplicacion.gestorMusica.lista import Lista
from gestorAplicacion.gestorMusica.cancion import Cancion
from gestorAplicacion.gestorPersonas.persona import Persona

import random

''' Módulo Usuario.py
    Autores: Carolina Álvarez Murillo, Miller Johan Chica Acero,
             Tomás Rodríguez Taborda, Jerónimo Ledesma Patiño,
             Catalina Restrepo Salgado
    Este módulo contiene la clase usuario
'''

class Usuario(Persona):
    __usuariosExistentes = []

    def __init__(self,nombre, genFavorito = Genero.NO_ESPECIFICADO):
        self.__nombre = nombre
        self.__genFavorito = genFavorito
        self.__coleccion = Coleccion(self)
        self.__favoritos = meGusta(self)
        self.__tiempoEscuchado = 0
        Usuario.__usuariosExistentes.append(self)
    
    def reproducirCancion(self,cancion):
        cancion.aumentarReproducciones()
        self.__tiempoEscuchado += cancion.getDuracion()
        return cancion.__str__()

    def agregarMeGusta(self, cancion):
        return self.__favoritos.agregarCancion(cancion)

    def eliminarMeGusta(self, cancion):
        return self.__favoritos.eliminarCancion(cancion)

    def reproducirLista(self, lista):
        self.__tiempoEscuchado += lista.duracionLista()
        lista.aumentarReproducciones()
        return lista.__str__()

    def __str__(self):
        return "¡Hola! Mi nombre es " + self.__nombre

    def topArtista(self):
        contadorRepro = 0
        listaMasRepro = []
        verificador = False
        for lista in self.__coleccion.getListas():
            if lista.getReproducciones() > contadorRepro:
                listaMasRepro = lista.getLista()
                contadorRepro = lista.getReproducciones()
                verificador = True
        if verificador:
            reproPorArtista = {}
            artista = None
            cont = 0
            for cancion in listaMasRepro:
                if cancion.getArtista() in reproPorArtista:
                    newValue=reproPorArtista[cancion.getArtista()] + 1
                    reproPorArtista[cancion.getArtista()] = newValue
                else:
                    reproPorArtista[cancion.getArtista()] = 1
                if reproPorArtista[cancion.getArtista()] > cont:
                    artista = cancion.getArtista()
                    cont = reproPorArtista[cancion.getArtista()]
            return artista
        else:
            return None
    
    def setTiempoEscuchado(self,tiempoEscuchado):
        self.__tiempoEscuchado=tiempoEscuchado

    def getTiempoEscuchado(self):
        return self.__tiempoEscuchado

    def setColeccion(self, coleccion):
        self.__coleccion = coleccion

    def getColeccion(self):
        return self.__coleccion

    def getFavoritos(self):
        return self.__favoritos
    
    def getNombre(self):
        return self.__nombre
        
    def setNombre(self, nombre):
        self.__nombre = nombre
    
    def getGenFavorito(self):
        return self.__genFavorito
    
    def setGenFavorito(self, genFavorito):
        self.__genFavorito = genFavorito

    @classmethod
    def getUsuariosExistentes(cls):
        return cls.__usuariosExistentes

    @classmethod
    def setUsuariosExistentes(cls, usuariosExistentes):
        cls.__usuariosExistentes = usuariosExistentes
	
    @classmethod
    def genFavoritoSpotyfree(cls):
        REGGAETON=0
        ROCK=0
        POP=0
        SALSA=0
        Kpop=0 
        NO_ESPECIFICADO=0
		
        mayor=0
        genero=None
        for usuario in cls.getUsuariosExistentes():
            if usuario.getGenFavorito()==Genero.REGGAETON:
                REGGAETON+=1
                if REGGAETON>mayor:
                    mayor=REGGAETON
                    genero=Genero.REGGAETON
            elif usuario.getGenFavorito()==Genero.ROCK:
                ROCK+=1
                if (ROCK>mayor):
                    mayor=ROCK
                    genero=Genero.ROCK
            
            elif usuario.getGenFavorito()==Genero.SALSA:
                SALSA+=1
                if SALSA>mayor:
                    mayor=SALSA
                    genero=Genero.SALSA
            
            elif usuario.getGenFavorito()==Genero.POP:
                POP+=1
                if (POP>mayor):
                    mayor=POP
                    genero=Genero.POP
            
            elif usuario.getGenFavorito()==Genero.KPOP:
                Kpop+=1
                if (Kpop>mayor):
                    mayor=Kpop
                    genero=Genero.KPOP
            else:
                NO_ESPECIFICADO+=1
                if NO_ESPECIFICADO>mayor:
                    mayor=NO_ESPECIFICADO
                    genero=Genero.NO_ESPECIFICADO
        return genero
	
    def puntosFavoritos(self, usuario):
        REGGAETON=0
        ROCK=0
        POP=0
        SALSA=0
        KPOP=0 
        NO_ESPECIFICADO=0
        Puntos=[]
        total = len(usuario.getFavoritos().getFavoritos())
        if len(usuario.getFavoritos().getFavoritos())==0:
            Puntos.append(0.0)
            Puntos.append(0.0)
            Puntos.append(0.0)
            Puntos.append(0.0)
            Puntos.append(0.0)
            Puntos.append(0.0)
        else:
            for cancion in usuario.getFavoritos().getFavoritos():
                if cancion.getGenero()==Genero.REGGAETON:
                    REGGAETON+=1
                elif cancion.getGenero()==Genero.ROCK:
                    ROCK+=1
                elif cancion.getGenero()==Genero.SALSA: 
                    SALSA+=1
                elif cancion.getGenero()==Genero.POP:
                    POP+=1
                elif cancion.getGenero()==Genero.KPOP:
                    KPOP+=1
                else:
                    NO_ESPECIFICADO+=1

            Puntos.append(REGGAETON/total*100)
            Puntos.append(ROCK/total*100)
            Puntos.append(SALSA/total*100)
            Puntos.append(POP/total*100)
            Puntos.append(KPOP/total*100)
            Puntos.append(NO_ESPECIFICADO/total*100)
        return Puntos
        
    def tuGenFavorito(self, usuario):
        REGGAETON=0
        ROCK=0
        POP=0
        SALSA=0
        Kpop=0 
        NO_ESPECIFICADO=0
        genero=None
        mayor = 0
        
        for cancion in usuario.getFavoritos().getFavoritos():
            if cancion.getGenero()==Genero.REGGAETON:
                    REGGAETON+=1
                    if REGGAETON>mayor:
                        mayor=REGGAETON
                        genero=Genero.REGGAETON
            elif cancion.getGenero()==Genero.ROCK:
                ROCK+=1
                if ROCK>mayor:
                    mayor=ROCK
                    genero=Genero.ROCK
            elif cancion.getGenero()==Genero.SALSA: 
                SALSA+=1
                if SALSA>mayor:
                    mayor=SALSA
                    genero=Genero.SALSA
            elif cancion.getGenero()==Genero.POP:
                if POP>mayor:
                    mayor=POP
                    genero=Genero.POP
                POP+=1
            elif cancion.getGenero()==Genero.KPOP:
                Kpop+=1
                if Kpop>mayor:
                    mayor=Kpop
                    genero=Genero.KPOP
            else:
                NO_ESPECIFICADO+=1
                if NO_ESPECIFICADO>mayor:
                    mayor=NO_ESPECIFICADO
                    genero=Genero.NO_ESPECIFICADO
        return genero

    def encontrarAmigo(self,posiblesAmigos=[]):
        if len(posiblesAmigos) > 0:
            return random.choice(posiblesAmigos)
        else:
            return None
