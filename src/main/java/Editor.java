public class Editor extends User implements ICrear, IEditar{
    
    public Editor() {
        super(); 
    }
        @Override
    public String editar(Contenido c) {
        return "Contenido editado";
    }

    @Override
    public void crear(String nombre, Editor creador, String descripcion) {
        // Aquí irá la lógica para crear un nuevo contenido
    }
}