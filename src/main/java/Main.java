//Jose Pinto
//Valeria Hernandez
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Clase principal que lanza la aplicacion JavaFX y maneja la navegacion entre las vistas.
public class Main extends Application {

    private Stage primaryStage;
    
    // Almacenamos las instancias de las vistas
    private VistaLogin vistaLogin;
    private VistaAdmin vistaAdmin;
    private VistaEditor vistaEditor;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        // Instancia de todas las vistas
        vistaLogin = new VistaLogin();
        vistaAdmin = new VistaAdmin();
        vistaEditor = new VistaEditor();

        // Configuracion la navegacion
        // Login -> Admin
        vistaLogin.setOnShowAdmin(() -> {
            primaryStage.getScene().setRoot(vistaAdmin);
            primaryStage.setTitle("Vista Administrador");
        });

        // Login -> Editor
        vistaLogin.setOnShowEditor(() -> {
            primaryStage.getScene().setRoot(vistaEditor);
            primaryStage.setTitle("Vista Editor");
        });
        
        // El Runnable para volver al login
        Runnable onBackToLogin = () -> {
            primaryStage.getScene().setRoot(vistaLogin);
            primaryStage.setTitle("CMS Login");
        };

        // Acción de volver a login de Admin y Editor
        vistaAdmin.setOnBack(onBackToLogin);
        vistaEditor.setOnBack(onBackToLogin);

        // Mostrar escena inicial Login
        Scene scene = new Scene(vistaLogin, 400, 300); 
        primaryStage.setTitle("CMS Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}