package gestorAplicacion.gestorMusica;

import java.util.ArrayList;

public class Coleccion {
	
	private String nombre;
	private ArrayList<Lista> playlist= new ArrayList<Lista>();
	
	
	public Coleccion(String nombre, ArrayList<Lista> playlist) {
		this.nombre=nombre;
		for (int i=0;i<playlist.size();i++) {
			 this.playlist.add(playlist.get(i));
		}
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public ArrayList<Lista> getPlaylist() {
		return playlist;
	}


	public void setPlaylist(ArrayList<Lista> playlist) {
		this.playlist = playlist;
	}
	
	public String agregarLista(Lista lista) {
		playlist.add(lista);
		return "Se ha agregado la lista "+lista.getNombre()+" a la biblioteca "+nombre+" con exito";
	}
	
	public String eliminarLista(Lista lista) {
		int A= playlist.indexOf(lista);
		playlist.remove(A);
		return "Se ha eliminado la lista "+lista.getNombre()+" de la biblioteca "+nombre+" con exito";
	}
	// funcion compleja que retorna el tiempo total de lo escuchado por el artista de interes
	public String tiempoArtista(Artista artista) {
		int tiempo=0;
		for (int i=0;i<playlist.size();i++) {
			ArrayList<Cancion> canciones=playlist.get(i).getListas();
			for (int p=0;p<canciones.size();p++) {
				Cancion musica=canciones.get(p);
				if (musica.getArtistas()==artista) {
					tiempo+=musica.getDuracion()*musica.getReproducciones();		
				}
			}	
				
		}	
		return"El tiempo que has escuchado este "+artista.getNombre()+" es"+tiempo;			
	}
	
}



