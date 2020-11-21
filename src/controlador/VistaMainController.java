package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class VistaMainController implements Initializable {

    @FXML
    private Button btnAgregarOrd;
    @FXML
    private Button btnMostrarOrd;
    @FXML
    private Button btnMostrarOrd1;
    @FXML
    private Button btnGenerarReporte;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void agregarOrdenes(ActionEvent event) throws IOException {
        CargarEscena escena = new CargarEscena("/vista/VistaAgregarOrden.fxml", "Agregar Orden");
        escena.cargarEscena((Stage) this.btnAgregarOrd.getScene().getWindow());
    }

    @FXML
    private void mostrarOrdenes(ActionEvent event) throws IOException {
        CargarEscena escena = new CargarEscena();
        escena.cargarEscena("/vista/VistaMostrarOrdenes.fxml", "Mostrar Ordenes", (Stage) this.btnMostrarOrd.getScene().getWindow());
    }
    

    @FXML
    private void agregarProductoInventario(ActionEvent event) throws IOException {
        CargarEscena escena = new CargarEscena();
        escena.cargarEscena("/vista/VistaAgregarProductoInventario.fxml", "Vista inventario", (Stage) this.btnMostrarOrd1.getScene().getWindow());
    }

    @FXML
    private void GenerarReporte(ActionEvent event) throws IOException {
        CargarEscena escena = new CargarEscena();
        escena.cargarEscena("/vista/VistaGenerarReporte.fxml", "Generar reporte de ordenes", (Stage) this.btnGenerarReporte.getScene().getWindow());
    }

}
