package gestorAplicacion.gestorPersonas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Random;
import java.util.Set;

import gestorAplicacion.gestorMusica.Cancion;
import gestorAplicacion.gestorMusica.Coleccion;
import gestorAplicacion.gestorMusica.Genero;
import gestorAplicacion.gestorMusica.Lista;
import gestorAplicacion.gestorMusica.MeGusta;

public class Usuario implements Persona, Serializable {

	private static final long serialVersionUID = 1L;
	private static ArrayList<Usuario> usuariosExistentes = new ArrayList<Usuario>(); 
    private Coleccion coleccion;
    private int tiempoEscuchado;
    private String nombre;
    private final MeGusta favoritos;
    private Genero genFavorito;
    
    public Usuario(String nombre) {
    	this(nombre,Genero.NO_ESPECIFICADO);
    }
    
    public Usuario(String nombre,Genero genFavorito){
      this.nombre = nombre;
      this.genFavorito = genFavorito;
      coleccion = new Coleccion(this);
      favoritos = new MeGusta(this);
      // Agregar a la lista general de usuarios
      usuariosExistentes.add(this);
    }
    
    public String reproducir(Cancion cancion){
      cancion.aumentarReproducciones();
      tiempoEscuchado += cancion.getDuracion();
      return cancion.toString();
    }
    
    public String agregarMeGusta(Cancion cancion) {
		return favoritos.agregarCancion(cancion);
	}
    
    public String eliminarMeGusta(Cancion cancion) {
		return favoritos.eliminarCancion(cancion);
	}
    //verificar si parametro es un objeto lista o un ArrayList
    public String reproducir(Lista lista){
        lista.aumentarReproducciones();
        tiempoEscuchado += lista.duracionLista();
        return lista.toString();
    }

    @Override
    public String toString() {
        return "Soy " + nombre;
    }
    
    public Artista topArtista() {
    	 int contadorRepro=0;
    	 ArrayList<Cancion> listaMasRepro= new ArrayList<>();
    	 Boolean verificador=false;
    	 for (Lista lista: coleccion.getListas()) {
    		 if (lista.getReproducciones()>contadorRepro) {
    			 listaMasRepro=lista.getLista();
    			 contadorRepro=lista.getReproducciones();
    			 verificador=true;
    		 }
    	 }
    	 if (verificador) {
    		 Hashtable<Artista,Integer> reproPorArtista = new Hashtable<Artista,Integer>();
    		 Artista artista=null;
    		 int cont=0;
    		 for (Cancion cancion: listaMasRepro) {
    			 if(reproPorArtista.containsKey(cancion.getArtista())) {
    				 int oldValue=reproPorArtista.get(cancion.getArtista());
    				 reproPorArtista.replace(cancion.getArtista(), oldValue, oldValue++);
    			 }else {
    				 reproPorArtista.put(cancion.getArtista(), 1);
    			 }
    			 if(reproPorArtista.get(cancion.getArtista())>cont){
					 artista=cancion.getArtista();
					 cont=reproPorArtista.get(cancion.getArtista());
				 }
    		 }
    		 return artista;
    	 }else {
    		 return null;
    	 }
    }
    
    
    public void setTiempoEscuchado(int tiempoEscuchado) {
        this.tiempoEscuchado = tiempoEscuchado;
    }
    public float getTiempoEscuchado() {
        return tiempoEscuchado;
    }

    public void setColeccion(Coleccion coleccion) {
        this.coleccion = coleccion;
    }
    public Coleccion getColeccion() {
        return coleccion;
    }

    public MeGusta getFavoritos() {
		return favoritos;
	}
    
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Genero getGenFavorito() {
		return genFavorito;
	}

	public void setGenFavorito(Genero genFavorito) {
		this.genFavorito = genFavorito;
	}

	public static ArrayList<Usuario> getUsuariosExistentes() {
		return usuariosExistentes;
	}

	public static void setUsuariosExistentes(ArrayList<Usuario> usuariosExistentes) {
		Usuario.usuariosExistentes = usuariosExistentes;
	}
	
	public static Genero genFavoritoSpotyfree() {
		int REGGAETON=0;
		int ROCK=0;
		int POP=0;
		int SALSA=0;
		int Kpop=0; 
		int NO_ESPECIFICADO=0;
		
		int mayor=0;
		Genero genero=null;
		for (Usuario usuario: Usuario.getUsuariosExistentes()) {
			if (usuario.getGenFavorito().equals(Genero.REGGAETON)){
				REGGAETON++;
				if (REGGAETON>mayor) {
					mayor=REGGAETON;
					genero=Genero.REGGAETON;
				}
			}
			else if(usuario.getGenFavorito().equals(Genero.ROCK)){
				ROCK++;
				if (ROCK>mayor) {
					mayor=ROCK;
					genero=Genero.ROCK;
				}
			}
			else if(usuario.getGenFavorito().equals(Genero.SALSA)){
				SALSA++;
				if (SALSA>mayor) {
					mayor=SALSA;
					genero=Genero.SALSA;
				}
			}
			else if(usuario.getGenFavorito().equals(Genero.POP)){
				POP++;
				if (POP>mayor) {
					mayor=POP;
					genero=Genero.POP;
				}
			}
			else if(usuario.getGenFavorito().equals(Genero.KPOP)){
				Kpop++;
				if (Kpop>mayor) {
					mayor=Kpop;
					genero=Genero.KPOP;
				}
			}
			else {
				NO_ESPECIFICADO++;
				if (NO_ESPECIFICADO>mayor) {
					mayor=NO_ESPECIFICADO;
					genero=Genero.NO_ESPECIFICADO;
				}
			}
		}
		return genero;
	}
	
	public ArrayList<Double> puntosFavoritos(Usuario usuario) {
		double REGGAETON=0;
		double ROCK=0;
		double POP=0;
		double SALSA=0;
		double Kpop=0; 
		double NO_ESPECIFICADO=0;
		ArrayList<Double> Puntos = new ArrayList<Double>();
		double total = usuario.getFavoritos().getFavoritos().size();
		
		if( usuario.getFavoritos().getFavoritos().size()==0) {
			Puntos.add(0.0);Puntos.add(0.0);Puntos.add(0.0);Puntos.add(0.0);Puntos.add(0.0);
			Puntos.add(0.0);
			
		}
			else {for (Cancion cancion: usuario.getFavoritos().getFavoritos()) {
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
			Puntos.add(REGGAETON/total*100);Puntos.add(ROCK/total*100);Puntos.add(POP/total*100);Puntos.add(SALSA/total*100);Puntos.add(Kpop/total*100);
			Puntos.add(NO_ESPECIFICADO/total*100);
	}
		return Puntos;
	}


	public Genero tuGenFavorito(Usuario usuario) {
		int REGGAETON=0;
		int ROCK=0;
		int POP=0;
		int SALSA=0;
		int Kpop=0; 
		int NO_ESPECIFICADO=0;
		
		Genero genero=null;
		int mayor = 0;
		
		for (Cancion cancion: usuario.getFavoritos().getFavoritos()) {
			if (cancion.getGenero().equals(Genero.REGGAETON)){
				REGGAETON++;
				if (REGGAETON>mayor) {
					mayor=REGGAETON;
