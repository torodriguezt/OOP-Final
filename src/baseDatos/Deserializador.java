package baseDatos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import gestorAplicacion.gestorMusica.Cancion;
import gestorAplicacion.gestorMusica.Coleccion;
import gestorAplicacion.gestorMusica.Lista;
import gestorAplicacion.gestorMusica.MeGusta;
import gestorAplicacion.gestorPersonas.Artista;
import gestorAplicacion.gestorPersonas.Usuario;

public class Deserializador {
	
	public static <E> void deserializar(ArrayList<E> lista, String nombre) {
		
		FileInputStream fileIn;
		
		try {
			String path = System.getProperty("user.dir") + "/practica-g1-equipo-3/src/baseDatos/temp/" + nombre + ".txt";
			// System.out.println("Ruta " + path);
			fileIn = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			ArrayList<E> listaNueva = (ArrayList<E>) in.readObject();
			// System.out.println(listaNueva);
			
			for (E elemento : listaNueva) {
				lista.add(elemento);
			}
			in.close();
			fileIn.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("El archivo está vacío");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void deserializarDatos() {
		Deserializador.deserializar(Usuario.getUsuariosExistentes(), "Usuarios");
		Deserializador.deserializar(Artista.getArtistasDisponibles(), "Artistas");
		Deserializador.deserializar(Cancion.getCancionesDisponibles(), "Canciones");
		Deserializador.deserializar(Coleccion.getColeccionesExistentes(), "Colecciones");
		Deserializador.deserializar(Lista.getListasExistentes(), "Listas");
		Deserializador.deserializar(MeGusta.getFavoritosExistentes(), "MeGusta");
	}
}