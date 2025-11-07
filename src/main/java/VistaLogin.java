//Jose Pinto
//Valeria Hernandez
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VistaLogin extends VBox {

    // Botones de la vista
    private final Button btnAdministrador = new Button("Administrador");
    private final Button btnEditor = new Button("Editor");

    // Variables para llamar los runnable
    private Runnable onShowAdmin;
    private Runnable onShowEditor;

    public VistaLogin() {
        // Estilo del layout
        setPadding(new Insets(24));
        setSpacing(16);
        setAlignment(Pos.CENTER);
        setMaxWidth(400);
        
        // Labels de la vista
        Label titulo = new Label("CMS LOGIN");
        Label subtitulo = new Label("Por favor, seleccione su rol:");

        // Agregar todos los componentes a la vista
        getChildren().addAll(
            titulo,
            subtitulo,
            btnAdministrador,
            btnEditor
        );

        // Asignar acciones a los botones
        btnAdministrador.setOnAction(e -> { onShowAdmin.run(); });
        btnEditor.setOnAction(e -> { onShowEditor.run(); });
    }

    // Setters para las acciones de los botones de navegación
    public void setOnShowAdmin(Runnable onShowAdmin) {
        this.onShowAdmin = onShowAdmin;
    }

    public void setOnShowEditor(Runnable onShowEditor) {
        this.onShowEditor = onShowEditor;
    }
}