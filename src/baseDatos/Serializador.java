package baseDatos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import gestorAplicacion.gestorMusica.Cancion;
import gestorAplicacion.gestorMusica.Coleccion;
import gestorAplicacion.gestorMusica.Lista;
import gestorAplicacion.gestorMusica.MeGusta;
import gestorAplicacion.gestorPersonas.Artista;
import gestorAplicacion.gestorPersonas.Usuario;

public class Serializador {
	
	public static <E> void serializar(ArrayList<E> lista, String nombre) {
		
		FileOutputStream fileOut;

		try {
			String path = System.getProperty("user.dir") + "/src/baseDatos/temp/" + nombre + ".txt";
			fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(lista);
			// System.out.println("Se guardó correctamente en " + nombre);
			out.close();
			fileOut.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void serializarDatos() {
		Serializador.serializar(Usuario.getUsuariosExistentes(), "Usuarios");
		Serializador.serializar(Artista.getArtistasDisponibles(), "Artistas");
		Serializador.serializar(Cancion.getCancionesDisponibles(), "Canciones");
		Serializador.serializar(Coleccion.getColeccionesExistentes(), "Colecciones");
		Serializador.serializar(Lista.getListasExistentes(), "Listas");
		Serializador.serializar(MeGusta.getFavoritosExistentes(), "MeGusta");
		
	}
}