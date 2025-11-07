//Jose Pinto
//Valeria Hernandez
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VistaAdmin extends VBox {

    private final Button btnVolver = new Button("Volver al Login");
    private Runnable onBack;

    public VistaAdmin() {
        setAlignment(Pos.CENTER);
        
        Label titulo = new Label("VISTA ADMINISTRADOR");

        getChildren().addAll(titulo, btnVolver);
        
        btnVolver.setOnAction(e -> { onBack.run(); } );
    }
    
    // Setter para el boton de volver
    public void setOnBack(Runnable onBack) {
        this.onBack = onBack;
    }
}