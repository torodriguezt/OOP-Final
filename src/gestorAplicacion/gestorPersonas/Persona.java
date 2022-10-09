package gestorAplicacion.gestorPersonas;

public abstract class Persona {

    protected String nombre;
    protected float tiempoEscuchado;

    abstract public String toString();

    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setTiempoEscuchado(float tiempoEscuchado) {
        this.tiempoEscuchado = tiempoEscuchado;
    }

    public String getNombre() {
        return nombre;
    }
    public float getTiempoEscuchado() {
        return tiempoEscuchado;
    }
}
