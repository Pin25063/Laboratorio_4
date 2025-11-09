public class Articulo extends Contenido implements IDescargable{

    public Articulo(String nombre, Editor creador, String descripcion) {
        super(nombre, creador, descripcion);
    }

    @Override
    public String visualizar() {
        
    }

    @Override
    public String descargar() {
        
    }
}