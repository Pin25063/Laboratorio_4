//Jose Pinto
//Valeria Hernandez
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    private Scene scene; // Escena principal

    // Almacenamos las instancias de las vistas
    private VistaLogin vistaLogin;
    private VistaAdmin vistaAdmin;
    private VistaEditor vistaEditor;
    
    private ControladorPrincipal controlador;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Instanciar todas las vistas
        vistaLogin = new VistaLogin();
        vistaAdmin = new VistaAdmin();
        vistaEditor = new VistaEditor();

        // Instanciar el Controlador
        controlador = new ControladorPrincipal(this, vistaLogin, vistaAdmin, vistaEditor);

        // Mostrar escena inicial Login        
        scene = new Scene(vistaLogin, 400, 300); 
        primaryStage.setTitle("CMS Login"); 
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Métodos para que el Controlador cambie la Vista 

    //Mostrar escena vista de Login
    public void showLoginView() {
        scene.setRoot(vistaLogin);
        primaryStage.setTitle("CMS Login");
        primaryStage.sizeToScene();

    }

    //Muestra la vista de Administrador
    public void showAdminView() {
        scene.setRoot(vistaAdmin);
        primaryStage.setTitle("Vista Administrador");
        primaryStage.sizeToScene();


    }

    //Muestra la vista de Editor
    public void showEditorView() {
        scene.setRoot(vistaEditor);
        primaryStage.setTitle("Vista Editor");
        primaryStage.sizeToScene();

    }


    public static void main(String[] args) {
        launch(args);
    }
}