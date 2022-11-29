from tkinter import *
import tkinter as tk
import pathlib
import os
from interfazGrafica.principal import Principal
from tkinter import PhotoImage


class Inicio(tk.Tk):

    def __init__(self):

        # Creación de la ventana
        super().__init__()
        self.title("Spotifree")
        self.resizable(False, False)

        # Para eliminar las líneas punteadas de la barra de menú
        self.option_add("*tearOff",  False)
        #Para crear la pantalla de tamaño completo
        self.ancho_total = self.winfo_screenwidth()
        self.alto_total = self.winfo_screenheight()

        self.geometry(str(self.ancho_total)+"x"+str(self.alto_total))
  
        # Creación de la barra de menú
        self.__menu = tk.Menu(self)
        menu_inicio =tk.Menu(self.__menu)
        menu_inicio.add_command(label="Descripcion", command=lambda: self.descripcion())
        menu_inicio.add_command(label="Salir", command=lambda: self.destroy())
        self.__menu.add_cascade(label="Inicio", menu=menu_inicio)
        self.config(menu = self.__menu)

        # La ventana de inicio está dividida en dos Frames
        self.__frameIzquierda = FrameIzquierda(self)
        self.__frameDerecha = FrameDerecha(self)

        self.__frameIzquierda.grid(row=0, column=0)
        self.__frameDerecha.grid(row=0, column=1)

    def descripcion(self):
        # Todos los frames se están manejando con grid
        self.__frameIzquierda.descripcion.grid(row=1, column=0)

class FrameIzquierda(tk.Frame):

    
    def __init__(self, ventana):

        super().__init__(ventana)

        # Referencia a la ventana de inicio, para poder ocultarla y mostrarla
        self.__ventana = ventana

        self.ancho_total = self.winfo_screenwidth()
        self.alto_total = self.winfo_screenheight()

        self.__p3 = tk.Frame(self, width=round(self.ancho_total/2)-10)
        self.__p42 = tk.Frame(self, width=round(self.ancho_total/2)-10)
       
        # Saludo de bienvenida
        saludo = "¡Bienvenido a Spotifree!"
        self.__saludo = tk.Label(self.__p3, text=saludo, font=("Segoe Print", 20), fg="#2C34FA")
        self.__saludo.grid(row=0, column=0)

        # Descripción del sistema
        descripcion = f"Spotifree es un gestor de música del que se puede hacer uso ingresando como usuario. \nCada usuario tiene una colección en la que puede administrar sus listas de reproducción, \nagregando y eliminando canciones."
        self.descripcion = tk.Label(self.__p3, text = descripcion, width = 90, justify = "left", font=("Verdana", 8))
        #self.descripcion.grid(row=1,column=0)
        
        self._imagenActual = 0 # Imagen actual
        self._imagenes = []

        for i in range(5):
            archivo = os.path.join(pathlib.Path(__file__).parent.parent.parent.absolute(), f"src\\contenidoGrafico\\App{i+1}.png")
            imagen = PhotoImage(file = archivo)
            self._imagenes.append(imagen)

        # Imprimir la primera imagen relacionada a la aplicacion en P4

        self._imagen = Label(self.__p42, image = self._imagenes[0], height = 480, width = 640)
        self._imagen.bind('<Enter>', self.cambiarImagen) # Cambiar de imagen de P4 al pasar el mouse por encima
        self._imagen.pack()

        self._boton = tk.Button(self.__p42, text="Acceder a la aplicación", font=("Verdana", 16), fg="white", bg="#2C34FA", command=self.abrirVentanaPrincipal)
        self._boton.pack()

        self.__p3.grid(row=0, column=0)
        self.__p42.grid(row=2, column=0, pady=(10, 10))
        
    def cambiarImagen(self, _):
        if self._imagenActual == 4:
            self._imagenActual = 0
        else:
            self._imagenActual += 1

        self._imagen.configure(image = self._imagenes[self._imagenActual])
        self._imagen.image = self._imagenes[self._imagenActual]
        
    def abrirVentanaPrincipal(self):
        self.__ventana.withdraw()
        Principal(self.__ventana)

class FrameDerecha(tk.Frame):
    __posicion_imagen = [(0, 0), (0, 1), (1, 0), (1, 1)]

    def __init__(self, ventana):

        super().__init__(ventana)

        self.ancho_total = self.winfo_screenwidth()
        self.alto_total = self.winfo_screenheight()
        
        self._p5 = tk.Frame(self,width=round(self.ancho_total/2), height=self.alto_total)
        self._p6 = tk.Frame(self,width=round(self.ancho_total/2), height=self.alto_total)

        self.__text = None
        self.__next_cf = 0
        self.__fotos = [None, None, None, None]
        self.__labels = []

        self.cargarCFTexto(0)
        
        for i in range(0, 4):
            label = tk.Label(self._p6, width=round(self.ancho_total/4), height=round(self.alto_total/3))
            (r, c) = FrameDerecha.__posicion_imagen[i]
            label.grid(row=r, column=c)
            self.__labels.append(label)
            # Se cargan las primeras imagenes
            self.cargarCFImagen(0, i)

        self._p5.grid(row=0, column= 0)
        self._p6.grid(row=1, column= 0)

    # Se usa para mostrar la hoja de vida que sigue, aumentando el atributo next_hv
    def proximo(self, _):
        if self.__next_cf < 4:
            self.__next_cf = self.__next_cf + 1
        else:
            self.__next_cf = 0

        self._fotos = [None, None, None, None]
        self.cargarCFTexto(self.__next_cf)
        for i in range(0, 4):
            self.cargarCFImagen(self.__next_cf, i)


    def cargarCFImagen(self, cf_num, numero):
        path = os.path.join(pathlib.Path(__file__).parent.parent.parent.absolute(),'src\\contenidoGrafico\CF{0}{1}.png'.format(cf_num, numero))
        foto = tk.PhotoImage(file = path)
        self.__labels[numero].configure(image = foto)
        self.__labels[numero].image = foto

    # Carga el texto para la hoja de vida respecto al numero asignado

    def cargarCFTexto(self, numero):
        self.__text = tk.Text(self._p5, height = 10, font = ("Verdana",10), width = 80, border= False)
        self.__text.grid(row = 1, column = 0)
        self.__text.bind('<Button-1>', self.proximo)

        path = os.path.join(pathlib.Path(__file__).parent.parent.parent.absolute(),'src\\contenidoGrafico\CF{0}4.txt'.format(numero))

        with open(path, "r+") as cf_text:
            self.__text.insert(tk.INSERT, cf_text.read())
