public class Video extends Contenido implements IDescargable, IReproducible{
    
    public Video(String nombre, Editor creador, String descripcion) {
        super(nombre, creador, descripcion);
    }

    @Override
    public String visualizar() {
        return "Visualizando el video" + nombre + "...";
    }

    @Override
    public String reproducir() {
        return "Reproduciendo" + nombre + "...";
    }

    @Override
    public String descargar() {
        return "Descargando " + nombre + "...";
    }

    
}
