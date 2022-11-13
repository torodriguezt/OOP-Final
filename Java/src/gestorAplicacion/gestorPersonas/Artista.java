package gestorAplicacion.gestorPersonas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

import gestorAplicacion.gestorMusica.*;

public class Artista implements Persona, Serializable {
	
	private static final long serialVersionUID = 1L;
	private static ArrayList<Artista> artistasDisponibles;
	private String nombre;
	private ArrayList<Cancion> canciones= new ArrayList<Cancion>();
	private Genero genero;
	private Double puntaje;
	
	static {
		artistasDisponibles = new ArrayList<>();
	}

	public Artista(String nombre, Genero genero) {
		this.nombre = nombre;
		this.genero = genero;
		// Agregar a la lista general de artistas
		artistasDisponibles.add(this);
	}
	
	public Artista(String nombre) {
		this(nombre, Genero.NO_ESPECIFICADO);
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

	public Double getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(Double puntaje) {
		this.puntaje = puntaje;
	}

	public String agregarCancion(Cancion cancion) {
		canciones.add(cancion);
		return "La cancion " + cancion.getNombre() + "fue agregada existosamente";
	}
	
	public String eliminarCancion(Cancion cancion) {
		canciones.remove(cancion);
		return "La cancion " + cancion.getNombre() + "fue eliminada existosamente";
	}
	
	@Override
	public String toString() {
		return nombre + " es un artista con " + canciones.size() + " canciones publicadas en Spotifree pertenecientes al género " 
				+ genero;
	}
	
	public static ArrayList<Artista> getArtistasDisponibles() {
		return artistasDisponibles;
	}

	public static void setArtistasDisponibles(ArrayList<Artista> artistasDisponibles) {
		Artista.artistasDisponibles = artistasDisponibles;
	}

	
	public int getMeGusta(Artista artista) {
		int like=0;
		for(Usuario usuario: Usuario.getUsuariosExistentes()){
			for(Cancion cancion: usuario.getFavoritos().getFavoritos()) {
				if(cancion.getArtista().getNombre().equals(artista.getNombre())) {
					like++;
				}
			}
		}
		return like;
	}
	
	public static ArrayList<Artista> ordenarPorPuntaje(){
		artistasDisponibles.sort(Comparator.comparing(Artista::getPuntaje).thenComparing(Artista::getNombre));
	    return artistasDisponibles;
	}

	public int getReproducciones(Artista artista) {
		int reproducciones = 0;
		for(Cancion cancion: Cancion.getCancionesDisponibles()){
			if(cancion.getArtista().getNombre().equals(artista.getNombre())){
				reproducciones += cancion.getReproducciones();
			}
		}
		return reproducciones;
	}
}