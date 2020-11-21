package controlador;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.OrdenServicio;


public class ModificarOrdenController implements Initializable {
    
    @FXML
    private TextField mailC;
    @FXML
    private TextField telefonoC;
    @FXML
    private TextField rutC;
    @FXML
    private TextField apellidoC;
    @FXML
    private TextField nombreC;
    @FXML
    private DatePicker fechaIng;
    @FXML
    private DatePicker fechaEntrega;
    
    private OrdenServicio modOrden;
    @FXML
    private Button modificar;
    @FXML
    private Label labelOrden;
    private int numOrd;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void recibe(OrdenServicio ordenMod) {
        labelOrden.setText("Orden N°" + ordenMod.getCodigo());
        numOrd = ordenMod.getCodigo();
        this.fechaEntrega.setValue(LocalDate.now());
        this.fechaIng.setValue(ordenMod.getFechaRecepcion());
        this.apellidoC.setText(ordenMod.getCliente().getApellido());
        this.nombreC.setText(ordenMod.getCliente().getNombres());
        this.rutC.setText(ordenMod.getCliente().getRut());
        this.telefonoC.setText(ordenMod.getCliente().getTelefono());
        this.mailC.setText(ordenMod.getCliente().getEmail());
    }

    @FXML
    private void modificarOrden(ActionEvent event) {
        modOrden = new OrdenServicio(numOrd, fechaIng.getValue(),
        fechaEntrega.getValue(),rutC.getText(), nombreC.getText(), apellidoC.getText(), mailC.getText(), telefonoC.getText());
        
        Stage stage = (Stage) this.modificar.getScene().getWindow();
        stage.close();
    }

    public OrdenServicio getModOrden() {
        return modOrden;
    }
}
