package uiMain;

import java.io.IOException;
import java.util.*;

import baseDatos.Deserializador;
import baseDatos.Serializador;
import gestorAplicacion.gestorMusica.*;
import gestorAplicacion.gestorPersonas.*;

public class Aplicacion {
	static Scanner sc = new Scanner(System.in);
	
	public static void main (String args[]) {
		Deserializador.deserializarDatos();
		// inicializar();
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
						System.out.println(usuario);
					}	
					break;
				case 3:
					System.out.println("---------------------");
					accederUsuario();
					break;
				case 4:
					System.out.println("---------------------");
					Serializador.serializarDatos();
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
	
	/* static void inicializar() {
		
		// Creación de artistas
		Artista art1 = new Artista("Feid", Genero.REGGAETON);
		Artista art2 = new Artista("Juanes", Genero.ROCK);
		Artista art3 = new Artista("Roberto Carlos");
		Artista art4 = new Artista("Jin",Genero.KPOP);
		Artista art5 = new Artista("BTS",Genero.KPOP);
		Artista art6 = new Artista("Daftpunk", Genero.POP);
		Artista art7 = new Artista("Morat", Genero.POP);
		
		// Creación de canciones
		Cancion test1 = new Cancion("Ferxxo 100", art1, Genero.REGGAETON, 168, 2022);
		Cancion test2 = new Cancion("Juntos", art2, Genero.ROCK, 204, 2015);
		Cancion test3 = new Cancion("A Dios le pido", art2, Genero.ROCK, 211, 2009);
		Cancion test4 = new Cancion("¿Qué será de ti?", art3, 249, 1972);
		Cancion test5 = new Cancion("La guerra de los niños", art3, 360, 1980);
		Cancion test6 = new Cancion("Tú en mi vida", art3, 245, 1991);
		Cancion test7 = new Cancion("Epiphany",art4,Genero.KPOP,230,2020);
		Cancion test8 = new Cancion("Butter",art5,Genero.KPOP,300,2021);
		Cancion test9 = new Cancion("mom",art4,Genero.KPOP,200,2018);
		Cancion test10 = new Cancion("Dynamite",art5,Genero.KPOP,400,2020);
		Cancion test11 = new Cancion("Permision to dance",art5,Genero.KPOP,300,2022);
		Cancion test12 = new Cancion("Instant crush", art6, Genero.POP, 180, 2013);
		Cancion test13 = new Cancion("Amor con hielo", art7, Genero.POP, 180, 2016);
		Cancion test14 = new Cancion("Gotas de agua dulce", art2, Genero.ROCK, 180, 2007);
				
		// Creación de usuarios
		Usuario us1 = new Usuario("Catalina");
		Usuario us2 = new Usuario("Miller");
		Usuario us3 = new Usuario("Carolina",Genero.KPOP);
		Usuario us4 = new Usuario("Tomas");
		Usuario us5 = new Usuario("Jeronimo");
		
		// Creación de listas
		ArrayList<Cancion> canciones1 = new ArrayList<>();
		canciones1.add(test4); canciones1.add(test5); canciones1.add(test6);
		ArrayList<Cancion> canciones2 = new ArrayList<>();
		canciones2.add(test1); canciones2.add(test2); canciones2.add(test5);
		ArrayList<Cancion> canciones3 = new ArrayList<>();
		canciones3.add(test8); canciones3.add(test10); canciones3.add(test11);
		ArrayList<Cancion> canciones4 = new ArrayList<>();
		canciones4.add(test7); canciones4.add(test9);
		ArrayList<Cancion> canciones5 = new ArrayList<>();
		canciones5.add(test7); canciones5.add(test8); canciones5.add(test9);canciones5.add(test10);canciones5.add(test11);
		
		Lista lista1 = new Lista("Canciones de Roberto Carlos", canciones1, us1, "Canciones para escuchar con mi papá");
		Lista lista2 = new Lista("BTS",canciones3, us3, "canciones de BTS");
		Lista lista4 = new Lista("Jin",canciones4,us3,"canciones de jin");
		Lista lista5= new Lista("KPOP",canciones5,us3,"Canciones de KPOP");
		Lista lista3 = new Lista("Pruebas",canciones2, us2, "verificacion");
		
		// Canciones para la Lista de Favoritos
		us1.agregarMeGusta(test2); us1.agregarMeGusta(test3); us1.agregarMeGusta(test7); 
		us2.agregarMeGusta(test1); us2.agregarMeGusta(test7);
		us3.agregarMeGusta(test7); us3.agregarMeGusta(test8); us3.agregarMeGusta(test9); us3.agregarMeGusta(test10);
		us3.agregarMeGusta(test11); 
		us4.agregarMeGusta(test12); us4.agregarMeGusta(test13); 
		us5.agregarMeGusta(test14); us5.agregarMeGusta(test2);

	} */
	


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
		int opcion1;
		Genero genero;
		
		System.out.println("¿Cuál es tu Género Musical favorito?");
		System.out.println("1) Reggaeton");
		System.out.println("2) Rock");
		System.out.println("3) Pop");
		System.out.println("4) Salsa");
		System.out.println("5) Kpop");
		System.out.println("6) Ninguno de los anteriores");
		
		opcion1 = sc.nextInt();
		if(opcion1 == 1) { genero = Genero.REGGAETON;}
		else if(opcion1 == 2) { genero = Genero.ROCK; }
		else if(opcion1 == 3) { genero = Genero.POP; }
		else if(opcion1 == 4) { genero = Genero.SALSA; }
		else if(opcion1 == 5) { genero = Genero.KPOP; }
		else { 
			new Usuario(nombre);
			System.out.println("El usuario " + nombre + " fue creado exitosamente");
			return;
		}
		new Usuario(nombre,genero);
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
					System.out.println("3) Agregar Artista");
					System.out.println("4) Agregar Canción");
					System.out.println("5) Acceder a tu Colección");
					System.out.println("6) Mostrar Ranking Billboard");
					System.out.println("7) Volver al Menú Anterior");
					
					opcion2 = sc.nextInt();
					
					switch(opcion2) {
						case 1:
							System.out.println("---------------------");
							System.out.println("Artistas Disponibles\n");
							for (Artista artista: Artista.getArtistasDisponibles()) {
								System.out.println(artista.toString()); }
							break; 
						case 2:
							System.out.println("---------------------");
							System.out.println("Canciones Disponibles\n");
							for (Cancion cancion: Cancion.getCancionesDisponibles()) {
								System.out.println(cancion.descripcion()); }
							break;
						case 3:
							System.out.println("---------------------");
							crearArtista();
							break;
						case 4:
							System.out.println("---------------------");
							crearCancion();
							break;
						case 5: 
							System.out.println("---------------------");
							accederColeccion(usuario);
							break;	
						case 6: 
							System.out.println("---------------------");
							billboard(usuario);
							break;
						}	
						if (opcion2 != 7) {
							System.out.println("\nPresiona Enter para continuar");
						try {
							System.in.read();
						} catch (IOException error) {
							error.printStackTrace();
						}
					}
