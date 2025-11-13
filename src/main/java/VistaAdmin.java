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
    private ArrayList<Contenido> listaOriginal;

    // Botones
    private final Button btnPublicar = new Button("Publicar");
    private final Button btnEliminar = new Button("Eliminar");
    private final Button btnReporte = new Button("Generar Reporte");
    private final Button btnCerrarSesion = new Button("Volver al Login");
    private final Button btnVisualizar = new Button("Visualizar");
    private final Button btnDescargar = new Button("Descargar");

    // Callbacks (para que el controlador asigne la lógica)
    private Runnable onPublicar;
    private Runnable onEliminar;
    private Runnable onReporte;
    private Runnable onCerrarSesion;
    private Runnable onVisualizar;
    private Runnable onDescargar;
    private Runnable onBack;


    public VistaAdmin() {
        setPadding(new Insets(24));
        setSpacing(16);
        setAlignment(Pos.CENTER);

        // Estilo del título
        lblTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        // Configuración del filtro
        cbFiltro.getItems().addAll("Todos", "Video", "Imagen", "Articulo");
        cbFiltro.setValue("Todos");

        HBox filtroBox = new HBox(10, new Label("Filtrar por tipo:"), cbFiltro);
        filtroBox.setAlignment(Pos.CENTER);

        // Configuración de la tabla
        TableColumn<Contenido, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Contenido, String> colDescripcion = new TableColumn<>("Descripción");
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        TableColumn<Contenido, String> colVisibilidad = new TableColumn<>("Estado");
        colVisibilidad.setCellValueFactory(new PropertyValueFactory<>("estado"));
        
        TableColumn<Contenido, Integer> colVistas = new TableColumn<>("Vistas");
        colVistas.setCellValueFactory(new PropertyValueFactory<>("vistas"));

        tablaContenidos.getColumns().addAll(colNombre, colDescripcion, colVisibilidad, colVistas);
        tablaContenidos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        tablaContenidos.setPrefHeight(400);

        // Botonoes superiores
        HBox botonesBox = new HBox(15, btnPublicar, btnEliminar, btnReporte);
        botonesBox.setAlignment(Pos.CENTER);

        // Configurar HBox de Acciones
        HBox sectorAcciones = new HBox(15, btnVisualizar, btnDescargar);
        sectorAcciones.setAlignment(Pos.CENTER);

        // Listeners de los botones
        cbFiltro.setOnAction(e -> aplicarFiltro());
        btnPublicar.setOnAction(e -> { 
            if (onPublicar != null) onPublicar.run(); 
        });
        btnEliminar.setOnAction(e -> { 
            if (onEliminar != null) onEliminar.run(); 
        });
        btnReporte.setOnAction(e -> { 
            if (onReporte != null) onReporte.run(); 
        });
        btnVisualizar.setOnAction(e -> {
            if (onVisualizar != null) onVisualizar.run();
        });

        btnDescargar.setOnAction(e -> {
            if (onDescargar != null) onDescargar.run();
        });
        btnCerrarSesion.setOnAction(e -> { 
            if (onCerrarSesion != null) onCerrarSesion.run(); 
        });

        // Estructura general
        getChildren().addAll(lblTitulo, filtroBox, botonesBox, tablaContenidos, sectorAcciones, btnCerrarSesion);

        btnCerrarSesion.setOnAction(e -> { 
            if (onBack != null) onBack.run(); 
        });
    }

    // Setters para acciones
    public void setOnPublicar(Runnable onPublicar) {
        this.onPublicar = onPublicar;
    }
    public void setOnEliminar(Runnable onEliminar) { 
        this.onEliminar = onEliminar; 
    }
    public void setOnReporte(Runnable onReporte) { 
        this.onReporte = onReporte; 
    }
    public void setOnCerrarSesion(Runnable onCerrarSesion) { 
        this.onCerrarSesion = onCerrarSesion; 
    }
    public void setOnVisualizar(Runnable onVisualizar){
        this.onVisualizar = onVisualizar;
    }
    public void setOnDescargar(Runnable onDescargar){
        this.onDescargar = onDescargar;
    }
    public void setOnBack(Runnable onBack) {
        this.onBack = onBack;
    }

    // Getters
    public Contenido getContenidoSeleccionado() {
        return tablaContenidos.getSelectionModel().getSelectedItem();
    }

    public String getFiltroSeleccionado() {
        return cbFiltro.getValue();
    }

    // Actualiza los contenidos mostrados en la tabla
    public void actualizarTabla(ArrayList<Contenido> contenidos) {
        this.listaOriginal = new ArrayList<>(contenidos); // Guarda la lista completa
        aplicarFiltro(); // Aplica filtro actual
    }
    
    //Método para aplicar el filtro
    private void aplicarFiltro() {
        if (listaOriginal == null) return;

        String filtro = cbFiltro.getValue();
        ArrayList<Contenido> filtrados = new ArrayList<>();

        for (Contenido c : listaOriginal) {
            String tipo = c.getClass().getSimpleName();
            if (filtro.equals("Todos") || tipo.equalsIgnoreCase(filtro)) {
                filtrados.add(c);
            }
        }

        tablaContenidos.getItems().setAll(filtrados);
    }

    // Mostrar alertas
    public void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    // Mostrar reporte en ventana aparte 
    public void mostrarReporte(String reporte) {
        TextArea area = new TextArea(reporte);
        area.setEditable(false);
        area.setWrapText(true);
        area.setFont(Font.font("Consolas", 13));

        Alert ventanaReporte = new Alert(Alert.AlertType.INFORMATION);
        ventanaReporte.setTitle("Reporte General del Sistema");
        ventanaReporte.setHeaderText("Resultados del reporte");
        ventanaReporte.getDialogPane().setContent(area);
        ventanaReporte.getDialogPane().setPrefSize(600, 400); // ancho, alto
        ventanaReporte.showAndWait();
    }

}