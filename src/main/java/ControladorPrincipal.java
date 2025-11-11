//Jose Pinto
//Valeria Hernandez
import java.util.ArrayList;

public class ControladorPrincipal {
    
    private User usuarioActual;
    private Editor editor; 
    private Admin administrador; 
    private ArrayList<Contenido> listaContenido;
    private Contenido contenidoSiendoEditado = null; 

    // Vistas y App Principal
    private Main mainApp;
    private VistaLogin vistaLogin;
    private VistaAdmin vistaAdmin;
    private VistaEditor vistaEditor;

    public ControladorPrincipal(Main mainApp, VistaLogin vistaLogin, VistaAdmin vistaAdmin, VistaEditor vistaEditor) {
        this.mainApp = mainApp;
        this.vistaLogin = vistaLogin;
        this.vistaAdmin = vistaAdmin;
        this.vistaEditor = vistaEditor;

        // Crear los modelos de usuario
        this.editor = new Editor();
        this.administrador = new Admin();

        // Cargar el contenido 
        this.listaContenido = generarContenido();
        
        // Conectar botones de VistaLogin
        this.vistaLogin.setOnShowAdmin(this::handleShowAdmin);
        this.vistaLogin.setOnShowEditor(this::handleShowEditor);

        // Conectar botones Volver
        Runnable onBackToLogin = this::handleShowLogin;
        this.vistaAdmin.setOnBack(onBackToLogin);
        this.vistaEditor.setOnBack(onBackToLogin);
        
        // Conexiones de vistaEditor
        this.vistaEditor.setOnGuardar(this::handleGuardarContenido);
        this.vistaEditor.setOnEditar(this::handleCargarParaEditar);
    }

    // Logica de Contenido
    public final ArrayList<Contenido> generarContenido() {
        ArrayList<Contenido> out = new ArrayList<>();
        Contenido video1 = new Video("Video de POO", this.editor, "Un video sobre polimorfismo");
        out.add(video1);
        Contenido video2 = new Video("Tutorial de JavaFX", this.editor, "Cómo usar VBox y HBox");
        out.add(video2);
        Contenido imagen1 = new Imagen("Diagrama UML", this.editor, "Diseño del proyecto CMS");
        out.add(imagen1);
        Contenido imagen2 = new Imagen("Logo UVG", this.editor, "Logo de la universidad");
        out.add(imagen2);
        Contenido articulo1 = new Articulo("Qué es MVC", this.editor, "Explicación del patrón Modelo-Vista-Controlador");
        out.add(articulo1);
        Contenido articulo2 = new Articulo("Interfaces vs Clases Abstractas", this.editor, "Cuándo usar cada una");
        out.add(articulo2);
        return out;
    }

    // Manejadores de Navegación
    private void handleShowAdmin() {
        this.usuarioActual = this.administrador;
        this.mainApp.showAdminView();
    }

    private void handleShowEditor() {
        this.usuarioActual = this.editor;
        this.mainApp.showEditorView();
        
        // Cargar la lista de contenido cada que entramos a la vista
        this.vistaEditor.actualizarLista(this.listaContenido);
        this.contenidoSiendoEditado = null; // Resetea el modo edición
    }

    private void handleShowLogin() {
        this.usuarioActual = null;
        this.mainApp.showLoginView();
    }
    
    // Manejadores de Lógica del Editor

    //Decide si crear uno nuevo o actualizar uno existente
    private void handleGuardarContenido() {
        // Obtener datos de la Vista
        String nombre = vistaEditor.getNombreContenido();
        String desc = vistaEditor.getDescripcionContenido();
        
        if (nombre.isEmpty() || desc.isEmpty()) {
            return;
        }

        if (contenidoSiendoEditado != null) {
            // Modo edicion
            contenidoSiendoEditado.setNombre(nombre);
            contenidoSiendoEditado.setDescripcion(desc);
            contenidoSiendoEditado = null; // Salir de modo edición
            
        } else {
            // Modo creacion
            String tipo = vistaEditor.getTipoContenido();
            Editor creador = this.editor;
            Contenido nuevoContenido = null;

            switch (tipo) {
                case "Articulo":
                    nuevoContenido = new Articulo(nombre, creador, desc);
                    break;
                case "Imagen":
                    nuevoContenido = new Imagen(nombre, creador, desc);
                    break;
                case "Video":
                    nuevoContenido = new Video(nombre, creador, desc);
                    break;
            }

            if (nuevoContenido != null) {
                this.listaContenido.add(nuevoContenido);
            }
        }     
        vistaEditor.actualizarLista(this.listaContenido);
    }
    
    //Toma el ítem seleccionado de la lista y lo pone en el formulario.
    private void handleCargarParaEditar() {
        Contenido seleccionado = vistaEditor.getContenidoSeleccionado();
        if (seleccionado != null) {
            // Cargar datos en el formulario de la vista
            vistaEditor.cargarDatosFormulario(seleccionado);
            
            // Guardar que estamos editando
            this.contenidoSiendoEditado = seleccionado;
        }
    }
}