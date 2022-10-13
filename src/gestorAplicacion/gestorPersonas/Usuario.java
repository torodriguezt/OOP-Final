package gestorAplicacion.gestorPersonas;

import gestorAplicacion.gestorMusica.Cancion;
import gestorAplicacion.gestorMusica.Coleccion;
import gestorAplicacion.gestorMusica.Lista;

public class Usuario extends Persona {

    private Coleccion coleccion;
    private int tiempoEscuchado=0;
    
    public Usuario(String nombre){
    	super(nombre);
    }
    
    public String reproducir(Cancion cancion){
        cancion.aumentarReproducciones();
        tiempoEscuchado += cancion.getDuracion();
        return cancion.toString();
    }

    public String reproducir(Lista lista){
        lista.aumentarReproducciones();
        tiempoEscuchado += lista.getDuracion();
        return lista.toString();
    }

    @Override
    public String toString() {
        return "Soy " + nombre + " y he usado la aplicación por" + String.valueOf(tiempoEscuchado) + " segundos.";
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
