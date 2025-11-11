import java.util.ArrayList;

public class Admin extends User implements IEliminar, IPublicar {
    
    public Admin() {
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
    public String eliminar(Contenido c, ArrayList<Contenido> contenido) {
        if (c == null) {
            return "No se ha seleccionado ningún contenido para eliminar.";
        }

        if (contenido == null) {
            return "No se pudo acceder a la lista de contenidos";
        }

        contenido.remove(c);

        return "Se ha eliminado [" + c.getClass().getSimpleName() + "] " + c.getNombre() + " correctamente.";
    }

    
}