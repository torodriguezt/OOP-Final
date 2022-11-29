import tkinter as tk

class MostrarLista(tk.Frame):

    def __init__(self, root: tk.Tk):

        super().__init__(root)

        screenwidth = tk.Tk.winfo_screenwidth(self)
        screenheight = tk.Tk.winfo_screenheight(self)
        
        self.config(width= screenwidth, height= screenheight)
        self.pack(expand = True, fill = "both", anchor = "center")

        parent= tk.Label(self)
        parent.pack()
        # Instrucciones

        label1 = tk.Label(parent, text = "Mostrar Lista", font = ("Segoe Print", 25), fg = "#2C34FA")
        label1.grid(row=0, column= 0, pady = 10)

        texto = """Selecciona MOSTRAR para ver las canciones de tu lista
Selecciona AGREGAR para añadir una cancion a tu lista
Selecciona ELIMINAR para remover una cancion de tu lista
Selecciona REPRODUCIR para escuchar tu lista"""

        label2 = tk.Label(parent, text= texto, justify = "center", font = ("Verdana", 10))
        label2.grid(row=1, column=0, pady = 10)
        
        #Botones

        buttonParent = tk.Label(parent)
        buttonParent.grid(row=2, column= 0, pady = 10)

        button1 = tk.Button(buttonParent, text = "MOSTRAR", font = ("Verdana", 16), fg = "white", bg = "#2C34FA", width= 12)
        button1.pack(side=tk.LEFT, padx= 5)

        button2 = tk.Button(buttonParent, text = "AGREGAR", font = ("Verdana", 16), fg = "white", bg = "#2C34FA", width= 12)
        button2.pack(side=tk.LEFT, padx= 5)

        button3 = tk.Button(buttonParent, text = "ELIMINAR", font = ("Verdana", 16), fg = "white", bg = "#2C34FA", width= 12)
        button3.pack(side=tk.LEFT, padx= 5)

        button4 = tk.Button(buttonParent, text = "REPRODUCIR", font = ("Verdana", 16), fg = "white", bg = "#2C34FA", width= 12)
        button4.pack(side=tk.LEFT, padx= 5)

        #Output

        output = tk.Text(parent, border= False, width= 100)
        output.grid(row = 3, column= 0, pady = 10)     