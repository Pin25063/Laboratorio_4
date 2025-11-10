public class Editor extends User implements ICrear, IEditar{
    
    public Editor(String correo, String password) {
        super(correo, password);
    }

    @Override
    public String editar(Contenido c) {
        return "Contenido editado";
    }

    @Override
    public void crear(String nombre, Editor creador, String descripcion) {
        
    }
}
