package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import modelo.*;

public class VistaMostrarOrdenesController implements Initializable {
    @FXML
    private TableView<OrdenServicio> tableOrdenes;
    @FXML
    private TableColumn colNumOrd;
    @FXML
    private TableColumn colFechaIng;
    @FXML
    private TableColumn colFechaEntr;
    @FXML
    private Button btnCerrar;
    @FXML
    private TableView<Dispositivo> tablaVistaDisp;
    @FXML
    private TableColumn colTipo;
    @FXML
    private TableColumn colModelo;
    @FXML
    private TableColumn colMarca;
    @FXML
    private TableColumn colAnalisis;
    @FXML
    private Label labelNom;
    @FXML
    private Label labelRut;
    @FXML
    private Label labelFono;
    @FXML
    private Label labelEmail;
    @FXML
    private TableColumn precioTotal;
    @FXML
    private TableColumn colRep;
    @FXML
    private TableColumn colPrecio;
    @FXML
    private DatePicker fecgaHasta;
    @FXML
    private DatePicker fechaDesde;
    @FXML
    private TextField cantDesde;
    @FXML
    private TextField cantHasta;
        
    private ObservableList<Dispositivo> dispositivos;
    private ServicioTecn miserv = ServicioTecn.getInstance();
    private ObservableList<OrdenServicio> ordenesObs;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.ordenesObs = miserv.getOrdenes();
        tablaVistaDisp.setItems(dispositivos);
        tableOrdenes.setItems(ordenesObs);
        
         this.colNumOrd.setCellValueFactory(new PropertyValueFactory("codigo"));
         this.colFechaIng.setCellValueFactory(new PropertyValueFactory("fechaRecepcion"));
         this.colFechaEntr.setCellValueFactory(new PropertyValueFactory("fechaEntrega"));
         this.precioTotal.setCellValueFactory(new PropertyValueFactory("precioTotal"));
         
         inicializarLabel();
        
         this.colTipo.setCellValueFactory(new PropertyValueFactory("tipoDispositivo")); 
         this.colModelo.setCellValueFactory(new PropertyValueFactory("modelo"));
         this.colMarca.setCellValueFactory(new PropertyValueFactory("marca"));
         this.colAnalisis.setCellValueFactory(new PropertyValueFactory("diagnostico"));
         this.colRep.setCellValueFactory(new PropertyValueFactory("modeloRepuesto"));
         this.colPrecio.setCellValueFactory(new PropertyValueFactory("precioRepuesto"));
         
    }    
    
    private void inicializarLabel() {
         labelNom.setText("Nombre cliente:\t");
         labelRut.setText("Rut Cliente:\t");
         labelFono.setText("Telefono cliente:\t");
         labelEmail.setText("E-mail cliente:\t" );
    }
    
    public void closeWindows() throws IOException {
        CargarEscena cargar = new CargarEscena();
        cargar.cargarEscena("/vista/VistaMain.fxml", "Menú Principal Servicio Tecnico", (Stage) this.btnCerrar.getScene().getWindow());
    }

    @FXML
    private void cerrar(ActionEvent event) throws IOException {
        closeWindows();
    }
    
    private void listarDispositivos () {
        tablaVistaDisp.setItems(dispositivos);
    }
    
    public void setCliente(Cliente cliente) {
          labelNom.setText("Nombre cliente:\t" + cliente.getNombres() +" " +  cliente.getApellido());
          labelRut.setText("Rut Cliente:\t" +cliente.getRut());
          labelFono.setText("Telefono cliente:\t" + cliente.getTelefono());
          labelEmail.setText("E-mail cliente:\t" + cliente.getEmail());
    }

    @FXML
    private void select(javafx.scene.input.MouseEvent event) {
        if (!tableOrdenes.getSelectionModel().isEmpty()) {
            dispositivos = tableOrdenes.getSelectionModel().getSelectedItem().getDispositivos();
            setCliente(tableOrdenes.getSelectionModel().getSelectedItem().getCliente());
            listarDispositivos();
        }
        
    }

    @FXML
    private void modificiarDispositivo(ActionEvent event) throws IOException {
        if (this.tablaVistaDisp.getSelectionModel().getSelectedItem() != null) {
            
            CargarEscena escena = new CargarEscena("/vista/VistaModificarDispositivo.fxml", "Modificar dispositivo");
            VistaModificarDispositivoController controlador = (VistaModificarDispositivoController)escena.getController();
        
            controlador.recibe(this.tablaVistaDisp.getSelectionModel().getSelectedItem());

           escena.cargarEscena();
           if (controlador.getDispositivo() != null) {
              this.miserv.modificarDispo(this.tableOrdenes.getSelectionModel().getSelectedItem().getCodigo(), controlador.getDispositivo(), this.tablaVistaDisp.getSelectionModel().getSelectedItem());
              tablaVistaDisp.refresh();
           }

        }
    }

    @FXML
    private void eliminarDispositiivo(ActionEvent event) {
        if (this.tablaVistaDisp.getSelectionModel().getSelectedItem() != null) {
            miserv.eliminarDispo(tableOrdenes.getSelectionModel().getSelectedItem().getCodigo(), tablaVistaDisp.getSelectionModel().getSelectedItem());
            dispositivos.remove(this.tablaVistaDisp.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void eliminarOrden(ActionEvent event) {
         if (tableOrdenes.getSelectionModel().getSelectedItem() != null) {
         this.miserv.eliminarOrdenServicio(tableOrdenes.getSelectionModel().getSelectedItem().getCodigo());
         inicializarLabel();
         dispositivos.clear();
         ordenesObs.remove(tableOrdenes.getSelectionModel().getSelectedItem());
        } 
    }
    
    
    // modifica la orden
    @FXML
    private void modOrden(ActionEvent event) throws IOException {
        if (this.tableOrdenes.getSelectionModel().getSelectedItem() != null) {
            CargarEscena escena = new CargarEscena("/vista/modificarOrden.fxml", "Modificar orden");
            ModificarOrdenController controlador = (ModificarOrdenController)escena.getController();
            controlador.recibe(this.tableOrdenes.getSelectionModel().getSelectedItem()); // envia la orden a modificar al controlador de la vista modificar orden
            escena.cargarEscena(); // carga la escena
            if (controlador.getModOrden() != null) {
                miserv.modificarOrden(this.tableOrdenes.getSelectionModel().getSelectedItem().getCodigo(), controlador.getModOrden());
                tableOrdenes.refresh();
                setCliente(tableOrdenes.getSelectionModel().getSelectedItem().getCliente());
            }
        }
    }

    @FXML
    private void eventKey(KeyEvent event) { // Aquí se añade una restriccion para que solo se agreguen numeros en el campo del filtrado por numero de dispositivos.
          Object evt = event.getTarget();
          if (evt.equals(this.cantDesde)) {
              if (!Character.isDigit(event.getCharacter().charAt(0))) {
                  event.consume();
              }
          } else if (evt.equals(this.cantHasta)) {
              if (!Character.isDigit(event.getCharacter().charAt(0))) {
                  event.consume();
              }
          }
    }

    @FXML
    private void filtroCantDispo(ActionEvent event) {
        ArrayList<OrdenServicio> nuevo = new ArrayList<>();
        if (this.cantDesde.getText().isEmpty() && this.cantHasta.getText().isEmpty()) {
            nuevo = this.miserv.listaOrdenes();
        } else {
            nuevo = this.miserv.filtrar(Integer.parseInt(this.cantDesde.getText()), Integer.parseInt(this.cantHasta.getText()));
        }
         this.tableOrdenes.setItems(FXCollections.observableArrayList(nuevo));
    }

    @FXML
    private void filtrarFechas(ActionEvent event) {
        ArrayList<OrdenServicio> ordenesFiltradas = new ArrayList();
        if (this.fechaDesde.getValue() == null && this.fecgaHasta.getValue() ==  null) {
                        ordenesFiltradas = this.miserv.listaOrdenes();
        } else {
             ordenesFiltradas = this.miserv.filtrar(this.fechaDesde.getValue(), this.fecgaHasta.getValue());
        }
         this.tableOrdenes.setItems(FXCollections.observableArrayList(ordenesFiltradas));
    }

    @FXML
    private void getClienteMayorDispositivos(ActionEvent event) throws IOException {
            CargarEscena escena = new CargarEscena("/vista/mostrarCliente.fxml", "Cliente Con Mayor Cantidad De Dispositivos");
            escena.cargarEscena();
    }
}
