package gestorAplicacion.gestorPersonas;

import java.util.ArrayList;

import gestorAplicacion.gestorMusica.Cancion;
import gestorAplicacion.gestorMusica.Coleccion;
import gestorAplicacion.gestorMusica.Lista;

public class Usuario extends Persona {

    private Coleccion coleccion;
    private int tiempoEscuchado=0;
    private ArrayList <Artista> artistas;
    
    public Usuario(String nombre){
    	super(nombre);
    }
    
    public String reproducir(Cancion cancion){
        cancion.aumentarReproducciones();
        tiempoEscuchado += cancion.getDuracion();
        return cancion.toString();
    }
    
    //verificar si parametro es un objeto lista o un ArrayList
    public String reproducir(Lista lista){
        lista.aumentarReproducciones();
        tiempoEscuchado += lista.duracionLista(lista);
        return lista.toString();
    }

    @Override
    public String toString() {
        return "Soy " + nombre + " y he usado la aplicación por" + String.valueOf(tiempoEscuchado) + " segundos.";
    }
    
    public void agregarArtista(Cancion cancion) {
    	artistas.add(cancion.getArtistas());
    }
    
    //revisar donde comunicar ese artista con el usuario
    public String topArtista(ArrayList <Artista> artistas) {
    	 int vecesReproducido = 0;
    	 Artista masEscuchado = null;
    	 for (int i = 0; i < artistas.size(); i++) {
    		 int count = 0;
    	     for (int j = 0; j < artistas.size(); j++) {
    	        if (artistas.get(i) == artistas.get(j)) {
    	          count++;
    	        }
    	      }
    	      if (count > vecesReproducido) {
    	        vecesReproducido = count;
    	        masEscuchado = artistas.get(i);
    	      }
    	 }
    	    return "Tu artista mas escuchado fue " + masEscuchado.getNombre();
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

}
