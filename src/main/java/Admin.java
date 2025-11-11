import java.util.ArrayList;

public class Admin extends User implements IEliminar, IPublicar {
    
    public Admin(String correo, String password) {
        super(correo, password);
    }

    @Override
    public String publicar(Contenido c) {
        if (c == null) {
            return "No se ha seleccionado ningún contenido para publicar.";
        }

        if (c.isVisible()) {
            return "El contenido [" + c.getNombre() + "] ya está publicado.";
        }

        c.setVisible(true);
        return "Se ha publicado [" + c.getClass().getSimpleName() + "] " + c.getNombre() + " correctamente.";
    }

    @Override
    public String eliminar(Contenido c, ArrayList<Contenido> contenido, Editor e) {
        if (c == null) {
            return "No se ha seleccionado ningún contenido para eliminar.";
        }

        if (contenido == null || e == null) {
            return "No se pudo acceder a la lista de contenidos o al editor.";
        }

        contenido.remove(c);
        e.getContenido().remove(c);

        return "Se ha eliminado [" + c.getClass().getSimpleName() + "] " + c.getNombre() + " correctamente.";
    }

    
}
