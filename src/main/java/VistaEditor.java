//Jose Pinto
//Valeria Hernandez
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory; // Reemplaza a ListView
import javafx.scene.layout.HBox; // Para las columnas
import javafx.scene.layout.VBox; // Para conectar los datos

public class VistaEditor extends VBox {

    // Botones de Acción
    private final Button btnVolver = new Button("Volver al Login");
    private final Button btnEditar = new Button("Cargar para Editar");
    private final Button btnGuardar = new Button("Guardar Contenido"); 
    private final Button btnReporte = new Button("Generar reporte");

    private final HBox sectorBotones = new HBox();
    
    // Formulario 
    private final Label lblFormularioTitulo = new Label("Detalles del Contenido");
    private final TextField txtNombre = new TextField();
    private final TextField txtDescripcion = new TextField();
    private final ComboBox<String> cmbTipo = new ComboBox<>();
    
    // --- CAMBIO DE LISTVIEW A TABLEVIEW ---
    private final Label lblListaTitulo = new Label("Contenido Existente");
    private final TableView<Contenido> tablaContenido = new TableView<>(); // Era: listaContenidoView
    
    // Callbacks para el Controlador
    private Runnable onBack;
    private Runnable onGuardar; 
    private Runnable onEditar; 
    private Runnable onReporte;

    public VistaEditor() {
        setPadding(new Insets(24));
        setSpacing(16);
        setAlignment(Pos.CENTER);
        
        Label titulo = new Label("VISTA EDITOR");
        Label descripcion = new Label("Para crear contenido, ingrese los datos de la nueva publicacion y luego");
        titulo.setAlignment(Pos.CENTER);
        descripcion.setAlignment(Pos.CENTER);

        // Configurar HBox de Botones
        sectorBotones.setAlignment(Pos.CENTER);
        sectorBotones.setSpacing(10);
        sectorBotones.getChildren().addAll(btnGuardar, btnEditar, btnReporte); 

        // Configurar Formulario
        cmbTipo.getItems().addAll("Articulo", "Imagen", "Video");
        cmbTipo.setValue("Articulo"); 
        cmbTipo.setMaxWidth(Double.MAX_VALUE);
        txtNombre.setPromptText("Nombre del contenido");
        txtDescripcion.setPromptText("Descripción");

        // Configuración de TableView
        TableColumn<Contenido, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        
        TableColumn<Contenido, String> colDescripcion = new TableColumn<>("Descripción");
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        
        TableColumn<Contenido, Integer> colVistas = new TableColumn<>("Vistas");
        colVistas.setCellValueFactory(new PropertyValueFactory<>("vistas"));
        // Añadir las columnas a la tabla
        tablaContenido.getColumns().addAll(colNombre, colDescripcion, colVistas);
        // Hacer que las columnas ocupen el espacio disponible
        tablaContenido.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        

        // Añadir todo a la vista principal 
        getChildren().addAll(
            titulo,
            descripcion,
            lblFormularioTitulo,
            txtNombre,
            txtDescripcion,
            cmbTipo,
            sectorBotones,
            lblListaTitulo,
            tablaContenido, 
            btnVolver
        );
        
        //Asignar Acciones 
        btnVolver.setOnAction(e -> { 
            if (onBack != null) onBack.run(); 
        });
        
        btnGuardar.setOnAction(e -> { 
            if (onGuardar != null) onGuardar.run(); 
        });
        
        btnEditar.setOnAction(e -> { 
            if (onEditar != null) onEditar.run(); 
        });

        btnReporte.setOnAction(e -> {
            if (onReporte != null) onReporte.run();
        });
    }
    
    // Setters para el Controlador
    
    public void setOnBack(Runnable onBack) {
        this.onBack = onBack;
    }
    
    public void setOnGuardar(Runnable onGuardar) {
        this.onGuardar = onGuardar;
    }
    
    public void setOnEditar(Runnable onEditar) {
        this.onEditar = onEditar;
    }
    public void setOnReporte(Runnable onReporte){
        this.onReporte = onReporte;
    }
    
    // Getters para que el Controlador lea los datos

    public String getNombreContenido() {
        return txtNombre.getText();
    }
    
    public String getDescripcionContenido() {
        return txtDescripcion.getText();
    }
    
    public String getTipoContenido() {
        return cmbTipo.getValue();
    }
    
    // Obtener selección de la Tabla
    public Contenido getContenidoSeleccionado() {
        return tablaContenido.getSelectionModel().getSelectedItem();
    }
    
    // Limpiar formulario
    public void limpiarFormulario() {
        txtNombre.clear();
        txtDescripcion.clear();
        cmbTipo.setValue("Articulo");
        cmbTipo.setDisable(false); // Habilitar por si estaba deshabilitado
    }

    public void cargarDatosFormulario(Contenido c) {
        txtNombre.setText(c.getNombre());
        txtDescripcion.setText(c.getDescripcion());
        
        if (c instanceof Articulo) cmbTipo.setValue("Articulo");
        else if (c instanceof Imagen) cmbTipo.setValue("Imagen");
        else if (c instanceof Video) cmbTipo.setValue("Video");
        cmbTipo.setDisable(true); // No se puede cambiar el tipo al editar
    }
    
    // Actualizar la Tabla
    public void actualizarLista(ArrayList<Contenido> contenidos) {
        tablaContenido.getItems().clear();
        tablaContenido.getItems().addAll(contenidos);
    }
}