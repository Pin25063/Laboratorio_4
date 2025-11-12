public class Admin extends User implements IEliminar, IPublicar {
    
    public Admin() {
    }

    @Override
    public String publicar(Contenido c) {
        return "Contenido publicado";
    }

    @Override
    public String eliminar(Contenido c) {
        return "Contenido eliminado";
    }

    
}