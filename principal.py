from tkinter import *
import tkinter as tk
from tkinter import ttk
from tkinter import messagebox
from baseDatos.serializador import Serializador
from interfazGrafica.fieldframe import FieldFrame
from gestorAplicacion.gestorPersonas.usuario import Usuario
from gestorAplicacion.gestorPersonas.artista import Artista
from gestorAplicacion.gestorMusica.cancion import Cancion
from gestorAplicacion.gestorMusica.genero import Genero
from excepciones.datosincorrectos import Numero
from excepciones.elementoinexistente import UsuarioInexistente, ArtistaInexistente

class Principal():
    
     # Frames
     frames = []

     def __init__(self, ventana):
          
          self = tk.Toplevel(ventana)
          self.title("Spotifree")
          self.option_add('*tearOff', False)
          self.resizable(False, False)

          ancho_total = self.winfo_screenwidth()
          alto_total = self.winfo_screenheight()
       
          # Aplicamos la siguiente formula para calcular donde debería posicionarse
          self.geometry(str(ancho_total)+"x"+str(alto_total)+"+"+"0"+"+"+"0")

          # Cambiar frame
          def cambiarFrame(frameUtilizado):
               for frame in Principal.frames:
                    frame.place_forget()
               frameUtilizado.place(relx=0.5, rely=0.5, anchor="c")

          # Mostrar un output
          def mostrarSalida(string, texto):
               texto.delete("1.0", "end")
               texto.insert(tk.INSERT, string)
               texto.pack(fill=tk.X, expand=True, padx=(10,10))
       
          def informacionAplicacion():
               ventanaDialogo = tk.Tk()
               window_height = 220
               window_width = 800
            
               screen_width = ventanaDialogo.winfo_screenwidth()
               screen_height = ventanaDialogo.winfo_screenheight()
               x = int((screen_width/2) - (window_width/2))
               y = int((screen_height/2) - (window_height/2))
            
               ventanaDialogo.geometry("{}x{}+{}+{}".format(window_width, window_height, x, y))
               ventanaDialogo.title("Spotifree")
               txt = "Spotifree es un gestor de musica.\nDonde el usuario podra agregar a sus artistas favoritas y sus canciones,\na la misma que puede crear listas con canciones disponibles en la aplicacion.\nAparte podra disfrutar de 5 funciones unicas para descubrir nuevas experiencias."
               info = tk.Label(ventanaDialogo, text=txt, justify="center", font=("Verdana", 12))
               info.pack(fill=tk.Y, expand=True)

          def autores():
               ventanaIntegrantes = tk.Tk()
               window_height = 220
               window_width = 400
            
               screen_width = ventanaIntegrantes.winfo_screenwidth()
               screen_height = ventanaIntegrantes.winfo_screenheight()
            
               x = int((screen_width/2) - (window_width/2))
               y = int((screen_height/2) - (window_height/2))
            
               ventanaIntegrantes.geometry("{}x{}+{}+{}".format(window_width, window_height, x, y))
               ventanaIntegrantes.title("Spotifree")
               txt = "Autores:\nMiller Johan Chica Acero\nCatalina Restrepo Salgado\nCarolina Alvarez Murillo\nTomas Rodriguez Taborda\nJeronimo Ledesma Patiño"
               info = tk.Label(ventanaIntegrantes, text=txt, justify="center", font=("Verdana", 12))
               info.pack(fill=tk.Y, expand=True)

          def volver():
               Serializador.serializarDatos()
               self.withdraw()
               ventana.deiconify()
        
          menubar = tk.Menu(self)
          menuArchivo = tk.Menu(menubar)
          menuProceso = tk.Menu(menubar)
          menuAyuda = tk.Menu(menubar)  

          # Barra de menú
    
          menuAyuda.add_command(label="Acerca de", command=lambda: autores())

          menuProceso.add_command(label="Crear usuario", command=lambda: cambiarFrame(frameCrearUsuario))
          menuProceso.add_command(label="Crear artista", command=lambda:cambiarFrame(frameCrearArtista))
          menuProceso.add_command(label="Crear cancion", command=lambda:cambiarFrame(frameCrearCancion))
          menuProceso.add_command(label="Mostrar usuarios", command=lambda:cambiarFrame(frameMostrarUsuarios))
          menuProceso.add_command(label="Mostrar artistas", command=lambda:cambiarFrame(frameMostrarArtistas))
          menuProceso.add_command(label="Mostrar canciones", command=lambda:cambiarFrame(frameMostrarCanciones))
          menuProceso.add_command(label="Acceder como usuario", command=lambda:cambiarFrame(frameUsuario))
      
          menuArchivo.add_command(label="Aplicación", command= lambda: informacionAplicacion())
       
          menuArchivo.add_command(label="Salir", command= lambda: volver())

          menubar.add_cascade(label="Archivo",menu=menuArchivo)
          menubar.add_cascade(label="Procesos y Consultas", menu=menuProceso)
          menubar.add_cascade(label="Ayuda", menu=menuAyuda)
          self.config(menu=menubar)

          # Frame Inicial
          frameInicial= tk.Frame(self)
          nombreInicial = tk.Label(frameInicial, text="¿Cómo usar Spotifree?", font=("Segoe Print", 20), fg="#2C34FA")
          textoInicial = f"¡Bienvenido a la pantalla principal! Desde aquí puedes comenzar a explorar todas las\n" \
                         f"funciones que hemos preparado para ti. Conoce a los artistas que se han registrado en\n" \
                         f"Spotifree y dale un vistazo a todas las canciones que puedes disfrutar. Si gustas, puedes\n" \
                         f"crear una cuenta y acceder a tu propia Colección, ¿qué esperas?" 
          descInicial = tk.Label(frameInicial, text=textoInicial, font=("Verdana", 12))
       
          Principal.frames.append(frameInicial)

          nombreInicial.pack()
          descInicial.pack()

          Principal.frames.append(frameInicial)

          frameInicial.place(relx=0.5, rely=0.5, anchor="c")

          # Funcion para acceder como usuario
          def accederUsuario():
               nombre = fieldUsuario.getValue("Nombre")
               usuario = None
               for persona in Usuario.getUsuariosExistentes():
                    if persona.getNombre() == nombre:
                         usuario = persona
               # Excepción de un usuario que no exista
               '''usuario.getNombre()
               from interfazGrafica.principal2 import Principal2
               self.withdraw()
               Principal2(usuario, ventana, self)'''
               try:
                    usuario.getNombre()
                    from interfazGrafica.principal2 import Principal2
                    self.withdraw()
                    Principal2(usuario, ventana, self)
               except:
                    messagebox.showerror("Aviso", UsuarioInexistente(nombre).mostrarMensaje())
                           
          # Frame para acceder como usuario
          frameUsuario= tk.Frame(self)
          nombreUsuario = tk.Label(frameUsuario, text="Acceder como Usuario", font=("Segoe Print", 20), fg="#2C34FA")
          descUsuario = tk.Label(frameUsuario,text="Por favor ingresa tu nombre de Usuario",font=("Verdana", 12))
          fieldUsuario = FieldFrame(frameUsuario, None, ["Nombre"], None, None, None)
          fieldUsuario.crearBotones(accederUsuario)

          salidaUsuario = tk.Text(frameUsuario, height=50, font=("Verdana", 10), border=False)
          Principal.frames.append(salidaUsuario)

          nombreUsuario.pack()
          descUsuario.pack()
          fieldUsuario.pack(pady=(10,10))

          Principal.frames.append(frameUsuario)

          # Función para mostrar usuarios
          def mostrarUsuarios():
               texto_usuarios = ""
               usuarios = Usuario.getUsuariosExistentes()
               for persona in usuarios:
                    texto_usuarios += f"{persona}\n\n"
               if texto_usuarios == "":
                    messagebox.showinfo("Aviso", "¡Nadie se ha registrado aún!")
               mostrarSalida(texto_usuarios, salidaVerUsuarios)

          # Frame para mostrar usuarios

          frameMostrarUsuarios = tk.Frame(self)
          nombreMostrarUsuarios = tk.Label(frameMostrarUsuarios, text="Usuarios registrados en Spotifree", font=("Segoe Print", 20), fg="#2C34FA")
          descMostrarUsuarios = tk.Label(frameMostrarUsuarios, text="Puede que no observes todos los usuarios a la misma vez \nMueve el cursor del mouse para conocer a más personas", font=("Verdana", 12))
          mostrarUsuarios = tk.Button(frameMostrarUsuarios, text="Mostrar usuarios", font=("Verdana", 12), fg="white", bg="#2C34FA", command=mostrarUsuarios)
          
          salidaVerUsuarios= tk.Text(frameMostrarUsuarios, font=("Verdana", 10), border=False)
          Principal.frames.append(salidaVerUsuarios)
          
          nombreMostrarUsuarios.pack()
          descMostrarUsuarios.pack()
          mostrarUsuarios.pack(pady=(10,10))
          Principal.frames.append(frameMostrarUsuarios)

          # Función para mostrar artistas
          def mostrarArtistas():
               texto_artistas = ""
               artistas = Artista.getArtistasDisponibles()
               for persona in artistas:
                    texto_artistas += f"{persona}\n\n"
               if texto_artistas == "":
                    messagebox.showinfo("Aviso", "¡Aún no tenemos artistas!")
               mostrarSalida(texto_artistas, salidaVerArtistas)

          # Frame para mostrar artistas

          frameMostrarArtistas = tk.Frame(self)
          nombreMostrarArtistas = tk.Label(frameMostrarArtistas, text="Artistas registrados en Spotifree", font=("Segoe Print", 20), fg="#2C34FA")
          descMostrarArtistas = tk.Label(frameMostrarArtistas, text="Puede que no observes todos los artistas a la misma vez \nMueve el cursor del mouse para conocer a más artistas", font=("Verdana", 12))
          mostrarArtistas = tk.Button(frameMostrarArtistas, text="Mostrar artistas", font=("Verdana", 12), fg="white", bg="#2C34FA", command=mostrarArtistas)
          
          salidaVerArtistas= tk.Text(frameMostrarArtistas, width=100, font=("Verdana", 10), border=False)
          Principal.frames.append(salidaVerArtistas)
          
          
          nombreMostrarArtistas.pack()
          descMostrarArtistas.pack()
          mostrarArtistas.pack(pady=(10,10))
          Principal.frames.append(frameMostrarArtistas)

          # Función para mostrar canciones
          def mostrarCanciones():
               texto_canciones = ""
               canciones = Cancion.getCancionesDisponibles()
               for cancion in canciones:
                    texto_canciones += f"{cancion.descripcion()}\n\n"
               if texto_canciones == "":
                    messagebox.showinfo("Aviso", "¡Todavía no tenemos canciones!")
               mostrarSalida(texto_canciones, salidaVerCanciones)

          # Frame para mostrar canciones

          frameMostrarCanciones = tk.Frame(self)
          nombreMostrarCanciones = tk.Label(frameMostrarCanciones, text="Canciones disponibles en Spotifree", font=("Segoe Print", 20), fg="#2C34FA")
          descMostrarCanciones = tk.Label(frameMostrarCanciones, text="Puede que no observes todas las canciones a la misma vez \nMueve el cursor del mouse para conocer más música", font=("Verdana", 12))
          mostrarCanciones = tk.Button(frameMostrarCanciones, text="Mostrar canciones", font=("Verdana", 12), fg="white", bg="#2C34FA", command=mostrarCanciones)
          
          salidaVerCanciones = tk.Text(frameMostrarCanciones, font=("Verdana", 10), border=False)
          Principal.frames.append(salidaVerCanciones)
          
          nombreMostrarCanciones.pack()
          descMostrarCanciones.pack()
          mostrarCanciones.pack(pady=(10,10))
          Principal.frames.append(frameMostrarCanciones)
          
          #Funcion para crear Artista
          def crearArtista():
               nombre = fieldCrearArtista.getValue("Nombre")
               genero = self.comboArtista.get()
               genArtista = None
               for i in Genero:
                    if genero == i.value:
                         genArtista = i
               if genArtista == None:
                    genArtista = Genero.NO_ESPECIFICADO
               Artista(nombre, genArtista)
               messagebox.showinfo("Exito", "El artista fue creado correctamente")
               return
          
          
          #FieldFrame para crear Artista
          frameCrearArtista = tk.Frame(self)