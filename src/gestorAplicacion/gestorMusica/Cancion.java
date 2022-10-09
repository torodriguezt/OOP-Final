package gestorAplicacion.gestorMusica;

import java.util.ArrayList;
import gestorAplicacion.gestorPersonas.Artista;

public class Cancion {
	
	private static ArrayList<Cancion> cancionesExistentes;
	private String nombre;
	private Artista artista;
	private Genero genero;
	private int duracion; // Duracion en segundos, para facilitar los calculos
	private int reproducciones;
	
	static {
		cancionesExistentes = new ArrayList<>();
	}
	
	public Cancion(String nombre, Artista artista, Genero genero, int duracion) {
		this.nombre = nombre;
		this.artista = artista;
		this.genero = genero;
		this.duracion = duracion;
		cancionesExistentes.add(this);
		
	}
		
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Artista getArtistas() {
		return artista;
	}
	
	public void setArtistas(Artista artista) {
		this.artista = artista;
	}
		
	public Genero getGenero() {
		return genero;
	}
	
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	public int getDuracion() {
		return duracion;
	}
	
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	public int getReproducciones() {
		return reproducciones;
	}
	
	public void setReproducciones(int reproducciones) {
		this.reproducciones = reproducciones;
	}
	
	public static ArrayList<Cancion> getCancionesExistentes() {
		return cancionesExistentes;
	}
	
	public static void setCancionesExistentes(ArrayList<Cancion> cancionesExistentes) {
		Cancion.cancionesExistentes = cancionesExistentes;
	}

	public String toString() {
		return "Se está reproduciendo la canción " + nombre;
	}
	
	public void aumentarReproducciones(){
		reproducciones++; 
		}
	
}