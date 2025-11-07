//Jose Pinto
//Valeria Hernandez
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VistaEditor extends VBox {

    private final Button btnVolver = new Button("Volver al Login");
    private Runnable onBack;

    public VistaEditor() {
        setAlignment(Pos.CENTER);
        
        Label titulo = new Label("VISTA EDITOR");

        getChildren().addAll(titulo, btnVolver);
        
        btnVolver.setOnAction(e -> { onBack.run(); });
    }

    // Setter para el "callback" de volver
    public void setOnBack(Runnable onBack) {
        this.onBack = onBack;
    }
}