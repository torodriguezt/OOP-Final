''' Módulo deserializador.py
    Autores: Carolina Álvarez Murillo, Miller Johan Chica Acero,
             Tomás Rodríguez Taborda, Jerónimo Ledesma Patiño,
             Catalina Restrepo Salgado
    Este módulo contiene la clase Deserializador, que nos va a permitir acceder al flujo de bytes que se
    haya guardado previamente en cada archivo .txt
'''

import pickle
import pathlib
import os

from gestorAplicacion.gestorMusica.cancion import Cancion
from gestorAplicacion.gestorMusica.coleccion import Coleccion
from gestorAplicacion.gestorMusica.lista import Lista
from gestorAplicacion.gestorMusica.meGusta import meGusta
from gestorAplicacion.gestorPersonas.artista import Artista
from gestorAplicacion.gestorPersonas.usuario import Usuario

class Deserializador:

    def deserializar(lista, nombre):
        ruta = os.path.join(pathlib.Path(__file__).parent.absolute(), "temp/" + nombre + ".txt")

        try:
            picklefile = open(ruta, 'rb')

        except:
            picklefile = open(ruta, 'x')
            picklefile = open(ruta, 'rb')

        if os.path.getsize(ruta) > 0:
            lista = pickle.load(picklefile)
        picklefile.close()

        return lista

    def deserializarDatos():
        Usuario.setUsuariosExistentes(Deserializador.deserializar(Usuario.getUsuariosExistentes(), "Usuarios"))
        Artista.setArtistasDisponibles(Deserializador.deserializar(Artista.getArtistasDisponibles(), "Artistas"))
        Cancion.setCancionesDisponibles(Deserializador.deserializar(Cancion.getCancionesDisponibles(), "Canciones"))
        Coleccion.setColeccionesExistentes(Deserializador.deserializar(Coleccion.getColeccionesExistentes(), "Colecciones"))
        Lista.setListasExistentes(Deserializador.deserializar(Lista.getListasExistentes(), "Listas"))
        meGusta.setFavoritosExistentes(Deserializador.deserializar(meGusta.getFavoritosExistentes(), "MeGusta")) 
        