import java.util.Random;

public abstract class Contenido {
    protected String nombre;
    protected Editor creador;
    protected int vistas;
    protected String descripcion;
    protected boolean visible;

    public Contenido(String nombre, Editor creador, String descripcion) {
        this.nombre = nombre;
        this.creador = creador;
        this.vistas = new Random().nextInt(5000);
        this.descripcion = descripcion;
        this.visible = false;
    }

    public abstract String visualizar();
}
