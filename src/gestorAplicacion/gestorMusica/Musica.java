public abstract class Musica {

    protected String nombre;
    protected int reproducciones;

    public Musica(String nombre){

        
        this.nombre = nombre;
        this.reproducciones = 0;
    }

    public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

    public String getReproducciones() {
		return reproducciones;
	}

	public void setReproducciones(String reproducciones) {
		this.reproducciones = reproducciones;
	}
     
    public abstract void aumentarReproducciones();

    public abstract String toString();

}