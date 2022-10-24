package gestorAplicacion.gestorMusica;
import java.io.Serializable;
import java.util.ArrayList;
import gestorAplicacion.gestorPersonas.Artista;
import gestorAplicacion.gestorPersonas.Usuario;

public class Coleccion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static ArrayList<Coleccion> coleccionesExistentes;
	private Usuario usuario;
	private ArrayList<Lista> listas = new ArrayList<Lista>();
	private ArrayList<Lista> cancionesRecomendadas = new ArrayList<Lista>();
	private ArrayList<Lista> colaborativas = new ArrayList<Lista>();
		
	static {
		coleccionesExistentes = new ArrayList<Coleccion>();
	}
	
	public Coleccion(Usuario usuario) {
		this.usuario = usuario;
		usuario.setColeccion(this);
		coleccionesExistentes.add(this);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ArrayList<Lista> getListas() {
		return listas;
	}

	public void setListas(ArrayList<Lista> listas) {
		this.listas = listas;
	}
	
	public static ArrayList<Coleccion> getColeccionesExistentes() {
		return coleccionesExistentes;
	}

	public static void setColeccionesExistentes(ArrayList<Coleccion> coleccionesExistentes) {
		Coleccion.coleccionesExistentes = coleccionesExistentes;
	}
	
	public String agregarLista(Lista lista) {
		listas.add(lista);
		return "Se ha agregado la lista "+lista.getNombre()+" a la Colección con éxito";
	}
	
	public String eliminarLista(Lista lista) {
		int A = listas.indexOf(lista);
		listas.remove(A);
		return "Se ha eliminado la lista "+lista.getNombre()+" de la Colección con éxito";
	}
	
	public String tiempoArtista(Artista artista) {
		int tiempo = 0;
		for (int i = 0; i < listas.size(); i++) {
			ArrayList<Cancion> canciones = listas.get(i).getLista();
			for (int p = 0; p < canciones.size(); p++) {
				Cancion musica = canciones.get(p);
				if (musica.getArtista() == artista) {
					tiempo += musica.getDuracion()*musica.getReproducciones();		
				}
			}			
		}	
		return"El tiempo que has escuchado este "+artista.getNombre()+" es"+tiempo;			
	}

	public ArrayList<Cancion> cancionesUsuario(){

		ArrayList<Cancion> cancionesUsuario = new ArrayList<Cancion>();
		for (Lista lista : this.listas) {
			for (Cancion cancion : lista.getLista()) {
				if(!cancionesUsuario.contains(cancion)){
					cancionesUsuario.add(cancion);
				}				
			}
		}
		return cancionesUsuario;
	}

	@Override
	public String toString(){
		String des = "";
		for (Lista lista : listas) {
			des += lista.infoLista() + "\n";	
		}
		return des;
	}
	
	public  ArrayList<Double> PuntosExtras(ArrayList<Lista> listas){
		double REGGAETON=0;
		double ROCK=0;
		double POP=0;
		double SALSA=0;
		double Kpop=0; 
		double NO_ESPECIFICADO=0;
		ArrayList<Double> PuntosExtras = new ArrayList<Double>();
		for(Lista filas:listas) {			
			for(Cancion cancion:filas.getLista()) {
				if (cancion.getGenero().equals(Genero.REGGAETON)){
					REGGAETON++;
				}
				else if(cancion.getGenero().equals(Genero.ROCK)){
					ROCK++;

				}
				else if(cancion.getGenero().equals(Genero.SALSA)){
					SALSA++;

				}
				else if(cancion.getGenero().equals(Genero.POP)){
					POP++;
				}
				else if(cancion.getGenero().equals(Genero.KPOP)){
					Kpop++;
				}
				else {
					NO_ESPECIFICADO++;
				}	
			}	
		}
	PuntosExtras.add(0.25*REGGAETON); PuntosExtras.add(0.25*ROCK); PuntosExtras.add(0.25*POP); 
	PuntosExtras.add(0.25*SALSA); PuntosExtras.add(0.25*Kpop); PuntosExtras.add(0.25* NO_ESPECIFICADO);
	return PuntosExtras;
	}
	
	//Metodo sobrecargado para crear la colaborativa a cada usuario
	public String agregarLista(Lista lista, Usuario usuario) {
		usuario.getColeccion().agregarColaborativa(lista);
		return "Lista colaborariva creada";
	}
		
	public void agregarColaborativa(Lista lista) {
		colaborativas.add(lista);
	}
		
	// Recibe listas de los usuarios que forman la colaborativa
	public String similitudesCancion(ArrayList<Cancion> listas) {
		int totalCanciones = 0;
			
		for (int i= 0; i<listas.size();i++) {
			for(int j=0; i<listas.size()-1;i++) {
				if (listas.get(i) == listas.get(j+1)) { //Compara si el objeto es el mismo
					totalCanciones = totalCanciones+1;
				}
			}
		}
		
		if(listas.size() > 0) {
			int proporcion = (totalCanciones/listas.size())*100;
			if (proporcion > 80) { //numero elegido al azar para probar
				return "Sus gustos coinciden en más de un 80%";
			}
			else {
				return "La coincidencia en sus gustos es muy baja";
			}
		} else {
			return "La Lista Colaborativa está vacía";
		}
	}
		
	public String similitudesGenero(ArrayList<Cancion> listas) {
		int totalGenero = 0;
		int REGGAETON=0;
		double ROCK=0;
		double POP=0;
		double SALSA=0;
		double Kpop=0; 
		
		for (int i= 0; i<listas.size();i++) {
			for(int j=0; i<listas.size()-1;i++) {
				if (listas.get(i).getGenero() == listas.get(j+1).getGenero()) { //Compara si el objeto es el mismo
					totalGenero = totalGenero+1;
					if(listas.get(i).getGenero().equals(Genero.REGGAETON)){
						REGGAETON = REGGAETON + 1;
					}
					else if(listas.get(i).getGenero().equals(Genero.ROCK)){
						ROCK = ROCK + 1;
					}
					else if(listas.get(i).getGenero().equals(Genero.POP)){
						POP = POP + 1 ;
					}
					else if(listas.get(i).getGenero().equals(Genero.SALSA)){
						SALSA = SALSA + 1 ;
					}
					else if(listas.get(i).getGenero().equals(Genero.KPOP)){
						Kpop = Kpop + 1 ;
					}
				}
			}
		}
		
		if(listas.size() > 0) {
			int proporcion = (totalGenero/listas.size())*100;
			if (proporcion > 50) { // Se espera que los usuarios se parezcan en un 50%
				return "Sus generos coinciden en mas de un 80%";
			}
			else {
				return "La coincidencia en sus géneros es muy baja";
			}
		} else {
			return "La Lista Colaborativa está vacía";
		}
		
	}
}

