package gestorAplicacion.gestorMusica;

import java.io.Serializable;
import java.util.ArrayList;
import gestorAplicacion.gestorPersonas.Artista;

public class Cancion extends Musica implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static ArrayList<Cancion> cancionesDisponibles;
	private Artista artista;
	private Genero genero;
	private int duracion; // Duracion en segundos, para facilitar los calculos
	private int ano;
	
	static {
		cancionesDisponibles = new ArrayList<>();
	}
	
	public Cancion(String nombre, Artista artista, Genero genero, int duracion, int ano) {
		super(nombre);
		this.artista = artista;
		this.genero = genero;
		this.duracion = duracion;
		this.ano = ano;
		cancionesDisponibles.add(this);
		// Para agregar la canción al portafolio del artista
		artista.agregarCancion(this);
	}
	
	public Cancion(String nombre, Artista artista, int duracion, int ano) {
		this(nombre, artista, Genero.NO_ESPECIFICADO, duracion, ano);
	}
	
	public Artista getArtista() {
		return artista;
	}
	
	public void setArtista(Artista artista) {
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
		this.ano = ano;
	}
	
	public static ArrayList<Cancion> getCancionesDisponibles() {
		return cancionesDisponibles;
	}
	
	public static void setCancionesDisponibles(ArrayList<Cancion> cancionesDisponibles) {
		Cancion.cancionesDisponibles = cancionesDisponibles;
	}

	@Override
	public String toString() {
		return "Se está reproduciendo la canción " + nombre;
	}
	
	public String descripcion() {
		return nombre + " - " + this.getArtista().getNombre();
	}
	
	@Override
	public void aumentarReproducciones(){
		this.reproducciones = this.reproducciones + 1; 
	}
	
	public static Cancion topCancion() {
	   	 Cancion masEscuchada = null;
	   	 int mayor = 0;
	   	 for (Cancion cancion: Cancion.getCancionesDisponibles()) {
	   		 if (cancion.getReproducciones()>mayor) {
	   			 masEscuchada=cancion;
	   			 mayor=cancion.getReproducciones();
	   		 }
	   	 }
	   	 return masEscuchada;
	   }
		
}
