package gestorAplicacion.gestorMusica;

import java.util.ArrayList;
import java.util.Comparator;
import gestorAplicacion.gestorPersonas.*;

public class Lista extends Musica {
	private ArrayList<Cancion> lista;
	private Usuario usuario;
	
	public Lista(String nombre, Usuario usuario) {
		
		
		lista=new ArrayList<>();
		this.usuario = usuario;
	}
	
	public Lista(String nombre,ArrayList<Cancion> lista, Usuario usuario) {
		
		super(nombre);
		this.lista=lista;
		this.usuario = usuario;
	}
	
	public ArrayList<Cancion> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Cancion> lista) {
		this.lista = lista;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String agregarCancion(Cancion cancion) {
		lista.add(cancion);
		return "Se ha agregado la canción "+cancion.getNombre()+" a la lista "+nombre+" con exito";
	}
	
	public String eliminarCancion(Cancion cancion) {
		lista.remove(cancion);
		return "Se ha eliminado la canción "+cancion.getNombre()+" de la lista "+nombre+"con exito";
	}
	
	public String toString() {
		return "Reproduciendo la lista "+nombre;
	}

	public void aumentarReproducciones(){
		
		this.numeroReproducciones++; 
		
		for (int i=0;i<lista.size();i++) {
			lista.get(i).aumentarReproducciones();
		}
	}
	public ArrayList<Cancion> ordenarPorNombre(Lista lista){
		lista.sort(Comparator.comparing(Cancion::getName()).thenComparing(Cancion::getArtista().getNombre()));
		return lista;
	}
	public ArrayList<Cancion> ordenarPorAno(Lista lista){
		lista.sort(Comparator.comparing(Cancion::getAno()));
		return lista;
	}
	public ArrayList<Cancion> ordenarPorReproduciones(Lista lista){
		lista.sort(Comparator.comparing(Cancion::getReproduciones()));
		return lista;
	}
}
