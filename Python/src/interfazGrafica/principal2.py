from tkinter import *
import tkinter as tk
from tkinter import ttk
from tkinter import messagebox
from gestorAplicacion.gestorMusica.lista import Lista
from baseDatos.serializador import Serializador
from interfazGrafica.fieldframe import FieldFrame
from gestorAplicacion.gestorPersonas.usuario import Usuario
from gestorAplicacion.gestorPersonas.artista import Artista
from gestorAplicacion.gestorMusica.cancion import Cancion
from gestorAplicacion.gestorMusica.genero import Genero
from gestorAplicacion.gestorMusica.lista import Lista
from gestorAplicacion.gestorMusica.meGusta import meGusta
from excepciones.datosincorrectos import ListaIncorrecta, CancionIncorrecta
from excepciones.elementoinexistente import ListaInexistente
import random

class Principal2():
    frames=[]
    
    def __init__(self, usuario: Usuario, ventana_principal, ventana_anterior):
        
        self = tk.Toplevel(ventana_principal)
        self.title("Colección de {}".format(usuario.getNombre()))
        self.option_add('*tearOff', False)
        self.resizable(False, False)

        ancho_total = self.winfo_screenwidth()
        alto_total = self.winfo_screenheight()
        # Aplicamos la siguiente formula para calcular donde debería posicionarse
        self.geometry(str(ancho_total)+"x"+str(alto_total)+"+"+"0"+"+"+"0")

        # Cambiar frame
        def cambiarFrame(frameUtilizado):
               for frame in Principal2.frames:
                    frame.place_forget()
               frameUtilizado.place(relx=0.5, rely=0.5, anchor="c")

        def mostrarSalida(string, texto):
               texto.delete("1.0", "end")
               texto.insert(tk.INSERT, string)
               texto.pack(fill=tk.X, expand=True, padx=(10,10))

        def volver():
            from interfazGrafica.principal import Principal
            Serializador.serializarDatos()
            self.withdraw()
            Principal(ventana_principal)
            
        menubar = tk.Menu(self)
        menuArchivo = tk.Menu(menubar)
        menuProceso = tk.Menu(menubar)
        
        # Barra de menú
        menuProceso.add_command(label="Crear/Eliminar Listas", command= lambda: cambiarFrame(frameCrearLista))
        menuProceso.add_command(label="Mostrar/Editar Listas", command= lambda: cambiarFrame(frameMostrarLista))
        menuProceso.add_command(label="Mostrar Favoritos", command=lambda: cambiarFrame(frameVerCanciones))
        menuProceso.add_command(label="Reproducir",command=lambda: cambiarFrame(frameReproducir))
        menuProceso.add_command(label="Ranking",command=lambda: cambiarFrame(frameRanking))
        menuProceso.add_command(label="Agrupacion")
        menuProceso.add_command(label="Colaborativa", command=lambda:cambiarFrame(frameColaborativa))
        menuProceso.add_command(label="Recomendar Musica", command=lambda:cambiarFrame(frameRecomendarMusica))
        menuProceso.add_command(label="Resumen",command=lambda:cambiarFrame(frameResumen))
       
        menuArchivo.add_command(label="Regresar a la Ventana Anterior",command=volver)

        menubar.add_cascade(label="Archivo",menu=menuArchivo)
        menubar.add_cascade(label="Procesos y Consultas", menu=menuProceso)
        self.config(menu=menubar)

        # Frame Inicial
        frameInicial= tk.Frame(self)
        nombreInicial = tk.Label(frameInicial, text="Sigue Explorando", font=("Segoe Print", 20), fg="#2C34FA")
        textoInicial = f"¡Bienvenido a tu coleccion! Desde aquí puedes visualizar tus listas y favoritoa\n" \
                         f"Reproducirlas, agregar y eliminar canciones o incluso crear listas nuevas\n" \
                         f"Ademas, contamos con unas funciones bastante novedosas\n" \
                         f"Animate a probar todo lo que tenemos para ofrecerte, ¿qué esperas?" 
        descInicial = tk.Label(frameInicial, text=textoInicial, font=("Verdana", 12))

        nombreInicial.pack()
        descInicial.pack()

        Principal2.frames.append(frameInicial)
        frameInicial.place(relx=0.5, rely=0.5, anchor="c")
       
        # CrearLista

        def crearLista(): 
            nombres = fieldCrearLista.getValue("Canciones").split(",")
            canciones = [cancion for cancion in Cancion.getCancionesDisponibles() if cancion.getNombre() in nombres]
            nombre_lista = fieldCrearLista.getValue("Nombre")

            # Manejando la excepción de una lista que ya existe
            for lista in usuario.getColeccion().getListas():
                if nombre_lista == lista.getNombre():
                    messagebox.showerror("Error", ListaIncorrecta(nombre_lista).mostrarMensaje())
                    for entry in fieldCrearLista._elementos:
                        entry.delete(0, "end")
                    return

            lista = Lista(fieldCrearLista.getValue("Nombre"), usuario, fieldCrearLista.getValue("Descripcion"),canciones)
            usuario.getColeccion().agregarLista(lista)
            messagebox.showinfo("Aviso", "¡Se ha creado tu lista con éxito!")
                           
        def eliminarLista(): 
            nombre = fieldCrearLista.getValue("Nombre")
            elementolista = None
            for lista in usuario.getColeccion().getListas():
                if lista.getNombre() == nombre:
                    elementolista = lista
            # Manejando la excepción de una lista que no existe
            try:
                elementolista.getNombre()
                usuario.getColeccion().eliminarLista(lista)
                messagebox.showinfo("Aviso", f"Se ha eliminado la lista {nombre}")
            except:
                messagebox.showerror("Error", ListaInexistente(nombre).mostrarMensaje())
                                     
        frameCrearLista = tk.Frame(self)
        nombrecrearLista = tk.Label(frameCrearLista, text="Crear / Eliminar Listas", font=("Segoe Print", 20), fg = "#2C34FA")
        blankCrearLista = tk.Label(frameCrearLista,text="Por favor ingresa los nombres de las canciones separados por coma", font=("Verdana", 12))
        fieldCrearLista = FieldFrame(frameCrearLista, None, ["Nombre", "Descripcion", "Canciones"], None, None, None)
          
        botonCrear: tk.Button = fieldCrearLista.crearBotones(crearLista, texto= "CREAR")
        botonEliminar: tk.Button = fieldCrearLista.crearBotones(eliminarLista, texto= "ELIMINAR", Column= 1)
          
        outputLista = tk.Text(frameCrearLista, height=100, font=("Verdana", 10))
        Principal2.frames.append(outputLista)
          
        nombrecrearLista.pack()
        blankCrearLista.pack()
        fieldCrearLista.pack(pady=(10,10))
          
        Principal2.frames.append(frameCrearLista)

        #MostrarLista

        frameMostrarLista = tk.Frame(self)
        nombreMostrarLista = tk.Label(frameMostrarLista, text="Mostrar y Editar Listas", font=("Segoe Print", 20), fg = "#2C34FA", pady= 20)

        texto = """Selecciona MOSTRAR para ver las canciones de tu lista
Selecciona AGREGAR para añadir una cancion a tu lista
Selecciona ELIMINAR para remover una cancion de tu lista
Selecciona REPRODUCIR para escuchar tu lista"""
        blankMostrarLista = tk.Label(frameMostrarLista, text = texto, font=("Verdana", 10))
        fieldMostrarLista = FieldFrame(frameMostrarLista, None, ["Nombre Lista", "Nombre Cancion"], None, None, None)
        output = tk.Text(frameMostrarLista,font=("Verdana", 10), border= False, width= 100)

        def MostrarLista():
            
            nombreLista = fieldMostrarLista.getValue("Nombre Lista")
            Lista = [x for x in usuario.getColeccion().getListas() if x.getNombre() == nombreLista]

            if nombreLista == "":
                msg = "Tus listas de reproducción son:\n"
                for l in usuario.getColeccion().getListas():
                    if l.getDescripcion() == "Colaborativa":     
                        msg = msg + l.infoListaColaborativa() + "\n"
                    else:
                        msg = msg + l.getNombre() + "\n"
                mostrarSalida(msg, output)

            elif len(Lista) > 0 and Lista[0].getDescripcion() == "colaborativa":
                if len(Lista) > 0:
                    mostrarSalida(list(Lista[0].infoListaColaborativa()), output)
                else:
                    messagebox.showerror("Error", ListaInexistente(nombreLista).mostrarMensaje())
                        
            elif len(Lista) > 0:
                mostrarSalida(Lista[0].infoLista(), output)
            else:
                mostrarSalida("¡Esta lista no existe!", output)
                
        def AgregarCancion():

            nombreLista = fieldMostrarLista.getValue("Nombre Lista")
            nombreCancion = fieldMostrarLista.getValue("Nombre Cancion")
            lista = [x for x in usuario.getColeccion().getListas() if x.getNombre() == nombreLista]
            
            cancion = [x for x in Cancion.getCancionesDisponibles() if x.getNombre() == nombreCancion]
            # Manejando la excepción para las canciones
            try:
                messagebox.showinfo("Aviso", lista[0].agregarCancion(cancion[0]))
            except:
                messagebox.showerror("Error", CancionIncorrecta(nombreCancion).mostrarMensaje())
                   
        def EliminarCancion():

            nombreLista = fieldMostrarLista.getValue("Nombre Lista")
            nombreCancion = fieldMostrarLista.getValue("Nombre Cancion")
            lista = [x for x in usuario.getColeccion().getListas() if x.getNombre() == nombreLista]
            
            cancion = [x for x in Cancion.getCancionesDisponibles() if x.getNombre() == nombreCancion]
            # Manejando la excepción para las canciones
            try:
                messagebox.showinfo("Aviso", lista[0].eliminarCancion(cancion[0]))
            except: 
                messagebox.showerror("Error", CancionIncorrecta(nombreCancion).mostrarMensaje())
                
        def ReproducirLista():

            nombreLista = fieldMostrarLista.getValue("Nombre Lista")
            Lista = [x for x in usuario.getColeccion().getListas() if x.getNombre() == nombreLista]
            # Manejando la excepción de una lista que no existe
            try:
                usuario.reproducirLista(lista = Lista[0])
                mostrarSalida(Lista[0], output)    
            except:
                messagebox.showerror("Error", ListaInexistente(nombreLista).mostrarMensaje())
                                      
        botonMostrar: tk.Button = fieldMostrarLista.crearBotones(MostrarLista, texto= "MOSTRAR", Column=0)
        botonAgregar: tk.Button = fieldMostrarLista.crearBotones(AgregarCancion, texto= "AGREGAR", Column=1)
        botonEliminar: tk.Button = fieldMostrarLista.crearBotones(EliminarCancion, texto= "ELIMINAR", Column=2)
        botonReproducir: tk.Button = fieldMostrarLista.crearBotones(ReproducirLista, texto= "REPRODUCIR", Column=3, Padx= 70)

        Principal2.frames.append(output)

        nombreMostrarLista.pack()
        blankMostrarLista.pack()
        fieldMostrarLista.pack(pady=(10,10))
       
        Principal2.frames.append(frameMostrarLista)    
        
        frameVerCanciones = tk.Frame(self)
        nombreVC = tk.Label(frameVerCanciones, text="Mostrar y Editar Favoritos", font=("Segoe Print", 20), fg="#2C34FA", pady= 20)
        texto = """Selecciona MOSTRAR para ver las canciones de tus favoritos
Selecciona AGREGAR para añadir una cancion a tus favoritos
Selecciona ELIMINAR para remover una cancion de tus favoritos
Selecciona REPRODUCIR para escuchar tus favoritos"""
        desVC = tk.Label(frameVerCanciones, text = texto,font=("Verdana", 10))
        fieldFavoritos = FieldFrame(frameVerCanciones, None, ["Nombre Cancion"], None, None, None)
        salidaFavoritos = tk.Text(frameVerCanciones,font=("Verdana", 10), border= False, width= 100)

        def Mostrar():
            favoritos = usuario.getFavoritos().getFavoritos()
            if len(favoritos)>0:
               mostrarSalida(usuario.getFavoritos().__str__(), salidaFavoritos)
            else:
               messagebox.showinfo("Aviso", "No tiene canciones en favoritos")
        
        def Agregar():
            nCancion = fieldFavoritos.getValue("Nombre Cancion")
            canciones = Cancion.getCancionesDisponibles()
            c = None
            for cancion in canciones:
                if nCancion == cancion.getNombre():
                    c = cancion
           
            # Manejando la excepción para las canciones
            try:
                messagebox.showinfo("Aviso", usuario.agregarMeGusta(c))
                mostrarSalida("", salidaFavoritos)
            except:
                messagebox.showerror("Error", CancionIncorrecta(nCancion).mostrarMensaje())

        def Eliminar():
            nCancion = fieldFavoritos.getValue("Nombre Cancion")
            canciones = Cancion.getCancionesDisponibles()
            c = None
            for cancion in canciones:
                if nCancion == cancion.getNombre():
                    c = cancion
            if c == None:
                messagebox.showerror("Error", CancionIncorrecta(nCancion).mostrarMensaje())
            else:
                for cancion in usuario.getFavoritos().getFavoritos():
                        if nCancion == cancion.getNombre():
                            c = cancion
                texto = usuario.eliminarMeGusta(c)
                mostrarSalida(texto, salidaFavoritos)
  
        def Reproducir():
            favoritos = usuario.getFavoritos()
            if len(favoritos.getFavoritos()) > 0:
                mostrarSalida("Tus favoritos se estan reproduciendo", salidaFavoritos)
            else:
                messagebox.showinfo("Aviso", "No tienes canciones para reproducir")
       
        botonMostrar: tk.Button = fieldFavoritos.crearBotones(Mostrar, texto= "MOSTRAR", Column=0)
        botonAgregar: tk.Button = fieldFavoritos.crearBotones(Agregar, texto= "AGREGAR", Column=1)
        botonEliminar: tk.Button = fieldFavoritos.crearBotones(Eliminar, texto= "ELIMINAR", Column=2)
        botonReproducir: tk.Button = fieldFavoritos.crearBotones(Reproducir, texto= "REPRODUCIR", Column=3, Padx= 70)
        Principal2.frames.append(salidaFavoritos)
        nombreVC.pack()
        desVC.pack()
        fieldFavoritos.pack(pady=(10,10))

        Principal2.frames.append(frameVerCanciones)    
        
        frameReproducir = tk.Frame(self)
        nombreR= tk.Label(frameReproducir, text="Reproducir Canción", font=("Segoe Print", 20), fg = "#2C34FA", pady= 20)

        texto = "Selecciona REPRODUCIR para escuchar la cancion ingresada"
        desR = tk.Label(frameReproducir, text = texto, font=("Verdana", 10))
        fieldRepro = FieldFrame(frameReproducir, None, ["Nombre Cancion"], None, None, None)
        
        def reproducirCancion():
            nCancion=fieldRepro.getValue("Nombre Cancion")
            canciones = Cancion.getCancionesDisponibles()
            c=None
            for cancion in canciones:
                if nCancion==cancion.getNombre():
                    c=cancion
            if c==None:
                messagebox.showinfo("Aviso", "Esa cancion no existe")
            else:
                texto= usuario.reproducirCancion(c)
                mostrarSalida(texto,salidaRepro)
       
        repro = tk.Button(frameReproducir, text="Reproducir", font=("Verdana", 12), fg="white", bg="#2C34FA", command=reproducirCancion)
          
        salidaRepro= tk.Text(frameReproducir,font=("Verdana", 10), border= False, width= 100)
        Principal2.frames.append(salidaRepro) 

        nombreR.pack()
        desR.pack()
        fieldRepro.pack(pady=(10,10))
        repro.pack()

        Principal2.frames.append(frameReproducir) 

        frameRanking = tk.Frame(self)
        nombreRanking = tk.Label(frameRanking, text="Ranking BILLBOARD", font=("Segoe Print", 20), fg = "#2C34FA", pady= 20)

        desRanking = tk.Label(frameRanking, text = "Selecciona Ver para visualizar el ranking", font=("Verdana", 10))
        
        def billboard():
            artistaTop=usuario.topArtista()
            if artistaTop==None:
                imprimir="No tienes artista favorito\n"
            else:
                imprimir="Tu artista favorito es "+artistaTop.getNombre()+"\n"
            
            for artista in Artista.getArtistasDisponibles():
                puntaje=0.0
                if artista.getGenero().__eq__(Usuario.genFavoritoSpotyfree()):
                    puntaje=artista.getReproducciones(artista)*Artista.FACTORREPRODUCCIONES+artista.getMeGusta(artista)*Artista.FACTORMEGUSTA+Usuario.BONIFICACION
                    artista.setPuntaje(puntaje);
                else:
                    puntaje=artista.getReproducciones(artista)*Artista.FACTORREPRODUCCIONES+artista.getMeGusta(artista)*Artista.FACTORMEGUSTA
                    artista.setPuntaje(puntaje)
            if Cancion.topCancion()!=None:
                aBoniCancion = Cancion.topCancion().getArtista()
                oldPuntaje = aBoniCancion.getPuntaje()
                aBoniCancion.setPuntaje(oldPuntaje+Usuario.BONIFICACION)
            
            imprimir=imprimir+"\nRANKING BILLBOARD 2022\n"
            ListaArtista = Artista.ordenarPorPuntaje()
            for a in range(len(ListaArtista)):
                if a==0:
                    artistaUno=ListaArtista[a].getNombre()
                imprimir=imprimir+"#"+str(a+1)+" "+ListaArtista[a].getNombre()+"\n"
            if artistaTop!=None:
                if artistaUno==artistaTop.getNombre():
                    imprimir=imprimir+"\n"+"FELICITACIONES! Tu artista favorito es el numero uno"
                else:
                    imprimir=imprimir+"\n"+"A seguir esforzandose para que tu artista favoritos sea el numero uno"
            mostrarSalida(imprimir,salidaRanking)
       
        ranking = tk.Button(frameRanking, text="Ver", font=("Verdana", 12), fg="white", bg="#2C34FA", command=billboard)
          
        salidaRanking= tk.Text(frameRanking,font=("Verdana", 10), border= False, width= 100)
        Principal2.frames.append(salidaRanking) 

        nombreRanking.pack()
        desRanking.pack()
        ranking.pack()

        Principal2.frames.append(frameRanking)

        #Resumen 
        frameResumen = tk.Frame(self)
        nombreResumen = tk.Label(frameResumen, text = "Resumen", font=("Segoe Print", 20), fg = "#2C34FA")
        
        desResumen = tk.Label(frameResumen, text = "Mira tus resumenes de genero", font=("Verdana", 10))

        def resumen():
            Puntos=usuario.puntosFavoritos(usuario)
            PuntosExtras=usuario.getColeccion().puntosExtras(usuario.getColeccion().getListas())
            PuntosSumados=[]
            PuntosFinales=[]
            genero=[]
            genero.append(Genero.REGGAETON)
            genero.append(Genero.ROCK)
            genero.append(Genero.POP)
            genero.append(Genero.SALSA)
            genero.append(Genero.KPOP)
            genero.append(Genero.NO_ESPECIFICADO)
            TotalPuntos=0

            for i in range(0,len(Puntos)):
                suma=Puntos[i]+PuntosExtras[i]
                TotalPuntos+=suma
                PuntosSumados.append(suma)
            
            for i in range(0,len(PuntosSumados)):
                operacion=PuntosSumados[i]/TotalPuntos*100
                PuntosFinales.append(operacion)

            mayor=0
            posicion=0

            for i in range(0,len(PuntosFinales)):
                if mayor<PuntosFinales[i]:
                    mayor=PuntosFinales[i]
                    posicion=i

            REGGAETON=round(Puntos[0]*10/100)
            ROCK=round(Puntos[1]*10/100)
            POP=round(Puntos[2]*10/100)
            SALSA=round(Puntos[3]*10/100)
            KPOP=round(Puntos[4]*10/100)
            NO_ESPECIFICADO=round(Puntos[5]*10/100)

            cancionesREGGAETON=Lista.listaPorGenero(Genero.REGGAETON)
            cancionesROCK=Lista.listaPorGenero(Genero.ROCK)
            cancionesPOP=Lista.listaPorGenero(Genero.POP)
            cancionesSALSA=Lista.listaPorGenero(Genero.SALSA)
            cancionesKPOP=Lista.listaPorGenero(Genero.KPOP)
            cancionesNO_ESPECIFICADO=Lista.listaPorGenero(Genero.NO_ESPECIFICADO)
            CancionMix=[]

            R=0
            parada=True
            while parada==True:
                if REGGAETON!=0:
                    Cancion=cancionesREGGAETON[random.randint(0,len(cancionesREGGAETON)-1)]
                    if Cancion not in CancionMix:
                           CancionMix.append(Cancion)
                           R+=1
                    if  R>=REGGAETON:
                        parada=False
                else:
                    parada=False

            R=0
            parada=True
            while parada==True:
                if ROCK!=0:
                    Cancion=cancionesROCK[random.randint(0,len(cancionesROCK)-1)]
                    if Cancion not in CancionMix:
                           CancionMix.append(Cancion)
                           R+=1
                    if  R>=ROCK:
                        parada=False
                else:
                    parada=False
            
            R=0
            parada=True
            while parada==True:
                if POP!=0:
                    Cancion=cancionesPOP[random.randint(0,len(cancionesPOP)-1)]
                    if Cancion not in CancionMix:
                           CancionMix.append(Cancion)
                           R+=1
                    if  R>=POP:
                        parada=False
                else:
                    parada=False

            R=0
            parada=True
            while parada==True:
                if SALSA!=0:
                    Cancion=cancionesSALSA[random.randint(0,len(cancionesSALSA)-1)]
                    if Cancion not in CancionMix:
                           CancionMix.append(Cancion)
                           R+=1
                    if  R>=SALSA:
                        parada=False
                else:
                    parada=False

            R=0
            parada=True
            while parada==True:
                if KPOP!=0:
                    Cancion=cancionesKPOP[random.randint(0,len(cancionesKPOP)-1)]
                    if Cancion not in CancionMix:
                           CancionMix.append(Cancion)
                           R+=1
                    if  R>=KPOP:
                        parada=False
                else:
                    parada=False
            
            R=0
            parada=True
            while parada==True:
                if NO_ESPECIFICADO!=0:
                    Cancion=cancionesNO_ESPECIFICADO[random.randint(0,len(cancionesNO_ESPECIFICADO)-1)]
                    if Cancion not in CancionMix:
                           CancionMix.append(Cancion)
                           R+=1
                    if  R>=NO_ESPECIFICADO:
                        parada=False
                else:
                    parada=False
            
            p0=""
            if usuario.getGenFavorito()!=genero[posicion]:
                p0="Tus gustos cambian constantemente. El género que mas escuchas es {}".format(genero[posicion].value)
            else:
                p0="Tu género Favorito sigue siendo el mismo"
            
            p1="Has utilizado la aplicacion {} segundos".format(usuario.getTiempoEscuchado())
            p2="Eres un {} compatible con el genero de REGGAETON".format(round(PuntosFinales[0],2))
            p3="Eres un {} compatible con el genero de ROCK".format(round(PuntosFinales[1],2))
            p4="Eres un {} compatible con el genero de POP".format(round(PuntosFinales[2],2))
            p5="Eres un {} compatible con el genero de SALSA".format(round(PuntosFinales[3],2))
            p6="Eres un {} compatible con el genero de KPOP".format(round(PuntosFinales[4],2))
            p7="Eres un {} compatible con el genero de NO_ESPECIFICADO".format(round(PuntosFinales[5],2))
            p8="Hemos creado una listaMix y se ha agregado a tu coleccion"

            listaMix = Lista("Lista Mix", usuario, "Tu Lista Mix", CancionMix)
            usuario.getColeccion().agregarLista(listaMix)

            texto=p0+"\n"+"\n"+p1+"\n"+p2+"\n"+p3+"\n"+p4+"\n"+p5+"\n"+p6+"\n"+p7+"\n"+"\n"+p8

            mostrarSalida(texto, salidaResumen)

        Resumenbotton = tk.Button(frameResumen, text="Generar", font=("Verdana", 12), fg="white", bg="#2C34FA",command=resumen)

        salidaResumen= tk.Text(frameResumen,font=("Verdana", 10), border= False, width= 100)
        Principal2.frames.append(salidaResumen) 

        nombreResumen.pack()
        desResumen.pack()
        Resumenbotton.pack()
        Principal2.frames.append(frameResumen)  
        
        # Colaborativa
        
        frameColaborativa = tk.Frame(self)
        nombreColaborativa = tk.Label(frameColaborativa, text = "Colaborativa", font=("Segoe Print", 20), fg = "#2C34FA")
        
        desColaborativa = tk.Label(frameColaborativa, text = "Selecciona crear para generar tu colaborativa", font=("Verdana", 10))
        
        def colaborativa():
            usuarios = Usuario.getUsuariosExistentes()
            dueños = []
            cancionesAgregar = []
            
            control = True
            
            while(control == True):
                usuario2 = usuarios[random.randint(0,len(usuarios)-1)]
                if(usuario2 != usuario):
                    dueños.append(usuario)
                    dueños.append(usuario2)
                    control = False
                    
            nombre1 = dueños[0].getNombre()
            nombre2 = dueños[1].getNombre()
            nombre = nombre1 + " + " + nombre2
            
            lista1 = dueños[0].getFavoritos()
            lista2 = dueños[1].getFavoritos()
            
            for i in lista1.getFavoritos():
                cancionesAgregar.append(i)
            
            for j in lista2.getFavoritos():
                cancionesAgregar.append(j)
                
            cancionesColaborativa = []
            
            for k in cancionesAgregar:
                cancionesColaborativa.append(k)
                
            cancionesColaborativa = set(cancionesColaborativa)
            
            canciones = []
            cancionesNombre = []
            
            for i in cancionesColaborativa:
                if i.getNombre() not in cancionesNombre:
                    canciones.append(i)
                    cancionesNombre.append(i.getNombre())
            
            colaborativa = Lista(nombre, dueños[0], "colaborativa", [], [], set(canciones))
            
            listasUsuario1 = [x for x in usuario.getColeccion().getListas()]
            listasUsuario1Nombres = []
            
            for l in listasUsuario1:
                listasUsuario1Nombres.append(l.getNombre())
            
            if colaborativa.getNombre() in listasUsuario1Nombres:

                for l in usuario.getColeccion().getListas():

                    if l.getNombre() == colaborativa.getNombre():

                        usuario.getColeccion().getListas().remove(l)
            
            usuario.getColeccion().agregarLista(colaborativa)
            mensaje1 = f"Colaborativa {colaborativa.getNombre()} creada"
            mensaje2 = mensaje1 + "\n" +dueños[0].getColeccion().similitudesGenero(cancionesAgregar)
            
            mostrarSalida(mensaje2, salidaColabora)
            
        colabora = tk.Button(frameColaborativa, text="Crear", font=("Verdana", 12), fg="white", bg="#2C34FA", command=colaborativa)
          
        salidaColabora= tk.Text(frameColaborativa,font=("Verdana", 10), border= False, width= 100)
        Principal2.frames.append(salidaColabora) 

        nombreColaborativa.pack()
        desColaborativa.pack()
        colabora.pack()

        Principal2.frames.append(frameColaborativa) 

        # Recomendar Música

        def encontrarMusica():

            listas = usuario.getColeccion().getListas()
            cancionesUsuario = []
            genero = usuario.getGenFavorito()

            for l in listas:

                for cancion in l.getLista():
                    if cancion.getNombre() not in cancionesUsuario:
                        
                        cancionesUsuario.append(cancion.getNombre())

            recomendadas = []

            if genero != None:
                for cancion in Cancion.getCancionesDisponibles():
                    if (not(cancion.getNombre() in cancionesUsuario)) and cancion.getGenero() == genero:
                        recomendadas.append(cancion)
                    else:
                        pass
                if len(recomendadas) > 0:
                    mensaje = "Te podemos recomendar estas canciones: \n\n"
                    for cancion in recomendadas:
                        mensaje = mensaje + cancion.getNombre() + "\n"
                    mostrarSalida(mensaje, outputRecomendarMusica)
                else:
                    canciones = Cancion.getCancionesDisponibles()
                    canciones.sort(key= Cancion.getReproducciones)
                    mensaje = "Tienes agregadas todas las canciones correspondientes a tu genero, ¿que tal si escuchas algo nuevo?, aqui estan las canciones mas escuchadas: \n \n"
                    
                    for i in range(3):
                        mensaje = mensaje + canciones[i].getNombre() + "\n"
                    mostrarSalida(mensaje, outputRecomendarMusica)
  
            else:
                canciones = Cancion.getCancionesDisponibles()
                canciones.sort(key= Cancion.getReproducciones)

                mensaje = "¿No tienes genero favorito? Dale Me Gusta a algunas canciones, por ahora, aqui estan las canciones mas escuchadas: \n \n"
                for i in range(3):
                    mensaje = mensaje + canciones[i].getNombre() + "\n"
                mostrarSalida(mensaje, outputRecomendarMusica)         

        frameRecomendarMusica = tk.Frame(self)
        nombreRecomendarMusica = tk.Label(frameRecomendarMusica, text="Recomendaciones Musicales", font=("Segoe Print", 20), fg="#2C34FA")

        outputRecomendarMusica = tk.Text(frameRecomendarMusica, height=20, font=("Verdana", 10))
        fieldRecomendarMusica = FieldFrame(frameRecomendarMusica, None, [], None, None, None)
          
        botonEncontrar: tk.Button = fieldRecomendarMusica.crearBotones(encontrarMusica, texto= "ENCONTRAR MUSICA")

        nombreRecomendarMusica.pack()
        fieldRecomendarMusica.pack(pady=(10,10))

        nombreRecomendarMusica.pack()
        outputRecomendarMusica.pack()

        Principal2.frames.append(frameRecomendarMusica)

        def agrupacion():
            totalRe = 0; totalRo = 0; totalP = 0; totalS = 0; totalK = 0; totalN = 0
            naranja = []; negro = []; rosado = []; rojo = []; morado = []; blanco = []
            amigo = None
            colores = []
            textoColores = ""
            
            for usuarioComparar in Usuario.getUsuariosExistentes():
                for lista in usuarioComparar.getColeccion().getListas():
                    for cancion in lista.getLista():
                        if cancion.getGenero() == Genero.REGGAETON: totalRe += 1
                        elif cancion.getGenero() == Genero.ROCK: totalRo += 1
                        elif cancion.getGenero() == Genero.POP: totalP += 1
                        elif cancion.getGenero() == Genero.SALSA: totalS += 1
                        elif cancion.getGenero() == Genero.KPOP: totalK += 1
                        else: totalN += 1
                for cancion in usuarioComparar.getFavoritos().getFavoritos():
                    if cancion.getGenero() == Genero.REGGAETON: totalRe += 1
                    elif cancion.getGenero() == Genero.ROCK: totalRo += 1
                    elif cancion.getGenero() == Genero.POP: totalP += 1
                    elif cancion.getGenero() == Genero.SALSA: totalS += 1
                    elif cancion.getGenero() == Genero.KPOP: totalK += 1
                    else: totalN += 1
            
            Re = 0; Ro = 0; P = 0; S = 0; K = 0; N = 0

            for usuarioComparar in Usuario.getUsuariosExistentes():
                for lista in usuarioComparar.getColeccion().getListas():
                    Re += lista.totalPorGenero(Genero.REGGAETON)
                    Ro += lista.totalPorGenero(Genero.ROCK)
                    P += lista.totalPorGenero(Genero.POP)
                    S += lista.totalPorGenero(Genero.SALSA)
                    K += lista.totalPorGenero(Genero.KPOP)
                    N += lista.totalPorGenero(Genero.NO_ESPECIFICADO)
                
                favoritos = usuarioComparar.getFavoritos()
                Re += favoritos.totalPorGenero(Genero.REGGAETON)
                Ro += favoritos.totalPorGenero(Genero.ROCK)
                P += favoritos.totalPorGenero(Genero.POP)
                S += favoritos.totalPorGenero(Genero.SALSA)
                K += favoritos.totalPorGenero(Genero.KPOP)
                N += favoritos.totalPorGenero(Genero.NO_ESPECIFICADO)

                if Re*100 / totalRe >= 50: naranja.append(usuarioComparar)
                if Ro*100 / totalRo >= 50: negro.append(usuarioComparar)
                if P*100 / totalP >= 50: rosado.append(usuarioComparar)
                if S*100 / totalS >= 50: rojo.append(usuarioComparar)
                if K*100 / totalK >= 50: morado.append(usuarioComparar)
                if N*100 / totalN >= 50: blanco.append(usuarioComparar)

            totales = []
            if(usuario in naranja):
                textoColores += "Naranja\n" 
                cancion_color = Cancion.topCancionGenero(Genero.REGGAETON)
                if cancion_color != None:
                    colores.append(cancion_color)
                totales += [miembro for miembro in naranja if miembro.getNombre() != usuario.getNombre()]
            if(usuario in negro):
                textoColores += "Negro\n" 
                cancion_color = Cancion.topCancionGenero(Genero.ROCK)
                if cancion_color != None:
                    colores.append(cancion_color)
                totales += [miembro for miembro in negro if miembro.getNombre() != usuario.getNombre()]
            if(usuario in rosado):
                textoColores += "Rosado\n" 
                cancion_color = Cancion.topCancionGenero(Genero.POP)
                if cancion_color != None:
                    colores.append(cancion_color)
                totales += [miembro for miembro in rosado if miembro.getNombre() != usuario.getNombre()]
            if(usuario in rojo):
                textoColores += "Rojo\n" 
                cancion_color = Cancion.topCancionGenero(Genero.SALSA)
                if cancion_color != None:
                    colores.append(cancion_color)
                totales += [miembro for miembro in rojo if miembro.getNombre() != usuario.getNombre()]
            if(usuario in morado):
                textoColores += "Morado\n" 
                cancion_color = Cancion.topCancionGenero(Genero.KPOP)
                if cancion_color != None:
                    colores.append(cancion_color)
                totales += [miembro for miembro in morado if miembro.getNombre() != usuario.getNombre()]
            if(usuario in blanco):
                textoColores += "Blanco\n" 
                cancion_color = Cancion.topCancionGenero(Genero.NO_ESPECIFICADO)
                if cancion_color != None:
                    colores.append(cancion_color)
                totales += [miembro for miembro in blanco if miembro.getNombre() != usuario.getNombre()]
                
            amigo = usuario.encontrarAmigo(totales)
            
            if amigo == None: 
                textoColores += "No compartes tu Agrupación por Colores con ningún usuario :c \n"
            else:
                textoColores += f"Según tus colores, alguien que podrías conocer es: \n\n{amigo.getNombre()}\n"

            listaColores = Lista("Tus Colores", usuario, "Esta lista combina a la perfección con tu estado de ánimo", colores)
            usuario.getColeccion().agregarLista(listaColores)

            textoColores += "¡En tu colección encontrarás una lista que hemos creado para tu estado de ánimo!"
            mostrarSalida(textoColores, salidaAgrupacion)  
     
        frameAgrupacion = tk.Frame(self)
        nombreAgrupacion = tk.Label(frameAgrupacion, text="Conoce tu Agrupación por Colores", font=("Segoe Print", 20), fg="#2C34FA")
        desAgrupacion = tk.Label(frameAgrupacion, text = "Selecciona Conocer para descubrir los colores que te representan", font=("Verdana", 12))
        
        botonAgrupacion = tk.Button(frameAgrupacion, text="Conocer", font=("Verdana", 10), fg="white", bg="#2C34FA", command=agrupacion)
        salidaAgrupacion = tk.Text(frameAgrupacion, border=False, font=("Verdana", 12))

        Principal2.frames.append(salidaAgrupacion) 

        nombreAgrupacion.pack()
        desAgrupacion.pack()
        botonAgrupacion.pack()
        Principal2.frames.append(frameAgrupacion)  

        self.mainloop()
