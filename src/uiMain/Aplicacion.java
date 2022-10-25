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
					System.exit(0);
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
				} while(opcion2 != 7);
				return;
			}
	    }
		System.out.println("El usuario no existe");
	}
	
	static void crearArtista() {
		System.out.println("Ingresa el nombre del Artista");
		sc.nextLine();
		String nombre = sc.nextLine();
		int opcion;
		Genero genero;
		
		System.out.println("¿Cuál es su Género Musical?");
		System.out.println("1) Reggaeton");
		System.out.println("2) Rock");
		System.out.println("3) Pop");
		System.out.println("4) Salsa");
		System.out.println("5) Kpop");
		System.out.println("6) Ninguno de los anteriores");
		
		opcion = sc.nextInt();
		if(opcion == 1) { genero = Genero.REGGAETON;}
		else if(opcion == 2) { genero = Genero.ROCK; }
		else if(opcion == 3) { genero = Genero.POP; }
		else if(opcion == 4) { genero = Genero.SALSA; }
		else if(opcion == 5) { genero = Genero.KPOP; }
		else { 
			new Artista(nombre);
			System.out.println("¡El artista " + nombre + " se añadió con éxito!");
			return;
		}
		
		new Artista(nombre, genero);
		System.out.println("¡El artista " + nombre + " se añadió con éxito!");
	}
	
	static void crearCancion() {
		System.out.println("Ingresa el nombre de la Canción");
		sc.nextLine();
		String nCancion = sc.nextLine();
		String nArtista; int opcion; Genero genero; int duracion; int ano;
		
		System.out.println("\nIngresa el nombre del Artista que la interpreta");
		nArtista = sc.nextLine();
		for(Artista artista: Artista.getArtistasDisponibles()) {
			if(artista.getNombre().equals(nArtista)) {
				
				System.out.println("\n¿Cuánto dura la Canción (en segundos)?");
				duracion = sc.nextInt();
				System.out.println("\n¿En qué año se lanzó la Canción?");
				ano = sc.nextInt();
				
				System.out.println("\n¿Cuál es el Género Musical de la Canción?");
				System.out.println("1) Reggaeton");
				System.out.println("2) Rock");
				System.out.println("3) Pop");
				System.out.println("4) Salsa");
				System.out.println("5) Kpop");
				System.out.println("6) Ninguno de los anteriores");
				
				opcion = sc.nextInt();
				if(opcion == 1) { genero = Genero.REGGAETON;}
				else if(opcion == 2) { genero = Genero.ROCK; }
				else if(opcion == 3) { genero = Genero.POP; }
				else if(opcion == 4) { genero = Genero.SALSA; }
				else if(opcion == 5) { genero = Genero.KPOP; }
				else {
					new Cancion(nCancion, artista, duracion, ano);
					System.out.println("¡La canción " + nCancion + " se añadió con éxito!");
					return;
				}
				
				new Cancion(nCancion, artista, genero, duracion, ano);
				System.out.println("¡La canción " + nCancion + " se añadió con éxito!");
				return;
			}
		}
		System.out.println("¡El artista " + nArtista + " no existe!");
		return;
	}
	
	static void billboard(Usuario usu) {
		Artista artistaTop=usu.topArtista();
		if (artistaTop==null) {
			System.out.println("No tienes artista favorito");
		}else {
		System.out.println("Tu artista favorito es "+artistaTop.getNombre());
		}
		
		for (Artista artista: Artista.getArtistasDisponibles()) {
			double puntaje=0.0;
			if(artista.getGenero().equals(Usuario.genFavoritoSpotyfree())) {
				puntaje=artista.getReproducciones(artista)*Artista.FACTORREPRODUCCIONES+artista.getMeGusta(artista)*Artista.FACTORMEGUSTA+Usuario.BONIFICACION;
				artista.setPuntaje(puntaje);
			}else {
				puntaje=artista.getReproducciones(artista)*Artista.FACTORREPRODUCCIONES+artista.getMeGusta(artista)*Artista.FACTORMEGUSTA;
				artista.setPuntaje(puntaje);
			}
		}
		if (Cancion.topCancion()!=null) {
		    Artista aBoniCancion = Cancion.topCancion().getArtista();
			Double oldPuntaje = aBoniCancion.getPuntaje();
			if (oldPuntaje == null){
				oldPuntaje = 0.0;
			}
			aBoniCancion.setPuntaje(oldPuntaje+Usuario.BONIFICACION);
		}
		
	    System.out.println("\nRANKING BILLBOARD 2022");
	    System.out.println("");
	    int i= Artista.getArtistasDisponibles().size()-1;
	    int p=1;
	    String mensaje="";
	    ArrayList<Artista> ListaArtista = Artista.ordenarPorPuntaje();;
	  
	    while(i!=0) {
	        if(p==1) {
	        	if(artistaTop!=null) {
	        		if (ListaArtista.get(i).getNombre().equals(artistaTop.getNombre())){
	        			mensaje="Felicidades! Tu artista favorito es el #1!";
	        		}else {
	        			mensaje="A seguir esforzandose para que tu artista logre ser el #1!";
	        	    }
	            }else {
	            	mensaje="";
	            }
	        }
	        System.out.println("Artista #"+p+" "+ListaArtista.get(i).getNombre());
	        p++;
	        i=i-1;
	    }
	    System.out.println("Artista #"+p+" "+ListaArtista.get(0).getNombre());
	    System.out.println("\n"+mensaje);
	}
	
	
	static void accederColeccion(Usuario usuario) {
		
		int opcion3;
		do {
			System.out.println(" _____       _               _             \r\n"
					+ "/  __ \\     | |             (_)            \r\n"
					+ "| /  \\/ ___ | | ___  ___ ___ _  ___  _ __  \r\n"
					+ "| |    / _ \\| |/ _ \\/ __/ __| |/ _ \\| '_ \\ \r\n"
					+ "| \\__/\\ (_) | |  __/ (_| (__| | (_) | | | |\r\n"
					+ " \\____/\\___/|_|\\___|\\___\\___|_|\\___/|_| |_|\r\n"
					+ "");
			System.out.println("\nHola, " + usuario.getNombre());
			System.out.println("Estas son tus Opciones:");
			System.out.println("1) Ver tus Listas de Reproducción");
			System.out.println("2) Ver Tus Canciones Favoritas");
			System.out.println("3) Crear Lista de Reproducción");
			System.out.println("4) Eliminar Lista de Reproducción");
			System.out.println("5) Encontrar Nueva Música");
			System.out.println("6) Agregar una Canción a una Lista");
			System.out.println("7) Eliminar una Canción de una Lista");
			System.out.println("8) Ver Descripción de una Lista");
			System.out.println("9) Cambiar Descripción de una Lista");
			System.out.println("10) Reproducir una Canción");
			System.out.println("11) Reproducir una Lista");
			System.out.println("12) Tus Resúmenes de Género y Gustos");
			System.out.println("13) Conoce tu Agrupación por Colores");
			System.out.println("14) Ver tu lista colaborativa");
			System.out.println("15) Salir de la Colección");
			
			opcion3 = sc.nextInt();
			
			switch(opcion3) {
				case 1:
					System.out.println("---------------------");
					if (usuario.getColeccion().getListas().size() > 1) {
						for (Lista lista: usuario.getColeccion().getListas()) {
							if(!(lista instanceof MeGusta)) {
								System.out.println(lista.infoLista()); }
						} 
					} else {
						System.out.println("¡No has creado ninguna lista aún!");
					}
					break; 
				case 2:
					System.out.println("---------------------");
					System.out.println(usuario.getFavoritos());
					int elegir;
					do {
						System.out.println("1) Agregar Canción a Favoritos");
						System.out.println("2) Eliminar Canción de Favoritos");
						System.out.println("3) Reproducir Favoritos");
						System.out.println("4) Volver al Menú Anterior");
						elegir = sc.nextInt();
							
						switch(elegir) {
							case 1:
								cancionMeGusta(usuario);
								break;
							case 2:
								cancionNoMeGusta(usuario);
								break;
							case 3: 
								reproLista(usuario,usuario.getFavoritos().getFavoritos());
								break;
						}
						if (elegir != 4) {
							System.out.println("\nPresiona Enter para continuar");
							try {
								System.in.read();
							} catch (IOException error) {
								error.printStackTrace();
								}
						}
					}	
					 while(elegir !=4);	
					break;
				case 3:
					System.out.println("---------------------");
					crearLista(usuario);
					break;
				case 4: 
					System.out.println("---------------------");
					eliminarLista(usuario);
					break;
				case 5:
					System.out.println("---------------------");
					recomendarMusica(usuario);
					break;
				case 6: 
					System.out.println("---------------------");
					agregarCancion(usuario);
					break;
				case 7:
					System.out.println("---------------------");
					eliminarCancion(usuario);
					break;
				case 8:
					System.out.println("---------------------");
					descripcionLista(usuario);
					break;
				case 9:
					System.out.println("---------------------");
					cambiarDescripcion(usuario);
					break;
				case 10:
					System.out.println("---------------------");
					reproCancion(usuario);
					break;
				case 11:
				
					System.out.println("---------------------");
					System.out.println("Tus Listas Individuales");
					reproLista(usuario);
					break;
								
				case 12:
					System.out.println("---------------------");
					resumenesGenerosyGustos(usuario);
					break;
				case 13:
					System.out.println("---------------------");
					agrupacionColores(usuario);
					break;
					
				case 14:
					System.out.println("---------------------");
					System.out.println("Tu Lista Colaborativa\n");
					reproColaborativas(usuario); //Ver donde acomodar este llamado y ver metodo de usuarios que conforman la fusion
					break;
					
				}
			
				if (opcion3 != 15) {
					System.out.println("\nPresiona Enter para continuar");
				try {
					System.in.read();
				} catch (IOException error) {
					error.printStackTrace();
				}
			}
		} while(opcion3 != 15);
		return;
				
	}
	
	static void crearLista(Usuario usuario) {	
		System.out.println("Ingresa el nombre de la Lista");
		sc.nextLine();
		String nombre = sc.nextLine();
		System.out.println("Ingresa una descripción para tu Lista");
		String descripcion = sc.nextLine();
		new Lista(nombre, usuario, descripcion);
		System.out.println("¡La lista " + nombre + " se creó con éxito!");
	}

	static void crearLista(Usuario usuario, ArrayList<Cancion> lista) {	
		System.out.println("Ingresa el nombre de la Lista");
		sc.nextLine();
		String nombre = sc.nextLine();
		System.out.println("Ingresa una descripción para tu Lista");
		String descripcion = sc.nextLine();
		new Lista(nombre, lista, usuario, descripcion);
		System.out.println("¡La lista " + nombre + " se creó con éxito!");
	}
	
	static void eliminarLista(Usuario usuario) {
		System.out.println("Ingresa el nombre de la Lista que quieres eliminar");
		sc.nextLine();
		String nombre = sc.nextLine();
		for (Lista lista: usuario.getColeccion().getListas()) {
			if (lista.getNombre().equals(nombre)) {
				usuario.getColeccion().eliminarLista(lista);
				System.out.println("La Lista: "+ nombre + " se eliminó con éxito");
				return;
			}
		}
		System.out.println("La lista " + nombre + " no está en tu Colección");
	}

	static void recomendarMusica(Usuario usuario) {
		
		ArrayList<Cancion> recomendadas = new ArrayList<Cancion>();	
		
		Genero genero = usuario.tuGenFavorito(usuario);
		
		if(genero != null){
			
			for (Cancion cancion : Cancion.getCancionesDisponibles()) {
				if(cancion.getGenero() == genero){
					recomendadas.add(cancion);
				}	
			}

			ArrayList<Cancion> canciones = new ArrayList<Cancion>();
			
			canciones = usuario.getColeccion().cancionesUsuario();

			for(Cancion cancion : usuario.getFavoritos().getFavoritos()){

				if(canciones.contains(cancion) == false){

					canciones.add(cancion);
				}
			}
			ArrayList<Cancion> recomendadas2 = new ArrayList<Cancion>();

			for (Cancion cancion : recomendadas) {

				boolean flag = true;

				for (Cancion cancion2 : canciones) {

					String a1 = cancion.getNombre();
					String a2 = cancion2.getNombre();

					if(a1.equals(a2)){
						flag = false;
					}
					
				}

				if(flag){
					recomendadas2.add(cancion);
				}
				
			}

			System.out.println("Segun la musica de tu colección estas canciones te podrian interesar: \n");
			recomendadas2.forEach((Cancion cancion) -> System.out.println(cancion.getNombre()));
		}
		else{
			System.out.println("Para recomendaciones personalizadas dale me gusta a algunas canciones. \n");
			System.out.println("Estas son las 3 canciones mas escuchadas por la comunidad");
			recomendadas = (ArrayList<Cancion>)Cancion.getCancionesDisponibles().clone();
			recomendadas.sort((Comparator.comparing(Cancion :: getReproducciones)).reversed());

			for(int i = 0; i<3; i++){
				System.out.println(recomendadas.get(i).getNombre());
			}
		}

		System.out.println("");
		System.out.println("¿Que deseas hacer?");
		System.out.println("1) Escuchar las canciones");
		System.out.println("2) Agregar lista de recomendaciones a la colección");
		System.out.println("3) Agregar canción a una lista");
		System.out.println("4) Volver al menu principal");

		int opcion4 = sc.nextInt();
			
		switch(opcion4) {
			case 1:
				System.out.println("---------------------");
				reproLista(usuario, recomendadas);
				break; 
			case 2:
				System.out.println("---------------------");
				crearLista(usuario, recomendadas);
				break;
			case 3: 
				System.out.println("---------------------");
				agregarCancion(usuario);
				break;
			case 4: 
				accederColeccion(usuario);
				break;
		}
	}

	static void agregarCancion(Usuario usuario) {
		System.out.println("Ingresa el nombre de la Lista");
		sc.nextLine();
		String nombre = sc.nextLine();
		for (Lista lista: usuario.getColeccion().getListas()) {
			if (lista.getNombre().equals(nombre)) {
				System.out.println("\nIngresa el nombre de la Canción");
				String nCancion = sc.nextLine();
				for (Cancion cancion: Cancion.getCancionesDisponibles()) {
					if (cancion.getNombre().equals(nCancion)) {
						for(Cancion c: lista.getLista()) {
							if (cancion.getNombre().equals(c.getNombre())) {
								System.out.println("No puedes agregar esta canción a tu lista, porque ya es parte de ella");
							    return;
							}
						}
						lista.agregarCancion(cancion);
						System.out.println("La canción: "+ nCancion + " se añadió con éxito");
						return;
					}
				}
				System.out.println("La canción " + nCancion + " no existe");
				return;
			}
		}
		System.out.println("La lista " + nombre + " no está en tu Colección");
	}
	
	static void eliminarCancion(Usuario usuario) {
		System.out.println("Ingresa el nombre de la Lista");
		sc.nextLine();
		String nombre = sc.nextLine();
		for (Lista lista: usuario.getColeccion().getListas()) {
			if (lista.getNombre().equals(nombre)) {
				System.out.println("\nIngresa el nombre de la Canción");
				String nCancion = sc.nextLine();
				for (Cancion cancion: Cancion.getCancionesDisponibles()) {
					if (cancion.getNombre().equals(nCancion)) {
						lista.eliminarCancion(cancion);
						System.out.println("La canción: "+ nCancion + " se eliminó con éxito");
						return;
					}
				}
				System.out.println("La canción " + nCancion + " no existe");
				return;
			}
		}
		System.out.println("La lista " + nombre + " no está en tu Colección");
	}
	
	static void descripcionLista(Usuario usuario) {
		System.out.println("Ingresa el nombre de la Lista");
		sc.nextLine();
		String nombre = sc.nextLine();
		for (Lista lista: usuario.getColeccion().getListas()) {
			if (lista.getNombre().equals(nombre)) {
				System.out.println("\nDescripcion de la lista "+nombre+":");
				System.out.println(lista.getDescripcion());
				return;
				
			}
		}
		System.out.println("La lista " + nombre + " no está en tu Colección");
	}
	
	static void cambiarDescripcion(Usuario usuario) {
		System.out.println("Ingresa el nombre de la Lista");
		sc.nextLine();
		String nombre = sc.nextLine();
		for (Lista lista: usuario.getColeccion().getListas()) {
			if (lista.getNombre().equals(nombre)) {
				System.out.println("\nIngrese la nueva descripción de la lista");
				String nDescripcion = sc.nextLine();
				lista.setDescripcion(nDescripcion);
				System.out.println("\nLa descripción de la lista "+nombre+" se ha cambiado con éxito!");
				return;
			}
		}
		System.out.println("La lista " + nombre + " no está en tu Colección");
	}
	
	static void reproCancion(Usuario usuario) {
		System.out.println("Ingresa el nombre de la canción");
		sc.nextLine();
		String nombre = sc.nextLine();
		for (Cancion cancion: Cancion.getCancionesDisponibles()) {
			if (cancion.getNombre().equals(nombre)) {
				System.out.println(usuario.reproducir(cancion));
				System.out.println("\n...");
				System.out.println("\n...");
				System.out.println("\n...");
				System.out.println("\n...");
				System.out.println("\nLuego de " + cancion.getDuracion() + " segundos, la canción ha terminado");
				return;
			}
		}
		System.out.println("La cancion " + nombre + " no existe");
	}
	
	static void reproLista(Usuario usuario) {
		System.out.println("Ingresa el nombre de la Lista");
		sc.nextLine();
		String nombre = sc.nextLine();
		for (Lista lista: usuario.getColeccion().getListas()) {
			if (lista.getNombre().equals(nombre)) {
				System.out.println(usuario.reproducir(lista));
				System.out.println("\n...");
				System.out.println("\n...");
				System.out.println("\n...");
				System.out.println("\n...");
				System.out.println("\nLuego de " + lista.duracionLista() + " segundos, la lista ha terminado");
				return;
			}
		}
		System.out.println("La lista " + nombre + " no está en tu Colección");
	}

	static void reproLista(Usuario usuario, ArrayList<Cancion> lista) {
		for (Cancion cancion: lista) {
			System.out.println(usuario.reproducir(cancion));
			System.out.println("\n...");
			System.out.println("\n...");
			System.out.println("\n...");
			System.out.println("\n...");
			System.out.println("\nLuego de "+ cancion.getDuracion() + " segundos, la canción ha terminado");
		}
	}
	
	static void cancionMeGusta(Usuario usuario) {
		System.out.println("Ingresa el nombre de la canción");
		sc.nextLine();
		String nombre = sc.nextLine();
		for (Cancion favorita: Cancion.getCancionesDisponibles()) {
			if (favorita.getNombre().equals(nombre)) {
				System.out.println(usuario.agregarMeGusta(favorita));
				return;
			}
		}
		System.out.println("La canción " + nombre + " no está disponible");
	}
	static void cancionNoMeGusta(Usuario usuario) {
		System.out.println("Ingresa el nombre de la canción");
		sc.nextLine();
		String nombre = sc.nextLine();
		for (Cancion cancion: usuario.getFavoritos().getFavoritos()) {
			if (cancion.getNombre().equals(nombre)) {
				System.out.println(usuario.eliminarMeGusta(cancion));
				return;
			}
		}
		System.out.println("La canción " + nombre + " no está disponible");
	}
	
	static void resumenesGenerosyGustos(Usuario usuario) {
		 ArrayList<Double> Puntos= usuario.puntosFavoritos(usuario);
		 ArrayList<Double> PuntosExtras = usuario.getColeccion().PuntosExtras(usuario.getColeccion().getListas());
		 ArrayList<Double> PuntosSumados = new ArrayList<Double>();
		 ArrayList<Double> PuntosFinales = new ArrayList<Double>();
		 ArrayList<String> genero = new ArrayList<String>();
		 genero.add("REGGAETON");genero.add("ROCK");genero.add("POP");genero.add("SALSA");genero.add("KPOP");genero.add("NO_ESPECIFICADO");
		 double totalPuntos=0;
		 for (int i = 0; i < Puntos.size(); i++) {
		 double suma =Puntos.get(i)+PuntosExtras.get(i);
		 totalPuntos+=suma;
		 PuntosSumados.add(suma);	
		 }
		 for (int i = 0; i < PuntosSumados.size(); i++) {
			double operacion= PuntosSumados.get(i)/totalPuntos*100;
			 PuntosFinales.add(operacion);
		 }
		 double mayor=0;
		 int posicion=0;
		 for (int i = 0; i < PuntosFinales.size(); i++) {
			if(mayor<PuntosFinales.get(i)) {
				mayor=PuntosFinales.get(i);
				posicion=i;
			}
		 }
		int REGGAETON= (int) Math.round((Puntos.get(0)*10)/100);
		int ROCK=(int) Math.round((Puntos.get(1)*10)/100);
		int POP= (int) Math.round((Puntos.get(2)*10)/100);
		int SALSA=(int) Math.round((Puntos.get(3)*10)/100);
		int KPOP= (int) Math.round((Puntos.get(4)*10)/100);
		int NO_ESPECIFICADO= (int) Math.round((Puntos.get(5)*10)/100);
		ArrayList<Cancion> cancionesREGGAETON =Lista.listaPorGenero(Genero.REGGAETON);
		ArrayList<Cancion>  cancionesROCK =Lista.listaPorGenero(Genero.ROCK);
		ArrayList<Cancion>  cancionesPOP =Lista.listaPorGenero(Genero.POP);
		ArrayList<Cancion>  cancionesSALSA =Lista.listaPorGenero(Genero.SALSA);
		ArrayList<Cancion>  cancionesKPOP =Lista.listaPorGenero(Genero.KPOP);
		ArrayList<Cancion>  cancionesNO_ESPECIFICADO =Lista.listaPorGenero(Genero.NO_ESPECIFICADO);
		ArrayList<Cancion> CancionMix = new ArrayList<Cancion>();
		
		int R=0;
		Boolean parada = true;
		while (parada == true) {
			if(REGGAETON!=0) {
				Cancion cancion = cancionesREGGAETON.get(new Random().nextInt(cancionesREGGAETON.size()));
					if (CancionMix.contains(cancion)==false) {
						CancionMix.add(cancion);
						R++;
					}
					if(R>=REGGAETON) {
						parada=false;
					}
			}
			else { 
				parada=false;
			}
		} 
		
		R=0;
		parada = true;
		while (parada == true) {
			if(ROCK!=0) {
				Cancion cancion = cancionesROCK.get(new Random().nextInt(cancionesROCK.size()));
					if (CancionMix.contains(cancion)==false) {
						CancionMix.add(cancion);
						R++;
					}
					if(R>=ROCK) {
						parada=false;
					}
			}
			else { 
				parada=false;
			}
		} 
		
		R=0;
		parada = true;
		while (parada == true) {
			if(POP!=0) {
				Cancion cancion = cancionesPOP.get(new Random().nextInt(cancionesPOP.size()));
					if (CancionMix.contains(cancion)==false) {
						CancionMix.add(cancion);
						R++;
					}
					if(R>=POP) {
						parada=false;
					}
			}
			else { 
				parada=false;
			}
		} 
		
		R=0;
		parada = true;
		while (parada == true) {
			if(SALSA!=0) {
				Cancion cancion = cancionesSALSA.get(new Random().nextInt(cancionesSALSA.size()));
					if (CancionMix.contains(cancion)==false) {
						CancionMix.add(cancion);
						R++;
					}
					if(R>=SALSA) {
						parada=false;
					}
			}
			else { 
				parada=false;
			}
		} 
		
		R=0;
		parada = true;
		while (parada == true) {
			if(KPOP!=0) {
				Cancion cancion = cancionesKPOP.get(new Random().nextInt(cancionesKPOP.size()));
					if (CancionMix.contains(cancion)==false) {
						CancionMix.add(cancion);
						R++;
					}
					if(R>=KPOP) {
						parada=false;
					}
			}
			else { 
				parada=false;
			}
		} 
		
		R=0;
		parada = true;
		while (parada == true) {
			if(NO_ESPECIFICADO!=0) {
				Cancion cancion = cancionesNO_ESPECIFICADO.get(new Random().nextInt(cancionesNO_ESPECIFICADO.size()));
					if (CancionMix.contains(cancion)==false) {
						CancionMix.add(cancion);
						R++;
					}
					if(R>=NO_ESPECIFICADO) {
						parada=false;
					}
				}
				else { 
					parada=false;
				}
			} 
			 System.out.println("Has utilizado la aplicacion "+usuario.getTiempoEscuchado()+" segundos");
			 System.out.printf("\nEres un %.2f compatible con el genero REGGAETON",PuntosFinales.get(0));
			 System.out.printf("\nEres un %.2f compatible con el genero ROCK",PuntosFinales.get(1));
			 System.out.printf("\nEres un %.2f compatible con el genero POP",PuntosFinales.get(2));
			 System.out.printf("\nEres un %.2f compatible con el genero SALSA",PuntosFinales.get(3));
			 System.out.printf("\nEres un %.2f compatible con el genero KPOP",PuntosFinales.get(4));
			 System.out.printf("\nEres un %.2f compatible con el genero NO_ESPECIFICADO\n\n",PuntosFinales.get(5));
			 
			 if(usuario.getGenFavorito().toString()!=genero.get(posicion)) {
				 System.out.println("Tus gustos cambian constantemente. El género que mas escuchas es "+genero.get(posicion)+". ¿Quieres cambiar tu genero favorito a este?");
				 System.out.println("1) Sí");
				 System.out.println("2) No");
				 int seleccion=sc.nextInt();
				 if(seleccion==1) {usuario.setGenFavorito(Genero.valueOf(genero.get(posicion)));
				 System.out.println("\nTu género ha sido cambiado exitosamente");
		}
				 
		}		
			 else {System.out.println("\nEl género "+genero.get(posicion)+" sigue siedo tu favorito");}
			System.out.println("Creamos una lista de compatibilidad de tus generos llamada ListaMix. ¿la quieres guardar? ");
			 System.out.println("1) Sí");
			 System.out.println("2) No");
			 int seleccion=sc.nextInt();
			  if(seleccion==1) {				
				  if (usuario.getColeccion().getListas().size() > 0){
					for (Lista lista: usuario.getColeccion().getListas()){
						if("ListaMix".equals(lista.getNombre())){
							lista.setLista(CancionMix);
						}
					} 
				} 
				new Lista("ListaMix",CancionMix,usuario,"Lista creada por tus gustos");
				System.out.println("\nEsperamos que la disfrutes ;)");
		  }
			  else  System.out.println("\nLástima :(");
		}
		static void reproColaborativas(Usuario usuario) {
			ArrayList<Usuario> users = Usuario.getUsuariosExistentes();
			ArrayList<Usuario> dueños = new ArrayList<Usuario>();
			ArrayList<Cancion> cancionesAgregar = new ArrayList<Cancion>();
			
			Boolean control = true;
			while (control == true){
				Usuario usuario2 = users.get(new Random().nextInt(users.size()));
				if (usuario != usuario2) {
					dueños.add(usuario);
					dueños.add(usuario2);
					control = false;
				}
			}
			
			String nombre1 = dueños.get(0).getNombre();
			String nombre2 = dueños.get(1).getNombre();
			String nombre = nombre1 + " + " + nombre2;
					
			// Ya tenemos donde guardar la lista para ambos, empezamos a ponerle canciones de sus listas al azar
			MeGusta lista1 = dueños.get(0).getFavoritos();
			MeGusta lista2 = dueños.get(1).getFavoritos();
					
			for(Cancion cancion: lista1.getFavoritos()) {
				cancionesAgregar.add(cancion);
			}
			
			for (Cancion cancion: lista2.getFavoritos()) {
				cancionesAgregar.add(cancion);
			}
			
			// Con esta instancia eliminamos las posibles canciones repetidas de la lista colaborativa
			Set<Cancion> cancionesColaborativa = new HashSet<Cancion>();
			for (Cancion cancion: cancionesAgregar) {
				cancionesColaborativa.add(cancion);
			}
			
			Lista colaborativa = new Lista(nombre, dueños, cancionesColaborativa);
			
			System.out.println(colaborativa.infoColaborativa());
		
			// Revisar si el acceso a algun usuario se da correctamente por ser un array de dos elementos
			System.out.println(colaborativa.getUsuarios().get(0).getColeccion().similitudesGenero(cancionesAgregar));
			//System.out.println(colaborativa.getUsuarios().get(1).getColeccion().similitudesCancion(cancionesAgregar));
			
			ArrayList<Cancion> canciones = new ArrayList <Cancion>(cancionesColaborativa);
			
			for(Usuario i: dueños) {
				new Lista(nombre,canciones, i, "Lista colaborativa");
			}
			System.out.println("Cancion colaborativa agregada a tu colección");
		}
		
		static void agrupacionColores(Usuario usuario){
					
			int totalRe = 0; int totalRo = 0; int totalP = 0; int totalS = 0; int totalK = 0; int totalN = 0;
	
			ArrayList<Usuario> naranja = new ArrayList<Usuario>();
			ArrayList<Usuario> negro = new ArrayList<Usuario>();
			ArrayList<Usuario> rosado = new ArrayList<Usuario>();
			ArrayList<Usuario> rojo = new ArrayList<Usuario>();
			ArrayList<Usuario> morado = new ArrayList<Usuario>();
			ArrayList<Usuario> blanco = new ArrayList<Usuario>();
			Usuario amigo;
			
			// Con esta iteración se obtiene el total de canciones por género que tienen todos los usuarios 
			// en sus listas, incluidas las listas de favoritos
			for(Usuario usuarioComparar: Usuario.getUsuariosExistentes()){
				for (Lista lista : usuarioComparar.getColeccion().getListas()) { 
					for (Cancion cancion: lista.getLista()) {
						if(cancion.getGenero().equals(Genero.REGGAETON)) { totalRe++; }
						else if(cancion.getGenero().equals(Genero.ROCK)) { totalRo++; }
						else if(cancion.getGenero().equals(Genero.POP)) { totalP++; }
						else if(cancion.getGenero().equals(Genero.SALSA)) { totalS++; }
						else if(cancion.getGenero().equals(Genero.KPOP)) { totalK++; }
						else { totalN++; }
					}
				}
				MeGusta favoritos = usuarioComparar.getFavoritos();
				for (Cancion cancion: favoritos.getFavoritos()) {
					if(cancion.getGenero().equals(Genero.REGGAETON)) { totalRe++; }
					else if(cancion.getGenero().equals(Genero.ROCK)) { totalRo++; }
					else if(cancion.getGenero().equals(Genero.POP)) { totalP++; }
					else if(cancion.getGenero().equals(Genero.SALSA)) { totalS++; }
					else if(cancion.getGenero().equals(Genero.KPOP)) { totalK++; }
					else { totalN++; }
				}
			}
					
			int Re = 0; int Ro = 0; int P = 0; int S = 0; int K = 0; int N = 0;
			for(Usuario usuarioComparar: Usuario.getUsuariosExistentes()) {
				for (Lista lista : usuarioComparar.getColeccion().getListas()) {
					Re += lista.totalPorGenero(Genero.REGGAETON);
					Ro += lista.totalPorGenero(Genero.ROCK);
					P += lista.totalPorGenero(Genero.POP);
					S += lista.totalPorGenero(Genero.SALSA);
					K += lista.totalPorGenero(Genero.KPOP);
					N += lista.totalPorGenero(Genero.NO_ESPECIFICADO);	
				}
				
				MeGusta favoritos = usuarioComparar.getFavoritos();
				Re += favoritos.totalPorGenero(Genero.REGGAETON);
				Ro += favoritos.totalPorGenero(Genero.ROCK);
				P += favoritos.totalPorGenero(Genero.POP);
				S += favoritos.totalPorGenero(Genero.SALSA);
				K += favoritos.totalPorGenero(Genero.KPOP);
				N += favoritos.totalPorGenero(Genero.NO_ESPECIFICADO);
						
				if (Re*100/totalRe >= 40) {
					naranja.add(usuarioComparar);
				} if(Ro*100/totalRo >= 40) {
					negro.add(usuarioComparar);
				} if(P*100/totalP >= 40) {
					rosado.add(usuarioComparar);
				} if(S*100/totalS >= 40) {
					rojo.add(usuarioComparar);
				} if(K*100/totalK >= 40) {
					morado.add(usuarioComparar);
				} if(N*100/totalN >= 40) {
					blanco.add(usuarioComparar);
				}
			}
					
			System.out.println("¡Estos son los Colores que te Representan!\n");
			ArrayList<Usuario> totales = new ArrayList<Usuario>();
			if(naranja.contains(usuario)) {
				System.out.println("Naranja");
				for(Usuario miembro: naranja) {
					if(!(miembro.getNombre().equals(usuario.getNombre()))) {
						totales.add(miembro);
					}
				}
			} 
			if(negro.contains(usuario)) {
				System.out.println("Negro");
				for(Usuario miembro: negro) {
					if(!(miembro.getNombre().equals(usuario.getNombre()))) {
						totales.add(miembro);
					}
				}
			} 
			if(rosado.contains(usuario)) {
				System.out.println("Rosado");
				for(Usuario miembro: rosado) {
					if(!(miembro.getNombre().equals(usuario.getNombre()))) {
						totales.add(miembro);
					}
				}
			} 
			if(rojo.contains(usuario)) {
				System.out.println("Rojo");
				for(Usuario miembro: rojo) {
					if(!(miembro.getNombre().equals(usuario.getNombre()))) {
						totales.add(miembro);
					}
				}
			} 
			if(morado.contains(usuario)) {
				System.out.println("Morado");
				for(Usuario miembro: morado) {
					if(!(miembro.getNombre().equals(usuario.getNombre()))) {
						totales.add(miembro);
					}
				}
			} 
			if(blanco.contains(usuario)) {
				System.out.println("Blanco");
				for(Usuario miembro: blanco) {
					if(!(miembro.getNombre().equals(usuario.getNombre()))) {
						totales.add(miembro);
					}
				}
			} 
			
			amigo = usuario.encontrarAmigo(totales);
			
			if (amigo == null) {
				System.out.println("No compartes tu Agrupación por Colores con ningún usuario :c");
			} else {
				System.out.println("Según tus colores, alguien que podrías conocer es: \n");
				System.out.println(amigo.getNombre());
			}
					
		}
	}
	