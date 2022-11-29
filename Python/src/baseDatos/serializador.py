''' Módulo serializador.py
    Autores: Carolina Álvarez Murillo, Miller Johan Chica Acero,
             Tomás Rodríguez Taborda, Jerónimo Ledesma Patiño,
             Catalina Restrepo Salgado
    Este módulo contiene la clase Serializador, que nos va a permitir guardar en cada archivo .txt un 
    flujo de bytes correspondiente a los objetos que se han creado durante la ejecución del programa
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

class Serializador:

    def serializar(lista, nombre):
        ruta = os.path.join(pathlib.Path(__file__).parent.absolute(), "temp/" + nombre + ".txt")
        
        try:
            picklefile = open(ruta, 'wb')
            pickle.dump(lista, picklefile)
            picklefile.close()
            
        except:
            print("¡Ocurrió un error!")

    def serializarDatos():
        Serializador.serializar(Usuario.getUsuariosExistentes(), "Usuarios")
        Serializador.serializar(Artista.getArtistasDisponibles(), "Artistas")
        Serializador.serializar(Cancion.getCancionesDisponibles(), "Canciones")
        Serializador.serializar(Coleccion.getColeccionesExistentes(), "Colecciones")
        Serializador.serializar(Lista.getListasExistentes(), "Listas")
        Serializador.serializar(meGusta.getFavoritosExistentes(), "MeGusta")
        