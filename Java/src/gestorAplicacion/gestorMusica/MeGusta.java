package gestorAplicacion.gestorMusica;

import java.io.Serializable;
import java.util.ArrayList;
import gestorAplicacion.gestorPersonas.Usuario;

public class MeGusta extends Lista implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static ArrayList<MeGusta> favoritosExistentes;
	private ArrayList<Cancion> favoritos = new ArrayList<Cancion>();
		
	static {
		setFavoritosExistentes(new ArrayList<MeGusta>());
	}
	
	public MeGusta(Usuario usuario){
		super("Tus Me Gusta", usuario, "Tus canciones favoritas");
		favoritosExistentes.add(this);
	}
	
	@Override
	public String agregarCancion(Cancion cancion) {
		favoritos.add(cancion);
		return "Cancion agregada con exito a tus Me Gusta";
	}
	
	@Override
	public String eliminarCancion(Cancion cancion) {
		favoritos.remove(cancion);
		return "Cancion eliminada con exito de tus Me Gusta";
	}

	public ArrayList<Cancion> getFavoritos() {
		return favoritos;
	}
	
	public void setFavoritos(ArrayList<Cancion> favoritos) {
		this.favoritos = favoritos;
	}

	public static ArrayList<MeGusta> getFavoritosExistentes() {
		return favoritosExistentes;
	}

	public static void setFavoritosExistentes(ArrayList<MeGusta> favoritosExistentes) {
		MeGusta.favoritosExistentes = favoritosExistentes;
	}

	@Override
	public int totalPorGenero(Genero genero) {	
		int total = 0;
		for(Cancion cancion: getFavoritos()) {
			if(cancion.getGenero().equals(genero)) {
				total += 1;
			}
		}
		return total;
	}
	
	@Override
	public String toString() {
		String des = "";
		ArrayList<Cancion> lista = usuario.getFavoritos().getFavoritos();
		if (lista.size() > 0) {
			for (Cancion cancion : lista) {
				des += cancion.descripcion() + "\n";
			}
			return "Tus Canciones Favoritas:" + "\n" + "\n" + "Canciones: " + "\n" + des;
		} else {
			return "No tienes canciones favoritas";
		}
	}
}