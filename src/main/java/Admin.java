public class Admin extends User implements IEliminar, IPublicar {
    
    public Admin(String correo, String password) {
        super(correo, password);
    }

    @Override
    public String publicar(Contenido c) {
        return "Contenido publicado!";
    }

    @Override
    public String eliminar(Contenido c) {
        return "Contenido eliminado!";
    }

    
}
