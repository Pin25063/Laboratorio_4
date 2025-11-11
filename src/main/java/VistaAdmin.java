// Jose Pinto
// Valeria Hernandez
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class VistaAdmin extends VBox {
    // Elementos principales
    private final Label lblTitulo = new Label("Panel del Administrador");
    private final ComboBox<String> cbFiltro = new ComboBox<>();
    private final TableView<Contenido> tablaContenidos = new TableView<>();

    private Runnable onBack;

    // Botones
    private final Button btnPublicar = new Button("Publicar");
    private final Button btnEliminar = new Button("Eliminar");
    private final Button btnReporte = new Button("Generar Reporte");
    private final Button btnCerrarSesion = new Button("Cerrar Sesión");

    // Callbacks (para que el controlador asigne la lógica)
    private Runnable onPublicar;
    private Runnable onEliminar;
    private Runnable onReporte;
    private Runnable onCerrarSesion;

    public VistaAdmin() {
        setPadding(new Insets(24));
        setSpacing(16);
        setAlignment(Pos.CENTER);

        // Estilo del título
        lblTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        // Configuración del filtro
        cbFiltro.getItems().addAll("Todos", "Video", "Imagen", "Artículo");
        cbFiltro.setValue("Todos");

        HBox filtroBox = new HBox(10, new Label("Filtrar por tipo:"), cbFiltro);
        filtroBox.setAlignment(Pos.CENTER);

        // Configuración de la tabla
        TableColumn<Contenido, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Contenido, String> colAutor = new TableColumn<>("Autor");
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));

        TableColumn<Contenido, String> colDescripcion = new TableColumn<>("Descripción");
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        TableColumn<Contenido, String> colVisibilidad = new TableColumn<>("Visibilidad");

        colVisibilidad.setCellValueFactory(new PropertyValueFactory<>("visible"));
        TableColumn<Contenido, Integer> colVistas = new TableColumn<>("Vistas");
        colVistas.setCellValueFactory(new PropertyValueFactory<>("vistas"));

        tablaContenidos.getColumns().addAll(colNombre, colAutor, colDescripcion, colVisibilidad, colVistas);
        tablaContenidos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        tablaContenidos.setPrefHeight(400);

        // Botonoes inferiores
        HBox botonesBox = new HBox(15, btnPublicar, btnEliminar, btnReporte, btnCerrarSesion);
        botonesBox.setAlignment(Pos.CENTER);

        // Estilos básicos de botones
        btnPublicar.setStyle("-fx-background-color: #0e853fff; -fx-text-fill: white; -fx-font-weight: bold;");
        btnEliminar.setStyle("-fx-background-color: #d12a17ff; -fx-text-fill: white; -fx-font-weight: bold;");
        btnReporte.setStyle("-fx-background-color: #34495eff; -fx-text-fill: white; -fx-font-weight: bold;");
        btnCerrarSesion.setStyle("-fx-background-color: #7f8c8dff; -fx-text-fill: white; -fx-font-weight: bold;");

        // Listeners de los botones
        btnPublicar.setOnAction(e -> { if (onPublicar != null) onPublicar.run(); });
        btnEliminar.setOnAction(e -> { if (onEliminar != null) onEliminar.run(); });
        btnReporte.setOnAction(e -> { if (onReporte != null) onReporte.run(); });
        btnCerrarSesion.setOnAction(e -> { if (onCerrarSesion != null) onCerrarSesion.run(); });
        // Estructura general
        getChildren().addAll(lblTitulo, filtroBox, tablaContenidos, botonesBox);

        btnCerrarSesion.setOnAction(e -> { 
            if (onBack != null) onBack.run(); 
        });
    }

    // --- Setters para asignar acciones ---
    public void setOnPublicar(Runnable onPublicar) { this.onPublicar = onPublicar; }
    public void setOnEliminar(Runnable onEliminar) { this.onEliminar = onEliminar; }
    public void setOnReporte(Runnable onReporte) { this.onReporte = onReporte; }
    public void setOnCerrarSesion(Runnable onCerrarSesion) { this.onCerrarSesion = onCerrarSesion; }

    // --- Getters ---
    public Contenido getContenidoSeleccionado() {
        return tablaContenidos.getSelectionModel().getSelectedItem();
    }

    public String getFiltroSeleccionado() {
        return cbFiltro.getValue();
    }

    // --- Actualiza los contenidos mostrados ---
    public void actualizarTabla(ArrayList<Contenido> contenidos) {
        tablaContenidos.getItems().setAll(contenidos);
    }

    // --- Muestra una alerta simple ---
    public void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    // --- Mostrar reporte en ventana aparte ---
    public void mostrarReporte(String reporte) {
        TextArea area = new TextArea(reporte);
        area.setEditable(false);
        area.setWrapText(true);
        area.setFont(Font.font("Consolas", 13));

        Alert ventanaReporte = new Alert(Alert.AlertType.INFORMATION);
        ventanaReporte.setTitle("Reporte General del Sistema");
        ventanaReporte.setHeaderText("Resultados del reporte");
        ventanaReporte.getDialogPane().setContent(area);
        ventanaReporte.showAndWait();
    }

    public void setOnBack(Runnable onBack) {
        this.onBack = onBack;
    }
}