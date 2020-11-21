package controlador;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import modelo.ServicioTecn;

public class VistaGenerarReporteController implements Initializable {

    @FXML
    private DatePicker fechaDesde;
    @FXML
    private DatePicker fechaHasta;
    private ServicioTecn miserv = ServicioTecn.getInstance();
    @FXML
    private Button generar;
    @FXML
    private Button volver;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void generarReporte(ActionEvent event) throws IOException {
        LocalDate desde = this.fechaDesde.getValue();
        LocalDate hasta = this.fechaHasta.getValue();
        
        this.miserv.generarReporteMensual(desde, hasta);
        closeWindow(this.generar);
    }
    
    public void closeWindow(Button btn) throws IOException {
        CargarEscena cargar = new CargarEscena("/vista/VistaMain.fxml", "Menú Principal Servicio Tecnico");
        cargar.cargarEscena((Stage) btn.getScene().getWindow());
    }

    @FXML
    private void volver(ActionEvent event) throws IOException {
        closeWindow(this.volver);
    }
}
