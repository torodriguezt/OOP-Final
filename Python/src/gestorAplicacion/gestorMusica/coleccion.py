from gestorAplicacion.gestorMusica.genero import Genero

''' Módulo musica.py
    Autores: Carolina Álvarez Murillo, Miller Johan Chica Acero,
             Tomás Rodríguez Taborda, Jerónimo Ledesma Patiño,
             Catalina Restrepo Salgado
    Este módulo contiene la clase Coleccion
'''

class Coleccion:
    
    __coleccionesExistentes=[]

    def __init__(self, usuario, listas=[], cancionesRecomendadas=[], colaborativas=[]):
        self.__usuario=usuario
        self.__listas=[]
        self.__cancionesRecomendadas = []
        self.__colaborativas=[]
        Coleccion.__coleccionesExistentes.append(self)
    
    def getUsuario(self):
        return self.__usuario
    
    def setUsuario(self,usuario):
        self.__usuario=usuario

    def getListas(self) -> list:
        return self.__listas
    
    def setListas(self,listas):
        self.__listas=listas
    
    @classmethod
    def getColeccionesExistentes(cls):
        return cls.__coleccionesExistentes
    
    @classmethod
    def setColeccionesExistentes(cls,coleccionesExistentes):
        cls.__coleccionesExistentes=coleccionesExistentes
    
    def agregarLista(self,lista):
        self.__listas.append(lista)
        return "Se ha agregado la lista "+lista.getNombre()+" a la Colección con éxito"

    def eliminarLista(self,lista):
        posicion=self.__listas.index(lista)
        self.__listas.pop(posicion)
        return "Se ha eliminado la lista "+lista.getNombre()+" de la Colección con éxito"
    
    def cancionesUsuarios(self):
        cancionesUsuario=[]
        for lista in self.__listas:
            for cancion in lista.getLista():
                if cancion not in cancionesUsuario:
                    cancionesUsuario.append(cancion)
        
        return cancionesUsuario
    
    def __str__(self):
        des=""
        for lista in self.__listas:
            des+=lista.infoLista()+"\n"
        
        return des
    
    def puntosExtras(self, listas):
        REGGAETON=0
        ROCK=0
        POP=0
        SALSA=0
        KPOP=0
        NO_ESPECIFICADO=0
        PuntosExtras=[]
        for filas in listas:
            for cancion in filas.getLista():
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
        PuntosExtras.append(0.25*REGGAETON)
        PuntosExtras.append(0.25*ROCK)
        PuntosExtras.append(0.25*SALSA)
        PuntosExtras.append(0.25*POP)
        PuntosExtras.append(0.25*KPOP)
        PuntosExtras.append(0.25*NO_ESPECIFICADO)

        return PuntosExtras
           
    def agregarListaColaborativa(self,lista):
        self.__usuario.getColeccion(self).agregarColaborativa(lista)
        return "Lista colaborativa creada"

    def agregarColaborativa(self,lista):
        self.__colaborativas.append(lista)
    
    def similitudesGenero(self,listas):
        totalGenero=0
        REGGAETON=0
        ROCK=0
        POP=0
        SALSA=0
        KPOP=0
		
        for i in listas:
            if i==None:
                listas.remove(i)
                
        for i in range(len(listas)):
            for j in range(len(listas)-1):
                if listas[i].getGenero()==listas[j+1].getGenero():
                    totalGenero+=1
                    if listas[i].getGenero()==Genero.REGGAETON:
                        REGGAETON+=1
                    elif listas[i].getGenero()==Genero.ROCK:
                        ROCK+=1
                    elif listas[i].getGenero()==Genero.POP:
                        POP+=1
                    elif listas[i].getGenero()==Genero.SALSA:
                        SALSA+=1
                    elif listas[i].getGenero()==Genero.KPOP:
                        KPOP+=1
        if len(listas)>0:
            proporcion=(totalGenero/len(listas))*100
            if proporcion>50: #Se espera que los usuarios se parezcan en un 50%
               return  "Sus generos coinciden en mas de un 80%"
            else:
                return "La coincidencia en sus géneros es muy baja"
        else:
            return "La Lista Colaborativa está vacía"
            