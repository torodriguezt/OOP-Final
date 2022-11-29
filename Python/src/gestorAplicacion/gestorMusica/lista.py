from gestorAplicacion.gestorMusica.musica import Musica
from gestorAplicacion.gestorMusica.cancion import Cancion
from gestorAplicacion.gestorMusica.genero import Genero

''' Módulo lista.py
    Autores: Carolina Álvarez Murillo, Miller Johan Chica Acero,
             Tomás Rodríguez Taborda, Jerónimo Ledesma Patiño,
             Catalina Restrepo Salgado
    Este módulo contiene la clase Lista, que hereda de la clase Música
'''

class Lista(Musica):

    __listasExistentes = [] #List[Lista]

    def __init__(self, str_nombre, Usuario_usuario = None, str_descripcion = "", list_lista: list = [], list_usuarios = [], set_listaColaborativa = {}):
        super().__init__(str_nombre)
        self.__usuario = Usuario_usuario
        self.__descripcion = str_descripcion
        self.__lista = list_lista
        self.usuarios = list_usuarios
        self.__listaColaborativa = set_listaColaborativa
        Lista.__listasExistentes.append(self)
    
    def duracionLista(self):
        duracion = 0
        for i in self.__lista:
            duracion += i.getDuracion()
        return duracion

    def getLista(self):
        return self.__lista #list
    
    def setLista(self, list_lista):
        self.__lista = list_lista

    def getListaColaborativa(self):
        return self.__listaColaborativa #list

    def setListaColaborativa(self, list_listaColaborativa):
        self.__listaColaborativa = list_listaColaborativa

    def getUsuario(self):
        return self.__usuario 

    def setUsuario(self, Usuario_usuario):
        self.__usuario = Usuario_usuario
    
    @classmethod
    def getListasExistentes(cls):
        return cls.__listasExistentes #list
    
    @classmethod
    def setListasExistentes(cls, list_listasExistentes):
        cls.__listasExistentes = list_listasExistentes
    
    def getUsuarios(self):
        return self.usuarios #list
    
    def setUsuarios(self, list_usuarios):
        self.usuarios = list_usuarios
    
    def agregarCancion(self, Cancion_cancion):
        if Cancion_cancion in self.__lista:
            return f"La canción {Cancion_cancion.getNombre()} ya está dentro de la lista"
        else:
            self.__lista.append(Cancion_cancion)
            return "Se ha agregado la canción " + Cancion_cancion.getNombre() + " a la lista " + self.getNombre() + " con éxito"
        
    def eliminarCancion(self, Cancion_cancion):
        if Cancion_cancion in self.__lista:
            self.__lista.remove(Cancion_cancion)
            return "Se ha eliminado la canción " + Cancion_cancion.getNombre() + " de la lista " + self.getNombre() + " con éxito"      
        else:
            return f"La canción {Cancion_cancion.getNombre()} no está dentro de la lista"

    def __str__(self):
        return "Se está reproduciendo la lista: " + self.getNombre()
    
    def infoLista(self):
        des = ""
        if len(self.__lista) > 0:
            for i in self.__lista:
                des += i.descripcion() + "\n\n"
            return "Lista: " + self.getNombre() + "\n" + "\n" + "Canciones: " + "\n" + des
        else:  
            return "La lista: " + self.getNombre() + " está vacía\n"
        
    def infoListaColaborativa(self):
        des = ""
        if len(self.__listaColaborativa) > 0:
            for i in self.__listaColaborativa:
                des += i.descripcion() + "\n\n"
            return "Lista: " + self.getNombre() + "\n" + "\n" + "Canciones: " + "\n" + des
        else:  
            return "La lista: " + self.getNombre() + " está vacía\n"

    def infoColaborativa(self):
        des = ""
        if len(self.__listaColaborativa) > 0:
            for cancion in self.__listaColaborativa:
                des += cancion.descripcion() + "\n"
            return "Lista: " + self.getNombre() + "\n" + "\n" + "Canciones: " + "\n" + des
        else:
            return "Lista: " + self.getNombre() + " está vacía"
        
    def aumentarReproducciones(self):
        self.setReproducciones(self.getReproducciones() + 1)
        for cancion in self.__lista:
            cancion.aumentarReproducciones()
    
    def colaborativa(self, list_Usuario):
        for usuario in list_Usuario:
            usuario.getColeccion().agregarLista(self, usuario)
        return "Lista colaborativa creada"
    
    def getDescripcion(self):
        return self.__descripcion #str
    
    def setDescripcion(self, str_descripcion):
        self.__descripcion = str_descripcion
    
    @classmethod
    def listaPorGenero(cls, Genero):
        lista = []
        for cancion in Cancion.getCancionesDisponibles():
            if cancion.getGenero() == Genero:
                lista.append(cancion)
        return lista

    def totalPorGenero(self, Genero_genero):
        total = 0
        for cancion in self.getLista():
            if cancion.getGenero() == Genero_genero:
                total += 1 
        return total
