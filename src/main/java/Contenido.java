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

    public String getNombre() {
        return nombre;
    }

    public Editor getCreador() {
        return creador;
    }

    public int getVistas() {
        return vistas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCreador(Editor creador) {
        this.creador = creador;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
