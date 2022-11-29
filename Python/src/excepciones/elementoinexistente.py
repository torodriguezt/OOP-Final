from excepciones.erroraplicacion import ErrorAplicacion

class ElementoInexistente(ErrorAplicacion):
    def __init__(self, error):
        super().__init__(error)

class UsuarioInexistente(ElementoInexistente):
    def __init__(self, nombre):
        super().__init__(f"¡El usuario {nombre} no existe!")

class ArtistaInexistente(ElementoInexistente):
    def __init__(self, nombre):
        super().__init__(f"¡El artista {nombre} no existe!")

class ListaInexistente(ElementoInexistente):
    def __init__(self, nombre):
        super().__init__(f"¡La lista {nombre} no existe!")
