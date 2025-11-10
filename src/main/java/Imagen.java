public class Imagen extends Contenido implements IDescargable{

    public Imagen(String nombre, Editor creador, String descripcion) {
        super(nombre, creador, descripcion);
    }

    @Override
    public String visualizar() {
        return "Visualizando " + nombre + "...";
    }

    @Override
    public String descargar() {
        return "Archivo descargado!";
    }
}