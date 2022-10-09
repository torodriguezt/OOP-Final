package gestorAplicacion.gestorPersonas;

import gestorAplicacion.gestorMusica.Cancion;
import gestorAplicacion.gestorMusica.Coleccion;
import gestorAplicacion.gestorMusica.Lista;

public class Usuario extends Persona {

    private Coleccion coleccion;
    private float tiempoEscuchado;

    public String reproducir(Cancion cancion){

        return cancion.toString();
    }

    public String reproducir(Lista lista){

        return lista.toString();
    }

    @Override
    public String toString() {
        
        return "soy " + nombre + " y he usado la aplicacion por" + String.valueOf(tiempoEscuchado);
    }

    public void setTiempoEscuchado(float tiempoEscuchado) {
        this.tiempoEscuchado = tiempoEscuchado;
    }
    public float getTiempoEscuchado() {
        return tiempoEscuchado;
    }

}
