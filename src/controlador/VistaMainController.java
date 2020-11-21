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
        CargarEscena escena = new CargarEscena("/vista/VistaMostrarOrdenes.fxml", "Mostrar Ordenes");
        
        escena.cargarEscena((Stage) this.btnMostrarOrd.getScene().getWindow());
    }
    

    @FXML
    private void agregarProductoInventario(ActionEvent event) throws IOException {
        CargarEscena escena = new CargarEscena("/vista/VistaAgregarProductoInventario.fxml", "Vista inventario");
        escena.cargarEscena((Stage) this.btnMostrarOrd1.getScene().getWindow());
    }

    @FXML
    private void GenerarReporte(ActionEvent event) throws IOException {
        CargarEscena escena = new CargarEscena("/vista/VistaGenerarReporte.fxml", "Generar reporte de ordenes");
        escena.cargarEscena((Stage) this.btnGenerarReporte.getScene().getWindow());
    }

}
