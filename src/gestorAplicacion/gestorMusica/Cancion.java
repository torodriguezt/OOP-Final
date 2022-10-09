package gestorAplicacion.gestorMusica;

import java.util.ArrayList;
import gestorAplicacion.gestorPersonas.Artista;

public class Cancion {
	
	private static ArrayList<Cancion> cancionesExistentes;
	private String nombre;
	private Artista artista;
	private ArrayList<Artista> listaArtistas = null;
	private Genero genero;
	private int duracion; // Duracion en segundos, para facilitar los calculos
	private int reproducciones;
	
	static {
		cancionesExistentes = new ArrayList<>();
	}
	
	// Este constructor está sobrecargado: Una canción puede tener un solo artista
	public Cancion(String nombre, Artista artista, Genero genero, int duracion) {
		this.nombre = nombre;
		this.artista = artista;
		this.genero = genero;
		this.duracion = duracion;
		cancionesExistentes.add(this);
		
	}
	
	// Este constructor está sobrecargado: Una canción puede tener varios artistas
		public Cancion(String nombre, ArrayList<Artista> listaArtistas, Genero genero, int duracion) {
			this.nombre = nombre;
			this.listaArtistas = listaArtistas;
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
	
	public ArrayList<Artista> getListaArtistas() {
		return listaArtistas;
	}

	public void setListaArtistas(ArrayList<Artista> listaArtistas) {
		this.listaArtistas = listaArtistas;
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
		
		String nombreA = "";
		int minutos = duracion / 60;
		int segundos = duracion % 60;
		
		if (listaArtistas != null){
			int tam = listaArtistas.size();
			for (int i = 0; i < tam - 1; i++) {
				nombreA += listaArtistas.get(i).getNombre() + ", ";
			}
			nombreA += listaArtistas.get(tam - 1);
		} else {
			nombreA = artista.getNombre();
		}
		
		return "Nombre: " + nombre + "\n" + 
			   "Artista(s): " + nombreA + "\n" +
			   "Género: " + genero + "\n" +
			   "Duración: " + minutos + ":" + segundos;
	}
	
	
}
