package gestorAplicacion.gestorMusica;

import java.io.Serializable;

public abstract class Musica implements Serializable{

	private static final long serialVersionUID = 1L;
	protected String nombre;
    protected int reproducciones;

    protected Musica(String nombre){
        this.nombre = nombre;
        this.reproducciones = 0;
    }

    public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

    public int getReproducciones() {
		return reproducciones;
	}

	public void setReproducciones(int reproducciones) {
		this.reproducciones = reproducciones;
	}
     
    public abstract void aumentarReproducciones();

    @Override
    public abstract String toString();

}