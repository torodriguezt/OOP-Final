package gestorAplicacion.gestorPersonas;

import java.util.ArrayList;
import gestorAplicacion.gestorMusica.*;

public class Artista extends Persona {
	private String nombre;
	private ArrayList<Cancion> canciones= new ArrayList<Cancion>();
	private Genero genero;
	
	public Artista(String nombre, ArrayList<Cancion> canciones, Genero genero) {
		//Modificar clase abstract
		super(nombre);
		//this.nombre = nombre;
		this.canciones = canciones;
		this.genero = genero;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Cancion> getCanciones() {
		return canciones;
	}
	public void setCanciones(ArrayList<Cancion> canciones) {
		this.canciones = canciones;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	public String agregarCancion(Cancion cancion) {
		canciones.add(cancion);
		return "La cancion " + cancion.getNombre() + "fue agregada existosamente";
	}
	
	public String eliminarCancion(Cancion cancion) {
		canciones.remove(cancion);
		return "La cancion " + cancion.getNombre() + "fue eliminada existosamente";
	}
	
	public String toString() {
		return nombre + "es un artista con " + canciones.size() + "publicadas en Spotifree pertenecientes al genero de " 
				+ genero;
	}
	
	public String estadisticas(ArrayList<Cancion> canciones) {
		
		Cancion masDuracion = null;
		Cancion menosDuracion = null;
		int sumaTotal = 0;
		
		for(int i = 0; i<canciones.size(); i++) {
			canciones.get(i).getDuracion();
			if (masDuracion == null || masDuracion.getDuracion()<canciones.get(i).getDuracion())
				masDuracion = canciones.get(i);
		}
		for(int i = 0; i<canciones.size(); i++) {
			canciones.get(i).getDuracion();
			if (menosDuracion == null || menosDuracion.getDuracion()>canciones.get(i).getDuracion())
				menosDuracion = canciones.get(i);
		}	
		for(int i = 0; i<canciones.size(); i++) {
			sumaTotal = sumaTotal + canciones.get(i).getDuracion();
		}	
		int promedio = (sumaTotal/canciones.size());
		
		return "La cancion mas larga de " + nombre + "es: " +  masDuracion.getNombre() + "\n" +
				"La canciones mas corta del "+ nombre + "es: " +  menosDuracion.getNombre() + "\n" +
				"El promedio de las canciones de " + nombre + "es: " + promedio;
		
	}
	
	
		
}
