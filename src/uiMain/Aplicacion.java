package uiMain;

import java.io.IOException;
import java.util.*;
import gestorAplicacion.gestorMusica.*;
import gestorAplicacion.gestorPersonas.*;

public class Aplicacion {
	static Scanner sc = new Scanner(System.in);
	
	public static void main (String args[]) {
		
		inicializar();
		int opcion;
		
		do {
			System.out.println("¡Bienvenido a Spotifree!");
			System.out.println(" ___           _   _  __             \r\n"
					+ "/ __|_ __  ___| |_(_)/ _|_ _ ___ ___ \r\n"
					+ "\\__ \\ '_ \\/ _ \\  _| |  _| '_/ -_) -_)\r\n"
					+ "|___/ .__/\\___/\\__|_|_| |_| \\___\\___|\r\n"
					+ "    |_|       ");
			System.out.println("\nEstas son tus opciones: ");
			System.out.println("1) Crear Usuario");
			System.out.println("2) Mostrar Usuarios Existentes");
			System.out.println("3) Acceder como Usuario");
			System.out.println("4) Guardar y Salir");
				
			opcion = sc.nextInt();
			
			switch(opcion) {
				case 1:
					System.out.println("---------------------");
					crearUsuario();
					break;
				case 2: 
		
					System.out.println("---------------------");
					System.out.println("Usuarios Existentes\n");
					for (Usuario usuario: Usuario.getUsuariosExistentes()) {
						System.out.println(usuario); }
					break;
				case 3:
					System.out.println("---------------------");
					accederUsuario();
					break;
				case 4:
					System.out.println("---------------------");
					System.out.println("¡Hasta luego!");
					break;
				default:
					System.out.println("---------------------");
					System.out.println("¡Esta no es una opción válida!");
					break;
				}	
				if (opcion != 4) {
					System.out.println("\nPresiona Enter para continuar");
				try {
					System.in.read();
				} catch (IOException error) {
					error.printStackTrace();
				}
			}
		} while(opcion != 4);
	}
	
	static void inicializar() {
		
		// Creación de artistas
		Artista art1 = new Artista("Feid", Genero.REGGAETON);
		Artista art2 = new Artista("Juanes", Genero.POP);
		Artista art3 = new Artista("Roberto Carlos");
		
		// Creación de canciones
		Cancion test1 = new Cancion("Ferxxo 100", art1, Genero.REGGAETON, 168, 2022);
		Cancion test2 = new Cancion("Juntos", art2, Genero.POP, 204, 2015);
		Cancion test3 = new Cancion("A Dios le pido", art2, Genero.POP, 211, 2009);
		Cancion test4 = new Cancion("¿Qué será de ti?", art3, 249, 1972);
		Cancion test5 = new Cancion("La guerra de los niños", art3, 360, 1980);
		Cancion test6 = new Cancion("Tú en mi vida", art3, 245, 1991);
				
		// Creación de usuarios
		Usuario us1 = new Usuario("Catalina");
		Usuario us2 = new Usuario("Miller");
		Usuario us3 = new Usuario("Carolina");
		Usuario us4 = new Usuario("Tomás");
		Usuario us5 = new Usuario("Jerónimo");
		
		// Creación de listas
		ArrayList<Cancion> canciones1 = new ArrayList<>();
		canciones1.add(test4); canciones1.add(test5); canciones1.add(test6);
		Lista lista1 = new Lista("Canciones de Roberto Carlos", canciones1, us1, "Canciones para escuchar con mi papá");
		Lista lista2 = new Lista("Canciones por definir", us3, "Aquí van las tristezas de Caro");
	}
	
	static void crearUsuario() {
		System.out.println("Escribe el nombre del nuevo usuario");
		sc.nextLine();
		String nombre = sc.nextLine();
		for (Usuario usuario: Usuario.getUsuariosExistentes()) {
			if (nombre.equals(usuario.getNombre())){
				System.out.println("El usuario ya existe");
				return;
			}
		}
		new Usuario(nombre);
		System.out.println("El usuario " + nombre + " fue creado exitosamente");
	}
	
	static void accederUsuario() {
		System.out.println("Escribe el nombre de usuario");
		sc.nextLine();
		String nombre = sc.nextLine();
		for (Usuario usuario: Usuario.getUsuariosExistentes()) {
			if (nombre.equals(usuario.getNombre())){
				System.out.println("\n¡Bienvenido, "+ usuario.getNombre() + "!");
				int opcion2;
				
				do {
					System.out.println("\nEstas son tus opciones: ");
					System.out.println("1) Mostrar Artistas Disponibles");
					System.out.println("2) Mostrar Canciones Disponibles");
					System.out.println("3) Agregar Artista a Spotifree");
					System.out.println("4) Agregar Canción a Spotifree");
