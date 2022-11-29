from gestorAplicacion.gestorMusica.lista import Lista

class meGusta(Lista):
    
    __favoritosExistentes = []
    
    #falta inicializador estatico
    
    def __init__(self, usuario):
        super().__init__("Tus Me Gusta", usuario, "Tus canciones favoritos")
        self.__favoritos = []
        meGusta.__favoritosExistentes.append(self)
        
    def agregarCancion(self, cancion):
        if cancion in self.__favoritos:
            return f"La canción {cancion.getNombre()} ya está dentro de tus favoritos"
        else:
            self.__favoritos.append(cancion)
            return "Se ha agregado la canción " + cancion.getNombre() + " a tus favoritos con éxito"
    
    def eliminarCancion(self, cancion):
        self.__favoritos.remove(cancion)
        return "Canción eliminada con éxito de tus Me Gusta"
    
    def getFavoritos(self):
        return self.__favoritos
    
    def setFavoritos(self, favoritos):
        self.__favoritos = favoritos
    
    @classmethod
    def getFavoritosExistentes(cls):
        return cls.__favoritosExistentes
    
    @classmethod
    def setFavoritosExistentes(cls, favoritosExistentes):
        cls.__favoritosExistentes = favoritosExistentes
        
    def totalPorGenero(self, genero):
        total = 0
        for cancion in meGusta.getFavoritos(self):
            if(cancion.getGenero() == genero):
                total += 1
        return total
    
    def __str__(self):
        des = ""
        if(len(self.__favoritos) > 0):
            for cancion in self.__favoritos:
                des += cancion.descripcion() + "\n\n"
            return "Tus Canciones Favoritas:" + "\n" + "\n" + "Canciones: " + "\n" + des
        else:
            return "No tienes canciones favoritas"