 package controlador;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.*;

public class VistaAgregarOrdenController implements Initializable {

    @FXML
    private TextField nombreC;
    @FXML
    private TextField apellidoC;
    @FXML
    private TextField rutC;
    @FXML
    private TextField telefonoC;
    @FXML
    private TextField mailC;
    @FXML
    private TextField modeloD;
    @FXML
    private TextField marcaD;
    @FXML
    private ComboBox<String> tipoD;
    @FXML
    private TextArea analisisD;
    @FXML
    private Button btnCrear;
    @FXML
    private TableColumn colTipo;
    @FXML
    private TableColumn colModelo;
    @FXML
    private TableColumn colMarca;
    @FXML
    private TableColumn colAnalisis;
    @FXML
    private TableView<Dispositivo> tablaVista;
    @FXML
    private Label labelTitulo;
    @FXML
    private Button agregarDispos;
    @FXML
    private ComboBox<Repuesto> listaRepuesto;
    @FXML
    private TableColumn colRep;
    @FXML
    private TableColumn colPrecio;
    @FXML
    private RadioButton formateo;
    @FXML
    private RadioButton limpieza;
    @FXML
    private Pane dispoPane;
    @FXML
    private DatePicker fechaEntrega;
    @FXML
    private Button volver;
    
    private ObservableList<Dispositivo> dispositivos;
    private ServicioTecn serv = ServicioTecn.getInstance();     
    OrdenServicio nuevaOrden;
    
    @Override 
    public void initialize(URL url, ResourceBundle rb) {
        fechaEntrega.setDayCellFactory(picker -> { // descativa fechas anteriores a la actual
            return new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();
                    setDisable(empty || date.compareTo(today) <= 0 );
                }
            };  });
        
        labelTitulo.setText("Orden Servicio N° " + (serv.cantidadOrdnes() + 1)); // se setea la el numero de orden que se ingresa.
        
        listaRepuesto.setItems(serv.getInventario()); // se enlistan los repuestos
        dispositivos = FXCollections.observableArrayList(); //inicializacion lista observable de dispositivos
        
        this.colTipo.setCellValueFactory(new PropertyValueFactory("tipoDispositivo")); 
        this.colModelo.setCellValueFactory(new PropertyValueFactory("modelo"));
        this.colMarca.setCellValueFactory(new PropertyValueFactory("marca"));
        this.colAnalisis.setCellValueFactory(new PropertyValueFactory("diagnostico"));
        this.colRep.setCellValueFactory(new PropertyValueFactory("modeloRepuesto"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory("precioRepuesto"));

        ObservableList<String> tipos = FXCollections.observableArrayList("Notebook", "Celular", "PC Escritorio", "Otros"); // inicializacion lista de tipos de dispositivos permitidos
        tipoD.setItems(tipos);
    }
    
    @FXML
    private void crearDisp(ActionEvent event) {
        if (this.listaRepuesto.getValue() != null || this.formateo.isSelected() || this.limpieza.isSelected()) {
            if (this.modeloD.getText().isEmpty() && this.marcaD.getText().isEmpty()){
                nuevaOrden.agregarDispositivo(tipoD.getValue(), analisisD.getText(), listaRepuesto.getValue(), this.formateo.isSelected(), this.limpieza.isSelected());
            } else if (this.marcaD.getText().isEmpty()) {
                 nuevaOrden.agregarDispositivo(modeloD.getText(), tipoD.getValue(), analisisD.getText(), listaRepuesto.getValue(), this.formateo.isSelected(), this.limpieza.isSelected());
            } else {
                nuevaOrden.agregarDispositivo(modeloD.getText(), marcaD.getText(), tipoD.getValue(), analisisD.getText(), listaRepuesto.getValue(), this.formateo.isSelected(), this.limpieza.isSelected());
           }

            tablaVista.setItems(nuevaOrden.getDispositivos());
            
            tipoD.getSelectionModel().clearSelection();
            listaRepuesto.getSelectionModel().clearSelection();
            this.limpieza.setSelected(false);
            this.formateo.setSelected(false);
            this.modeloD.clear();
            this.marcaD.clear();
            this.analisisD.clear();
        } else {
             Alerta alert = new Alerta();
             alert.mostrarAlerta("POR FAVOR INGRESE UN REPUESTO PARA EL DISPOSITIVO", AlertType.INFORMATION);
        }
    }

    @FXML
    private void crearOrden(ActionEvent event) throws IOException {
         if (rutC.getText().equals("") || nombreC.getText().equals("") ||  this.apellidoC.getText().equals("") || this.telefonoC.getText().equals("")) { // se valide q se haya ingresado los datos del cliente.
             Alerta alert = new Alerta();
             alert.mostrarAlerta("POR FAVOR LLENE LOS CAMPOS DEL CLIENTE", AlertType.WARNING);
        } else {
             if (!this.serv.agregarOrden(nuevaOrden)) {
                 Alerta alert = new Alerta();
                 alert.mostrarAlerta("LA ORDEN NO SE HA PODIDO CREAR", AlertType.WARNING);
             } else {
                 Alerta alert = new Alerta();
                 alert.mostrarAlerta("LA ORDEN SE HA CREADO SATISFACTORIAMENTE", AlertType.CONFIRMATION);
                 closeWindows(this.btnCrear);
             }
        }
    }
    
    public void closeWindows(Button btn) throws IOException {
        CargarEscena cargar = new CargarEscena("/vista/VistaMain.fxml", "Menú Principal Servicio Tecnico");
        cargar.cargarEscena((Stage) btn.getScene().getWindow());
    }

    @FXML
    private void eliminarDisp(ActionEvent event) {
        if (dispositivos.isEmpty())
            return ;
        dispositivos.remove(tablaVista.getSelectionModel().getSelectedItem());    // se elimina dispositivo de la lista obsevable.
    }

    @FXML
    private void registrarCliente(ActionEvent event) {
         String nombre = this.nombreC.getText(), apellido = this.apellidoC.getText(), rut = this.rutC.getText();
         String num = this.telefonoC.getText(), email = this.mailC.getText();
        
         nuevaOrden = new OrdenServicio(serv.cantidadOrdnes() + 1, LocalDate.now(), this.fechaEntrega.getValue(), nombre, apellido, rut, num, email);
        
          for (int i = 0; this.dispoPane.getChildren().size() > i ; i++) {
                        this.dispoPane.getChildren().get(i).setDisable(false);
          }
    }

    @FXML
    private void volver(ActionEvent event) throws IOException {
        closeWindows(volver);
    }
}
 