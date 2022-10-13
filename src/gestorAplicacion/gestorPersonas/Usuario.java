package gestorAplicacion.gestorPersonas;

import java.util.ArrayList;

import gestorAplicacion.gestorMusica.Cancion;
import gestorAplicacion.gestorMusica.Coleccion;
import gestorAplicacion.gestorMusica.Lista;

public class Usuario extends Persona {

    private Coleccion coleccion;
    private int tiempoEscuchado=0;
    private ArrayList <Artista> artistas;
    private ArrayList <Cancion> canciones;
    
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
    
    public void agregarCancion(Cancion cancion) {
    	canciones.add(cancion.getNombre());
    }
    
    
  //revisar donde comunicar esa cancion con las otras clases
    public String topCancion(ArrayList <Cancion> canciones) {
   	 int vecesReproducido = 0;
   	 Cancion masEscuchada = null;
   	 for (int i = 0; i < canciones.size(); i++) {
   		 int contador = 0;
   	     for (int j = 0; j < canciones.size(); j++) {
   	        if (canciones.get(i) == canciones.get(j)) {
   	          contador++;
   	        }
   	      }
   	      if (contador > vecesReproducido) {
   	        vecesReproducido = contador;
   	        masEscuchada = canciones.get(i);
   	      }
   	 }
   	    return "Tu cancion mas escuchado fue " + masEscuchada.getNombre();
   }
    
    //revisar donde comunicar ese artista con las otras clases
    public String topArtista(ArrayList <Artista> artistas) {
    	 int vecesReproducido = 0;
    	 Artista masEscuchado = null;
    	 for (int i = 0; i < artistas.size(); i++) {
    		 int contador = 0;
    	     for (int j = 0; j < artistas.size(); j++) {
    	        if (artistas.get(i) == artistas.get(j)) {
    	          contador++;
    	        }
    	      }
    	      if (contador > vecesReproducido) {
    	        vecesReproducido = contador;
    	        masEscuchado = artistas.get(i);
    	      }
    	 }
    	    return "Tu artista mas escuchado fue " + masEscuchado.getNombre();
    }
    
    //plantear estadisticas como funcionalidad compleja entre clases junto con tiempo total
    
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
