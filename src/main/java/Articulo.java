public class Articulo extends Contenido implements IDescargable{

    public Articulo(String nombre, Editor creador, String descripcion) {
        super(nombre, creador, descripcion);
    }

    @Override
    public String visualizar() {
        return "Mostrando en pantalla el articulo " + nombre + "...";
    }

    @Override
    public String descargar() {
       return "Descargando articulo...";
    }
}