package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import modelo.DiscoDuro;
import modelo.Memoria;
import modelo.Pantallas;
import modelo.Repuesto;
import modelo.ServicioTecn;

public class VistaAgregarProductoInventarioController implements Initializable {

    @FXML
    private Button btnAgregar;
    @FXML
    private TextField codProducto;
    @FXML
    private TextField modeloProducto;
    @FXML
    private TextField marcaProducto;
    @FXML
    private TextField cantidad;
    @FXML
    private TextField precio;
    private ObservableList<Repuesto> inventario;
    @FXML
    private TableView<Repuesto> tablaInventario;
    @FXML
    private TableColumn colCodi;
    @FXML
    private TableColumn colModelo;
    @FXML
    private TableColumn colMarca;
    @FXML
    private TableColumn colPrecio;
    @FXML
    private TableColumn colStock;
    @FXML
    private MenuButton tipoProd;
    @FXML
    private TextField tamPantalla;
    @FXML
    private TextField resPantalla;
    @FXML
    private TextField panelPantalla;
    @FXML
    private TextField capDiscoDuro;
    @FXML
    private TextField veloDiscoDuro;
    @FXML
    private TextField tamañoDiscoDuro;
    @FXML
    private TextField capMemoria;
    @FXML
    private TextField frecMemoria;
    @FXML
    private TextField tipoMemoria;
    @FXML
    private MenuItem pantalla;
    @FXML
    private MenuItem discoDuro;
    @FXML
    private MenuItem memoria;
    @FXML
    private Pane MainPanel;
      
    private ServicioTecn miServ = ServicioTecn.getInstance();
    @FXML
    private Button volver;

    @Override
    public void initialize(URL url, ResourceBundle rb) { // se inicializan las columnas de la tabla del inventario.
        inventario = FXCollections.observableArrayList(miServ.getInventario()); // se inicializa la lista observable para con los productos de inventario.
        this.tablaInventario.setItems(inventario); // se setean los productos en la tabla.
        
         this.colCodi.setCellValueFactory(new PropertyValueFactory("codigo")); 
         this.colModelo.setCellValueFactory(new PropertyValueFactory("modelo"));
         this.colMarca.setCellValueFactory(new PropertyValueFactory("marca"));
         this.colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
         this.colStock.setCellValueFactory(new PropertyValueFactory("stock"));   
         
         this.cantidad.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        
    }    
    
    public void closeWindows() throws IOException { // Aqui se cierra la ventana y se vuelve a llamar a la main.
        CargarEscena cargar = new CargarEscena("/vista/VistaMain.fxml", "Menú Principal Servicio Tecnico");
        cargar.cargarEscena((Stage) this.volver.getScene().getWindow());
    }
    
    private Repuesto crearProducto() { // se crea un objeto de repuesto, para luego agregarlo a la lista del inventario
        Repuesto nuevo;
        if (this.resPantalla.isVisible()) {
          nuevo = new Pantallas(Integer.parseInt(this.tamPantalla.getText()), this.resPantalla.getText(), 
                    this.panelPantalla.getText(),Integer.parseInt(this.codProducto.getText()), this.modeloProducto.getText(), this.marcaProducto.getText(),
                    Integer.parseInt(this.cantidad.getText()), Integer.parseInt(this.precio.getText()));
          return nuevo;
        }
        
        if (this.capMemoria.isVisible()) {
          nuevo = new Memoria(Integer.parseInt(this.frecMemoria.getText()), this.tipoMemoria.getText(),
                    Integer.parseInt(this.capMemoria.getText()),Integer.parseInt(this.codProducto.getText()), this.modeloProducto.getText(), this.marcaProducto.getText(),
                    Integer.parseInt(this.cantidad.getText()), Integer.parseInt(this.precio.getText()));
            return nuevo;
        }
        
        if (this.capDiscoDuro.isVisible()) {
          nuevo = new DiscoDuro(Integer.parseInt(this.capDiscoDuro.getText()), 
                    Integer.parseInt(this.veloDiscoDuro.getText()), Integer.parseInt(this.tamañoDiscoDuro.getText()), Integer.parseInt(this.codProducto.getText()) ,
                    this.modeloProducto.getText(), this.marcaProducto.getText(), Integer.parseInt(this.cantidad.getText()), Integer.parseInt(this.precio.getText()));
          return nuevo;
        }
        
        return null;
    }

    @FXML
    private void agregarProducto(ActionEvent event) {
        Repuesto nuevo = crearProducto();
            if (miServ.agregarRepuesto(nuevo)) {
                inventario.add(nuevo);
                for (int i = 0; this.MainPanel.getChildren().size() > i ; i++) {
                    if (this.MainPanel.getChildren().get(i).getClass().getName().equals("javafx.scene.control.TextField")) {
                        TextField caja = (TextField) this.MainPanel.getChildren().get(i);
                        caja.clear();
                    }
                }

                this.miServ.describirProd();
            }
    }

    @FXML
    private void eliminarProducto(ActionEvent event) { // metodo eliminar producto del inventario
        if (this.tablaInventario.getSelectionModel().getSelectedItem() != null) { // Se verifica que haya un item seleccionado
              miServ.eliminarProducto(this.tablaInventario.getSelectionModel().getSelectedItem().getCodigo()); // se elimina desde el arraylist del modelo
             inventario.remove(tablaInventario.getSelectionModel().getSelectedItem()); // se elimina de la tabla de productos
        }
    }

    @FXML
    private void eventKey(KeyEvent event) { // valida q solo se perminan números en los campos codigo, precio y cantidad.
        
        Object evt = event.getTarget();
        
        if (evt.equals(this.codProducto)) {
            if (!Character.isDigit(event.getCharacter().charAt(0))) {
                event.consume();
            }
        } else if (evt.equals(this.cantidad)) {
             if (!Character.isDigit(event.getCharacter().charAt(0))) {
                event.consume();
            }
        } else if (evt.equals(this.precio)) {
            if (!Character.isDigit(event.getCharacter().charAt(0))) {
                event.consume();
            }
        }
    }
    
    private void ocultarPantalla() {
        this.tamPantalla.setVisible(false);
        this.resPantalla.setVisible(false);
        this.panelPantalla.setVisible(false);
    }
    
    private void mostrarPantalla() {
        this.ocultarDisco();
        this.ocultarMemoria();
        this.tamPantalla.setVisible(true);
        this.resPantalla.setVisible(true);
        this.panelPantalla.setVisible(true);
    }
    
    @FXML
    private void mostAddPanta(ActionEvent event) {
        this.mostrarPantalla();
        
    }
    
    private void ocultarDisco() {
        this.capDiscoDuro.setVisible(false);
        this.tamañoDiscoDuro.setVisible(false);
        this.veloDiscoDuro.setVisible(false);
    }
    
    private void mostrarDiscoDuro() {
        this.ocultarPantalla();
        this.ocultarMemoria();
        this.capDiscoDuro.setVisible(true);
        this.tamañoDiscoDuro.setVisible(true);
        this.veloDiscoDuro.setVisible(true);
    }

    @FXML
    private void mostrarAddDiscDur(ActionEvent event) {
        this.mostrarDiscoDuro();
    }
    
    private void ocultarMemoria() {
        this.capMemoria.setVisible(false);
        this.frecMemoria.setVisible(false);
        this.tipoMemoria.setVisible(false);
    }
    
    private void mostrarMemoria() {
        this.ocultarDisco();
        this.ocultarPantalla();
        this.capMemoria.setVisible(true);
        this.frecMemoria.setVisible(true);
        this.tipoMemoria.setVisible(true);
    }

    @FXML
    private void mostrarAddMemoria(ActionEvent event) {
        this.mostrarMemoria();
    }

    @FXML
    private void volver(ActionEvent event) throws IOException {
        closeWindows();
    }
}
