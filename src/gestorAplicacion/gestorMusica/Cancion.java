package gestorAplicacion.gestorMusica;

import java.util.ArrayList;
import gestorAplicacion.gestorPersonas.Artista;

public class Cancion {
	
	private static ArrayList<Cancion> cancionesExistentes;
	private Artista artista;
	private Genero genero;
	private int duracion; // Duracion en segundos, para facilitar los calculos
	private int ano;
	
	static {
		cancionesExistentes = new ArrayList<>();
	}
	
	public Cancion(String nombre, Artista artista, Genero genero, int duracion, int ano) {
		super(nombre);
		this.artista = artista;
		this.genero = genero;
		this.duracion = duracion;
		this.ano=ano;
		cancionesExistentes.add(this);
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
	
	public int getAno(){
		return ano;
	}
	public void setAno(int ano){
		this.ano=ano;
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
		this.reproducciones++; 
		}
	
}
