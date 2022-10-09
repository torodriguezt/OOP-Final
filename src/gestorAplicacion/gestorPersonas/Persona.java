package gestorAplicacion.gestorPersonas;

public abstract class Persona {

    protected String nombre;

    abstract public String toString();
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

}
