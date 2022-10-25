package gestorAplicacion.gestorMusica;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

import gestorAplicacion.gestorPersonas.*;

public class Lista extends Musica implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static ArrayList<Lista> listasExistentes;
	private ArrayList<Cancion> lista;
	private Set<Cancion> listaColaborativa;
	protected ArrayList<Usuario> usuarios;
	protected Usuario usuario;
	private String descripcion;
	
	static {
		setListasExistentes(new ArrayList<Lista>());
	}
	
	public Lista(String nombre, Usuario usuario, String descripcion) {
		super(nombre);
		lista = new ArrayList<>();
		this.usuario = usuario;
		this.descripcion = descripcion;
		this.usuario.getColeccion().agregarLista(this);
		listasExistentes.add(this);
	}
	
	public Lista(String nombre, ArrayList<Cancion> lista, Usuario usuario, String descripcion) {
		this(nombre, usuario, descripcion);
		this.lista = lista;
	}
	
	public Lista(String nombre, ArrayList<Usuario> usuarios, Set<Cancion> canciones) {
		super(nombre);
		this.usuarios = usuarios;
		this.listaColaborativa = canciones;
		//ver como afecta que en el constructor solo reciba usuarios invidivuales en el caso de tener arrays 
		this.colaborativa(usuarios);
	}
	
	
	public ArrayList<Cancion> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Cancion> lista) {
		this.lista = lista;
	}

	public Set<Cancion> getListaColaborativa() {
		return listaColaborativa;
	}

	public void setListaColaborativa(Set<Cancion> listaColaborativa) {
		this.listaColaborativa = listaColaborativa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public static ArrayList<Lista> getListasExistentes() {
		return listasExistentes;
	}

	public static void setListasExistentes(ArrayList<Lista> listasExistentes) {
		Lista.listasExistentes = listasExistentes;
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String agregarCancion(Cancion cancion) {
		lista.add(cancion);
		return "Se ha agregado la canción "+cancion.getNombre()+" a la lista "+nombre+" con exito";
	}
	
	public String eliminarCancion(Cancion cancion) {
		lista.remove(cancion);
		return "Se ha eliminado la canción "+cancion.getNombre()+" de la lista "+nombre+"con exito";
	}
	
	@Override
	public String toString() {
		return "Se está reproduciendo la lista " + nombre;
	}
	
	public String infoLista() {
		String des = "";
		if (lista.size() > 0) {
			for (Cancion cancion : lista) {
				des += cancion.descripcion() + "\n";
			}
			return "Lista: " + nombre + "\n" + "\n" + "Canciones: " + "\n" + des;
		} else {
			return "La lista: " + nombre + " está vacía\n";
		}
	}
	
	public String infoColaborativa() {
		String des = "";
		if (listaColaborativa.size() > 0) {
			for (Cancion cancion : listaColaborativa) {
				des += cancion.descripcion() + "\n";
			}
			return "Lista: " + nombre + "\n" + "\n" + "Canciones: " + "\n" + des;
		} else {
			return "La lista: " + nombre + " está vacía\n";
		}
	}

	@Override
	public void aumentarReproducciones(){
		this.reproducciones++; 
		for (int i = 0; i < lista.size(); i++) {
			lista.get(i).aumentarReproducciones();
		}
	}
	
	public String colaborativa(ArrayList<Usuario> usuarios) { //Para que se asigne individualmente a cada usuario
		for(Usuario persona: usuarios) {
			persona.getColeccion().agregarLista(this, persona);
		}
		return "Lista Colaborativa creada";
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public static ArrayList<Cancion> listaPorGenero(Genero genero){
		ArrayList<Cancion> cancionGenero = new ArrayList<Cancion>();
		
		for(Cancion existentes: Cancion.getCancionesDisponibles()) {
			if(existentes.getGenero().equals(genero)) {
				cancionGenero.add(existentes);
			}
		} return cancionGenero;
	}
		
	public int totalPorGenero(Genero genero) {
		
		int total = 0;
		for(Cancion cancion: getLista()) {
			if(cancion.getGenero().equals(genero)) {
				total += 1;
			}
		}
		return total;
	}
}

